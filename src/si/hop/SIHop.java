/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.hop;

import java.sql.*;
import java.time.Year;

/**
 *
 * @author Loïc
 */
public class SIHop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int maxIPP = 1612345;
        int annee = maxIPP;
        while (annee > 100) {
            System.out.println(annee % 10);
            annee = annee / 10;
        }
        System.out.println(annee);
        int year = Year.now().getValue();
        System.out.println(year);
        annee += 2000;
        if(year == annee){
            maxIPP += 1;
            System.out.println("valeur finale : " + maxIPP);
        } else {
            year = year - 2000;
            maxIPP = year*100000+1;
            System.out.println("valeur finale : " + maxIPP);
        }
    }

}
