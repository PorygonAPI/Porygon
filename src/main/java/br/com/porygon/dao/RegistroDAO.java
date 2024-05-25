package br.com.porygon.dao;

import br.com.porygon.Registro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroDAO {
    private final String url = "jdbc:mysql://localhost:3306/porygon?useTimezone=true&serverTimezone=UTC";
    private final String username = "porygon";
    private final String password = "pesquisador";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Connection to database failed: " + e.getMessage());
        }
        return connection;
    }

    // Realiza a inserção dos dados lidos no banco
    public int salvarRegistro(Timestamp dataHora, int arquivo, String tipoArquivo) {
        Connection con = null;
        int generatedId = -1;

        try {
            con = getConnection();

            String selectSQL = "SELECT id FROM registro WHERE data_hora = ? AND arquivo = ? AND tipo_arquivo = ?";

            try (PreparedStatement selectStmt = con.prepareStatement(selectSQL)) {
                selectStmt.setTimestamp(1, dataHora);
                selectStmt.setInt(2, arquivo);
                selectStmt.setString(3, tipoArquivo);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        // Registro já existe, retorna o ID
                        return rs.getInt("id");
                    }
                }
            }

            String insert_sql = "INSERT INTO registro (data_hora, arquivo, tipo_arquivo) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(insert_sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setTimestamp(1, dataHora);
            pst.setInt(2, arquivo);
            pst.setString(3, tipoArquivo);

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir novo atributo!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
        return generatedId;
    }

    public void salvarInformacao(int registro, String nome, double valor) {
        Connection con = null;

        try {
            con = getConnection();
            String insert_sql = "INSERT INTO reg_informacao (registro, nome, valor) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE valor = values(valor)";
            PreparedStatement pst = con.prepareStatement(insert_sql);
            pst.setInt(1, registro);
            pst.setString(2, nome);
            pst.setDouble(3, valor);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir novo atributo!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
    }


    public void updateSuspectData(int registro, String nomeVariavel, Double novoValor, boolean dadoSuspeito) throws SQLException {
        Connection con = null;
        try {

            con = getConnection();

            String newSQL = "UPDATE reg_informacao\n" +
                    "    SET dado_suspeito = ? AND valor = ?\n" +
                    "    WHERE registro = ? AND nome = ?;";
            PreparedStatement ist = con.prepareStatement(newSQL);
            ist.setBoolean(1, dadoSuspeito);
            ist.setDouble(2, novoValor);
            ist.setInt(3, registro);
            ist.setString(3, nomeVariavel);
            ist.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar dado suspeito!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
    }

    private void verificarVariavel(Connection con, String SQL, int registro, String variavel, String limiteMaior,
            String limiteMenor) throws SQLException {
        PreparedStatement ist = con.prepareStatement(SQL);
        ist.setInt(1, registro);
        ist.setString(2, variavel);
        ist.setDouble(3, Double.parseDouble(limiteMaior));
        ist.setDouble(4, Double.parseDouble(limiteMenor));
        ist.setInt(5, registro);
        ist.setString(6, variavel);
        ist.executeUpdate();
    }

    /**
     * Essa função atualiza a lista de dados apurados
     * 
     * @return ObservableList<Map<String, String>>
     */
    public ObservableList<Map<String, String>> getDadosApurados() {
        Connection con = null;
        ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();

        try {
            con = getConnection();
            String select_sql = "select * from registro";
            PreparedStatement pst = con.prepareStatement(select_sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String sql = "SELECT r.data_hora,\n" +
                        "       r.tipo_arquivo,\n" +
                        "       MAX(CASE WHEN ri.nome = 'tempIns' THEN ri.valor END) AS temperatura,\n" +
                        "       MAX(CASE WHEN ri.nome = 'pressaoIns' THEN ri.valor END) AS pressao,\n" +
                        "       MAX(CASE WHEN ri.nome = 'velVento' THEN ri.valor END) AS velVento,\n" +
                        "       MAX(CASE WHEN ri.nome = 'chuva' THEN ri.valor END) AS chuva,\n" +
                        "       MAX(CASE WHEN ri.nome = 'ptoOrvalhoIns' THEN ri.valor END) AS ptoOrvalho,\n" +
                        "       MAX(CASE WHEN ri.nome = 'umiIns' THEN ri.valor END) AS umidade,\n" +
                        "       MAX(CASE WHEN ri.nome = 'nebulosidade' THEN ri.valor END) AS nebulosidade,\n" +
                        "       MAX(CASE WHEN ri.nome = 'radiacao' THEN ri.valor END) AS radiacao,\n" +
                        "       MAX(CASE WHEN ri.nome = 'dirVento' THEN ri.valor END) AS dirVento,\n" +
                        "       MAX(CASE WHEN ri.nome = 'insolacao' THEN ri.valor END) AS insolacao,\n" +
                        "       MAX(CASE WHEN ri.nome = 'rajVento' THEN ri.valor END) AS rajVento\n" +
                        "FROM registro r\n" +
                        "    LEFT JOIN reg_informacao ri ON r.id = ri.registro\n" +
                        "where r.id = ? and ri.dado_suspeito = false\n" +
                        "GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo;";

                try (PreparedStatement ist = con.prepareStatement(sql)) {
                    ist.setInt(1, rs.getInt("id"));

                    try (ResultSet result = ist.executeQuery()) {
                        if (result.next()) {
                            Map<String, String> row = new HashMap<>();

                            String dataHora = result.getTimestamp("data_hora").toString();
                            String tipoArquivo = result.getString("tipo_arquivo");
                            row.put("data_hora", dataHora);
                            row.put("tipo_arquivo", tipoArquivo);
                            row.put("temperatura", result.getString("temperatura"));
                            row.put("pressao", result.getString("pressao"));
                            row.put("velVento", result.getString("velVento"));
                            row.put("chuva", result.getString("chuva"));
                            row.put("ptoOrvalho", result.getString("ptoOrvalho"));
                            row.put("umidade", result.getString("umidade"));
                            row.put("nebulosidade", result.getString("nebulosidade"));
                            row.put("radiacao", result.getString("radiacao"));
                            row.put("dirVento", result.getString("dirVento"));
                            row.put("insolacao", result.getString("insolacao"));
                            row.put("rajVento", result.getString("rajVento"));
                            dados.add(row);
                        }
                    }
                }

            }
            return dados;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao resgatar atributo!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
    }

    /**
     * Essa função atualiza a lista de dados suspeitos
     * 
     * @return ObservableList<Map<String, String>>
     */
    public ObservableList<Map<String, String>> getDadosSuspeitos() {
        Connection con = null;
        ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();

        try {
            con = getConnection();
            String select_sql = "select * from registro";
            PreparedStatement pst = con.prepareStatement(select_sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String sql = "SELECT r.data_hora,\n" +
                        "       r.id AS id,\n"+
                        "       r.tipo_arquivo,\n" +
                        "       MAX(CASE WHEN ri.nome = 'tempIns' THEN ri.valor END) AS temperatura,\n" +
                        "       MAX(CASE WHEN ri.nome = 'pressaoIns' THEN ri.valor END) AS pressao,\n" +
                        "       MAX(CASE WHEN ri.nome = 'velVento' THEN ri.valor END) AS velVento,\n" +
                        "       MAX(CASE WHEN ri.nome = 'chuva' THEN ri.valor END) AS chuva,\n" +
                        "       MAX(CASE WHEN ri.nome = 'ptoOrvalhoIns' THEN ri.valor END) AS ptoOrvalho,\n" +
                        "       MAX(CASE WHEN ri.nome = 'umiIns' THEN ri.valor END) AS umidade,\n" +
                        "       MAX(CASE WHEN ri.nome = 'nebulosidade' THEN ri.valor END) AS nebulosidade,\n" +
                        "       MAX(CASE WHEN ri.nome = 'radiacao' THEN ri.valor END) AS radiacao,\n" +
                        "       MAX(CASE WHEN ri.nome = 'dirVento' THEN ri.valor END) AS dirVento,\n" +
                        "       MAX(CASE WHEN ri.nome = 'insolacao' THEN ri.valor END) AS insolacao,\n" +
                        "       MAX(CASE WHEN ri.nome = 'rajVento' THEN ri.valor END) AS rajVento\n" +
                        "FROM registro r\n" +
                        "    LEFT JOIN reg_informacao ri ON r.id = ri.registro\n" +
                        "where r.id = ? and ri.dado_suspeito = true\n" +
                        "GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo;";

                try (PreparedStatement ist = con.prepareStatement(sql)) {
                    ist.setInt(1, rs.getInt("id"));

                    try (ResultSet result = ist.executeQuery()) {
                        if (result.next()) {
                            Map<String, String> row = new HashMap<>();

                            String dataHora = result.getTimestamp("data_hora").toString();
                            String tipoArquivo = result.getString("tipo_arquivo");
                            row.put("data_hora_sus", dataHora);
                            row.put("tipo_arquivo_sus", tipoArquivo);
                            row.put("registro_id_sus", result.getString("id"));
                            row.put("temperatura_sus", result.getString("temperatura"));
                            row.put("pressao_sus", result.getString("pressao"));
                            row.put("velVento_sus", result.getString("velVento"));
                            row.put("chuva_sus", result.getString("chuva"));
                            row.put("ptoOrvalho_sus", result.getString("ptoOrvalho"));
                            row.put("umidade_sus", result.getString("umidade"));
                            row.put("nebulosidade_sus", result.getString("nebulosidade"));
                            row.put("radiacao_sus", result.getString("radiacao"));
                            row.put("dirVento_sus", result.getString("dirVento"));
                            row.put("insolacao_sus", result.getString("insolacao"));
                            row.put("rajVento_sus", result.getString("rajVento"));
                            dados.add(row);
                        }
                    }
                }

            }
            return dados;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao resgatar atributo!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
    }

    public void updateData(
            Integer arquivoId,
            String tempMaxima,
            String tempMinima,
            String umiMaxima,
            String umiMinima,
            String presMaxima,
            String presMinima,
            String velVentoMaxima,
            String velVentoMinima,
            String nebuMaxima,
            String nebuMinima,
            String dirVentoMaxima,
            String dirVentoMinima,
            String ptoOrvalhoMaximo,
            String ptoOrvalhoMinimo,
            String rajVentoMaximo,
            String rajVentoMinimo,
            String insoMaxima,
            String insoMinima,
            String chuvaMaxima,
            String chuvaMinima) {
        Connection con = null;
        ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();

        try {
            String select_sql;
            con = getConnection();
            PreparedStatement pst;
            ResultSet rs;
            if( arquivoId == null){
                select_sql = "select * from registro";
                pst = con.prepareStatement(select_sql);
                rs = pst.executeQuery();
            }else{
                select_sql = "select * from registro where arquivo = ?";
                pst = con.prepareStatement(select_sql);
                pst.setInt(1, arquivoId);
                rs = pst.executeQuery();
            }
            while (rs.next()) {



                String newSQL = "UPDATE reg_informacao\n" +
                        "SET dado_suspeito = CASE\n" +
                        "                        WHEN registro = ? AND nome = ? AND (valor < ? OR valor > ?)\n" +
                        "                            THEN 1\n" +
                        "                        ELSE 0\n" +
                        "                    END\n" +
                        "WHERE registro = ? AND nome = ?;";

                verificarVariavel(con, newSQL, rs.getInt("id"), "tempIns", tempMinima, tempMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "pressaoIns", presMinima, presMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "velVento", velVentoMinima,velVentoMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "chuva", chuvaMinima, chuvaMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "ptoOrvalhoIns", ptoOrvalhoMinimo, ptoOrvalhoMaximo);
                verificarVariavel(con, newSQL, rs.getInt("id"), "umiIns", umiMinima, umiMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "nebulosidade", nebuMinima, nebuMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "dirVento", dirVentoMinima, dirVentoMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "insolacao", insoMinima, insoMaxima);
                verificarVariavel(con, newSQL, rs.getInt("id"), "rajVento", rajVentoMinimo, rajVentoMaximo);



            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao resgatar atributo!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
    }

}
