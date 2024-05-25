package org.example.morpionRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;

public class GameHandler extends  Thread{
    private Socket player1Socket;
    private Socket player2Socket;
    private TicTacToe game;

    public GameHandler(Socket player1Socket, Socket player2Socket, TicTacToe game) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        this.game = game;
    }

    public void run() {
        try (BufferedReader in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
             PrintWriter out1 = new PrintWriter(player1Socket.getOutputStream(), true);
             BufferedReader in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
             PrintWriter out2 = new PrintWriter(player2Socket.getOutputStream(), true)) {

            out1.println("Bienvenue joueur 1! Vous êtes X.");
            out2.println("Bienvenue joueur 2! Vous êtes O.");

            boolean continuePlaying = true;
            while (continuePlaying) {
                game.resetBoard();
                while (true) {
                    out1.println(game.getBoard());
                    out2.println(game.getBoard());

                    out1.println("Votre mouvement (format: x y):");
                    String move1 = in1.readLine();
                    String[] parts1 = move1.split(" ");
                    int x1 = Integer.parseInt(parts1[0]);
                    int y1 = Integer.parseInt(parts1[1]);
                    game.makeMove(x1, y1, 1);
                    if (checkGameEnd(out1, out2)) break;

                    out2.println(game.getBoard());
                    out1.println(game.getBoard());

                    out2.println("Votre mouvement (format: x y):");
                    String move2 = in2.readLine();
                    String[] parts2 = move2.split(" ");
                    int x2 = Integer.parseInt(parts2[0]);
                    int y2 = Integer.parseInt(parts2[1]);
                    game.makeMove(x2, y2, 2);

                    if (checkGameEnd(out1, out2)) break;
                }
                continuePlaying = askForRematch(in1, out1, in2, out2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean askForRematch(BufferedReader in1, PrintWriter out1, BufferedReader in2, PrintWriter out2) throws IOException {
        out1.println("Voulez-vous rejouer ? (oui/non)");
        out2.println("Voulez-vous rejouer ? (oui/non)");

        String response1 = in1.readLine().trim().toLowerCase();
        String response2 = in2.readLine().trim().toLowerCase();

        return response1.equals("oui") && response2.equals("oui");
    }

    private boolean checkGameEnd(PrintWriter out1, PrintWriter out2) throws RemoteException {
        int winner = game.checkWinner();
        if (winner != 0) {
            String message;
            if (winner == 1) {
                message = "Joueur 1 (X) gagne !";
            } else if (winner == 2) {
                message = "Joueur 2 (O) gagne !";
            } else {
                message = "Match nul !";
            }
            out1.println(message);
            out2.println(message);
            return true;
        }
        return false;
    }
}
