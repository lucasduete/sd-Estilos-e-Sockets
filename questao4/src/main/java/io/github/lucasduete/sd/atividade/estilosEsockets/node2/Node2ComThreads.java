package io.github.lucasduete.sd.atividade.estilosEsockets.node2;

import io.github.lucasduete.sd.atividade.estilosEsockets.node2.daoWithThreads.DaoMySQL;
import io.github.lucasduete.sd.atividade.estilosEsockets.node2.daoWithThreads.DaoPG;
import io.github.lucasduete.sd.atividade.estilosEsockets.node2.model.User;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Node2ComThreads {


    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(33802);
        System.out.println("Node iniciou, esperando conexoes na porta 33802");

        long initialInstant = 0;
        long finalInstant =  0;
        int i = 0;

        List<User> users = new ArrayList<>();

        for (; i < 1000; i++) {
            System.out.println("Esperando");
            Socket socket = server.accept();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) inputStream.readObject();

            if (i == 0) initialInstant =  System.currentTimeMillis();
            else if (i == 999) finalInstant = System.currentTimeMillis();

            users.add(user);
            if (users.size() >= 100) {
                System.out.println("entrou com " + users.size());
                List<User> protectedUsers = new ArrayList<>(users);
                users.clear();
                new Thread(() -> {
                    DaoMySQL.insert(protectedUsers);
                    DaoPG.insert(protectedUsers);
                }).start();
            }

            System.out.println("Nova requisiçao chegou");
        }

        System.out.println(users.size());
        System.out.println("Innitial Time: " + initialInstant);
        System.out.println("Final Time: " + finalInstant);
        System.out.println("Total Time: " + (finalInstant - initialInstant));

    }
}
