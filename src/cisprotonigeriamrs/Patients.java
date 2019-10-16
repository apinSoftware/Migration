/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author APINPC
 */
public class Patients {
    
  private String  pepid;
  private String  uniqueid;
  private String  surname;
  private String  othernames;
  private String  hospitalid;
  private String  Sex;
  private String  DOB;
  private String  maritalstatus;
  private String  educationallevel;
  private String  educationallevelchild;
  private String  educationallevelmother;
  private String  educationallevelfather;
  private String  educationallevelcaregiver;
  private String  jobstatus;
  private String  address;
  private String  wardvillage;
  private String  town; 
  private Date enroldate;
  private Date hivposdate;
  private String  hivmode;
  private String  careentrypoint;
  private String  priorart;
  private String  presentingcomplaint;
  private String  symptomsreview;
  private String  dur_fever;
  private String  dur_nausea;
  private String  dur_nsweats;
  private String  dur_weigthl;
  private String  dur_cough;
  private String  dur_head;
  private String  dur_rash;
  private String  dur_itch;
  private String  dur_diarh;
  private String  dur_gendis;
  private String  dur_genitch;
  private String  dur_gensore;
  private String  dur_shortbreath;
  private String  dur_visimp;
  private String  dur_painswall;
  private String  dur_numb;
  private String  dur_otherpain;
  private String  dur_eardis;
  private String  dur_painmict;
  private String  dur_oralsores;
  private String  dur_irr;
  private String  dur_sleep;
  private String  dur_food;
  private String  dur_convul;
  private String  symduration;
  private String  addiotnalsymcmments;
  private String  pastmedicalhist;
  private String  relfamilyhist;
  private String  hospitalization;
  private String  drugallergies;
  private String  riskfactors;
  private String  lastmenstrualperiod;
  private String  currentlypregnant;
  private String  gestationalage;
  private String  edd;
  private String  lastcd4;
  private String  lastcd4date;
  private String  lastvl;
  private String  lastvldate;
  private String  prevarvexp;
  private String  prevarvtype;
  private String  prevfacilityname;
  private String  prevdurationofcarefromdate;
  private String  prevdurationofcaretodate;
  private String  currentmedications;
  private String  serviceentry;
  private String  adherenceyn;
  private String  missedadh3daysyn;
  private String  missadhreason;
  private String  missedadhcode;
  private String  interradhcode;
  private String  stopadhcode;
  private String  trtmtinterruptionyn;
  private String  trtmtinterdate;
  private String  trtmtinterduration;
  private String  trtmtinterreason;
  private String  trtmtstopyn;
  private String  trtmtstopdate;
  private String  trtmtstopduration;
  private String  trtmtstopreason;
  private String  hivstatdisclosure;
  private String  hivstatdisclosureother;
  private String  hivstatdiscussion;
  private String  supportgrpyn;
  private String  pastcurrarvsideeff;
  private String  petemp;
  private String  pebp;
  private String  pepulse;
  private String  peweight;
  private String  peheight;
  private String  peweightatart;
  private String  peheightatart;
  private String  cd4atart;
  private String  pegenappearance;
  private String  peskin;
  private String  peheadeye;
  private String  pebreasts;
  private String  pecardiovascular;
  private String  pegenitalia;
  private String  gerespiratory;
  private String  gerespiratoryrate;
  private String  gegastrointestinal;
  private String  geneurological;
  private String  gementalstatus;
  private String  peadditional;
  private String  patfunction;
  private String  assessment;
  private String  assessmentnotes;
  private String  whostagecriteria;
  private String  whostage;
  private String  plan;
  private String  planlab;
  private String  plantbs;
  private String   planoip;
  private String  planpep;
  private String  planadh;
  private String  planoit;
  private String  planadm;
  private String  planpain;
  private String  plancounsel;
  private String  planref;
  private String  planpalliative;
  private String  plancxr;
  private String  planpmtct;
  private String  planother;
  private String  enrollin;
  private String  planarvtherapy;
  private String  planarvtherchgtrtmtreason;
  private String  planarvtherstoptrtmtreason;
  private String  regimen1st;
  private String  regimen2nd;
  private String  regimenalternate;
  private String  regimenothers;
  private Date nextappointmentdate;
  private String  nokphoneno;
  private String  Clinicianname;
  private Date created_on;

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
    public String getPepid() {
        return pepid;
    }

