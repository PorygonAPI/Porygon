package br.com.porygon.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistroDAO {
    private final String url = "jdbc:mysql://localhost:3306/porygon?useTimezone=true&serverTimezone=UTC";
    private final String username = "porygon";
    private final String password = "pesquisador";
    ZoneId zoneId = ZoneId.of("America/Sao_Paulo"); // Brasilia time, which is GMT-3 with daylight saving adjustments

    Map<String, String> dictionary = new HashMap<String, String>() {
        {
            put("temperatura", """
                    SELECT temperatura
                    FROM (
                             SELECT MAX(ri.valor) AS temperatura
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'tempIns'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE temperatura IS NOT NULL;
                    """);
            put("pressao", """
                    SELECT pressao
                    FROM (
                             SELECT MAX(ri.valor) AS pressao
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'pressaoIns'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE pressao IS NOT NULL;
                    """);
            put("velVento", """
                    SELECT velVento
                    FROM (
                             SELECT MAX(ri.valor) AS velVento
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'velVento'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE velVento IS NOT NULL;
                    """);
            put("chuva", """
                    SELECT chuva
                    FROM (
                             SELECT MAX(ri.valor) AS chuva
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'chuva'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE chuva IS NOT NULL;
                    """);
            put("ptoOrvalho", """
                    SELECT ptoOrvalho
                    FROM (
                             SELECT MAX(ri.valor) AS ptoOrvalho
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'ptoOrvalhoIns'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE ptoOrvalho IS NOT NULL;
                    """);
            put("umiIns", """
                    SELECT umiIns
                    FROM (
                             SELECT MAX(ri.valor) AS umiIns
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'umiIns'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE umiIns IS NOT NULL;
                    """);
            put("nebulosidade", """
                    SELECT nebulosidade
                    FROM (
                             SELECT MAX(ri.valor) AS nebulosidade
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'nebulosidade'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE nebulosidade IS NOT NULL;
                    """);
            put("radiacao", """
                    SELECT radiacao
                    FROM (
                             SELECT MAX(ri.valor) AS radiacao
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'radiacao'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE radiacao IS NOT NULL;
                    """);
            put("dirVento", """
                    SELECT dirVento
                    FROM (
                             SELECT MAX(ri.valor) AS dirVento
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'dirVento'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE dirVento IS NOT NULL;
                    """);
            put("insolacao", """
                    SELECT insolacao
                    FROM (
                             SELECT MAX(ri.valor) AS insolacao
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'insolacao'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE insolacao IS NOT NULL;
                    """);
            put("rajVento", """
                    SELECT rajVento
                    FROM (
                             SELECT MAX(ri.valor) AS rajVento
                             FROM registro r
                                      LEFT JOIN arquivo a ON r.arquivo = a.id
                                      LEFT JOIN reg_informacao ri ON r.id = ri.registro
                                      LEFT JOIN estacao e ON a.estacao = e.codigo
                             WHERE ri.nome = 'rajVento'
                               AND ri.valor IS NOT NULL
                               AND ri.dado_suspeito = false
                               AND e.nome = ?
                               AND (r.data_hora BETWEEN ? AND ?)
                             GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo
                         ) AS sub
                    WHERE rajVento IS NOT NULL;
                    """);
        }
    };

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
            String insert_sql = "INSERT IGNORE INTO reg_informacao (registro, nome, valor) VALUES (?, ?, ?)";
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

    public void saveRelatory(String sigla, LocalDate dataInicio, LocalDate dataFim, String csvFilePath) {
        Connection con = null;
        ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();

        try {
            con = getConnection();
            String filterSQL = """
                    SELECT r.data_hora,
                           a.cidade AS cidade,
                           r.tipo_arquivo,
                           MAX(CASE WHEN ri.nome = 'tempIns' THEN ri.valor END) AS temperatura,
                           MAX(CASE WHEN ri.nome = 'pressaoIns' THEN ri.valor END) AS pressao,
                           MAX(CASE WHEN ri.nome = 'velVento' THEN ri.valor END) AS velVento,
                           MAX(CASE WHEN ri.nome = 'chuva' THEN ri.valor END) AS chuva,
                           MAX(CASE WHEN ri.nome = 'ptoOrvalhoIns' THEN ri.valor END) AS ptoOrvalho,
                           MAX(CASE WHEN ri.nome = 'umiIns' THEN ri.valor END) AS umiIns,
                           MAX(CASE WHEN ri.nome = 'nebulosidade' THEN ri.valor END) AS nebulosidade,
                           MAX(CASE WHEN ri.nome = 'radiacao' THEN ri.valor END) AS radiacao,
                           MAX(CASE WHEN ri.nome = 'dirVento' THEN ri.valor END) AS dirVento,
                           MAX(CASE WHEN ri.nome = 'insolacao' THEN ri.valor END) AS insolacao,
                           MAX(CASE WHEN ri.nome = 'rajVento' THEN ri.valor END) AS rajVento
                    FROM registro r
                             LEFT JOIN arquivo a ON r.arquivo = a.id
                             LEFT JOIN cidade c ON a.cidade = c.sigla
                             LEFT JOIN reg_informacao ri ON r.id = ri.registro
                    where ri.dado_suspeito = false and c.nome = ? and (r.data_hora BETWEEN ? AND ?)
                    GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo;""";
            try (PreparedStatement ist = con.prepareStatement(filterSQL)) {
                Timestamp tmstpInicio = Timestamp.valueOf(dataInicio.atStartOfDay());
                Timestamp tmstpFim = Timestamp.valueOf(dataFim.atTime(23, 59, 59, 0));
                System.out.println(tmstpInicio);
                System.out.println(tmstpFim);
                ist.setString(1, sigla);
                ist.setTimestamp(2, tmstpInicio);
                ist.setTimestamp(3, tmstpFim);
                FileWriter fileWriter = new FileWriter(csvFilePath);

                try (ResultSet result = ist.executeQuery()) {
                    // Escreva o cabeçalho
                    ResultSetMetaData metaData = result.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        fileWriter.append(metaData.getColumnLabel(i));
                        if (i < columnCount) {
                            fileWriter.append(",");
                        } else {
                            fileWriter.append("\n");
                        }
                    }

                    // Escreva os dados do ResultSet no arquivo CSV
                    while (result.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            fileWriter.append(result.getString(i));
                            if (i < columnCount) {
                                fileWriter.append(",");
                            } else {
                                fileWriter.append("\n");
                            }
                        }
                    }

                    // Feche o FileWriter
                    fileWriter.close();

                    System.out.println("Os dados foram gravados com sucesso no arquivo CSV.");
                }
            }
        } catch (SQLException | IOException e) {
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

    public ArrayList<Double> getSpecificData(LocalDate dataInicio, LocalDate dataFim, String nomeTable,
            String estacaoSigla) {
        Connection con = null;
        ArrayList<Double> dados = new ArrayList<>();
        try {
            con = getConnection();
            String dataSQL = dictionary.get(nomeTable);
            try (PreparedStatement ist = con.prepareStatement(dataSQL)) {

                ZonedDateTime startDateTime = dataInicio.atStartOfDay(zoneId);
                ZonedDateTime endDateTime = dataFim.atTime(23, 59, 59).atZone(zoneId);

                Timestamp tmstpInicio = Timestamp.valueOf(startDateTime.toLocalDateTime());
                Timestamp tmstpFim = Timestamp.valueOf(endDateTime.toLocalDateTime());

                ist.setString(1, estacaoSigla);
                ist.setString(2, String.valueOf(tmstpInicio));
                ist.setString(3, String.valueOf(tmstpFim));

                try (ResultSet result = ist.executeQuery()) {
                    while (result.next()) {
                        dados.add(Double.parseDouble(result.getString(nomeTable)));
                    }
                }
            }
            return dados;
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

    public ObservableList<Map<String, String>> filterSituationalRelatory(String cidadeEscolhida) {
        Connection con = null;
        ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();
        try {
            con = getConnection();
            String filterSQL = """
                    WITH dados_numerados AS (
                        SELECT
                        r.data_hora,
                        a.estacao,
                        a.cidade AS cidade,
                        r.tipo_arquivo,
                        MAX(CASE WHEN ri.nome = 'tempIns' THEN ri.valor END) AS temperatura,
                        MAX(CASE WHEN ri.nome = 'pressaoIns' THEN ri.valor END) AS pressao,
                        MAX(CASE WHEN ri.nome = 'velVento' THEN ri.valor END) AS velVento,
                        MAX(CASE WHEN ri.nome = 'chuva' THEN ri.valor END) AS chuva,
                        MAX(CASE WHEN ri.nome = 'ptoOrvalhoIns' THEN ri.valor END) AS ptoOrvalho,
                        MAX(CASE WHEN ri.nome = 'umiIns' THEN ri.valor END) AS umiIns,
                        MAX(CASE WHEN ri.nome = 'nebulosidade' THEN ri.valor END) AS nebulosidade,
                        MAX(CASE WHEN ri.nome = 'radiacao' THEN ri.valor END) AS radiacao,
                        MAX(CASE WHEN ri.nome = 'dirVento' THEN ri.valor END) AS dirVento,
                        MAX(CASE WHEN ri.nome = 'insolacao' THEN ri.valor END) AS insolacao,
                        MAX(CASE WHEN ri.nome = 'rajVento' THEN ri.valor END) AS rajVento,
                        ROW_NUMBER() OVER (PARTITION BY a.cidade, a.estacao ORDER BY r.data_hora DESC) AS rn
                        FROM
                        registro r
                        LEFT JOIN arquivo a ON r.arquivo = a.id
                        LEFT JOIN reg_informacao ri ON r.id = ri.registro
                        LEFT JOIN cidade c ON a.cidade = c.sigla
                        WHERE
                        ri.dado_suspeito = false AND
                        c.nome = ?
                        GROUP BY
                        a.cidade, a.estacao, r.arquivo, r.data_hora, r.tipo_arquivo
                        )
                        SELECT
                        data_hora,
                        estacao,
                        cidade,
                        tipo_arquivo,
                        temperatura,
                        pressao,
                        velVento,
                        chuva,
                        ptoOrvalho,
                        umiIns,
                        nebulosidade,
                        radiacao,
                        dirVento,
                        insolacao,
                        rajVento
                        FROM
                        dados_numerados
                        WHERE
                        rn = 1;""";
            try (PreparedStatement ist = con.prepareStatement(filterSQL)) {

                ist.setString(1, cidadeEscolhida);
                ;

                try (ResultSet result = ist.executeQuery()) {
                    while (result.next()) {
                        Map<String, String> row = new HashMap<>();

                        String dataHora = result.getTimestamp("data_hora").toString();
                        row.put("data_hora_sit", dataHora);
                        row.put("temperatura_sit", result.getString("temperatura"));
                        row.put("pressao_sit", result.getString("pressao"));
                        row.put("velVento_sit", result.getString("velVento"));
                        row.put("chuva_sit", result.getString("chuva"));
                        row.put("ptoOrvalho_sit", result.getString("ptoOrvalho"));
                        row.put("umidade_sit", result.getString("umiIns"));
                        row.put("nebulosidade_sit", result.getString("nebulosidade"));
                        row.put("radiacao_sit", result.getString("radiacao"));
                        row.put("dirVento_sit", result.getString("dirVento"));
                        row.put("insolacao_sit", result.getString("insolacao"));
                        row.put("rajVento_sit", result.getString("rajVento"));
                        dados.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro!", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar conexão", e);
            }
        }
        return dados;
    }

    public ObservableList<Map<String, String>> filterBetweenDates(String sigla, LocalDate dataInicio,
            LocalDate dataFim) {
        Connection con = null;
        ObservableList<Map<String, String>> dados = FXCollections.observableArrayList();

        try {
            con = getConnection();
            String filterSQL = """
                    SELECT r.data_hora,
                           a.cidade AS cidade,
                           r.tipo_arquivo,
                           MAX(CASE WHEN ri.nome = 'tempIns' THEN ri.valor END) AS temperatura,
                           MAX(CASE WHEN ri.nome = 'pressaoIns' THEN ri.valor END) AS pressao,
                           MAX(CASE WHEN ri.nome = 'velVento' THEN ri.valor END) AS velVento,
                           MAX(CASE WHEN ri.nome = 'chuva' THEN ri.valor END) AS chuva,
                           MAX(CASE WHEN ri.nome = 'ptoOrvalhoIns' THEN ri.valor END) AS ptoOrvalho,
                           MAX(CASE WHEN ri.nome = 'umiIns' THEN ri.valor END) AS umiIns,
                           MAX(CASE WHEN ri.nome = 'nebulosidade' THEN ri.valor END) AS nebulosidade,
                           MAX(CASE WHEN ri.nome = 'radiacao' THEN ri.valor END) AS radiacao,
                           MAX(CASE WHEN ri.nome = 'dirVento' THEN ri.valor END) AS dirVento,
                           MAX(CASE WHEN ri.nome = 'insolacao' THEN ri.valor END) AS insolacao,
                           MAX(CASE WHEN ri.nome = 'rajVento' THEN ri.valor END) AS rajVento
                    FROM registro r
                             LEFT JOIN arquivo a ON r.arquivo = a.id
                             LEFT JOIN reg_informacao ri ON r.id = ri.registro
                             LEFT JOIN cidade c ON a.cidade = c.sigla
                    where ri.dado_suspeito = false and c.nome = ? and (r.data_hora BETWEEN ? AND ?)
                    GROUP BY r.arquivo, r.data_hora, r.tipo_arquivo;""";
            try (PreparedStatement ist = con.prepareStatement(filterSQL)) {
                Timestamp tmstpInicio = Timestamp.valueOf(dataInicio.atStartOfDay());
                Timestamp tmstpFim = Timestamp.valueOf(dataFim.atTime(23, 59, 59, 0));
                System.out.println(tmstpInicio);
                System.out.println(tmstpFim);
                ist.setString(1, sigla);
                ist.setString(2, String.valueOf(tmstpInicio));
                ist.setString(3, String.valueOf(tmstpFim));

                try (ResultSet result = ist.executeQuery()) {
                    while (result.next()) {
                        Map<String, String> row = new HashMap<>();

                        String dataHora = result.getTimestamp("data_hora").toString();
                        String tipoArquivo = result.getString("tipo_arquivo");
                        row.put("data_hora_rel", dataHora);
                        row.put("tipo_arquivo_rel", tipoArquivo);
                        row.put("temperatura_rel", result.getString("temperatura"));
                        row.put("pressao_rel", result.getString("pressao"));
                        row.put("velVento_rel", result.getString("velVento"));
                        row.put("chuva_rel", result.getString("chuva"));
                        row.put("ptoOrvalho_rel", result.getString("ptoOrvalho"));
                        row.put("umidade_rel", result.getString("umiIns"));
                        row.put("nebulosidade_rel", result.getString("nebulosidade"));
                        row.put("radiacao_rel", result.getString("radiacao"));
                        row.put("dirVento_rel", result.getString("dirVento"));
                        row.put("insolacao_rel", result.getString("insolacao"));
                        row.put("rajVento_rel", result.getString("rajVento"));
                        dados.add(row);
                    }
                }
            }
            return dados;
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

    public void alterarRegistroSuspeito(int registro, String nomeVariavel, Double novoValor, boolean dadoSuspeito)
            throws SQLException {
        Connection con = null;
        try {

            con = getConnection();

            String newSQL = "UPDATE reg_informacao\n" +
                    "    SET dado_suspeito = ?, valor = ?\n" +
                    "    WHERE registro = ? AND nome = ?;";
            PreparedStatement ist = con.prepareStatement(newSQL);
            ist.setBoolean(1, dadoSuspeito);
            ist.setDouble(2, novoValor);
            ist.setInt(3, registro);
            ist.setString(4, nomeVariavel);
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

    public void excluirRegistroSuspeito(int registro, String nomeVariavel) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();

            String deleteSQL = "DELETE FROM reg_informacao WHERE registro = ? AND nome = ?";
            try (PreparedStatement pst = con.prepareStatement(deleteSQL)) {
                pst.setInt(1, registro);
                pst.setString(2, nomeVariavel);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir dado!", e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Erro ao fechar conexão", e);
                }
            }
        }
    }

    public void restaurarRegistroSuspeito(int registro, String nomeVariavel) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();

            String manterSQL = "UPDATE reg_informacao SET dado_suspeito = FALSE WHERE registro = ? AND nome = ?";
            try (PreparedStatement pst = con.prepareStatement(manterSQL)) {
                pst.setInt(1, registro);
                pst.setString(2, nomeVariavel);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao manter dado!", e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Erro ao fechar conexão", e);
                }
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
                        "       r.id AS id,\n" +
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

    public Map<String, Double> getDadoSuspeito(int registroId) {
        Connection con = null;
        try {
            String select_sql;
            con = getConnection();
            PreparedStatement pst;
            ResultSet rs;
            select_sql = "select * from reg_informacao where registro = ? and dado_suspeito = 1";
            pst = con.prepareStatement(select_sql);
            pst.setInt(1, registroId);
            Map<String, Double> map = new HashMap<String, Double>();

            rs = pst.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("nome"), rs.getDouble("valor"));
            }
            return map;
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
            if (arquivoId == null) {
                select_sql = "select * from registro";
                pst = con.prepareStatement(select_sql);
                rs = pst.executeQuery();
            } else {
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
                verificarVariavel(con, newSQL, rs.getInt("id"), "velVento", velVentoMinima, velVentoMaxima);
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
