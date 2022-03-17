import java.util.Arrays;
import java.util.Queue;

public class SortAlgorithms
{
    /**
     * swap the neighbor if out of order and advancing the pointers
     * 比较相邻的两个数，如果第一个比第二个大，交换
     * 每一对做同样的工作，从第一对到最后一对
     * 重复以上步骤
     * */
    public static int[] bubbleSort(int[] array)
    {
        if (array.length == 0) return array;
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array.length - 1; j++)
            {
                if (array[j + 1] < array[j])
                {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
    /**
     * Find the smallest in the array
     * swap the smallest with the 0th index
     * advancing the pointer
     * */
    public static int[] selectionSort(int[] array)
    {
        if (array.length == 0) return array;
        for (int i = 0; i < array.length; i++)
        {
            int minIndex = i;
            for (int j = i; j < array.length; j++)
            {
                if (array[j] < array[minIndex])
                {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
    /**
     * 取出第二个元素，向前扫描
     * 重复向前扫描，如果当前元素小于前面一个元素，前面一个元素向后移动一个位置
     * 如果不小于，插入到当前扫描到的位置
     * 然后继续检查下一个
     * */
    public static int[] insertionSort(int[] array)
    {
        if (array.length == 0) return array;
        int current;
        for (int i = 0; i < array.length - 1; i++)
        {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex])
            {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static int[] mergeSort(int[] array)
    {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }
    private static int[] merge(int[] left, int[] right)
    {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++)
        {
            if (i >= left.length)       // i 为左边array index，如果i 超出左边array长度，抄右边所有的element
            {
                result[index] = right[j++];
            }
            else if (j >= right.length)   // j 为右边array index，如果j 超出you边array长度，抄左边所有的element
            {
                result[index] = left[i++];
            }
            else if (left[i] > right[j])   // 右边element 小，加进到result array
            {
                result[index] = right[j++];
            }
            else
            {
                result[index] = left[i++];
            }
        }
        return result;
    }
    public static int[] quickSort(int[] array, int start, int end)
    {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
        {
            quickSort(array, start, smallIndex - 1);
        }
        if (smallIndex < end)
        {
            quickSort(array, smallIndex + 1, end);
        }
        return array;
    }
    public static int partition(int[] array, int start, int end)
    {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
        {
            if (array[i] <= array[end])
            {
                smallIndex++;
                if (i > smallIndex)
                {
                    swap(array, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }
    private static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
        public static void sortBooleanArray(boolean [] arr) {
        int [] m = new int [2];
        for(Boolean b : arr)
        {
            if(b)
            {
                m[1]++;
            }
            else
            {
                m[0]++;
            }
        }
        int index = 0;
        for(int i = 0; i < m.length; i++)
        {
            for(int j = 0; j < m[i]; j++)
            {
                if(i == 0) arr[index++] = false;
                else arr[index++] = true;
            }
        }
    }
    public static Object[] sortBooleanArray1(Object[] arr)
    {
        int[] m = new int[3]; // contain true or false
        Object[] newArray = new Object[arr.length];
        for (Object obj : arr)
        {
            if (!(obj instanceof Boolean))
            {
                m[1]++;
            }
            else if ((boolean)obj)
            {
                m[2]++;
            }
            else
            {
                m[0]++;
            }
        }
        int index = 0;
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[i]; j++)
            {
                if (i == 0) newArray[index] = false;
                else if (i == 1) newArray[index] = "maybe";
                else newArray[index] = true;
                index++;
            }
        }
        return newArray;
    }
}
