/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interoperabilite;

import library.interfaces.ClientHL7;
import library.interfaces.Patient;
import nf.DPI.DPI;

/**
 *
 * @author Loïc
 */
public class Client {
    private final ClientHL7 client = new ClientHL7();
    private final DPI dpi;
    private final Patient p;

    public Client(DPI dpi, String adresse, int portEnvoie) {
        this.dpi = dpi;
        this.p = dpi.dpiToPatient();
        client.connexion(adresse, portEnvoie);
        System.out.println("-----------testConnexion------------");
        client.admit(p);
    }

    public ClientHL7 getClient() {
        return client;
    }

    public DPI getDpi() {
        return dpi;
    }

    public Patient getP() {
        return p;
    }
    
    
    
}
