package client;

import com.google.gson.Gson;
import model.Answer;
import model.Expression;
import model.Result;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)) {
                Gson gson = new Gson();
                Expression expression = gson.fromJson(reader, Expression.class);
                System.out.print(expression);

                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

                Double clientAnswer = Double.valueOf(consoleReader.readLine());
                gson.toJson(new Answer(clientAnswer), writer);

                Result result = gson.fromJson(reader, Result.class);

                System.out.println(result);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
