package bookingsystem;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Bdb is a helper class for accessing the SQLite database.
 */

public class Bdb {
    
    public static Connection conn = null;
    public static String dbPath = "jdbc:sqlite:bdb.db";
    
    /**
     * Setup and connect to the SQLite database
     * @return Connection success
     * @throws SQLException 
     */
    public static boolean setup() throws SQLException {
        conn = DriverManager.getConnection(dbPath);
        return true;
    }
    
    /**
     * Make a SELECT SQL query to the database
     * @param query SQL query
     * @return Results from the database query
     * @throws SQLException 
     */
    public static ResultSet selectQuery(String query) throws SQLException {
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        return rs;
    }
    
    /**
     * Make an INSERT or UPDATE query to the database
     * @param query SQL query
     * @return Success of the INSERT or UPDATE to the database
     * @throws SQLException 
     */
    public static boolean iuQuery(String query) throws SQLException {
        
        Statement stmt = conn.createStatement();
        int updates = stmt.executeUpdate(query);
        
        if (updates > 0) {
            return true;
        }
        return false;
        
    }
    
}
