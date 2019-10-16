/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author APINPC
 */
public class LogToFile {

    private static final Logger LOGGER = Logger.getLogger(LogToFile.class.getName());
    public static void writeLog(String info) {
        Handler fileHandler = null;
        Formatter simpleFormatter = null;
        try{
             
            // Creating FileHandler
            fileHandler = new FileHandler("./migration.formatter.log");
             
            // Creating SimpleFormatter
            simpleFormatter = new SimpleFormatter();
             
            // Assigning handler to logger
            LOGGER.addHandler(fileHandler);
             
            // Logging message of Level info (this should be publish in the default format i.e. XMLFormat)
            LOGGER.info(info);
             
            // Setting formatter to the handler
            fileHandler.setFormatter(simpleFormatter);
             
            // Setting Level to ALL
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
             
            // Logging message of Level finest (this should be publish in the simple format)
            LOGGER.finest("Finnest message: Logger with SIMPLE FORMATTER");
        }catch(IOException exception){
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
    }
}
