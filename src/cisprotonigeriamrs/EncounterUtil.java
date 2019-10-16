/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author APINPC
 */
public class EncounterUtil {

    private static PreparedStatement encounterTable;
    private static ResultSet encounterRes;
    private static PreparedStatement patientencounterProviderTable;

    public static Encounter createNewEncounter(
            int encounterType,
            int patientId,
            int locationId,
            int formId,
            Date encounterDate,
            int creator,
            Date dateCreated,
            int visitId
    ) {
        try {
            Encounter encounter = new Encounter();
            String encounterTableSql = String
                    .format(
                            " INSERT INTO encounter ( "
                            + "encounter_type,"
                            + "patient_id,"
                            + "location_id,"
                            + "form_id,"
                            + "encounter_datetime,"
                            + "creator,"
                            + "date_created,"
                            + "visit_id,"
                            + "uuid ) "
                            + "VALUES(%s, %s, %s, %s,'%s',%s,'%s',%s ,UUID()) ",
                            encounterType,
                            patientId,
                            locationId,
                            formId,
                            encounterDate,
                            creator,
                            dateCreated,
                            visitId
                    );

            encounterTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(encounterTableSql,
                    Statement.RETURN_GENERATED_KEYS
            );
            encounterTable.executeUpdate();
            encounterRes = encounterTable.getGeneratedKeys();

            if (encounterRes.next()) {
                encounter.setEncounterID(encounterRes.getInt(1));
                encounter.setEncounterDate(encounterDate);
                insertProvider(encounter.getEncounterID(), encounter.getEncounterDate());
                return encounter;
            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EncounterUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void insertProvider(int encounterId, Date encounterDate) {
        try {
            String patientencounterProviderTableSql = String
                    .format(
                            " INSERT INTO encounter_provider ( "
                            + "encounter_id,"
                            + "provider_id,"
                            + "creator,"
                            + "encounter_role_id,"
                            + "date_created,"
                            + "uuid ) "
                            + "VALUES(%s, %s, %s,%s,'%s',UUID()) ",
                            encounterId,
                            1,
                            1,
                            2,
                            encounterDate
                    );

            patientencounterProviderTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(patientencounterProviderTableSql);
            patientencounterProviderTable.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EncounterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Encounter getEncounterByDate(Date encounterDate, int PatientID, int encounterType) {
        try {
            Encounter encounter = new Encounter();
            String encounterTableSql = String
                    .format(
                            " select encounter_id, encounter_datetime from encounter where encounter_datetime  = '%s' and patient_id = %s  and encounter_type = %s limit 1",
                            encounterDate, PatientID, encounterType
                    );

            encounterTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(encounterTableSql);
            int encounterId = 0;
            ResultSet encounterCountRes = encounterTable.executeQuery();
            if (encounterCountRes.next()) {
                while (encounterCountRes.next()) {
                    encounter.setEncounterID(encounterRes.getInt(0));
                    encounter.setEncounterDate(encounterDate);
                }
                return encounter;
            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EncounterUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
