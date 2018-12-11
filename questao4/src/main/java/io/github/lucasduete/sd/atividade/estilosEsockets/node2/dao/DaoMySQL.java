package io.github.lucasduete.sd.atividade.estilosEsockets.node2.dao;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        }

        return conn;
    }

    public static void insert(User user) {
        try {
            Connection connection = getConnection();

            String sql = "INSERT INTO tb_user(code, name) VALUES (?,?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, user.getCode());
            statement.setString(2, user.getName());

            statement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
