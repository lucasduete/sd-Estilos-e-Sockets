package io.github.lucasduete.sd.atividade.estilosEsockets.node2;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.dao.DaoMySQL;
import io.github.lucasduete.sd.atividade.estilosEsockets.node2.dao.DaoPG;
import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketHandler extends Thread {

    private final Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) inputStream.readObject();


//            System.out.println(user);
            DaoMySQL.insert(user);
            DaoPG.insert(user);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}