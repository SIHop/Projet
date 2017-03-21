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
 *Tests pour l'intéropérabilité 
 * @author Loïc
 */
public class TestHL7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Thread(() -> {
            Patient ps;

            ServeurHL7 serveur = new ServeurHL7();
            serveur.connection(6565);
            serveur.ecoute();
            String messageHL7 = serveur.protocole();
            String messageHL7final = "";
            for (int i = 0; i < messageHL7.length(); i++) {
                char ch = messageHL7.charAt(i);
                if (Character.isWhitespace(ch)) {
                } else {
                    messageHL7final += ch;
                }
            }
            System.out.println("---------------------------------------");
            System.out.println("messageHL7final : ");
            System.out.println(messageHL7final);
            System.out.println("---------------------------------------");
            ps = serveur.getPatient();
            Message mess = serveur.getMessage();
            serveur.fermer();
            System.out.println("---------------------------------------");
            System.out.println("Patient serveur nom de famille : "+ps.getFamillyName());
            System.out.println("Patient serveur prénom : "+ps.getFirstName());
            System.out.println("Patient serveur IPP : "+ps.getID());
            System.out.println("Patient serveur est mort : "+ps.isDeath());
            System.out.println("Patient serveur sexe : "+ps.getCharSex());
            System.out.println("Patient serveur date de naissance : "+ps.getBirth());
            System.out.println("---------------------------------------");
        }).start();

        ClientHL7 client = new ClientHL7();
        client.connexion("localhost", 6565);

        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
        
        System.out.println("---------------------------------------");
        System.out.println("Patient de la base de donnée: " + dpi.toString());
        System.out.println("---------------------------------------");

        System.out.println("---------------------------------------");
        System.out.println("test : ");
        Patient p = dpi.dpiToPatient();
        System.out.println("---------------------------------------");
        
        System.out.println("---------------------------------------");
        System.out.println("Patient client nom de famille : " + p.getFamillyName()
                + "\nPatient client prénom : " + p.getFirstName()
                + "\nPatient client sexe : " + p.getCharSex()
                + "\nPatient client date de naissance : " + p.getBirth()
                + "\nPatient clientIPP : " + p.getID()
                + "\nPatient client est mort : " + p.isDeath()
                + "\nPatient client date de décès : " + p.getDeath()
                + "\nPatient client est parti le : " + p.getDateDicharge());
        System.out.println("---------------------------------------");
        
        System.out.println("---------------------------------------");
        System.out.println("admit : ");
        client.admit(p);
        System.out.println("---------------------------------------");

    }

}
