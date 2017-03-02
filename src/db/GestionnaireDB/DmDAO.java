/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nf.DPI.DM.DM;
import nf.DPI.DM.FicheDeSoins;
import nf.GestionDexploitation.Lit;

/**
 *Retourn les fiche de soins du dm chercher
 * @author Loïc
 */
public class DmDAO implements DAO<DM>{
private String query = "";

    @Override
    public DM find(ArrayList<String> arg, ArrayList<String> val) {
        return null;
    }

    @Override
    public ArrayList<DM> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        return null;
    }

    @Override
    public DM create(DM obj) {
        return null;
    }

    @Override
    public DM update(DM obj) {
        return null;
    }

    @Override
    public DM delete(DM obj) {
        return null;
    }

    @Override
    public int getMaxId() {
        return -1;
    }

        
}
