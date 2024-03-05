package org.example.exampleRMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;

public class Client {
    public static void main(String[] args) {
        try {
            Remote r = Naming.lookup("rmi://" + InetAddress.getLocalHost().getHostName() + "/Generation");
            if (r instanceof GenerationInterface) {
                String chaine = ((GenerationInterface) r).genererChaine(100); System.out.println("Chaîne générée : " + chaine);
            }
        } catch (Exception e) { e.printStackTrace();
        }
    }
}
