/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interoperabilite;

import db.GestionnaireDB.DAOFactory;
import java.util.ArrayList;
import java.util.Arrays;
import library.interfaces.ClientHL7;
import library.interfaces.Patient;
import library.interfaces.ServeurHL7;
import library.structure.groupe.messages.Message;
import nf.DPI.DPI;

/**
 *
 * @author Loïc
 */
public class TestHL7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Thread(() -> {
            Patient ps;
            Message mess;
            
            ServeurHL7 serveur = new ServeurHL7();
            serveur.connection(6565);
            serveur.ecoute();
            String messageHL7 = serveur.protocole();
            String messageHL7final = "";
            for(int i = 0;i<messageHL7.length();i++){
                char ch = messageHL7.charAt(i);
                if (Character.isWhitespace(ch)) {
                } else {
                    messageHL7final += ch;
                }
            }
            System.out.println(messageHL7final);
            ps = serveur.getPatient();
            mess = serveur.getMessage();
            serveur.fermer();
        }).start();

        ClientHL7 client = new ClientHL7();
        client.connexion("localhost", 6565);

        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
        
        System.out.println("patient : " + dpi.toString());

        Patient p = dpi.dpiToPatient();
        
        System.out.println("Nom : " + p.getFamillyName() 
                + "\nPrenom : " + p.getFirstName() 
                + "\nSexe : " + p.getCharSex() 
                + "\nDate de naissance : " + p.getBirth() 
                + "\nIPP : " + p.getID()
                + "\nDécéde : " + p.isDeath() 
                + "\ndecé (date) : " + p.getDeath() 
                + "\ndischarge le : " + p.getDateDicharge());
        
        client.admit(p);

    }

}
