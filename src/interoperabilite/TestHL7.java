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

        new Thread(new Runnable() {

            @Override
            public void run() {
                Patient ps = null;
                Message mess = null;
                
                ServeurHL7 serveur = new ServeurHL7();
                serveur.connection(6502);
                serveur.ecoute();
                String messageHL7 = serveur.protocole();
                System.out.println("test");
                System.out.println("Recus : "  + messageHL7 );  
                ps = serveur.getPatient();
                mess = serveur.getMessage();
                serveur.fermer();
                System.out.println(ps.getFamillyName());
                System.out.println(ps.getCharSex());
                System.out.println(mess);
            }
        }).start();

        ClientHL7 client = new ClientHL7();
        client.connexion("localhost", 6502);

        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
        

        Patient p = dpi.dpiToPatient();
        System.out.println(dpi.getDateDeNaissance().getC().getTime());
        System.out.println("Nom : " + p.getFamillyName() + " Prenom : " + p.getFirstName() + " Sexe : " + p.getCharSex() + " Date de naissance : " + p.getBirth() + " IPP : " + p.getID()
                + " Décéde : " + p.isDeath() + " decé (date) : " + p.getDeath() + " discharge le : " + p.getDateDicharge());

        client.admit(p);

    }

}
