/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.sql.Date;

/**
 *
 * @author APINPC
 */
public class Visit {
    private int visitId;
    private Date visitDate;

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
   
}
