package org.example.testConnexionPC;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommInterface extends Remote {

    public void envoyerMessage(String message) throws RemoteException;
}
