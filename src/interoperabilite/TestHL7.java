/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interoperabilite;

import api.Parser;
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
        String adresse = "localhost";
        int port = 6565;
        new Thread(() -> {
            Serveur serveur = new Serveur(port);
            Patient ps;

            String messageHL7 = serveur.getMessageHL7();
            String messageHL7final = serveur.getMessageHL7lisible();
            System.out.println("---------------------------------------");
            System.out.println("messageHL7final : ");
            System.out.println(messageHL7final);
            System.out.println("---------------------------------------");
            ps = serveur.getPs();
            Parser parse = new Parser(messageHL7);
            System.out.println("---------------------------------------");
            System.out.println("parser : ");
            System.out.println(parse.getPatient().getBirth());
            System.out.println("---------------------------------------");
            serveur.fermeture();
            System.out.println("---------------------------------------");
            System.out.println("Patient serveur nom de famille : "+ps.getFamillyName());
            System.out.println("Patient serveur prénom : "+ps.getFirstName());
            System.out.println("Patient serveur IPP : "+ps.getID());
            System.out.println("Patient serveur est mort : "+ps.isDeath());
            System.out.println("Patient serveur sexe : "+ps.getCharSex());
            System.out.println("Patient serveur date de naissance : "+ps.getBirth());
            System.out.println("---------------------------------------");
        }).start();
        
        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
        
        Client client = new Client(dpi,adresse,port);
        
        System.out.println("---------------------------------------");
        System.out.println("Patient de la base de donnée: " + dpi.toString());
        System.out.println("---------------------------------------");

        System.out.println("---------------------------------------");
        System.out.println("test : ");
        Patient p = client.getP();
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
        
        
    }
}
