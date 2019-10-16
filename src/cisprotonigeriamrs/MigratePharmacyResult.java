/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import static cisprotonigeriamrs.Utility.checkDuplicateVisitdate;
import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class MigratePharmacyResult {

    private PreparedStatement getPharmacyResult;
    private ResultSet pharmacyResult;
    private String lastEncounterId;
    private PreparedStatement getPharmacyTotalResult;
    private PreparedStatement getPharmacyTotal;
    private ResultSet getPharmacyTotalResultset;

    private Label countPlabel, completedPLabel, modulePProcessing;
    private Button migratePPharmacy;
    private ImageView loadingGIF, loadingHomeImage2;
    private int pharmacycount;
    private PreparedStatement getPharmacyResult1;
    private ResultSet pharmacyResult1;
    private PreparedStatement getPharmacyResult2;
    private ResultSet pharmacyResult2;
    private PreparedStatement getPharmacyResult3;
    private ResultSet pharmacyResult3;
    private Statement statement1;
    private Statement statement2;
    private Statement statement3;
    private ResultSet rs1;
    private Connection conn;
    private Statement stmtss;
    private String columnType;
    private PreparedStatement errorloghelper;
    private int errorNum;
    private String allerros;
    private String columnConceptId;
    private String valueConceptId;

    ObservableList<HomepageController.Book> list = FXCollections.observableArrayList();
    private String incorrectvalue;
    private String pepid;
    private String columnName;

//    public void load(Label countlabel, Label completedLabel, Button migratePharmacy, ImageView loadingLabel, Label moduleProcessing) {
//       // cleanPharmacy();
//        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
//        if (resultn == JOptionPane.YES_OPTION) {
//            if (FXMLDocumentController.basicInfoMigrationStatus == 0) {
//                JOptionPane.showMessageDialog(null, "Please Migrate Patient Basic Info first!", "Information", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                loadingGIF = loadingLabel;
//                loadingGIF.setVisible(true);
//                migratePPharmacy = migratePharmacy;
//                migratePPharmacy.setVisible(false);
//                countPlabel = countlabel;
//                modulePProcessing = moduleProcessing;
//                //modulePProcessing.setText("Uploading patient pediatric initial clinical evaluation");
//                int j = 0;
//                String symptomsreview = "";
//                String columnConceptId = "";
//                String valueConceptId = "";
//                String columnConceptIdDataType = "";
//                String SIGN_SYMPTOM_NAME = "1728";
//                String SIGNS_AND_SYMPTOMS = "1727";
//                String SIGN_SYMPTOM_DURATION = "1731";
//                String symptomTypeValue = "";
//                String symptomObsTrimed = "";
//                String value = "";
//                String[] symptomsArray = new String[0];
//                int lastInsertedObsId = 0;
//                int totalRowCount = 0;
//                int numResults = 1;
//
//                try {
//                    getPharmacyResult = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT pharmacyid, \n"
//                            + "  `pepid`,\n"
//                            + "  `visitdate`,\n"
//                            + "  'Human_immunodeficiency_virus_treatment',\n"
//                            + "  `drugname1`,\n"
//                            + "  `drugdose1`,\n"
//                            + "  `drugno1`,\n"
//                            + "  `frequency`,\n"
//                            + "   CAST(TRIM(regduration1) AS UNSIGNED) as regduration1,\n"
//                            + "  'Drug_prescription_for_Anti-TB_drugs',\n"
//                            + "  `tbdrug`,\n"
//                            + "  `tbqty`,\n"
//                            + "   CAST(TRIM(tbduration) AS UNSIGNED) as tbduration,\n"
//                            + "  'Drugs_FOR_OI_Prophylaxis',\n"
//                            + "  `oidrug`,\n"
//                            + "  `oiqty`,\n"
//                            + "  CAST(TRIM(oiduration) AS UNSIGNED) as oiduration,\n"
//                            + "  `pregyn`,\n"
//                            + "  `refillyn`,\n"
//                            + "  'ordered_date'\n"
//                            + "FROM\n"
//                            + "  `pharmacy` where migrated = 0   \n"
//                            + "GROUP BY `pharmacyid`,\n"
//                            + "  pepid,\n"
//                            + "  visitdate  ");
//                    pharmacyResult = getPharmacyResult.executeQuery();
//
//                    getPharmacyTotal = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT COUNT(*) FROM pharmacy");
//                    getPharmacyTotalResultset = getPharmacyTotal.executeQuery();
//                    getPharmacyTotalResultset.next();
//                    numResults = getPharmacyTotalResultset.getInt(1);
//
//                    while (pharmacyResult.next()) {
//                        if (!StringUtils.isNullOrEmpty(pharmacyResult.getString("pepid")) && !StringUtils.isNullOrEmpty(pharmacyResult.getString("visitdate"))) {
//                         //   System.out.print(pharmacyResult.getString("pepid"));
//                            ///get patient_id from patient_identifier table
//                            String patient_id = Utility.getPatient(pharmacyResult.getString("pepid"));
//                            ///get the concept_id from concepts using the tests in cispro table
//                            lastEncounterId = Utility.loadpatientVisit(patient_id, pharmacyResult.getString("visitdate"), "13", "27");
//
//                            String[] excludedFields = new String[]{"visitdate"};
//                            String[] excludedParentFields = new String[]{"Human_immunodeficiency_virus_treatment", "Drug_prescription_for_Anti-TB_drugs", "Drugs_FOR_OI_Prophylaxis"};
//
//                            ResultSetMetaData rsmd = pharmacyResult.getMetaData();
//                            int columnCount = rsmd.getColumnCount();
//                            for (int i = 1; i <= columnCount; i++) {
//                                String name = rsmd.getColumnName(i);
//                                if (!StringUtils.isNullOrEmpty(name) && !name.equalsIgnoreCase("pepid") && !Arrays.asList(excludedFields).contains(name)) {
//
//                                    columnConceptId = Utility.conceptIdsforPharmacyFORM(name).getConceptId();
//
//                                    columnConceptIdDataType = Utility.conceptIdsforPharmacyFORM(name).getConceptDataType();
//                                    if (!StringUtils.isNullOrEmpty(columnConceptIdDataType)) {
//                                        if (columnConceptIdDataType.equals("1") || columnConceptIdDataType.equals("3")) {
//                                            valueConceptId = pharmacyResult.getString(name);
//                                            valueConceptId = valueConceptId;
//                                        } else {
//                                            valueConceptId = Utility.conceptIdsforPharmacyFORM(pharmacyResult.getString(name)).getConceptId();
//                                            valueConceptId = valueConceptId;
//                                        }
//                                    }
//
//                                    if (name.equals("ordered_date")) {
//                                        Utility.insertObsRowAlt("8", patient_id, "164989", pharmacyResult.getString("visitdate"), lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
//                                        Utility.insertObsRowAlt("8", patient_id, "164989", pharmacyResult.getString("visitdate"), lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
//
//                                    } else if (name.equals("refillyn")) {
//                                        if (!StringUtils.isNullOrEmpty(pharmacyResult.getString(name))) {
//                                            valueConceptId = (pharmacyResult.getString("refillyn").equalsIgnoreCase("Yes")) ? "165773" : "165662";
//                                            valueConceptId = valueConceptId;
//                                            Utility.insertObsRowAlt("10", patient_id, "165774", valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
//                                        }
//                                    } else if (name.equals("pregyn")) {
//                                        if (!StringUtils.isNullOrEmpty(pharmacyResult.getString(name))) {
//                                            valueConceptId = (pharmacyResult.getString("pregyn").equalsIgnoreCase("Yes")) ? "165048" : "165047";
//                                            if(!StringUtils.isNullOrEmpty(valueConceptId)){
//                                                valueConceptId = valueConceptId;
//                                                Utility.insertObsRowAlt("10", patient_id, "165050", valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
//                                            }
//                                          
//                                        }
//                                    } else {
//                                        if (!StringUtils.isNullOrEmpty(valueConceptId)) {
//                                            if (Arrays.asList(excludedParentFields).contains(name)) {
//                                                lastInsertedObsId = Utility.insertObsRowAlt("11", patient_id, columnConceptId, null, lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
//                                            } else {
//                                                if (name.equals("regduration1") || name.equals("tbduration") || name.equals("oiduration")) {
//                                                    if (!StringUtils.isNullOrEmpty(name)) {
//                                                        double systomValueConverted = Utility.stringtoDouble(pharmacyResult.getString(name)); 
//                                                        int valueConceptIdint = (int)systomValueConverted;
//                                                        valueConceptId = String.valueOf(valueConceptIdint * 31);
//                                                        valueConceptId = valueConceptId;
//                                                        Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, columnConceptId, valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), String.valueOf(lastInsertedObsId), null);
//                                                    }
//                                                } else {
//                                                    Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, columnConceptId, valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), String.valueOf(lastInsertedObsId), null);
//
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//
//                        pharmacycount++;
//                        String pharmacyId = pharmacyResult.getString("pharmacyid");
//
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                countPlabel.setText(String.valueOf(pharmacycount));
//                                new LoadSettings().updateMigrationField("pharmacy", "migrated", "pharmacyid",pharmacyId );
//                            }
//                        });
//                     
//                    }
//                    completedPLabel = completedLabel;
//                    completedPLabel.setVisible(true);
//                    loadingGIF.setVisible(false);
//
//                } catch (Exception ex) {
//                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
    
     public void load(Label countlabel, Label completedLabel, Button migratePharmacy, ImageView loadingLabel, Label moduleProcessing) {
       // cleanPharmacy();
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {
            if (FXMLDocumentController.basicInfoMigrationStatus == 0) {
                JOptionPane.showMessageDialog(null, "Please Migrate Patient Basic Info first!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadingGIF = loadingLabel;
                loadingGIF.setVisible(true);
                migratePPharmacy = migratePharmacy;
                migratePPharmacy.setVisible(false);
                countPlabel = countlabel;
                modulePProcessing = moduleProcessing;
                //modulePProcessing.setText("Uploading patient pediatric initial clinical evaluation");
                int j = 0;
                String symptomsreview = "";
                String columnConceptId = "";
                String valueConceptId = "";
                String columnConceptIdDataType = "";
                String SIGN_SYMPTOM_NAME = "1728";
                String SIGNS_AND_SYMPTOMS = "1727";
                String SIGN_SYMPTOM_DURATION = "1731";
                String symptomTypeValue = "";
                String symptomObsTrimed = "";
                String value = "";
                String[] symptomsArray = new String[0];
                int lastInsertedObsId = 0;
                int totalRowCount = 0;
                int numResults = 1;

                try {
                    getPharmacyResult = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT pharmacyid, \n"
                            + "  `pepid`,\n"
                            + "  `visitdate`,\n"
                            + "  'Human_immunodeficiency_virus_treatment',\n"
                            + "  CASE\n" +
                            "WHEN drugname1  =   '	ABC/3TC/LPV/r/' THEN  162200\n" +
                            "WHEN drugname1  =   '3TC/AZT/ABC' THEN  817\n" +
                            "WHEN drugname1  =   '3TC/AZT/ATV/r/DTG' THEN  164511\n" +
                            "WHEN drugname1  =   '3TC/AZT/DRV' THEN  165698\n" +
                            "WHEN drugname1  =   '3TC/AZT/DTG' THEN  165682\n" +
                            "WHEN drugname1  =   '3TC/AZT/EFV/ABC' THEN  817\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/3TC/AZT' THEN  817\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/ATV/r' THEN  164511\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/DTG' THEN  165691\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/LPV/r' THEN  162561\n" +
                            "WHEN drugname1  =   '3TC/AZT/RTV' THEN  165698\n" +
                            "WHEN drugname1  =   '3TC/TDF/DTG' THEN  165681\n" +
                            "WHEN drugname1  =   'ABC/3TC/3TC/AZT' THEN  817\n" +
                            "WHEN drugname1  =   'ABC/3TC/ATV/r' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/AZT' THEN  817\n" +
                            "WHEN drugname1  =   'ABC/3TC/DDL' THEN  165526\n" +
                            "WHEN drugname1  =   'ABC/3TC/DRV' THEN  165698\n" +
                            "WHEN drugname1  =   'ABC/3TC/DRV/r' THEN  165698\n" +
                            "WHEN drugname1  =   'ABC/3TC/DTG' THEN  165691\n" +
                            "WHEN drugname1  =   'ABC/3TC/EFV' THEN  162563\n" +
                            "WHEN drugname1  =   'ABC/3TC/EFV/ABC' THEN  162563\n" +
                            "WHEN drugname1  =   'ABC/3TC/LPV/r' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/LPV/r/' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/LPV/r/DTG' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/LPV/r/LPV/r' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/LVP/r' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/NVP' THEN  162199\n" +
                            "WHEN drugname1  =   'ABC/3TC/RTV' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/AZT/LPV/r' THEN  162200\n" +
                            "WHEN drugname1  =   'AZT/3TC/ABC' THEN  817\n" +
                            "WHEN drugname1  =   'AZT/3TC/ATV/r' THEN  164511\n" +
                            "WHEN drugname1  =   'AZT/3TC/ATV/r/' THEN  164511\n" +
                            "WHEN drugname1  =   'AZT/3TC/DRV/r' THEN  165698\n" +
                            "WHEN drugname1  =   'AZT/3TC/DTG' THEN  165691\n" +
                            "WHEN drugname1  =   'AZT/3TC/EFV' THEN  160124\n" +
                            "WHEN drugname1  =   'AZT/3TC/LPV/r' THEN  162561\n" +
                            "WHEN drugname1  =   'AZT/3TC/NVP' THEN  1652\n" +
                            "WHEN drugname1  =   'AZT/3TC/TDF' THEN  165682\n" +
                            "WHEN drugname1  =   'CHLORPHENIRAMINE MALEATE' THEN  1652\n" +
                            "WHEN drugname1  =   'd4T/3TC/EFV' THEN  792\n" +
                            "WHEN drugname1  =   'd4T/3TC/NVP' THEN  792\n" +
                            "WHEN drugname1  =   'DDL/3TC/NVP' THEN  792\n" +
                            "WHEN drugname1  =   'DRV' THEN  165698\n" +
                            "WHEN drugname1  =   'DTG' THEN  165681\n" +
                            "WHEN drugname1  =   'EFV/3TC/AZT' THEN  160124\n" +
                            "WHEN drugname1  =   'EFV/ABC/3TC' THEN  162563\n" +
                            "WHEN drugname1  =   'ETR' THEN  160124\n" +
                            "WHEN drugname1  =   'LPV/r/ABC/3TC' THEN  162200\n" +
                            "WHEN drugname1  =   'syp NVP' THEN  80586\n" +
                            "WHEN drugname1  =   'TDF/3TC' THEN  161364\n" +
                            "WHEN drugname1  =   'TDF/3TC/3TC/AZT' THEN  165681\n" +
                            "WHEN drugname1  =   'TDF/3TC/ABC' THEN  161364\n" +
                            "WHEN drugname1  =   'TDF/3TC/ABC/3TC' THEN  161364\n" +
                            "WHEN drugname1  =   'TDF/3TC/ATV/r' THEN  164512\n" +
                            "WHEN drugname1  =   'TDF/3TC/ATV/r/DTG' THEN  164512\n" +
                            "WHEN drugname1  =   'TDF/3TC/DRV/r' THEN  165698\n" +
                            "WHEN drugname1  =   'TDF/3TC/DTG' THEN  165681\n" +
                            "WHEN drugname1  =   'TDF/3TC/DTG/' THEN  165681\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV' THEN  164505\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/ABC/3TC/LPV/r' THEN  162201\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/DTG' THEN  165681\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/LPV/r' THEN  162201\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/NVP' THEN  164505\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/TDF/3TC/LPV/r' THEN  164505\n" +
                            "WHEN drugname1  =   'TDF/3TC/LPV/r' THEN  162201\n" +
                            "WHEN drugname1  =   'TDF/3TC/LPV/r/DTG' THEN  162201\n" +
                            "WHEN drugname1  =   'TDF/3TC/LPV/r/NVP' THEN  162201\n" +
                            "WHEN drugname1  =   'TDF/3TC/NVP' THEN  162565\n" +
                            "WHEN drugname1  =   'TDF/3TC/RAL' THEN  162565\n" +
                            "WHEN drugname1  =   'TDF/FTC/ATV/r' THEN  165524\n" +
                            "WHEN drugname1  =   'TDF/FTC/EFV' THEN  104565\n" +
                            "WHEN drugname1  =   'TDF/FTC/NVP' THEN  164854\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV' THEN  164505\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP' THEN  161364\n" +
                            "WHEN drugname1  =   'TDF/3TC' THEN  161364\n" +
                            "WHEN drugname1  =   '3TC/AZT' THEN  630\n" +
                            "WHEN drugname1  =   'EFV' THEN  103166\n" +
                            "WHEN drugname1  =   'AZT' THEN  75523\n" +
                            "WHEN drugname1  =   'ATV/r' THEN  159809\n" +
                            "WHEN drugname1  =   'LPV/r' THEN  794\n" +
                            "WHEN drugname1  =   'DRV' THEN  74258\n" +
                            "WHEN drugname1  =   'RTV' THEN  83412\n" +
                            "WHEN drugname1  =   'DRV/r' THEN  162796\n" +
                            "WHEN drugname1  =   'NVP' THEN  80586\n" +
                            "WHEN drugname1  =   'ABC' THEN  70056\n" +
                            "WHEN drugname1  =   'RTV' THEN  154378\n" +
                            "WHEN drugname1  =   'DTC' THEN  165631\n" +
                            "WHEN drugname1  =   'FDC (3TC/AZT/NVP)' THEN  164505\n" +
                            "WHEN drugname1  =   'ABC/3TC' THEN  630\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP' THEN  1652\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/NVP' THEN  1652\n" +
                            "WHEN drugname1  =   'AZT/3TC/NVP' THEN  1652\n" +
                            "WHEN drugname1  =   '3TC/AZT' THEN  165690\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/NVP' THEN  164854\n" +
                            "WHEN drugname1  =   'ABC/3TC/ATV/r' THEN  165691\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/3TC/AZT' THEN  165693\n" +
                            "WHEN drugname1  =   'ABC/3TC/LPV/r' THEN  162200\n" +
                            "WHEN drugname1  =   'ABC/3TC/EFV' THEN  162563\n" +
                            "WHEN drugname1  =   'TDF/3TC/AZT/ATV/r' THEN  164512\n" +
                            "WHEN drugname1  =   'TDF/3TC/ATV/r' THEN  164512\n" +
                            "WHEN drugname1  =   '3TC/AZT/NVP/LPV/r' THEN  162561\n" +
                            "WHEN drugname1  =   'EFV/ATV/r' THEN  164511\n" +
                            "WHEN drugname1  =   'EFV' THEN  162200\n" +
                            "WHEN drugname1  =   'NVP' THEN  165695\n" +
                            "WHEN drugname1  =   'TDF/3TC' THEN  165696\n" +
                            "WHEN drugname1  =   'TDF/3TC/LPV/r' THEN  162201\n" +
                            "WHEN drugname1  =   '3TC/ZDV/NVP' THEN  162559\n" +
                            "WHEN drugname1  =   '3TC/AZT/EFV' THEN  160124\n" +
                            "WHEN drugname1  =   'TDF/FTC/EFV' THEN  104565\n" +
                            "WHEN drugname1  =   'ABC/3TC/AZT' THEN  817\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/ABC/3TC/LPV/r' THEN  164505\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV/3TC/ZDV/NVP' THEN  164505\n" +
                            "WHEN drugname1  =   'TDF/3TC/EFV' THEN  164505\n" +
                            "ELSE 164505\n" +
                            "END AS drugname1,\n"
                            +"'165720' AS TRGRoup,\n" +
                            "'165709' AS TRGRoupVal,\n" +
                            "'165708' AS currentRegimentLine,\n" +
                            "CASE\n" +
                            "WHEN drugname1 LIKE '%LPV/r%' THEN 164506\n" +
                            "WHEN drugname1 LIKE '%APV/r%' THEN 164513\n" +
                            "WHEN drugname1 LIKE '%DRV/r%' THEN 164513\n" +
                            "ELSE 164506 \n" +
                            "END AS currentRegimentLineValue,\n" +
                            "CASE \n" +
                            "WHEN drugname1 LIKE '%LPV/r%' THEN 164506\n" +
                            "WHEN drugname1 LIKE '%APV/r%' THEN 164513\n" +
                            "WHEN drugname1 LIKE '%DRV/r%' THEN 164513\n" +
                            "ELSE 164506 \n" +
                            "END AS regmenLine, "
                            + "  `drugdose1`,\n"
                            + "  `drugno1`,\n"
                            + "  `frequency`,\n"
                            + "   CAST(TRIM(regduration1) AS UNSIGNED) as regduration1,\n"
                            + "  'Drug_prescription_for_Anti-TB_drugs',\n"
                            + "  `tbdrug`,\n"
                            + "  `tbqty`,\n"
                            + "   CAST(TRIM(tbduration) AS UNSIGNED) as tbduration,\n"
                            + "  'Drugs_FOR_OI_Prophylaxis',\n"
                            + "  `oidrug`,\n"
                            + "  `oiqty`,\n"
                            + "  CAST(TRIM(oiduration) AS UNSIGNED) as oiduration,\n"
                            + "  `pregyn`,\n"
                            + "  `refillyn`,\n"
                            + "  'ordered_date'\n"
                            + "FROM\n"
                            + "  `pharmacy` where migrated = 0   \n"
                            + "GROUP BY `pharmacyid`,\n"
                            + "  pepid,\n"
                            + "  visitdate  ");
                    pharmacyResult = getPharmacyResult.executeQuery();

                    getPharmacyTotal = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT COUNT(*) FROM pharmacy");
                    getPharmacyTotalResultset = getPharmacyTotal.executeQuery();
                    getPharmacyTotalResultset.next();
                    numResults = getPharmacyTotalResultset.getInt(1);

                    while (pharmacyResult.next()) {
                        if (!StringUtils.isNullOrEmpty(pharmacyResult.getString("pepid")) && !StringUtils.isNullOrEmpty(pharmacyResult.getString("visitdate"))) {
                         //   System.out.print(pharmacyResult.getString("pepid"));
                            ///get patient_id from patient_identifier table
                            String patient_id = Utility.getPatient(pharmacyResult.getString("pepid"));
                            ///get the concept_id from concepts using the tests in cispro table
                            lastEncounterId = Utility.loadpatientVisit(patient_id, pharmacyResult.getString("visitdate"), "13", "27");

                            String[] excludedFields = new String[]{"visitdate"};
                            String[] excludedParentFields = new String[]{"Human_immunodeficiency_virus_treatment", "Drug_prescription_for_Anti-TB_drugs", "Drugs_FOR_OI_Prophylaxis","drugname1"};

                            ResultSetMetaData rsmd = pharmacyResult.getMetaData();
                            int columnCount = rsmd.getColumnCount();
                            for (int i = 1; i <= columnCount; i++) {
                                String name = rsmd.getColumnName(i);
                                if (!StringUtils.isNullOrEmpty(name) && !name.equalsIgnoreCase("pepid") && !Arrays.asList(excludedFields).contains(name)) {

                                    columnConceptId = Utility.conceptIdsforPharmacyFORM(name).getConceptId();

                                    columnConceptIdDataType = Utility.conceptIdsforPharmacyFORM(name).getConceptDataType();
                                    if (!StringUtils.isNullOrEmpty(columnConceptIdDataType)) {
                                        if (columnConceptIdDataType.equals("1") || columnConceptIdDataType.equals("3")) {
                                            valueConceptId = pharmacyResult.getString(name);
                                            valueConceptId = valueConceptId;
                                        } else {
                                            valueConceptId = Utility.conceptIdsforPharmacyFORM(pharmacyResult.getString(name)).getConceptId();
                                            valueConceptId = valueConceptId;
                                        }
                                    }
                                     if (name.equals("drugname1")) {
                                         //Treatment age group
                                        Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, pharmacyResult.getString("TRGRoup"), pharmacyResult.getString("TRGRoupVal"), lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
                                       
                                         //Current Regimen Line
                                        Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, pharmacyResult.getString("currentRegimentLine"), pharmacyResult.getString("currentRegimentLineValue"), lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
                                        
                                        //Adult 1st line ARV regimen
                                        Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, pharmacyResult.getString("regmenLine"), pharmacyResult.getString("drugname1"), lastEncounterId, pharmacyResult.getString("visitdate"), String.valueOf(lastInsertedObsId), null);
                                        
                                        //regimen
                                        Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, columnConceptId, pharmacyResult.getString("drugname1"), lastEncounterId, pharmacyResult.getString("visitdate"), String.valueOf(lastInsertedObsId), null);
                                    }
                                    else if (name.equals("ordered_date")) {
                                        Utility.insertObsRowAlt("8", patient_id, "164989", pharmacyResult.getString("visitdate"), lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
                                        Utility.insertObsRowAlt("8", patient_id, "164989", pharmacyResult.getString("visitdate"), lastEncounterId, pharmacyResult.getString("visitdate"), null, null);

                                    } else if (name.equals("refillyn")) {
                                        if (!StringUtils.isNullOrEmpty(pharmacyResult.getString(name))) {
                                            valueConceptId = (pharmacyResult.getString("refillyn").equalsIgnoreCase("Yes")) ? "165773" : "165662";
                                            valueConceptId = valueConceptId;
                                            Utility.insertObsRowAlt("10", patient_id, "165774", valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
                                        }
                                    } else if (name.equals("pregyn")) {
                                        if (!StringUtils.isNullOrEmpty(pharmacyResult.getString(name))) {
                                            valueConceptId = (pharmacyResult.getString("pregyn").equalsIgnoreCase("Yes")) ? "165048" : "165047";
                                            if(!StringUtils.isNullOrEmpty(valueConceptId)){
                                                valueConceptId = valueConceptId;
                                                Utility.insertObsRowAlt("10", patient_id, "165050", valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
                                            }
                                          
                                        }
                                    } else {
                                        if (!StringUtils.isNullOrEmpty(valueConceptId)) {
                                            if (Arrays.asList(excludedParentFields).contains(name)) {
                                                lastInsertedObsId = Utility.insertObsRowAlt("11", patient_id, columnConceptId, null, lastEncounterId, pharmacyResult.getString("visitdate"), null, null);
                                            } else {
                                                if (name.equals("regduration1") || name.equals("tbduration") || name.equals("oiduration")) {
                                                    if (!StringUtils.isNullOrEmpty(name)) {
                                                        double systomValueConverted = Utility.stringtoDouble(pharmacyResult.getString(name)); 
                                                        int valueConceptIdint = (int)systomValueConverted;
                                                        valueConceptId = String.valueOf(valueConceptIdint * 31);
                                                        valueConceptId = valueConceptId;
                                                        Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, columnConceptId, valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), String.valueOf(lastInsertedObsId), null);
                                                    }
                                                } else {
                                                    Utility.insertObsRowAlt(columnConceptIdDataType, patient_id, columnConceptId, valueConceptId, lastEncounterId, pharmacyResult.getString("visitdate"), String.valueOf(lastInsertedObsId), null);

                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }

                        pharmacycount++;
                        String pharmacyId = pharmacyResult.getString("pharmacyid");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                countPlabel.setText(String.valueOf(pharmacycount));
                                new LoadSettings().updateMigrationField("pharmacy", "migrated", "pharmacyid",pharmacyId );
                            }
                        });
                     
                    }
                    completedPLabel = completedLabel;
                    completedPLabel.setVisible(true);
                    loadingGIF.setVisible(false);

                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void cleanPharmacy() {

        try {

            //Disable auto-commit mode
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            String sql1 = "UPDATE pharmacy\n"
                    + "SET regduration1 = 1\n"
                    + "WHERE regduration1 = '1/12%'\n"
                    + "OR\n"
                    + "regduration1 = 0.25\n"
                    + "OR\n"
                    + "regduration1 = 0.5\n"
                    + "OR\n"
                    + "regduration1 = 0.75\n"
                    + "OR\n"
                    + "regduration1 BETWEEN 12 AND 33\n"
                    + "OR\n"
                    + "regduration1 = '1%'\n"
                    + "OR\n"
                    + "regduration1 = 'I%'\n"
                    + "OR\n"
                    + "regduration1 = '1/52' \n"
                    + "OR\n"
                    + "regduration1 = '2/52'\n"
                    + "OR\n"
                    + "regduration1 = '3/52'\n"
                    + "OR\n"
                    + "regduration1 = '4/52'\n"
                    + "OR\n"
                    + "regduration1 = '1/12%'\n"
                    + "OR\n"
                    + "regduration1 = '.'\n"
                    + "OR\n"
                    + "regduration1 = '%1/12%'\n"
                    + "OR\n"
                    + "regduration1 = '1m%'\n"
                    + "OR\n"
                    + "regduration1 = '1 m%'\n"
                    + "OR\n"
                    + "regduration1 = '1n%'\n"
                    + "OR\n"
                    + "regduration1 = '1w'\n"
                    + "OR\n"
                    + "regduration1 = '1 w'\n"
                    + "OR\n"
                    + "regduration1 = '2 w%'\n"
                    + "OR\n"
                    + "regduration1 = '2w%'\n"
                    + "OR\n"
                    + "regduration1 = '3w%'\n"
                    + "OR\n"
                    + "regduration1 = 'Iw%'\n"
                    + "OR\n"
                    + "regduration1 = '5w%'\n"
                    + "OR\n"
                    + "regduration1 = '%1MO%'\n"
                    + "OR\n"
//                    + "regduration1 = '1mth%'\n" 
//                    + "OR\n"
//                    + "regduration1 = '7 d%'\n"                    
//                    + "OR\n"
                    + "regduration1 = '1 Month%';";
         //   PreparedStatement pstmt1 = DatabaseHandler.getInstance().getApinConnection().prepareStatement(sql1);

           // pstmt1.executeUpdate();
          //  DatabaseHandler.getInstance().getApinConnection().commit();

            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            String sql_stmt2 = "UPDATE pharmacy\n"
                    + "SET regduration1 = 2\n"
                    + "WHERE regduration1 = ''\n"
                    + "or\n"
                    + "regduration1 = '2M%'\n"
                    + "or\n" + "regduration1 = '2MONTHS%'\n"
                    + "or\n"
                    + "regduration1 = '2 Months%'\n"
                    + "or\n"
                    + "regduration1 IS NULL\n"
                    + "or\n"
                    + "regduration1 = '2 M%'\n"
                    + "or\n"
                    + "regduration1 = '2.5'\n"
                    + "or\n"
                    + "regduration1 between 45 AND 60\n"
                    + "or\n"
                    + "regduration1 = '2/12'\n"
                    + "or\n"
                    + "regduration1 = '22/12%'\n"
                    + "or\n"
                    + "regduration1 = '2/13%'\n"
                    + "or\n"
                    + "regduration1 = '2016%'\n"
                    + "or\n"
                    + "regduration1 = '2n%'\n"
                    + "or\n"
                    + "regduration1 = '2O%'\n"
                    + "or\n"
                    + "regduration1 = '2COTC%'\n"
                    + "or\n"
                    + "regduration1 = '2j'\n"
                    + "or\n"
                    + "regduration1 = '2T%'\n"
                    + "or\n"
                    + "regduration1 = 60\n"
                    + "or\n"
                    + "regduration1 = '6w%'\n"
                    + "or\n"
                    + "regduration1 = '6k%'\n"
                    + "or\n"
                    + "regduration1 = '6O%';";
            PreparedStatement pstmt2 = DatabaseHandler.getInstance().getApinConnection().prepareStatement(sql_stmt2);
            pstmt2.executeUpdate();
            DatabaseHandler.getInstance().getApinConnectionAlt().commit();

            DatabaseHandler.getInstance().getApinConnectionAlt().setAutoCommit(false);

            String sql_stmt3 = "UPDATE pharmacy\n"
                    + "SET regduration1 = 3\n"
                    + "WHERE regduration1 > 60 AND regduration1 < 120\n"
                    + "or regduration1 = '3m%'\n"
                    + "or regduration1 = '3 m%'\n"
                    + "or regduration1 = '3MONTHS%'\n"  
                     + "or regduration1 = ' 3 MONTH%'\n"
                    + "or regduration1 = '3/12%';";
            PreparedStatement pstmt3 = DatabaseHandler.getInstance().getApinConnection().prepareStatement(sql_stmt3);
            pstmt3.executeUpdate();
            DatabaseHandler.getInstance().getApinConnection().commit();

            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            String sql_stmt4 = "UPDATE pharmacy\n"
                    + "SET regduration1 = 3\n"
                    + "WHERE regduration1 > 60 AND regduration1 < 120\n"
                    + "or regduration1 = '3m%'\n"
                    + "or regduration1 = '3 m%'\n"
                    + "or regduration1 = ' 3 MONTH%'\n"
                    + "or regduration1 = '3/12%';";
            PreparedStatement pstmt4 = DatabaseHandler.getInstance().getApinConnectionAlt().prepareStatement(sql_stmt4);
            pstmt4.executeUpdate();
            DatabaseHandler.getInstance().getApinConnectionAlt().commit();
            JOptionPane.showMessageDialog(null, "Pharmacy database cleaned press ok to continue", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public ObservableList<HomepageController.Book> cleanPharmacyRecors(Label errorCount, ImageView loadingHomeImagep2) {
        loadingHomeImage2 = loadingHomeImagep2;  
        List<List<String>> listOfLists = Lists.newArrayList();
        listOfLists.add(Lists.newArrayList("RowId", "PepID", "visit date", "Column", "WrongValue", "ErrorText"));

        // cleanPharmacy();
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {
            loadingHomeImage2.setVisible(true);

            int j = 0;
            errorNum = 0;
            allerros = "";
            int totalRowCount = 0;
            int numResults = 1;
            Pattern pattern = Pattern.compile(
                    "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
                    + "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
                    + "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
                    + "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

            try {
                getPharmacyResult = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT pharmacyid, \n"
                        + "  `pepid`,\n"
                        + "  `visitdate`,\n"
                        + "  'Human_immunodeficiency_virus_treatment',\n"
                        + "  `drugname1`,\n"
                        + "  `drugdose1`,\n"
                        + "  `drugno1`,\n"
                        + "  `frequency`,\n"
                        + "  `regduration1`,\n"
                        + "  'Drug_prescription_for_Anti-TB_drugs',\n"
                        + "  `tbdrug`,\n"
                        + "  `tbqty`,\n"
                        + "  `tbduration`,\n"
                        + "  'Drugs_FOR_OI_Prophylaxis',\n"
                        + "  `oidrug`,\n"
                        + "  `oiqty`,\n"
                        + "  `oiduration`,\n"
                        + "  `pregyn`,\n"
                        + "  `refillyn`,\n"
                        + "  'ordered_date'\n"
                        + "FROM\n"
                        + "  `pharmacy` \n"
                        + "GROUP BY `pharmacyid`,\n"
                        + "  pepid,\n"
                        + "  visitdate  ");
                pharmacyResult = getPharmacyResult.executeQuery();

                getPharmacyTotal = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT COUNT(*) FROM pharmacy");
                getPharmacyTotalResultset = getPharmacyTotal.executeQuery();
                getPharmacyTotalResultset.next();
                numResults = getPharmacyTotalResultset.getInt(1);

                while (pharmacyResult.next()) {
                    if (!StringUtils.isNullOrEmpty(pharmacyResult.getString("pepid")) && !StringUtils.isNullOrEmpty(pharmacyResult.getString("visitdate"))) {

                        ///get patient_id from patient_identifier table
                        String[] excludedFields = new String[]{"visitdate"};
                        String[] excludedParentFields = new String[]{"Human_immunodeficiency_virus_treatment", "Drug_prescription_for_Anti-TB_drugs", "Drugs_FOR_OI_Prophylaxis"};
                        String[] numericFields = new String[]{"oiqty", "oiduration", "tbqty", "regduration1", "tbduration"};

                        ResultSetMetaData rsmd = pharmacyResult.getMetaData();
                        int columnCount = rsmd.getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            String name = rsmd.getColumnName(i);
                            if (!StringUtils.isNullOrEmpty(name) && !name.equalsIgnoreCase("pepid") && !name.equalsIgnoreCase("pharmacyid") && !Arrays.asList(excludedFields).contains(name)) {

                                if (!StringUtils.isNullOrEmpty(pharmacyResult.getString(name)) && !Arrays.asList(numericFields).contains(name) && !Arrays.asList(excludedParentFields).contains(name) && !name.equals("ordered_date")) {
                                    columnConceptId = Utility.conceptIdsforPharmacyFORM(pharmacyResult.getString(name).trim().replaceAll(", ", ",")).getConceptId();
                                    valueConceptId = Utility.conceptIdsforPharmacyFORM(pharmacyResult.getString(name).trim().replaceAll(", ", ",")).getConceptDataType();
                                    if (StringUtils.isNullOrEmpty(columnConceptId)) {
                                        incorrectvalue = pharmacyResult.getString(name);
                                        columnName = name;
                                        pepid = pharmacyResult.getString("pepid");
                                        allerros = "Invalid concept value";
                                        errorNum++;
                                        list.add(new HomepageController.Book(pharmacyResult.getString("pharmacyid"), pepid, columnName, incorrectvalue, allerros));
                                        String visitDate = (StringUtils.isNullOrEmpty(pharmacyResult.getString("visitdate"))) ? "-"  :  pharmacyResult.getString("visitdate");
                                        listOfLists.add(Lists.newArrayList(pharmacyResult.getString("pharmacyid"),visitDate, pepid, columnName, incorrectvalue, allerros));
                                    }
                                } else if (!StringUtils.isNullOrEmpty(pharmacyResult.getString(name)) && Arrays.asList(numericFields).contains(name)) {
                                    if (!pattern.matcher(pharmacyResult.getString(name.trim())).matches()) {
                                        incorrectvalue = pharmacyResult.getString(name);
                                        columnName = name;
                                        pepid = pharmacyResult.getString("pepid");
                                        allerros = "Invalid numeric value";
                                        errorNum++;
                                        list.add(new HomepageController.Book(pharmacyResult.getString("pharmacyid"), pepid, columnName, incorrectvalue, allerros));
                                        String visitDate = (StringUtils.isNullOrEmpty(pharmacyResult.getString("visitdate"))) ? "-"  :  pharmacyResult.getString("visitdate");
                                        listOfLists.add(Lists.newArrayList(pharmacyResult.getString("pharmacyid"),visitDate, pepid, columnName, incorrectvalue, allerros));
                                    }
                                }

                            }
                        }

                    }

                    //   pharmacycount++;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // errorlog.setText(allerros);
                            errorCount.setText(String.valueOf("You have " + errorNum + " unresolved errors"));
                        }
                    });
                    Errors.setUpdatableList(listOfLists);

                }

            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    private static class icResult {

        public icResult() {
        }
    }

}
