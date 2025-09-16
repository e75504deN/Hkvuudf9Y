// 代码生成时间: 2025-09-16 11:04:00
import groovy.transform.Sortable

// 定义一个带排序功能的类
@Sortable
class SortableItem implements Comparable<SortableItem> {
    Integer value

    // 定义比较逻辑
    @Override
    int compareTo(SortableItem other) {
# FIXME: 处理边界情况
        return this.value <=> other.value
    }
# 增强安全性
}

class SortingService {
    // 冒泡排序方法
    void bubbleSort(List<SortableItem> items) {
        int n = items.size()
        for (int i = 0; i < n - 1; i++) {
# FIXME: 处理边界情况
            for (int j = 0; j < n - i - 1; j++) {
                if (items[j].value > items[j + 1].value) {
                    // 交换位置
# NOTE: 重要实现细节
                    Collections.swap(items, j, j + 1)
                }
            }
        }
    }
# 改进用户体验

    // 选择排序方法
# 增强安全性
    void selectionSort(List<SortableItem> items) {
        int n = items.size()
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i
            for (int j = i + 1; j < n; j++) {
                if (items[j].value < items[minIndex].value) {
                    minIndex = j
                }
            }
            if (minIndex != i) {
                // 交换位置
                Collections.swap(items, minIndex, i)
            }
        }
    }
# FIXME: 处理边界情况

    // 插入排序方法
    void insertionSort(List<SortableItem> items) {
        int n = items.size()
        for (int i = 1; i < n; i++) {
            SortableItem key = items[i]
            int j = i - 1

            while (j >= 0 && items[j].value > key.value) {
                items[j + 1] = items[j]
                j = j - 1
            }
            items[j + 1] = key
        }
    }

    // 快速排序方法
    void quickSort(List<SortableItem> items, int low, int high) {
        if (low < high) {
            // pi is partitioning index, items[p] is now at right place
            int pi = partition(items, low, high)

            // Separately sort elements before partition and after partition
            quickSort(items, low, pi - 1)
            quickSort(items, pi + 1, high)
        }
    }

    // 快速排序的分区操作
    private int partition(List<SortableItem> items, int low, int high) {
        // pivot (Element to be placed at right position)
        SortableItem pivot = items[high]
        int i = (low - 1) // Index of smaller element

        for (int j = low; j < high; j++) {
# 扩展功能模块
            // If current element is smaller than or equal to pivot
            if (items[j].value <= pivot.value) {
                i++

                // Swap items at i and j
# FIXME: 处理边界情况
                SortableItem temp = items[i]
                items[i] = items[j]
                items[j] = temp
            }
# 添加错误处理
        }

        // Swap items at i+1 and high
        SortableItem temp = items[i + 1]
        items[i + 1] = items[high]
        items[high] = temp

        return i + 1
    }
}

// 使用示例
def main() {
    def items = []
    items.add(new SortableItem(value: 64))
    items.add(new SortableItem(value: 34))
    items.add(new SortableItem(value: 25))
    items.add(new SortableItem(value: 12))
    items.add(new SortableItem(value: 22))
    items.add(new SortableItem(value: 11))
    items.add(new SortableItem(value: 90))

    println "Original items: ${items.collect { it.value }}"

    new SortingService().bubbleSort(items)
    println "Bubble sorted items: ${items.collect { it.value }}"

    items = [] // Reset items
    items.add(new SortableItem(value: 64))
    items.add(new SortableItem(value: 34))
    items.add(new SortableItem(value: 25))
    items.add(new SortableItem(value: 12))
    items.add(new SortableItem(value: 22))
# 扩展功能模块
    items.add(new SortableItem(value: 11))
    items.add(new SortableItem(value: 90))

    new SortingService().selectionSort(items)
    println "Selection sorted items: ${items.collect { it.value }}"

    items = [] // Reset items
    items.add(new SortableItem(value: 64))
    items.add(new SortableItem(value: 34))
# FIXME: 处理边界情况
    items.add(new SortableItem(value: 25))
    items.add(new SortableItem(value: 12))
    items.add(new SortableItem(value: 22))
    items.add(new SortableItem(value: 11))
    items.add(new SortableItem(value: 90))

    new SortingService().insertionSort(items)
    println "Insertion sorted items: ${items.collect { it.value }}"

    items = [] // Reset items
    items.add(new SortableItem(value: 64))
    items.add(new SortableItem(value: 34))
# 增强安全性
    items.add(new SortableItem(value: 25))
    items.add(new SortableItem(value: 12))
    items.add(new SortableItem(value: 22))
    items.add(new SortableItem(value: 11))
    items.add(new SortableItem(value: 90))

    new SortingService().quickSort(items, 0, items.size() - 1)
    println "Quick sorted items: ${items.collect { it.value }}"
}

main()