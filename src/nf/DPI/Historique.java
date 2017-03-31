package nf.DPI;


import db.GestionnaireDB.DAOFactory;
import db.GestionnaireDB.HistoriqueDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import nf.Adresse.DateT;
import nf.DPI.DMA.IPP;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SIHop coding team
 */
public class Historique {

    private final IPP ipp;
    private final DateT dateDeces;
    private DateT dateFinArchivage;

    public Historique(IPP ipp, DateT dateDeces, DateT dateFinArchivage) {
        this.ipp = ipp;
        this.dateDeces = dateDeces;
        if (dateFinArchivage == null) {
            dateDeces.getC().add(Calendar.YEAR, +3);
            this.dateFinArchivage = dateDeces;
        }else{
            this.dateFinArchivage = dateFinArchivage;
        }
    }
    
    public static Historique getHistoriqueByIPP(int IPP){
        HistoriqueDAO histDAO = (HistoriqueDAO) DAOFactory.getHistoriqueDAO();
        return histDAO.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(Integer.toString(IPP))));
    }

    /**
     * @return the ipp
     */
    public IPP getIpp() {
        return ipp;
    }

    /**
     * @return the dateDeces
     */
    public DateT getDateDeces() {
        return dateDeces;
    }

    /**
     * @return the dateFinArchivage
     */
    public DateT getDateFinArchivage() {
        return dateFinArchivage;
    }

    /**
     * @param dateFinArchivage the dateFinArchivage to set
     */
    public void setDateFinArchivage(DateT dateFinArchivage) {
        this.dateFinArchivage = dateFinArchivage;
    }
/**
 * renvoi l'historique ipp d'un patient décédé 
 * @return 
 */
    @Override
    public String toString() {
        return "Historique ipp = " + this.ipp.getIPP() + " date de décès = '" + this.dateDeces.toString() + "' date de fin d'archivage = '" + this.dateFinArchivage.toString()+"'";
    }

}
