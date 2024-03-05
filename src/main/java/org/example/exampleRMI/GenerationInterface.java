package org.example.exampleRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GenerationInterface extends Remote {
    public String genererChaine(int n) throws RemoteException;
}
