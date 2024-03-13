package org.example.testCoPCDist;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RemoteInterface {
    protected Server() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("Message reçu du client : " + message);
    }

    public static void main(String[] args) {
        try {
            String serverAddress = "localhost"; // Adresse IP ou nom d'hôte du serveur
            System.setProperty("java.rmi.server.hostname", serverAddress);

            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Server server = new Server();
            java.rmi.Naming.rebind("//" + serverAddress + "/MyServer", server);
            System.out.println("Server started...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

