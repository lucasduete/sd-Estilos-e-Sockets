package io.github.lucasduete.sd.atividade.node3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Loader {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(33802)) {
            System.out.println("Node 3 Iniciado");

            System.out.println("Esperado Conexão");
            Socket accept = serverSocket.accept();

            ObjectInputStream inputStream = new ObjectInputStream(accept.getInputStream());
            List<Integer> numbers = (List<Integer>) inputStream.readObject();

            Integer num1 = numbers.get(0);
            Integer num2 = numbers.get(1);

            Double result = Math.pow(num2, num2) + Math.pow(num1, num1);
            System.out.println("Valor Calculado " + result);

            ObjectOutputStream outputStream = new ObjectOutputStream(accept.getOutputStream());
            outputStream.writeDouble(result);
            outputStream.flush();

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }


    }

}
