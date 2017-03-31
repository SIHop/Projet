/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author SIHop coding team
 * @param <T>
 */
public interface DAO<T> {
    
    
    public Connection connect = ConnectionHygieSQL.getInstance();

    /**
     * Récupérer un objet en fonction des paramètres envoyés
     *
     * @param arg liste des arguments de recherche
     * @param val liste des valeurs des arguments de recherche /!\ même ordre que
     * arg /!\
     */
    public abstract T find(ArrayList<String> arg, ArrayList<String> val);

    /**
     * Récupérer une liste d'objet en fonction des paramètres envoyés
     *
     * @param arg liste des arguments de recherche
     * @param val liste des valeurs des arguments de recherche /!\ même ordre que
     * arg /!\
     */
    public abstract ArrayList<T> findMultiple(ArrayList<String> arg, ArrayList<String> val);

    /**
     * Créer une entrée dans la basse de données à partir de l'objet envoyé
     *
     * @param obj
     */
    public abstract T create(T obj);

    /**
     * Met à jour les données en db en fonction de l'objet fournis
     *
     * @param obj l'objet à mettre à jour dans la DB
     */
    public abstract T update(T obj);

    /**
     * Supprime une entrée de la base en fonction de l'objet fourni
     */
    public abstract T delete(T obj);
    
    /**
     * Retourne l'id maximum de la table en forme de String
     * @return id max, où -1 si la requête a échoué
     */
    public abstract int getMaxId();

}
