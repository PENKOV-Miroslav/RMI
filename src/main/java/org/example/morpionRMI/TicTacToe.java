package org.example.morpionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToe extends Remote {
    String getBoard() throws RemoteException;
    boolean makeMove(int x, int y, int player) throws RemoteException;
    int checkWinner() throws RemoteException;
    void resetBoard() throws RemoteException;
}
