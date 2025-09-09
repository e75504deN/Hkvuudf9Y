// 代码生成时间: 2025-09-10 00:23:16
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import groovy.io.FileType
import java.io.*

/**
 * Excel表格自动生成器服务
 *
 * @author YourName
 * @version 1.0
 */
class ExcelGeneratorService {

    /**
     * 创建一个Excel文件
     *
     * @param data 数据列表
     * @param sheetName 工作表名称
     * @param fileName 文件名称
     * @return 文件路径
     */
    String createExcel(List<List<String>> data, String sheetName, String fileName) {
        Workbook workbook = new XSSFWorkbook()
        Sheet sheet = workbook.createSheet(sheetName)

        try {
            int rowNum = 0
            int cellNum
            // 创建标题行
            Row headerRow = sheet.createRow(rowNum++)
            cellNum = 0
            headerRow.createCell(cellNum++).setCellValue('Column 1')
            headerRow.createCell(cellNum++).setCellValue('Column 2')
            // 根据数据创建其他行
            data.each { row ->
                Row dataRow = sheet.createRow(rowNum++)
                row.eachWithIndex { cellData, index ->
                    dataRow.createCell(index).setCellValue(cellData)
                }
            }
            // 将工作簿写入文件
            def file = new File(fileName)
            FileOutputStream outputStream = new FileOutputStream(file)
            workbook.write(outputStream)
            outputStream.close()
            workbook.close()
            return file.absolutePath
        } catch (Exception e) {
            e.printStackTrace()
            return null
        } finally {
            workbook.close()
        }
    }

    /**
     * 读取Excel文件并返回数据
     *
     * @param filePath 文件路径
     * @return 数据列表
     */
    List<List<String>> readExcel(String filePath) {
        FileInputStream inputStream = new FileInputStream(filePath)
        Workbook workbook = new XSSFWorkbook(inputStream)
        List<List<String>> data = new ArrayList<>()

        try {
            Sheet sheet = workbook.getSheetAt(0)
            Iterator<Row> rowIterator = sheet.iterator()
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next()
                List<String> rowData = new ArrayList<>()
                Iterator<Cell> cellIterator = row.cellIterator()
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next()
                    rowData.add(getCellValue(cell))
                }
                data.add(rowData)
            }
        } catch (Exception e) {
            e.printStackTrace()
            return null
        } finally {
            workbook.close()
            inputStream.close()
        }
        return data
    }

    /**
     * 获取单元格的值
     *
     * @param cell 单元格
     * @return 单元格的值
     */
    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case CellType.STRING:
                return cell.getStringCellValue()
            case CellType.NUMERIC:
                return String.valueOf(cell.getNumericCellValue())
            case CellType.BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue())
            case CellType.ERROR:
                return String.valueOf(cell.getErrorCellValue())
            default:
                return ''
        }
    }
}
