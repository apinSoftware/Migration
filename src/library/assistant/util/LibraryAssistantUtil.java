package library.assistant.util;

import cisprotonigeriamrs.FXMLDocumentController;
import cisprotonigeriamrs.ListtoExcel;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import library.assistant.alert.AlertMaker;
import library.assistant.export.pdf.ListToPDF;

public class LibraryAssistantUtil {

    public static final String ICON_IMAGE_LOC = "/resources/icon.png";
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static Thread thread;
    private static boolean flag;

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }

    public static void initPDFExprot(AnchorPane rootPane, Node contentPane, Stage stage, List<List> data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as PDF");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);
        ListToPDF ltp = new ListToPDF();
        boolean flag = ltp.doPrintToPdf(data, saveLoc, ListToPDF.Orientation.LANDSCAPE);
        JFXButton okayBtn = new JFXButton("Okay");
        JFXButton openBtn = new JFXButton("View File");
        openBtn.setOnAction((ActionEvent event1) -> {
            try {
                Desktop.getDesktop().open(saveLoc);
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "Could not load file", "Cant load file", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        if (flag) {
            JOptionPane.showMessageDialog(null, "Completed", "Member data has been exported.", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static void initExcelExprot(AnchorPane rootPane, Node contentPane, Stage stage, List<List> data) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as Excel");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);
        ListtoExcel ltp = new ListtoExcel();
        boolean flag = ltp.toExcel(data, saveLoc);
        JFXButton okayBtn = new JFXButton("Okay");
        JFXButton openBtn = new JFXButton("View File");
        openBtn.setOnAction((ActionEvent event1) -> {
            try {
                Desktop.getDesktop().open(saveLoc);
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "Could not load file", "Cant load file", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        if (flag) {
            JOptionPane.showMessageDialog(null, "Completed", "Member data has been exported.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static String formatDateTimeString(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static String getDateString(Date date) {
        return DATE_FORMAT.format(date);
    }
}
