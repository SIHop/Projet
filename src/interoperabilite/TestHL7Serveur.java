/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interoperabilite;

import api.Parser;
import library.interfaces.Patient;

/**
 *
 * @author SIHop coding team
 */
public class TestHL7Serveur {

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        int port = 6565;//port d'écoute de mon serveur
//        new Thread(() -> {
//            Serveur serveur = new Serveur(port);
//            Patient ps;
//
//            System.out.println("TestHL7Serveur");
//            String messageHL7 = serveur.getMessageHL7();
//            String messageHL7final = serveur.getMessageHL7lisible();
//            System.out.println("---------------------------------------");
//            System.out.println("messageHL7final : ");
//            System.out.println(messageHL7final);
//            System.out.println("---------------------------------------");
//            ps = serveur.getPs();
//            Parser parse = new Parser(messageHL7);
//            System.out.println("---------------------------------------");
//            System.out.println("parser : ");
//            System.out.println(parse.getPatient().getBirth());
//            System.out.println("---------------------------------------");
//            serveur.fermeture();
//            System.out.println("---------------------------------------");
//            System.out.println("Patient serveur nom de famille : " + ps.getFamillyName());
//            System.out.println("Patient serveur prénom : " + ps.getFirstName());
//            System.out.println("Patient serveur IPP : " + ps.getID());
//            System.out.println("Patient serveur est mort : " + ps.isDeath());
//            System.out.println("Patient serveur sexe : " + ps.getCharSex());
//            System.out.println("Patient serveur date de naissance : " + ps.getBirth());
//            System.out.println("---------------------------------------");
//        }).start();
//    }
}
