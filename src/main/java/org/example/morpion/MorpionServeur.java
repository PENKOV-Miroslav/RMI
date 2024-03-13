package org.example.morpion;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MorpionServeur extends UnicastRemoteObject implements InterfaceMorpion {
    protected MorpionServeur() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            int hauteur = 3;
            int largeur = 3;

            MorpionGame game = new MorpionGame(hauteur, largeur);
            MorpionServeur morpionServeur = new MorpionServeur();
            Registry registry = LocateRegistry.createRegistry(1099);
            String addr = "rmi://" + "192.168.84.124" + "/communication"; Naming.rebind(addr, morpionServeur);
            registry.bind("MorpionGame", game);

            System.out.println("Serveur prêt !");
        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void playMove(int row, int col, char player) throws RemoteException {

    }

    @Override
    public boolean isGameOver() throws RemoteException {
        return false;
    }

    @Override
    public char checkWinner() throws RemoteException {
        return 0;
    }

    @Override
    public void resetGame() throws RemoteException {

    }

    @Override
    public String visualizeGrid() throws RemoteException {
        return null;
    }
}
