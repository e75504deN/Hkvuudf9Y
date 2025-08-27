// 代码生成时间: 2025-08-27 09:03:00
package analyzer

import groovy.io.FileType
import groovy.io.FileUtil

/**
 * 文本文件内容分析器
 *
 * @author YourName
 */
class TextFileAnalyzer {

    private File file

    /**
     * 构造函数，传入要分析的文件路径
     * @param filePath 文件路径
     */
    TextFileAnalyzer(String filePath) {
        this.file = new File(filePath)
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: ${filePath}")
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Provided path is not a file: ${filePath}")
        }
        if (!file.canRead()) {
            throw new IllegalArgumentException("File cannot be read: ${filePath}")
        }
    }

    /**
     * 分析文件内容，返回文件的基本信息和内容摘要
     * @return Map 包含文件信息和内容摘要的Map
     */
    Map<String, Object> analyze() {
        Map<String, Object> analysisResult = [:])
        analysisResult.totalLines = file.eachLine.count
        analysisResult.numberOfLinesWithText = file.eachLine.findAll { it.trim() }.size()
        analysisResult.numberOfLinesWithOnlySpaces = file.eachLine.findAll { it.trim().isEmpty() }.size()
        analysisResult.wordCount = file.text.split(/\s+/).size()
        analysisResult.characterCount = file.text.length()
        analysisResult
    }

    /**
     * 打印分析结果
     * @param analysisResult 分析结果Map
     */
    void printAnalysisResult(Map<String, Object> analysisResult) {
        println "Total lines: ${analysisResult.totalLines}"
        println "Lines with text: ${analysisResult.numberOfLinesWithText}"
        println "Lines with only spaces: ${analysisResult.numberOfLinesWithOnlySpaces}"
        println "Total words: ${analysisResult.wordCount}"
        println "Total characters: ${analysisResult.characterCount}"
    }
}

/**
 * 用于运行文本文件内容分析器的入口类
 */
class AnalyzerRunner {

    static void main(String[] args) {
        if (args.length < 1) {
            println "Usage: AnalyzerRunner <file_path>"
            return
        }
        String filePath = args[0]
        try {
            TextFileAnalyzer analyzer = new TextFileAnalyzer(filePath)
            Map<String, Object> analysisResult = analyzer.analyze()
            analyzer.printAnalysisResult(analysisResult)
        } catch (Exception e) {
            println e.message
        }
    }
}
