## Para execução construa as imagens e faça

docker run -p 33801:33801 -v /home/lucasduete/temp:/opt/shared --name node1 sd/node1

docker run -p 33802:33802 -v /home/lucasduete/temp:/opt/shared --name node2 sd/node2

docker run -v /home/lucasduete/temp:/opt/shared --link node1:node1 --link node2:node2 --name client sd/client
