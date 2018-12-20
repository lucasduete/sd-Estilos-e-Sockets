package io.github.lucasduete.sd.atividade.estilosEsockets.questao5.node1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Loader {

    public static void main(String[] args) throws Exception {

        File file = new File("/opt/shared/sum.txt");
        if (!file.exists()) throw new Exception("File not exists");

        ServerSocket server = new ServerSocket(33801);
        System.out.println("Esperando conexoes na porta 33801");

        // Espera conexao
        Socket accept = server.accept();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Stream<String> lines = bufferedReader.lines();

        List<String> numbers = lines.collect(Collectors.toList());

        bufferedReader.close();

        if (numbers.size() < 2) throw new Exception("File corrupted");

        Integer num1 = Integer.valueOf(numbers.get(numbers.size() - 2));
        Integer num2 = Integer.valueOf(numbers.get(numbers.size() - 1));

        Integer sum = num1 + num2;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        bufferedWriter.write(num1.toString());
        bufferedWriter.flush();

        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(num2.toString());
        bufferedWriter.flush();

        bufferedWriter.newLine();
        bufferedWriter.write(sum.toString());

        bufferedWriter.flush();
        bufferedWriter.close();

        accept.getOutputStream().flush();
        accept.close();
        server.close();
    }
}
