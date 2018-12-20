package io.github.lucasduete.sd.atividade.estilosEsockets.questao5.client;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Loader {

    public static void main(String[] args) throws Exception {

        Random random = new Random();

        Integer num1 = random.nextInt(100);
        Integer num2 = random.nextInt(100);
        System.out.println("Numeros escolhidos, num1: " + num1 + "; num2: " + num2 + ";");

        sum(num1, num2);
        diff(num1, num2);
    }

    public static void sum(Integer num1, Integer num2) throws Exception {
        File sumFile = new File("/opt/shared/sum.txt");
        if (!sumFile.exists()) throw new Exception("Sum File not exists");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sumFile));

        bufferedWriter.write(num1.toString());
        bufferedWriter.flush();

        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(num2.toString());
        bufferedWriter.flush();

        bufferedWriter.close();

        Socket socketSum = new Socket("node1", 33801);
        socketSum.getInputStream().read();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(sumFile));
        Stream<String> lines = bufferedReader.lines();
        List<String> numbers = lines.collect(Collectors.toList());

        System.out.println("Resultado da soma e: " + numbers.get(numbers.size() - 1));
    }

    public static void diff(Integer num1, Integer num2) throws Exception {
        File diffFile = new File("/opt/shared/diff.txt");
        if (!diffFile.exists()) throw new Exception("Diff File not exists");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(diffFile));

        bufferedWriter.write(num1.toString());
        bufferedWriter.flush();

        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(num2.toString());
        bufferedWriter.flush();

        bufferedWriter.close();

        Socket socketDiff = new Socket("node2", 33802);
        socketDiff.getInputStream().read();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(diffFile));
        Stream<String> lines = bufferedReader.lines();
        List<String> numbers = lines.collect(Collectors.toList());

        System.out.println("Resultado da subtracao e: " + numbers.get(numbers.size() - 1));
    }

}
