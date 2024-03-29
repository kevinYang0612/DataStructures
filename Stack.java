public class Stack<T> implements Iterable<T>
{
    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

    public Stack(){}

    public Stack(T firstElement){ push(firstElement);}

    public int size(){return list.size();}

    public boolean isEmpty(){return size() == 0;}

    public void push(T element){list.addLast(element);}

    public T pop()
    {
        if (isEmpty())
        {
            throw new java.util.EmptyStackException();
        }
        return this.list.removeLast();
    }
    public T peek()
    {
        if (isEmpty())
        {
            throw new java.util.EmptyStackException();
        }
        return this.list.peekLast();
    }
    @Override
    public java.util.Iterator<T> iterator()
    {
        return list.iterator();
    }
}
