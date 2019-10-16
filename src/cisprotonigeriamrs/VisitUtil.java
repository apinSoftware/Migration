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
public class VisitUtil {

    private static PreparedStatement visitTable;
    private static ResultSet visitRes;

    
    public static Visit createNewVisit(
            int visit_type_id,
            int patientId,
            Date date_started,
            Date date_stopped,
            int creator,
            int location_id
    ) {
        try {
            Visit visit = new Visit();
            String visitTableSql = String
                    .format(
                            " INSERT INTO visit ( "
                            + "visit_type_id,"
                            + "patient_id,"
                            + "location_id,"
                            + "date_started,"
                            + "date_stopped,"
                            + "creator,"
                            + "date_created,"
                            + "uuid ) "
                            + "VALUES(%s, %s, %s, '%s','%s',%s,'%s' ,UUID()) ",
                            visit_type_id,
                            patientId,
                            location_id,
                            date_started,
                            date_stopped,
                            creator,
                            date_started
                    );
         
            visitTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(visitTableSql,
                    Statement.RETURN_GENERATED_KEYS
            );
            visitTable.executeUpdate();
            visitRes = visitTable.getGeneratedKeys();

            if (visitRes.next()) {
                visit.setVisitId(visitRes.getInt(1));
                visit.setVisitDate(date_started);
                return visit;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(VisitUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Visit getVisitByDate(Date visitDate, int PatientID, int visitType){
         try {
            Visit visit = new Visit();
            String encounterTableSql = String
                    .format(
                            " select   visit_id,  patient_id, date_started from visit where date_started  = '%s' and patient_id = %s and visit_type_id = %s limit 1",
                            visitDate, PatientID,visitType
                    );
         
            visitTable = DatabaseHandler.getInstance().getMRSDBConnection().prepareStatement(encounterTableSql);
            ResultSet visitCountRes = visitTable.executeQuery();
            if(visitCountRes.next()){
                 while (visitCountRes.next()) {
                 visit.setVisitId(visitCountRes.getInt(0));
                 visit.setVisitDate(visitDate);
                }
                return visit;
            }
           
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(VisitUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
