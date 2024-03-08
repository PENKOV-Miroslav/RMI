package org.example.morpion;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MorpionServeur {
    public static void main(String[] args) {
        try {
            int hauteur = 3;
            int largeur = 3;

            MorpionGame game = new MorpionGame(hauteur, largeur);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("MorpionGame", game);

            System.out.println("Serveur prêt !");
        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}
