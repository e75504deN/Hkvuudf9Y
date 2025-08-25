// 代码生成时间: 2025-08-26 03:48:52
class SortService {

    /**
     * Sorts an array of integers using bubble sort algorithm.
     * 
     * @param array The array of integers to be sorted
     * @return The sorted array
     */
    def bubbleSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException('Array is null or empty')
        }

        int n = array.length
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
        return array
    }

    /**
     * Sorts an array of integers using selection sort algorithm.
     * 
     * @param array The array of integers to be sorted
     * @return The sorted array
     */
    def selectionSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException('Array is null or empty')
        }

        int n = array.length
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i
            for (int j = i + 1; j < n; j++) {
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
     * Sorts an array of integers using insertion sort algorithm.
     * 
     * @param array The array of integers to be sorted
     * @return The sorted array
     */
    def insertionSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException('Array is null or empty')
        }

        for (int i = 1; i < array.length; i++) {
            int key = array[i]
            int j = i - 1
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = key
        }
        return array
    }
}
