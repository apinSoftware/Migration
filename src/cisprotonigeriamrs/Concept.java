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
class Concept {

    public String getConcepValue() {
        return concepValue;
    }

    public void setConcepValue(String concepValue) {
        this.concepValue = concepValue;
    }
    String conceptId, conceptDataType, concepValue;

    public String getConceptId() {
        return conceptId;
    }

    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }

    public String getConceptDataType() {
        return conceptDataType;
    }

    public void setConceptDataType(String conceptDataType) {
        this.conceptDataType = conceptDataType;
    }
    
}
