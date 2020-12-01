package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Answer;
import model.Expression;
import model.Result;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)) {
                System.out.println("Waiting for players to load");

                ObjectMapper mapper = new ObjectMapper();
                Expression expression = mapper.readValue(reader.readLine(), Expression.class);
                System.out.print(expression);

                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

                double clientAnswer;
                while (true) {
                    String value = consoleReader.readLine();
                    if (NumberUtils.isNumber(value)) {
                        clientAnswer = NumberUtils.createDouble(value);
                        break;
                    } else {
                        System.out.println("It's not a number, enter correct value");
                        System.out.print("Answer> ");
                    }
                }

                writer.println(mapper.writeValueAsString(new Answer(clientAnswer)));

                System.out.println("Waiting for the result");

                Result result = mapper.readValue(reader.readLine(), Result.class);

                System.out.println(result);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
