package org.example.testConnexionPC;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur extends UnicastRemoteObject implements CommInterface{

    protected Serveur() throws RemoteException {
        super();
    }

    public void envoyerMessage(String message) throws RemoteException {
        System.out.println("Message reçu : " + message);
    }

    public static void main(String[] args) {
        try {
            Serveur serveur = new Serveur();
            Registry registry = LocateRegistry.createRegistry(1099); // Port par défaut
            registry.rebind("communication", serveur);
            System.out.println("Serveur RMI prêt.");
        } catch (Exception e) {
            System.err.println("Erreur du serveur RMI : " + e.toString());
            e.printStackTrace();
        }
    }
}
