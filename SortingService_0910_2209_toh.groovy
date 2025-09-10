// 代码生成时间: 2025-09-10 22:09:39
package com.example

import groovy.transform.CompileStatic

@CompileStatic
class SortingService {

    /**
     * Sorts an array of integers using bubble sort algorithm.
     *
     * @param array The array of integers to sort.
     * @return The sorted array.
     */
    Integer[] bubbleSort(Integer[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException('Array must not be null or empty')
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[j]) {
                    // Swap elements
                    int temp = array[j - 1]
                    array[j - 1] = array[j]
                    array[j] = temp
                }
            }
        }

        return array
    }

    /**
     * Sorts an array of integers using insertion sort algorithm.
     *
     * @param array The array of integers to sort.
     * @return The sorted array.
     */
    Integer[] insertionSort(Integer[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException('Array must not be null or empty')
        }

        for (int i = 1; i < array.length; i++) {
            int key = array[i]
            int j = i - 1

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j = j - 1
            }
            array[j + 1] = key
        }

        return array
    }

    /**
     * Sorts an array of integers using selection sort algorithm.
     *
     * @param array The array of integers to sort.
     * @return The sorted array.
     */
    Integer[] selectionSort(Integer[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException('Array must not be null or empty')
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j
                }
            }

            // Swap elements
            int temp = array[minIndex]
            array[minIndex] = array[i]
            array[i] = temp
        }

        return array
    }

    /**
     * Test method to demonstrate sorting algorithms.
     *
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        SortingService service = new SortingService()
        Integer[] unsortedArray = [64, 34, 25, 12, 22, 11, 90]

        println 'Unsorted Array: ' + unsortedArray

        Integer[] sortedArray1 = service.bubbleSort(unsortedArray.clone() as Integer[])
        println 'Sorted Array (Bubble Sort): ' + sortedArray1

        Integer[] sortedArray2 = service.insertionSort(unsortedArray.clone() as Integer[])
        println 'Sorted Array (Insertion Sort): ' + sortedArray2

        Integer[] sortedArray3 = service.selectionSort(unsortedArray.clone() as Integer[])
        println 'Sorted Array (Selection Sort): ' + sortedArray3
    }
}