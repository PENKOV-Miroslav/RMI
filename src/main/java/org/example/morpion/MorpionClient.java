package org.example.morpion;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MorpionClient {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 1099;
    private static final String REGISTRY_NAME = "MorpionGame";

    public static void main(String[] args) {
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;

        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            InterfaceMorpion stub = (InterfaceMorpion) registry.lookup(REGISTRY_NAME);

            Scanner scanner = new Scanner(System.in);
            boolean gameOver = false;

            while (!gameOver) {
                System.out.println(stub.visualizeGrid());
                System.out.println("Entrez les coordonnées de votre prochain coup (ligne colonne) : ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Vérifier si les coordonnées sont valides
                if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                    System.out.println("Coordonnées invalides. Veuillez entrer des valeurs entre 0 et 2 inclusivement.");
                    continue; // Demander à nouveau les coordonnées
                }

                stub.playMove(row, col, 'X');

                gameOver = stub.isGameOver();

                if (gameOver) {
                    char winner = stub.checkWinner();
                    if (winner == ' ') {
                        System.out.println("Match nul !");
                    } else {
                        System.out.println("Le joueur " + winner + " a gagné !");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur client : " + e.toString());
            e.printStackTrace();
        }
    }
}
