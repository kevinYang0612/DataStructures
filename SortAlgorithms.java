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
        
    public static int[] merge3Sorted(int[] A, int[] B, int[] C)
    {
        int[] result = new int[A.length + B.length + C.length];
        int p = 0;
        int i = 0, j = 0, k = 0;
        while (i < A.length && j < B.length && k < C.length)
        {
            int a = A[i];
            int b = B[j];
            int c = C[k];
            if (a <= b && a <= c)
            {
                result[p] = a;
                i++;
            }
            else if (b <= a && b <= c)
            {
                result[p] = b;
                j++;
            }
            else
            {
                result[p] = c;
                k++;
            }
            p++;
        }
        while (i < A.length && j < B.length)
        {
            if (A[i] < B[j])
            {
                result[p] = A[i];
                i++;
            }
            else
            {
                result[p] = B[j];
                j++;
            }
            p++;
        }
        while (j < B.length && k < C.length)
        {
            if (B[j] < C[k])
            {
                result[p] = B[j];
                j++;
            }
            else
            {
                result[p] = C[k];
                k++;
            }
            p++;
        }
        while (i < A.length && k < C.length)
        {
            if (A[i] < C[k])
            {
                result[p] = A[i];
                i++;
            }
            else
            {
                result[p] = C[k];
                k++;
            }
            p++;
        }
        while (i < A.length)
        {
            result[p] = A[i];
            i++;
            p++;
        }
        while (j < B.length) {
            result[p] = B[j];
            j++;
            p++;
        }
        while (k < C.length)
        {
            result[p] = C[k];
            k++;
            p++;
        }
        return result;
    }
    public static void mergeSort3Way(Integer[] gArray)
    {
        // if array of size is zero returns null
        if (gArray == null)
            return;

        // creating duplicate of given array
        Integer[] fArray = new Integer[gArray.length];

        // copying elements of given array into
        // duplicate array
        for (int i = 0; i < fArray.length; i++)
            fArray[i] = gArray[i];

        // sort function
        mergeSort3WayRec(fArray, 0, gArray.length, gArray);

        // copy back elements of duplicate array
        // to given array
        for (int i = 0; i < fArray.length; i++)
            gArray[i] = fArray[i];
    }

    /* Performing the merge sort algorithm on the
       given array of values in the rangeof indices
       [low, high).  low is minimum index, high is
       maximum index (exclusive) */
    public static void mergeSort3WayRec(Integer[] gArray,
                                        int low, int high, Integer[] destArray)
    {
        // If array size is 1 then do nothing
        if (high - low < 2)
            return;

        // Splitting array into 3 parts
        int mid1 = low + ((high - low) / 3);
        int mid2 = low + 2 * ((high - low) / 3) + 1;

        // Sorting 3 arrays recursively
        mergeSort3WayRec(destArray, low, mid1, gArray);
        mergeSort3WayRec(destArray, mid1, mid2, gArray);
        mergeSort3WayRec(destArray, mid2, high, gArray);

        // Merging the sorted arrays
        merge(destArray, low, mid1, mid2, high, gArray);
    }

    /* Merge the sorted ranges [low, mid1), [mid1,
       mid2) and [mid2, high) mid1 is first midpoint
       index in overall range to merge mid2 is second
       midpoint index in overall range to merge*/
    public static void merge(Integer[] gArray, int low,
                             int mid1, int mid2, int high,
                             Integer[] destArray)
    {
        int i = low, j = mid1, k = mid2, l = low;

        // choose smaller of the smallest in the three ranges
        while ((i < mid1) && (j < mid2) && (k < high))
        {
            if (gArray[i].compareTo(gArray[j]) < 0)
            {
                if (gArray[i].compareTo(gArray[k]) < 0)
                    destArray[l++] = gArray[i++];

                else
                    destArray[l++] = gArray[k++];
            }
            else
            {
                if (gArray[j].compareTo(gArray[k]) < 0)
                    destArray[l++] = gArray[j++];
                else
                    destArray[l++] = gArray[k++];
            }
        }

        // case where first and second ranges have
        // remaining values
        while ((i < mid1) && (j < mid2))
        {
            if (gArray[i].compareTo(gArray[j]) < 0)
                destArray[l++] = gArray[i++];
            else
                destArray[l++] = gArray[j++];
        }

        // case where second and third ranges have
        // remaining values
        while ((j < mid2) && (k < high))
        {
            if (gArray[j].compareTo(gArray[k]) < 0)
                destArray[l++] = gArray[j++];

            else
                destArray[l++] = gArray[k++];
        }

        // case where first and third ranges have
        // remaining values
        while ((i < mid1) && (k < high))
        {
            if (gArray[i].compareTo(gArray[k]) < 0)
                destArray[l++] = gArray[i++];
            else
                destArray[l++] = gArray[k++];
        }

        // copy remaining values from the first range
        while (i < mid1)
            destArray[l++] = gArray[i++];

        // copy remaining values from the second range
        while (j < mid2)
            destArray[l++] = gArray[j++];

        // copy remaining values from the third range
        while (k < high)
            destArray[l++] = gArray[k++];
    }

    
    
    public static void sortBooleanArray(boolean [] arr) 
    {
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
