/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.awt.print.Book;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import library.assistant.alert.AlertMaker;
import library.assistant.util.LibraryAssistantUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author tnnakwe
 */
public class HomepageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button cisproBTN;

    @FXML
    private Button apinopenmrsBTN, setConnection;

    @FXML
    private ImageView loadingHomeImage1, loadingHomeImage2, loadingHomeImage21;

    @FXML
    public Pane logo, settings;

    @FXML
    public TabPane tabsmenus;

    @FXML
    public Pane migrationPanel;

    @FXML
    public ComboBox dbconnection;

    @FXML
    public Label errorCount, processinglabel;

    @FXML
    public TextField ciString, ciUsername, ciPassword, apinString, apinUsername, apinPassword, openmrsString, openmrsUsername, openmrsPassword;
    private Thread thread;

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> rowId;
    @FXML
    private TableColumn<Book, String> errorText;
    @FXML
    private TableColumn<Book, String> pepID;
    @FXML
    private TableColumn<Book, String> column;
    @FXML
    private TableColumn<Book, String> wrongValue;
    @FXML
    private AnchorPane contentPane;

    ObservableList<Book> list = FXCollections.observableArrayList();
    private Thread thread1;

    /**
     * Initializes the controller class.
     */
    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void initCol() {
        rowId.setCellValueFactory(new PropertyValueFactory<>("rowId"));
        pepID.setCellValueFactory(new PropertyValueFactory<>("pepID"));
        column.setCellValueFactory(new PropertyValueFactory<>("column"));
        wrongValue.setCellValueFactory(new PropertyValueFactory<>("wrongValue"));
        errorText.setCellValueFactory(new PropertyValueFactory<>("errorText"));
    }

    @FXML
    void handleStartMigration(ActionEvent event) throws IOException {
        int selectedValue = dbconnection.getSelectionModel().getSelectedIndex();
        if (selectedValue != 0) {
            //loadingHomeImage.setVisible(false);
            JOptionPane.showMessageDialog(null, "All settings loaded successfully, press OK to continue", "Migration Alert", JOptionPane.INFORMATION_MESSAGE);

            Parent blah = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.getIcons().add(new Image("/images/apinlogo.png"));
            appStage.setTitle("APIN Public Health Initiatives");
            appStage.centerOnScreen();
            appStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a database to migrate to", "Migration Alert", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    void handleSetConnection(ActionEvent event) throws IOException {

        if (setConnection.getText().equals("Submit")) {
            logo.setVisible(true);
            settings.setVisible(false);
            setConnection.setText("Set Connection Parameter");
            Connectionstring.setNGMRSDB_URL(openmrsString.getText().trim());
            Connectionstring.setUsername(openmrsUsername.getText());
            Connectionstring.setPassword(openmrsPassword.getText());
            JOptionPane.showMessageDialog(null, "Settings changed sucessfully", "Migration Alert", JOptionPane.INFORMATION_MESSAGE);

        } else {
            logo.setVisible(false);
            settings.setVisible(true);
            setConnection.setText("Submit");
        }

    }

    @FXML
    void handleCleanICFORMResult(ActionEvent event) {
        resetList();
        int selectedValue = dbconnection.getSelectionModel().getSelectedIndex();
        if (selectedValue != 0) {
            thread1 = new Thread() {

                @Override
                public void run() {
                    Connectionstring.setAPINDB_URL(ciString.getText().trim());
                    Connectionstring.setUsername(ciUsername.getText().trim());
                    Connectionstring.setPassword(ciPassword.getText().trim());
                    list = new MigrateICFORMResult().clean(errorCount, loadingHomeImage2);
                    tableView.setItems(list);
                    loadingHomeImage2.setVisible(false);

                }
            };
            thread1.start();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a database to migrate to", "Migration Alert", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    void handleCleanPharmarcy(ActionEvent event) {
        resetList();
        int selectedValue = dbconnection.getSelectionModel().getSelectedIndex();
        if (selectedValue != 0) {
            thread = new Thread() {

                @Override
                public void run() {
                    Connectionstring.setAPINDB_URL(ciString.getText().trim());
                    Connectionstring.setUsername(ciUsername.getText().trim());
                    Connectionstring.setPassword(ciPassword.getText().trim());
                    list = new MigratePharmacyResult().cleanPharmacyRecors(errorCount, loadingHomeImage2);
                    tableView.setItems(list);
                    loadingHomeImage2.setVisible(false);
                }
            };
            thread.start();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a database to migrate to", "Migration Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    void handleCleanLab(ActionEvent event) {
        resetList();
        int selectedValue = dbconnection.getSelectionModel().getSelectedIndex();
        if (selectedValue != 0) {
            thread = new Thread() {

                @Override
                public void run() {
                    Connectionstring.setAPINDB_URL(ciString.getText().trim());
                    Connectionstring.setUsername(ciUsername.getText().trim());
                    Connectionstring.setPassword(ciPassword.getText().trim());
                    list = new LabHelpers().cleanLabRecors(errorCount, loadingHomeImage2);
                    tableView.setItems(list);
                    loadingHomeImage2.setVisible(false);
                }
            };
            thread.start();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a database to migrate to", "Migration Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {

    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void exportAsPDF(ActionEvent event) {

        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), (List) Errors.getUpdatableList());

    }

    @FXML
    private void exportAsExcel(ActionEvent event) {
        LibraryAssistantUtil.initExcelExprot(rootPane, contentPane, getStage(), (List) Errors.getUpdatableList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        // TODO
        //  loadingHomeImage1.setVisible(false);

        ciString.setText("jdbc:mysql://127.0.0.1:3306/apindb?zeroDateTimeBehavior=convertToNull");
        ciUsername.setText("root");
        ciPassword.setText("Nu66et");

        apinString.setText("jdbc:mysql://127.0.0.1:3306/apindb?zeroDateTimeBehavior=convertToNull");
        apinUsername.setText("root");
        apinPassword.setText("Nu66et");

        openmrsString.setText("jdbc:mysql://127.0.0.1:3306/server2?zeroDateTimeBehavior=convertToNull");
        openmrsUsername.setText("root");
        openmrsPassword.setText("Nu66et");

        settings.setVisible(false);

        Connectionstring.setNGMRSDB_URL(openmrsString.getText().trim());
        Connectionstring.setNGMRSDB_username(openmrsUsername.getText().trim());
        Connectionstring.setNGMRSDB_passsword(openmrsPassword.getText().trim());
        logo.setVisible(true);

        dbconnection.getItems().addAll(
                "Select database to work with",
                "Cispro database",
                "Apin Opemrs database"
        );
        dbconnection.setValue("Select database to work with");

        dbconnection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                int selectedValue = dbconnection.getSelectionModel().getSelectedIndex();
                if (selectedValue == 1) {

                    thread = new Thread() {

                        @Override
                        public void run() {
                            loadingHomeImage21.setVisible(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    Connectionstring.setAPINDB_URL(ciString.getText().trim());
                                    Connectionstring.setUsername(ciUsername.getText().trim());
                                    Connectionstring.setPassword(ciPassword.getText().trim());

                                    Connectionstring.setCURRENT_MIRATION_TITLE("MIGRATE CISPRO TO NIGERIAMRS");
                                    new LoadSettings().createIcformConcept();
                                    new LoadSettings().createPharmacyConcept();
                                    new LoadSettings().createAdultICEConcept();
                                    new LoadSettings().createLabCheckBoxesConcept();
                                    new LoadSettings().createLabConcept();
                                    new LoadSettings().createMigratefield(); 
                                    new LoadSettings().setMigrationField();
                                    migrationPanel.setVisible(true);
                                }
                            });
                        }
                    };
                    thread.start();
                    JOptionPane.showMessageDialog(null, "CISPRO selected", "CISPRO selected", JOptionPane.INFORMATION_MESSAGE);
                } else if (selectedValue == 2) {
                    loadingHomeImage21.setVisible(false);
                    thread = new Thread() {

                        @Override
                        public void run() {
                            loadingHomeImage21.setVisible(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Connectionstring.setAPINDB_URL(apinString.getText().trim());
                                    Connectionstring.setUsername(apinUsername.getText().trim());
                                    Connectionstring.setPassword(apinPassword.getText().trim());
                                    Connectionstring.setCURRENT_MIRATION_TITLE("MIGRATE APIN OPENMRS TO NIGERIAMRS");
                                    new LoadSettings().createIcformConcept();
                                    new LoadSettings().createPharmacyConcept();
                                    new LoadSettings().createAdultICEConcept();
                                    new LoadSettings().createLabCheckBoxesConcept();
                                    new LoadSettings().createLabConcept();
                                    new LoadSettings().createMigratefield(); 
                                    new LoadSettings().setMigrationField();
                                    migrationPanel.setVisible(true);
                                }
                            });
                        }
                    };
                    thread.start();
                    JOptionPane.showMessageDialog(null, "Apin OpenMrs selected", "Apin OpenMrs selected", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

    }

    private void loadData() {
        list.clear();

//        String titlex = "seun";
//        String author = "shola";
//        String id = "kola";
//        String publisher = "bayo";
//        String errorText = "bayo";
//
//        list.add(new Book(titlex, id, author, publisher, errorText));
//        tableView.setItems(list);
    }

    private void resetList() {
        list.clear();
        tableView.setItems(list);
        errorCount.setText("");
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }

    public static class Book {

        private final SimpleStringProperty rowId;
        private final SimpleStringProperty pepID;
        private final SimpleStringProperty column;
        private final SimpleStringProperty wrongValue;
        private final SimpleStringProperty errorText;

        public Book(String rowId, String pepID, String column, String wrongValue, String errorText) {
            this.rowId = new SimpleStringProperty(rowId);
            this.pepID = new SimpleStringProperty(pepID);
            this.column = new SimpleStringProperty(column);
            this.wrongValue = new SimpleStringProperty(wrongValue);
            this.errorText = new SimpleStringProperty(errorText);
        }

        public String getRowId() {
            return rowId.get();
        }

        public String getPepID() {
            return pepID.get();
        }

        public String getColumn() {
            return column.get();
        }

        public String getWrongValue() {
            return wrongValue.get();
        }

        public String getErrorText() {
            return errorText.get();
        }

    }

}
