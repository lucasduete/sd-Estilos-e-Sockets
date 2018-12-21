package io.github.lucasduete.sd.atividade.estilosEsockets.questao2.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Loader {

    public static void main(String[] args) {

        Map<String, Integer> data = new HashMap<>();

        data.put("x", 7);
        data.put("y", 13);
        data.put("op", 1);

        try (Socket client = new Socket("127.0.0.1", 33803)) {

            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(data);

            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            int result = inputStream.readInt();

            System.out.println("Resultado é: " +result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
