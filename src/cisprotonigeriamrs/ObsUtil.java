package cisprotonigeriamrs;

import static com.google.common.base.Strings.isNullOrEmpty;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.NumberUtils;

public abstract class ObsUtil {

    private static PreparedStatement obsTable;

    public static boolean InsertObsChild(
            int personId,
            int conceptId,
            int encounterId,
            Date obsDateTime,
            int locationId,
            int obsGroupId,
            String conceptValue
    ) {

        try {
            int parentIds = (obsGroupId == 0) ? null : obsGroupId;
            String personNameTableSql = "";
            int valueTypeId = getObsConceptType(conceptId);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            switch (valueTypeId) {
                case 1: //Numeric
                    if (isNullOrEmpty(conceptValue)) {
                        conceptValue = conceptValue.trim();
                        conceptValue = conceptValue.replaceAll("[^0-9.]", "");
                        conceptValue = conceptValue.replace("..", ".");
                        conceptValue = conceptValue.replaceAll("[.]$", "");
                        Double numericValue = isNullOrEmpty(conceptValue) ? Double.parseDouble(conceptValue) : null;
                        if (numericValue != null) {

                            personNameTableSql = String
                                    .format(
                                            " INSERT INTO obs ( "
                                            + "person_id,"
                                            + "concept_id,"
                                            + "encounter_id,"
                                            + "obs_datetime,"
                                            + "location_id,"
                                            + "value_numeric,"
                                            + "creator,"
                                            + "date_created,"
                                            + "obs_group_id, "
                                            + "uuid ) "
                                            + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',%s, UUID()) ",
                                            personId,
                                            conceptId,
                                            encounterId,
                                            obsDateTime,
                                            locationId,
                                            numericValue,
                                            1,
                                            obsDateTime,
                                            parentIds
                                    );
                        }
                    }
                    break;
                case 2: //Coded
                    int conceptCheckValue = getObsConceptId(conceptValue);
                    if (conceptCheckValue != 0) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_coded,"
                                        + "creator,"
                                        + "date_created,"
                                        + "obs_group_id, "
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',%s, UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        conceptValue,
                                        1,
                                        obsDateTime,
                                        parentIds
                                );
                    }
                    break;
                case 3: //Text
                    personNameTableSql = String
                            .format(
                                    " INSERT INTO obs ( "
                                    + "person_id,"
                                    + "concept_id,"
                                    + "encounter_id,"
                                    + "obs_datetime,"
                                    + "location_id,"
                                    + "value_text,"
                                    + "creator,"
                                    + "date_created,"
                                    + "obs_group_id, "
                                    + "uuid ) "
                                    + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s',%s, UUID()) ",
                                    personId,
                                    conceptId,
                                    encounterId,
                                    obsDateTime,
                                    locationId,
                                    conceptValue,
                                    1,
                                    obsDateTime,
                                    parentIds
                            );
                    break;
                case 4: //N/A
                    personNameTableSql = String
                            .format(
                                    " INSERT INTO obs ( "
                                    + "person_id,"
                                    + "concept_id,"
                                    + "encounter_id,"
                                    + "obs_datetime,"
                                    + "location_id,"
                                    + "value_coded,"
                                    + "creator,"
                                    + "date_created,"
                                    + "obs_group_id, "
                                    + "uuid ) "
                                    + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',%s, UUID()) ",
                                    personId,
                                    conceptId,
                                    encounterId,
                                    obsDateTime,
                                    locationId,
                                    conceptValue,
                                    1,
                                    obsDateTime,
                                    parentIds
                            );

                    break;
                case 6: //Date
                    Date dateValue = isNullOrEmpty(conceptValue) ? dateFormat.parse(conceptValue) : null;
                    if (dateValue != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_datetime,"
                                        + "creator,"
                                        + "date_created,"
                                        + "obs_group_id, "
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s',%s, '%s',%s, UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        dateValue,
                                        1,
                                        obsDateTime,
                                        parentIds
                                );
                    }

