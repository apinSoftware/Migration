/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import static com.google.common.base.Strings.isNullOrEmpty;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import library.assistant.util.LibraryAssistantUtil;

/**
 *
 * @author tnnakwe
 */
public class FXMLDocumentController implements Initializable {

    PreparedStatement preonline = null;
    ResultSet rsonline = null;
    PreparedStatement patientTable = null;
    PreparedStatement personTable = null;
    PreparedStatement personNameTable = null;
    PreparedStatement patientIdentifierTable = null;
    PreparedStatement checkPatientIdentifierTable = null;
    PreparedStatement getPatientId = null;
    PreparedStatement getConceptId = null;
    PreparedStatement loadLabVisit = null;
    PreparedStatement getpatientstartVisit = null;

    ResultSet rs = null;
    ResultSet rs2 = null, rslab, rspatientId;
    int last_person_id = 0;
    ResultSet personRs = null;
    Connection omrs = null, apinDB = null;
    @FXML
    private String AuditNumber;
    private String ComponentRow;
    private ResultSet rsgetconcept;
    private ResultSet rsgetpatientVisit;
    private PreparedStatement checkduplicateVisitdate;
    private ResultSet rsvisitdatecheck;
    private PreparedStatement loadVisit;
    private ResultSet rsloadVisit;
    private PreparedStatement patientVisitTable;

    @FXML
    private Button button;

    @FXML
    private Button migrateLABID;

    @FXML
    private Button migrateAdultICEBtn;

    @FXML
    private Button migrateClinicalEncounter, backBtn;

    @FXML
    private Button migratePharmacy;

    @FXML
    private Label patientBasicInfoLabel;

    @FXML
    private Label migrationTitletext;

    @FXML
    private Label adultClinicalEvalLabel;

    @FXML
    private Label laboratoryLabel;

    @FXML
    private Label clinicalEncounterLabel;

    @FXML
    private Label pharmacyLabel;

    @FXML
    private Label completedLabelBasicInfo;

    @FXML
    private Label completedLabelAdultICE;

    @FXML
    private Label completedLabelLAB;

    @FXML
    private Label completedLabelEncounter;

    @FXML
    private Label completedLabelPharmacy;

    @FXML
    private Button migratePediatricICEBtn;

    @FXML
    private Label pediatricClinicalEvalLabel;

    @FXML
    private Label completedLabelPediatricICE, patientsTotal, labTotal, pedTotal, adultTotal, pharmacyTotal, clinicalTotal;

    @FXML
    private ImageView loadingImageLabel;

    @FXML
    private Label moduleProcessing;
    private TextArea errorlog;
    private PreparedStatement insertlocationTable;
    private ResultSet locationRs;
    //private int last_location_id;
    private PreparedStatement getfacilityname;
    private ResultSet getfacilitynameRs;

    private PreparedStatement getfacilitynameomrs;
    private ResultSet getfacilitynameRsomrs;
    private PreparedStatement insertlocationTagTable;
    private PreparedStatement updateLocationTable;
    private PreparedStatement getpatientVisitId;
    private ResultSet rsgetpatientVisitId;
    private PreparedStatement patientencounterTable;
    private VisitObs visitObs;
    private ResultSet patientVisitTableRs;
    private String last_visit_id;
    private PreparedStatement Obs;
    private PreparedStatement obs;
    private ResultSet patientencounterTableRs;
    private String lastEncounterId;
    private PreparedStatement getopenMRSdatatype;
    private ResultSet rsgetopenMRSdatatype;
    private ResultSet rsencounterdatecheck;
    private String resultsfromCisPro = "0";
    private Concept concept;
    private ConceptCheckBoxes conceptCheckBoxes;
    private PreparedStatement newvisitobs;
    private PreparedStatement checkVisitInOBS;
    private ResultSet rscheckVisitInOBS;
    private PreparedStatement patientencounterProviderTable;
    private PreparedStatement obsLABSignature;
    private PreparedStatement checkpatientLabCheckboxesResult;
    private ResultSet rscheckpatientLabCheckboxesResult;
    private PreparedStatement checkpatientLabSignature;
    private ResultSet rscheckpatientLabSignature;
    private PreparedStatement checkPatientIdentifierTable2;
    private ResultSet rslab2;
    private String lastEncounterId2;
    private PreparedStatement checkPatientIdentifierTable3;
    private ResultSet rslab3;
    private String lastEncounterId3;
    private PreparedStatement getConceptId2;
    private ResultSet rsgetconcept2;
    private PreparedStatement getopenMRSdatatype2;
    private ResultSet rsgetopenMRSdatatype2;
    private PreparedStatement personTableSignature;
    private int last_person_id_signature;
    private ResultSet personRsSignature;
    private PreparedStatement personNameTableSignature;
    private Thread thread1;
    private Thread thread2;
    private PreparedStatement getPatientAdultICE;
    private ResultSet rsgetPatientAdultICE;
    private PreparedStatement obsPresentingComplain;
    private PreparedStatement obsSymptomsReview;
    private ResultSet rsobsSymptomsReview;
    private int last_obsSymptomsReview_id;
    private PreparedStatement obsAdditionalComments;
    private PreparedStatement obsAccessedTB;
    private PreparedStatement obsMedicalHistory;
    private PreparedStatement obsFamilyHistory;
    private PreparedStatement obsHospitalization;
    private PreparedStatement obsCurrentlyPregnant;
    private PreparedStatement obsPreviousARV;
    private PreparedStatement obsCurrentMedications;
    private PreparedStatement obsHIVstatusDisclosure;
    private PreparedStatement obsHivstatdiscussion;
    private PreparedStatement obsPastcurrarvsideeff;
    private PreparedStatement obsTemperature;
    private PreparedStatement obsBP;
    private PreparedStatement obsWeight;
    private PreparedStatement obsHeight;
    private PreparedStatement obsPegenappearance;
    private PreparedStatement obsGerespiratory;
    private PreparedStatement obsPeskin;
    private PreparedStatement obsGegastrointestinal;
    private PreparedStatement obsPeheadeye;
    private PreparedStatement obsPecardiovascular;
    private PreparedStatement obsGeneurological;
    private PreparedStatement obsPebreasts;
    private PreparedStatement obsPegenitalia;
    private PreparedStatement obsGementalstatus;
    private PreparedStatement obsPeadditional;
    private PreparedStatement obsAssessment;
    private PreparedStatement obsWhostagecriteria;
    private PreparedStatement obsPlan;
    private PreparedStatement obsPlanlab;
    private PreparedStatement obsEnrollin;
    private PreparedStatement obsPlanarvtherapy;
    private PreparedStatement obsNextappointmentdate;
    private Thread thread3;
    private PreparedStatement getPatientClinicalEval;
    private ResultSet rsgetPatientClinicalEval;
    private PreparedStatement obsWeightClinicalEval;
    private PreparedStatement obsBPClinicalEval;
    private PreparedStatement obsFamilyPlanning;
    private PreparedStatement obsFunctionalStatus;
    private PreparedStatement obsWHOStage;
    private PreparedStatement obsTBStatus;
    private PreparedStatement obsOtherOI;
    private PreparedStatement obsSideEffects;
    private PreparedStatement obsRegadherencea;
    private PreparedStatement obsCotrimadherencea;
    private PreparedStatement obsInhadherencea;
    private PreparedStatement obsNextapptdate;
    private Thread thread4;
    private PreparedStatement checkPatientLabTable;
    private int demographicscount;
    private int labcount;
    private int adulticecount;
    private int clinicalevalcount;
    private Thread thread5;
    private Thread thread6;
    private Thread thread7;
    public static int basicInfoMigrationStatus = 0;
    private Label titleText;
    private PreparedStatement dashboardData;
    private ResultSet rsdashboardData;
    private PreparedStatement enrollPatientInProgramTableStm;

    public FXMLDocumentController() {

    }

    @FXML
    void handlebackBTN(ActionEvent event) throws IOException {

        Parent blah = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.getIcons().add(new Image("/images/apinlogo.png"));
        appStage.setTitle("APIN Public Health Initiatives");
        appStage.centerOnScreen();
        appStage.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        thread1 = new Thread() {
            @Override
            public void run() {

                updateOpenMRSLocation();
                demographics();

            }
        };
        thread1.start();

    }

    public void finishedDemographics() {
        completedLabelBasicInfo.setVisible(true);
    }

    @FXML
    private void labButtonAction(ActionEvent event) {
        thread2 = new Thread() {

            @Override
            public void run() {

                migrateLabResult();
            }
        };
        thread2.start();

    }

    @FXML
    void adultICEBtnAction(ActionEvent event) {
        thread3 = new Thread() {

            @Override
            public void run() {

                migrateAdultICE();
            }
        };
        thread3.start();
    }

    @FXML
    void clinicalEncounterBtn(ActionEvent event) {
        thread4 = new Thread() {

            @Override
            public void run() {

                migrateClinicalEncounter();
            }
        };
        thread4.start();
    }

    @FXML
    void pediatricICEBtnAction(ActionEvent event) {

        thread6 = new Thread() {

            @Override
            public void run() {

                new MigrateICFORMResult().load(pediatricClinicalEvalLabel, completedLabelPediatricICE, migratePediatricICEBtn, loadingImageLabel, moduleProcessing);
            }
        };
        thread6.start();
    }

    @FXML
    void pharmacyBtn(ActionEvent event) {

        thread5 = new Thread() {

            @Override
            public void run() {
                new MigratePharmacyResult().load(pharmacyLabel, completedLabelPharmacy, migratePharmacy, loadingImageLabel, moduleProcessing);
            }
        };
        thread5.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadingImageLabel.setVisible(false);
        completedLabelBasicInfo.setVisible(false);
        completedLabelAdultICE.setVisible(false);
        completedLabelPediatricICE.setVisible(false);
        completedLabelLAB.setVisible(false);
        completedLabelEncounter.setVisible(false);
        completedLabelPharmacy.setVisible(false);

        getDashboardRecords();

        migrationTitletext.setText(Connectionstring.getCURRENT_MIRATION_TITLE());
    }

