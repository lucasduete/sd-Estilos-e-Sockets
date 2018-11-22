package io.github.lucasduete.sd.atividade.node2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Loader {

    public static void main(String[] args) {

        Integer num1 = 0;
        Integer num2 = 0;

        try (ServerSocket server = new ServerSocket(33801)) {
            System.out.println("Node 2 Iniciado");

            Socket accept = null;
            List<Integer> numbers = null;
            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;

            while (num1.equals(num2)) {

                if (accept != null) {
                    outputStream = new ObjectOutputStream(accept.getOutputStream());
                    outputStream.writeBoolean(false);
                    outputStream.flush();
                }

                System.out.println("Esperando Conexão");
                accept = server.accept();

                inputStream = new ObjectInputStream(accept.getInputStream());
                numbers = (List<Integer>) inputStream.readObject();

                num1 = numbers.get(0);
                num2 = numbers.get(1);
            }

            outputStream = new ObjectOutputStream(accept.getOutputStream());
            outputStream.writeBoolean(true);
            outputStream.flush();

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

        try (Socket client = new Socket("127.0.0.1", 33802)) {

            List<Integer> numbers = Arrays.asList(num1, num2);

            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(numbers);

            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            Double result = inputStream.readDouble();

            System.out.println("Valor calculado : " + result);

            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