    public void setPepid(String pepid) {
        this.pepid = pepid;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOthernames() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getEducationallevel() {
        return educationallevel;
    }

    public void setEducationallevel(String educationallevel) {
        this.educationallevel = educationallevel;
    }

    public String getEducationallevelchild() {
        return educationallevelchild;
    }

    public void setEducationallevelchild(String educationallevelchild) {
        this.educationallevelchild = educationallevelchild;
    }

    public String getEducationallevelmother() {
        return educationallevelmother;
    }

    public void setEducationallevelmother(String educationallevelmother) {
        this.educationallevelmother = educationallevelmother;
    }

    public String getEducationallevelfather() {
        return educationallevelfather;
    }

    public void setEducationallevelfather(String educationallevelfather) {
        this.educationallevelfather = educationallevelfather;
    }

    public String getEducationallevelcaregiver() {
        return educationallevelcaregiver;
    }

    public void setEducationallevelcaregiver(String educationallevelcaregiver) {
        this.educationallevelcaregiver = educationallevelcaregiver;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWardvillage() {
        return wardvillage;
    }

    public void setWardvillage(String wardvillage) {
        this.wardvillage = wardvillage;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Date getEnroldate() {
        return enroldate;
    }

    public void setEnroldate(Date enroldate) {
        this.enroldate = enroldate;
    }

    public Date getHivposdate() {
        return hivposdate;
    }

    public void setHivposdate(Date hivposdate) {
        this.hivposdate = hivposdate;
    }

    public String getHivmode() {
        return hivmode;
    }

    public void setHivmode(String hivmode) {
        this.hivmode = hivmode;
    }

    public String getCareentrypoint() {
        return careentrypoint;
    }

    public void setCareentrypoint(String careentrypoint) {
        this.careentrypoint = careentrypoint;
    }

    public String getPriorart() {
        return priorart;
    }

    public void setPriorart(String priorart) {
        this.priorart = priorart;
    }

    public String getPresentingcomplaint() {
        return presentingcomplaint;
    }

    public void setPresentingcomplaint(String presentingcomplaint) {
        this.presentingcomplaint = presentingcomplaint;
    }

    public String getSymptomsreview() {
        return symptomsreview;
    }

    public void setSymptomsreview(String symptomsreview) {
        this.symptomsreview = symptomsreview;
    }

    public String getDur_fever() {
        return dur_fever;
    }

    public void setDur_fever(String dur_fever) {
        this.dur_fever = dur_fever;
    }

    public String getDur_nausea() {
        return dur_nausea;
    }

    public void setDur_nausea(String dur_nausea) {
        this.dur_nausea = dur_nausea;
    }

    public String getDur_nsweats() {
        return dur_nsweats;
    }

    public void setDur_nsweats(String dur_nsweats) {
        this.dur_nsweats = dur_nsweats;
    }

    public String getDur_weigthl() {
        return dur_weigthl;
    }

    public void setDur_weigthl(String dur_weigthl) {
        this.dur_weigthl = dur_weigthl;
    }

    public String getDur_cough() {
        return dur_cough;
    }

    public void setDur_cough(String dur_cough) {
        this.dur_cough = dur_cough;
    }

    public String getDur_head() {
        return dur_head;
    }

    public void setDur_head(String dur_head) {
        this.dur_head = dur_head;
    }

    public String getDur_rash() {
        return dur_rash;
    }

    public void setDur_rash(String dur_rash) {
        this.dur_rash = dur_rash;
    }

    public String getDur_itch() {
        return dur_itch;
    }

    public void setDur_itch(String dur_itch) {
        this.dur_itch = dur_itch;
    }

    public String getDur_diarh() {
        return dur_diarh;
    }

    public void setDur_diarh(String dur_diarh) {
        this.dur_diarh = dur_diarh;
    }

    public String getDur_gendis() {
        return dur_gendis;
    }

    public void setDur_gendis(String dur_gendis) {
        this.dur_gendis = dur_gendis;
    }

    public String getDur_genitch() {
        return dur_genitch;
    }

    public void setDur_genitch(String dur_genitch) {
        this.dur_genitch = dur_genitch;
    }

    public String getDur_gensore() {
        return dur_gensore;
    }

    public void setDur_gensore(String dur_gensore) {
        this.dur_gensore = dur_gensore;
    }

    public String getDur_shortbreath() {
        return dur_shortbreath;
    }

    public void setDur_shortbreath(String dur_shortbreath) {
        this.dur_shortbreath = dur_shortbreath;
    }

    public String getDur_visimp() {
        return dur_visimp;
    }

    public void setDur_visimp(String dur_visimp) {
        this.dur_visimp = dur_visimp;
    }

    public String getDur_painswall() {
        return dur_painswall;
    }

    public void setDur_painswall(String dur_painswall) {
        this.dur_painswall = dur_painswall;
    }

    public String getDur_numb() {
        return dur_numb;
    }

    public void setDur_numb(String dur_numb) {
        this.dur_numb = dur_numb;
    }

    public String getDur_otherpain() {
        return dur_otherpain;
    }

    public void setDur_otherpain(String dur_otherpain) {
        this.dur_otherpain = dur_otherpain;
    }

    public String getDur_eardis() {
        return dur_eardis;
    }

    public void setDur_eardis(String dur_eardis) {
        this.dur_eardis = dur_eardis;
    }

    public String getDur_painmict() {
        return dur_painmict;
    }

    public void setDur_painmict(String dur_painmict) {
        this.dur_painmict = dur_painmict;
    }

    public String getDur_oralsores() {
        return dur_oralsores;
    }

    public void setDur_oralsores(String dur_oralsores) {
        this.dur_oralsores = dur_oralsores;
    }

    public String getDur_irr() {
        return dur_irr;
    }

    public void setDur_irr(String dur_irr) {
        this.dur_irr = dur_irr;
    }

    public String getDur_sleep() {
        return dur_sleep;
    }

    public void setDur_sleep(String dur_sleep) {
        this.dur_sleep = dur_sleep;
    }

    public String getDur_food() {
        return dur_food;
    }

    public void setDur_food(String dur_food) {
        this.dur_food = dur_food;
    }

    public String getDur_convul() {
        return dur_convul;
    }

    public void setDur_convul(String dur_convul) {
        this.dur_convul = dur_convul;
    }

    public String getSymduration() {
        return symduration;
    }

    public void setSymduration(String symduration) {
        this.symduration = symduration;
    }

    public String getAddiotnalsymcmments() {
        return addiotnalsymcmments;
    }

    public void setAddiotnalsymcmments(String addiotnalsymcmments) {
        this.addiotnalsymcmments = addiotnalsymcmments;
    }

    public String getPastmedicalhist() {
        return pastmedicalhist;
    }

    public void setPastmedicalhist(String pastmedicalhist) {
        this.pastmedicalhist = pastmedicalhist;
    }

    public String getRelfamilyhist() {
        return relfamilyhist;
    }

    public void setRelfamilyhist(String relfamilyhist) {
        this.relfamilyhist = relfamilyhist;
    }

    public String getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(String hospitalization) {
        this.hospitalization = hospitalization;
    }

    public String getDrugallergies() {
        return drugallergies;
    }

    public void setDrugallergies(String drugallergies) {
        this.drugallergies = drugallergies;
    }

    public String getRiskfactors() {
        return riskfactors;
    }

    public void setRiskfactors(String riskfactors) {
        this.riskfactors = riskfactors;
    }

    public String getLastmenstrualperiod() {
        return lastmenstrualperiod;
    }

    public void setLastmenstrualperiod(String lastmenstrualperiod) {
        this.lastmenstrualperiod = lastmenstrualperiod;
    }

    public String getCurrentlypregnant() {
        return currentlypregnant;
    }

    public void setCurrentlypregnant(String currentlypregnant) {
        this.currentlypregnant = currentlypregnant;
    }

    public String getGestationalage() {
        return gestationalage;
    }

    public void setGestationalage(String gestationalage) {
        this.gestationalage = gestationalage;
    }

    public String getEdd() {
        return edd;
    }

    public void setEdd(String edd) {
        this.edd = edd;
    }

    public String getLastcd4() {
        return lastcd4;
    }

    public void setLastcd4(String lastcd4) {
        this.lastcd4 = lastcd4;
    }

    public String getLastcd4date() {
        return lastcd4date;
    }

    public void setLastcd4date(String lastcd4date) {
        this.lastcd4date = lastcd4date;
    }

    public String getLastvl() {
        return lastvl;
    }

    public void setLastvl(String lastvl) {
        this.lastvl = lastvl;
    }

    public String getLastvldate() {
        return lastvldate;
    }

    public void setLastvldate(String lastvldate) {
        this.lastvldate = lastvldate;
    }

    public String getPrevarvexp() {
        return prevarvexp;
    }

    public void setPrevarvexp(String prevarvexp) {
        this.prevarvexp = prevarvexp;
    }

    public String getPrevarvtype() {
        return prevarvtype;
    }

    public void setPrevarvtype(String prevarvtype) {
        this.prevarvtype = prevarvtype;
    }

    public String getPrevfacilityname() {
        return prevfacilityname;
    }

    public void setPrevfacilityname(String prevfacilityname) {
        this.prevfacilityname = prevfacilityname;
    }

    public String getPrevdurationofcarefromdate() {
        return prevdurationofcarefromdate;
    }

    public void setPrevdurationofcarefromdate(String prevdurationofcarefromdate) {
        this.prevdurationofcarefromdate = prevdurationofcarefromdate;
    }

    public String getPrevdurationofcaretodate() {
        return prevdurationofcaretodate;
    }

    public void setPrevdurationofcaretodate(String prevdurationofcaretodate) {
        this.prevdurationofcaretodate = prevdurationofcaretodate;
    }

    public String getCurrentmedications() {
        return currentmedications;
    }

    public void setCurrentmedications(String currentmedications) {
        this.currentmedications = currentmedications;
    }

    public String getServiceentry() {
        return serviceentry;
    }

    public void setServiceentry(String serviceentry) {
        this.serviceentry = serviceentry;
    }

    public String getAdherenceyn() {
        return adherenceyn;
    }

    public void setAdherenceyn(String adherenceyn) {
        this.adherenceyn = adherenceyn;
    }

    public String getMissedadh3daysyn() {
        return missedadh3daysyn;
    }

    public void setMissedadh3daysyn(String missedadh3daysyn) {
        this.missedadh3daysyn = missedadh3daysyn;
    }

    public String getMissadhreason() {
        return missadhreason;
    }

    public void setMissadhreason(String missadhreason) {
        this.missadhreason = missadhreason;
    }

    public String getMissedadhcode() {
        return missedadhcode;
    }

    public void setMissedadhcode(String missedadhcode) {
        this.missedadhcode = missedadhcode;
    }

    public String getInterradhcode() {
        return interradhcode;
    }

    public void setInterradhcode(String interradhcode) {
        this.interradhcode = interradhcode;
    }

    public String getStopadhcode() {
        return stopadhcode;
    }

    public void setStopadhcode(String stopadhcode) {
        this.stopadhcode = stopadhcode;
    }

    public String getTrtmtinterruptionyn() {
        return trtmtinterruptionyn;
    }

    public void setTrtmtinterruptionyn(String trtmtinterruptionyn) {
        this.trtmtinterruptionyn = trtmtinterruptionyn;
    }

    public String getTrtmtinterdate() {
        return trtmtinterdate;
    }

    public void setTrtmtinterdate(String trtmtinterdate) {
        this.trtmtinterdate = trtmtinterdate;
    }

    public String getTrtmtinterduration() {
        return trtmtinterduration;
    }

    public void setTrtmtinterduration(String trtmtinterduration) {
        this.trtmtinterduration = trtmtinterduration;
    }

    public String getTrtmtinterreason() {
        return trtmtinterreason;
    }

    public void setTrtmtinterreason(String trtmtinterreason) {
        this.trtmtinterreason = trtmtinterreason;
    }

    public String getTrtmtstopyn() {
        return trtmtstopyn;
    }

    public void setTrtmtstopyn(String trtmtstopyn) {
        this.trtmtstopyn = trtmtstopyn;
    }

    public String getTrtmtstopdate() {
        return trtmtstopdate;
    }

    public void setTrtmtstopdate(String trtmtstopdate) {
        this.trtmtstopdate = trtmtstopdate;
    }

    public String getTrtmtstopduration() {
        return trtmtstopduration;
    }

    public void setTrtmtstopduration(String trtmtstopduration) {
        this.trtmtstopduration = trtmtstopduration;
    }

    public String getTrtmtstopreason() {
        return trtmtstopreason;
    }

    public void setTrtmtstopreason(String trtmtstopreason) {
        this.trtmtstopreason = trtmtstopreason;
    }

    public String getHivstatdisclosure() {
        return hivstatdisclosure;
    }

    public void setHivstatdisclosure(String hivstatdisclosure) {
        this.hivstatdisclosure = hivstatdisclosure;
    }

    public String getHivstatdisclosureother() {
        return hivstatdisclosureother;
    }

    public void setHivstatdisclosureother(String hivstatdisclosureother) {
        this.hivstatdisclosureother = hivstatdisclosureother;
    }

    public String getHivstatdiscussion() {
        return hivstatdiscussion;
    }

    public void setHivstatdiscussion(String hivstatdiscussion) {
        this.hivstatdiscussion = hivstatdiscussion;
    }

    public String getSupportgrpyn() {
        return supportgrpyn;
    }

    public void setSupportgrpyn(String supportgrpyn) {
        this.supportgrpyn = supportgrpyn;
    }

    public String getPastcurrarvsideeff() {
        return pastcurrarvsideeff;
    }

    public void setPastcurrarvsideeff(String pastcurrarvsideeff) {
        this.pastcurrarvsideeff = pastcurrarvsideeff;
    }

    public String getPetemp() {
        return petemp;
    }

    public void setPetemp(String petemp) {
        this.petemp = petemp;
    }

    public String getPebp() {
        return pebp;
    }

    public void setPebp(String pebp) {
        this.pebp = pebp;
    }

    public String getPepulse() {
        return pepulse;
    }

    public void setPepulse(String pepulse) {
        this.pepulse = pepulse;
    }

    public String getPeweight() {
        return peweight;
    }

    public void setPeweight(String peweight) {
        this.peweight = peweight;
    }

    public String getPeheight() {
        return peheight;
    }

    public void setPeheight(String peheight) {
        this.peheight = peheight;
    }

    public String getPeweightatart() {
        return peweightatart;
    }

    public void setPeweightatart(String peweightatart) {
        this.peweightatart = peweightatart;
    }

    public String getPeheightatart() {
        return peheightatart;
    }

    public void setPeheightatart(String peheightatart) {
        this.peheightatart = peheightatart;
    }

    public String getCd4atart() {
        return cd4atart;
    }

    public void setCd4atart(String cd4atart) {
        this.cd4atart = cd4atart;
    }

    public String getPegenappearance() {
        return pegenappearance;
    }

    public void setPegenappearance(String pegenappearance) {
        this.pegenappearance = pegenappearance;
    }

    public String getPeskin() {
        return peskin;
    }

    public void setPeskin(String peskin) {
        this.peskin = peskin;
    }

    public String getPeheadeye() {
        return peheadeye;
    }

    public void setPeheadeye(String peheadeye) {
        this.peheadeye = peheadeye;
    }

    public String getPebreasts() {
        return pebreasts;
    }

    public void setPebreasts(String pebreasts) {
        this.pebreasts = pebreasts;
    }

    public String getPecardiovascular() {
        return pecardiovascular;
    }

    public void setPecardiovascular(String pecardiovascular) {
        this.pecardiovascular = pecardiovascular;
    }

    public String getPegenitalia() {
        return pegenitalia;
    }

    public void setPegenitalia(String pegenitalia) {
        this.pegenitalia = pegenitalia;
    }

    public String getGerespiratory() {
        return gerespiratory;
    }

    public void setGerespiratory(String gerespiratory) {
        this.gerespiratory = gerespiratory;
    }

    public String getGerespiratoryrate() {
        return gerespiratoryrate;
    }

    public void setGerespiratoryrate(String gerespiratoryrate) {
        this.gerespiratoryrate = gerespiratoryrate;
    }

    public String getGegastrointestinal() {
        return gegastrointestinal;
    }

    public void setGegastrointestinal(String gegastrointestinal) {
        this.gegastrointestinal = gegastrointestinal;
    }

    public String getGeneurological() {
        return geneurological;
    }

    public void setGeneurological(String geneurological) {
        this.geneurological = geneurological;
    }

    public String getGementalstatus() {
        return gementalstatus;
    }

    public void setGementalstatus(String gementalstatus) {
        this.gementalstatus = gementalstatus;
    }

    public String getPeadditional() {
        return peadditional;
    }

    public void setPeadditional(String peadditional) {
        this.peadditional = peadditional;
    }

    public String getPatfunction() {
        return patfunction;
    }

    public void setPatfunction(String patfunction) {
        this.patfunction = patfunction;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getAssessmentnotes() {
        return assessmentnotes;
    }

    public void setAssessmentnotes(String assessmentnotes) {
        this.assessmentnotes = assessmentnotes;
    }

    public String getWhostagecriteria() {
        return whostagecriteria;
    }

    public void setWhostagecriteria(String whostagecriteria) {
        this.whostagecriteria = whostagecriteria;
    }

    public String getWhostage() {
        return whostage;
    }

    public void setWhostage(String whostage) {
        this.whostage = whostage;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlanlab() {
        return planlab;
    }

    public void setPlanlab(String planlab) {
        this.planlab = planlab;
    }

    public String getPlantbs() {
        return plantbs;
    }

    public void setPlantbs(String plantbs) {
        this.plantbs = plantbs;
    }

    public String getPlanoip() {
        return planoip;
    }

    public void setPlanoip(String planoip) {
        this.planoip = planoip;
    }

    public String getPlanpep() {
        return planpep;
    }

    public void setPlanpep(String planpep) {
        this.planpep = planpep;
    }

    public String getPlanadh() {
        return planadh;
    }

    public void setPlanadh(String planadh) {
        this.planadh = planadh;
    }

    public String getPlanoit() {
        return planoit;
    }

    public void setPlanoit(String planoit) {
        this.planoit = planoit;
    }

    public String getPlanadm() {
        return planadm;
    }

    public void setPlanadm(String planadm) {
        this.planadm = planadm;
    }

    public String getPlanpain() {
        return planpain;
    }

    public void setPlanpain(String planpain) {
        this.planpain = planpain;
    }

    public String getPlancounsel() {
        return plancounsel;
    }

    public void setPlancounsel(String plancounsel) {
        this.plancounsel = plancounsel;
    }

    public String getPlanref() {
        return planref;
    }

    public void setPlanref(String planref) {
        this.planref = planref;
    }

    public String getPlanpalliative() {
        return planpalliative;
    }

    public void setPlanpalliative(String planpalliative) {
        this.planpalliative = planpalliative;
    }

    public String getPlancxr() {
        return plancxr;
    }

    public void setPlancxr(String plancxr) {
        this.plancxr = plancxr;
    }

    public String getPlanpmtct() {
        return planpmtct;
    }

    public void setPlanpmtct(String planpmtct) {
        this.planpmtct = planpmtct;
    }

    public String getPlanother() {
        return planother;
    }

    public void setPlanother(String planother) {
        this.planother = planother;
    }

    public String getEnrollin() {
        return enrollin;
    }

    public void setEnrollin(String enrollin) {
        this.enrollin = enrollin;
    }

    public String getPlanarvtherapy() {
        return planarvtherapy;
    }

    public void setPlanarvtherapy(String planarvtherapy) {
        this.planarvtherapy = planarvtherapy;
    }

    public String getPlanarvtherchgtrtmtreason() {
        return planarvtherchgtrtmtreason;
    }

    public void setPlanarvtherchgtrtmtreason(String planarvtherchgtrtmtreason) {
        this.planarvtherchgtrtmtreason = planarvtherchgtrtmtreason;
    }

    public String getPlanarvtherstoptrtmtreason() {
        return planarvtherstoptrtmtreason;
    }

    public void setPlanarvtherstoptrtmtreason(String planarvtherstoptrtmtreason) {
        this.planarvtherstoptrtmtreason = planarvtherstoptrtmtreason;
    }

    public String getRegimen1st() {
        return regimen1st;
    }

    public void setRegimen1st(String regimen1st) {
        this.regimen1st = regimen1st;
    }

    public String getRegimen2nd() {
        return regimen2nd;
    }

    public void setRegimen2nd(String regimen2nd) {
        this.regimen2nd = regimen2nd;
    }

    public String getRegimenalternate() {
        return regimenalternate;
    }

    public void setRegimenalternate(String regimenalternate) {
        this.regimenalternate = regimenalternate;
    }

    public String getRegimenothers() {
        return regimenothers;
    }

    public void setRegimenothers(String regimenothers) {
        this.regimenothers = regimenothers;
    }

    public Date getNextappointmentdate() {
        return nextappointmentdate;
    }

    public void setNextappointmentdate(Date nextappointmentdate) {
        this.nextappointmentdate = nextappointmentdate;
    }

    public String getNokphoneno() {
        return nokphoneno;
    }

    public void setNokphoneno(String nokphoneno) {
        this.nokphoneno = nokphoneno;
    }

    public String getClinicianname() {
        return Clinicianname;
    }

    public void setClinicianname(String Clinicianname) {
        this.Clinicianname = Clinicianname;
    }

}
