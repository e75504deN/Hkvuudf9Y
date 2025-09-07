// 代码生成时间: 2025-09-07 20:49:31
import groovy.transform.CompileStatic
import org.springframework.stereotype.Service
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.batch.item.file.FlatFileParser
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.batch.item.file.builder.FlatFileReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor
import org.springframework.batch.item.file.transform.DelimitedLineAggregator
import org.springframework.batch.item.file.transform.LineAggregator
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper
import org.springframework.batch.item.file.MultiResourceItemReader
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.batch.support.transaction.TransactionAwareProxyFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.List
import java.util.ArrayList
import static org.springframework.batch.core.BatchStatus.*
import static org.springframework.batch.core.ExitStatus.*

// Define a service class
@Service
@CompileStatic
class ImageResizerService {

    // Define the directory for input and output images
    private static final String INPUT_DIR = "input/"
    private static final String OUTPUT_DIR = "output/"
    private static final String TEMP_DIR = "temp/"
    private static final String IMAGE_FORMAT = "png"

    // Define the default width and height for resizing
    private static final int DEFAULT_WIDTH = 800
    private static final int DEFAULT_HEIGHT = 600

    // Define the job builder factory
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // Define the step builder factory
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // Define the transaction manager
    @Autowired
    private PlatformTransactionManager transactionManager;

    // Define a method to create a batch job
    public Job createBatchJob() throws Exception {
        // Define the job
        Job job = jobBuilderFactory.get("imageResizerJob")
                .incrementer(new RunIdIncrementer())
                .listener(new JobExecutionListenerSupport() {
                    @Override
                    public ExitStatus afterJob(JobExecution jobExecution) {
                        if(jobExecution.getStatus() == BATCH_COMPLETED) {
                            log.info("Job completed successfully")
                            return ExitStatus.COMPLETED
                        } else {
                            log.error("Job failed")
                            return ExitStatus.FAILED
                        }
                    }
                })
                .flow(step1())
                .end()
                .build();
        return job;
    }

    // Define a step to read input files
    private Step step1() throws Exception {
        // Define the file reader
        ResourceAwareItemReaderItemStream<ImageFile> reader = new MultiResourceItemReaderBuilder<ImageFile>()
                .name("fileReader")
                .delimited()
                .names(new String[]{INPUT_DIR + "*." + IMAGE_FORMAT})
                .fieldSetMapper(new PassThroughFieldSetMapper<ImageFile>())
                .build();

        // Define the item processor
        ItemProcessor<ImageFile, ImageFile> processor = new ItemProcessor<ImageFile, ImageFile>() {
            @Override
            public ImageFile process(ImageFile item) throws Exception {
                // Resize the image
                BufferedImage originalImage = ImageIO.read(item.getFile());
                BufferedImage resizedImage = resizeImage(originalImage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                item.setFile(createImageFile(resizedImage));
                return item;
            }
        };

        // Define the file writer
        FlatFileItemWriter<ImageFile> writer = new FlatFileItemWriterBuilder<ImageFile>()
                .name("fileWriter")
                .resource(new FileSystemResource(OUTPUT_DIR))
                .delimited()
                .fieldExtractor(new BeanWrapperFieldExtractor<ImageFile>() {
                    @Override
                    public Object[] extract(ImageFile t) {
                        return new Object[]{t.getFile().getName()};
                    }
                })
                .build();

        // Define the step
        return stepBuilderFactory.get("step1")
                .<ImageFile, ImageFile>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // Define a method to resize an image
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        // Create a new image with the specified width and height
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());

        // Draw the original image onto the new image
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        // Return the resized image
        return resizedImage;
    }

    // Define a method to create an image file
    private ImageFile createImageFile(BufferedImage image) throws IOException {
        // Create a temporary file
        File tempFile = createTempFile(IMAGE_FORMAT);

        // Write the image to the file
        ImageIO.write(image, IMAGE_FORMAT, tempFile);

        // Return the image file
        return new ImageFile(tempFile);
    }

    // Define a method to create a temporary file
    private File createTempFile(String extension) throws IOException {
        // Create a temporary directory if it does not exist
        File tempDir = new File(TEMP_DIR);
        if(!tempDir.exists()) {
            tempDir.mkdirs();
        }

        // Create a temporary file
        File tempFile = File.createTempFile("image", "." + extension, tempDir);
        tempFile.deleteOnExit();
        return tempFile;
    }
}

// Define a controller class
@RestController
class ImageResizerController {

    // Define the image resizer service
    @Autowired
    private ImageResizerService imageResizerService;

    // Define a method to handle file uploads
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile[] files) {
        try {
            // Create a batch job
            Job job = imageResizerService.createBatchJob();

            // Execute the batch job
            JobExecution jobExecution = job.execute();

            // Check if the job completed successfully
            if(jobExecution.getStatus() == BATCH_COMPLETED) {
                return new ModelAndView("uploadStatus", "message", "Files resized successfully");
            } else {
                return new ModelAndView("uploadStatus", "message", "Files resized failed");
            }
        } catch (Exception e){
            // Handle exceptions
            return new ModelAndView("uploadStatus", "message", "Error resizing files: 