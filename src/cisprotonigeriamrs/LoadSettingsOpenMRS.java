package cisprotonigeriamrs;


import com.mysql.jdbc.StringUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import cisprotonigeriamrs.FXMLDocumentController;
import static cisprotonigeriamrs.Utility.checkNullValueNumeric;
import static cisprotonigeriamrs.Utility.cleanValueNumeric;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class LoadSettingsOpenMRS {

    private static PreparedStatement icconcept;
    private Statement statement;
    private PreparedStatement dropTable;
    
    public  void createIcformConcept() {
        try {
            DatabaseHandler.getInstance().getApinConnection();
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            
                dropTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                String.format("DROP TABLE IF EXISTS %s", "icform_concepts"));
                dropTable.execute();
            
                String sql_stmt = "CREATE TABLE `icform_concepts` (\n" +
                    "  `concept_id` double DEFAULT NULL,\n" +
                    "  `concept` varchar(255) DEFAULT NULL,\n" +
                    "  `cisproconcepts` varchar(255) DEFAULT NULL,\n" +
                    "  `conceptvalue` varchar(255) DEFAULT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                statement = DatabaseHandler.getInstance().getApinConnection().createStatement();
                statement.executeUpdate(sql_stmt);
                
                icconcept = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                        "insert  into `icform_concepts`(`concept_id`,`concept`,`cisproconcepts`,`conceptvalue`) values "
                        + "(5588,'MOTHER DECEASED','pmotheralive',NULL),(1593,'MOTHER\\'S NAME','pmothername',NULL),(164936,'Mother\\'s Address','pmotheradd',NULL),"
                        + "(5589,'FATHER DECEASED','pfatheralive',NULL),(1594,'FATHER\\'S NAME','pfathername',NULL),(164939,'Father\\'s Address','pfatheradd',NULL),"
                        + "(164942,'CHILD\\'S PARENT/CAREGIVER ARE','pcaregiversrelationshipstatus',NULL),(5573,'NUMBER OF SIBLINGS','psiblings',NULL),(1727,'SIGNS AND SYMPTOMS','symptomsreview',NULL),"
                        + "(1728,'SIGN/SYMPTOM NAME',NULL,NULL),(1731,'SIGN/SYMPTOM DURATION',NULL,NULL),(1732,'Duration units',NULL,NULL),(164800,'Tuberculosis screening status','patacc_tb',NULL),"
                        + "(1200,'DEVELOPMENTAL EXAM FINDINGS','pdevassess',NULL),(164134,'Vaccination Complete','pimmunecomplete',NULL),(1151,'Infant feeding method','pfeedingmode',NULL),"
                        + "(160533,'Previous ARV treatment','prevarvexp',NULL),(162724,'Health facility name','prevfacilityname',NULL),(159368,'Medication duration',NULL,NULL),"
                        + "(165301,'Previous ARV exposure other than PMTCT','ppreviousarvexp',NULL),"
                        + "(159368,'Medication duration',NULL,NULL),(165675,'Patient has any known drug allergies','drugallergies',NULL),(160221,'Past medical history added (text)','pastmedicalhist',NULL),"
                        + "(159910,'past antiretroviral treatment, patient reported',NULL,NULL),(165305,'Current Medications','pcurrmeds',NULL),(5088,'Temperature (C)','petemp',NULL),(5087,'Pulse','pepulse',NULL),"
                        + "(5089,'Weight (kg)','peweight',NULL),(5090,'Height (cm)','pedcircumf',NULL),(980,'BODY SURFACE AREA','pedsurface',NULL),(1343,'MID-UPPER ARM CIRCUMFERENCE','pedmuac',NULL),"
                        + "(165327,'Head/Eye/ENT','peheadeye',NULL),(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),"
                        + "(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),(165327,'Head/Eye/ENT',NULL,NULL),"
                        + "(165327,'Head/Eye/ENT',NULL,NULL),(165318,'Skin','peskin',NULL),(165318,'Skin',NULL,NULL),(165318,'Skin',NULL,NULL),(165318,'Skin',NULL,NULL),(165318,'Skin',NULL,NULL),(165318,'Skin',NULL,NULL),"
                        + "(165318,'Skin',NULL,NULL),(165318,'Skin',NULL,NULL),(165318,'Skin',NULL,NULL),(165339,'Mental State','gementalstatus',NULL),(165339,'Mental State',NULL,NULL),(165339,'Mental State',NULL,NULL),"
                        + "(165339,'Mental State',NULL,NULL),(165339,'Mental State',NULL,NULL),(165339,'Mental State',NULL,NULL),(165339,'Mental State',NULL,NULL),(165339,'Mental State',NULL,NULL),"
                        + "(165339,'Mental State',NULL,NULL),(165316,'Respiratory','gerespiratory',NULL),(165316,'Respiratory',NULL,NULL),(165316,'Respiratory',NULL,NULL),(165316,'Respiratory',NULL,NULL),"
                        + "(165316,'Respiratory',NULL,NULL),(165316,'Respiratory',NULL,NULL),(165316,'Respiratory',NULL,NULL),(165316,'Respiratory',NULL,NULL),(165325,'Neurological','geneurological',NULL),("
                        + "165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),"
                        + "(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),(165325,'Neurological',NULL,NULL),"
                        + "(165313,'General Appearance','pegenappearance',NULL),(165313,'General Appearance',NULL,NULL),(165313,'General Appearance',NULL,NULL),(165313,'General Appearance',NULL,NULL),"
                        + "(165313,'General Appearance',NULL,NULL),(165333,'Genitalia','pegenitalia',NULL),(165333,'Genitalia',NULL,NULL),(165333,'Genitalia',NULL,NULL),(165333,'Genitalia',NULL,NULL),"
                        + "(165330,'Gastrointestinal','gegastrointestinal',NULL),(165330,'Gastrointestinal',NULL,NULL),(165330,'Gastrointestinal',NULL,NULL),(165330,'Gastrointestinal',NULL,NULL),"
                        + "(165330,'Gastrointestinal',NULL,NULL),(165330,'Gastrointestinal',NULL,NULL),(165326,'Cardiovascular',NULL,NULL),(165326,'Cardiovascular',NULL,NULL),"
                        + "(165326,'Cardiovascular',NULL,NULL),(165326,'Cardiovascular',NULL,NULL),(165331,'Glands','pedpeglands',NULL),(165331,'Glands',NULL,NULL),"
                        + "(165331,'Glands',NULL,NULL),(1391,'PHYSICAL EXAM','peadditional',NULL),(165602,'Assessment','assessment',NULL),(165602,'Assessment',NULL,NULL),"
                        + "(165602,'Assessment',NULL,NULL),(165602,'Assessment',NULL,NULL),(5356,'CURRENT WHO HIV STAGE','whostage',NULL),(165352,'Plan','plan',NULL),"
                        + "(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),"
                        + "(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),"
                        + "(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),(165352,'Plan',NULL,NULL),"
                        + "(165352,'Plan',NULL,NULL),(165355,'Enroll in','enrollin',NULL),(165355,'Enroll in',NULL,NULL),(165355,'Enroll in',NULL,NULL),(165361,'ART Plan','planarvtherapy',NULL),"
                        + "(165361,'ART Plan',NULL,NULL),(165361,'ART Plan',NULL,NULL),(165361,'ART Plan',NULL,NULL),(165361,'ART Plan',NULL,NULL),(161011,'Free text comment','pedadditional',NULL),"
                        + "(5096,'RETURN VISIT DATE','nextappointment',NULL),(1,'Anemia due to Blood Loss',NULL,NULL),(1060,'Living with partner',NULL,NULL),(140238,'Fever','Fever','dur_fever'),"
                        + "(1072,'Days',NULL,NULL),(163606,'Burning with urination','Pain on micturation','dur_painmict'),(832,'Weight loss','Weight Loss/Failure to gain weight','dur_weigthl'),"
                        + "(1073,'Weeks',NULL,NULL),(864,'GENITAL SORES','Genital sores/discharge','dur_gensore'),(1074,'Months',NULL,NULL),(133027,'Night sweats','Night Sweats','dur_nsweats'),"
                        + "(163074,'Numbness and tingling of skin','Numbness/Tingling','dur_numb'),(6023,'Irritability','Irritability','dur_irr'),(113054,'Convulsions','Convulsion','dur_convul'),"
                        + "(142371,'Difficulty Sleeping','Difficulty sleeping','dur_sleep'),(512,'Rash','Rash','dur_rash'),(139084,'Headache','Headache','dur_head'),(879,'Itching','Itching','dur_itch'),"
                        + "(159298,'visual impairment','New Visual impairment','dur_visimp'),(5622,'OTHER NON-CODED',NULL,NULL),(873,'OTORRHEA','Ear Discharge','dur_eardis'),(5244,'ORAL SORES','Oral Sores','dur_oralsores'),"
                        + "(142373,'Difficulty breathing','Shortness of Breath','dur_shortbreath'),(135595,'Loss of Appetite','Food refusal','dur_food'),(142412,'Diarrhea','Chronic Diarrhea','dur_diarh'),"
                        + "(133473,'Nausea and Vomiting','Nausea/Vomitting','dur_nausea'),(143264,'Cough','Cough','dur_cough'),(1065,'Yes','Yes',NULL),(1116,'Abnormal',NULL,NULL),(1066,'No','No',NULL),"
                        + "(1173,'EXPRESSED BREASTMILK',NULL,NULL),(1185,'TREATMENT','Treatment',NULL),(162228,'Allergen detail',NULL,NULL),(817,'ABACAVIR / LAMIVUDINE / ZIDOVUDINE',NULL,NULL),"
                        + "(165304,'Anti-TB Drugs',NULL,NULL),(165312,'No Significant Findings','No Significant Findings',NULL),(5334,'CANDIDIASIS, ORAL',NULL,NULL),(644,'KAPOSI SARCOMA ORAL','Oral KS',NULL),"
                        + "(139438,'Gingivitis','Gingivitis',NULL),(131113,'Otitis media','Otitis media',NULL),(111721,'Mouth ulceration','Oral ulcer',NULL),(5192,'Icteric sclera',NULL,NULL),"
                        + "(156441,'fundoscopy abnormal',NULL,NULL),(1249,'PAPULAR PRURITIC ERUPTION','Pruritic Paplar Dermatitis',NULL),(140,'Scabies','Scabies',NULL),(117543,'Herpes Zoster','Herpes Zoster',NULL),"
                        + "(150632,'Abscess','Abcesses',NULL),(165317,'Kaposi\\'s Lesions','Kaposis Lesions',NULL),(113116,'Seborrheic Dermatitis','Suborrheic Dermatits',NULL),(171,'Fungal infection','Fungal Infections',NULL),"
                        + "(165335,'Slow mentation','Slow Mentation',NULL),(121657,'Memory Loss','Memory Loss',NULL),(165336,'Mood swings','Mood Swings',NULL),(119537,'Depression','Depression',NULL),(121543,'Anxiety','Anxiety',NULL),"
                        + "(165338,'Suicidal ideation','Suicidal Ideation',NULL),(5242,'Respiratory rate',NULL,NULL),(136201,'Labored Breathing','Labored breathing',NULL),(122863,'Wheezing','Wheezing',NULL),"
                        + "(165314,'Inter/Subcostal recession','Intercostal (sub)Recession',NULL),(143050,'CYANOSIS','Cyanosis',NULL),(165315,'Auscultation','Auscultation Findings',NULL),"
                        + "(165319,'Disorientated  in TPP','Disoriented in TPP',NULL),(165320,'Impaired Consciousness',NULL,NULL),(126351,'Slurred Speech','Slurred speech',NULL),"
                        + "(5170,'NUCHAL RIGIDITY','Neck Stiffness',NULL),(165321,'Blindness 1 or 2 eyes','Blindness 1/2 Eyes',NULL),(165322,'Weakness/Paralysis','Weakness/paralysis',NULL),"
                        + "(165323,'Numbness of extremities','Numbness/Tingling',NULL),(165324,'Fisting/Spacsticity',NULL,NULL),(117655,'Hemiplegia',NULL,NULL),(147096,'Body Pale','Pale',NULL),"
                        + "(142630,'Dehydration','Dehydrated',NULL),(136443,'Jaundice','Jaunduce',NULL),(165311,'Edematous','Edematous',NULL),(165332,'Genital Lesions','Genital lesions',NULL),"
                        + "(165573,'Genital Discharge','Genital sores/discharge',NULL),(5008,'Hepatomegaly','Hepatomegaly',NULL),(165328,'Tenderness','Tenderness',NULL),(5009,'Splenomegaly','Spenomegaly',NULL),"
                        + "(165329,'Distention','Dystention',NULL),(136522,'Irregular Heartbeat','Irregular pulse',NULL),(130792,'Parotid Swelling','Parotid swelling',NULL),(135488,'Lymphadenopathy','Discharge',NULL),"
                        + "(1068,'Symptomatic','Symptomatic',NULL),(5006,'Asymptomatic','Asymptomatic',NULL),(165307,'AIDS Defining Illness','AIDS Defining illness',NULL),(131768,'Opportunistic Infectious Disease','Opportunistic Infections',NULL),"
                        + "(1221,'WHO STAGE 2 PEDS',NULL,NULL),(165341,'Lab Evaluation','Lab Evaluation',NULL),(165342,'OI Therapy','OI Therapy',NULL),(164800,'Tuberculosis screening status','TB Screening',NULL),"
                        + "(165343,'Admission','Admission',NULL),(165344,'OI Prophylaxis','OI Prophylaxis ',NULL),(165345,'Symptomatic treatment','Symptomatic treatment/Pain control',NULL),"
                        + "(165346,'Palliative care','Palliative care',NULL),(5490,'Psychosocial counseling',NULL,NULL),(165347,'OVC Care',NULL,NULL),(165348,'End of Life Care',NULL,NULL),"
                        + "(12,'X-ray, chest','CXR',NULL),(165350,'Counseling','Counselling',NULL),(5488,'ADHERENCE COUNSELING',NULL,NULL),(1356,'HIV Test',NULL,NULL),(1380,'NUTRITION COUNSELING',NULL,NULL),"
                        + "(165349,'Peer Health Support',NULL,NULL),(1272,'REFERRALS ORDERED','Referrals',NULL),(5486,'SOCIAL SUPPORT SERVICES',NULL,NULL),"
                        + "(5576,'ARV FOR MOTHER',NULL,NULL),(165353,'General Medical Follow-Up','General Medical Follow up',NULL),(165303,'Antiretroviral Therapy','ARV Therapy',NULL),"
                        + "(165354,'Pending Lab Results','Pending lab results',NULL),(165356,'Continue current treatment',NULL,NULL),(165357,'Start new treatment','Start new treatment',NULL),"
                        + "(162904,'Restart medication','Restart treatment',NULL),(165358,'Change treatment','Change treatment',NULL),(165360,'Stop ART','Stop treatment',NULL),(968,'COW MILK',NULL,NULL),"
                        + "(1107,'None','None',NULL),(1220,'WHO STAGE 1 PEDS',NULL,NULL),(5555,'Married','Married',NULL),(1060,'Living with partner ','Co-habiting',NULL),(5615,'Not married','Single',NULL),"
                        + "(1107,'None','None',NULL),(1185,'TREATMENT','Treatment',NULL),(1391,'PHYSICAL EXAM','peadditional',NULL),(161011,'Free text comment','pedadditional',NULL),"
                        + "(142412,'Diarrhea','Diarrhea','dur_diarh'),(832,'Weight loss','Recent Weight Loss','dur_weigthl'),(159298,'visual impairment','New Visual Imparity','dur_visimp'),"
                        + "(864,'GENITAL SORES','Genital Discharge','dur_gensore'),(165326,'Cardiovascular','pecardiovascular',NULL),(165331,'Glands','pedpeglands',NULL),"
                        + "(165327,'Head/Eye/ENT','peheadeye',NULL),(165333,'Genitalia','pegenitalia',NULL),(165316,'Respiratory','gerespiratory',NULL),(165325,'Neurological','geneurological',NULL),"
                        + "(165339,'Mental status','gementalstatus',NULL),(165318,'Skin','peskin',NULL),(165313,'General appearance','pegenappearance',NULL);", Statement.RETURN_GENERATED_KEYS);               
                icconcept.executeUpdate();
                DatabaseHandler.getInstance().getApinConnection().commit();            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public  void createPharmacyConcept() {
        try {
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            
                dropTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                String.format("DROP TABLE IF EXISTS %s", "parmacy_concepts"));
                dropTable.execute();
            
                String sql_stmt = "CREATE TABLE `parmacy_concepts` (\n" +
                "  `concept_id` double DEFAULT NULL,\n" +
                "  `concept` varchar(255) DEFAULT NULL,\n" +
                "  `cisproconcepts` varchar(255) DEFAULT NULL,\n" +
                "  `conceptvalue` varchar(255) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

                statement = DatabaseHandler.getInstance().getApinConnection().createStatement();
                statement.executeUpdate(sql_stmt);
                
                icconcept = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                "insert  into `parmacy_concepts`(`concept_id`,`concept`,`cisproconcepts`,`conceptvalue`) values "
                        + "(164181,'Visit type','visittype',NULL),(165050,'Prenancy/Breastfeeding Status','pregyn',NULL),"
                        + "(165774,'Pick Up Reason',NULL,NULL),(165836,'Number of missed doses per month',NULL,NULL),"
                        + "(165832,'Adherence counseling offering',NULL,NULL),(165720,'Treatment age group',NULL,NULL),"
                        + "(165708,'Current Regimen Line',NULL,NULL),(164507,'Child 1st line ARV regimen',NULL,NULL),"
                        + "(162240,'Human immunodeficiency virus treatment regimen','Human_immunodeficiency_virus_treatment',NULL),"
                        + "(159368,'Medication duration','regduration1',NULL),(165725,'ARV Drug Strength','drugdose1',NULL),"
                        + "(165723,'Drug Frequency','frequency',NULL),(160856,'Quantity of medication prescribed per dose','drugdose1',NULL),"
                        + "(1443,'Medication dispensed','drugno1',NULL),(165724,'ARV Medication',NULL,NULL),(165726,'Drugs for OI Prophylaxis',NULL,NULL),"
                        + "(165725,'ARV Drug Strength','drugdose1',NULL),(159368,'Medication duration','regduration1',NULL),(1443,'Medication dispensed','drugno1',NULL),"
                        + "(165723,'Drug Frequency','frequency',NULL),(160856,'Quantity of medication prescribed per dose','drugdose1',NULL),(165727,'OI drugs','oidrug',NULL),"
                        + "(165726,'Drugs for OI Prophylaxis',NULL,NULL),(1443,'Medication dispensed','drugno1',NULL),(165723,'Drug Frequency','frequency',NULL),(165727,'OI drugs','oidrug',NULL),"
                        + "(159368,'Medication duration','regduration1',NULL),(165725,'ARV Drug Strength','drugdose1',NULL),(160856,'Quantity of medication prescribed per dose','drugdose1',NULL),"
                        + "(165728,'Drug Prescription for Anti-TB drugs',NULL,NULL),(165723,'Drug Frequency','frequency',NULL),(165304,'Anti-TB Drugs','tbdrug',NULL),(1443,'Medication dispensed','drugno1',NULL),"
                        + "(159368,'Medication duration','regduration1',NULL),(160856,'Quantity of medication prescribed per dose','drugdose1',NULL),"
                        + "(165725,'ARV Drug Strength','drugdose1',NULL),(165728,'Drug Prescription for Anti-TB drugs',NULL,NULL),(165725,'ARV Drug Strength','drugdose1',NULL),"
                        + "(165304,'Anti-TB Drugs','tbdrug',NULL),(165723,'Drug Frequency','frequency',NULL),(160856,'Quantity of medication prescribed per dose','drugdose1',NULL),"
                        + "(159368,'Medication duration','regduration1',NULL),(1443,'Medication dispensed','drugno1',NULL),(164989,'Orered date','visitdate',NULL),"
                        + "(164989,'Orered date','visitdate',NULL),(165257,'CTX prophylaxis','CTX',NULL),(76488,'FLUCONAZOLE','INH',NULL),(160862,'OD (once daily)','OD',NULL),"
                        + "(165633,'30/60/50mg','300/300/600',NULL),(165633,'30/60/50mg','300/300/600mg',NULL),(165620,'150/300/200mg','60/30mg/50mg/5ml',NULL),"
                        + "(164505,'Lamivudine / Efavirenz / Tenofovir','TDF/3TC/EFV','0.000000'),(161364,'lamivudine / tenofovir','3TC/AZT/NVP','0.000000'),"
                        + "(NULL,'LAMIVUDINE / ZIDOVUDINE','TDF/3TC','0.000000'),(630,'ABACAVIR / LAMIVUDINE','3TC/AZT','0.000000'),(103166,'EFAVIRENZ','EFV','0.000000'),"
                        + "(75523,'ZIDOVUDINE','AZT','0.000000'),(159809,'ATAZANAVIR AND RITONAVIR','ATV/r','0.000000'),(794,'LOPINAVIR / RITONAVIR','LPV/r','0.000000'),"
                        + "(74258,'DARUNAVIR','DRV','0.000000'),(83412,'RITONAVIR','RTV','0.000000'),(162796,'Darunavir / Ritonavir','DRV/r','0.000000'),"
                        + "(80586,'NEVIRAPINE','NVP','0.000000'),(70056,'ABACAVIR','ABC','0.000000'),(154378,'Raltegravir','RTV','0.000000'),"
                        + "(165631,'Dolutegravir','DTC','0.000000'),(164505,'Lamivudine / Efavirenz / Tenofovir','FDC (3TC/AZT/NVP)','4.000000'),"
                        + "(630,'ABACAVIR / LAMIVUDINE','ABC/3TC','0.000000'),(1443,'Medication dispensed','oiqty',NULL),(159368,'Medication duration','oiduration',NULL),"
                        + "(1443,'Medication dispensed','tbqty',NULL),(165724,'ARV Medication','drugname1',NULL),(165728,'Drug Prescription FOR Anti-TB drugs','Drug_prescription_for_Anti-TB_drugs',NULL),"
                        + "(165726,'Drugs FOR OI Prophylaxis','Drugs_FOR_OI_Prophylaxis',NULL),"
                        + "(164989,'Orered DATE','ordered_date',NULL),"
                        + "(1675,'RHZE/RH','INH',NULL),(NULL,NULL,'',NULL);");       
                icconcept.executeUpdate();
                DatabaseHandler.getInstance().getApinConnection().commit();            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
 public  void createAdultICEConcept() {
        try {
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            
                dropTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                String.format("DROP TABLE IF EXISTS %s", "concept_adultice"));
                dropTable.execute();
            
                String sql_stmt = "CREATE TABLE IF NOT EXISTS `concept_adultice` (\n" +
"  `concept_id` int(11) DEFAULT NULL,\n" +
"  `name` varchar(6885) DEFAULT NULL,\n" +
"  `cisproconcepts` varchar(2295) DEFAULT NULL\n" +
") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

                statement = DatabaseHandler.getInstance().getApinConnection().createStatement();
                statement.executeUpdate(sql_stmt);
                
                icconcept = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                "INSERT INTO `concept_adultice` (`concept_id`, `name`, `cisproconcepts`) VALUES\n" +
"(512, 'Rash', 'Rash'),\n" +
"(832, 'Weight loss', 'Recent Weight Loss'),\n" +
"(864, 'GENITAL SORES', 'Genital Sores'),\n" +
"(1065, 'Yes', 'Yes'),\n" +
"(1066, 'No', 'No'),\n" +
"(1067, 'Unknown', 'Uncertain'),\n" +
"(1107, 'None', 'None*'),\n" +
"(1434, 'CURRENTLY PREGNANT', 'currentlypregnant'),\n" +
"(5632, 'CURRENTLY BREASTFEEDING CHILD', 'currentlybreastf'),\n" +
"(133027, 'Night sweats', 'Night Sweats'),\n" +
"(133473, 'Nausea and Vomiting', 'Nausea/Vomitting'),\n" +
"(139084, 'Headache', 'Headache'),\n" +
"(140238, 'Fever', 'Fever/Chills'),\n" +
"(142373, 'Difficulty breathing', 'Shortness of Breath'),\n" +
"(142412, 'Diarrhea', 'Chronic Diarrhea'),\n" +
"(143264, 'Cough', 'Cough'),\n" +
"(1438, 'gestational age wks', 'gestationalage'),\n" +
"(879, 'Itching', 'Itching'),\n" +
"(159298, 'visual impairment', 'New Visual Imparity'),\n" +
"(159423, 'HIV status disclosed to partner', 'Spouse'),\n" +
"(159424, 'HIV status disclosed to other family members', 'Family Member'),\n" +
"(159425, 'HIV status disclosed to friends or neighbors', 'Friend'),\n" +
"(512, 'Skin rash', 'Rash'),\n" +
"(1427, 'Last menstrual period', 'lastmenstrualperiod'),\n" +
"(5596, 'Expected date of delivery', 'edd'),\n" +
"(105281, 'CTX', 'CTX'),\n" +
"(5622, 'Other', 'otherspec'),\n" +
"(162724, 'Health facility name', 'prevfacilityname'),\n" +
"(163074, 'Numbness and tingling of skin', 'Numbness/Tingling'),\n" +
"(164800, 'Tuberculosis screening status', 'patacc_tb'),\n" +
"(165045, 'Additional Comments', 'addiotnalsymcmments'),\n" +
"(165240, 'PMTCT Only', 'Earlier ARV not transfer in'),\n" +
"(160221, 'Past medical history (text)', 'pastmedicalhist'),\n" +
"(165303, 'ART', 'ART'),\n" +
"(165304, 'Anti-TB Drugs', 'Anti-TB'),\n" +
"(165426, 'Relevant Family History', 'relfamilyhist'),\n" +
"(165586, 'Previous ARV exposure', 'prevarvexp'),\n" +
"(165601, 'Hospitalization', 'hospitalization'),\n" +
"(165675, 'Patient has any known drug allergies', 'knwnallergy'),\n" +
"(165676, 'Patient has disclosed HIV status to spiritual leader', 'Spiritual Leader'),\n" +
"(165677, 'Patient has not disclosed HIV status', 'No one'),\n" +
"(165679, 'HIV status can be discussed with', 'hivstatdiscussion'),\n" +
"(165680, 'Presenting Complaints', 'presentingcomplaint'),\n" +
"(165712, 'Earlier ARV but not a transfer in', 'Transfer in with records'),\n" +
"(165713, 'Has never received ARVs', 'Never recieved ARVs'),\n" +
"(165714, 'On Previous ARV exposure', 'prevarvtype'),\n" +
"(165829, 'Duration from', 'prevdurationofcarefromdate'),\n" +
"(165830, 'Duration to', 'prevdurationofcaretodate');");       
                icconcept.executeUpdate();
                DatabaseHandler.getInstance().getApinConnection().commit();            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
  
 public  void createLabConcept() {
        try {
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            
                dropTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                String.format("DROP TABLE IF EXISTS %s", "concept_lab"));
                dropTable.execute();
            
                String sql_stmt = "CREATE TABLE IF NOT EXISTS `concept_lab` (\n" +
"  `concept_id` double DEFAULT NULL,\n" +
"  `NAME` varchar(6885) DEFAULT NULL,\n" +
"  `cisproconcepts` varchar(6885) DEFAULT NULL,\n" +
"  `datatype` int(11) DEFAULT NULL\n" +
") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

                statement = DatabaseHandler.getInstance().getApinConnection().createStatement();
                statement.executeUpdate(sql_stmt);
                
                icconcept = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                "INSERT INTO `concept_lab` (`concept_id`, `NAME`, `cisproconcepts`, `datatype`) VALUES\n" +
"(45, 'URINE PREGNANCY TEST', 'Pregnancy', NULL),\n" +
"(299, 'VDRL', 'VDRL', NULL),\n" +
"(302, 'URINALYSIS ORDER', 'Urinalysis', NULL),\n" +
"(653, 'SERUM GLUTAMIC-OXALOACETIC TRANSAMINASE', 'SGOT', NULL),\n" +
"(654, 'SERUM GLUTAMIC-PYRUVIC TRANSAMINASE', 'ALT/SGPT', NULL),\n" +
"(655, 'TOTAL BILIRUBIN', 'Total Bilirubin', NULL),\n" +
"(678, 'WHITE BLOOD CELLS', 'WBC', NULL),\n" +
"(678, 'WBC', 'WBC', NULL),\n" +
"(729, 'Platelets', 'Platelets', NULL),\n" +
"(730, 'CD4 PERCENT', 'CD4%', NULL),\n" +
"(785, 'ALKALINE PHOSPHATASE', 'Alk. Phosphatase', NULL),\n" +
"(856, 'HIV VIRAL LOAD', 'Viral Load', NULL),\n" +
"(856, 'VIRAL LOAD', 'Viral Load', NULL),\n" +
"(887, 'GLUCOSE', 'GLUCOSE', NULL),\n" +
"(1006, 'TOTAL CHOLESTEROL', 'Total Cholesterol', NULL),\n" +
"(1007, 'HDL', 'HDL', NULL),\n" +
"(1007, 'HDL', 'HDL', NULL),\n" +
"(1008, 'LOW-DENSITY LIPOPROTEIN CHOLESTEROL', 'LDL', NULL),\n" +
"(1008, 'LDL', 'LDL', NULL),\n" +
"(1008, 'LDL', 'LDL', NULL),\n" +
"(1009, 'TRIGLYCERIDES', 'Triglyceride', NULL),\n" +
"(1009, 'TG', 'Triglyceride', NULL),\n" +
"(1009, 'TG', 'Triglyceride', NULL),\n" +
"(1009, 'TRIG', 'Triglyceride', NULL),\n" +
"(1015, 'PCV', 'PCV/Hb', NULL),\n" +
"(1021, 'LYMPHOCYTES', NULL, NULL),\n" +
"(1022, 'POLYMORPHONUCLEAR CELLS', 'Polymorph', NULL),\n" +
"(1023, 'MONOCYTES', 'Monocytes', NULL),\n" +
"(1024, 'EOSINOPHILS', 'Eosinophils', NULL),\n" +
"(1025, 'BASOPHILS', 'Basophils', NULL),\n" +
"(1132, 'NA', 'Na+', NULL),\n" +
"(1132, 'NA', 'Na+', NULL),\n" +
"(1133, 'K', 'K+', NULL),\n" +
"(1875, 'URINE PROTEIN (DIP STICK)', 'Urinalysis: PROTEIN', NULL),\n" +
"(5497, 'CD4 COUNT', 'CD4', NULL),\n" +
"(5497, 'CD4', 'CD4', NULL),\n" +
"(5497, 'CD4', 'CD4', NULL),\n" +
"(159430, 'HBsAg', 'HBsAG (*At Baseline)', NULL),\n" +
"(159661, 'creatinine measurement, urine, 24 hour', NULL, NULL),\n" +
"(160053, 'Fasting Glucose', 'Fasting Glucose', NULL),\n" +
"(164982, 'Reported by', 'reportedby', NULL),\n" +
"(164983, 'Checked by', 'checkedby', NULL),\n" +
"(1542, 'Occupation', 'jobstatus', NULL),\n" +
"(164984, 'Date Checked', 'checkdate', NULL),\n" +
"(164989, 'Orered date', 'cliniciandate', NULL),\n" +
"(164989, 'Ordered date', 'cliniciandate', NULL),\n" +
"(165414, 'Date Reported', 'reportdate', NULL),\n" +
"(165562, 'HCV status', 'HCV Antibody', NULL),\n" +
"(1054, 'Civil status', 'maritalstatus', NULL),\n" +
"(1712, 'HIGHEST EDUCATION LEVEL', 'educationallevel', NULL),\n" +
"(1712, 'Education Level', 'educationallevel', NULL),\n" +
"(1712, 'Current education level', 'educationallevel', NULL),\n" +
"(1712, 'Highest Educational Level', 'educationallevel', NULL),\n" +
"(163260, 'Date first seen by healthcare provider', 'enroldate', NULL),\n" +
"(163260, 'Date of first visit', 'enroldate', NULL),\n" +
"(164981, 'Name of Clinician', 'Clinicianname', NULL),\n" +
"(161199, 'Cytology', 'Cytology: VIA/Pap Smear', NULL),\n" +
"(162476, 'Specimen sources', 'pcrsampletype', NULL),\n" +
"(159951, 'specimen date', 'pcrsamplecolldate', NULL),\n" +
"(1366, 'MALARIA SMEAR, QUALITATIVE', 'Malaria smear', NULL),\n" +
"(1319, 'LYMPHOCYTE COUNT', 'Lymphocytes', NULL),\n" +
"(164364, 'Serum creatinine (mg/dL)', 'Creatinine', NULL);");       
                icconcept.executeUpdate();
                DatabaseHandler.getInstance().getApinConnection().commit();            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
 public  void createLabCheckBoxesConcept() {
        try {
            DatabaseHandler.getInstance().getApinConnection().setAutoCommit(false);
            
                dropTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                String.format("DROP TABLE IF EXISTS %s", "concept_lab_checkboxes"));
                dropTable.execute();
            
                String sql_stmt = "CREATE TABLE IF NOT EXISTS `concept_lab_checkboxes` (\n" +
"  `concept_id` int(11) DEFAULT NULL,\n" +
"  `name` varchar(20655) DEFAULT NULL,\n" +
"  `cisproconcepts` varchar(6885) DEFAULT NULL\n" +
") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

                statement = DatabaseHandler.getInstance().getApinConnection().createStatement();
                statement.executeUpdate(sql_stmt);
                
                icconcept = DatabaseHandler.getInstance().getApinConnection().prepareStatement(
                "INSERT INTO `concept_lab_checkboxes` (`concept_id`, `name`, `cisproconcepts`) VALUES\n" +
"(1542, 'Occupation', 'jobstatus'),\n" +
"(164981, 'Name of Clinician', 'Clinicianname'),\n" +
"(164982, 'Reported by', 'reportedby'),\n" +
"(164983, 'Checked by', 'checkedby'),\n" +
"(164984, 'Date Checked', 'checkdate'),\n" +
"(164989, 'Orered date', 'cliniciandate'),\n" +
"(165414, 'Date Reported', 'reportdate'),\n" +
"(165731, 'CD4+ cell count Order', 'CD4'),\n" +
"(165732, 'WBC Order', 'WBC'),\n" +
"(165733, 'Lymphocytes order', 'Lymphocytes'),\n" +
"(165734, 'Monocytes Order', 'Monocytes'),\n" +
"(165735, 'Eosinophils Order', 'Eosinophils'),\n" +
"(165736, 'Basophils Order', 'Basophils'),\n" +
"(165737, 'PCV/Hb Order', 'PCV/Hb'),\n" +
"(165738, 'HCV antibody Order', 'HCV Antibody'),\n" +
"(165739, 'VDRL Order', 'VDRL'),\n" +
"(165740, 'ALT/SGPT Order', 'ALT/SGPT'),\n" +
"(165741, 'Alk. Phosphatase Order', 'Alk. Phosphatase'),\n" +
"(165742, 'Na+ Order', 'Na+'),\n" +
"(165743, 'Fasting Glucose Order', 'Fasting Glucose'),\n" +
"(165744, 'LDL Order', 'LDL'),\n" +
"(165745, 'Glucose Order', 'GLUCOSE'),\n" +
"(165746, 'Cytology/Pap Smear Order', 'Cytology: VIA/Pap Smear'),\n" +
"(165747, 'Pregnancy Order', 'Pregnancy'),\n" +
"(165748, 'CD4% Order', 'CD4%'),\n" +
"(165749, 'Polymorphs Order', 'Polymorph'),\n" +
"(165754, 'Platelets Order', 'Platelets'),\n" +
"(165755, 'HBsAG Order', 'HBsAG (*At Baseline)'),\n" +
"(165756, 'Creatinine Order', 'Creatinine'),\n" +
"(165757, 'AST/SGOT Order', 'AST/SGOT'),\n" +
"(165758, 'Total Bilirubin Order', 'Total Bilirubin'),\n" +
"(165759, 'K+ Order', 'K+'),\n" +
"(165760, 'Total Cholesterol Order', 'Total Cholesterol'),\n" +
"(165761, 'HDL Order', 'HDL'),\n" +
"(165762, 'Protein Order', 'Urinalysis: PROTEIN'),\n" +
"(165763, 'Triglyceride Order', 'Triglyceride'),\n" +
"(165764, 'Malaria smear Order', 'Malaria smear'),\n" +
"(165765, 'Viral Load Order', 'Viral Load');");       
                icconcept.executeUpdate();
                DatabaseHandler.getInstance().getApinConnection().commit();            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
 
    
}
