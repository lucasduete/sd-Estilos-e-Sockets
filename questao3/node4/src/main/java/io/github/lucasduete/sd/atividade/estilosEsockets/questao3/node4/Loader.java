package io.github.lucasduete.sd.atividade.estilosEsockets.node4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Loader {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(33804)) {
            System.out.println("Node4 iniciado na porta 33804");

            System.out.println("Esperando Requisições");
            Socket accept = server.accept();

            ObjectInputStream inputStream = new ObjectInputStream(accept.getInputStream());
            Map<String, Integer> data = (Map<String, Integer>) inputStream.readObject();

            Integer x = data.get("x");
            Integer y = data.get("y");
            Integer node = data.get("node");
            Integer result = -1;

            if (node == 2) result = x + y;
            else if (node == 3) result = x - y;

            System.out.println("Resultado calculado: " + result);

            ObjectOutputStream outputStream = new ObjectOutputStream(accept.getOutputStream());
            outputStream.writeInt(result);
            outputStream.flush();

            inputStream.close();
            outputStream.close();

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

}
