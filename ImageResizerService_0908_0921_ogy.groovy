// 代码生成时间: 2025-09-08 09:21:10
package com.example

import grails.transaction.Transactional
import groovy.transform.CompileStatic
import org.springframework.web.multipart.MultipartFile

/**
 * Image Resizer Service for batch resizing images.
 *
 * @author Your Name
 * @since 1.0.0
 */
@Transactional
@CompileStatic
class ImageResizerService {

    private static final int MAX_WIDTH = 800
    private static final int MAX_HEIGHT = 600
    private static final String TEMP_DIR = System.getProperty('java.io.tmpdir')

    /**
     * Resizes a list of images to a specified dimension.
     *
     * @param images The list of images to resize.
     * @param width The new width of the images.
     * @param height The new height of the images.
     * @return A list of resized image files.
     */
    List<File> resizeImages(List<MultipartFile> images, int width, int height) {
        List<File> resizedImages = []

        images.each { MultipartFile image ->
            try {
                File tempFile = saveImage(image)
                resizedImages << resizeImage(tempFile, width, height)
            } catch (Exception e) {
                log.error('Error resizing image: ' + image.originalFilename, e)
                // Handle the error appropriately, e.g., add the error to a list and return
                resizedImages << null
            }
        }

        return resizedImages
    }

    /**
     * Saves the uploaded image to a temporary file.
     *
     * @param image The image to save.
     * @return The saved temporary file.
     */
    private File saveImage(MultipartFile image) {
        File tempFile = File.createTempFile('resize', null, new File(TEMP_DIR))
        image.transferTo(tempFile)
        return tempFile
    }

    /**
     * Resizes an image to the specified dimension.
     *
     * @param imageFile The image file to resize.
     * @param width The new width of the image.
     * @param height The new height of the image.
     * @return The resized image file.
     */
    private File resizeImage(File imageFile, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile)
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)
            File resizedFile = new File(imageFile.parent, 'resized_' + imageFile.name)
            BufferedImageWriter.write(resizedFile, resizedImage)
            return resizedFile
        } catch (IOException e) {
            throw new RuntimeException('Error resizing image: ' + imageFile.name, e)
        }
    }

    /**
     * Writes the resized image to a file using BufferedImageWriter.
     *
     * @param file The file to write to.
     * @param image The image to write.
     */
    static class BufferedImageWriter {
        static void write(File file, Image image) {
            try {
                ImageIO.write((BufferedImage) image, getFormat(file), file)
            } catch (IOException e) {
                throw new RuntimeException('Error writing image to file: ' + file.name, e)
            }
        }

        private static String getFormat(File file) {
            return file.name.substring(file.name.lastIndexOf('.') + 1)
        }
    }
}
