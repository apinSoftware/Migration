/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class LabHelpers {

    private ImageView loadingHomeImage2;
    private int errorNum;
    private String allerros;
    private PreparedStatement checkPatientLabTable;
    private ResultSet rslab;
    private String columnConceptId;
    private String valueConceptId;
    private String incorrectvalue;
    private String columnName;
    private String pepid;

    ObservableList<HomepageController.Book> list = FXCollections.observableArrayList();
    private String columnConceptId2;
    private String valueConceptId2;

    public ObservableList<HomepageController.Book> cleanLabRecors(Label errorCount, ImageView loadingHomeImagep2) {
        loadingHomeImage2 = loadingHomeImagep2;
        List<List<String>> listOfLists = Lists.newArrayList();
        listOfLists.add(Lists.newArrayList("RowId", "PepID", "visit date", "Column", "WrongValue", "ErrorText"));
        // cleanPharmacy();
        int resultn = JOptionPane.showConfirmDialog(null, "Are you sure you want to migrate?");
        if (resultn == JOptionPane.YES_OPTION) {
            loadingHomeImage2.setVisible(true);

            int j = 0;
            errorNum = 0;
            allerros = "";
            int totalRowCount = 0;
            int numResults = 1;
            Pattern pattern = Pattern.compile(
                    "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
                    + "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
                    + "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
                    + "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

            try {
                checkPatientLabTable = DatabaseHandler.getInstance().getApinConnection().prepareStatement("SELECT labid, \n"
                        + "  pepid,\n"
                        + "  tests,\n"
                        + "  results,\n"
                        + "  reportedby,\n"
                        + "  reportdate,\n"
                        + "  checkedby,\n"
                        + "  checkdate,\n"
                        + "  clinicianname,\n"
                        + "  visitdate\n"
                        + "   \n"
                        + "FROM\n"
                        + "  `laboratory` \n"
                        + "WHERE `visitdate` !='0000-00-00' AND pepid IN \n"
                        + "  (SELECT \n"
                        + "    pepid \n"
                        + "  FROM\n"
                        + "    `patient`) \n"
                        + "GROUP BY labid,\n"
                        + "  pepid,\n"
                        + "  visitdate ");
                rslab = checkPatientLabTable.executeQuery();
                while (rslab.next()) {

                    if (!StringUtils.isNullOrEmpty(rslab.getString("pepid")) && !StringUtils.isNullOrEmpty(rslab.getString("visitdate"))) {

                        ///get patient_id from patient_identifier table
                        String[] excludedFields = new String[]{"visitdate", "reportedby", "reportdate", "checkedby", "clinicianname", "checkdate"};
                        String[] numericFields = new String[]{"results"};
                        String[] remove = new String[]{"results"};

                        String[] staticFields = new String[]{"HBsAG (*At Baseline)", "VDRL", "HCV Antibody", "HBsAG"};

                        ResultSetMetaData rsmd = rslab.getMetaData();
                        int columnCount = rsmd.getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            String name = rsmd.getColumnName(i);
                            if (name.equalsIgnoreCase("tests")) {
                                if (!StringUtils.isNullOrEmpty(rslab.getString(name))) {
                                    columnConceptId = Utility.conceptIdsforLabFORM(rslab.getString("tests").trim().replaceAll(", ", ",")).getConceptId();
                                    columnConceptId2 = Utility.conceptIdsforLabCheckboxFORM(rslab.getString("tests").trim().replaceAll(", ", ",")).getConceptId();

                                    if (StringUtils.isNullOrEmpty(columnConceptId) && StringUtils.isNullOrEmpty(columnConceptId2)) {
                                        incorrectvalue = rslab.getString(name);
                                        columnName = name;
                                        pepid = rslab.getString("pepid");
                                        allerros = "Invalid concept value";
                                        errorNum++;
                                        list.add(new HomepageController.Book(rslab.getString("labid"), pepid, columnName, incorrectvalue, allerros));
                                        String visitDate = (StringUtils.isNullOrEmpty(rslab.getString("visitdate"))) ? "-" : rslab.getString("visitdate");
                                        listOfLists.add(Lists.newArrayList(rslab.getString("labid"), visitDate, pepid, columnName, incorrectvalue, allerros));

                                    }
                                }

                            } else if (!StringUtils.isNullOrEmpty(rslab.getString(name)) && Arrays.asList(numericFields).contains(name) && !Arrays.asList(staticFields).contains(rslab.getString("tests"))) {
                                if (!pattern.matcher(rslab.getString(name.trim())).matches()) {
                                    incorrectvalue = rslab.getString(name);
                                    columnName = name + "->" + rslab.getString("tests");
                                    pepid = rslab.getString("pepid");
                                    allerros = "Invalid numeric value";
                                    errorNum++;
                                    list.add(new HomepageController.Book(rslab.getString("labid"), pepid, columnName, incorrectvalue, allerros));

                                    String visitDate = (StringUtils.isNullOrEmpty(rslab.getString("visitdate"))) ? "-" : rslab.getString("visitdate");
                                    listOfLists.add(Lists.newArrayList(rslab.getString("labid"), visitDate, pepid, columnName, incorrectvalue, allerros));

                                }
                            }
                        }

                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // errorlog.setText(allerros);
                            errorCount.setText(String.valueOf("You have " + errorNum + " unresolved errors"));
                        }
                    });
                    Errors.setUpdatableList(listOfLists);
                }

            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return list;
    }

    private static class pharmacyResult {

        public pharmacyResult() {
        }
    }

}
