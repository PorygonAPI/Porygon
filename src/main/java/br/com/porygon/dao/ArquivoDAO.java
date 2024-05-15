package br.com.porygon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
//      public void salvarArquivo(String nome, String cidade, String estacao) {
//         Connection con = null;
//         try {
//             con = getConnection();
//             String insert_sql = "INSERT INTO arquivo (nome, cidade, estacao) VALUES (?, ?, ?)";
//             PreparedStatement pst = con.prepareStatement(insert_sql);
//             pst.setString(1, nome);
//             pst.setString(2, cidade);
//             pst.setString(3, estacao);
//             pst.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw new RuntimeException("Erro ao inserir novo atributo!", e);
//         } finally {
//             try {
//                 if (con != null)
//                     con.close();
//             } catch (SQLException e) {
//                 e.printStackTrace();
//                 throw new RuntimeException("Erro ao fechar conexão", e);
//             }
//         }
//     }
// }

    public void salvarArquivo() {
         Connection con = null;
        try {
            con = getConnection();
            String insert_sql = "insert into arquivo (nome, cidade, estacao) values (?, ?, ?)";
            PreparedStatement pst;
            pst = con.prepareStatement(insert_sql);
            pst.setString(1, "Nome teste");
            pst.setString(2, "Cidade teste");
            pst.setString(3, "Estacao teste");
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
}
