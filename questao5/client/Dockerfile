FROM maven:3.3.9-jdk-8

ADD . /opt/client
RUN cd /opt/client && mvn compile package -DskipTests=true
RUN chmod +x /opt/client/start.sh

CMD /opt/client/start.sh