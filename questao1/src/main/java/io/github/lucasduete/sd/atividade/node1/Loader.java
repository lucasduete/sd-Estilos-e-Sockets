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

            boolean aux = false;

            while (aux == false) {
//                List<Integer> numbers = Arrays.asList(random.nextInt(101), random.nextInt(101));

                List<Integer> numbers = Arrays.asList(1, 2);
                System.out.println("Valores gerados: ");
                numbers.forEach(System.out::println);

                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                outputStream.writeObject(numbers);


                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                aux = inputStream.readBoolean();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
