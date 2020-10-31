package server;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080, 2)) {
            //noinspection InfiniteLoopStatement
            while (true) {
                new Session(serverSocket.accept(), serverSocket.accept());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
