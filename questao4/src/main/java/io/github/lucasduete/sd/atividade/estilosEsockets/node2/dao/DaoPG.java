package io.github.lucasduete.sd.atividade.estilosEsockets.node2.dao;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPG {

    private static final String url = "jdbc:postgresql://localhost:5432/sd";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";
    private static Connection conn = null;

    private DaoPG() {

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
