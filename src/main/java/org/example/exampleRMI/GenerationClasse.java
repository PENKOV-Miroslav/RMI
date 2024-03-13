package org.example.exampleRMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class GenerationClasse extends UnicastRemoteObject implements GenerationInterface {

    protected GenerationClasse() throws RemoteException{
        super();
    }

    public String genererChaine(int n)throws RemoteException{
        String chaine ="";
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            chaine +=(char) (rnd.nextInt(26)+ 'a');
        }
        return chaine;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); GenerationClasse gc = new GenerationClasse();
            // adresse IP wifi de la machine sur laquelle le serveur se lance
            String addr = "rmi://" + InetAddress.getLocalHost().getHostName() + "/Generation"; Naming.rebind(addr, gc);
        } catch (Exception e) { e.printStackTrace();
        }
    }

}
