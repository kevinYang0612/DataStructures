public class Queue<T> implements Iterable<T>
{
    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

    private Queue() {}

    private Queue(T firstElement){ add(firstElement);}

    public void add(T element)  // add an element to the back of the queue
    {
        this.list.addLast(element);
    }
    public int size()
    {
        return this.list.size();
    }
    public boolean isEmpty()
    {
        return size() == 0;
    }
    public T peek()
    {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return this.list.peekFirst();
    }
    public T poll() // retrieves and removes the head of this queue
    {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return this.list.removeFirst();
    }
    @Override
    public java.util.Iterator<T> iterator()
    {
        return this.iterator();
    }
}
