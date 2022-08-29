import java.util.Arrays;

public class ArrayStack<T>
{
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }
    public ArrayStack(final int initialCapacity)
    {
        this.size = 0;
        this.elements = (T[]) (new Object[Math.max(DEFAULT_CAPACITY, initialCapacity)]);
    }
    public int size()
    {
        return this.size;
    }
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    private void expandCapacity()
    {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }
    public void push(final T element)
    {
        if (size() == this.elements.length)
        {
            expandCapacity();
        }
        this.elements[this.size] = element;
        this.size++;
    }
    public T pop()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Empty stack");
        }
        this.size--;
        final T result = this.elements[this.size];
        this.elements[this.size] = null;
        return result;
    }
    public T peek()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Empty stack");
        }
        return this.elements[this.size - 1];
    }
}
