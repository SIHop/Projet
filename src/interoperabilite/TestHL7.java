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
import library.interfaces.ServeurHL7;
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
            public void run() {
                ServeurHL7 serveur = new ServeurHL7();
                serveur.connection(30000);
                serveur.ecoute();
                serveur.protocole();
                System.out.println(DPI.patientToDpi(serveur.getPatient()).toString());
                serveur.fermer();
                
            }
       }).start();
        
        ClientHL7 client = new ClientHL7();
        client.connexion("localhost", 30000);
        
        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
        System.out.println(dpi.getLit().getIdentifient());
        client.admit(dpi.dpiToPatient());
        System.out.println(client.getMsg().toString());
    }

}
