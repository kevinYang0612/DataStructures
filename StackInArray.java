public class StackInArray
{
    private Object[] array;
    private int position = 0;

    public StackInArray(int maxSize)
    {
        this.array = new Object[maxSize];
    }

    public int size()
    {
        return this.position;
    }

    public boolean isEmpty()
    {
        return this.position == 0;
    }
    public Object peek(){return this.array[position - 1];}

    public void push(Object element)
    {
        this.array[position++] = element;
    }
    public Object pop()
    {
        return this.array[--position]; // return the array at -- position and
                                       // decrement the position
    }

}
