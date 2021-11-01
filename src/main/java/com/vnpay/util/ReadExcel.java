package com.vnpay.util;

import com.vnpay.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {
    private static Logger logger = LogManager.getLogger(SendDataToServer.class);
    public static List<Message> readData() throws IOException {
        List<Message> messages = new ArrayList<>();
        try {
            Files.move(Paths.get("Sent/demo.xlsx"),Paths.get("Processing/demo.xlsx"), StandardCopyOption.REPLACE_EXISTING);
            File file = new File("Processing/demo.xlsx");
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
//            System.out.println(firstCell.getStringCellValue());
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Message message = new Message();
                message.setKeyword(currentRow.getCell(0).getStringCellValue());
                message.setSender(currentRow.getCell(1).getStringCellValue());
                message.setDestination(currentRow.getCell(2).getStringCellValue());
                message.setShortMessage(currentRow.getCell(3).getStringCellValue());
                messages.add(message);
            }
            workbook.close();
            return messages;
        } catch (FileNotFoundException e) {
            logger.error("Can't found file",e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Read data from file",e);
            e.printStackTrace();
        }
        return messages;
    }
}
