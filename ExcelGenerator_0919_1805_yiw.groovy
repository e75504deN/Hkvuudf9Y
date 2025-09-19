// 代码生成时间: 2025-09-19 18:05:58
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import groovy.io.FileType
import java.io.*

/**
 * An Excel generator class that allows for the creation of Excel files.
 */
class ExcelGenerator {

    /**
     * Generates an Excel file with the specified data.
     *
     * @param fileName The name of the Excel file to generate.
     * @param data A List of Lists representing the rows and columns of the Excel file.
     * @param sheetName The name of the sheet in the Excel workbook.
     * @throws IOException If an I/O error occurs when creating the file.
     */
    void generateExcelFile(String fileName, List<List<Object>> data, String sheetName) {
        try {
            // Create a new workbook and sheet
            Workbook workbook = new XSSFWorkbook()
            Sheet sheet = workbook.createSheet(sheetName)

            // Iterate over the data and populate the sheet
            int rowNum = 0
            for (List<Object> rowData : data) {
                Row row = sheet.createRow(rowNum++)
                int cellNum = 0
                for (Object cellData : rowData) {
                    Cell cell = row.createCell(cellNum++)
                    cell.setCellValue(cellData.toString())
                }
            }

            // Write the workbook to the specified file
            FileOutputStream out = new FileOutputStream(fileName)
            workbook.write(out)
            out.close()
            workbook.close()

            println "Excel file '$fileName' generated successfully."
        } catch (Exception e) {
            // Log the exception and rethrow it to be handled by the caller
            println "Error generating Excel file: ${e.message}"
            throw e
        }
    }

    /**
     * Main method to test the ExcelGenerator class.
     *
     * @param args Command line arguments (not used).
     */
    static void main(String[] args) {
        def excelGen = new ExcelGenerator()
        def data = [["Header1", "Header2", "Header3"], ["Data1", "Data2", "Data3"], ["Data4", "Data5", "Data6"]]
        excelGen.generateExcelFile("example.xlsx", data, "SampleSheet")
    }
}
