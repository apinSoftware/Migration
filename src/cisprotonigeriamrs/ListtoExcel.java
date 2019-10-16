/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cisprotonigeriamrs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author user
 */
public class ListtoExcel {
    
    public boolean toExcel(List<List> list, File saveLoc) {
        try {
           
             
            if (saveLoc == null) {
                return false;
            }
            if (!saveLoc.getName().endsWith(".xls")) {
                saveLoc = new File(saveLoc.getAbsolutePath() + ".xls");
            }

             Workbook workbook = new SXSSFWorkbook(200);
             Sheet spreadsheet = workbook.createSheet("sample");

               Row row = spreadsheet.createRow(0);

             for (int i = 0; i <  list.size();  i++) {
                 row = spreadsheet.createRow(i + 1);   
                 for (int j = 0; j < list.get(i).size(); j++) {
                     if(list.get(i).get(j)!= null) { 

                         row.createCell(j).setCellValue((list.get(i).get(j).toString())); 
                     }
                     else {
                         row.createCell(j).setCellValue("");
                     }   
                 }
             }

            FileOutputStream fileOut = new FileOutputStream(saveLoc);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();

             return true;
         } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "Error occurred during EXCEl export", "error", JOptionPane.INFORMATION_MESSAGE);
         }
         return false;

    }
    
}
