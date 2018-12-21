package io.github.lucasduete.sd.atividade.estilosEsockets.node1;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Loader {

    public static void main(String[] args) throws Exception {

        Random random = new Random();
        AtomicInteger counter = new AtomicInteger(0);
        List<String> nomes = Arrays.asList("Lucas", "Vanda", "May", "Alexa", "Michelle", "Kaiq");

        for (int i = 0; i < 1000; i++) {
            int aux = random.nextInt(5);
            Socket socket = new Socket("localhost", 33802);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(new User(counter.getAndIncrement(), nomes.get(aux)));
            outputStream.flush();
            System.out.println(nomes.get(aux));
        }
        System.out.println(counter.get());

    }

}
