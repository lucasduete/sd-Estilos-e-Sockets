package io.github.lucasduete.sd.atividade.estilosEsockets.questao3.node1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Loader {

    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 33803)) {

            Map<String, Integer> data = new HashMap<>();

            data.put("x", 7);
            data.put("y", 13);
            data.put("node", 1);

            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(data);

            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            Integer result = inputStream.readInt();

            System.out.println("O resultado é: " + result);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
