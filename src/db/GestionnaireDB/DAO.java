/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Loïc
 * @param <T>
 */
public interface DAO<T> {

    public Connection connect = ConnectionHygieSQL.getInstance();

    /**
     * Récupéré un objet en fonction des parametre envoyer
     *
     * @param arg liste des argument de recherche
     * @param val liste des valeur des argument de recherche /!\ même ordre que
     * arg /!\
     */
    public abstract T find(ArrayList<String> arg, ArrayList<String> val);

    /**
     * Récupéré une liste d'objet en fonction des parametre envoyer
     *
     * @param arg liste des argument de recherche
     * @param val liste des valeur des argument de recherche /!\ même ordre que
     * arg /!\
     */
    public abstract ArrayList<T> findMultiple(ArrayList<String> arg, ArrayList<String> val);

    /**
     * Crée une entré dans la basse de donnée a partir de l'objet envoyer
     *
     * @param obj
     */
    public abstract T create(T obj);

    /**
     * Met a jours les donnée en db en fonction de l'objet fournis
     *
     * @param obj l'objet a mettre a jours dans la DB
     */
    public abstract T update(T obj);

    /**
     * Supprime une entrée de la base en fonction de l'objet fournis
     */
    public abstract T delete(T obj);
    
    /**
     * Renvoie l'id maximum de la table en forme de String
     * @return id max, où -1 si la requete a échouer
     */
    public abstract int getMaxId();

}
