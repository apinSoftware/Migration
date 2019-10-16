/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class DatabaseHandler {
    
    private static DatabaseHandler handler = null;

        
    public static String APINDB_URL = "jdbc:mysql://127.0.0.1:3306/apindb?zeroDateTimeBehavior=convertToNull";  
    public static String NMRSURL = "jdbc:mysql://127.0.0.1:3306/openmrs?zeroDateTimeBehavior=convertToNull";  
    private static  String username = "root";
    private static  String password = "Nu66et";  
    
    //public static String APINDB_URL;  
    //public static String NGMRSDB_URL; 
   // private static  String username;
   // private static  String password;  
    
    
    private static Connection conn = null;
    private static Connection conn2 = null;
    private static Statement stmt = null;
    private static Connection conn3;
    

    static {
        
        createApinConnection();
        createMRSDBConnection(); 
    }

    private DatabaseHandler() {
      
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
          
            handler = new DatabaseHandler();
        }
        return handler;
    }
    
     private static void createApinConnection() {
        try {
           // APINDB_URL =   Connectionstring.getAPINDB_URL();
           // username =  Connectionstring.getUsername();
            //password = Connectionstring.getPassword();
            
             APINDB_URL =   Connectionstring.getAPINDB_URL();
            username =  Connectionstring.getUsername();
            password = Connectionstring.getPassword();
            System.out.print(APINDB_URL +"|"+username+"|"+password);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(APINDB_URL,username,password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't load database \n Please ensure database is running and databse exist before runing app", "Database Error", JOptionPane.ERROR_MESSAGE);
            exit(0);
        }
    }
    private static void createMRSDBConnection() {
        try {
            //NGMRSDB_URL =  Connectionstring.getNGMRSDB_URL();
            //username =  Connectionstring.getNGMRSDB_username();
            //password = Connectionstring.getNGMRSDB_passsword();
            //NGMRSDB_URL =  "jdbc:mysql://127.0.0.1:3306/openmrs?zeroDateTimeBehavior=convertToNull";
          
               System.out.print(NMRSURL +"|"+username+"|"+password);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn2 = DriverManager.getConnection(NMRSURL,username,password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't load database  \n Please ensure database is running and databse exist before runing app", "Database Error", JOptionPane.ERROR_MESSAGE);
             exit(0);
        }
    }

    public Connection getApinConnection() {
         return conn;
    }
    
    public Connection closeApinConnection() {
         return conn;
    }
    
    public Connection getApinConnectionAlt() {
         return conn3;
    }
    
    public Connection getMRSDBConnection() {
        return conn2;
    }
}