                    break;
                case 8: //Datetime
                    Date dateValue2 = isNullOrEmpty(conceptValue) ? dateFormat.parse(conceptValue) : null;
                    if (dateValue2 != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_datetime,"
                                        + "creator,"
                                        + "date_created,"
                                        + "obs_group_id, "
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s',%s, '%s',%s, UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        dateValue2,
                                        1,
                                        obsDateTime,
                                        parentIds
                                );
                    }
                    break;
                case 10://boolean
                    int conceptCheckValue2 = getObsConceptId(conceptValue);
                    if (conceptCheckValue2 != 0) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_coded,"
                                        + "creator,"
                                        + "date_created,"
                                        + "obs_group_id, "
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',%s, UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        conceptCheckValue2,
                                        1,
                                        obsDateTime,
                                        parentIds
                                );
                    }

                default:

                    break;
            }
            if (!isNullOrEmpty(personNameTableSql)) {
                obsTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(personNameTableSql);
                obsTable.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean InsertObs(
            int personId,
            int conceptId,
            int encounterId,
            Date obsDateTime,
            int locationId,
            String conceptValue
    ) {
        String personNameTableSql = "";
        try {

            int valueTypeId = getObsConceptType(conceptId);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            switch (valueTypeId) {
                case 1: //Numeric
                    Double numericValue = isNullOrEmpty(conceptValue) ? Double.parseDouble(conceptValue) : null;
                    if (numericValue != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_numeric,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        numericValue,
                                        1,
                                        obsDateTime
                                );
                    }
                    break;
                case 2: //Coded
                    int conceptCheckValue = getObsConceptId(conceptValue);
                    if (conceptCheckValue != 0) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_coded,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        conceptCheckValue,
                                        1,
                                        obsDateTime
                                );
                    }
                    break;
                case 3: //Text
                    personNameTableSql = String
                            .format(
                                    " INSERT INTO obs ( "
                                    + "person_id,"
                                    + "concept_id,"
                                    + "encounter_id,"
                                    + "obs_datetime,"
                                    + "location_id,"
                                    + "value_text,"
                                    + "creator,"
                                    + "date_created,"
                                    + "uuid ) "
                                    + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s', UUID()) ",
                                    personId,
                                    conceptId,
                                    encounterId,
                                    obsDateTime,
                                    locationId,
                                    conceptValue,
                                    1,
                                    obsDateTime
                            );
                    break;
                case 4: //N/A
                    personNameTableSql = String
                            .format(
                                    " INSERT INTO obs ( "
                                    + "person_id,"
                                    + "concept_id,"
                                    + "encounter_id,"
                                    + "obs_datetime,"
                                    + "location_id,"
                                    + "value_coded,"
                                    + "creator,"
                                    + "date_created,"
                                    + "uuid ) "
                                    + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s', UUID()) ",
                                    personId,
                                    conceptId,
                                    encounterId,
                                    obsDateTime,
                                    locationId,
                                    conceptValue,
                                    1,
                                    obsDateTime
                            );

                    break;
                case 6: //Date
                    String dateValue = !isNullOrEmpty(conceptValue) ? conceptValue : null;
                    if (dateValue != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_datetime,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s', UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        dateValue,
                                        1,
                                        obsDateTime
                                );
                    }

                    break;
                case 8: //Datetime
                    String dateValue2 = !isNullOrEmpty(conceptValue) ? conceptValue : null;
                    if (dateValue2 != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_datetime,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s', UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        dateValue2,
                                        1,
                                        obsDateTime
                                );
                    }
                    break;
                case 10://boolean
                    int conceptCheckValue2 = getObsConceptId(conceptValue);
                    if (conceptCheckValue2 != 0) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_coded,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        conceptCheckValue2,
                                        1,
                                        obsDateTime
                                );
                    }

                default:

                    break;
            }
            if (!isNullOrEmpty(personNameTableSql)) {
               // System.out.println(personNameTableSql);
                obsTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(personNameTableSql);
                obsTable.executeUpdate();
            }
            return true;
        } catch (Exception e) {
           // System.out.println(personNameTableSql);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean InsertObsLab(
            int personId,
            int conceptId,
            int encounterId,
            Date obsDateTime,
            int locationId,
            String conceptValue
    ) {
        String personNameTableSql = "";
        try {

            int valueTypeId = getObsConceptType(conceptId);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            switch (valueTypeId) {
                case 1: //Numeric
                    Double numericValue = isNullOrEmpty(conceptValue) ? Double.parseDouble(conceptValue) : null;
                    if (numericValue != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_numeric,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        numericValue,
                                        1,
                                        obsDateTime
                                );
                    }
                    break;
                case 2: //Coded

                        int conceptCheckValue = Integer.parseInt(conceptValue);
                        if (conceptCheckValue != 0) {
                            personNameTableSql = String
                                    .format(
                                            " INSERT INTO obs ( "
                                            + "person_id,"
                                            + "concept_id,"
                                            + "encounter_id,"
                                            + "obs_datetime,"
                                            + "location_id,"
                                            + "value_coded,"
                                            + "creator,"
                                            + "date_created,"
                                            + "uuid ) "
                                            + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',UUID()) ",
                                            personId,
                                            conceptId,
                                            encounterId,
                                            obsDateTime,
                                            locationId,
                                            conceptCheckValue,
                                            1,
                                            obsDateTime
                                    );
                        }
                    
                    break;
                case 3: //Text
                    personNameTableSql = String
                            .format(
                                    " INSERT INTO obs ( "
                                    + "person_id,"
                                    + "concept_id,"
                                    + "encounter_id,"
                                    + "obs_datetime,"
                                    + "location_id,"
                                    + "value_text,"
                                    + "creator,"
                                    + "date_created,"
                                    + "uuid ) "
                                    + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s', UUID()) ",
                                    personId,
                                    conceptId,
                                    encounterId,
                                    obsDateTime,
                                    locationId,
                                    conceptValue,
                                    1,
                                    obsDateTime
                            );
                    break;
                case 4: //N/A
                    personNameTableSql = String
                            .format(
                                    " INSERT INTO obs ( "
                                    + "person_id,"
                                    + "concept_id,"
                                    + "encounter_id,"
                                    + "obs_datetime,"
                                    + "location_id,"
                                    + "value_coded,"
                                    + "creator,"
                                    + "date_created,"
                                    + "uuid ) "
                                    + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s', UUID()) ",
                                    personId,
                                    conceptId,
                                    encounterId,
                                    obsDateTime,
                                    locationId,
                                    conceptValue,
                                    1,
                                    obsDateTime
                            );

                    break;
                case 6: //Date
                    String dateValue = !isNullOrEmpty(conceptValue) ? conceptValue : null;
                    if (dateValue != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_datetime,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s', UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        dateValue,
                                        1,
                                        obsDateTime
                                );
                    }

                    break;
                case 8: //Datetime
                    String dateValue2 = !isNullOrEmpty(conceptValue) ? conceptValue : null;
                    if (dateValue2 != null) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_datetime,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,'%s',%s, '%s', UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        dateValue2,
                                        1,
                                        obsDateTime
                                );
                    }
                    break;
                case 10://boolean
                    int conceptCheckValue2 = Integer.parseInt(conceptValue);
                    if (conceptCheckValue2 != 0) {
                        personNameTableSql = String
                                .format(
                                        " INSERT INTO obs ( "
                                        + "person_id,"
                                        + "concept_id,"
                                        + "encounter_id,"
                                        + "obs_datetime,"
                                        + "location_id,"
                                        + "value_coded,"
                                        + "creator,"
                                        + "date_created,"
                                        + "uuid ) "
                                        + "VALUES(%s, %s, %s, '%s',%s,%s,%s, '%s',UUID()) ",
                                        personId,
                                        conceptId,
                                        encounterId,
                                        obsDateTime,
                                        locationId,
                                        conceptCheckValue2,
                                        1,
                                        obsDateTime
                                );
                    }

                default:

                    break;
            }
            if (!isNullOrEmpty(personNameTableSql)) {
                //System.out.println(personNameTableSql);
                obsTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(personNameTableSql);
                obsTable.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            //System.out.println(personNameTableSql);
            e.printStackTrace();
            return false;
        }
    }

    public static int getObsConceptType(int conceptId) {
        try {
            String obsbleSql = String
                    .format(
                            " select datatype_id  from concept where concept_id  = %s limit 1",
                            conceptId
                    );

            PreparedStatement conceptable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(obsbleSql);
            int conceptType = 0;
            ResultSet conceptableRes = conceptable.executeQuery();

            while (conceptableRes.next()) {
                conceptType = conceptableRes.getInt(1);
            }
            return conceptType;
        } catch (SQLException ex) {
            Logger.getLogger(ObsUtil.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static int getObsConceptId(String conceptName) {
        try {
            String encounterTableSql = String
                    .format(
                            " SELECT concept_id  FROM concept_name c WHERE  c.NAME  = '%s' AND locale = \"en\" LIMIT 1 ",
                            conceptName
                    );

            PreparedStatement conceptable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(encounterTableSql);
            int conceptID = 0;
            ResultSet conceptableRes = conceptable.executeQuery();

            while (conceptableRes.next()) {
                conceptID = conceptableRes.getInt(1);
            }
            return conceptID;
        } catch (SQLException ex) {
            Logger.getLogger(EncounterUtil.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
