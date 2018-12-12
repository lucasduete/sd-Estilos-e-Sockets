package io.github.lucasduete.sd.atividade.estilosEsockets.node2;

import java.net.ServerSocket;

public class Node2 {


    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(33802);
        System.out.println("Node iniciou, esperando conexoes na porta 33802");

        long initialInstant = 0;
        long finalInstant =  0;
        int i = 0;

        for (; i < 1000; i++) {
            System.out.println("Esperando");
            SocketHandler socketHander = new SocketHandler(server.accept());

            if (i == 0) initialInstant =  System.currentTimeMillis();
            else if (i == 999) finalInstant = System.currentTimeMillis();

            System.out.println("Nova requisiçao chegou");
            socketHander.start();
        }


        System.out.println("Innitial Time: " + initialInstant);
        System.out.println("Final Time: " + finalInstant);
        System.out.println("Total Time: " + (finalInstant - initialInstant));

    }
}
