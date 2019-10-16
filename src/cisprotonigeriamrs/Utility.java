/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class Utility {

    private static int lastID;
    private static PreparedStatement errorObs;
    private static ResultSet errorObsTableRs1;
    private static PreparedStatement obs1;
    private static PreparedStatement patientencounterProviderTable;
    private static PreparedStatement patientencounterTable;
    private static ResultSet patientencounterTableRs;
    private static PreparedStatement getpatientVisitId;
    private static ResultSet rsgetpatientVisitId;
    private static VisitObs visitObs;
    private static PreparedStatement patientVisitTable;
    private static ResultSet patientVisitTableRs;
    private static String last_visit_id;
    private static PreparedStatement checkduplicateVisitdate;
    private static ResultSet rsvisitdatecheck;
    private static ResultSet rsencounterdatecheck;
    private static PreparedStatement checkPatientIdentifierTable;
    private static PreparedStatement getICConceptId;
    private static ResultSet ICrsgetconcept;
    private static PreparedStatement getopenMRSdatatype;
    private static ResultSet rsgetopenMRSdatatype;
    private static ResultSet rs2;
    private static PreparedStatement getPatientId;
    private static ResultSet rspatientId;
    private static PreparedStatement personTableSignature;
    private static PreparedStatement personNameTableSignature;
    private static ResultSet personRsSignature;
    private static int last_person_id_signature;
    private static PreparedStatement checkVisitInOBS;
    private static ResultSet rscheckVisitInOBS;
    private static PreparedStatement newvisitobs;
    private static ResultSet obs1TableRs;

    public Utility() {

    }

    public static double stringtoDouble(String valuetoConvert) {

        try {
            String[] strArray = new String[0];
            float newvalue;
            if (StringUtils.isNullOrEmpty(valuetoConvert)) {
                return 0.0;
            }
            strArray = valuetoConvert.split("/");
            if (strArray.length > 1) {
                float value1 = Float.valueOf(strArray[0]);
                float value2 = Float.valueOf(strArray[1]);
                newvalue = value1 / value2;
            } else {
                newvalue = Float.valueOf(valuetoConvert);
            }
            return Double.valueOf(newvalue);
        } catch (NumberFormatException e) {
            return 2;
        }
    }

    public static String checkNullValueNumeric(String value) {
        try {
            if (value.equals("") || value.equals(null)) {
                value = "0";
                return value;
            }
        } catch (Exception ex) {
            return "0";
        }
        return value;
    }

    public boolean containNumbersOnly(String source) {
        boolean result = false;
        Pattern pattern = Pattern.compile("[0-9]+.[0-9]+"); //correct pattern for both float and integer.
        //  Pattern pattern = Pattern.compile("\\d+.\\d+"); //correct pattern for both float and integer.
        return result = pattern.matcher(source).matches();
    }

    public static String cleanValueNumeric(String value_numeric) {
        String value = value_numeric.trim();
        value = value_numeric.replaceAll("[^0-9.]", "");
        value = value_numeric.replace("..", ".");
        return value;
    }

    public static int insertObsRow(String encounterDataType, String patientId, String concept, String conceptValue, String lastEncounterId, String visitdate, String obsGroupid) {
        if (!StringUtils.isNullOrEmpty(encounterDataType) && !StringUtils.isNullOrEmpty(concept)) {
            switch (encounterDataType) {
                case "1":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,
                            conceptValue,//value numeric type
                            null,
                            visitdate,
                            obsGroupid,
                            null
                    );

                    break;
                case "2":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            conceptValue, //valuecoded type
                            null,
                            null,
                            null,
                            visitdate,
                            obsGroupid,
                            null
                    );

                    break;
                case "3":
                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,
                            null,
                            conceptValue, //value text type
                            visitdate,
                            obsGroupid,
                            null
                    );

                    break;
                case "8":
                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            conceptValue,//datetime type
                            null,
                            null,
                            visitdate,
                            obsGroupid,
                            null
                    );

                    break;
                case "10":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            conceptValue, //valuecoded type
                            null,
                            null,
                            null,
                            visitdate,
                            obsGroupid,
                            null
                    );

                    break;
                case "0":
                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,//datetime type
                            null,
                            null,
                            visitdate,
                            null,
                            null
                    );
                    break;
            }
        }
        return lastID;
    }

    public static int insertObsRowAlt(String encounterDataType, String patientId, String concept, String conceptValue, String lastEncounterId, String visitdate, String obsGroupid, String systomValueConverted) {
        if (!StringUtils.isNullOrEmpty(encounterDataType) && !StringUtils.isNullOrEmpty(concept)) {

            switch (encounterDataType) {
                case "1":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,
                            conceptValue,//value numeric type
                            null,
                            visitdate,
                            obsGroupid,
                            systomValueConverted
                    );

                    break;
                case "2":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            conceptValue, //valuecoded type
                            null,
                            null,
                            null,
                            visitdate,
                            obsGroupid,
                            systomValueConverted
                    );

                    break;
                case "3":
                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,
                            null,
                            conceptValue, //value text type
                            visitdate,
                            obsGroupid,
                            systomValueConverted
                    );

                    break;
                case "8":
                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            conceptValue,//datetime type
                            null,
                            null,
                            visitdate,
                            obsGroupid,
                            systomValueConverted
                    );

                    break;
                case "10":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            conceptValue, //valuecoded type
                            null,
                            null,
                            null,
                            visitdate,
                            obsGroupid,
                            systomValueConverted
                    );

                    break;
                case "11":

                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,
                            conceptValue,//value numeric type
                            null,
                            visitdate,
                            obsGroupid,
                            systomValueConverted
                    );

                    break;
                case "0":
                    lastID = insertObs(
                            patientId,
                            encounterDataType,
                            concept,
                            lastEncounterId,
                            null,
                            null,//datetime type
                            null,
                            null,
                            visitdate,
                            null,
                            systomValueConverted
                    );
                    break;

            }
        }
        return lastID;
    }

    public static int insertObs(
            String person_id,
            String encounter_type,
            String concept_id,
            String encounter_id,
            String value_coded,
            String value_datetime,
            String value_numeric,
            String value_text,
            String date,
            String obsGroupId,
            String systomValueConverted
    ) {
        try {
            DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            String value_numeric_value;

            if (encounter_type.equals("1")) {
                value_numeric_value = cleanValueNumeric(checkNullValueNumeric(value_numeric));
            } else {
                value_numeric_value = null;
            }

            obs1 = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("INSERT INTO `obs` (\n"
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
                    + "    UUID()\n"
                    + "  )\n"
                    + "", Statement.RETURN_GENERATED_KEYS);
            //,Statement.RETURN_GENERATED_KEYS
            obs1.setString(1, person_id);
            obs1.setString(2, concept_id);
            obs1.setString(3, encounter_id);
            obs1.setString(4, value_coded);
            obs1.setString(5, value_datetime);
            obs1.setString(6, value_numeric_value);
            obs1.setString(7, value_text);
            obs1.setString(8, date);
            obs1.setString(9, date);
            obs1.setString(10, obsGroupId);
            obs1.executeUpdate();
            DatabaseHandler.getInstance().getMRSDBConnection().commit();
            DatabaseHandler.getInstance().getApinConnection().commit();

            obs1TableRs = obs1.getGeneratedKeys();
            if (obs1TableRs.next()) {
                lastID = obs1TableRs.getInt(1);
                lastID = lastID;
            }
         //   System.out.print(obs1.toString());

            if (!StringUtils.isNullOrEmpty(systomValueConverted)) {
                DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
                errorObs = DatabaseHandler.getInstance().getApinConnection().prepareStatement("INSERT INTO `error_log` (\n"
                        + " a `obs_id`,\n"
                        + "  `enconter_type`,\n"
                        + "  `patient_id`,\n"
                        + "  `pepid`,\n"
                        + "  `concept_id`,\n"
                        + "  `value_coded`,\n"
                        + "  `value_numric`,\n"
                        + "  `place_value`,\n"
                        + "  `created`,\n"
                        + "  `timestamp`\n"
                        + ") \n"
                        + "VALUES\n"
                        + "  (\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "    ?,\n"
                        + "   ?,\n"
                        + "    now(),\n"
                        + "    'timestamp'\n"
                        + "  )\n"
                        + "", Statement.RETURN_GENERATED_KEYS);
                errorObs.setString(1, String.valueOf(lastID));
                errorObs.setString(2, encounter_id);
                errorObs.setString(3, person_id);
                errorObs.setString(4, person_id);
                errorObs.setString(5, concept_id);
                errorObs.setString(6, value_coded);
                errorObs.setString(7, systomValueConverted);
                errorObs.setString(8, value_numeric);
                errorObs.executeUpdate();
                DatabaseHandler.getInstance().getApinConnection().commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastID;
    }

    public static void insertEcounterProvider(String encounterid, String date) {
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

    public static String loadpatientEnCounter(String patientid, String visitId, String encounterType, String form_id, String date) {

        try {
            // String form_id = (encounterType == "13") ? "27" : "21";
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
            patientencounterTable.setString(3, form_id);
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

    public String getPatientVisitId(String patientid, String visitdate) {
        try {
            getpatientVisitId = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                        SELECT \n"
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

    public static String loadpatientVisit(String patientid, String visitdate, String encounterType, String formId) {
        visitObs = new VisitObs();
        try {
            DatabaseHandler.getInstance().getMRSDBConnection().setAutoCommit(false);
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);

            visitObs = checkDuplicateVisitdate(encounterType, patientid, visitdate);

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
                        formId,
                        visitdate
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return visitObs.getEcounterId();
    }

    ////check for duplicate visit date
    public static VisitObs checkDuplicateVisitdate(String enconterType, String patientid, String datestart) {

        visitObs = new VisitObs();
        try {
            checkduplicateVisitdate = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(""
                    + "SELECT * FROM visit  AS v  JOIN `encounter` AS e  ON  e.`visit_id` =v.`visit_id` WHERE e.`encounter_type` = ? AND v.patient_id = ? AND v.date_started = ?");
            String datestartf = datestart + " 00:00:00";
            checkduplicateVisitdate.setString(1, enconterType);
            checkduplicateVisitdate.setString(2, patientid);
            checkduplicateVisitdate.setString(3, datestartf);
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
    public static String getVisitEncounter(String visitID) {

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
    /////Checking for duplicate patient insert

    public static boolean checkDuplicate(String patientid) {
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

    public static String getPatient(String pepid) {
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

    public static void insertNewandReturnVisitsOBS(String person_id, String encounter_id, String date) {
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

    public static String insertPersonTable(String name) {
        try {

            // omrs.setAutoCommit(false);
            // apinDB.setAutoCommit(false);
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

            //omrs.commit();
            // apinDB.commit();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(last_person_id_signature);
    }

    public static Concept conceptIdsforICFORM(String concept) {
        Concept conceptObs = new Concept();
        try {
            getICConceptId = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id`, conceptvalue \n"
                    + "FROM\n"
                    + "  `icform_concepts`\n"
                    + "  WHERE `cisproconcepts` = ?");
            getICConceptId.setString(1, concept);
            ICrsgetconcept = getICConceptId.executeQuery();
            if (ICrsgetconcept.next()) {
                conceptObs.setConceptId(ICrsgetconcept.getString("concept_id"));
                conceptObs.setConcepValue(ICrsgetconcept.getString("conceptvalue"));
                getopenMRSdatatype = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                               SELECT \n"
                        + "  `datatype_id` \n"
                        + "FROM\n"
                        + "  `concept`\n"
                        + "  WHERE `concept_id` = ?");
                getopenMRSdatatype.setString(1, ICrsgetconcept.getString("concept_id"));
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

    public static Concept conceptIdsforLabFORM(String concept) {
        Concept conceptObs = new Concept();
        try {
            getICConceptId = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id` \n"
                    + "FROM\n"
                    + "  `concept_lab` \n"
                    + "  WHERE `cisproconcepts` = ?");
            getICConceptId.setString(1, concept);
            ICrsgetconcept = getICConceptId.executeQuery();
            if (ICrsgetconcept.next()) {
                conceptObs.setConceptId(ICrsgetconcept.getString("concept_id"));
                getopenMRSdatatype = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                               SELECT \n"
                        + "  `datatype_id` \n"
                        + "FROM\n"
                        + "  `concept`\n"
                        + "  WHERE `concept_id` = ?");
                getopenMRSdatatype.setString(1, ICrsgetconcept.getString("concept_id"));
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

    public static Concept conceptIdsforLabCheckboxFORM(String concept) {
        Concept conceptObs = new Concept();
        try {
            getICConceptId = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id` \n"
                    + "FROM\n"
                    + "  `concept_lab_checkboxes` \n"
                    + "  WHERE `cisproconcepts` = ?");
            getICConceptId.setString(1, concept);
            ICrsgetconcept = getICConceptId.executeQuery();
            if (ICrsgetconcept.next()) {
                conceptObs.setConceptId(ICrsgetconcept.getString("concept_id"));
                getopenMRSdatatype = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                               SELECT \n"
                        + "  `datatype_id` \n"
                        + "FROM\n"
                        + "  `concept`\n"
                        + "  WHERE `concept_id` = ?");
                getopenMRSdatatype.setString(1, ICrsgetconcept.getString("concept_id"));
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

    public static Concept conceptIdsforPharmacyFORM(String concept) {
        Concept conceptObs = new Concept();
        try {
            getICConceptId = DatabaseHandler.getInstance().getApinConnection().prepareStatement("                                                               SELECT \n"
                    + "  `concept_id`, conceptvalue \n"
                    + "FROM\n"
                    + "  `parmacy_concepts` \n"
                    + "  WHERE `cisproconcepts` = ?");
            getICConceptId.setString(1, concept);
            ICrsgetconcept = getICConceptId.executeQuery();
            if (ICrsgetconcept.next()) {
                conceptObs.setConceptId(ICrsgetconcept.getString("concept_id"));
                conceptObs.setConcepValue(ICrsgetconcept.getString("conceptvalue"));
                getopenMRSdatatype = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement("                                                               SELECT \n"
                        + "  `datatype_id` \n"
                        + "FROM\n"
                        + "  `concept`\n"
                        + "  WHERE `concept_id` = ?");
                getopenMRSdatatype.setString(1, ICrsgetconcept.getString("concept_id"));
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

}
