// 代码生成时间: 2025-09-20 21:27:27
package com.example

import grails.transaction.Transactional
import groovy.io.FileType
import groovy.util.logging.Slf4j
import org.springframework.web.multipart.MultipartFile

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import java.io.IOException

@Transactional
@Slf4j
class ImageResizerService {

    // 文件存储路径
    private static final String IMAGE_STORAGE_DIR = '/path/to/image/storage'

    public File resizeImage(MultipartFile file, int targetWidth, int targetHeight) {
        try {
            // 创建一个新的文件对象，用于保存调整尺寸后的图片
            File resizedImageFile = new File(IMAGE_STORAGE_DIR + '/' + file.originalFilename)

            // 使用 ImageIO 读取传入的图片文件
            BufferedImage originalImage = ImageIO.read(file.getInputStream())

            // 创建一个新的 BufferedImage 对象，用于保存调整尺寸后的图片
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.type)

            // 获取原始图片的 Graphics2D 对象，并设置为非抗锯齿渲染
            Graphics2D graphics2D = resizedImage.createGraphics()
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null)
            graphics2D.dispose()

            // 将调整尺寸后的图片写入到新的文件对象中
            ImageIO.write(resizedImage, 'jpg', resizedImageFile)

            // 返回新的文件对象
            return resizedImageFile
        } catch (IOException e) {
            log.error('Error resizing image', e)
            throw new RuntimeException('Error resizing image', e)
        }
    }

    // 批量调整图片尺寸
    public List<File> batchResizeImages(List<MultipartFile> files, int targetWidth, int targetHeight) {
        List<File> resizedFiles = []
        for (MultipartFile file in files) {
            File resizedFile = resizeImage(file, targetWidth, targetHeight)
            resizedFiles.add(resizedFile)
        }
        return resizedFiles
    }
}
