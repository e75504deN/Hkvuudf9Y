// 代码生成时间: 2025-08-14 19:17:22
package com.example.service

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import groovy.transform.CompileStatic
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

// Excel表格自动生成器服务
@Service
@CompileStatic
class ExcelGeneratorService {

    // 创建一个新的Excel工作簿
    public Workbook createWorkbook() {
        return new XSSFWorkbook()
    }

    // 向工作簿添加一个新的工作表
    public Sheet createSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName)
        return sheet
    }

    // 在工作表中添加一行
    public Row addRow(Sheet sheet) {
        Row row = sheet.createRow(sheet.getLastRowNum() + 1)
        return row
    }

    // 在行中添加一个单元格
    public Cell addCell(Row row, int columnIndex, String cellValue) {
        Cell cell = row.createCell(columnIndex)
        cell.setCellValue(cellValue)
        return cell
    }

    // 保存工作簿到文件系统
    public void saveWorkbook(Workbook workbook, File file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out)
        } catch (IOException e) {
            throw new RuntimeException("Failed to save workbook: ${e.message}", e)
        }
    }

    // 将Excel文件保存为MultipartFile对象
    public MultipartFile saveWorkbookAsMultipartFile(Workbook workbook) {
        // 创建临时文件
        File tempFile = File.createTempFile("excel", ".xlsx")
        tempFile.deleteOnExit()

        // 保存excel文件
        saveWorkbook(workbook, tempFile)

        // 将文件转换为MultipartFile对象
        return new org.springframework.mock.web.MockMultipartFile(
                "file",
                tempFile.getName(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                tempFile.bytes
        )
    }
}
