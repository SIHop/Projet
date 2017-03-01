/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Loïc
 */
public class ConnectionHygieSQL {
    /**
	 * URL de connection
	 */
	private static String url = "jdbc:mysql://mysql-sihop.alwaysdata.net:3306/sihop_db";
	/**
	 * Nom du user
	 */
	private static String user = "sihop";
	/**
	 * Mot de passe du user
	 */
	private static String passwd = "sihop123cbo";
	/**
	 * Objet Connection
	 */
	private static Connection connect;
	
	/**
	 * Méthode qui va nous retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	
}


