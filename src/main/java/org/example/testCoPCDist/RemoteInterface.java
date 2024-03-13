package org.example.testCoPCDist;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    void sendMessage(String message) throws RemoteException;
}

