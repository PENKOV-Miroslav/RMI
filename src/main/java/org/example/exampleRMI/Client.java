package org.example.exampleRMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;

public class Client {
    public static void main(String[] args) {
        try {
            // adresse IP wifi de la machine sur laquelle le serveur se lance
            Remote r = Naming.lookup("rmi://" + "192.168.83.76" + "/Generation");
            if (r instanceof GenerationInterface) {
                String chaine = ((GenerationInterface) r).genererChaine(100); System.out.println("Chaîne générée : " + chaine);
            }
        } catch (Exception e) { e.printStackTrace();
        }
    }
}
