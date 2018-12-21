package io.github.lucasduete.sd.atividade.estilosEsockets.questao3.node2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Loader {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(33802)) {
            System.out.println("Node3 iniciado na porta 33802");

            System.out.println("Esperando Requisições");
            Socket accept = server.accept();

            ObjectInputStream inputStreamServer = new ObjectInputStream(accept.getInputStream());
            Map<String, Integer> data = (Map<String, Integer>) inputStreamServer.readObject();

            Integer node = data.get("node");
            Integer result = -1;

            Socket client = null;

            data.replace("node", 2);

            if (node == 1) client = new Socket("127.0.0.1", 33803);
            else if (node == 3) client = new Socket("127.0.0.1", 33804);

            result = redirect(client, data);

            ObjectOutputStream outputStreamServer = new ObjectOutputStream(accept.getOutputStream());
            outputStreamServer.writeInt(result);
            outputStreamServer.flush();

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    private static Integer redirect(Socket client, Map<String, Integer> data) throws ClassNotFoundException, IOException {

        ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
        outputStream.writeObject(data);

        ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
        Integer result = inputStream.readInt();

        outputStream.close();
        inputStream.close();


        return result;
    }

}
