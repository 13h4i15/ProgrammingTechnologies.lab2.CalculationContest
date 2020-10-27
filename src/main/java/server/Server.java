package server;

import model.Expression;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) {
        //System.out.println(new Expression(1, 2, Expression.Operation.ADDITION));
        try (ServerSocket serverSocket = new ServerSocket(8080, 2)) {
            Session session = new Session(serverSocket.accept(), serverSocket.accept());
            try {
                Thread.sleep(1000000000L);
            }catch (InterruptedException ignore){

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}
