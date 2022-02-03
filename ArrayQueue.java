public class ArrayQueue
{
    private Object[] array;
    private int currentSize;
    private int front;
    private int back;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue()
    {
        this.array = new Object[DEFAULT_CAPACITY];
        makeEmpty();
    }
    public void makeEmpty()
    {
        this.currentSize = 0;
        this.front = 0;
        this.back = -1;
    }
    public boolean isEmpty()
    {
        return this.currentSize == 0;
    }
    public void enqueue(Object x)
    {
        if (this.currentSize == this.array.length)
        {
            increaseCapacity();
        }
        this.back = increment(this.back);
        this.array[this.back] = x;
        currentSize++;
    }
    public Object dequeue()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Array Queue is empty");
        }
        this.currentSize--;
        Object result = this.array[this.front];
        this.front = increment(this.front);
        return result;
    }
    public Object peek()
    {
        if (isEmpty()) throw new RuntimeException("Empty");
        return this.array[this.front];
    }
    private int increment(int x)
    {
        if (++x == this.array.length)       // pre increment, then check if after dequeue elements to empty
        {
            x = 0;                          // reset the front to 0
        }
        return x;
    }
    private void increaseCapacity()
    {
        Object[] newArray = new Object[this.array.length * 2];
        for (int i = 0; i < this.currentSize; i++, this.front = increment(this.front))
        {
            // copy everything start the front, not 0
            // example:
            // newArray[D, T, W, , , ]
            // oldArray[A, C, [D, T, W]]

            newArray[i] = this.array[this.front];
        }
        this.array = newArray;
        this.front = 0;     // Since it is recopied, A and C are lost, D is in the front, reset front to 0
        this.back = this.currentSize - 1;   // reset the back as well
    }
}
