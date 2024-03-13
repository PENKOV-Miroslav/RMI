package org.example.testCoPCDist;

import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            String serverAddress = "localhost"; // Adresse IP ou nom d'hôte du serveur
            System.setProperty("java.rmi.registry.host", serverAddress);

            RemoteInterface server = (RemoteInterface) Naming.lookup("//" + serverAddress + "/MyServer");
            server.sendMessage("Bonjour, serveur!");
            System.out.println("Message envoyé au serveur.");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

