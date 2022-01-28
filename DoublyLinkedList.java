/**
 * This is doubly linked list source code
 * */
public class DoublyLinkedList<T> implements Iterable<T>
{
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // inner class define Node type
    private static class Node<T>
    {
        private T data;
        private Node<T> previous;
        private Node<T> next;

        public Node(T data, Node<T> previous, Node<T> next)
        {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
        @Override
        public String toString()
        {
            return this.data.toString();
        }
    }
    // clear everything
    public void clear()
    {
        Node<T> traverse = this.head;
        while (traverse != null)
        {
            Node<T> next = traverse.next;
            traverse.previous = traverse.next = null;
            traverse.data = null;
            traverse = next;
        }
        this.head = this.tail = traverse = null;
        this.size = 0;
    }
    public int size()
    {
        return this.size;
    }
    public boolean isEmpty()
    {
        return size() == 0;
    }
    /**
     * by default, add the element to the end of the list
     * */
    public void add(T element)
    {
        addLast(element);
    }
    public void addLast(T element)
    {
        if (isEmpty()) // if empty add the element at first and link prev and next null
        {
            this.head = new Node<T>(element, null, null);
            this.tail = new Node<T>(element, null, null);
        }
        else // not empty, add after tail, so that previous is tail, next is null
        {
            this.tail.next = new Node<T>(element, this.tail, null);
            this.tail = this.tail.next;  // reset the tail to tail.next
        }
        this.size++;
    }
    public void addFirst(T element)
    {
        // AKA empty list
        if (isEmpty())
        {
            this.head = new Node<T>(element, null, null);
            this.tail = new Node<T>(element, null, null);
        }
        else  // if not empty list, we are trying to add in the front of the list
        {
            // new node at the head previous
            this.head.previous = new Node<T>(element, null, this.head);
            this.head = this.head.previous; // reset the head pointer to the head previous
            // because the original head is taken by new Node, reset the new head to be head.previous
        }
        this.size++;
    }
    public T peekFirst()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Empty List");
        }
        return this.head.data;
    }
    public T peekLast()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Empty List");
        }
        return this.tail.data;
    }
    public T removeFirst()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Empty List");
        }
        T data = this.head.data;  // get the data
        this.head = this.head.next;  // reset the head to original head.next
        --this.size;

        if (isEmpty())
        {
            this.tail = null;  // memory clean up
        }
        else
        {
            this.head.previous = null;
        }
        return data;
    }
    public T removeLast()
    {
        if (isEmpty()) throw new RuntimeException("Empty List");
        // Extract the data at the tail and move
        // the tail pointer backwards one node
        T data = this.tail.data;
        this.tail = this.tail.previous;
        --size;
        // if the list is now empty set and head to null
        if (isEmpty()) this.head = null;

        // do a memory clean of the node that was just removed
        else this.tail.next = null;
        return data;
    }
    private T remove(Node<T> node)  // in support of removeAt and remove(object), not for public use
    {
        // 如果要移除的node前面什么都没有，说明在头，remove 第一个
        // 如果要移除的node后面什么都没有，说明在尾，remove 最后一个
        if (node.previous == null) return removeFirst();
        if (node.next == null) removeLast();

        // Make the pointers of adjacent nodes skip over 'node'
        // If provided a removing object, go to the object do following
        node.next.previous = node.previous;
        node.previous.next = node.next;

        T data = node.data;

        node.data = null;
        node = node.previous = node.next = null;
        --size;
        return data;
    }
    public boolean remove(Object obj)
    {
        Node<T> traverse;

        // support searching for null
        if (obj == null)
        {
            for (traverse = this.head; traverse != null; traverse = traverse.next)
            {
                if (traverse.data == null)
                {
                    remove(traverse);
                    return true;
                }
            }
        }
        else
        {
            for (traverse = this.head; traverse != null; traverse = traverse.next)
            {
                if (obj.equals(traverse.data))
                {
                    remove(traverse);
                    return true;
                }
            }
        }
        return false;
    }
    public int indexOf(Object obj)
    {
        int index = 0;
        Node<T> traverse = this.head;
        if (obj == null)
        {
            for (; traverse != null; traverse = traverse.next, index++)
            {
                // support searching for null
                if (traverse.data == null)
                {
                    return index;
                }
            }
        }
        else
        {
            for (; traverse != null; traverse = traverse.next, index++)
            {
                // support searching for null
                if (obj.equals(traverse.data))
                {
                    return index;
                }
            }
        }
        return -1;
    }
    public void toCircle()
    {
        Node<T> start = this.head;
        while (this.head.next != null)
        {
            this.head = this.head.next;
        }
        head.next = start;
    }

    /*
    Depends on what is provided, if provided a removing index, go to the one before removing element
    If provided a removing object, go to the object
    * */
    public void removeAtInDoubly(int index) // remove with an index
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index Out of bound");
        }
        int i;
        Node<T> traverse;
        if (index < size / 2)
        {
            for (i = 0, traverse = this.head; i < index + 1; i++) // get to the node before
            {
                traverse = traverse.next;
            }
        }
        else
        {
            for (i = size - 1, traverse = this.tail; i >= index; i--)
            {
                traverse = traverse.previous;
            }
        }
        remove(traverse);
//        node.next = node.next.next;   // node.next is already update, skipped the removing node,
//        node.next.previous = node;    // node.next is already updated, the element behind removing element
//        size--;                       // previous pointing to itself
    }
    public boolean contains(Object obj)
    {
        return indexOf(obj) != -1;
    }
    @Override
    public java.util.Iterator<T> iterator()
    {
        return new java.util.Iterator<T>()
        {
            private Node<T> traverse = head;
            @Override
            public boolean hasNext()
            {
                return traverse != null;
            }
            @Override
            public T next()
            {
                T data = traverse.data;
                traverse = traverse.next;
                return data;
            }
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> traverse = this.head;
        while (traverse != null)
        {
            sb.append(traverse.data).append(" ");
            traverse = traverse.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
