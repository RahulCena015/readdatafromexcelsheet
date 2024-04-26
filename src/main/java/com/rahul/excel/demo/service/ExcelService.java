package com.rahul.excel.demo.service;

import com.rahul.excel.demo.model.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public Student readExcelData(MultipartFile file) throws IOException {
        Student rowData = new Student();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // assuming the data is in the first sheet

        for (Row row : sheet) {
            if ("StudentName".equalsIgnoreCase(row.getCell(0).getStringCellValue())) {
                rowData.setStudentName(row.getCell(1).getStringCellValue());
            }
            if ("Department".equalsIgnoreCase(row.getCell(0).getStringCellValue())) {
                rowData.setDepartment(row.getCell(1).getStringCellValue());
            }
            if ("Roll".equalsIgnoreCase(row.getCell(0).getStringCellValue())) {
                rowData.setRoll(row.getCell(1).getNumericCellValue());
            }
        }
        System.out.println(rowData.toString());
        workbook.close();
        return rowData;
    }
}
