package io.github.lucasduete.sd.atividade.estilosEsockets.questao6;

import java.io.*;
import java.util.Random;

public class Node3 {

    private static final File file = new File("/home/lucasduete/temp/quest6.txt");
    private static final Random random = new Random(1000);

    public static void main(String[] args) throws Exception {

        if (!file.exists()) file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        while (true) {
            if (file.length() > 0) bufferedWriter.newLine();

            bufferedWriter.write(String.valueOf(random.nextInt()));
            bufferedWriter.flush();
            Thread.sleep(2000);
        }

    }
}
