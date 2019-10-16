/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

/**
 *
 * @author user
 */
public class Connectionstring {
    
//    public static String APINDB_URL = "jdbc:mysql://127.0.0.1:3306/apindb?zeroDateTimeBehavior=convertToNull";
//    public static String APINDB_URL_ALT = "jdbc:mysql://127.0.0.1:3306/apindb";
//    
//    private static final String username = "root";
//    private static final String password ="Nu66et";   
    
    
    public static String APINDB_URL;
    public static String APINDB_URL_ALT;
    
    public static  String username;
    public static  String password;  
    
    public static String NGMRSDB_URL;
    public static String NGMRSDB_username;
    public static String NGMRSDB_passsword;
    
    public static String CURRENT_MIRATION_TITLE;

    public static String getCURRENT_MIRATION_TITLE() {
        return CURRENT_MIRATION_TITLE;
    }

    public static void setCURRENT_MIRATION_TITLE(String CURRENT_MIRATION_TITLE) {
        Connectionstring.CURRENT_MIRATION_TITLE = CURRENT_MIRATION_TITLE;
    }

    public static String getNGMRSDB_username() {
        return NGMRSDB_username;
    }

    public static void setNGMRSDB_username(String NGMRSDB_username) {
        Connectionstring.NGMRSDB_username = NGMRSDB_username;
    }

    public static String getNGMRSDB_passsword() {
        return NGMRSDB_passsword;
    }

    public static void setNGMRSDB_passsword(String NGMRSDB_passsword) {
        Connectionstring.NGMRSDB_passsword = NGMRSDB_passsword;
    }

    public static String getNGMRSDB_URL() {
        return NGMRSDB_URL;
    }

    public static void setNGMRSDB_URL(String NGMRSDB_URL) {
        Connectionstring.NGMRSDB_URL = NGMRSDB_URL;
    }

    public static String getAPINDB_URL() {
        return APINDB_URL;
    }

    public static void setAPINDB_URL(String APINDB_URL) {
        Connectionstring.APINDB_URL = APINDB_URL;
    }

    public static String getAPINDB_URL_ALT() {
        return APINDB_URL_ALT;
    }

    public static void setAPINDB_URL_ALT(String APINDB_URL_ALT) {
        Connectionstring.APINDB_URL_ALT = APINDB_URL_ALT;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Connectionstring.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Connectionstring.password = password;
    }
    
    
    
    
}
