package io.github.lucasduete.sd.atividade.node1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Loader {


    public static void main(String[] args) {

        final Random random = new Random();

        try (Socket client = new Socket("127.0.0.1", 33801)) {

            Integer aux;

            do {
                List<Integer> numbers = Arrays.asList(random.nextInt(101), random.nextInt(101));

                System.out.println("Valores gerados: ");
                numbers.forEach(System.out::println);

                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                outputStream.writeObject(numbers);


                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                aux = inputStream.readInt();

                if (aux == 0)
                    System.out.println("Os números eram iguais");

            } while (aux == 0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
