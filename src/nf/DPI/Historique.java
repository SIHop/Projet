package nf.DPI;


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
 * @author audre
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
        return "Historique ipp = " + this.ipp.getIPP() + " date de déces = '" + this.dateDeces.toString() + "' date de fin d'archivage = '" + this.dateFinArchivage.toString()+"'";
    }

}
