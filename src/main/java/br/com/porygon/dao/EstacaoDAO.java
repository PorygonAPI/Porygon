package br.com.porygon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;

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
            throw new RuntimeException("Erro ao resgatar estacao!", e);
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
