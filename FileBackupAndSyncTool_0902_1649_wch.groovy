// 代码生成时间: 2025-09-02 16:49:35
package com.example.tools

import groovy.io.FileType
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.TrueFileFilter

/**
 * File Backup and Sync Tool using Java and Grails framework.
 * This tool can backup and sync files from source to destination.
 */
class FileBackupAndSyncTool {

    /**
     * Source directory path.
     */
    String sourceDir

    /**
     * Destination directory path.
     */
    String destDir

    /**
     * Constructor to initialize source and destination directories.
     *
     * @param sourceDir The source directory path.
     * @param destDir The destination directory path.
     */
    FileBackupAndSyncTool(String sourceDir, String destDir) {
        this.sourceDir = sourceDir
        this.destDir = destDir
    }

    /**
     * Backups files from source directory to destination directory.
     *
     * @return The number of files backed up.
     */
    int backupFiles() {
        int backupCount = 0
        new File(sourceDir).eachFileMatch(FileType.FILES) { file ->
            try {
                FileUtils.copyFileToDirectory(file, new File(destDir))
                backupCount++
            } catch (IOException e) {
                println "Error backing up file ${file.name}: ${e.message}"
            }
        }
        return backupCount
    }

    /**
     * Syncs files between source and destination directories.
     * This method ensures that the destination directory has the same files as the source directory.
     *
     * @return The number of files synced.
     */
    int syncFiles() {
        int syncCount = 0
        new File(sourceDir).eachFileMatch(FileType.FILES) { file ->
            try {
                // Check if the file exists in the destination directory
                if (!new File(destDir, file.name).exists()) {
                    FileUtils.copyFileToDirectory(file, new File(destDir))
                    syncCount++
                }
            } catch (IOException e) {
                println "Error syncing file ${file.name}: ${e.message}"
            }
        }
        return syncCount
    }

    /**
     * Removes files from the destination directory that are not present in the source directory.
     *
     * @return The number of files removed.
     */
    int removeOrphanFiles() {
        int removeCount = 0
        new File(destDir).eachFileMatch(FileType.FILES) { destFile ->
            if (!new File(sourceDir, destFile.name).exists()) {
                destFile.delete()
                removeCount++
            }
        }
        return removeCount
    }

    /**
     * Main method for running the file backup and sync tool.
     *
     * @param args The command line arguments.
     */
    static void main(String[] args) {
        if (args.length < 2) {
            println "Usage: java FileBackupAndSyncTool <sourceDir> <destDir>"
            return
        }

        String sourceDir = args[0]
        String destDir = args[1]
        new FileBackupAndSyncTool(sourceDir, destDir).with {
            println "Backed up ${backupFiles()} files."
            println "Synced ${syncFiles()} files."
            println "Removed ${removeOrphanFiles()} orphan files."
        }
    }
}
