// 代码生成时间: 2025-08-21 12:24:11
import groovy.transform.CompileStatic

/**
 * A utility class for sorting algorithms in Grails.
 */
@CompileStatic
class SortingAlgorithmGrails {

    /**
     * Sorts an array using the Bubble Sort algorithm.
     * 
     * @param array The array to sort.
     * @return The sorted array.
     */
    static List<Integer> bubbleSort(List<Integer> array) {
        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException('Array must not be null or empty')
        }

        boolean swapped
        int n = array.size()

        for (int i = 0; i < n - 1; i++) {
            swapped = false
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    def temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                    swapped = true
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) {
                break
            }
        }

        return array
    }

    /**
     * Sorts an array using the Quick Sort algorithm.
     * 
     * @param array The array to sort.
     * @return The sorted array.
     */
    static List<Integer> quickSort(List<Integer> array) {
        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException('Array must not be null or empty')
        }

        return quickSortRecursive(array, 0, array.size() - 1)
    }

    private static List<Integer> quickSortRecursive(List<Integer> array, int low, int high) {
        List<Integer> sorted = new ArrayList<>(array)
        if (low < high) {
            // Find the pivot element such that elements on (low to (i - 1)) are less than pivot
            // and elements on ((i + 1) to high) are greater than pivot
            int pivotIndex = partition(sorted, low, high)

            // Recursively sort elements before and after partition
            quickSortRecursive(sorted, low, pivotIndex - 1)
            quickSortRecursive(sorted, pivotIndex + 1, high)
        }
        return sorted
    }

    private static int partition(List<Integer> array, int low, int high) {
        int pivot = array[high]
        int i = (low - 1) // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (array[j] <= pivot) {
                i++
                // Swap array[i] and array[j]
                def temp = array[i]
                array[i] = array[j]
                array[j] = temp
            }
        }

        // Swap array[i+1] and array[high] (or pivot)
        def temp = array[i + 1]
        array[i + 1] = array[high]
        array[high] = temp

        return i + 1
    }

    // Add more sorting methods as needed
}

/**
 * Example usage of SortingAlgorithmGrails class.
 */
class SortingAlgorithmExample {

    static void main(String[] args) {
        def arrayToSort = [64, 34, 25, 12, 22, 11, 90]

        println 'Original array: ' + arrayToSort

        List<Integer> sortedArray = SortingAlgorithmGrails.bubbleSort(new ArrayList<>(arrayToSort))

        println 'Sorted array using Bubble Sort: ' + sortedArray

        sortedArray = SortingAlgorithmGrails.quickSort(new ArrayList<>(arrayToSort))

        println 'Sorted array using Quick Sort: ' + sortedArray

        // Add more example usages for other sorting methods
    }
}