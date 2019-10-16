/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

/**
 *
 * @author tnnakwe
 */
class VisitObs {
    String visit_id;
    String ecounterId;
    boolean isValid;

    public String getEcounterId() {
        return ecounterId;
    }

    public void setEcounterId(String ecounterId) {
        this.ecounterId = ecounterId;
    }

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
    
}
