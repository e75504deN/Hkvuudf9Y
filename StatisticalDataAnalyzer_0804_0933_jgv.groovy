// 代码生成时间: 2025-08-04 09:33:00
package com.example

import groovy.lang.Tuple
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

/**
 * StatisticalDataAnalyzer is a class designed to perform statistical analysis on a dataset.
 * It provides methods to calculate basic statistical measures such as mean, median,
 * variance, standard deviation, etc.
 *
 * @author Your Name
 * @version 1.0
 */
class StatisticalDataAnalyzer {

    /**
     * This method calculates the mean of the dataset.
     *
     * @param data The dataset to calculate the mean for.
     * @return The mean of the dataset.
     */
    double calculateMean(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be empty.")
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return stats.getMean()
    }

    /**
     * This method calculates the median of the dataset.
     *
     * @param data The dataset to calculate the median for.
     * @return The median of the dataset.
     */
    double calculateMedian(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be empty.")
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return stats.getPercentile(50)
    }

    /**
     * This method calculates the variance of the dataset.
     *
     * @param data The dataset to calculate the variance for.
     * @return The variance of the dataset.
     */
    double calculateVariance(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be empty.")
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return stats.getVariance()
    }

    /**
     * This method calculates the standard deviation of the dataset.
     *
     * @param data The dataset to calculate the standard deviation for.
     * @return The standard deviation of the dataset.
     */
    double calculateStandardDeviation(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be empty.")
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return Math.sqrt(stats.getVariance())
    }

    /**
     * Main method for testing the StatisticalDataAnalyzer class.
     *
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        StatisticalDataAnalyzer analyzer = new StatisticalDataAnalyzer()
        List<Double> data = [10.0, 20.0, 30.0, 40.0, 50.0]

        println "Mean: ${analyzer.calculateMean(data)}"
        println "Median: ${analyzer.calculateMedian(data)}"
        println "Variance: ${analyzer.calculateVariance(data)}"
        println "Standard Deviation: ${analyzer.calculateStandardDeviation(data)}"
    }
}
