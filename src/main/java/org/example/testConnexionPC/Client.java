package org.example.testConnexionPC;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            CommInterface serveur = (CommInterface) registry.lookup("communication");
            serveur.envoyerMessage("Bonjour depuis le client !");
        } catch (Exception e) {
            System.err.println("Erreur du client RMI : " + e.toString());
            e.printStackTrace();
        }
    }
}
