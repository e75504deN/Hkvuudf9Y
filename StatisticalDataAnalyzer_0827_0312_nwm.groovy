// 代码生成时间: 2025-08-27 03:12:33
package com.example

// 引入Grails框架的核心类和常用库
import grails.transaction.Transactional
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
@Transactional
class StatisticalDataAnalyzer {
    // 构造函数
    StatisticalDataAnalyzer() {}
    
    // 统计数据的方法，假设输入为一个包含数值的列表
    BigDecimal calculateMean(List<BigDecimal> dataList) {
        try {
            if (dataList == null || dataList.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            BigDecimal sum = dataList.sum()
            BigDecimal mean = sum.divide(BigDecimal.valueOf(dataList.size()))
            return mean
        } catch (Exception e) {
            // 错误处理，打印异常信息并重新抛出
            e.printStackTrace()
            throw e
        }
    }
    
    // 统计数据的方法，计算标准差
    BigDecimal calculateStandardDeviation(List<BigDecimal> dataList) {
        try {
            if (dataList == null || dataList.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            BigDecimal mean = calculateMean(dataList)
            BigDecimal sumOfSquares = dataList.collect {
                (BigDecimal.valueOf(it.doubleValue()).subtract(mean)).pow(2)
            }.sum()
            BigDecimal variance = sumOfSquares.divide(BigDecimal.valueOf(dataList.size()))
            BigDecimal standardDeviation = variance.sqrt()
            return standardDeviation
        } catch (Exception e) {
            e.printStackTrace()
            throw e
        }
    }
    
    // 统计数据的方法，计算方差
    BigDecimal calculateVariance(List<BigDecimal> dataList) {
        try {
            if (dataList == null || dataList.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            BigDecimal mean = calculateMean(dataList)
            BigDecimal sumOfSquares = dataList.collect {
                (BigDecimal.valueOf(it.doubleValue()).subtract(mean)).pow(2)
            }.sum()
            BigDecimal variance = sumOfSquares.divide(BigDecimal.valueOf(dataList.size()))
            return variance
        } catch (Exception e) {
            e.printStackTrace()
            throw e
        }
    }
    
    // 统计数据的方法，计算中位数
    BigDecimal calculateMedian(List<BigDecimal> dataList) {
        try {
            if (dataList == null || dataList.isEmpty()) {
                throw new IllegalArgumentException('Data list cannot be null or empty')
            }
            List<BigDecimal> sortedList = dataList.sort()
            int middleIndex = sortedList.size() / 2
            if (sortedList.size() % 2 == 0) {
                // 如果是偶数个，取中间两个数的平均值
                return (sortedList.get(middleIndex - 1) + sortedList.get(middleIndex)).divide(BigDecimal.valueOf(2))
            } else {
                // 如果是奇数个，取中间的数值
                return sortedList.get(middleIndex)
            }
        } catch (Exception e)
        {
            e.printStackTrace()
            throw e
        }
    }
}
