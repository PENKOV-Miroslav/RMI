package org.example.morpionRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToe{
    private char[][] board;
    private int currentPlayer;

    protected TicTacToeImpl() throws RemoteException {
        super();
        board = new char[3][3];
        currentPlayer = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public String getBoard() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
                if (j < 2) sb.append("|");
            }
            sb.append("\n");
            if (i < 2) sb.append("-----\n");
        }
        return sb.toString();
    }

    public boolean makeMove(int x, int y, int player) throws RemoteException {
        if (board[x][y] == ' ' && player == currentPlayer) {
            board[x][y] = (player == 1) ? 'X' : 'O';
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            return true;
        } else {
            return false;
        }
    }

    public int checkWinner() throws RemoteException {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return (board[i][0] == 'X') ? 1 : 2;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return (board[0][i] == 'X') ? 1 : 2;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return (board[0][0] == 'X') ? 1 : 2;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return (board[0][2] == 'X') ? 1 : 2;
        }

        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) return 3;

        return 0;
    }

    public void resetBoard() throws RemoteException {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 1;
    }
}
