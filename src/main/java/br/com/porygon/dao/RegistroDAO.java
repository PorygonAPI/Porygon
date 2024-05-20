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

    private String verificarVariavel(String variavel, String limiteMaior, String limiteMenor) {
        if (variavel != null && !variavel.trim().isEmpty()) {
            double variavelParsed = Double.parseDouble(variavel);
            double limiteMaiorParsed = Double.parseDouble(limiteMaior);
            double limiteMenorParsed = Double.parseDouble(limiteMenor);
            if (variavelParsed <= limiteMaiorParsed && variavelParsed >= limiteMenorParsed) {
                return variavel;
            }
        }
        return null;
    }

    public ObservableList<Map<String, String>> recuperarDados(
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
        String chuvaMinima
    ) {
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
                        "         LEFT JOIN reg_informacao ri ON r.id = ri.registro\n" +
                        "where r.id = ?\n" +
                        "GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo;";

                try (PreparedStatement ist = con.prepareStatement(sql)) {
                    ist.setInt(1, rs.getInt("id"));

                    try (ResultSet result = ist.executeQuery()) {
                        if (result.next()) {
                            // Registro já existe, retorna o ID
                            // return rs.getInt("id");
                            Map<String, String> row = new HashMap<>();

                            String dataHora = result.getTimestamp("data_hora").toString();
                            String tipoArquivo = result.getString("tipo_arquivo");
                            String temp = verificarVariavel(result.getString("temperatura"), tempMaxima, tempMinima);
                            // String temp = result.getString("temperatura");

                            // verificarVariavel(temp, )
                            row.put("data_hora", dataHora);
                            row.put("tipo_arquivo", tipoArquivo);
                            row.put("temperatura", temp);
                            row.put("pressao", result.getString("pressao"));
                            row.put("velVento", Double.toString(result.getDouble("velVento")));
                            row.put("chuva", result.getString("chuva"));
                            row.put("ptoOrvalho", result.getString("ptoOrvalho"));
                            row.put("umidade", result.getString("umidade"));
                            row.put("nebulosidade", String.valueOf(result.getDouble("nebulosidade")));
                            row.put("radiacao", result.getString("radiacao"));
                            row.put("dirVento", result.getString("dirVento"));
                            row.put("insolacao", result.getString("insolacao"));
                            row.put("rajVento", result.getString("rajVento"));

                            dados.add(row);
                        }
                    }
                }

                // try (PreparedStatement statement = con.prepareStatement(sql);
                // ResultSet resultSet = statement.executeQuery()) {
                //
                // // Criar uma coluna para cada valor da coluna "chave"
                // while (resultSet.next()) {
                // String chave = resultSet.getString("chave");
                // String valor = resultSet.getString("valor");
                //
                // TableColumn<RegistroInformacao, String> column = new TableColumn<>(chave);
                // column.setCellValueFactory(cellData ->
                // cellData.getValue().getValorProperty());
                //
                // tableView.getColumns().add(column);
                //
                // // Adicionar os dados à TableView
                // tableView.getItems().add(new RegistroInformacao(chave, valor));
                // }
                // }

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

}
