// 代码生成时间: 2025-08-23 05:52:07
package com.example;

import grails.transaction.Transactional;
import groovy.transform.CompileStatic;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Service class for resizing images in bulk.
 */
@CompileStatic
@Transactional
class ImageResizerService {

    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 600;
    private static final String TEMP_DIRECTORY = "temp";

    /**
     * Resizes a list of images to a specified maximum width and height.
     *
     * @param files List of images to be resized.
     * @return A list of paths to the resized images.
     * @throws IOException If an I/O error occurs during processing.
     */
    List<Path> resizeImages(List<MultipartFile> files) throws IOException {
        List<Path> resizedFiles = [];

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            Path originalPath = Files.createTempFile(Paths.get(TEMP_DIRECTORY), "image", ".tmp");
            file.transferTo(originalPath.toFile());

            try {
                resizeImage(originalPath, MAX_WIDTH, MAX_HEIGHT);
                resizedFiles.add(originalPath);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception or rethrow
            }
        }

        return resizedFiles;
    }

    /**
     * Resizes a single image to the specified maximum width and height.
     *
     * @param path Path to the image file.
     * @param maxWidth Maximum width of the resized image.
     * @param maxHeight Maximum height of the resized image.
     * @throws IOException If an I/O error occurs during processing.
     */
    void resizeImage(Path path, int maxWidth, int maxHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(path.toFile());
        double ratio = findRatio(maxWidth, maxHeight, originalImage.getWidth(), originalImage.getHeight());

        int width = (int) (originalImage.getWidth() * ratio);
        int height = (int) (originalImage.getHeight() * ratio);

        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D graphics = resizedImage.createGraphics();

        graphics.drawImage(
            originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
            0, 0, null
        );

        graphics.dispose();

        ImageIO.write(resizedImage, "jpg", path.toFile());
    }

    /**
     * Calculates the scaling ratio to maintain the aspect ratio of the image.
     *
     * @param maxWidth Maximum width of the resized image.
     * @param maxHeight Maximum height of the resized image.
     * @param imageWidth Width of the original image.
     * @param imageHeight Height of the original image.
     * @return The scaling ratio.
     */
    double findRatio(int maxWidth, int maxHeight, int imageWidth, int imageHeight) {
        double ratioWidth = (double) maxWidth / imageWidth;
        double ratioHeight = (double) maxHeight / imageHeight;

        return Math.min(ratioWidth, ratioHeight);
    }
}
