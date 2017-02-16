/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.hop;

import java.sql.*;

/**
 *
 * @author Loïc
 */
public class SIHop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost/mynewdatabase?"
                    + "user=root&password=loic93";
            con = DriverManager.getConnection(connectionUrl);
            
            Statement stmt = null;
            ResultSet rs = null;

            //SQl query command
            String SQl = "SELECT * FROM counselor";
            stmt = con.createStatement();
            
            String strSQL = "DELETE FROM counselor WHERE first_name = 'Loïc' ";
            
            int rowsEffected = stmt.executeUpdate(strSQL);
            System.out.println(rowsEffected + "  : Rows effected");
            
            
            rs = stmt.executeQuery(SQl);
            while(rs.next()){
                System.out.println(rs.getString("first_name") + " : " + rs.getString("nick_name"));
            }
            
            stmt.close();
            
            
        } catch (SQLException e) {
            System.out.println("SQL Esxception" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class not found exception" + cE.toString());
        }

    }

}
