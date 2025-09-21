// 代码生成时间: 2025-09-21 17:44:18
import grails.io.IOUtils
import groovy.io.FileType
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.io.IOException

// 批量文件重命名工具
class BatchFileRenamer {

    // 重命名文件的函数
    void renameFiles(Path startPath, String oldName, String newName) {
        // 检查起始路径是否存在
        if (!Files.exists(startPath)) {
            println 'Start directory does not exist.'
            return
        }

        // 通过文件访问器遍历文件
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = file.getFileName().toString()
                if (fileName.contains(oldName)) {
                    String newFileName = fileName.replace(oldName, newName)
                    Path newFilePath = Paths.get(file.toString().replace(fileName, newFileName))
                    Files.move(file, newFilePath, StandardCopyOption.REPLACE_EXISTING)
                    println "Renamed file from ${file} to ${newFilePath}"
                }
                return FileVisitResult.CONTINUE
            }
        })
    }

    // 程序入口点
    static void main(String[] args) {
        if (args.length < 3) {
            println 'Usage: BatchFileRenamer <startPath> <oldName> <newName>'
            return
        }

        // 解析命令行参数
        String startPath = args[0]
        String oldName = args[1]
        String newName = args[2]

        // 创建工具实例并执行重命名操作
        new BatchFileRenamer().renameFiles(Paths.get(startPath), oldName, newName)
    }
}
