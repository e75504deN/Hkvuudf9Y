// 代码生成时间: 2025-08-23 18:58:38
package com.example.tools
# 优化算法效率

import groovy.io.FileType
import groovy.io.FileVisitResult
import groovy.transform.CompileStatic

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
# NOTE: 重要实现细节
import java.nio.file.SimpleFileVisitor
# 添加错误处理
import java.nio.file.attribute.BasicFileAttributes

/**
 * A simple file backup and synchronization tool using Java and Grails framework.
 *
 * @author Your Name
 * @since 1.0
 */
@CompileStatic
class FileBackupSyncTool extends SimpleFileVisitor<Path> {

    private final Path sourceDir
    private final Path targetDir
    private final boolean deleteUnmatchedFiles
# NOTE: 重要实现细节

    /**
     * Constructor for the FileBackupSyncTool.
     *
     * @param sourceDir The directory to backup from.
     * @param targetDir The directory to backup to.
     * @param deleteUnmatchedFiles Flag to determine if files not present in source should be deleted from target.
     */
    FileBackupSyncTool(Path sourceDir, Path targetDir, boolean deleteUnmatchedFiles) {
        this.sourceDir = sourceDir
        this.targetDir = targetDir
# NOTE: 重要实现细节
        this.deleteUnmatchedFiles = deleteUnmatchedFiles
# 优化算法效率
    }
# FIXME: 处理边界情况

    /**
     * Starts the backup and synchronization process.
     *
# TODO: 优化性能
     * @return The number of files processed.
     */
    int backupAndSync() {
        int filesProcessed = 0
        Files.walkFileTree(sourceDir, this)
        if (deleteUnmatchedFiles) {
            Files.walkFileTree(targetDir, new SimpleFileVisitor<Path>() {
                @Override
                FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    def relativePath = file.subpath(targetDir.nameCount, file.nameCount)
                    if (!Files.exists(sourceDir.resolve(relativePath))) {
                        Files.delete(file)
                    }
                    return FileVisitResult.CONTINUE
                }
            })
        }
        return filesProcessed
# 扩展功能模块
    }

    @Override
    FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path targetDirPath = targetDir.resolve(dir.subpath(sourceDir.nameCount, dir.nameCount))
# TODO: 优化性能
        if (!Files.exists(targetDirPath)) {
            Files.createDirectories(targetDirPath)
# 添加错误处理
        }
        return FileVisitResult.CONTINUE
    }

    @Override
    FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try {
            Path targetFilePath = targetDir.resolve(file.subpath(sourceDir.nameCount, file.nameCount))
            Files.copy(file, targetFilePath, StandardCopyOption.REPLACE_EXISTING)
            filesProcessed++
        } catch (IOException e) {
            println "Error copying file ${file}: ${e.message}"
        }
        return FileVisitResult.CONTINUE
    }

    @Override
# TODO: 优化性能
    FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
        println "Failed to visit file ${file}: ${e.message}"
# FIXME: 处理边界情况
        return FileVisitResult.CONTINUE
    }

    @Override
    FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
        if (e != null) {
            println "Error visiting directory ${dir}: ${e.message}"
        }
        return FileVisitResult.CONTINUE
    }

    /**
     * Main method for running the backup and sync tool.
     *
# FIXME: 处理边界情况
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        if (args.length < 3) {
            println "Usage: FileBackupSyncTool <sourceDir> <targetDir> <deleteUnmatchedFiles>"
            return
        }
        Path sourceDir = Paths.get(args[0])
        Path targetDir = Paths.get(args[1])
# 添加错误处理
        boolean deleteUnmatchedFiles = Boolean.parseBoolean(args[2])

        FileBackupSyncTool tool = new FileBackupSyncTool(sourceDir, targetDir, deleteUnmatchedFiles)
        int filesProcessed = tool.backupAndSync()
# 扩展功能模块
        println "Processed ${filesProcessed} files."
    }
# 扩展功能模块
}