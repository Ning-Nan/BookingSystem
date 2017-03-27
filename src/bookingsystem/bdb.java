/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bdb {
    
    public static Connection conn = null;
    public static String dbPath = "jdbc:sqlite:bdb.db";
    
    public static boolean setup() throws SQLException {
        conn = DriverManager.getConnection(dbPath);
        return true;
    }
    
    public static ResultSet selectQuery(String query) throws SQLException {
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        return rs;
    }
    
    public static boolean iuQuery(String query) throws SQLException {
        
        Statement stmt = conn.createStatement();
        int updates = stmt.executeUpdate(query);
        
        if (updates > 0) {
            return true;
        }
        return false;
        
    }
    
}
