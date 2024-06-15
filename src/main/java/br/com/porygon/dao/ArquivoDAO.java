package br.com.porygon.dao;

import java.sql.*;

public class ArquivoDAO {
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

//     Realiza a inserção dos dados lidos no banco
     public int salvarArquivo(String cidadeSigla, String estacaoSigla) {
        Connection con = null;
        int generatedId = -1;

        try {
            con = getConnection();

            String selectSQL = "SELECT id FROM arquivo WHERE cidade = ? AND estacao = ?";


            try (PreparedStatement selectStmt = con.prepareStatement(selectSQL)) {
                selectStmt.setString(1, cidadeSigla);
                selectStmt.setString(2, estacaoSigla);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        // Registro já existe, retorna o ID
                        return rs.getInt("id");
                    }
                }
            }



            addOrUpdateCity(cidadeSigla, con);

            addOrUpdateStation(estacaoSigla, con);

            String insert_sql = "INSERT INTO arquivo (cidade, estacao) VALUES ( ?, ?)";
            PreparedStatement pst = con.prepareStatement(insert_sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, cidadeSigla);
            pst.setString(2, estacaoSigla);
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
            throw new RuntimeException("Erro ao inserir novo arquivo!", e);
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

    private static void addOrUpdateStation(String estacaoSigla, Connection con) throws SQLException {
        String getEstacaoSQL = "SELECT codigo FROM estacao WHERE codigo = ?";
        String setEstacaoSQL = "INSERT INTO estacao (codigo, nome) VALUES (?, ?)";

        PreparedStatement selectStationStmt = con.prepareStatement(getEstacaoSQL);
        selectStationStmt.setString(1, estacaoSigla);
        try (ResultSet rsSelect = selectStationStmt.executeQuery()){
            if(!rsSelect.next()){
                PreparedStatement insertStationStmt = con.prepareStatement(setEstacaoSQL);
                insertStationStmt.setString(1, estacaoSigla);
                insertStationStmt.setString(2, estacaoSigla);
                insertStationStmt.executeUpdate();
            }
        }
    }

    private static void addOrUpdateCity(String cidadeSigla, Connection con) throws SQLException {
        String getCidadeSQL = "SELECT sigla FROM cidade WHERE sigla = ?";
        String setCidadeSQL = "INSERT INTO cidade (sigla, nome) VALUES (?, ?)";

        PreparedStatement selectStmt = con.prepareStatement(getCidadeSQL);
        selectStmt.setString(1, cidadeSigla);
        try (ResultSet rsSelect = selectStmt.executeQuery()){
            if(!rsSelect.next()){
                PreparedStatement insertStmt = con.prepareStatement(setCidadeSQL);
                insertStmt.setString(1, cidadeSigla);
                insertStmt.setString(2, cidadeSigla);
                insertStmt.executeUpdate();
            }
        }
    }

    // public void salvarArquivo() {
    //      Connection con = null;
    //     try {
    //         con = getConnection();
    //         String insert_sql = "insert into arquivo (nome, cidade, estacao) values (?, ?, ?)";
    //         PreparedStatement pst;
    //         pst = con.prepareStatement(insert_sql);
    //         pst.setString(1, "Nome teste");
    //         pst.setString(2, "Cidade teste");
    //         pst.setString(3, "Estacao teste");
    //         pst.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         throw new RuntimeException("Erro ao inserir novo atributo!", e);
    //     } finally {
    //         try {
    //             if (con != null)
    //                 con.close();
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //             throw new RuntimeException("Erro ao fechar conexão", e);
    //         }
    //     }
    // }
}
