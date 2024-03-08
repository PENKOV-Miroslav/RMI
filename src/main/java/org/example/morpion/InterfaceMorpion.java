package org.example.morpion;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceMorpion extends Remote {
    void playMove(int row, int col, char player) throws RemoteException;
    boolean isGameOver() throws RemoteException;
    char checkWinner() throws RemoteException;
    void resetGame() throws RemoteException;
    String visualizeGrid() throws RemoteException;
}
