package br.com.porygon.dao;

import javafx.collections.ObservableList;

import java.sql.*;

public class EstacaoDAO {

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

    public String resgatarCodigo(String nome) {
        Connection con = null;
        try {
            con = getConnection();
            String select_sql = "select * from estacao where nome = (?)";
            PreparedStatement pst = con.prepareStatement(select_sql);
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();
            String valor = null;
            while(rs.next()) {
                valor = rs.getString("codigo");
            }
            return valor;
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

    public void getEstacoes(ObservableList<String> lista) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();

            String getEstacaoSQL = "SELECT * FROM estacao";

            PreparedStatement selectStmt = con.prepareStatement(getEstacaoSQL);
            try (ResultSet rsSelect = selectStmt.executeQuery()){
                while(rsSelect.next()) {
                    lista.add(rsSelect.getString("nome"));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao resgatar cidade!", e);
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

    //     Realiza a inserção dos dados lidos no banco
    public void updateEstacao(String codigo, String cidade) {
        Connection con = null;
        int generatedId = -1;

        try {
            con = getConnection();
            String insert_sql = "INSERT INTO estacao (codigo, nome) VALUES (?, ?) on duplicate key update nome = VALUES(nome)";
            PreparedStatement pst = con.prepareStatement(insert_sql);
            pst.setString(1, codigo);
            pst.setString(2, cidade);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir nova estacao!", e);
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
