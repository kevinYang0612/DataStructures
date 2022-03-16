import java.util.Comparator;

public class PriorityQueue<T extends Comparable<T>>
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize = 0;
    private T[] array;
    private Comparator<? super T> compare;

    public PriorityQueue()
    {
        this.currentSize = 0;
        this.compare = null;
        this.array = (T[]) new Object[DEFAULT_CAPACITY + 1];
    }
    public PriorityQueue(Comparator<? super T> comparator)
    {
        this.currentSize = 0;
        this.compare = comparator;
        this.array = (T[]) new Object[DEFAULT_CAPACITY + 1];
    }
    private int compare(T lhs, T rhs)
    {
        if (this.compare == null)
        {
            return lhs.compareTo(rhs);
        }
        else
        {
            return this.compare.compare(lhs, rhs);
        }
    }
    public boolean add(T element)
    {
        if (this.currentSize + 1 == this.array.length)
        {
            doubleArray();
        }
        
        // percolate up
        int hole = ++this.currentSize;
        this.array[0] = element;
        for ( ; compare(element, array[hole / 2]) < 0; hole /= 2)
        {
            this.array[hole] = this.array[hole / 2];
        }
        this.array[hole] = element;
        return true;
    }
    private void doubleArray()
    {
        T[] newArray = (T[]) new Object[this.array.length * 2];
        for (int i = 0; i < this.array.length; i++)
        {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }
    public T remove()
    {
        T minItem = this.array[1];
        this.array[1] = this.array[this.currentSize--];
        percolateDown(1);
        return minItem;
    }
    public void buildHeap()
    {
        for (int i = this.currentSize / 2; i > 0; i--)
        {
            percolateDown(i);
        }
    }
    private void percolateDown(int hole)
    {
        int child = 0;
        T temp = this.array[hole];
        for (; hole * 2 <= this.currentSize; hole = child)
        {
            child = hole * 2;
            if (child != this.currentSize && compare(array[child + 1], array[child]) < 0)
            {
                child++;
            }
            if (compare(array[child], temp) < 0)
            {
                array[hole] = array[child];
            }
            else
            {
                break;
            }
        }
        array[hole] = temp;
    }
    public static void maxHeapify(int[] arr, int n,
                                             int i)
    {
        // Find largest of node and its children
        if (i >= n) {
            return;
        }
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int max;
        if (l < n && arr[l] > arr[i]) {
            max = l;
        }
        else
            max = i;
        if (r < n && arr[r] > arr[max]) {
            max = r;
        }
         
        // Put maximum value at root and
        // recur for the child with the
        // maximum value
        if (max != i) {
            int temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, n, max);
        }
    }
     
    // Merges max heaps a[] and b[] into merged[]
    public static void mergeHeaps(int[] arr, int[] a,
                                  int[] b, int n, int m)
    {
        for (int i = 0; i < n; i++) {
            arr[i] = a[i];
        }
        for (int i = 0; i < m; i++) {
            arr[n + i] = b[i];
        }
        n = n + m;
 
        // Builds a max heap of given arr[0..n-1]
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, n, i);
        }
    }
}

