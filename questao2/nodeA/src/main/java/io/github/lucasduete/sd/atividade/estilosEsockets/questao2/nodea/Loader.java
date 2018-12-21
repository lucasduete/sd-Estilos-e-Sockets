package io.github.lucasduete.sd.atividade.estilosEsockets.questao2.nodea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Loader {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(33801)) {
            System.out.println("Iniciou NodeA em 33801");

            exec(server);

        } catch (BindException e) {

            try (ServerSocket server = new ServerSocket(33802)) {
                System.out.println("Iniciou NodeA em 33802");

                exec(server);

            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
            }

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void exec(ServerSocket server) throws IOException, ClassNotFoundException {

        Socket request = server.accept();
        ObjectInputStream inputStreamServer = new ObjectInputStream(request.getInputStream());

        Map<String, Integer> data = (Map<String, Integer>) inputStreamServer.readObject();

        Integer op = data.get("op");
        Integer result = -1;

        ObjectOutputStream outputStreamServer = new ObjectOutputStream(request.getOutputStream());

        if (op == 1) {


            Integer x = data.get("x");
            Integer y = data.get("y");

            result = 2 * y * x;
        } else {

            Socket client = new Socket("127.0.0.1", 33803);

            ObjectOutputStream outputStreamClient = new ObjectOutputStream(client.getOutputStream());
            outputStreamClient.writeObject(data);

            ObjectInputStream inputStreamClient = new ObjectInputStream(client.getInputStream());
            result = inputStreamClient.readInt();

            inputStreamClient.close();
            outputStreamClient.close();
            client.close();
        }

        outputStreamServer.writeInt(result);
        outputStreamServer.flush();

        outputStreamServer.close();

    }

}
