/**
 * Doubly linkedList with basic functions (from css 143 class)
 * */
public class DoubleyLinkedList<E extends Comparable<E>>
{
    private static class ListNode<E>
    {
        public E data;
        public ListNode<E> next;
        public ListNode<E> previous;

        public ListNode(E data)
        {
            this(data, null, null);
        }
        public ListNode(E data, ListNode<E> next, ListNode<E> previous)
        {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    public DoubleyLinkedList()
    {
        this.head = new ListNode<E>(null);
        this.tail = new ListNode<E>(null);
        clear();
    }
    public void clear()
    {
        this.head.next = this.tail;
        this.tail.previous = this.head;
        this.size = 0;
    }
    public int size()
    {
        return this.size;
    }

    public void swap(ListNode<E> first, ListNode<E> second)
    {
        E temp = first.data;
        first.data = second.data;
        second.data = temp;
    }
    private void checkIndex(int index)
    {
        if (index < 0 || index >= this.size)
        {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
    private ListNode<E> nodeAt(int index)
    {
        ListNode<E> traverse;
        if (this.size / 2 > index)      // index is at front half, traverse from front
        {
            traverse = this.head;
            for (int i = 0; i < index + 1; i++)
            {
                traverse = traverse.next;
            }
        }
        else
        {
            traverse = this.tail;
            for (int i = this.size - 1; i >= index; i--)
            {
                traverse = traverse.previous;
            }
        }
        return traverse;
    }
    public E get(int index)
    {
        checkIndex(index);
        ListNode<E> traverse = nodeAt(index);
        return traverse.data;
    }
    public int indexOf(E value)
    {
        int index = 0;
        int result = -1;
        ListNode<E> traverse = this.head;
        while (traverse.next != this.tail && result < 0)
        {
            if (traverse.data.equals(value))
            {
                result = index;
            }
            index++;
            traverse = traverse.next;
        }
        return result;
    }
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    public boolean contains(E value)
    {
        return indexOf(value) >= 0;         // if not, return -1, it was preset in indexOf already
    }
    public boolean add(E value)             // add in the end of the list by default
    {
        return add(this.size, value);
    }
    public boolean add(int index, E value)
    {
        if (index < 0 || index > this.size)
        {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ListNode<E> traverse = nodeAt(index - 1);       // find the index before
        ListNode<E> newNode = new ListNode<E>(value, traverse.next, traverse);  // instantiate the node with next and prev
        traverse.next = newNode;            // reset the traverse.next to newNode
        newNode.next.previous = newNode;    // reset newNode.next's prev to itself
        this.size++;
        return true;
    }
    public boolean remove(int index)
    {
        checkIndex(index);
        ListNode<E> traverse = nodeAt(index - 1);
        traverse.next = traverse.next.next;
        traverse.next.previous = traverse;      // traverse.next is being updated, new traverse.next.prev points to itself
        // The other way of doing it:
//        ListNode<E> traverse1= nodeAt(index);
//        traverse1.next.previous = traverse1.previous;
//        traverse1.previous = traverse1.next;
        this.size--;
        return true;
    }
    public void set(int index, E value)
    {
        checkIndex(index);
        ListNode<E> traverse = nodeAt(index);
        traverse.data = value;
    }


}
