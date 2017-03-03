/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.util.ArrayList;
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.Sejour;

/**
 *
 * @author Loïc
 */
public class testDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAO<DMA> dmaDAO = DAOFactory.getDmaDAO();
        ArrayList<Sejour> listeSejour = new ArrayList<>();
        listeSejour.add(new Sejour(null, "170200003", null, null, null, null));
        listeSejour.add(new Sejour(null, "170200004", null, null, null, null));
        listeSejour.add(new Sejour(null, "170200005", null, null, null, null));

        DMA test = new DMA(listeSejour, "150000011");
        
        dmaDAO.delete(test);

//        
//        String date = "1993-08-23";
//        DateT d = new DateT(date);
//        
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        System.out.println(dateFormat.format(d.getC().getTime()));
    }

}
