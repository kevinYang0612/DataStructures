import java.util.EmptyStackException;

public class ArrayStackFromBook
{
    private Object[] array;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStackFromBook()
    {
        this.array = new Object[DEFAULT_CAPACITY];
        top = -1;
    }
    public boolean isEmpty()
    {
        return top == -1;
    }
    public void makeEmpty()
    {
        top = -1;
    }
    public Object peek()
    {
        if (isEmpty()) throw new EmptyStackException();
        return this.array[this.top];
    }
    public Object pop()
    {
        if (isEmpty()) throw new EmptyStackException();
        return this.array[this.top--];
    }
    public void push(Object x)
    {
        if (this.top + 1 == this.array.length)
        {
            increaseCapacity();
        }
        this.array[++this.top] = x;
    }
    private void increaseCapacity()
    {
        Object[] newArray = new Object[this.array.length * 2];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }
}
