package org.example.morpionRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class cli_1 {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    System.out.println(serverMessage);
                    if (serverMessage.contains("Votre mouvement")) {
                        System.out.print("Entrer votre mouvement (format: x y): ");
                        String move = scanner.nextLine();
                        out.println(move);
                        break;
                    }else if (serverMessage.contains("Voulez-vous rejouer ?")) {
                        String response = scanner.nextLine();
                        out.println(response);
                        if (!response.equalsIgnoreCase("oui")) {
                            return;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
