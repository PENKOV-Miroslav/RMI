package org.example.morpionRMI;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Middleware {
    private static final int SOCKET_PORT = 12345;
    private static final int RMI_PORT = 2000;

    public static void main(String[] args) {
        try {
            TicTacToeImpl game = new TicTacToeImpl();
            Registry registry = LocateRegistry.createRegistry(RMI_PORT);
            registry.rebind("TicTacToeService", game);

            ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);

            while (true) {
                Socket clientSocket1 = serverSocket.accept();
                Socket clientSocket2 = serverSocket.accept();
                new GameHandler(clientSocket1, clientSocket2, game).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
