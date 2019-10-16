/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Obs {
    String obsfield;
    String value;
    String dataType;    
    Date obsDate;

    public Date getObsDate() {
        return obsDate;
    }

    public void setObsDate(Date obsDate) {
        this.obsDate = obsDate;
    }

    public String getObsfield() {
        return obsfield;
    }

    public void setObsfield(String obsfield) {
        this.obsfield = obsfield;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
