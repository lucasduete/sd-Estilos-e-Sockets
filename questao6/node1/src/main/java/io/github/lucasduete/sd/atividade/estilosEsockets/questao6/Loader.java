package io.github.lucasduete.sd.atividade.estilosEsockets.questao6;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Loader {

    static Runnable observer = () -> {

    };

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(33801);

        while (true) {
            Socket socket = server.accept();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println(inputStream.readObject().toString());
        }

    }
}
