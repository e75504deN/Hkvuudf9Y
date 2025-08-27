// 代码生成时间: 2025-08-27 18:45:31
 * difference between two points in time to detect memory leaks or abnormal usage.
 */

import java.lang.management.ManagementFactory
import java.lang.management.MemoryMXBean
import java.lang.management.MemoryUsage
import groovy.transform.CompileStatic

@CompileStatic
class MemoryUsageAnalyzer {

    // MemoryMXBean is used to get the memory usage information
    private MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean()

    /**
     * Retrieves the current memory usage.
     *
     * @return MemoryUsage containing the current memory usage statistics.
     */
    MemoryUsage getCurrentMemoryUsage() {
        memoryMXBean.getHeapMemoryUsage()
    }

    /**
     * Compares two memory usage snapshots to find the difference.
     *
     * @param before The memory usage snapshot before.
     * @param after The memory usage snapshot after.
     * @return A MemoryUsage object representing the difference.
     */
    MemoryUsage diffMemoryUsage(MemoryUsage before, MemoryUsage after) {
        if (before && after) {
            new MemoryUsage(
                init = after.getInit() - before.getInit(),
                used = after.getUsed() - before.getUsed(),
                committed = after.getCommitted() - before.getCommitted(),
                max = after.getMax() - before.getMax()
            )
        } else {
            throw new IllegalArgumentException("Memory usage snapshots cannot be null.")
        }
    }

    /**
     * Prints the current memory usage to the console.
     *
     * @param detailed If true, prints detailed memory usage statistics.
     */
    void printMemoryUsage(Boolean detailed = false) {
        MemoryUsage memoryUsage = getCurrentMemoryUsage()
        println "Heap Memory Usage: ${memoryUsage}"
        if (detailed) {
            println "  - Init: ${memoryUsage.getInit()}"
            println "  - Used: ${memoryUsage.getUsed()}"
            println "  - Committed: ${memoryUsage.getCommitted()}"
            println "  - Max: ${memoryUsage.getMax()}"
        }
    }
}

/**
 * This is an example usage of the MemoryUsageAnalyzer class.
 * It demonstrates how to create an instance and use the provided methods.
 */
MemoryUsageAnalyzer analyzer = new MemoryUsageAnalyzer()

// Print the current memory usage
analyzer.printMemoryUsage()

// Take a snapshot of the memory usage before some operations
MemoryUsage before = analyzer.getCurrentMemoryUsage()

// Perform some memory-intensive operations here
// ...

// Take another snapshot after the operations
MemoryUsage after = analyzer.getCurrentMemoryUsage()

// Calculate and print the difference in memory usage
MemoryUsage diff = analyzer.diffMemoryUsage(before, after)
println "Memory usage difference: ${diff}"
