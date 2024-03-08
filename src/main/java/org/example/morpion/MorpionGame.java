package org.example.morpion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MorpionGame extends UnicastRemoteObject implements InterfaceMorpion {
    private final MorpionGrille grid;
    private boolean gameOver;
    private char currentPlayer;

    public MorpionGame(int hauteur, int largeur) throws RemoteException {
        grid = new MorpionGrille(hauteur, largeur);
        gameOver = false;
        currentPlayer = 'X'; // Le joueur X commence la partie
    }

    @Override
    public void playMove(int row, int col, char player) throws RemoteException {
        // Vérifier si la case est vide et si la partie n'est pas terminée
        if (grid.getGrille()[row][col] == ' ' && !gameOver) {
            grid.getGrille()[row][col] = player; // Jouer le coup
            // Vérifier si le joueur actuel a gagné après avoir joué ce coup
            if (checkWinner(player)) {
                gameOver = true;
            } else {
                // Changer de joueur pour le prochain coup
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    @Override
    public boolean isGameOver() throws RemoteException {
        return gameOver;
    }

    @Override
    public char checkWinner() throws RemoteException {
        if (checkWinner(currentPlayer)) {
            return currentPlayer; // Renvoie le joueur actuel s'il a gagné
        } else {
            return ' '; // Renvoie un espace s'il n'y a pas de gagnant
        }
    }


    @Override
    public void resetGame() throws RemoteException {
        grid.initialiserGrille(); // Réinitialiser la grille
        gameOver = false; // Remettre le jeu à son état initial
        currentPlayer = 'X'; // Le joueur X commence la partie
    }

    @Override
    public String visualizeGrid() throws RemoteException {
        return grid.visualiserGrille();
    }

    private boolean checkWinner(char player) {
        // Logique pour vérifier s'il y a un gagnant (lignes, colonnes, diagonales)
        char[][] currentGrid = grid.getGrille();

        // Vérifier les lignes
        for (int i = 0; i < currentGrid.length; i++) {
            if (currentGrid[i][0] == player && currentGrid[i][1] == player && currentGrid[i][2] == player) {
                return true;
            }
        }

        // Vérifier les colonnes
        for (int j = 0; j < currentGrid[0].length; j++) {
            if (currentGrid[0][j] == player && currentGrid[1][j] == player && currentGrid[2][j] == player) {
                return true;
            }
        }

        // Vérifier les diagonales
        if ((currentGrid[0][0] == player && currentGrid[1][1] == player && currentGrid[2][2] == player) ||
                (currentGrid[0][2] == player && currentGrid[1][1] == player && currentGrid[2][0] == player)) {
            return true;
        }

        return false;
    }
}