    public String getPatient(String pepid) {
        try {
            getPatientId = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                        "
                    + "SELECT \n"
                    + "   patient_id \n"
                    + "  FROM\n"
                    + "    `patient_identifier` \n"
                    + "  WHERE `identifier` = ? AND identifier_type='4'");
            getPatientId.setString(1, pepid);
            rspatientId = getPatientId.executeQuery();
            if (rspatientId.next()) {
                return rspatientId.getString("patient_id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Concept conceptIdsforLAB(String concept) {
        Concept conceptObs = new Concept();
        try {
            getConceptId = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id` \n"
                    + "FROM\n"
                    + "  `concept_lab`\n"
                    + "  WHERE `cisproconcepts` = ?");
            getConceptId.setString(1, concept);
            rsgetconcept = getConceptId.executeQuery();
            if (rsgetconcept.next()) {
                conceptObs.setConceptId(rsgetconcept.getString("concept_id"));
                getopenMRSdatatype = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                               SELECT \n"
                        + "  `datatype_id` \n"
                        + "FROM\n"
                        + "  `concept`\n"
                        + "  WHERE `concept_id` = ?");
                getopenMRSdatatype.setString(1, rsgetconcept.getString("concept_id"));
                rsgetopenMRSdatatype = getopenMRSdatatype.executeQuery();
                if (rsgetopenMRSdatatype.next()) {
                    conceptObs.setConceptDataType(rsgetopenMRSdatatype.getString("datatype_id"));
                }
                return conceptObs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conceptObs;
    }

    public ConceptCheckBoxes conceptIdsforLABCheckeBoxes(String concept) {
        ConceptCheckBoxes conceptObs = new ConceptCheckBoxes();
        try {
            getConceptId2 = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id` \n"
                    + "FROM\n"
                    + "  `concept_lab_checkboxes`\n"
                    + "  WHERE `cisproconcepts` = ?");
            getConceptId2.setString(1, concept);
            rsgetconcept2 = getConceptId2.executeQuery();
            if (rsgetconcept2.next()) {
                conceptObs.setConceptId(rsgetconcept2.getString("concept_id"));
                getopenMRSdatatype2 = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                               SELECT \n"
                        + "  `datatype_id` \n"
                        + "FROM\n"
                        + "  `concept`\n"
                        + "  WHERE `concept_id` = ?");
                getopenMRSdatatype2.setString(1, rsgetconcept2.getString("concept_id"));
                rsgetopenMRSdatatype2 = getopenMRSdatatype2.executeQuery();
                if (rsgetopenMRSdatatype2.next()) {
                    conceptObs.setConceptDataType(rsgetopenMRSdatatype2.getString("datatype_id"));
                }
                return conceptObs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conceptObs;
    }

    /////Checking for duplicate patient insert
    public boolean checkDuplicate(String patientid) {
        try {
            checkPatientIdentifierTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(""
                    + "SELECT * from patient_identifier where identifier = ?");
            checkPatientIdentifierTable.setString(1, patientid);
            rs2 = checkPatientIdentifierTable.executeQuery();
            if (rs2.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    ////check for duplicate visit date
    public VisitObs checkDuplicateVisitdate(String patientid, String datestart) {

        visitObs = new VisitObs();
        try {
            checkduplicateVisitdate = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(""
                    + "SELECT * from visit where patient_id = ? AND date_started = ?");
            String datestartf = datestart + " 00:00:00";
            checkduplicateVisitdate.setString(1, patientid);
            checkduplicateVisitdate.setString(2, datestartf);
            rsvisitdatecheck = checkduplicateVisitdate.executeQuery();
            if (rsvisitdatecheck.next()) {
                visitObs.setEcounterId(getVisitEncounter(rsvisitdatecheck.getString("visit_id")));
                visitObs.setVisit_id(rsvisitdatecheck.getString("visit_id"));
                visitObs.setIsValid(true);
            } else {
                visitObs.setVisit_id("0");
                visitObs.setIsValid(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // visitObs.setVisit_id("0");
        //visitObs.setIsValid(false);
        return visitObs;
    }

    //get an already existing encounter ID
    public String getVisitEncounter(String visitID) {

        try {
            checkduplicateVisitdate = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(""
                    + "SELECT encounter_id,encounter_type from encounter where visit_id = ?");
            checkduplicateVisitdate.setString(1, visitID);
            rsencounterdatecheck = checkduplicateVisitdate.executeQuery();
            if (rsencounterdatecheck.next()) {
                return rsencounterdatecheck.getString("encounter_id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "0";
    }

    ///Update OpenMRS location using cispro table
    public void updateOpenMRSLocation() {
        try {
            DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);

            ///update location table
            updateLocationTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                    "UPDATE \n"
                    + "  `location` \n"
                    + "SET\n"
                    + "  `name` = ? , \n"
                    + "  `city_village` = ? , \n"
                    + "  `state_province` = ?  \n"
                    + "WHERE `location_id` ='8' AND \n"
                    + "`name` = 'Amani Hospital' ;\n"
                    + ""
            );

            getfacilityname = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT * FROM facility");
            getfacilitynameRs = getfacilityname.executeQuery();

            if (getfacilitynameRs.next()) {
                updateLocationTable.setString(1, getfacilitynameRs.getString("facilityname"));
                updateLocationTable.setString(2, getfacilitynameRs.getString("lga"));
                updateLocationTable.setString(3, getfacilitynameRs.getString("state"));
                updateLocationTable.executeUpdate();
            }

            DatabaseHandler.getInstance().getMRSDBConnection().commit();
            DatabaseHandler.getInstance().getApinConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String loadpatientVisit(String patientid, String visitdate, String encounterType, String formid) {
        visitObs = new VisitObs();
        try {
            DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);

            visitObs = checkDuplicateVisitdate(patientid, visitdate);

            // inserting patient visit date 
            patientVisitTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                    "INSERT INTO `visit` ("
                    + "`patient_id`,\n"
                    + "  `visit_type_id`,\n"
                    + "  `date_started`,\n"
                    + "  `location_id`,\n"
                    + "  `creator`,\n"
                    + "  `date_created`,\n"
                    + "`uuid`)"
                    + "VALUES(?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + " UUID())", Statement.RETURN_GENERATED_KEYS
            );
            if (visitObs.isValid == false) {
                //inserting into patient Visit table
                patientVisitTable.setString(1, patientid);
                patientVisitTable.setString(2, "1");
                patientVisitTable.setString(3, visitdate);
                patientVisitTable.setString(4, "8");
                patientVisitTable.setString(5, "1");
                patientVisitTable.setString(6, visitdate);
                patientVisitTable.executeUpdate();
                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                DatabaseHandler.getInstance().getApinConnection().commit();
                patientVisitTableRs = patientVisitTable.getGeneratedKeys();
                if (patientVisitTableRs.next()) {
                    last_visit_id = String.valueOf(patientVisitTableRs.getInt(1));
                }

                return loadpatientEnCounter(
                        patientid,
                        last_visit_id,
                        encounterType,
                        visitdate,
                        formid
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return visitObs.getEcounterId();
    }

    public String loadpatientEnCounter(String patientid, String visitId, String encounterType, String date, String formid) {

        try {
            DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            // inserting patient encounter
            patientencounterTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                    "INSERT INTO `encounter` ("
                    + "  `patient_id`,\n"
                    + "  `encounter_type`,\n"
                    + "  `location_id`,\n"
                    + "  `form_id`,\n"
                    + "  `creator`,\n"
                    + "  `visit_id`,\n"
                    + "  `encounter_datetime`,\n"
                    + "  `date_created`,\n"
                    + "  `uuid`\n"
                    + ") \n"
                    + "VALUES(?,\n"
                    + "    ?,\n"
                    + "    '8',\n"
                    + "    ?,\n"
                    + "    '1',\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "UUID() )", Statement.RETURN_GENERATED_KEYS
            );
            patientencounterTable.setString(1, patientid);
            patientencounterTable.setString(2, encounterType);
            patientencounterTable.setString(3, formid);
            patientencounterTable.setString(4, visitId);
            patientencounterTable.setString(5, date);
            patientencounterTable.setString(6, date);
            patientencounterTable.executeUpdate();
            DatabaseHandler.getInstance().getMRSDBConnection().commit();
            DatabaseHandler.getInstance().getApinConnection().commit();

            patientencounterTableRs = patientencounterTable.getGeneratedKeys();
            if (patientencounterTableRs.next()) {
                insertNewandReturnVisitsOBS(patientid, String.valueOf(patientencounterTableRs.getInt(1)), date);
                insertEcounterProvider(String.valueOf(patientencounterTableRs.getInt(1)), date);
                return String.valueOf(patientencounterTableRs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void insertEcounterProvider(String encounterid, String date) {
        try {
            DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            // inserting patient encounter Provider
            patientencounterProviderTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                    "INSERT INTO `encounter_provider` ("
                    + "  `encounter_id`,\n"
                    + "  `provider_id`,\n"
                    + "  `encounter_role_id`,\n"
                    + "  `creator`,\n"
                    + "  `date_created`,\n"
                    + "  `uuid`\n"
                    + ") \n"
                    + "VALUES(?,\n"
                    + "    '1',\n"
                    + "    '1',\n"
                    + "    '1',\n"
                    + "    ?,\n"
                    + "UUID() )"
            );
            patientencounterProviderTable.setString(1, encounterid);
            patientencounterProviderTable.setString(2, date);
            patientencounterProviderTable.executeUpdate();
            DatabaseHandler.getInstance().getMRSDBConnection().commit();
            DatabaseHandler.getInstance().getApinConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPatientVisitId(String patientid, String visitdate) {
        try {
            getpatientVisitId = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("SELECT \n"
                    + "  `visit_id`, \n"
                    + "FROM\n"
                    + "  `visit`\n"
                    + "  WHERE `patient_id` = ? AND `date_started` = ?");
            String visitdatef = visitdate + " 00:00:00";
            getpatientVisitId.setString(1, patientid);
            getpatientVisitId.setString(2, visitdatef);
            rsgetpatientVisitId = getpatientVisitId.executeQuery();
            if (rsgetpatientVisitId.next()) {
                return rsgetpatientVisitId.getString("visit_id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void migrateLabResult() {

        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {

            if (basicInfoMigrationStatus == 0) {
                JOptionPane.showMessageDialog(null, "Please Migrate Patient Basic Info first!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadingImageLabel.setVisible(true);
                migrateLABID.setVisible(false);
                //moduleProcessing.setText("Uploading patient laboratory");
                try {
                    checkPatientLabTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT  labid AS myid, \n"
                            + "                           pepid,\n"
                            + "                           tests,\n"
                            + "                           IF(\n"
                            + "                         \n"
                            + "			   tests NOT IN (\"Lymhocytes\",\"Urinalysis: GLUCOSE\",\"HBsAG\",\"HBsAG (*At Baseline)\",\"HBsAG\",\"VDRL\",\"Pregnancy\",\"HCV Antibody\",\"Malaria smear\",\"Urinalysis: PROTEIN\") \n"
                            + "			   AND results NOT IN(\"<20\", \"FAILLED\",\"FAILED\") ,\n"
                            + "                           CAST(TRIM(results) AS UNSIGNED),\n"
                            + "                           \n"
                            + "			   CASE  \n"
                            + "			   WHEN results LIKE \"%NEG%\" THEN 664 \n"
                            + "			   WHEN results LIKE \"%Negative%\" THEN 664\n"
                            + "			   WHEN results LIKE \"%Negative%\" THEN 664\n"
                            + "			   WHEN results LIKE \"%NRG%\" THEN 664\n"
                            + "			   WHEN results LIKE \"%Positive%\" THEN 703\n"
                            + "			   WHEN results LIKE \"%POS%\" THEN 703\n"
                            + "			   WHEN results LIKE \"%FAILLED%\" THEN NULL\n"
                            + "			   WHEN results LIKE \"%<20%\" THEN 50\n"
                            + "			   WHEN results LIKE \"%TRA%\" THEN NULL\n"
                            + "			   ELSE results\n"
                            + "			   END \n"
                            + "                           \n"
                            + "                           ) AS results,\n"
                            + "                           reportedby,\n"
                            + "                           reportdate,\n"
                            + "                           checkedby,\n"
                            + "                           checkdate,\n"
                            + "                           clinicianname,\n"
                            + "                           visitdate\n"
                            + "                            \n"
                            + "                            FROM\n"
                            + "                           laboratory\n"
                            + "                            WHERE migrated = 0 AND `visitdate` !='0000-00-00' AND pepid IN (SELECT pepid FROM patient)  \n"
                            + "                            \n"
                            + "                            GROUP BY labid, pepid, visitdate \n"
                            + "                            ");
                    rslab = checkPatientLabTable.executeQuery();
                    while (rslab.next()) {

                        ///get patient_id from patient_identifier table
                        String patient_id = this.getPatient(rslab.getString("pepid"));
                        ///get the concept_id from concepts using the tests in cispro table
                        lastEncounterId = loadpatientVisit(patient_id, rslab.getString("visitdate"), "11", "19");
                        //String tests = rslab.getString("tests");
                        String tests = "";

                        if (rslab.getString("tests").equals("BUN") || rslab.getString("tests").equals("HCO3")) {
                            //System.out.println("Skipped!" + rslab.getString("tests"));
                        } else {

                            if (rslab.getString("tests").equals("Lymhocytes")) {
                                tests = "Lymphocytes";
                            }
                            if (rslab.getString("tests").equals("Urinalysis: GLUCOSE")) {
                                tests = "GLUCOSE";
                            }
                            if (rslab.getString("tests").equals("HBsAG")) {
                                tests = "HBsAG (*At Baseline)";
                            }
                          
                            if (!rslab.getString("tests").equals("Lymhocytes") && !rslab.getString("tests").equals("Urinalysis: GLUCOSE") && !rslab.getString("tests").equals("HBsAG")) {
                                tests = rslab.getString("tests");
                                concept = this.conceptIdsforLAB(tests);
                            }

                            String encounterDataType = concept.getConceptDataType();
                            resultsfromCisPro = rslab.getString("results");

                            if (!isNullOrEmpty(encounterDataType)) {                               

                                if (!isNullOrEmpty(resultsfromCisPro)) {

                                    int conceptId = Integer.parseInt(this.conceptIdsforLAB(tests).getConceptId());

                                    Obs obs = new Obs();
                                    obs.setObsDate(rslab.getDate(rslab.findColumn("visitdate")));
                                    ObsUtil.InsertObsLab(Integer.parseInt(patient_id), conceptId, Integer.parseInt(lastEncounterId), obs.getObsDate(), 8, resultsfromCisPro);
                                }
                            }

                            this.migrateLabCheckboxesResult(patient_id, lastEncounterId, rslab.getString("visitdate"), this.conceptIdsforLABCheckeBoxes(tests).getConceptId(), tests);
                            this.migrateLabSignature(patient_id, lastEncounterId, rslab.getString("visitdate"), rslab.getString("reportedby"), rslab.getString("reportdate"), rslab.getString("checkedby"), rslab.getString("checkdate"), rslab.getString("clinicianname"));
                        }//// REMOVE THIS CLOSING IF STATEMENT LATER

                        labcount++;
                        
                        String labId = rslab.getString("myid");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                laboratoryLabel.setText(String.valueOf(labcount));
                                new LoadSettings().updateMigrationField("laboratory", "migrated", "labid", labId);

                            }
                        });
                        
                    }

                    loadingImageLabel.setVisible(false);
                    completedLabelLAB.setVisible(true);

                } catch (Exception ex) {                 
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                   // LogToFile.writeLog(ex.getMessage());
                }
            }
        }
    }

    public void migrateLabCheckboxesResult(String patientid, String encounterid, String visitdate, String conceptid, String tests) {
        try {

            ////check if patient Checkbox lab exists
            checkpatientLabCheckboxesResult = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = ?");
            String visitdatef = visitdate + " 00:00:00";
            checkpatientLabCheckboxesResult.setString(1, patientid);
            checkpatientLabCheckboxesResult.setString(2, encounterid);
            checkpatientLabCheckboxesResult.setString(3, visitdatef);
            checkpatientLabCheckboxesResult.setString(4, conceptid);
            rscheckpatientLabCheckboxesResult = checkpatientLabCheckboxesResult.executeQuery();
            if (rscheckpatientLabCheckboxesResult.next()) {
                // System.out.println("Patient Lab Check boxes already exists"+conceptid);
            } else {

                if (tests.equals("BUN") || tests.equals("HCO3")) {

                    /// System.out.println("Skipped!" + tests);
                } else {

                    if (tests.equals("Lymhocytes")) {
                        tests = "Lymphocytes";
                    }
                    if (tests.equals("Urinalysis: GLUCOSE")) {
                        tests = "GLUCOSE";
                    }
                    if (tests.equals("HBsAG")) {
                        tests = "HBsAG (*At Baseline)";
                    }
                    if (!tests.equals("Lymhocytes") && !tests.equals("Urinalysis: GLUCOSE") && !tests.equals("HBsAG")) {
                        //tests = rslab2.getString("tests");
                        conceptCheckBoxes = this.conceptIdsforLABCheckeBoxes(tests);
                    }

                    String encounterDataType = conceptCheckBoxes.getConceptDataType();

                    if (encounterDataType == null) {
                        System.out.println(tests);
                    } else {

                        insertObs(
                                patientid,
                                this.conceptIdsforLABCheckeBoxes(tests).getConceptId(),
                                encounterid,
                                "2", //valuecoded type
                                null,
                                null,
                                null,
                                visitdate
                        );
                    }

                }//// REMOVE THIS CLOSING IF STATEMENT LATER

            }
        } catch (Exception ex) {

            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void migrateAdultICESignature(String patientid, String encounterid, String visitdate, String reportedby, String reportdate, String checkedby, String checkdate, String clinicianname) {
        try {

            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '164987'");
            String visitdatef = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                //System.out.println("Patient Lab Signatures already exists");
            } else {

                String personid = this.insertPersonTable(clinicianname);
                ////insert clinician name//////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "164987");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, null);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, personid + " - " + clinicianname);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);
                obsLABSignature.executeUpdate();

            }

        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void migrateLabSignature(String patientid, String encounterid, String visitdate, String reportedby, String reportdate, String checkedby, String checkdate, String clinicianname) {
        try {

            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '164987'");
            String visitdatef = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                //System.out.println("Patient Lab Signatures already exists");
            } else {

                String personid = this.insertPersonTable(clinicianname);
                ////insert clinician name//////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "164987");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, null);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, personid + " - " + clinicianname);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);
                obsLABSignature.executeUpdate();

            }

            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '164989'");
            String visitdatef2 = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef2);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                //System.out.println("Patient Lab Signatures already exists");
            } else {

                ////insert clinician date ordered//////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "164989");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, reportdate);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, null);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);
                obsLABSignature.executeUpdate();
            }

            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '164982'");
            String visitdatef3 = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef3);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                //    System.out.println("Patient Lab Signatures already exists");
            } else {
                String personid = this.insertPersonTable(reportedby);
                ////insert reported by//////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "164982");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, null);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, personid + " - " + reportedby);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);
                obsLABSignature.executeUpdate();
            }

            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '165414'");
            String visitdatef4 = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef4);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                // System.out.println("Patient Lab Signatures already exists");
            } else {
                ////insert reported by date //////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "165414");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, reportdate);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, null);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);
                obsLABSignature.executeUpdate();
            }

            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '164983'");
            String visitdatef5 = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef5);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                //  System.out.println("Patient Lab Signatures already exists");
            } else {
                String personid = this.insertPersonTable(checkedby);
                ////insert checked by//////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "164983");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, null);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, personid + " - " + checkedby);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);
                obsLABSignature.executeUpdate();
            }
            ////check if patient Checkbox lab exists
            checkpatientLabSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  * \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? AND `encounter_id` = ? AND `obs_datetime` = ? AND `concept_id` = '164984'");
            String visitdatef6 = visitdate + " 00:00:00";
            checkpatientLabSignature.setString(1, patientid);
            checkpatientLabSignature.setString(2, encounterid);
            checkpatientLabSignature.setString(3, visitdatef6);
            //checkpatientLabSignature.setString(4, conceptid);
            rscheckpatientLabSignature = checkpatientLabSignature.executeQuery();
            if (rscheckpatientLabSignature.next()) {
                // System.out.println("Patient Lab Signatures already exists");
            } else {
                ////insert checked by date //////
                obsLABSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_datetime`,\n"
                        + "  `value_numeric`,\n"
                        + "  `value_text`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                obsLABSignature.setString(1, patientid);
                obsLABSignature.setString(2, "164984");
                obsLABSignature.setString(3, encounterid);
                obsLABSignature.setString(4, null);
                obsLABSignature.setString(5, reportdate);
                obsLABSignature.setString(6, null);
                obsLABSignature.setString(7, null);
                obsLABSignature.setString(8, visitdate);
                obsLABSignature.setString(9, visitdate);

                obsLABSignature.executeUpdate();
            }

        } catch (Exception ex) {

            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSymptomsDurationUnit(String durationunit) {
        String durationvalue;
        durationunit = durationunit.replaceAll("[^0-9/]", "");
        switch (durationunit) {
            case "1/7":
                durationvalue = "1";
                break;
            case "2/7":
                durationvalue = "2";
                break;
            case "3/7":
                durationvalue = "3";
                break;
            case "4/7":
                durationvalue = "4";
                break;
            case "5/7":
                durationvalue = "5";
                break;
            case "6/7":
                durationvalue = "6";
                break;
            case "7/7":
                durationvalue = "7";
                break;
            case "1/12":
                durationvalue = "30";
                break;
            case "2/12":
                durationvalue = "60";
                break;
            case "3/12":
                durationvalue = "90";
                break;
            case "4/12":
                durationvalue = "120";
                break;
            case "5/12":
                durationvalue = "150";
                break;
            case "6/12":
                durationvalue = "180";
                break;
            case "7/12":
                durationvalue = "210";
                break;
            case "8/12":
                durationvalue = "240";
                break;
            case "9/12":
                durationvalue = "270";
                break;
            case "10/12":
                durationvalue = "300";
                break;
            case "11/12":
                durationvalue = "330";
                break;
            case "12/12":
                durationvalue = "360";
                break;
            case "1/52":
                durationvalue = "7";
                break;
            case "2/52":
                durationvalue = "14";
                break;
            case "3/52":
                durationvalue = "21";
                break;
            case "4/52":
                durationvalue = "28";
                break;
            case "5/52":
                durationvalue = "35";
                break;
            case "6/52":
                durationvalue = "42";
                break;
            case "7/52":
                durationvalue = "49";
                break;
            case "8/52":
                durationvalue = "56";
                break;
            case "9/52":
                durationvalue = "63";
                break;
            case "10/52":
                durationvalue = "70";
                break;
            case "11/52":
                durationvalue = "77";
                break;
            case "12/52":
                durationvalue = "84";
                break;
            default:
                durationvalue = "0";
                break;
        }

        return durationvalue;
    }

    public void migrateAdultICE() {
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");

        if (resultn == JOptionPane.YES_OPTION) {
            if (basicInfoMigrationStatus == 0) {
                JOptionPane.showMessageDialog(null, "Please Migrate Patient Basic Info first!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadingImageLabel.setVisible(true);
                migrateAdultICEBtn.setVisible(false);
                //moduleProcessing.setText("Uploading patient adult initial clinical evaluation");
                try {
                    getPatientAdultICE = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT idpatient as  myid , p.* FROM `patient` as p WHERE "
                            + "LENGTH(pepid) = 10 and icfmigrated = 0");
                    rsgetPatientAdultICE = getPatientAdultICE.executeQuery();
                    DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                    DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                    while (rsgetPatientAdultICE.next()) {

                        ///get patient_id from patient_identifier table
                        String patient_id = this.getPatient(rsgetPatientAdultICE.getString("pepid"));

                        ///get the concept_id from concepts using the tests in cispro table
                        lastEncounterId = loadpatientVisit(patient_id, rsgetPatientAdultICE.getString("enroldate"), "8", "22");

                        if (rsgetPatientAdultICE.getString("presentingcomplaint") != null && !rsgetPatientAdultICE.getString("presentingcomplaint").equalsIgnoreCase("")) {
                            ////////////////////////////Presenting complain and Symptoms review /////////////////////////

                            obsPresentingComplain = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID()\n"
                                    + "  )\n"
                                    + "");
                            obsPresentingComplain.setString(1, patient_id);
                            obsPresentingComplain.setString(2, "165680");
                            obsPresentingComplain.setString(3, lastEncounterId);
                            obsPresentingComplain.setString(4, null);
                            obsPresentingComplain.setString(5, null);
                            obsPresentingComplain.setString(6, null);
                            obsPresentingComplain.setString(7, rsgetPatientAdultICE.getString("presentingcomplaint"));
                            obsPresentingComplain.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsPresentingComplain.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsPresentingComplain.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();
                        }

                        if (rsgetPatientAdultICE.getString("symptomsreview") != null && !rsgetPatientAdultICE.getString("symptomsreview").equals("")) {
                            // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);

                            String symptomsreview = rsgetPatientAdultICE.getString("symptomsreview");
                            symptomsreview = symptomsreview.replaceAll(", ", ",");
                            symptomsreview = symptomsreview.replaceAll(", ,", ",");
                            String symptomsreviewarray[] = symptomsreview.split(",");
                            boolean checkdata = true;
                            for (String symptomsreviewarrays : symptomsreviewarray) {

                                obsSymptomsReview = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())", Statement.RETURN_GENERATED_KEYS);
                                obsSymptomsReview.setString(1, patient_id);
                                obsSymptomsReview.setString(2, "1727");
                                obsSymptomsReview.setString(3, lastEncounterId);
                                obsSymptomsReview.setString(4, null);
                                obsSymptomsReview.setString(5, null);
                                obsSymptomsReview.setString(6, null);
                                obsSymptomsReview.setString(7, null);
                                obsSymptomsReview.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsSymptomsReview.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsSymptomsReview.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                                rsobsSymptomsReview = obsSymptomsReview.getGeneratedKeys();
                                if (rsobsSymptomsReview.next()) {
                                    last_obsSymptomsReview_id = rsobsSymptomsReview.getInt(1);
                                }
                                obsSymptomsReview = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `obs_group_id`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsSymptomsReview.setString(1, patient_id);
                                obsSymptomsReview.setString(2, "1731");
                                obsSymptomsReview.setString(3, lastEncounterId);
                                obsSymptomsReview.setString(4, null);
                                obsSymptomsReview.setString(5, null);

                                if (symptomsreviewarrays.equalsIgnoreCase("Fever/Chills") && !rsgetPatientAdultICE.getString("dur_fever").equals("")) {

                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_fever")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Rash") && !rsgetPatientAdultICE.getString("dur_rash").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_rash")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Shortness of Breath") && !rsgetPatientAdultICE.getString("dur_shortbreath").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_shortbreath")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Nausea/Vomitting") && !rsgetPatientAdultICE.getString("dur_nausea").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_nausea")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Itching") && !rsgetPatientAdultICE.getString("dur_itch").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_itch")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("New Visual Imparity") && !rsgetPatientAdultICE.getString("dur_visimp").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_visimp")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Night Sweats") && !rsgetPatientAdultICE.getString("dur_nsweats").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_nsweats")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Chronic Diarrhea") && !rsgetPatientAdultICE.getString("dur_diarh").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_diarh")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Pain/Difficulty when swallowing") && !rsgetPatientAdultICE.getString("dur_painswall").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_painswall")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Recent Weight Loss") && !rsgetPatientAdultICE.getString("dur_weigthl").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_weigthl")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Genital Discharge") && !rsgetPatientAdultICE.getString("dur_gendis").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_gendis")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Numbness/Tingling") && !rsgetPatientAdultICE.getString("dur_numb").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_numb")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Cough") && !rsgetPatientAdultICE.getString("dur_cough").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_cough")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Genital Itching") && !rsgetPatientAdultICE.getString("dur_genitch").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_genitch")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Pain (other site)") && !rsgetPatientAdultICE.getString("dur_otherpain").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_otherpain")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Headache") && !rsgetPatientAdultICE.getString("dur_otherpain").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_otherpain")));
                                } else if (symptomsreviewarrays.equalsIgnoreCase("Genital Sores") && !rsgetPatientAdultICE.getString("dur_gensore").equals("")) {
                                    obsSymptomsReview.setString(6, getSymptomsDurationUnit(rsgetPatientAdultICE.getString("dur_gensore")));
                                } else {
                                    //obsSymptomsReview.setString(6, "0");
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsSymptomsReview.setString(7, null);
                                    obsSymptomsReview.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsSymptomsReview.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsSymptomsReview.setString(10, String.valueOf(last_obsSymptomsReview_id));
                                    obsSymptomsReview.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }

                                obsSymptomsReview = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `obs_group_id`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsSymptomsReview.setString(1, patient_id);
                                obsSymptomsReview.setString(2, "1732");
                                obsSymptomsReview.setString(3, lastEncounterId);
                                obsSymptomsReview.setString(4, "1072");
                                obsSymptomsReview.setString(5, null);
                                obsSymptomsReview.setString(6, null);
                                obsSymptomsReview.setString(7, null);
                                obsSymptomsReview.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsSymptomsReview.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsSymptomsReview.setString(10, String.valueOf(last_obsSymptomsReview_id));
                                obsSymptomsReview.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();

                                obsSymptomsReview = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `obs_group_id`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsSymptomsReview.setString(1, patient_id);
                                obsSymptomsReview.setString(2, "1728");
                                obsSymptomsReview.setString(3, lastEncounterId);
                                obsSymptomsReview.setString(4, this.conceptIdsforAdultICE(symptomsreviewarrays).getConceptId());
                                obsSymptomsReview.setString(5, null);
                                obsSymptomsReview.setString(6, null);
                                obsSymptomsReview.setString(7, null);
                                obsSymptomsReview.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsSymptomsReview.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsSymptomsReview.setString(10, String.valueOf(last_obsSymptomsReview_id));
                                obsSymptomsReview.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                            /////////////////////////////////////////////////////////////////////////////////////////////
                        }
                        if (rsgetPatientAdultICE.getString("patacc_tb") != null && !rsgetPatientAdultICE.getString("patacc_tb").equals("")) {
                            if (rsgetPatientAdultICE.getString("patacc_tb").equalsIgnoreCase("No")) {
                                ////patient accessed for TB //////////////
                                //   DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsAccessedTB = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsAccessedTB.setString(1, patient_id);
                                obsAccessedTB.setString(2, "164800");
                                obsAccessedTB.setString(3, lastEncounterId);
                                obsAccessedTB.setString(4, "1066");
                                obsAccessedTB.setString(5, null);
                                obsAccessedTB.setString(6, null);
                                obsAccessedTB.setString(7, null);
                                obsAccessedTB.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsAccessedTB.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsAccessedTB.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                            if (rsgetPatientAdultICE.getString("patacc_tb").equalsIgnoreCase("Yes")) {
                                ////patient accessed for TB //////////////
                                //  DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsAccessedTB = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsAccessedTB.setString(1, patient_id);
                                obsAccessedTB.setString(2, "164800");
                                obsAccessedTB.setString(3, lastEncounterId);
                                obsAccessedTB.setString(4, "1065");
                                obsAccessedTB.setString(5, null);
                                obsAccessedTB.setString(6, null);
                                obsAccessedTB.setString(7, null);
                                obsAccessedTB.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsAccessedTB.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsAccessedTB.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();

//                         obsAccessedTB = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
//                                + "  `person_id`,\n"
//                                + "  `concept_id`,\n"
//                                + "  `encounter_id`,\n"
//                                + "  `location_id`,\n"
//                                + "  `value_coded`,\n"
//                                + "  `value_datetime`,\n"
//                                + "  `value_numeric`,\n"
//                                + "  `value_text`,\n"
//                                + "  `creator`,\n"
//                                + "  `obs_datetime`,\n"
//                                + "  `date_created`,\n"
//                                + "  `uuid`\n"
//                                + ") \n"
//                                + "VALUES\n"
//                                + "  (\n"
//                                + "    ?,\n"
//                                + "    ?,\n"
//                                + "    ?,\n"
//                                + "    '8',\n"
//                                + "    ?,\n"
//                                + "    ?,\n"
//                                + "    ?,\n"
//                                + "    ?,\n"
//                                + "    '1',\n"
//                                + "    ?,\n"
//                                + "    ?,\n"
//                                + "    UUID())");
//                        obsAccessedTB.setString(1, patient_id);
//                        obsAccessedTB.setString(2, "1659");
//                        obsAccessedTB.setString(3, lastEncounterId);
//                        obsAccessedTB.setString(4, "1065");
//                        obsAccessedTB.setString(5, null);
//                        obsAccessedTB.setString(6, null);
//                        obsAccessedTB.setString(7, null);
//                        obsAccessedTB.setString(8, rsgetPatientAdultICE.getString("enroldate"));
//                        obsAccessedTB.setString(9, rsgetPatientAdultICE.getString("enroldate"));
//                        obsAccessedTB.executeUpdate();
                            }

                        }
                        if (rsgetPatientAdultICE.getString("addiotnalsymcmments") != null && !rsgetPatientAdultICE.getString("addiotnalsymcmments").equals("")) {
                            ////addtional comments ///////////
                            // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            obsAdditionalComments = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsAdditionalComments.setString(1, patient_id);
                            obsAdditionalComments.setString(2, "165045");
                            obsAdditionalComments.setString(3, lastEncounterId);
                            obsAdditionalComments.setString(4, null);
                            obsAdditionalComments.setString(5, null);
                            obsAdditionalComments.setString(6, null);
                            obsAdditionalComments.setString(7, rsgetPatientAdultICE.getString("addiotnalsymcmments"));
                            obsAdditionalComments.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsAdditionalComments.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsAdditionalComments.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();
                        }

                        if (rsgetPatientAdultICE.getString("pastmedicalhist") != null && !rsgetPatientAdultICE.getString("pastmedicalhist").equals("")) {
                            /////////Past medical history/////////////////
                            //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            obsMedicalHistory = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsMedicalHistory.setString(1, patient_id);
                            obsMedicalHistory.setString(2, "160221");
                            obsMedicalHistory.setString(3, lastEncounterId);
                            obsMedicalHistory.setString(4, null);
                            obsMedicalHistory.setString(5, null);
                            obsMedicalHistory.setString(6, null);
                            obsMedicalHistory.setString(7, rsgetPatientAdultICE.getString("pastmedicalhist"));
                            obsMedicalHistory.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsMedicalHistory.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsMedicalHistory.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();
                        }

                        if (rsgetPatientAdultICE.getString("relfamilyhist") != null && !rsgetPatientAdultICE.getString("relfamilyhist").equals("")) {
                            /////////Past family history/////////////////
                            // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            obsFamilyHistory = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsFamilyHistory.setString(1, patient_id);
                            obsFamilyHistory.setString(2, "165426");
                            obsFamilyHistory.setString(3, lastEncounterId);
                            obsFamilyHistory.setString(4, null);
                            obsFamilyHistory.setString(5, null);
                            obsFamilyHistory.setString(6, null);
                            obsFamilyHistory.setString(7, rsgetPatientAdultICE.getString("relfamilyhist"));
                            obsFamilyHistory.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsFamilyHistory.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsFamilyHistory.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("hospitalization") != null && !rsgetPatientAdultICE.getString("hospitalization").equals("")) {
                            /////////Hospitalization/////////////////
                            //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            obsHospitalization = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsHospitalization.setString(1, patient_id);
                            obsHospitalization.setString(2, "165601");
                            obsHospitalization.setString(3, lastEncounterId);
                            obsHospitalization.setString(4, null);
                            obsHospitalization.setString(5, null);
                            obsHospitalization.setString(6, null);
                            obsHospitalization.setString(7, rsgetPatientAdultICE.getString("hospitalization"));
                            obsHospitalization.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsHospitalization.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsHospitalization.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("currentlypregnant") != null && !rsgetPatientAdultICE.getString("currentlypregnant").equals("")) {
                            /////////Currently Pregnant/////////////////
                            //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            if (rsgetPatientAdultICE.getString("currentlypregnant").equalsIgnoreCase("No")) {

                                obsCurrentlyPregnant = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsCurrentlyPregnant.setString(1, patient_id);
                                obsCurrentlyPregnant.setString(2, "1434");
                                obsCurrentlyPregnant.setString(3, lastEncounterId);
                                obsCurrentlyPregnant.setString(4, "2");
                                obsCurrentlyPregnant.setString(5, null);
                                obsCurrentlyPregnant.setString(6, null);
                                obsCurrentlyPregnant.setString(7, null);
                                obsCurrentlyPregnant.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsCurrentlyPregnant.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsCurrentlyPregnant.addBatch();

                                obsCurrentlyPregnant = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsCurrentlyPregnant.setString(1, patient_id);
                                obsCurrentlyPregnant.setString(2, "5632");
                                obsCurrentlyPregnant.setString(3, lastEncounterId);
                                obsCurrentlyPregnant.setString(4, this.conceptIdsforAdultICE(rsgetPatientAdultICE.getString("currentlybreastf")).getConceptId());
                                obsCurrentlyPregnant.setString(5, null);
                                obsCurrentlyPregnant.setString(6, null);
                                obsCurrentlyPregnant.setString(7, null);
                                obsCurrentlyPregnant.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsCurrentlyPregnant.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsCurrentlyPregnant.addBatch();
                                obsCurrentlyPregnant.executeBatch();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                            if (rsgetPatientAdultICE.getString("currentlypregnant").equalsIgnoreCase("Yes")) {
                                //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsCurrentlyPregnant = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsCurrentlyPregnant.setString(1, patient_id);
                                obsCurrentlyPregnant.setString(2, "1434");
                                obsCurrentlyPregnant.setString(3, lastEncounterId);
                                obsCurrentlyPregnant.setString(4, "1");
                                obsCurrentlyPregnant.setString(5, null);
                                obsCurrentlyPregnant.setString(6, null);
                                obsCurrentlyPregnant.setString(7, null);
                                obsCurrentlyPregnant.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsCurrentlyPregnant.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsCurrentlyPregnant.addBatch();
                                if (rsgetPatientAdultICE.getString("lastmenstrualperiod") != null) {
                                    obsCurrentlyPregnant = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                            + "  `person_id`,\n"
                                            + "  `concept_id`,\n"
                                            + "  `encounter_id`,\n"
                                            + "  `location_id`,\n"
                                            + "  `value_coded`,\n"
                                            + "  `value_datetime`,\n"
                                            + "  `value_numeric`,\n"
                                            + "  `value_text`,\n"
                                            + "  `creator`,\n"
                                            + "  `obs_datetime`,\n"
                                            + "  `date_created`,\n"
                                            + "  `uuid`\n"
                                            + ") \n"
                                            + "VALUES\n"
                                            + "  (\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    '8',\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    '1',\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    UUID())");
                                    obsCurrentlyPregnant.setString(1, patient_id);
                                    obsCurrentlyPregnant.setString(2, "1427");
                                    obsCurrentlyPregnant.setString(3, lastEncounterId);
                                    obsCurrentlyPregnant.setString(4, null);
                                    obsCurrentlyPregnant.setString(5, rsgetPatientAdultICE.getString("lastmenstrualperiod"));
                                    obsCurrentlyPregnant.setString(6, null);
                                    obsCurrentlyPregnant.setString(7, null);
                                    obsCurrentlyPregnant.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentlyPregnant.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentlyPregnant.addBatch();
                                }
                                if (rsgetPatientAdultICE.getString("gestationalage") != null) {
                                    obsCurrentlyPregnant = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                            + "  `person_id`,\n"
                                            + "  `concept_id`,\n"
                                            + "  `encounter_id`,\n"
                                            + "  `location_id`,\n"
                                            + "  `value_coded`,\n"
                                            + "  `value_datetime`,\n"
                                            + "  `value_numeric`,\n"
                                            + "  `value_text`,\n"
                                            + "  `creator`,\n"
                                            + "  `obs_datetime`,\n"
                                            + "  `date_created`,\n"
                                            + "  `uuid`\n"
                                            + ") \n"
                                            + "VALUES\n"
                                            + "  (\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    '8',\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    '1',\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    UUID())");
                                    obsCurrentlyPregnant.setString(1, patient_id);
                                    obsCurrentlyPregnant.setString(2, "1438");
                                    obsCurrentlyPregnant.setString(3, lastEncounterId);
                                    obsCurrentlyPregnant.setString(4, null);
                                    obsCurrentlyPregnant.setString(5, null);
                                    obsCurrentlyPregnant.setString(6, rsgetPatientAdultICE.getString("gestationalage").replaceAll("[^0-9]", ""));
                                    obsCurrentlyPregnant.setString(7, null);
                                    obsCurrentlyPregnant.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentlyPregnant.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentlyPregnant.addBatch();
                                }
                                if (rsgetPatientAdultICE.getString("edd") != null) {
                                    obsCurrentlyPregnant = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                            + "  `person_id`,\n"
                                            + "  `concept_id`,\n"
                                            + "  `encounter_id`,\n"
                                            + "  `location_id`,\n"
                                            + "  `value_coded`,\n"
                                            + "  `value_datetime`,\n"
                                            + "  `value_numeric`,\n"
                                            + "  `value_text`,\n"
                                            + "  `creator`,\n"
                                            + "  `obs_datetime`,\n"
                                            + "  `date_created`,\n"
                                            + "  `uuid`\n"
                                            + ") \n"
                                            + "VALUES\n"
                                            + "  (\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    '8',\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    '1',\n"
                                            + "    ?,\n"
                                            + "    ?,\n"
                                            + "    UUID())");
                                    obsCurrentlyPregnant.setString(1, patient_id);
                                    obsCurrentlyPregnant.setString(2, "5596");
                                    obsCurrentlyPregnant.setString(3, lastEncounterId);
                                    obsCurrentlyPregnant.setString(4, null);
                                    obsCurrentlyPregnant.setString(5, rsgetPatientAdultICE.getString("edd"));
                                    obsCurrentlyPregnant.setString(6, null);
                                    obsCurrentlyPregnant.setString(7, null);
                                    obsCurrentlyPregnant.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentlyPregnant.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentlyPregnant.addBatch();
                                }
                                obsCurrentlyPregnant.executeBatch();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                        }

                        if (rsgetPatientAdultICE.getString("prevarvexp") != null && !rsgetPatientAdultICE.getString("prevarvexp").equals("")) {

                            ////////// Previous ARV exposure /////////
                            if (rsgetPatientAdultICE.getString("prevarvexp").equalsIgnoreCase("No")) {
                                // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsPreviousARV = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPreviousARV.setString(1, patient_id);
                                obsPreviousARV.setString(2, "165586");
                                obsPreviousARV.setString(3, lastEncounterId);
                                obsPreviousARV.setString(4, "1066");
                                obsPreviousARV.setString(5, null);
                                obsPreviousARV.setString(6, null);
                                obsPreviousARV.setString(7, null);
                                obsPreviousARV.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                            if (rsgetPatientAdultICE.getString("prevarvexp").equalsIgnoreCase("Yes")) {
                                //  DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsPreviousARV = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPreviousARV.setString(1, patient_id);
                                obsPreviousARV.setString(2, "165586");
                                obsPreviousARV.setString(3, lastEncounterId);
                                obsPreviousARV.setString(4, "1065");
                                obsPreviousARV.setString(5, null);
                                obsPreviousARV.setString(6, null);
                                obsPreviousARV.setString(7, null);
                                obsPreviousARV.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.addBatch();

                                obsPreviousARV = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPreviousARV.setString(1, patient_id);
                                obsPreviousARV.setString(2, "165714");
                                obsPreviousARV.setString(3, lastEncounterId);
                                obsPreviousARV.setString(4, this.conceptIdsforAdultICE(rsgetPatientAdultICE.getString("prevarvtype")).getConceptId());
                                obsPreviousARV.setString(5, null);
                                obsPreviousARV.setString(6, null);
                                obsPreviousARV.setString(7, null);
                                obsPreviousARV.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.addBatch();

                                obsPreviousARV = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPreviousARV.setString(1, patient_id);
                                obsPreviousARV.setString(2, "162724");
                                obsPreviousARV.setString(3, lastEncounterId);
                                obsPreviousARV.setString(4, this.conceptIdsforAdultICE(rsgetPatientAdultICE.getString("prevfacilityname")).getConceptId());
                                obsPreviousARV.setString(5, null);
                                obsPreviousARV.setString(6, null);
                                obsPreviousARV.setString(7, null);
                                obsPreviousARV.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.addBatch();

                                obsPreviousARV = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPreviousARV.setString(1, patient_id);
                                obsPreviousARV.setString(2, "165829");
                                obsPreviousARV.setString(3, lastEncounterId);
                                obsPreviousARV.setString(4, this.conceptIdsforAdultICE(rsgetPatientAdultICE.getString("prevdurationofcarefromdate")).getConceptId());
                                obsPreviousARV.setString(5, null);
                                obsPreviousARV.setString(6, null);
                                obsPreviousARV.setString(7, null);
                                obsPreviousARV.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.addBatch();

                                obsPreviousARV = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPreviousARV.setString(1, patient_id);
                                obsPreviousARV.setString(2, "165830");
                                obsPreviousARV.setString(3, lastEncounterId);
                                obsPreviousARV.setString(4, this.conceptIdsforAdultICE(rsgetPatientAdultICE.getString("prevdurationofcaretodate")).getConceptId());
                                obsPreviousARV.setString(5, null);
                                obsPreviousARV.setString(6, null);
                                obsPreviousARV.setString(7, null);
                                obsPreviousARV.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPreviousARV.addBatch();
                                obsPreviousARV.executeBatch();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();

                            }
                        }
                        if (rsgetPatientAdultICE.getString("currentmedications") != null && !rsgetPatientAdultICE.getString("currentmedications").equals("")) {

                            ////////// Insert current medications ////////////////////
                            String CurrentMedications = rsgetPatientAdultICE.getString("currentmedications");
                            CurrentMedications = CurrentMedications.replaceAll(", ", ",");
                            CurrentMedications = CurrentMedications.replaceAll(", ,", ",");
                            String CurrentMedicationsarray[] = CurrentMedications.split(",");
                            boolean checkdata = true;
                            for (String CurrentMedicationsarrays : CurrentMedicationsarray) {
                                //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsCurrentMedications = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsCurrentMedications.setString(1, patient_id);
                                obsCurrentMedications.setString(2, "165305");
                                obsCurrentMedications.setString(3, lastEncounterId);
                                if (CurrentMedicationsarrays.equalsIgnoreCase("None*")) {
                                    obsCurrentMedications.setString(4, "1107");
                                } else if (CurrentMedicationsarrays.equalsIgnoreCase("ART")) {
                                    obsCurrentMedications.setString(4, "165303");
                                } else if (CurrentMedicationsarrays.equalsIgnoreCase("CTX")) {
                                    obsCurrentMedications.setString(4, "105281");
                                } else if (CurrentMedicationsarrays.equalsIgnoreCase("Anti-TB")) {
                                    obsCurrentMedications.setString(4, "165304");
                                } else if (CurrentMedicationsarrays.equalsIgnoreCase("STI")) {
                                    obsCurrentMedications.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsCurrentMedications.setString(5, null);
                                    obsCurrentMedications.setString(6, null);
                                    obsCurrentMedications.setString(7, null);
                                    obsCurrentMedications.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentMedications.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsCurrentMedications.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("hivstatdisclosure") != null && !rsgetPatientAdultICE.getString("hivstatdisclosure").equals("")) {

                            ////////// Insert hiv status disclosure ////////////////////
                            String Hivstatusdiclosure = rsgetPatientAdultICE.getString("hivstatdisclosure");
                            Hivstatusdiclosure = Hivstatusdiclosure.replaceAll(", ", ",");
                            Hivstatusdiclosure = Hivstatusdiclosure.replaceAll(", ,", ",");
                            String Hivstatusdiclosurearray[] = Hivstatusdiclosure.split(",");
                            boolean checkdata = true;
                            for (String Hivstatusdiclosurearrays : Hivstatusdiclosurearray) {
                                // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsHIVstatusDisclosure = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsHIVstatusDisclosure.setString(1, patient_id);
                                obsHIVstatusDisclosure.setString(2, "165678");
                                obsHIVstatusDisclosure.setString(3, lastEncounterId);
                                if (Hivstatusdiclosurearrays.equalsIgnoreCase("No one")) {
                                    obsHIVstatusDisclosure.setString(4, "165677");
                                } else if (Hivstatusdiclosurearrays.equalsIgnoreCase("Family Member")) {
                                    obsHIVstatusDisclosure.setString(4, "159424");
                                } else if (Hivstatusdiclosurearrays.equalsIgnoreCase("Friend")) {
                                    obsHIVstatusDisclosure.setString(4, "159425");
                                } else if (Hivstatusdiclosurearrays.equalsIgnoreCase("Spouse")) {
                                    obsHIVstatusDisclosure.setString(4, "159423");
                                } else if (Hivstatusdiclosurearrays.equalsIgnoreCase("Spiritual Leader")) {
                                    obsHIVstatusDisclosure.setString(4, "165676");
                                } else if (Hivstatusdiclosurearrays.equalsIgnoreCase("Other")) {
                                    obsHIVstatusDisclosure.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsHIVstatusDisclosure.setString(5, null);
                                    obsHIVstatusDisclosure.setString(6, null);
                                    obsHIVstatusDisclosure.setString(7, null);
                                    obsHIVstatusDisclosure.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsHIVstatusDisclosure.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsHIVstatusDisclosure.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("hivstatdiscussion") != null && !rsgetPatientAdultICE.getString("hivstatdiscussion").equals("")) {

                            ////////// Insert hiv status discussion ////////////////////
                            // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            obsHivstatdiscussion = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsHivstatdiscussion.setString(1, patient_id);
                            obsHivstatdiscussion.setString(2, "165679");
                            obsHivstatdiscussion.setString(3, lastEncounterId);
                            obsHivstatdiscussion.setString(4, null);
                            obsHivstatdiscussion.setString(5, null);
                            obsHivstatdiscussion.setString(6, null);
                            obsHivstatdiscussion.setString(7, rsgetPatientAdultICE.getString("hivstatdiscussion"));
                            obsHivstatdiscussion.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsHivstatdiscussion.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsHivstatdiscussion.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("pastcurrarvsideeff") != null && !rsgetPatientAdultICE.getString("pastcurrarvsideeff").equals("")) {
                            ////////// Insert Past or current ARV side effects ////////////////////
                            String Pastcurrarvsideeff = rsgetPatientAdultICE.getString("pastcurrarvsideeff");
                            Pastcurrarvsideeff = Pastcurrarvsideeff.replaceAll(", ", ",");
                            Pastcurrarvsideeff = Pastcurrarvsideeff.replaceAll(", ,", ",");
                            String Pastcurrarvsideeffarray[] = Pastcurrarvsideeff.split(",");
                            boolean checkdata = true;
                            for (String Pastcurrarvsideeffarrays : Pastcurrarvsideeffarray) {
                                //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsPastcurrarvsideeff = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPastcurrarvsideeff.setString(1, patient_id);
                                obsPastcurrarvsideeff.setString(2, "165054");
                                obsPastcurrarvsideeff.setString(3, lastEncounterId);
                                if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Signif. Nausea/Vomit")) {
                                    obsPastcurrarvsideeff.setString(4, "133473");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Insomnia/Bad Dreams")) {
                                    obsPastcurrarvsideeff.setString(4, "116743");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Steven Johnson Syndrome")) {
                                    obsPastcurrarvsideeff.setString(4, "125886");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Headache")) {
                                    obsPastcurrarvsideeff.setString(4, "139084");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Confusion/dizzy")) {
                                    obsPastcurrarvsideeff.setString(4, "120345");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Itching")) {
                                    obsPastcurrarvsideeff.setString(4, "136455");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Hyperglycemia")) {
                                    obsPastcurrarvsideeff.setString(4, "138291");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Diarrhea")) {
                                    obsPastcurrarvsideeff.setString(4, "121697");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Tingling of extremeties")) {
                                    obsPastcurrarvsideeff.setString(4, "165590");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Anemia")) {
                                    obsPastcurrarvsideeff.setString(4, "121629");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Kidney Problems")) {
                                    obsPastcurrarvsideeff.setString(4, "116505");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Pain abdomen or muscle")) {
                                    obsPastcurrarvsideeff.setString(4, "165589");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Rash")) {
                                    obsPastcurrarvsideeff.setString(4, "512");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Weakness/Fatigue")) {
                                    obsPastcurrarvsideeff.setString(4, "5226");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Liver Problems")) {
                                    obsPastcurrarvsideeff.setString(4, "165718");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Jaundice")) {
                                    obsPastcurrarvsideeff.setString(4, "136443");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Pancreatitis")) {
                                    obsPastcurrarvsideeff.setString(4, "114382");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Fat accumulation")) {
                                    obsPastcurrarvsideeff.setString(4, "165052");
                                } else if (Pastcurrarvsideeffarrays.equalsIgnoreCase("Other")) {
                                    obsPastcurrarvsideeff.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPastcurrarvsideeff.setString(5, null);
                                    obsPastcurrarvsideeff.setString(6, null);
                                    obsPastcurrarvsideeff.setString(7, null);
                                    obsPastcurrarvsideeff.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPastcurrarvsideeff.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPastcurrarvsideeff.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("petemp") != null && !rsgetPatientAdultICE.getString("petemp").equals("")) {
                            ////////// Insert Temperature ////////////////////
                            //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                            //DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                            obsTemperature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsTemperature.setString(1, patient_id);
                            obsTemperature.setString(2, "5088");
                            obsTemperature.setString(3, lastEncounterId);
                            obsTemperature.setString(4, null);
                            obsTemperature.setString(5, null);
                            obsTemperature.setString(6, rsgetPatientAdultICE.getString("petemp").replaceAll("[^0-9.]", ""));
                            obsTemperature.setString(7, null);
                            obsTemperature.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsTemperature.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsTemperature.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("pebp") != null && !rsgetPatientAdultICE.getString("pebp").equals("") && rsgetPatientAdultICE.getString("pebp").contains("/")) {
                            ////////// Insert Blood pressure ////////////////////
                            String BP = rsgetPatientAdultICE.getString("pebp").replaceAll("[^0-9/]", "");
                            BP = BP.replaceAll("//", "/");
                            String BParray[] = BP.split("/");
                            if (BParray.length == 2) {
                                obsBP = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsBP.setString(1, patient_id);
                                obsBP.setString(2, "5085");
                                obsBP.setString(3, lastEncounterId);
                                obsBP.setString(4, null);
                                obsBP.setString(5, null);
                                obsBP.setString(6, BParray[0]);
                                obsBP.setString(7, null);
                                obsBP.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsBP.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsBP.executeUpdate();

                                obsBP = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsBP.setString(1, patient_id);
                                obsBP.setString(2, "5086");
                                obsBP.setString(3, lastEncounterId);
                                obsBP.setString(4, null);
                                obsBP.setString(5, null);
                                obsBP.setString(6, BParray[1]);
                                obsBP.setString(7, null);
                                obsBP.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsBP.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsBP.executeUpdate();
                            }
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }
                        if (rsgetPatientAdultICE.getString("pepulse") != null && !rsgetPatientAdultICE.getString("pepulse").equals("")) {
                            ////////// Insert Pulse ////////////////////
                            obsBP = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsBP.setString(1, patient_id);
                            obsBP.setString(2, "5087");
                            obsBP.setString(3, lastEncounterId);
                            obsBP.setString(4, null);
                            obsBP.setString(5, null);
                            obsBP.setString(6, rsgetPatientAdultICE.getString("pepulse").replaceAll("[^0-9]", ""));
                            obsBP.setString(7, null);
                            obsBP.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsBP.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsBP.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("peweight") != null && !rsgetPatientAdultICE.getString("peweight").equals("")) {
                            ////////// Insert Weight ////////////////////
                            String weight = rsgetPatientAdultICE.getString("peweight").replaceAll("[^0-9.]", "");
                            weight = weight.replaceAll("[.]$", "");

                            obsWeight = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsWeight.setString(1, patient_id);
                            obsWeight.setString(2, "5089");
                            obsWeight.setString(3, lastEncounterId);
                            obsWeight.setString(4, null);
                            obsWeight.setString(5, null);
                            obsWeight.setString(6, weight);
                            obsWeight.setString(7, null);
                            obsWeight.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsWeight.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsWeight.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }
                        if (rsgetPatientAdultICE.getString("peheight") != null && !rsgetPatientAdultICE.getString("peheight").equals("")) {
                            ////////// Insert height ////////////////////
                            String height = rsgetPatientAdultICE.getString("peheight").replaceAll("[^0-9.]", "");
                            height = height.replaceAll("[.]$", "");
                            obsHeight = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsHeight.setString(1, patient_id);
                            obsHeight.setString(2, "5090");
                            obsHeight.setString(3, lastEncounterId);
                            obsHeight.setString(4, null);
                            obsHeight.setString(5, null);
                            obsHeight.setString(6, height);
                            obsHeight.setString(7, null);
                            obsHeight.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsHeight.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsHeight.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("pegenappearance") != null && !rsgetPatientAdultICE.getString("pegenappearance").equals("")) {
                            ////////// Insert General Appearance ////////////////////
                            String Pegenappearance = rsgetPatientAdultICE.getString("pegenappearance");
                            Pegenappearance = Pegenappearance.replaceAll(", ", ",");
                            Pegenappearance = Pegenappearance.replaceAll(", ,", ",");
                            String Pegenappearancearray[] = Pegenappearance.split(",");
                            boolean checkdata = true;
                            for (String Pegenappearancearrays : Pegenappearancearray) {
                                obsPegenappearance = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPegenappearance.setString(1, patient_id);
                                obsPegenappearance.setString(2, "165313");
                                obsPegenappearance.setString(3, lastEncounterId);
                                if (Pegenappearancearrays.equalsIgnoreCase("Palour")) {
                                    obsPegenappearance.setString(4, "147096");
                                } else if (Pegenappearancearrays.equalsIgnoreCase("Febrile")) {
                                    obsPegenappearance.setString(4, "165595");
                                } else if (Pegenappearancearrays.equalsIgnoreCase("Dehydrated")) {
                                    obsPegenappearance.setString(4, "142630");
                                } else if (Pegenappearancearrays.equalsIgnoreCase("Jaunduce")) {
                                    obsPegenappearance.setString(4, "136443");
                                } else if (Pegenappearancearrays.equalsIgnoreCase("Peripheral Edema")) {
                                    obsPegenappearance.setString(4, "130428");
                                } else if (Pegenappearancearrays.equalsIgnoreCase("Others")) {
                                    obsPegenappearance.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPegenappearance.setString(5, null);
                                    obsPegenappearance.setString(6, null);
                                    obsPegenappearance.setString(7, null);
                                    obsPegenappearance.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPegenappearance.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPegenappearance.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("gerespiratory") != null && !rsgetPatientAdultICE.getString("gerespiratory").equals("")) {
                            ////////// Insert General respiratory ////////////////////
                            String Gerespiratory = rsgetPatientAdultICE.getString("gerespiratory");
                            Gerespiratory = Gerespiratory.replaceAll(", ", ",");
                            Gerespiratory = Gerespiratory.replaceAll(", ,", ",");
                            String Gerespiratoryarray[] = Gerespiratory.split(",");
                            boolean checkdata = true;
                            for (String Gerespiratoryarrays : Gerespiratoryarray) {
                                obsGerespiratory = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsGerespiratory.setString(1, patient_id);
                                obsGerespiratory.setString(2, "165316");
                                obsGerespiratory.setString(3, lastEncounterId);
                                if (Gerespiratoryarrays.equalsIgnoreCase("Labored breathing")) {
                                    obsGerespiratory.setString(4, "136201");
                                } else if (Gerespiratoryarrays.equalsIgnoreCase("Cyanosis")) {
                                    obsGerespiratory.setString(4, "143050");
                                } else if (Gerespiratoryarrays.equalsIgnoreCase("Wheezing")) {
                                    obsGerespiratory.setString(4, "122863");
                                } else if (Gerespiratoryarrays.equalsIgnoreCase("Intercostal (sub)Recession")) {
                                    obsGerespiratory.setString(4, "165314");
                                } else if (Gerespiratoryarrays.equalsIgnoreCase("Auscultation Findings")) {
                                    obsGerespiratory.setString(4, "165315");
                                } else if (Gerespiratoryarrays.equalsIgnoreCase("Others")) {
                                    obsGerespiratory.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsGerespiratory.setString(5, null);
                                    obsGerespiratory.setString(6, null);
                                    obsGerespiratory.setString(7, null);
                                    obsGerespiratory.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGerespiratory.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGerespiratory.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("peskin") != null && !rsgetPatientAdultICE.getString("peskin").equals("") && !rsgetPatientAdultICE.getString("peskin").matches("[0-9]+")) {
                            ////////// Insert General Skin ////////////////////
                            String Peskin = rsgetPatientAdultICE.getString("peskin");
                            Peskin = Peskin.replaceAll(", ", ",");
                            Peskin = Peskin.replaceAll(", ,", ",");
                            String Peskinarray[] = Peskin.split(",");
                            boolean checkdata = true;
                            for (String Peskinarrays : Peskinarray) {
                                obsPeskin = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPeskin.setString(1, patient_id);
                                obsPeskin.setString(2, "165318");
                                obsPeskin.setString(3, lastEncounterId);
                                if (Peskinarrays.equalsIgnoreCase("Pruritic Paplar Dermatitis")) {
                                    obsPeskin.setString(4, "1249");
                                } else if (Peskinarrays.equalsIgnoreCase("Abcesses")) {
                                    obsPeskin.setString(4, "150632");
                                } else if (Peskinarrays.equalsIgnoreCase("Herpes Zoster")) {
                                    obsPeskin.setString(4, "117543");
                                } else if (Peskinarrays.equalsIgnoreCase("Kaposis Lesions")) {
                                    obsPeskin.setString(4, "165317");
                                } else if (Peskinarrays.equalsIgnoreCase("Suborrheic Dermatits")) {
                                    obsPeskin.setString(4, "113116");
                                } else if (Peskinarrays.equalsIgnoreCase("Fungal Infections")) {
                                    obsPeskin.setString(4, "171");
                                } else if (Peskinarrays.equalsIgnoreCase("Others")) {
                                    obsPeskin.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPeskin.setString(5, null);
                                    obsPeskin.setString(6, null);
                                    obsPeskin.setString(7, null);
                                    obsPeskin.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPeskin.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPeskin.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("gegastrointestinal") != null && !rsgetPatientAdultICE.getString("gegastrointestinal").equals("")) {
                            ////////// Insert General gegastrointestinal ////////////////////
                            String Gegastrointestinal = rsgetPatientAdultICE.getString("gegastrointestinal");
                            Gegastrointestinal = Gegastrointestinal.replaceAll(", ", ",");
                            Gegastrointestinal = Gegastrointestinal.replaceAll(", ,", ",");
                            String Gegastrointestinalarray[] = Gegastrointestinal.split(",");
                            boolean checkdata = true;
                            for (String Gegastrointestinalarrays : Gegastrointestinalarray) {
                                obsGegastrointestinal = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsGegastrointestinal.setString(1, patient_id);
                                obsGegastrointestinal.setString(2, "165330");
                                obsGegastrointestinal.setString(3, lastEncounterId);
                                if (Gegastrointestinalarrays.equalsIgnoreCase("Dystention")) {
                                    obsGegastrointestinal.setString(4, "165329");
                                } else if (Gegastrointestinalarrays.equalsIgnoreCase("Hepatomegaly")) {
                                    obsGegastrointestinal.setString(4, "5008");
                                } else if (Gegastrointestinalarrays.equalsIgnoreCase("Spenomegaly")) {
                                    obsGegastrointestinal.setString(4, "5009");
                                } else if (Gegastrointestinalarrays.equalsIgnoreCase("Tenderness")) {
                                    obsGegastrointestinal.setString(4, "165328");
                                } else if (Gegastrointestinalarrays.equalsIgnoreCase("Others")) {
                                    obsGegastrointestinal.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsGegastrointestinal.setString(5, null);
                                    obsGegastrointestinal.setString(6, null);
                                    obsGegastrointestinal.setString(7, null);
                                    obsGegastrointestinal.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGegastrointestinal.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGegastrointestinal.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("peheadeye") != null && !rsgetPatientAdultICE.getString("peheadeye").equals("")) {
                            ////////// Insert General peheadeye ////////////////////
                            String Peheadeye = rsgetPatientAdultICE.getString("peheadeye");
                            Peheadeye = Peheadeye.replaceAll(", ", ",");
                            Peheadeye = Peheadeye.replaceAll(", ,", ",");
                            String Peheadeyearray[] = Peheadeye.split(",");
                            boolean checkdata = true;
                            for (String Peheadeyearrays : Peheadeyearray) {
                                obsPeheadeye = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPeheadeye.setString(1, patient_id);
                                obsPeheadeye.setString(3, lastEncounterId);
                                if (Peheadeyearrays.equalsIgnoreCase("Icterus")) {
                                    obsPeheadeye.setString(2, "165327");
                                    obsPeheadeye.setString(4, "5192");
                                } else if (Peheadeyearrays.equalsIgnoreCase("Thrush")) {
                                    obsPeheadeye.setString(2, "165598");
                                    obsPeheadeye.setString(4, "5334");
                                } else if (Peheadeyearrays.equalsIgnoreCase("Oral KS")) {
                                    obsPeheadeye.setString(2, "165327");
                                    obsPeheadeye.setString(4, "644");
                                } else if (Peheadeyearrays.equalsIgnoreCase("Abnormal Fundoscopy")) {
                                    obsPeheadeye.setString(2, "165327");
                                    obsPeheadeye.setString(4, "156441");
                                } else if (Peheadeyearrays.equalsIgnoreCase("Others")) {
                                    obsPeheadeye.setString(2, "165327");
                                    obsPeheadeye.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPeheadeye.setString(5, null);
                                    obsPeheadeye.setString(6, null);
                                    obsPeheadeye.setString(7, null);
                                    obsPeheadeye.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPeheadeye.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPeheadeye.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("pecardiovascular") != null && !rsgetPatientAdultICE.getString("pecardiovascular").equals("")) {
                            ////////// Insert General pecardiovascular ////////////////////
                            String Pecardiovascular = rsgetPatientAdultICE.getString("pecardiovascular");
                            Pecardiovascular = Pecardiovascular.replaceAll(", ", ",");
                            Pecardiovascular = Pecardiovascular.replaceAll(", ,", ",");
                            String Pecardiovasculararray[] = Pecardiovascular.split(",");
                            boolean checkdata = true;
                            for (String Pecardiovasculararrays : Pecardiovasculararray) {
                                obsPecardiovascular = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPecardiovascular.setString(1, patient_id);
                                obsPecardiovascular.setString(2, "165326");
                                obsPecardiovascular.setString(3, lastEncounterId);
                                if (Pecardiovasculararrays.equalsIgnoreCase("Abnormal Heart rate")) {
                                    obsPecardiovascular.setString(4, "136522");
                                } else if (Pecardiovasculararrays.equalsIgnoreCase("Auscultation Finding")) {
                                    obsPecardiovascular.setString(4, "165315");
                                } else if (Pecardiovasculararrays.equalsIgnoreCase("Others")) {
                                    obsPecardiovascular.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPecardiovascular.setString(5, null);
                                    obsPecardiovascular.setString(6, null);
                                    obsPecardiovascular.setString(7, null);
                                    obsPecardiovascular.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPecardiovascular.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPecardiovascular.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("geneurological") != null && !rsgetPatientAdultICE.getString("geneurological").equals("")) {
                            ////////// Insert General pecardiovascular ////////////////////
                            String Geneurological = rsgetPatientAdultICE.getString("geneurological");
                            Geneurological = Geneurological.replaceAll(", ", ",");
                            Geneurological = Geneurological.replaceAll(", ,", ",");
                            String Geneurologicalarray[] = Geneurological.split(",");
                            boolean checkdata = true;
                            for (String Geneurologicalarrays : Geneurologicalarray) {
                                obsGeneurological = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsGeneurological.setString(1, patient_id);
                                obsGeneurological.setString(2, "165325");
                                obsGeneurological.setString(3, lastEncounterId);
                                if (Geneurologicalarrays.equalsIgnoreCase("Orientation to TPP")) {
                                    obsGeneurological.setString(4, "165319");
                                } else if (Geneurologicalarrays.equalsIgnoreCase("Speech Slurs")) {
                                    obsGeneurological.setString(4, "126351");
                                } else if (Geneurologicalarrays.equalsIgnoreCase("Neck Stiffness")) {
                                    obsGeneurological.setString(4, "5170");
                                } else if (Geneurologicalarrays.equalsIgnoreCase("Blindness 1/2 Eyes")) {
                                    obsGeneurological.setString(4, "165321");
                                } else if (Geneurologicalarrays.equalsIgnoreCase("Hemiplegia Paresis")) {
                                    obsGeneurological.setString(4, "117655");
                                } else if (Geneurologicalarrays.equalsIgnoreCase("Numbness of extremeties")) {
                                    obsGeneurological.setString(4, "165323");
                                } else if (Geneurologicalarrays.equalsIgnoreCase("Others")) {
                                    obsGeneurological.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsGeneurological.setString(5, null);
                                    obsGeneurological.setString(6, null);
                                    obsGeneurological.setString(7, null);
                                    obsGeneurological.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGeneurological.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGeneurological.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("pebreasts") != null && !rsgetPatientAdultICE.getString("pebreasts").equals("")) {
                            ////////// Insert General Breasts ////////////////////
                            String Pebreasts = rsgetPatientAdultICE.getString("pebreasts");
                            Pebreasts = Pebreasts.replaceAll(", ", ",");
                            Pebreasts = Pebreasts.replaceAll(", ,", ",");
                            String Pebreastsarray[] = Pebreasts.split(",");
                            boolean checkdata = true;
                            for (String Pebreastsarrays : Pebreastsarray) {
                                obsPebreasts = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPebreasts.setString(1, patient_id);
                                obsPebreasts.setString(2, "165598");
                                obsPebreasts.setString(3, lastEncounterId);
                                if (Pebreastsarrays.equalsIgnoreCase("Lumps|Masses")) {
                                    obsPebreasts.setString(4, "165596");
                                } else if (Pebreastsarrays.equalsIgnoreCase("Discharge")) {
                                    obsPebreasts.setString(4, "165597");
                                } else if (Pebreastsarrays.equalsIgnoreCase("Others")) {
                                    obsPebreasts.setString(4, "165597");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPebreasts.setString(5, null);
                                    obsPebreasts.setString(6, null);
                                    obsPebreasts.setString(7, null);
                                    obsPebreasts.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPebreasts.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPebreasts.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("pegenitalia") != null && !rsgetPatientAdultICE.getString("pegenitalia").equals("")) {
                            ////////// Insert General Genitalia ////////////////////
                            String Pegenitalia = rsgetPatientAdultICE.getString("pegenitalia");
                            Pegenitalia = Pegenitalia.replaceAll(", ", ",");
                            Pegenitalia = Pegenitalia.replaceAll(", ,", ",");
                            String Pegenitaliaarray[] = Pegenitalia.split(",");
                            boolean checkdata = true;
                            for (String Pegenitaliaarrays : Pegenitaliaarray) {
                                obsPegenitalia = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPegenitalia.setString(1, patient_id);

                                obsPegenitalia.setString(3, lastEncounterId);
                                if (Pegenitaliaarrays.equalsIgnoreCase("Genital Discharge")) {
                                    obsPegenitalia.setString(2, "165333");
                                    obsPegenitalia.setString(4, "165573");
                                } else if (Pegenitaliaarrays.equalsIgnoreCase("Genital Ulcer/Other Lesion")) {
                                    obsPegenitalia.setString(2, "165333");
                                    obsPegenitalia.setString(4, "165332");
                                } else if (Pegenitaliaarrays.equalsIgnoreCase("Inguinal Node Enlargement")) {
                                    obsPegenitalia.setString(2, "165326");
                                    obsPegenitalia.setString(4, "165719");
                                } else if (Pegenitaliaarrays.equalsIgnoreCase("Others")) {
                                    obsPegenitalia.setString(2, "165333");
                                    obsPegenitalia.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPegenitalia.setString(5, null);
                                    obsPegenitalia.setString(6, null);
                                    obsPegenitalia.setString(7, null);
                                    obsPegenitalia.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPegenitalia.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPegenitalia.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("gementalstatus") != null && !rsgetPatientAdultICE.getString("gementalstatus").equals("")) {
                            ////////// Insert General mentalstatus ////////////////////
                            String Gementalstatus = rsgetPatientAdultICE.getString("gementalstatus");
                            Gementalstatus = Gementalstatus.replaceAll(", ", ",");
                            Gementalstatus = Gementalstatus.replaceAll(", ,", ",");
                            String Gementalstatusarray[] = Gementalstatus.split(",");
                            boolean checkdata = true;
                            for (String Gementalstatusarrays : Gementalstatusarray) {
                                obsGementalstatus = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsGementalstatus.setString(1, patient_id);
                                obsGementalstatus.setString(2, "165339");
                                obsGementalstatus.setString(3, lastEncounterId);
                                if (Gementalstatusarrays.equalsIgnoreCase("Slow Mentation")) {
                                    obsGementalstatus.setString(4, "165335");
                                } else if (Gementalstatusarrays.equalsIgnoreCase("Memory Loss")) {
                                    obsGementalstatus.setString(4, "121657");
                                } else if (Gementalstatusarrays.equalsIgnoreCase("Mood Swings")) {
                                    obsGementalstatus.setString(4, "165336");
                                } else if (Gementalstatusarrays.equalsIgnoreCase("Depression")) {
                                    obsGementalstatus.setString(4, "119537");
                                } else if (Gementalstatusarrays.equalsIgnoreCase("Anxiety")) {
                                    obsGementalstatus.setString(4, "121543");
                                } else if (Gementalstatusarrays.equalsIgnoreCase("Suicidal Ideation")) {
                                    obsGementalstatus.setString(4, "165338");
                                } else if (Gementalstatusarrays.equalsIgnoreCase("Others")) {
                                    obsGementalstatus.setString(4, "5622");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsGementalstatus.setString(5, null);
                                    obsGementalstatus.setString(6, null);
                                    obsGementalstatus.setString(7, null);
                                    obsGementalstatus.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGementalstatus.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsGementalstatus.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("peadditional") != null && !rsgetPatientAdultICE.getString("peadditional").equals("")) {
                            ////////// Insert General Peadditional ////////////////////
                            obsPeadditional = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsPeadditional.setString(1, patient_id);
                            obsPeadditional.setString(2, "165600");
                            obsPeadditional.setString(3, lastEncounterId);
                            obsPeadditional.setString(4, null);
                            obsPeadditional.setString(5, null);
                            obsPeadditional.setString(6, null);
                            obsPeadditional.setString(7, rsgetPatientAdultICE.getString("peadditional"));
                            obsPeadditional.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsPeadditional.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsPeadditional.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientAdultICE.getString("assessment") != null && !rsgetPatientAdultICE.getString("assessment").equals("")) {
                            ////////// Insert General assessment ////////////////////
                            boolean checkdata = true;
                            obsAssessment = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsAssessment.setString(1, patient_id);
                            obsAssessment.setString(2, "165602");
                            obsAssessment.setString(3, lastEncounterId);
                            if (rsgetPatientAdultICE.getString("assessment").equalsIgnoreCase("Asymptomatic")) {
                                obsAssessment.setString(4, "5006");
                            } else if (rsgetPatientAdultICE.getString("assessment").equalsIgnoreCase("Symptomatic")) {
                                obsAssessment.setString(4, "1068");
                            } else if (rsgetPatientAdultICE.getString("assessment").equalsIgnoreCase("AIDS defining illness")) {
                                obsAssessment.setString(4, "165307");
                            } else if (rsgetPatientAdultICE.getString("assessment").equalsIgnoreCase("Opportunistic Infections")) {
                                obsAssessment.setString(4, "131768");
                            } else {
                                checkdata = false;
                            }
                            if (checkdata == true) {
                                obsAssessment.setString(5, null);
                                obsAssessment.setString(6, null);
                                obsAssessment.setString(7, null);
                                obsAssessment.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsAssessment.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsAssessment.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                        }

                        if (rsgetPatientAdultICE.getString("whostagecriteria") != null && !rsgetPatientAdultICE.getString("whostagecriteria").equals("")) {
                            boolean checkdata = true;
                            /////// insert WHO stage//////
                            if (rsgetPatientAdultICE.getString("whostage") != null) {
                                obsWhostagecriteria = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsWhostagecriteria.setString(1, patient_id);
                                obsWhostagecriteria.setString(2, "5356");
                                obsWhostagecriteria.setString(3, lastEncounterId);
                                if (rsgetPatientAdultICE.getString("whostage").equals("1")) {
                                    obsWhostagecriteria.setString(4, "1204");
                                } else if (rsgetPatientAdultICE.getString("whostage").equals("2")) {
                                    obsWhostagecriteria.setString(4, "1205");
                                } else if (rsgetPatientAdultICE.getString("whostage").equals("3")) {
                                    obsWhostagecriteria.setString(4, "1206");
                                } else if (rsgetPatientAdultICE.getString("whostage").equals("4")) {
                                    obsWhostagecriteria.setString(4, "1207");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsWhostagecriteria.setString(5, null);
                                    obsWhostagecriteria.setString(6, null);
                                    obsWhostagecriteria.setString(7, null);
                                    obsWhostagecriteria.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsWhostagecriteria.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsWhostagecriteria.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }

                            ////////// Insert whostagecriteria stage 1 ////////////////////
                            String Whostagecriteria = rsgetPatientAdultICE.getString("whostagecriteria");
                            Whostagecriteria = Whostagecriteria.replaceAll(", ", ",");
                            Whostagecriteria = Whostagecriteria.replaceAll(", ,", ",");
                            String Whostagecriteriaarray[] = Whostagecriteria.split(",");
                            boolean checkdatax = true;
                            for (String Whostagecriteriaarrays : Whostagecriteriaarray) {
                                obsWhostagecriteria = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsWhostagecriteria.setString(1, patient_id);
                                obsWhostagecriteria.setString(3, lastEncounterId);
                                ///////stage 1//////////
                                if (Whostagecriteriaarrays.equalsIgnoreCase("Asymptomatic*")) {
                                    obsWhostagecriteria.setString(2, "1204");
                                    obsWhostagecriteria.setString(4, "5327");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Persistent generalized Lympadenopathy*")) {
                                    obsWhostagecriteria.setString(2, "1204");
                                    obsWhostagecriteria.setString(4, "5328");
                                } else ///////stage 2//////////
                                if (Whostagecriteriaarrays.equalsIgnoreCase("Weight Loss <10% of Body Weight")) {
                                    obsWhostagecriteria.setString(2, "1205");
                                    obsWhostagecriteria.setString(4, "5332");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Minor Mucocutaneous Manifestations")) {
                                    obsWhostagecriteria.setString(2, "1205");
                                    obsWhostagecriteria.setString(4, "5330");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Herpes Zoster (within last 5 years)")) {
                                    obsWhostagecriteria.setString(2, "1205");
                                    obsWhostagecriteria.setString(4, "5329");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Recurrent Upper Respiratory Tract Infections")) {
                                    obsWhostagecriteria.setString(2, "1205");
                                    obsWhostagecriteria.setString(4, "5012");
                                } else //////stage 3 ///////
                                if (Whostagecriteriaarrays.equalsIgnoreCase("Weight loss >10% of body weight")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5339");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Unexplained Chronic Diarrhea (>1 month)")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5018");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Unexplained Prolonged Fever")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5027");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Oral Candidiasis")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5334");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Oral Hairy Leukoplakia")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5337");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("TB, Pulmonary (within previous year)")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5338");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Severe Bacterial Infections")) {
                                    obsWhostagecriteria.setString(2, "1206");
                                    obsWhostagecriteria.setString(4, "5030");
                                } else ///////stage 4////////
                                if (Whostagecriteriaarrays.equalsIgnoreCase("HIV Wasting syndrome")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "823");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("PCP")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "882");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Toxoplasmosis, CNS")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "990");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Cryptosporidiosis with Diarrhea (>1 month)")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5034");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Cryptococcosis, Extrapulmonary")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5033");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Cytomegalovirus disease")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5035");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Herpes Simplex (mucotaneous >1 month)")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5344");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Progressive Multifocal Leukoencephalopathy")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5046");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Mycosis, disseminated")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5350");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Candidiasis*")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5340");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Atypical Mycobacteriosis, disseminated")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5043");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Salmonella Septicemia, Non-Typhoid")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5354");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("TB, Extrapulmonary")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5042");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Lymphoma")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5041");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("Kaposi Sarcoma")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "507");
                                } else if (Whostagecriteriaarrays.equalsIgnoreCase("HIV Encephalopathy")) {
                                    obsWhostagecriteria.setString(2, "1207");
                                    obsWhostagecriteria.setString(4, "5345");
                                } else {
                                    checkdatax = false;
                                }
                                if (checkdatax == true) {
                                    obsWhostagecriteria.setString(5, null);
                                    obsWhostagecriteria.setString(6, null);
                                    obsWhostagecriteria.setString(7, null);
                                    obsWhostagecriteria.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsWhostagecriteria.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsWhostagecriteria.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("plan") != null && !rsgetPatientAdultICE.getString("plan").equals("")) {
                            String Plan = rsgetPatientAdultICE.getString("plan");
                            Plan = Plan.replaceAll(", ", ",");
                            Plan = Plan.replaceAll(", ,", ",");
                            String Planarray[] = Plan.split(",");
                            boolean checkdata = true;
                            for (String Planarrays : Planarray) {
                                ////////// Insert Plan ////////////////////
                                obsPlan = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsPlan.setString(1, patient_id);
                                obsPlan.setString(2, "165352");
                                obsPlan.setString(3, lastEncounterId);
                                if (Planarrays.equalsIgnoreCase("Lab Evaluation")) {
                                    obsPlan.setString(4, "165341");
                                } else if (Planarrays.equalsIgnoreCase("OI Therapy")) {
                                    obsPlan.setString(4, "165342");
                                } else if (Planarrays.equalsIgnoreCase("TB Investigation")) {
                                    obsPlan.setString(4, "164800");
                                } else if (Planarrays.equalsIgnoreCase("Admission")) {
                                    obsPlan.setString(4, "165343");
                                } else if (Planarrays.equalsIgnoreCase("OI Prophylaxis")) {
                                    obsPlan.setString(4, "165344");
                                } else if (Planarrays.equalsIgnoreCase("Symptomatic treatment/Pain control")) {
                                    obsPlan.setString(4, "165345");
                                } else if (Planarrays.equalsIgnoreCase("Adherence Counseling")) {
                                    obsPlan.setString(4, "165350");
                                } else if (Planarrays.equalsIgnoreCase("Other")) {
                                    obsPlan.setString(4, "165351");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsPlan.setString(5, null);
                                    obsPlan.setString(6, null);
                                    obsPlan.setString(7, null);
                                    obsPlan.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPlan.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsPlan.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                    
                        if (rsgetPatientAdultICE.getString("enrollin") != null && !rsgetPatientAdultICE.getString("enrollin").equals("")) {
                            String Enrollin = rsgetPatientAdultICE.getString("enrollin");
                            Enrollin = Enrollin.replaceAll(", ", ",");
                            Enrollin = Enrollin.replaceAll(", ,", ",");
                            String Enrollinarray[] = Enrollin.split(",");
                            boolean checkdata = true;
                            for (String Enrollinarrays : Enrollinarray) {
                                ////////// Insert Enrollin ////////////////////
                                obsEnrollin = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsEnrollin.setString(1, patient_id);
                                obsEnrollin.setString(2, "165355");
                                obsEnrollin.setString(3, lastEncounterId);
                                if (Enrollinarrays.equalsIgnoreCase("General Medical Follow up")) {
                                    obsEnrollin.setString(4, "165353");
                                } else if (Enrollinarrays.equalsIgnoreCase("ARV Therapy")) {
                                    obsEnrollin.setString(4, "165303");
                                } else if (Enrollinarrays.equalsIgnoreCase("Pending lab results")) {
                                    obsEnrollin.setString(4, "165354");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsEnrollin.setString(5, null);
                                    obsEnrollin.setString(6, null);
                                    obsEnrollin.setString(7, null);
                                    obsEnrollin.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                    obsEnrollin.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                    obsEnrollin.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientAdultICE.getString("planarvtherapy") != null && !rsgetPatientAdultICE.getString("planarvtherapy").equals("")) {

                            ////////// Insert Planarvtherapy ////////////////////
                            boolean checkdata = true;
                            obsPlanarvtherapy = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsPlanarvtherapy.setString(1, patient_id);
                            obsPlanarvtherapy.setString(2, "165361");
                            obsPlanarvtherapy.setString(3, lastEncounterId);
                            if (rsgetPatientAdultICE.getString("planarvtherapy").equalsIgnoreCase("Restart treatment")) {
                                obsPlanarvtherapy.setString(4, "162904");
                            } else if (rsgetPatientAdultICE.getString("planarvtherapy").equalsIgnoreCase("Start new treatment")) {
                                obsPlanarvtherapy.setString(4, "165357");
                            } else if (rsgetPatientAdultICE.getString("planarvtherapy").equalsIgnoreCase("Ongoing monitoring -ARV Tx post-poned/Clinical reasons")) {
                                obsPlanarvtherapy.setString(4, "165356");
                            } else if (rsgetPatientAdultICE.getString("planarvtherapy").equalsIgnoreCase("Change treatment")) {
                                obsPlanarvtherapy.setString(4, "165358");
                            } else if (rsgetPatientAdultICE.getString("planarvtherapy").equalsIgnoreCase("Stop treatment")) {
                                obsPlanarvtherapy.setString(4, "165360");
                            } else {
                                checkdata = false;
                            }
                            if (checkdata == true) {
                                obsPlanarvtherapy.setString(5, null);
                                obsPlanarvtherapy.setString(6, null);
                                obsPlanarvtherapy.setString(7, null);
                                obsPlanarvtherapy.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                                obsPlanarvtherapy.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                                obsPlanarvtherapy.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }

                        }

                        if (rsgetPatientAdultICE.getString("nextappointmentdate") != null && !rsgetPatientAdultICE.getString("nextappointmentdate").equals("")) {

                            ////////// Insert nextappointmentdate ////////////////////
                            obsNextappointmentdate = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsNextappointmentdate.setString(1, patient_id);
                            obsNextappointmentdate.setString(2, "165036");
                            obsNextappointmentdate.setString(3, lastEncounterId);
                            obsNextappointmentdate.setString(4, null);
                            obsNextappointmentdate.setString(5, rsgetPatientAdultICE.getString("nextappointmentdate"));
                            obsNextappointmentdate.setString(6, null);
                            obsNextappointmentdate.setString(7, null);
                            obsNextappointmentdate.setString(8, rsgetPatientAdultICE.getString("enroldate"));
                            obsNextappointmentdate.setString(9, rsgetPatientAdultICE.getString("enroldate"));
                            obsNextappointmentdate.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }
                        if (rsgetPatientAdultICE.getString("enroldate") != null && !rsgetPatientAdultICE.getString("enroldate").equalsIgnoreCase("") && rsgetPatientAdultICE.getString("Clinicianname") != null && !rsgetPatientAdultICE.getString("Clinicianname").equalsIgnoreCase("")) {
                            this.migrateAdultICESignature(patient_id, lastEncounterId, rsgetPatientAdultICE.getString("enroldate"), rsgetPatientAdultICE.getString("Clinicianname"), rsgetPatientAdultICE.getString("enroldate"), rsgetPatientAdultICE.getString("Clinicianname"), rsgetPatientAdultICE.getString("enroldate"), rsgetPatientAdultICE.getString("clinicianname"));
                        }

                        String lastEncounterForHivEnrolmentId = loadpatientVisit(patient_id, rsgetPatientAdultICE.getString("enroldate"), "14", "23");
                        Patients patient = new Patients();
                        patient.setEnroldate(rsgetPatientAdultICE.getDate(rsgetPatientAdultICE.findColumn("enroldate")));
                        patient.setCreated_on(rsgetPatientAdultICE.getDate(rsgetPatientAdultICE.findColumn("created_on")));

                        HIVEnrollmentForm(Integer.parseInt(patient_id), patient);

                        adulticecount++;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                adultClinicalEvalLabel.setText(String.valueOf(adulticecount));
                            }
                        });
                        new LoadSettings().updateMigrationField("patient", "icfmigrated", "idpatient", rsgetPatientAdultICE.getString("myid"));
                    }

                    loadingImageLabel.setVisible(false);
                    completedLabelAdultICE.setVisible(true);
                } catch (Exception ex) {
                    System.out.println("=======================================ERROR============");
                    System.out.println("");
                    System.out.println("=======================================ERROREND============");
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static boolean HIVEnrollmentForm(int PatientID, Patients patient) {

        try {

            Map<Integer, String> map = new HashMap<>();
            //map.put(PatientID, value)  //PatientUniqueID/ARTNo     
            map.put(160555, patient.getEnroldate().toString());//DateEnrolledinCare
            map.put(160554, patient.getEnroldate().toString()); //Date Confirmed HIV+	
            map.put(164947, patient.getHivmode()); //ModeofHIVTest	
            map.put(160540, patient.getCareentrypoint()); //CareEntryPoint
            map.put(165766, patient.getPriorart());//WaspatientpreviouslyonART	
            //SocialDemography
            map.put(1542, patient.getJobstatus()); //OccupationalStatus		
            map.put(1054, patient.getMaritalstatus()); //MaritalStatus		
            map.put(1712, patient.getEducationallevel());//EducationalLevel	
            // map.put(162729, patient.getNokname());//Nameofnextofkin
            //map.put(164943, patient.getNokrelationship());//Relationshipwithnextofkin
            map.put(159635, patient.getNokphoneno());//PhonenumberofnextofKin	
            //Signature
            //Visit Date:	
            //Enrolled by:		
            //Facility:
            Visit visit = VisitUtil.getVisitByDate(patient.getCreated_on(), PatientID, 1);
            int VisitID = 0;
            if (visit == null) {
                visit = VisitUtil.createNewVisit(1, PatientID, patient.getCreated_on(), patient.getCreated_on(), 1, 1);
                VisitID = visit.getVisitId();
            } else {
                VisitID = visit.getVisitId();
            }

            if (VisitID != 0) {
                Encounter encounter = EncounterUtil.getEncounterByDate(patient.getCreated_on(), PatientID, 14);
                int encounterID = 0;
                if (encounter == null) {
                    encounter = EncounterUtil.createNewEncounter(14, PatientID, 8, 23, patient.getCreated_on(), 1, patient.getCreated_on(), VisitID);
                    encounterID = encounter.getEncounterID();
                } else {
                    encounterID = encounter.getEncounterID();
                }

                int conceptId = 0;

                for (int key : map.keySet()) {

                    ObsUtil.InsertObs(PatientID, key, encounterID, encounter.getEncounterDate(), 8, map.get(key));
                }

                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void migrateClinicalEncounter() {
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {
            if (basicInfoMigrationStatus == 0) {
                JOptionPane.showMessageDialog(null, "Please Migrate Patient Basic Info first!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadingImageLabel.setVisible(true);
                migrateClinicalEncounter.setVisible(false);
                // moduleProcessing.setText("Uploading patient clinical encounter");
                try {
                    getPatientClinicalEval = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT   CAST(height AS UNSIGNED) AS myheight, CAST(`weight` AS UNSIGNED) AS myweight, clinicevalid AS myid , c.* FROM `clinicaleval` c WHERE migrated = 0 AND visitdate !='0000-00-00' AND pepid IN (SELECT pepid from `patient`)");
                    rsgetPatientClinicalEval = getPatientClinicalEval.executeQuery();
                    DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                    DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                    while (rsgetPatientClinicalEval.next()) {

                        ///get patient_id from patient_identifier table
                        String patient_id = this.getPatient(rsgetPatientClinicalEval.getString("pepid"));
                        ///get the concept_id from concepts using the tests in cispro table
                        lastEncounterId = loadpatientVisit(patient_id, rsgetPatientClinicalEval.getString("visitdate"), "12", "14");

                        if (rsgetPatientClinicalEval.getString("myweight") != null && !rsgetPatientClinicalEval.getString("myweight").equals("")) {

                            ////////// Insert weight of clinical evaluation ////////////////////
                            String weight = rsgetPatientClinicalEval.getString("myweight").replaceAll("[^0-9.]", "");
                            weight = weight.replaceAll("[.]$", "");
                            obsWeightClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsWeightClinicalEval.setString(1, patient_id);
                            obsWeightClinicalEval.setString(2, "5089");
                            obsWeightClinicalEval.setString(3, lastEncounterId);
                            obsWeightClinicalEval.setString(4, null);
                            obsWeightClinicalEval.setString(5, null);
                            obsWeightClinicalEval.setString(6, weight);
                            obsWeightClinicalEval.setString(7, null);
                            obsWeightClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWeightClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWeightClinicalEval.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("myheight") != null && !rsgetPatientClinicalEval.getString("myheight").equals("")) {
                            System.out.println("-->" + rsgetPatientClinicalEval.getString("myheight"));
                            ////////// Insert height of clinical evaluation ////////////////////
                            String height = rsgetPatientClinicalEval.getString("myheight").replaceAll("[^0-9.]", "");
                            height = height.replaceAll("[.]$", "");
                            obsWeightClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsWeightClinicalEval.setString(1, patient_id);
                            obsWeightClinicalEval.setString(2, "5090");
                            obsWeightClinicalEval.setString(3, lastEncounterId);
                            obsWeightClinicalEval.setString(4, null);
                            obsWeightClinicalEval.setString(5, null);
                            obsWeightClinicalEval.setString(6, height);
                            obsWeightClinicalEval.setString(7, null);
                            obsWeightClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWeightClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWeightClinicalEval.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("bmi_muac") != null && !rsgetPatientClinicalEval.getString("bmi_muac").equals("")) {

                            ////////// Insert bmi of clinical evaluation ////////////////////
                            obsWeightClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsWeightClinicalEval.setString(1, patient_id);
                            obsWeightClinicalEval.setString(2, "1342");
                            obsWeightClinicalEval.setString(3, lastEncounterId);
                            obsWeightClinicalEval.setString(4, null);
                            obsWeightClinicalEval.setString(5, null);
                            obsWeightClinicalEval.setString(6, rsgetPatientClinicalEval.getString("bmi_muac").replaceAll("[^0-9.]", ""));
                            obsWeightClinicalEval.setString(7, null);
                            obsWeightClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWeightClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWeightClinicalEval.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("bp") != null && !rsgetPatientClinicalEval.getString("bp").equals("") && rsgetPatientClinicalEval.getString("bp").contains("/")) {

                            ////////// Insert bp of clinical evaluation systolic ////////////////////
                            String BP = rsgetPatientClinicalEval.getString("bp").replaceAll("[^0-9/]", "");
                            BP = BP.replaceAll("//", "/");
                            String BParray[] = BP.split("/");
                            if (BParray.length == 2) {
                                obsBPClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsBPClinicalEval.setString(1, patient_id);
                                obsBPClinicalEval.setString(2, "5085");
                                obsBPClinicalEval.setString(3, lastEncounterId);
                                obsBPClinicalEval.setString(4, null);
                                obsBPClinicalEval.setString(5, null);
                                obsBPClinicalEval.setString(6, BParray[0].replaceAll("[^0-9]", ""));
                                obsBPClinicalEval.setString(7, null);
                                obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.executeUpdate();

                                obsBPClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsBPClinicalEval.setString(1, patient_id);
                                obsBPClinicalEval.setString(2, "5086");
                                obsBPClinicalEval.setString(3, lastEncounterId);
                                obsBPClinicalEval.setString(4, null);
                                obsBPClinicalEval.setString(5, null);
                                obsBPClinicalEval.setString(6, BParray[1].replaceAll("[^0-9]", ""));
                                obsBPClinicalEval.setString(7, null);
                                obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.executeUpdate();

                            }
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("pregnancybfstat") != null && !rsgetPatientClinicalEval.getString("pregnancybfstat").equals("")) {

                            ////////// Insert Pregnancy Breastfeeding Status ////////////////////
                            String Pregnancybfstat = rsgetPatientClinicalEval.getString("pregnancybfstat").replaceAll("[^a-zA-Z]", "");

                            obsBPClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");

                            if (Pregnancybfstat.equalsIgnoreCase("Breastfeeding")) {
                                obsBPClinicalEval.setString(1, patient_id);
                                obsBPClinicalEval.setString(3, lastEncounterId);
                                obsBPClinicalEval.setString(2, "5632");
                                obsBPClinicalEval.setString(4, "1065");
                                obsBPClinicalEval.setString(5, null);
                                obsBPClinicalEval.setString(6, null);
                                obsBPClinicalEval.setString(7, null);
                                obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.executeUpdate();
                            }
                            if (Pregnancybfstat.equalsIgnoreCase("Pregnant")) {
                                obsBPClinicalEval.setString(1, patient_id);
                                obsBPClinicalEval.setString(3, lastEncounterId);
                                obsBPClinicalEval.setString(2, "5632");
                                obsBPClinicalEval.setString(4, "1066");
                                obsBPClinicalEval.setString(5, null);
                                obsBPClinicalEval.setString(6, null);
                                obsBPClinicalEval.setString(7, null);
                                obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsBPClinicalEval.executeUpdate();
                            }

                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("pregnancybfstat") != null && !rsgetPatientClinicalEval.getString("pregnancybfstat").equals("")) {

                            ////////// Insert Pregnancy Breastfeeding Status ////////////////////
                            String Pregnancybfstat = rsgetPatientClinicalEval.getString("pregnancybfstat").replaceAll("[^a-zA-Z]", "");

                            if (Pregnancybfstat != null && !Pregnancybfstat.equals("")) {
                                obsBPClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                if (Pregnancybfstat.equalsIgnoreCase("Breastfeeding")) {
                                    obsBPClinicalEval.setString(1, patient_id);
                                    obsBPClinicalEval.setString(3, lastEncounterId);
                                    obsBPClinicalEval.setString(2, "1434");
                                    obsBPClinicalEval.setString(4, "2");
                                    obsBPClinicalEval.setString(5, null);
                                    obsBPClinicalEval.setString(6, null);
                                    obsBPClinicalEval.setString(7, null);
                                    obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.executeUpdate();
                                }
                                if (Pregnancybfstat.equalsIgnoreCase("Pregnant")) {
                                    obsBPClinicalEval.setString(1, patient_id);
                                    obsBPClinicalEval.setString(3, lastEncounterId);
                                    obsBPClinicalEval.setString(2, "1434");
                                    obsBPClinicalEval.setString(4, "1");
                                    obsBPClinicalEval.setString(5, null);
                                    obsBPClinicalEval.setString(6, null);
                                    obsBPClinicalEval.setString(7, null);
                                    obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.executeUpdate();
                                }

                                obsBPClinicalEval = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");

                                if (Pregnancybfstat.equalsIgnoreCase("Breastfeeding")) {
                                    obsBPClinicalEval.setString(1, patient_id);
                                    obsBPClinicalEval.setString(3, lastEncounterId);
                                    obsBPClinicalEval.setString(2, "5632");
                                    obsBPClinicalEval.setString(4, "1065");
                                    obsBPClinicalEval.setString(5, null);
                                    obsBPClinicalEval.setString(6, null);
                                    obsBPClinicalEval.setString(7, null);
                                    obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.executeUpdate();
                                }
                                if (Pregnancybfstat.equalsIgnoreCase("Pregnant") && rsgetPatientClinicalEval.getString("edddate") != null) {
                                    obsBPClinicalEval.setString(1, patient_id);
                                    obsBPClinicalEval.setString(3, lastEncounterId);
                                    obsBPClinicalEval.setString(2, "5596");
                                    obsBPClinicalEval.setString(4, null);
                                    obsBPClinicalEval.setString(5, rsgetPatientClinicalEval.getString("edddate"));
                                    obsBPClinicalEval.setString(6, null);
                                    obsBPClinicalEval.setString(7, null);
                                    obsBPClinicalEval.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsBPClinicalEval.executeUpdate();
                                }

                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }

                        }

                        if (rsgetPatientClinicalEval.getString("fp") != null && !rsgetPatientClinicalEval.getString("fp").equals("")) {

                            ////////// Insert family planning ////////////////////
                            String FP = rsgetPatientClinicalEval.getString("fp").replaceAll("[^a-z A-Z/]", "");

                            obsFamilyPlanning = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsFamilyPlanning.setString(1, patient_id);
                            obsFamilyPlanning.setString(3, lastEncounterId);
                            obsFamilyPlanning.setString(2, "5271");
                            obsFamilyPlanning.setString(4, "1");
                            obsFamilyPlanning.setString(5, null);
                            obsFamilyPlanning.setString(6, null);
                            obsFamilyPlanning.setString(7, null);
                            obsFamilyPlanning.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsFamilyPlanning.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            obsFamilyPlanning.executeUpdate();

                            boolean checkdata = true;
                            obsFamilyPlanning = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsFamilyPlanning.setString(1, patient_id);
                            obsFamilyPlanning.setString(3, lastEncounterId);
                            obsFamilyPlanning.setString(2, "374");
                            if (FP.equalsIgnoreCase("Condoms")) {
                                obsFamilyPlanning.setString(4, "190");
                            } else if (FP.equalsIgnoreCase("Oral Contraceptive Pills")) {
                                obsFamilyPlanning.setString(4, "780");
                            } else if (FP.equalsIgnoreCase("Injectable/Implantable Hormones")) {
                                obsFamilyPlanning.setString(4, "5279");
                            } else if (FP.equalsIgnoreCase("Diaphram/Cervical Cap")) {
                                obsFamilyPlanning.setString(4, "5278");
                            } else if (FP.equalsIgnoreCase("Intrauterine Device")) {
                                obsFamilyPlanning.setString(4, "5275");
                            } else if (FP.equalsIgnoreCase("Vasectomy/Tubal Ligation/Hysterectomy")) {
                                obsFamilyPlanning.setString(4, "1489");
                            } else {
                                checkdata = false;
                            }
                            obsFamilyPlanning.setString(5, null);
                            obsFamilyPlanning.setString(6, null);
                            obsFamilyPlanning.setString(7, null);
                            obsFamilyPlanning.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsFamilyPlanning.setString(9, rsgetPatientClinicalEval.getString("visitdate"));

                            if (checkdata == true) {

                                obsFamilyPlanning.executeUpdate();
                            }
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("functionalstatus") != null && !rsgetPatientClinicalEval.getString("functionalstatus").equals("")) {

                            ////////// Insert Functional status ////////////////////
                            String FunctionalStatus = rsgetPatientClinicalEval.getString("functionalstatus").replaceAll("[^a-zA-Z]", "");
                            boolean checkdata = true;
                            obsFunctionalStatus = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsFunctionalStatus.setString(1, patient_id);
                            obsFunctionalStatus.setString(3, lastEncounterId);
                            obsFunctionalStatus.setString(2, "165039");
                            if (FunctionalStatus.equalsIgnoreCase("Working")) {
                                obsFunctionalStatus.setString(4, "162750");
                            } else if (FunctionalStatus.equalsIgnoreCase("Bedridden")) {
                                obsFunctionalStatus.setString(4, "162752");
                            } else if (FunctionalStatus.equalsIgnoreCase("Ambulant")) {
                                obsFunctionalStatus.setString(4, "160026");
                            } else {
                                checkdata = false;
                            }
                            obsFunctionalStatus.setString(5, null);
                            obsFunctionalStatus.setString(6, null);
                            obsFunctionalStatus.setString(7, null);
                            obsFunctionalStatus.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsFunctionalStatus.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            if (checkdata == true) {
                                obsFunctionalStatus.executeUpdate();
                            }
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("whostage") != null && !rsgetPatientClinicalEval.getString("whostage").equals("")) {

                            ////////// Insert WHO Stage ////////////////////
                            String WHOStage = rsgetPatientClinicalEval.getString("whostage").replaceAll("[^0-9]", "");
                            boolean checkdata = true;
                            obsWHOStage = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsWHOStage.setString(1, patient_id);
                            obsWHOStage.setString(3, lastEncounterId);
                            obsWHOStage.setString(2, "5356");
                            if (WHOStage.equals("1")) {
                                obsWHOStage.setString(4, "1204");
                            } else if (WHOStage.equals("2")) {
                                obsWHOStage.setString(4, "1205");
                            } else if (WHOStage.equals("3")) {
                                obsWHOStage.setString(4, "1206");
                            } else if (WHOStage.equals("4")) {
                                obsWHOStage.setString(4, "1207");
                            } else {
                                checkdata = false;
                            }

                            obsWHOStage.setString(5, null);
                            obsWHOStage.setString(6, null);
                            obsWHOStage.setString(7, null);
                            obsWHOStage.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsWHOStage.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            if (checkdata == true) {
                                obsWHOStage.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }

                        }

                        if (rsgetPatientClinicalEval.getString("tbstatus") != null && !rsgetPatientClinicalEval.getString("tbstatus").equals("")) {

                            ////////// Insert TB Status ////////////////////
                            String TBStatus = rsgetPatientClinicalEval.getString("tbstatus").replaceAll("[^a-z A-Z-]", "");
                            boolean checkdata = true;
                            obsTBStatus = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsTBStatus.setString(1, patient_id);
                            obsTBStatus.setString(3, lastEncounterId);
                            obsTBStatus.setString(2, "1659");
                            if (TBStatus.equalsIgnoreCase("No Signs")) {
                                obsTBStatus.setString(4, "1660");
                            } else if (TBStatus.equalsIgnoreCase("Patient with Signs and Symptoms of TB and Referred for Evaluation")) {
                                obsTBStatus.setString(4, "142177");
                            } else if (TBStatus.equalsIgnoreCase("Currently On INH prophylaxis")) {
                                obsTBStatus.setString(4, "1662");
                            } else if (TBStatus.equalsIgnoreCase("Confirmed TB - Patient Has active TB")) {
                                obsTBStatus.setString(4, "1661");
                            } else {
                                checkdata = false;
                            }
                            obsTBStatus.setString(5, null);
                            obsTBStatus.setString(6, null);
                            obsTBStatus.setString(7, null);
                            obsTBStatus.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsTBStatus.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            if (checkdata == true) {
                                obsTBStatus.executeUpdate();
                            }

                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("otheroi") != null && !rsgetPatientClinicalEval.getString("otheroi").equals("")) {
                            ////////// Insert Other OIs/Other OI problems ////////////////////
                            String OtherOI = rsgetPatientClinicalEval.getString("otheroi");
                            OtherOI = OtherOI.replaceAll(", ", ",");
                            OtherOI = OtherOI.replaceAll(", ,", ",");
                            String OtherOIarray[] = OtherOI.split(",");
                            boolean checkdata = true;
                            for (String OtherOIarrays : OtherOIarray) {
                                //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsOtherOI = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsOtherOI.setString(1, patient_id);
                                obsOtherOI.setString(2, "160170");
                                obsOtherOI.setString(3, lastEncounterId);
                                if (OtherOIarrays.equalsIgnoreCase("Herpes Zoster")) {
                                    obsOtherOI.setString(4, "117543");
                                } else if (OtherOIarrays.equalsIgnoreCase("Pneumonia")) {
                                    obsOtherOI.setString(4, "114100");
                                } else if (OtherOIarrays.equalsIgnoreCase("Dementia/Encephalitis")) {
                                    obsOtherOI.setString(4, "119566");
                                } else if (OtherOIarrays.equalsIgnoreCase("Thrush Oral/Vaginal")) {
                                    obsOtherOI.setString(4, "5334");
                                } else if (OtherOIarrays.equalsIgnoreCase("Fever")) {
                                    obsOtherOI.setString(4, "140238");
                                } else if (OtherOIarrays.equalsIgnoreCase("Cough")) {
                                    obsOtherOI.setString(4, "143264");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsOtherOI.setString(5, null);
                                    obsOtherOI.setString(6, null);
                                    obsOtherOI.setString(7, null);
                                    obsOtherOI.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsOtherOI.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsOtherOI.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientClinicalEval.getString("sideeff") != null && !rsgetPatientClinicalEval.getString("sideeff").equals("")) {

                            ////////// Insert Other OIs/Other OI problems Noted Side Effects ////////////////////
                            String SideEffects = rsgetPatientClinicalEval.getString("sideeff");
                            SideEffects = SideEffects.replaceAll(", ", ",");
                            SideEffects = SideEffects.replaceAll(", ,", ",");
                            String SideEffectsarray[] = SideEffects.split(",");
                            boolean checkdata = true;
                            for (String SideEffectsarrays : SideEffectsarray) {
                                //DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                                // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                                obsSideEffects = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                        + "  `person_id`,\n"
                                        + "  `concept_id`,\n"
                                        + "  `encounter_id`,\n"
                                        + "  `location_id`,\n"
                                        + "  `value_coded`,\n"
                                        + "  `value_datetime`,\n"
                                        + "  `value_numeric`,\n"
                                        + "  `value_text`,\n"
                                        + "  `creator`,\n"
                                        + "  `obs_datetime`,\n"
                                        + "  `date_created`,\n"
                                        + "  `uuid`\n"
                                        + ") \n"
                                        + "VALUES\n"
                                        + "  (\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '8',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    '1',\n"
                                        + "    ?,\n"
                                        + "    ?,\n"
                                        + "    UUID())");
                                obsSideEffects.setString(1, patient_id);
                                obsSideEffects.setString(2, "159935");
                                obsSideEffects.setString(3, lastEncounterId);
                                if (SideEffectsarrays.equalsIgnoreCase("Nausea/Vomitting")) {
                                    obsSideEffects.setString(4, "133473");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Headache")) {
                                    obsSideEffects.setString(4, "139084");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Insomnia/bad dreams")) {
                                    obsSideEffects.setString(4, "119566");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Fatigue/weakness")) {
                                    obsSideEffects.setString(4, "5226");
                                } else if (SideEffectsarrays.equalsIgnoreCase("PV Bleding/discharge")) {
                                    obsSideEffects.setString(4, "512");
                                } else if (SideEffectsarrays.equalsIgnoreCase("FAT changes")) {
                                    obsSideEffects.setString(4, "165052");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Anemia")) {
                                    obsSideEffects.setString(4, "121629");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Drainage of liquor")) {
                                    obsSideEffects.setString(4, "165053");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Stevens Johnson Syndrome")) {
                                    obsSideEffects.setString(4, "125886");
                                } else if (SideEffectsarrays.equalsIgnoreCase("Hyperglycemia")) {
                                    obsSideEffects.setString(4, "138291");
                                } else {
                                    checkdata = false;
                                }
                                if (checkdata == true) {
                                    obsSideEffects.setString(5, null);
                                    obsSideEffects.setString(6, null);
                                    obsSideEffects.setString(7, null);
                                    obsSideEffects.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsSideEffects.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                    obsSideEffects.executeUpdate();
                                    DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                    DatabaseHandler.getInstance().getApinConnection().commit();
                                }
                            }
                        }

                        if (rsgetPatientClinicalEval.getString("regadherencea") != null && !rsgetPatientClinicalEval.getString("regadherencea").equals("")) {

                            ////////// Insert Reg Adherence ////////////////////
                            String Regadherencea = rsgetPatientClinicalEval.getString("regadherencea").replaceAll("[^a-zA-Z]", "");
                            boolean checkdata = true;

                            obsRegadherencea = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsRegadherencea.setString(1, patient_id);
                            obsRegadherencea.setString(2, "165290");
                            obsRegadherencea.setString(3, lastEncounterId);
                            if (Regadherencea.equalsIgnoreCase("Good")) {
                                obsRegadherencea.setString(4, "165287");
                            } else if (Regadherencea.equalsIgnoreCase("Fair")) {
                                obsRegadherencea.setString(4, "165289");
                            } else if (Regadherencea.equalsIgnoreCase("Poor")) {
                                obsRegadherencea.setString(4, "165288");
                            } else {
                                checkdata = false;
                            }
                            if (checkdata == true) {
                                obsRegadherencea.setString(5, null);
                                obsRegadherencea.setString(6, null);
                                obsRegadherencea.setString(7, null);
                                obsRegadherencea.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsRegadherencea.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsRegadherencea.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                        }

                        if (rsgetPatientClinicalEval.getString("cotrimadherencea") != null && !rsgetPatientClinicalEval.getString("cotrimadherencea").equals("")) {

                            ////////// Insert Cotrim Adherence ////////////////////
                            String Cotrimadherencea = rsgetPatientClinicalEval.getString("cotrimadherencea").replaceAll("[^a-zA-Z]", "");
                            boolean checkdata = true;

                            obsCotrimadherencea = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsCotrimadherencea.setString(1, patient_id);
                            obsCotrimadherencea.setString(2, "161652");
                            obsCotrimadherencea.setString(3, lastEncounterId);
                            if (Cotrimadherencea.equalsIgnoreCase("Good")) {
                                obsCotrimadherencea.setString(4, "165287");
                            } else if (Cotrimadherencea.equalsIgnoreCase("Fair")) {
                                obsCotrimadherencea.setString(4, "165289");
                            } else if (Cotrimadherencea.equalsIgnoreCase("Poor")) {
                                obsCotrimadherencea.setString(4, "165288");
                            } else {
                                checkdata = false;
                            }
                            if (checkdata == true) {
                                obsCotrimadherencea.setString(5, null);
                                obsCotrimadherencea.setString(6, null);
                                obsCotrimadherencea.setString(7, null);
                                obsCotrimadherencea.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsCotrimadherencea.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsCotrimadherencea.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                        }

                        if (rsgetPatientClinicalEval.getString("inhadherencea") != null && !rsgetPatientClinicalEval.getString("inhadherencea").equals("")) {

                            ////////// Insert INH Adherence ////////////////////
                            String Inhadherencea = rsgetPatientClinicalEval.getString("inhadherencea").replaceAll("[^a-zA-Z]", "");
                            boolean checkdata = true;

                            obsInhadherencea = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsInhadherencea.setString(1, patient_id);
                            obsInhadherencea.setString(2, "161653");
                            obsInhadherencea.setString(3, lastEncounterId);
                            if (Inhadherencea.equalsIgnoreCase("Good")) {
                                obsInhadherencea.setString(4, "165287");
                            } else if (Inhadherencea.equalsIgnoreCase("Fair")) {
                                obsInhadherencea.setString(4, "165289");
                            } else if (Inhadherencea.equalsIgnoreCase("Poor")) {
                                obsInhadherencea.setString(4, "165288");
                            } else {
                                checkdata = false;
                            }
                            if (checkdata == true) {
                                obsInhadherencea.setString(5, null);
                                obsInhadherencea.setString(6, null);
                                obsInhadherencea.setString(7, null);
                                obsInhadherencea.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                                obsInhadherencea.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                                obsInhadherencea.executeUpdate();
                                DatabaseHandler.getInstance().getMRSDBConnection().commit();
                                DatabaseHandler.getInstance().getApinConnection().commit();
                            }
                        }

                        if (rsgetPatientClinicalEval.getString("nextapptdate") != null && !rsgetPatientClinicalEval.getString("nextapptdate").equals("")) {

                            ////////// Insert nextapptdate ////////////////////
                            boolean checkdata = true;

                            obsNextapptdate = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                                    + "  `person_id`,\n"
                                    + "  `concept_id`,\n"
                                    + "  `encounter_id`,\n"
                                    + "  `location_id`,\n"
                                    + "  `value_coded`,\n"
                                    + "  `value_datetime`,\n"
                                    + "  `value_numeric`,\n"
                                    + "  `value_text`,\n"
                                    + "  `creator`,\n"
                                    + "  `obs_datetime`,\n"
                                    + "  `date_created`,\n"
                                    + "  `uuid`\n"
                                    + ") \n"
                                    + "VALUES\n"
                                    + "  (\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '8',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    '1',\n"
                                    + "    ?,\n"
                                    + "    ?,\n"
                                    + "    UUID())");
                            obsNextapptdate.setString(1, patient_id);
                            obsNextapptdate.setString(2, "5096");
                            obsNextapptdate.setString(3, lastEncounterId);
                            obsNextapptdate.setString(4, null);
                            obsNextapptdate.setString(5, rsgetPatientClinicalEval.getString("nextapptdate"));
                            obsNextapptdate.setString(6, null);
                            obsNextapptdate.setString(7, null);
                            obsNextapptdate.setString(8, rsgetPatientClinicalEval.getString("visitdate"));
                            obsNextapptdate.setString(9, rsgetPatientClinicalEval.getString("visitdate"));
                            obsNextapptdate.executeUpdate();
                            DatabaseHandler.getInstance().getMRSDBConnection().commit();
                            DatabaseHandler.getInstance().getApinConnection().commit();

                        }

                        if (rsgetPatientClinicalEval.getString("visitdate") != null && !rsgetPatientClinicalEval.getString("visitdate").equalsIgnoreCase("") && rsgetPatientClinicalEval.getString("clinician") != null && !rsgetPatientClinicalEval.getString("clinician").equalsIgnoreCase("")) {
                            this.migrateAdultICESignature(patient_id, lastEncounterId, rsgetPatientClinicalEval.getString("visitdate"), rsgetPatientClinicalEval.getString("clinician"), rsgetPatientClinicalEval.getString("visitdate"), rsgetPatientClinicalEval.getString("clinician"), rsgetPatientClinicalEval.getString("visitdate"), rsgetPatientClinicalEval.getString("clinician"));
                        }

                        clinicalevalcount++;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                clinicalEncounterLabel.setText(String.valueOf(clinicalevalcount));
                            }
                        });
                        new LoadSettings().updateMigrationField("clinicaleval", "migrated", "clinicevalid", rsgetPatientClinicalEval.getString("myid"));
                    }
                    loadingImageLabel.setVisible(false);
                    completedLabelEncounter.setVisible(true);
                } catch (Exception ex) {
                    System.out.println("=======================================ERROR============");
                    System.out.println("");
                    System.out.println("=======================================ERROREND============");
                    LogToFile.writeLog(ex.getStackTrace().toString());
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    public Concept conceptIdsforAdultICE(String concept) {
        Concept conceptObs = new Concept();
        try {
            getConceptId = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id` \n"
                    + "FROM\n"
                    + "  `concept_adultice`\n"
                    + "  WHERE `cisproconcepts` = ?");
            getConceptId.setString(1, concept);
            rsgetconcept = getConceptId.executeQuery();
            if (rsgetconcept.next()) {
                conceptObs.setConceptId(rsgetconcept.getString("concept_id"));
                return conceptObs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conceptObs;
    }

    public void insertObs(
            String person_id,
            String concept_id,
            String encounter_id,
            String value_coded,
            String value_datetime,
            String value_numeric,
            String value_text,
            String date
    ) {
        try {

            obs = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                    + "  `person_id`,\n"
                    + "  `concept_id`,\n"
                    + "  `encounter_id`,\n"
                    + "  `location_id`,\n"
                    + "  `value_coded`,\n"
                    + "  `value_datetime`,\n"
                    + "  `value_numeric`,\n"
                    + "  `value_text`,\n"
                    + "  `creator`,\n"
                    + "  `obs_datetime`,\n"
                    + "  `date_created`,\n"
                    + "  `uuid`\n"
                    + ") \n"
                    + "VALUES\n"
                    + "  (\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    '8',\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    '1',\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    UUID()\n"
                    + "  )\n"
                    + "");
            obs.setString(1, person_id);
            obs.setString(2, concept_id);
            obs.setString(3, encounter_id);
            obs.setString(4, value_coded);
            obs.setString(5, value_datetime);
            obs.setString(6, value_numeric);
            obs.setString(7, value_text);
            obs.setString(8, date);
            obs.setString(9, date);
            obs.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertNewandReturnVisitsOBS(String person_id, String encounter_id, String date) {
        try {

            checkVisitInOBS = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
                    + "  `person_id` \n"
                    + "FROM\n"
                    + "  `obs`\n"
                    + "  WHERE `person_id` = ? ");
            checkVisitInOBS.setString(1, person_id);
            rscheckVisitInOBS = checkVisitInOBS.executeQuery();
            if (rscheckVisitInOBS.next()) {
                newvisitobs = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    '164181',\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    '160530',\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                newvisitobs.setString(1, person_id);
                newvisitobs.setString(2, encounter_id);
                newvisitobs.setString(3, date);
                newvisitobs.setString(4, date);
                newvisitobs.executeUpdate();
            } else {
                newvisitobs = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
                        + "  `person_id`,\n"
                        + "  `concept_id`,\n"
                        + "  `encounter_id`,\n"
                        + "  `location_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `creator`,\n"
                        + "  `obs_datetime`,\n"
                        + "  `date_created`,\n"
                        + "  `uuid`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    '164181',\n"
                        + "    ?,\n"
                        + "    '8',\n"
                        + "    '164180',\n"
                        + "    '1',\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    UUID()\n"
                        + "  )\n"
                        + "");
                newvisitobs.setString(1, person_id);
                newvisitobs.setString(2, encounter_id);
                newvisitobs.setString(3, date);
                newvisitobs.setString(4, date);
                newvisitobs.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String insertPersonTable(String name) {
        try {

            // DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            // DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            // inserting person 
            personTableSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                    "INSERT INTO `person` ("
                    + "`gender`,\n"
                    + "  `birthdate`,\n"
                    + "  `birthdate_estimated`,\n"
                    + "  `creator`,\n"
                    + "  `date_created`,\n"
                    + "`uuid`)                                                                "
                    + "VALUES(?,\n"
                    + "    NOW(),\n"
                    + "    ?,\n"
                    + "    '1',\n"
                    + "    NOW(),\n"
                    + " UUID())", Statement.RETURN_GENERATED_KEYS
            );
            // inserting person name
            personNameTableSignature = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                    "INSERT INTO `person_name`("
                    + " `preferred`,\n"
                    + "  `person_id`,\n"
                    + "  `given_name`,\n"
                    + "  `family_name`,\n"
                    + "  `creator`,\n"
                    + "  `date_created`,\n"
                    + "`uuid`)                                                                "
                    + "VALUES(?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + " NOW(),\n"
                    + "UUID())"
            );

            //inserting into person name table for signature
            personTableSignature.setString(1, "M");
            personTableSignature.setString(2, "0");
            personTableSignature.executeUpdate();

            personRsSignature = personTableSignature.getGeneratedKeys();
            if (personRsSignature.next()) {
                last_person_id_signature = personRsSignature.getInt(1);
            }

            //inserting into person table
            personNameTableSignature.setString(1, "1");
            personNameTableSignature.setString(2, String.valueOf(last_person_id_signature));
            personNameTableSignature.setString(3, name);
            personNameTableSignature.setString(4, name);
            personNameTableSignature.setString(5, "1");
            personNameTableSignature.executeUpdate();

            //DatabaseHandler.getInstance().getMRSDBConnection().commit();
            // DatabaseHandler.getInstance().getApinConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(last_person_id_signature);
    }

    public boolean checkOBSDuplicate(String pepid) {
        try {
            getPatientId = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                        "
                    + "SELECT \n"
                    + "   identifier \n"
                    + "  FROM\n"
                    + "    `patient_identifier` \n"
                    + "  WHERE `identifier` = ? AND identifier_type='4'");
            getPatientId.setString(1, pepid);
            rspatientId = getPatientId.executeQuery();
            if (rspatientId.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void demographics() {

        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {
            button.setVisible(false);
            loadingImageLabel.setVisible(true);
            // moduleProcessing.setText("Uploading patient basic information");
            try {

                DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
                DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                // inserting person 
                personTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                        "INSERT INTO `person` ("
                        + "`gender`,\n"
                        + "  `birthdate`,\n"
                        + "  `birthdate_estimated`,\n"
                        + "  `creator`,\n"
                        + "  `date_created`,\n"
                        + "`uuid`)                                                                "
                        + "VALUES(?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + " UUID())", Statement.RETURN_GENERATED_KEYS
                );
                // inserting person name
                personNameTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(
                        "INSERT INTO `person_name`("
                        + " `preferred`,\n"
                        + "  `person_id`,\n"
                        + "  `given_name`,\n"
                        + "  `family_name`,\n"
                        + "  `creator`,\n"
                        + "  `date_created`,\n"
                        + "`uuid`)                                                                "
                        + "VALUES(?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + " ?,\n"
                        + "UUID())"
                );

                patientTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(""
                        + "INSERT INTO `patient`("
                        + "`patient_id`,"
                        + "`creator`,"
                        + "`date_created`,"
                        + "`allergy_status`)"
                        + "VALUES(?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?)");

                patientIdentifierTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(""
                        + "INSERT INTO `patient_identifier`("
                        + "`patient_id`,"
                        + "`identifier`,"
                        + "`identifier_type`,"
                        + "`preferred`,"
                        + "`location_id`,"
                        + "`creator`,"
                        + "`date_created`,"
                        + "`uuid`)"
                        + "VALUES(?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "UUID())");
                preonline = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT `idpatient` AS myid , p.* FROM `patient` AS p where migrated = 0 ");
                rsonline = preonline.executeQuery();
                while (rsonline.next()) {

                    if (!this.checkDuplicate(rsonline.getString("pepid"))) {
                        //inserting into person name table
                        if (rsonline.getString("sex").equals("male")
                                || rsonline.getString("sex").equals("Male")
                                || rsonline.getString("sex").equals("m")
                                || rsonline.getString("sex").equals("M")) {
                            personTable.setString(1, "M");
                        }
                        if (rsonline.getString("sex").equals("female")
                                || rsonline.getString("sex").equals("Female")
                                || rsonline.getString("sex").equals("f")
                                || rsonline.getString("sex").equals("F")) {
                            personTable.setString(1, "F");
                        }
                        personTable.setString(2, rsonline.getString("dob"));
                        personTable.setString(3, "0");
                        personTable.setString(4, "1");
                        personTable.setString(5, rsonline.getString("created_on"));
                        personTable.executeUpdate();

                        personRs = personTable.getGeneratedKeys();
                        if (personRs.next()) {
                            last_person_id = personRs.getInt(1);
                        }

                        //inserting into person table
                        personNameTable.setString(1, "1");
                        personNameTable.setString(2, String.valueOf(last_person_id));
                        personNameTable.setString(3, rsonline.getString("othernames"));
                        personNameTable.setString(4, rsonline.getString("surname"));
                        personNameTable.setString(5, "1");
                        personNameTable.setString(6, rsonline.getString("created_on"));
                        personNameTable.executeUpdate();

                        //inserting patient
                        patientTable.setString(1, String.valueOf(last_person_id));
                        patientTable.setString(2, "1");
                        patientTable.setString(3, rsonline.getString("created_on"));
                        patientTable.setString(4, "Unknown");
                        patientTable.executeUpdate();

                        ///insert into patient identifeir table
                        patientIdentifierTable.setString(1, String.valueOf(last_person_id));
                        patientIdentifierTable.setString(2, rsonline.getString("pepid"));
                        patientIdentifierTable.setString(3, "4");
                        patientIdentifierTable.setString(4, "1");
                        patientIdentifierTable.setString(5, "1");
                        patientIdentifierTable.setString(6, "1");
                        patientIdentifierTable.setString(7, rsonline.getString("created_on"));
                        patientIdentifierTable.addBatch();

                        patientIdentifierTable.setString(1, String.valueOf(last_person_id));
                        patientIdentifierTable.setString(2, rsonline.getString("pepid"));
                        patientIdentifierTable.setString(3, "3");
                        patientIdentifierTable.setString(4, "0");
                        patientIdentifierTable.setString(5, "1");
                        patientIdentifierTable.setString(6, "1");
                        patientIdentifierTable.setString(7, rsonline.getString("created_on"));
                        patientIdentifierTable.addBatch();

                        patientIdentifierTable.executeBatch();

                        String enrollPatientInProgramTableSql = String
                                .format(
                                        " INSERT INTO `patient_program` ( "
                                        + "patient_id,"
                                        + "program_id,"
                                        + "date_enrolled,"
                                        + "location_id,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, '%s',%s, %s, '%s', UUID()) ",
                                        last_person_id,
                                        1,
                                        rsonline.getString("created_on"),
                                        1,
                                        1,
                                        rsonline.getString("created_on")
                                );

                        enrollPatientInProgramTableStm = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(enrollPatientInProgramTableSql);
                        enrollPatientInProgramTableStm.executeUpdate();
                        enrollPatientInProgramTableStm.addBatch();

                        DatabaseHandler.getInstance().getMRSDBConnection().commit();
                        DatabaseHandler.getInstance().getApinConnection().commit();
                    }
                    demographicscount++;
                    new LoadSettings().updateMigrationField("patient", "migrated", "idpatient", rsonline.getString("myid"));

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            patientBasicInfoLabel.setText(String.valueOf(demographicscount));
                        }
                    });

                }
                basicInfoMigrationStatus = 1;
                loadingImageLabel.setVisible(false);

                finishedDemographics();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean getDashboardRecords() {
        try {
            dashboardData = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                        "
                    + "SELECT  \n"
                    + "(SELECT COUNT(*) FROM `patient` where migrated = 0 ) AS patientsrec, \n"
                    + "(SELECT COUNT(*) FROM `patient` WHERE LENGTH(pepid) = 10  AND icfmigrated = 0) AS aicforms,\n"
                    + "(SELECT COUNT(*) FROM `patient` WHERE LENGTH(pepid) = 11 AND icfmigrated = 0) AS picforms ,\n"
                    + "(SELECT COUNT(*) FROM `laboratory` WHERE  migrated = 0) AS labrec,\n"
                    + "(SELECT COUNT(*) FROM `pharmacy` WHERE migrated = 0) AS pharmacyrec,\n"
                    + "(SELECT COUNT(*) FROM `clinicaleval` WHERE migrated = 0) AS clinicaleval");
            rsdashboardData = dashboardData.executeQuery();
            if (rsdashboardData.next()) {
                patientsTotal.setText(rsdashboardData.getString("patientsrec"));
                labTotal.setText(rsdashboardData.getString("labrec"));
                pedTotal.setText(rsdashboardData.getString("picforms"));
                adultTotal.setText(rsdashboardData.getString("aicforms"));
                pharmacyTotal.setText(rsdashboardData.getString("pharmacyrec"));
                clinicalTotal.setText(rsdashboardData.getString("clinicaleval"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @FXML
    private void demomenu(ActionEvent event) {
        new LoadSettings().resetMigrationField("pateint", "migrated");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void adulticfmenu(ActionEvent event) {
        new LoadSettings().altResetMigrationField("patient", "icfmigrated", "pepid", "10");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void pedicfmenu(ActionEvent event) {
        new LoadSettings().altResetMigrationField("patient", "icfmigrated", "pepid", "11");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void labmenu(ActionEvent event) {
        new LoadSettings().resetMigrationField("laboratory", "migrated");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void enmenu(ActionEvent event) {
        new LoadSettings().resetMigrationField("clinicaleval", "migrated");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void pharmacymenu(ActionEvent event) {
        new LoadSettings().resetMigrationField("pharmacy", "migrated");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void resetAllMenu(ActionEvent event) {
        new LoadSettings().resetMigrationField("patient", "migrated");
        new LoadSettings().altResetMigrationField("patient", "icfmigrated", "pepid", "10");
        new LoadSettings().altResetMigrationField("patient", "icfmigrated", "pepid", "11");
        new LoadSettings().resetMigrationField("laboratory", "migrated");
        new LoadSettings().resetMigrationField("clinicaleval", "migrated");
        new LoadSettings().resetMigrationField("pharmacy", "migrated");
        JOptionPane.showMessageDialog(null, "Task completed!", "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void closeStage(ActionEvent event) {
        exit(0);
    }

}
