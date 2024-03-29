/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author tnnakwe
 */
public class CisProToNigeriaMRS extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/apinlogo.png"));
        stage.setTitle("APIN Public Health Initiatives");
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
