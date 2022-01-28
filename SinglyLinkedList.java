/**
 * This is generic type for singly linkedlist implementation
 * @Author: Bohan Yang
 * @Version: January 12, 2022
 * */
public class SinglyLinkedList<E>
{
    private static class ListNode<E>
    {
        public E data;
        public ListNode<E> next;

        public ListNode(E data)
        {
            this(data, null);
        }
        public ListNode(E data, ListNode<E> next)
        {
            this.data = data;
            this.next = next;
        }
    }
    private ListNode<E> head;

    public SinglyLinkedList()
    {
        this.head = null;
    }

    public int size()
    {
        int count = 0;
        ListNode<E> traverse = this.head;
        while (traverse != null)
        {
            traverse = traverse.next;
            count++;
        }
        return count;
    }

    public E get(int index)   // get the data of given index
    {
        return nodeAt(index).data;
    }

    private ListNode<E> nodeAt(int index)
    {
        ListNode<E> traverse = this.head;
        for (int i = 0; i < index; i++)
        {
            traverse = traverse.next;
        }
        return traverse;
    }

    public int indexOf(E value) // Where is given value? give me this value index
    {
        int index = 0;
        ListNode<E> traverse = this.head;
        while (traverse != null)
        {
            if (traverse.data == value)
            {
                return index;
            }
            index++;
            traverse = traverse.next;
        }
        return -1; // if not found
    }

    public void add(E value) // add a value in the end of linked list
    {
        if (this.head == null)
        {
            this.head = new ListNode<E>(value); // instantiate a listNode
        }
        else
        {
            ListNode<E> traverse = this.head;
            while (traverse.next != null)
            {
                traverse = traverse.next;
            }
            traverse.next = new ListNode<E>(value);
        }
    }

    public void add(int index, E value)
    {
        if (index == 0)
        {
            /*
            * (oldHead) val 1 -> val 2 -> val 3.....val n -> null
            * (newHead) newVal -> (oldHead) val 1 -> val 2 ->val 3.....val n -> null
            * */
            this.head = new ListNode<E>(value, this.head);
            // new value must be added in front of the old head
            // and value.next -> old head carries the rest of linked list
            // the new head is pointing to the new value
        }
        else
        {
            ListNode<E> traverse = nodeAt(index - 1);  // identify the ListNode before 1
            traverse.next = new ListNode<E>(value, traverse.next);
            /* (Head) val 1 -> val 2 -> val 3 -> .....-> val n -> null if index is 3
             * (Head) val 1 -> val 2(traverse.next) -> (new Node)new value -> (old traverse(traverse.next))val 3 -> .....-> val n -> null
             * (Head) val 1 -> val 2 -> new value -> (old traverse)-> val 3 -> .....-> val n -> null
             * (Head) val 1 -> val 2 -> new value -> val 3 -> .....-> val n -> null
             */
        }
    }

    public void set(int index, E value)
    {
        if (index < size())
        {
            nodeAt(index).data = value; // change original data to given value
        }
    }

    public void remove(int index)
    {
        if (index == 0)
        {
            this.head = this.head.next; // reset the head to original head next element
            // (Head) val 1 -> (head.next)val 2 -> val 3 -> .....-> val n -> null
            // (Head) -> val 2 -> val 3 -> .....-> val n -> null
        }
        else
        {
            ListNode<E> traverse = nodeAt(index - 1); // find the removeIndex previous element
            traverse.next = traverse.next.next;  // skip the remove value by setting to .next.next
        }
    }

    public void removeAll(E value)  // remove all that is this value
    {
        for (int index = 0; index < size(); index++)
        {
            if (get(index) == value) // get return's the data at index
            {
                remove(index);
                index--;
            }
        }
    }

    public String toString()
    {
        if (this.head == null)
        {
            return "[]";
        }
        else
        {
            String result = "[" + this.head.data;
            ListNode<E> traverse = this.head.next;
            while (traverse != null)
            {
                result = ", " + traverse.data;
                traverse = traverse.next;
            }
            result += "]";
            return result;
        }
    }
}
