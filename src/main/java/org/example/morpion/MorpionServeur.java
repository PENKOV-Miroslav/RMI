package org.example.morpion;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MorpionServeur {
    public static void main(String[] args) {
        try {
            int hauteur = 3;
            int largeur = 3;

            MorpionGame game = new MorpionGame(hauteur, largeur);
            InterfaceMorpion stub = (InterfaceMorpion) UnicastRemoteObject.exportObject(game, 0);

            // Vérifier si l'objet est déjà exporté, puis le désenregistrer si nécessaire
            try {
                UnicastRemoteObject.unexportObject(stub, true);
            } catch (Exception e) {
                // L'objet n'était pas exporté, pas besoin de désenregistrer
            }

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("MorpionGame", stub);

            System.out.println("Serveur prêt !");
        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}
