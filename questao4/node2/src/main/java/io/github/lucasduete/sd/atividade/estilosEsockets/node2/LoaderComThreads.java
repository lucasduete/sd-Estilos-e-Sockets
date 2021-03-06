package io.github.lucasduete.sd.atividade.estilosEsockets.node2;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.dao.DaoMySQL;
import io.github.lucasduete.sd.atividade.estilosEsockets.node2.dao.DaoPG;
import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoaderComThreads {


    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(33802);
        System.out.println("Node iniciou, esperando conexoes na porta 33802");

        final User.Proxy userProxy = new User.Proxy();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long initialInstant = 0;
        long finalInstant = 0;
        int i = 0;

        Runnable runnable = () -> {
            User userSafe = userProxy.getUser().clone();
            DaoPG.insert(userSafe);
            DaoPG.insert(userSafe);
        };

        for (; i < 1000; i++) {
            System.out.println("Esperando");
            Socket socket = server.accept();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            userProxy.setUser((User) inputStream.readObject());

            executorService.execute(runnable);
            if (i == 0) initialInstant = System.currentTimeMillis();
            else if (i == 999) finalInstant = System.currentTimeMillis();

            System.out.println("Nova requisiçao chegou");
        }

        System.out.println("Innitial Time: " + initialInstant);
        System.out.println("Final Time: " + finalInstant);
        System.out.println("Total Time: " + (finalInstant - initialInstant));

    }
}
