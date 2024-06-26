package org.example.testConnexionPC;

import java.net.InetAddress;
import java.rmi.Naming;
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
            LocateRegistry.createRegistry(1099); // Port par défaut
            String addr = "rmi://" + "192.168.84.124" + "/communication"; Naming.rebind(addr, serveur);
            System.out.println("Serveur RMI prêt.");
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            System.err.println("Erreur du serveur RMI : " + e.toString());
            e.printStackTrace();
        }
    }
}
