package LeetCode_100HotQ.Middle;

import java.util.Arrays;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class FindKthLargest {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 5));
    }

    public static int findKthLargest(int[] nums, int k) {
        qSort(nums);
        // mSort(nums);
        // hSort(nums);
        return nums[nums.length - k];
    }

    //-Quick Sort-----------------------------------------------------------------------------------------//

    public static void qSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (right <= left)
            return;
        int index = partition(nums, left, right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    // 将nums的left-right部分分为以返回值为界的左小右大两部分
    public static int partition(int[] array, int left, int right) {
        // 选择第一个值作为基准
        int pivot = array[left];
        while (left < right) {
            // 跳过比pivot大的
            while (left < right && array[right] >= pivot)
                right--;
            // 发现比pivot小的，直接移到左边去存在left处（覆盖了pivot的原始位置值）
            if (left < right)
                array[left] = array[right];

            // 跳过比pivot小的
            while (left < right && array[left] < pivot)
                left++;
            // 发现比pivot大的，置界移到右边去存在刚刚被赋值走的right处
            if (left < right)
                array[right] = array[left];
        }
        // 以上的逻辑巧妙之处在于
        // 一开始left处的值被保存在pivot中取出
        // 之后在right不断左移和left不断右移的过程中，新right的值写入旧left处，新left值再写入旧right处，实际上没有数据损失
        // 在停止的时候，最后left停止处其值被赋予right处，再将pivot值重新存入，整体实际上是连续不断的数据交换。
        array[left] = pivot;
        return left;
    }

    //-Merge Sort-----------------------------------------------------------------------------------------//

    public static void mSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, mid + 1, right);
    }

    public static void merge(int[] nums, int l1, int r1, int l2, int r2) {
        int length = r1 - l1 + r2 - l2 + 2;
        int[] temp = new int[length];
        int i = 0, i1 = l1, i2 = l2;
        while (i1 <= r1 && i2 <= r2) {
            if (nums[i1] < nums[i2]) {
                temp[i] = nums[i1];
                i1++;
            } else {
                temp[i] = nums[i2];
                i2++;
            }
            i++;
        }
        // 左边数组先用完，复制右边剩下的
        if (i1 > r1) {
            System.arraycopy(nums, i2, temp, i, r2 - i2 + 1);
        }
        // 右边数组先用完
        else {
            System.arraycopy(nums, i1, temp, i, r1 - i1 + 1);
        }

        // 复制temp到原位置
        System.arraycopy(temp, 0, nums, l1, length);
    }

    //-Heap Sort (Max Heap)------------------------------------------------------------------------------//

    public static void hSort(int[] array) {
        if (array == null || array.length <= 1)
            return;
        heapSort(array);
    }

    private static void heapSort(int[] array) {
        // 从第一个非叶子节点开始，初始化建立最大堆
        for (int i = array.length / 2; i >= 0; i--)
            maxHeapify(array, i, array.length);

        // 将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为最大堆
        for (int i = array.length - 1; i > 0; i--) {
            // 将最大的堆顶换到到末尾，随后对除了末尾以外的部分重新调整为大根堆
            swap(array, 0, i);
            maxHeapify(array, 0, i);
        }
    }

    private static void maxHeapify(int[] array, int index, int length) {
        int father, child;
        for (father = array[index]; leftChild(index) < length; index = child) {
            child = leftChild(index);

            // 如果左子树小于右子树，则需要比较右子树和父节点，即取左右孩子中较大的一个
            if (child != length - 1 && array[child] < array[child + 1])
                child++; // 序号加1，指向右子树

            // 如果父节点小于子结点，则需要交换
            if (father < array[child])
                array[index] = array[child];
            else
                break; // 最大堆结构未被破坏，不需要调整
        }
        array[index] = father;
    }

    /** 获取左孩子下标
     *
     *             0
     *           /  \
     *          1     2
     *        / \   /  \
     *      3   4  5    6
     *
     *   { 0, 1, 2, 3, 4, 5, 6}
     *
     * leftChild = Parent * 2 + 1
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
