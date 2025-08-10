// 代码生成时间: 2025-08-10 15:36:38
package com.example

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

/**
 * Service class for generating random numbers.
 * This class is responsible for providing random number generation functionality.
 */
@Service
@CompileStatic
class RandomNumberGeneratorService {

    /**
     * Generates a random number within a given range.
     *
     * @param min The minimum value of the range (inclusive).
     * @param max The maximum value of the range (inclusive).
     *
     * @return The generated random number.
     *
     * @throws IllegalArgumentException if min is greater than max.
     */
    int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.")
        }

        return (int) (Math.random() * (max - min + 1) + min)
    }

    /**
     * Generates a random number within a default range from 1 to 100.
     *
     * @return The generated random number.
     */
    int generateRandomNumber() {
        return generateRandomNumber(1, 100)
    }
}
