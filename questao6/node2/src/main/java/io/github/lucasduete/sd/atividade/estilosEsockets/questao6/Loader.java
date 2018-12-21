package io.github.lucasduete.sd.atividade.estilosEsockets.questao6;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

public class Loader {

    private static final File file = new File("/home/lucasduete/temp/quest6.txt");


    public static void main(String[] args) throws Exception {

        if (!file.exists()) file.createNewFile();

        Long linesBefore = new BufferedReader(new FileReader(file)).lines().count();

        Object[] linesActual = null;

        while (true) {
            Thread.sleep(100);

            linesActual = new BufferedReader(new FileReader(file)).lines().toArray();
            if (linesActual.length > linesBefore) {
                notify(linesActual[linesActual.length - 1]);
            }

            linesBefore = Long.valueOf(linesActual.length);
        }
    }

    public static void notify(Object obj) throws Exception {
        Socket socket = new Socket("localhost", 33801);

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(obj);
        outputStream.flush();

        outputStream.close();
        socket.close();
    }

}
