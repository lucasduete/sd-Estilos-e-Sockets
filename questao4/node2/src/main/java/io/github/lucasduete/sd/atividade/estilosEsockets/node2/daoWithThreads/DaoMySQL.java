package io.github.lucasduete.sd.atividade.estilosEsockets.node2.daoWithThreads;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.sql.*;
import java.util.List;

public class DaoMySQL {

    private static final String url = "jdbc:mysql://localhost:3306/sd";
    private static final String usuario = "root";
    private static final String senha = "root";
    private static Connection conn = null;

    private DaoMySQL() {

    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {

        if (conn == null) {
            conn = DriverManager.getConnection(url, usuario, senha);
            conn.setAutoCommit(false);

        }

        return conn;
    }

    public static void insert(List<User> users) {
        try {
            Connection connection = getConnection();

            String sql = "INSERT INTO tb_user(code, name) VALUES (%d, '%s');";

            Statement statement = connection.createStatement();
            users.forEach(user -> {
                try {
                    statement.addBatch(
                            String.format(sql, user.getCode(), user.getName())
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            statement.executeBatch();
            connection.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
