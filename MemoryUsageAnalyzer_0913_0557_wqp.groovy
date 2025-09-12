// 代码生成时间: 2025-09-13 05:57:20
import java.lang.management.ManagementFactory
import java.lang.management.MemoryMXBean
import java.lang.management.MemoryUsage

class MemoryUsageAnalyzer {
    // Memory MX Bean for accessing the memory usage
    private MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean()

    /**
     * Retrieves the current memory usage information.
     *
     * @return MemoryUsage object containing the current memory usage
     */
    MemoryUsage getCurrentMemoryUsage() {
        memoryMXBean.heapMemoryUsage
    }

    /**
     * Retrieves the peak memory usage since the Java virtual machine started.
     *
     * @return MemoryUsage object containing the peak memory usage
     */
    MemoryUsage getPeakMemoryUsage() {
        memoryMXBean.getPeakMemoryUsage()
    }

    /**
     * Retrieves the non heap memory usage.
     *
     * @return MemoryUsage object containing the non heap memory usage
     */
    MemoryUsage getNonHeapMemoryUsage() {
        memoryMXBean.nonHeapMemoryUsage
    }

    /**
     * Retrieves the peak non heap memory usage.
     *
     * @return MemoryUsage object containing the peak non heap memory usage
     */
    MemoryUsage getPeakNonHeapMemoryUsage() {
        memoryMXBean.getPeakNonHeapMemoryUsage()
    }

    /**
     * Prints out the memory usage details to the console.
     *
     * @param memoryUsage MemoryUsage object to be printed
     */
    void printMemoryUsage(MemoryUsage memoryUsage) {
        // Error handling for null memory usage
        if (memoryUsage == null) {
            println 'Memory usage data is not available.'
            return
        }

        println "Used Memory: ${memoryUsage.used} bytes"
        println "Committed Memory: ${memoryUsage.committed} bytes"
        println "Max Memory: ${memoryUsage.max} bytes"
        println "Threshold: ${memoryUsage.threshold} bytes"
    }

    /**
     * Main method to run the memory usage analyzer.
     *
     * @param args Command line arguments (not used)
     */
    static void main(String[] args) {
        MemoryUsageAnalyzer analyzer = new MemoryUsageAnalyzer()
        try {
            // Print current memory usage
            MemoryUsage heapUsage = analyzer.getCurrentMemoryUsage()
            println 'Current Heap Memory Usage:'
            analyzer.printMemoryUsage(heapUsage)

            // Print peak memory usage
            MemoryUsage peakHeapUsage = analyzer.getPeakMemoryUsage()
            println '
Peak Heap Memory Usage:'
            analyzer.printMemoryUsage(peakHeapUsage)

            // Print non heap memory usage
            MemoryUsage nonHeapUsage = analyzer.getNonHeapMemoryUsage()
            println '
Non Heap Memory Usage:'
            analyzer.printMemoryUsage(nonHeapUsage)

            // Print peak non heap memory usage
            MemoryUsage peakNonHeapUsage = analyzer.getPeakNonHeapMemoryUsage()
            println '
Peak Non Heap Memory Usage:'
            analyzer.printMemoryUsage(peakNonHeapUsage)

        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
