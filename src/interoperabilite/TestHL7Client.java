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
import library.interfaces.Patient;
import nf.DPI.DPI;

/**
 *
 * @author quentin
 */
public class TestHL7Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String adresse = "192.168.43.91"; //adresse du serveur à joindre
        int port = 6565; //port d'écoute du serveur
        new Thread(() -> {
            int i = 0;
            while(i < 200000){
                i++;
            }
//            Serveur serveur = new Serveur(6566);
//            Patient ps;
//            String messageHL7 = serveur.getMessageHL7();
//            String messageHL7final = serveur.getMessageHL7lisible();
//            serveur.fermeture();
        }).start();
        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));

        Client client = new Client(dpi, adresse, port);

        System.out.println("---------------------------------------");
        System.out.println("Patient de la base de donnée : " + dpi.toString());
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
