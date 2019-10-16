/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class MigrateICFORMResult {

    private static PreparedStatement icResultStm;
    private static ResultSet icResult;
    private static String lastEncounterId;

    private PreparedStatement obs1;
    private Label countPlabel, completedPLabel, modulePProcessing;
    private TextArea errorlogTxt;
    private PreparedStatement getIcformTotal;
    private ResultSet getIcformotalResultset;
    private int pedicecount;
    private Button migratePICE;
    private ImageView loadingGIF;
    private String allerros = "";
    private int errorNum;
    private String WHOStage;
    private String columnConceptIdValue;
    private String columnName;
    private String incorrectvalue;
    private String pepid;
    ObservableList<HomepageController.Book> list = FXCollections.observableArrayList();

    public void load(Label countlabel, Label completedLabel, Button migrateICE, ImageView loadingLabel, Label moduleProcessing) {
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {
            if (FXMLDocumentController.basicInfoMigrationStatus == 0) {
                JOptionPane.showMessageDialog(null, "Please Migrate Patient Basic Info first!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadingGIF = loadingLabel;
                loadingGIF.setVisible(true);
                migratePICE = migrateICE;
                migratePICE.setVisible(false);
                countPlabel = countlabel;
                modulePProcessing = moduleProcessing;
                // modulePProcessing.setText("Uploading patient pediatric initial clinical evaluation");
                int j = 1;
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
                int numResults = 1;
                try {
                    icResultStm = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT `idpatient` , `pepid`,\n"
                            + "  pfatheralive,\n"
                            + "  pfathername,\n"
                            + "  pfatheradd,\n"
                            + "  pmotheralive,\n"
                            + "  pmothername,\n"
                            + "  pmotheradd,\n"
                            + "  pcaregiversrelationshipstatus,\n"
                            + "  psiblings,\n"
                            + "  pdevassess,\n"
                            + "  pimmunecomplete,\n"
                            + "  prevarvexp,\n"
                            + "  prevfacilityname,\n"
                            + "ppreviousarvexp,\n"
                            + "drugallergies,\n"
                            + "pcurrmeds,\n"
                            + "petemp,\n"
                            + "pepulse,\n"
                            + "peweight,\n"
                            + "pedcircumf,\n"
                            + "pedsurface,\n"
                            + "pedmuac,\n"
                            + "pegenappearance,\n"
                            + "peskin,\n"
                            + "pecardiovascular, \n"
                            + "pedpeglands,\n"
                            + "peheadeye,\n"
                            + "pegenitalia,\n"
                            + "gerespiratory,\n"
                            + "geneurological,\n"
                            + "gementalstatus,\n"
                            + "peadditional,\n"
                            + "assessment,\n"
                            + "whostage,\n"
                            + "enrollin,\n"
                            + "planarvtherapy,\n"
                            + "pedadditional,\n"
                            + "  `symptomsreview`,\n"
                            + "  `enroldate`, \n"
                            + "  `dur_fever`,\n"
                            + "  `dur_weigthl`,\n"
                            + "  `dur_nausea`,\n"
                            + "  `dur_nsweats`,\n"
                            + "  `dur_cough`,\n"
                            + "  `dur_head`,\n"
                            + "  `dur_rash`,\n"
                            + "  `dur_itch`,\n"
                            + "  `dur_diarh`,\n"
                            + "  `dur_gendis`,\n"
                            + "  `dur_genitch`,\n"
                            + "  `dur_gensore`,\n"
                            + "  `dur_shortbreath`,\n"
                            + "  `dur_visimp`,\n"
                            + "  `dur_painswall`,\n"
                            + "  `dur_numb`,\n"
                            + "  `dur_otherpain`,\n"
                            + "  `dur_eardis`,\n"
                            + "  `dur_painmict`,\n"
                            + "  `dur_oralsores`,\n"
                            + "  `dur_irr`,\n"
                            + "  `dur_sleep`,\n"
                            + "  `dur_food`,\n"
                            + "  `dur_convul`\n"
                            + " FROM \n"
                            + "  patient  WHERE LENGTH(pepid) = 11  and  icfmigrated  = 0 ORDER BY `pepid` \n");
                    icResult = icResultStm.executeQuery();

                    getIcformTotal = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT COUNT(*) FROM patient WHERE LENGTH(pepid) = 11  ");
                    getIcformotalResultset = getIcformTotal.executeQuery();
                    getIcformotalResultset.next();
                    numResults = getIcformotalResultset.getInt(1);

                    while (icResult.next()) {
                        ResultSetMetaData rsmd = icResult.getMetaData();
                        int columnCount = rsmd.getColumnCount();

                        ///get patient_id from patient_identifier table
                        if (!StringUtils.isNullOrEmpty(icResult.getString("pepid")) && !StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) {
                            String patientId = Utility.getPatient(icResult.getString("pepid"));
                            lastEncounterId = Utility.loadpatientVisit(patientId, icResult.getString("enroldate"), "8", "20");

                            String[] excludedFields = new String[]{"dur_fever", "dur_weigthl", "dur_nausea", "dur_nsweats", "dur_cough", "dur_head", "dur_rash", "dur_itch", "dur_diarh", "dur_gendis",
                                "dur_genitch", "dur_gensore", "dur_shortbreath", "dur_visimp", "dur_painswall", "dur_numb", "dur_otherpain", "dur_eardis", "dur_painmict",
                                "dur_oralsores", "dur_irr", "dur_sleep", "dur_food", "dur_convul", "enroldate", "pdevassess"};

                            String[] PhyscalExamFields = new String[]{"pegenappearance", "peskin", "pecardiovascular", "pedpeglands", "peheadeye", "pegenitalia", "gerespiratory", "geneurological", "gementalstatus"};

                            String symptomtype = "";
                            int lastInsertedObsId = 0;
                            String columnConceptId1, valueConceptId1, columnConceptIdDataType1, columnConceptId2, valueConceptId2, columnConceptIdDataType2, columnConceptId3, valueConceptId3, columnConceptIdDataType3;
                            columnConceptId1 = valueConceptId1 = columnConceptIdDataType1 = columnConceptId2 = valueConceptId2 = columnConceptIdDataType2 = columnConceptId3 = valueConceptId3 = columnConceptIdDataType3 = null;
                            // The column count starts from 1
                            for (int i = 1; i <= columnCount; i++) {
                                String name = rsmd.getColumnName(i);
                                if (!StringUtils.isNullOrEmpty(icResult.getString(name)) && !name.equalsIgnoreCase("idpatient") && !name.equalsIgnoreCase("pepid") && !Arrays.asList(excludedFields).contains(name)) {
                                    ///get the concept_id from concepts using the tests in cispro table
                                    // String columnConceptId = this.conceptIdsforICFORM(name).getConceptId();
                                    if (name.equalsIgnoreCase("symptomsreview")) {
                                        if (!StringUtils.isNullOrEmpty(icResult.getString("symptomsreview"))) {
                                            symptomsreview = icResult.getString(name).trim().replaceAll(", $", "");
                                            if (!symptomsreview.equals("") && !symptomsreview.isEmpty() && !StringUtils.isNullOrEmpty(symptomsreview.replaceAll("^\\s+", "").replaceAll("\\s+$", "").trim())) {
                                                symptomsArray = symptomsreview.split(",");

                                                for (String symptomObs : symptomsArray) {
                                                    symptomObsTrimed = symptomObs.replaceAll("^\\s+", "").replaceAll("\\s+$", "").trim();
                                                    //insert SIGNS_AND_SYMPTOMS
                                                    columnConceptId1 = SIGNS_AND_SYMPTOMS;
                                                    valueConceptId1 = null;
                                                    columnConceptIdDataType1 = "0";
                                                    lastInsertedObsId = Utility.insertObsRow(columnConceptIdDataType1, patientId, columnConceptId1, valueConceptId1, lastEncounterId, icResult.getString("enroldate"), null);

                                                    //insert SIGN_SYMPTOM_NAME
                                                    columnConceptId2 = SIGN_SYMPTOM_NAME;
                                                    valueConceptId2 = Utility.conceptIdsforICFORM(symptomObsTrimed.trim()).getConceptId();
                                                    columnConceptIdDataType2 = "2";
                                                    Utility.insertObsRow(columnConceptIdDataType2, patientId, columnConceptId2, valueConceptId2, lastEncounterId, icResult.getString("enroldate"), String.valueOf(lastInsertedObsId));

                                                    columnConceptId3 = SIGN_SYMPTOM_DURATION;
                                                    if (symptomObsTrimed.trim().equals("Nause") || symptomObsTrimed.trim().equals("Nausea/Vomitting")) {
                                                        symptomTypeValue = "dur_nausea";
                                                    } else if (symptomObsTrimed.trim().equals("Fever/Chills") || symptomObsTrimed.trim().equals("Fever")) {
                                                        symptomTypeValue = "dur_fever";
                                                    } else {
                                                        symptomTypeValue = Utility.conceptIdsforICFORM(symptomObsTrimed.trim()).getConcepValue();
                                                    }
                                                    //System.out.println(symptomTypeValue + " | " + symptomObsTrimed.trim());
                                                    if (!StringUtils.isNullOrEmpty(symptomTypeValue)) {
                                                        String systomValueConverted = String.valueOf(Utility.stringtoDouble(icResult.getString(symptomTypeValue)));
                                                        String symptomTypeValueConverted = String.valueOf(Utility.stringtoDouble(icResult.getString(symptomTypeValue)));
                                                        valueConceptId3 = symptomTypeValueConverted;
                                                        columnConceptIdDataType3 = "1";
                                                        if (systomValueConverted.equals("997.5")) {
                                                            Utility.insertObsRowAlt(columnConceptIdDataType3, patientId, columnConceptId3, systomValueConverted, lastEncounterId, icResult.getString("enroldate"), String.valueOf(lastInsertedObsId), valueConceptId3);
                                                        } else {
                                                            Utility.insertObsRow(columnConceptIdDataType3, patientId, columnConceptId3, valueConceptId3, lastEncounterId, icResult.getString("enroldate"), String.valueOf(lastInsertedObsId));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else if (name.equalsIgnoreCase("whostage")) {
                                        WHOStage = icResult.getString("whostage");
                                        if (!StringUtils.isNullOrEmpty(WHOStage)) {
                                            if (WHOStage.equals("1")) {
                                                valueConceptId = "1204";
                                            } else if (WHOStage.equals("2")) {
                                                valueConceptId = "1205";
                                            } else if (WHOStage.equals("3")) {
                                                valueConceptId = "1206";
                                            } else if (WHOStage.equals("4")) {
                                                valueConceptId = "1207";
                                            } else if (WHOStage.equals("0")) {
                                                valueConceptId = "1204";
                                            }
                                            columnConceptId = Utility.conceptIdsforICFORM(name).getConceptId();
                                            columnConceptIdDataType = Utility.conceptIdsforICFORM(name).getConceptDataType();
                                            if (!StringUtils.isNullOrEmpty(columnConceptId)) {
                                                Utility.insertObsRow(columnConceptIdDataType, patientId, columnConceptId, valueConceptId, lastEncounterId, icResult.getString("enroldate"), null);
                                            }
                                        }
                                    } else if (name.equalsIgnoreCase("drugallergies") && !StringUtils.isNullOrEmpty(icResult.getString(name))) {
                                        valueConceptId = "162228";
                                        columnConceptId = Utility.conceptIdsforICFORM(name).getConceptId();
                                        columnConceptIdDataType = Utility.conceptIdsforICFORM(name).getConceptDataType();
                                        Utility.insertObsRow(columnConceptIdDataType, patientId, columnConceptId, valueConceptId, lastEncounterId, icResult.getString("enroldate"), null);
                                    } else {
                                        if (Arrays.asList(PhyscalExamFields).contains(name)) {
                                            String fields = icResult.getString(name).replaceAll(", ", ",");
                                            fields = fields.replaceAll(", ,", ",");
                                            String fieldsarray[] = fields.split(",");
                                            for (String allfields : fieldsarray) {
                                                columnConceptIdValue = Utility.conceptIdsforICFORM(allfields.trim().replaceAll(", ", ",")).getConceptId();
                                                if (StringUtils.isNullOrEmpty(columnConceptIdValue)) {
                                                    columnConceptId = Utility.conceptIdsforICFORM(name).getConceptId();
                                                    columnConceptIdDataType = "3";
                                                    valueConceptId = columnConceptIdValue;
                                                    Utility.insertObsRow(columnConceptIdDataType, patientId, columnConceptId, valueConceptId, lastEncounterId, icResult.getString("enroldate"), null);
                                                }
                                            }

                                        } else {
                                            //System.out.println("Not systomps:" + name);
                                            columnConceptId = Utility.conceptIdsforICFORM(name).getConceptId();
                                            columnConceptIdDataType = Utility.conceptIdsforICFORM(name).getConceptDataType();
                                          //  System.out.println(name);
                                            if (columnConceptIdDataType.equals("3") || columnConceptIdDataType.equals("1")) {
                                                value = icResult.getString(name);
                                                valueConceptId = value;
                                            } else {
                                                valueConceptId = Utility.conceptIdsforICFORM(icResult.getString(name)).getConceptId();
                                                valueConceptId = valueConceptId;
                                            }
                                            Utility.insertObsRow(columnConceptIdDataType, patientId, columnConceptId, valueConceptId, lastEncounterId, icResult.getString("enroldate"), null);

                                        }

                                    }

                                }

                            }

                        }

                        pedicecount++;
                        new LoadSettings().updateMigrationField("patient", "icfmigrated", "idpatient", icResult.getString("idpatient"));

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                countPlabel.setText(String.valueOf(pedicecount));
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

    public ObservableList<HomepageController.Book> clean(Label errorCount, ImageView loadingHomeImage2) {
        List<List<String>> listOfLists = Lists.newArrayList();
        listOfLists.add(Lists.newArrayList("RowId", "PepID", "visit date", "Column", "WrongValue", "ErrorText"));
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to clean this table?");
        if (resultn == JOptionPane.YES_OPTION) {
            loadingGIF = loadingHomeImage2;
            loadingGIF.setVisible(true);
            int j = 1;
            errorNum = 0;
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
            int numResults = 1;
            try {
                icResultStm = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT idpatient,enroldate, `pepid`,\n"
                        + "  pcaregiversrelationshipstatus,\n"
                        + "  psiblings,\n"
                        // + "  pdevassess,\n"
                        + "  pimmunecomplete,\n"
                        + "  prevarvexp,\n"
                        + "  prevfacilityname,\n"
                        + "ppreviousarvexp,\n"
                        // + "drugallergies,\n"
                        + "pcurrmeds,\n"
                        + "petemp,\n"
                        + "pepulse,\n"
                        + "peweight,\n"
                        + "pedcircumf,\n"
                        + "pedsurface,\n"
                        + "pedmuac,\n"
                        + "enroldate\n"
                        + "pegenappearance,\n"
                        + "peskin,\n"
                        + "pecardiovascular, \n"
                        + "pedpeglands,\n"
                        + "peheadeye,\n"
                        + "pegenitalia,\n"
                        + "gerespiratory,\n"
                        + "geneurological,\n"
                        + "gementalstatus,\n"
                        + "peadditional,\n"
                        + "assessment,\n"
                        + "whostage,\n"
                        + "enrollin,\n"
                        + "planarvtherapy,\n"
                        + "pedadditional,\n"
                        + "  `symptomsreview`,\n"
                        + "  `enroldate`, \n"
                        + "  `dur_fever`,\n"
                        + "  `dur_weigthl`,\n"
                        + "  `dur_nausea`,\n"
                        + "  `dur_nsweats`,\n"
                        + "  `dur_cough`,\n"
                        + "  `dur_head`,\n"
                        + "  `dur_rash`,\n"
                        + "  `dur_itch`,\n"
                        + "  `dur_diarh`,\n"
                        + "  `dur_gendis`,\n"
                        + "  `dur_genitch`,\n"
                        + "  `dur_gensore`,\n"
                        + "  `dur_shortbreath`,\n"
                        + "  `dur_visimp`,\n"
                        + "  `dur_painswall`,\n"
                        + "  `dur_numb`,\n"
                        + "  `dur_otherpain`,\n"
                        + "  `dur_eardis`,\n"
                        + "  `dur_painmict`,\n"
                        + "  `dur_oralsores`,\n"
                        + "  `dur_irr`,\n"
                        + "  `dur_sleep`,\n"
                        + "  `dur_food`,\n"
                        + "  `dur_convul`\n"
                        + " FROM \n"
                        + "  patient   ORDER BY `pepid`  \n");
                icResult = icResultStm.executeQuery();
                int currentRowCount = 0;
                int totalRowCount = 0;

                getIcformTotal = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT COUNT(*) FROM patient WHERE LENGTH(pepid) = 11  ");
                getIcformotalResultset = getIcformTotal.executeQuery();
                getIcformotalResultset.next();
                numResults = getIcformotalResultset.getInt(1);

                while (icResult.next()) {
                    ResultSetMetaData rsmd = icResult.getMetaData();
                    int columnCount = rsmd.getColumnCount();

                    if (StringUtils.isNullOrEmpty(icResult.getString("enroldate")) || icResult.getString("enroldate").equals("0000-00-00")) {
                        allerros += "Invalid value for column:  as row : " + icResult.getString("idpatient") + "column: enroldate " + "   Value: " + icResult.getString("enroldate") + " Pepid:" + icResult.getString("pepid") + "\n";
                        incorrectvalue = "IS NULL";
                        columnName = "enroldate";
                        pepid = icResult.getString("pepid");
                        allerros = "Invalid date value";
                        errorNum++;
                        list.add(new HomepageController.Book(icResult.getString("idpatient"), pepid, columnName, incorrectvalue, allerros));
                    }

                    ///get patient_id from patient_identifier table
                    if (!StringUtils.isNullOrEmpty(icResult.getString("pepid")) && !StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) {
                        String patientId = Utility.getPatient(icResult.getString("pepid"));

                        String symptomtype = "";
                        int lastInsertedObsId = 0;
                        String columnConceptId1, valueConceptId1, columnConceptIdDataType1, columnConceptId2, valueConceptId2, columnConceptIdDataType2, columnConceptId3, valueConceptId3, columnConceptIdDataType3;
                        columnConceptId1 = valueConceptId1 = columnConceptIdDataType1 = columnConceptId2 = valueConceptId2 = columnConceptIdDataType2 = columnConceptId3 = valueConceptId3 = columnConceptIdDataType3 = null;

                        String[] excludedFields = new String[]{"dur_fever", "dur_weigthl", "dur_nausea", "dur_nsweats", "dur_cough", "dur_head", "dur_rash", "dur_itch", "dur_diarh", "dur_gendis",
                            "dur_genitch", "dur_gensore", "dur_shortbreath", "dur_visimp", "dur_painswall", "dur_numb", "dur_otherpain", "dur_eardis", "dur_painmict",
                            "dur_oralsores", "dur_irr", "dur_sleep", "dur_food", "dur_convul", "enroldate", "idpatient", "whostage", "pdevassess"};

                        String[] PhyscalExamFields = new String[]{"pegenappearance", "peskin", "pecardiovascular", "pedpeglands", "peheadeye", "pegenitalia", "gerespiratory", "geneurological", "gementalstatus"};

                        Pattern pattern = Pattern.compile(
                                "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
                                + "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
                                + "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
                                + "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

                        // The column count starts from 1
                        for (int i = 1; i <= columnCount; i++) {
                            String name = rsmd.getColumnName(i);
                            if (!StringUtils.isNullOrEmpty(name) && !name.equalsIgnoreCase("pepid")) {

                                // checking numeric values
                                if (Arrays.asList(excludedFields).contains(name) && !name.equals("enroldate") && !StringUtils.isNullOrEmpty(icResult.getString(name))) {
                                    if (!pattern.matcher(icResult.getString(name.trim())).matches()) {
                                        incorrectvalue = icResult.getString(name);
                                        columnName = name;
                                        pepid = icResult.getString("pepid");
                                        allerros = "Invalid date value";
                                        errorNum++;
                                        list.add(new HomepageController.Book(icResult.getString("idpatient"), pepid, columnName, incorrectvalue, allerros));
                                        String visitDate = (StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) ? "-" : icResult.getString("enroldate");
                                        listOfLists.add(Lists.newArrayList(icResult.getString("idpatient"), visitDate, pepid, columnName, incorrectvalue, allerros));
                                    }
                                }
                                // checking numeric values
                                if (!Arrays.asList(excludedFields).contains(name) && !StringUtils.isNullOrEmpty(icResult.getString(name))) {

                                    String columnType = Utility.conceptIdsforICFORM(name).getConceptDataType();

                                    if (columnType.equals("1") && !StringUtils.isNullOrEmpty(icResult.getString(name))) {

                                        if (!pattern.matcher(icResult.getString(name.trim())).matches()) {
                                            incorrectvalue = icResult.getString(name);
                                            columnName = name;
                                            pepid = icResult.getString("pepid");
                                            allerros = "Invalid numeric value";
                                            errorNum++;
                                            list.add(new HomepageController.Book(icResult.getString("idpatient"), pepid, columnName, incorrectvalue, allerros));
                                            String visitDate = (StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) ? "-" : icResult.getString("enroldate");
                                            listOfLists.add(Lists.newArrayList(icResult.getString("idpatient"), visitDate, pepid, columnName, incorrectvalue, allerros));
                                        }
                                    }

                                    if (columnType.equals("2") && !StringUtils.isNullOrEmpty(icResult.getString(name).trim())) {

                                        if (Arrays.asList(PhyscalExamFields).contains(name)) {
                                            String fields = icResult.getString(name).replaceAll(", ", ",");
                                            fields = fields.replaceAll(", ,", ",");
                                            String fieldsarray[] = fields.split(",");
                                            for (String allfields : fieldsarray) {
                                                String innerfields = icResult.getString(name).replaceAll(", ", ",");
                                                innerfields = innerfields.replaceAll(", ,", ",");
                                                String innerfieldsrray[] = fields.split(",");

                                                if (innerfieldsrray.length > 1) {
                                                    for (String allinnerfields : innerfieldsrray) {
                                                        columnConceptId = Utility.conceptIdsforICFORM(allinnerfields.replaceAll(", ", ",")).getConceptId();
                                                        if (StringUtils.isNullOrEmpty(columnConceptId)) {
                                                            incorrectvalue = allinnerfields;
                                                            columnName = name;
                                                            pepid = icResult.getString("pepid");
                                                            allerros = "Invalid concept value";
                                                            errorNum++;
                                                            list.add(new HomepageController.Book(icResult.getString("idpatient"), pepid, columnName, incorrectvalue, allerros));
                                                            String visitDate = (StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) ? "-" : icResult.getString("enroldate");
                                                            listOfLists.add(Lists.newArrayList(icResult.getString("idpatient"), visitDate, pepid, columnName, incorrectvalue, allerros));
                                                        }
                                                    }
                                                } else {
                                                    columnConceptId = Utility.conceptIdsforICFORM(allfields.replaceAll(", ", ",")).getConceptId();
                                                    if (StringUtils.isNullOrEmpty(columnConceptId)) {
                                                        incorrectvalue = allfields;
                                                        columnName = name;
                                                        pepid = icResult.getString("pepid");
                                                        allerros = "Invalid concept value";
                                                        errorNum++;
                                                        list.add(new HomepageController.Book(icResult.getString("idpatient"), pepid, columnName, incorrectvalue, allerros));
                                                        String visitDate = (StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) ? "-" : icResult.getString("enroldate");
                                                        listOfLists.add(Lists.newArrayList(icResult.getString("idpatient"), visitDate, pepid, columnName, incorrectvalue, allerros));
                                                    }
                                                }

                                            }
                                        } else {
                                            columnConceptId = Utility.conceptIdsforICFORM(icResult.getString(name).trim().replaceAll(", ", ",")).getConceptId();
                                            if (StringUtils.isNullOrEmpty(columnConceptId)) {
                                                incorrectvalue = icResult.getString(name);
                                                columnName = name;
                                                pepid = icResult.getString("pepid");
                                                allerros = "Invalid concept value";
                                                errorNum++;
                                                list.add(new HomepageController.Book(icResult.getString("idpatient"), pepid, columnName, incorrectvalue, allerros));
                                                String visitDate = (StringUtils.isNullOrEmpty(icResult.getString("enroldate"))) ? "-" : icResult.getString("enroldate");
                                                listOfLists.add(Lists.newArrayList(icResult.getString("idpatient"), visitDate, pepid, columnName, incorrectvalue, allerros));
                                            }
                                        }
                                    }
                                }
                            }

                        }

                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            errorCount.setText(String.valueOf("You have " + errorNum + " unresolved errors"));
                        }
                    });
                    Errors.setUpdatableList(listOfLists);

                    pedicecount++;
                }
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

}
