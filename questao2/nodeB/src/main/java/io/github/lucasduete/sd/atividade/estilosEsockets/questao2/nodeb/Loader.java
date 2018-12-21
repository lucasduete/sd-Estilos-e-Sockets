package io.github.lucasduete.sd.atividade.estilosEsockets.questao2.nodeb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Loader {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(33803)) {
            System.out.println("INICIOU NodeB");

            System.out.println("Esperando Conexão");
            Socket request = server.accept();

            ObjectInputStream inputStreamServer = new ObjectInputStream(request.getInputStream());

            Map<String, Integer> data = (Map<String, Integer>) inputStreamServer.readObject();

            System.out.println("Object Data");
            data.entrySet().stream().forEach(System.out::println);

            Integer op = data.get("op");
            Integer result = -1;

            ObjectOutputStream outputStreamServer = new ObjectOutputStream(request.getOutputStream());

            if (op == 2) {


                Integer x = data.get("x");
                Integer y = data.get("y");

                result = (2 * x) / y;
            } else {

                try (Socket client = new Socket("127.0.0.1", 33801)) {

                    result = redirect(client, data);

                } catch (IOException ex1) {
                    try (Socket client = new Socket("127.0.0.1", 33802)) {

                        result = redirect(client, data);


                    } catch (IOException ex2) {
                        System.out.println("NodeB não pode redirecionar a nenhuma instância de NodeA");
                    }
                }

            }

            outputStreamServer.writeInt(result);
            outputStreamServer.flush();

            outputStreamServer.close();


        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    public static Integer redirect(Socket client, Map<String, Integer> data) throws IOException {

        ObjectOutputStream outputStreamClient = new ObjectOutputStream(client.getOutputStream());
        outputStreamClient.writeObject(data);

        ObjectInputStream inputStreamClient = new ObjectInputStream(client.getInputStream());
        Integer result = inputStreamClient.readInt();

        inputStreamClient.close();
        outputStreamClient.close();

        return result;
    }

}
