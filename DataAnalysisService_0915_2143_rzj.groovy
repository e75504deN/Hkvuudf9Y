// 代码生成时间: 2025-09-15 21:43:10
package com.example.analytics

import groovy.math.BigDecimal
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

/**
 * 统计数据分析器服务
 * 提供基本的统计数据分析功能
 */
class DataAnalysisService {

    /**
     * 计算一组数据的平均值
     *
     * @param data 数据数组
     * @return 平均值
     */
    BigDecimal calculateMean(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException('数据列表不能为空')
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return stats.getMean()
    }

    /**
     * 计算一组数据的中位数
     *
     * @param data 数据数组
     * @return 中位数
     */
    BigDecimal calculateMedian(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException('数据列表不能为空')
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return stats.getPercentile(50)
    }

    /**
     * 计算一组数据的标准差
     *
     * @param data 数据数组
     * @return 标准差
     */
    BigDecimal calculateStandardDeviation(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException('数据列表不能为空')
        }
        DescriptiveStatistics stats = new DescriptiveStatistics()
        data.each { stats.addValue(it) }
        return stats.getStandardDeviation()
    }

    /**
     * 计算一组数据的最大值
     *
     * @param data 数据数组
     * @return 最大值
     */
    Double calculateMax(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException('数据列表不能为空')
        }
        return Collections.max(data)
    }

    /**
     * 计算一组数据的最小值
     *
     * @param data 数据数组
     * @return 最小值
     */
    Double calculateMin(List<Double> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException('数据列表不能为空')
        }
        return Collections.min(data)
    }
}
