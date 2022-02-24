public class SplayTree<T extends Comparable<? super T>>
{
    class BinaryNode<T>
    {
        T data;
        BinaryNode<T> left;
        BinaryNode<T> right;
        BinaryNode(T element)
        {
            this.data = element;
            this.left = this.right = null;
        }
    }
    private BinaryNode<T> root;
    private BinaryNode<T> nullNode;
    private BinaryNode<T> newNode = null;   // used between different inserts
    private BinaryNode<T> header = new BinaryNode<>(null); // For splay

    public SplayTree()
    {
        this.nullNode = new BinaryNode<>(null);
        this.nullNode.left = this.nullNode.right = this.nullNode;
        this.root = this.nullNode;
    }

    public void insert(T element)
    {
        if (this.newNode == null)        // reach base case, instantiate the new node in place but with data to be null
        {
            this.newNode = new BinaryNode<T>(null);
        }
        this.newNode.data = element;   // then assign the newNode data to be the inserting data

        if (this.root == this.nullNode)
        {
            this.newNode.left = this.newNode.right = this.nullNode;
            this.root = this.newNode;
        }
        else
        {
            this.root = splay(element, this.root);
            if (element.compareTo(this.root.data) < 0)
            {
                this.newNode.left = this.root.left;
                this.newNode.right = root;
                this.root.left = this.nullNode;
                this.root = this.newNode;
            }
            else if (element.compareTo(this.root.data) > 0)
            {
                this.newNode.right = this.root.right;
                this.newNode.left = this.root;
                this.root.right = this.nullNode;
                this.root = this.newNode;
            }
            else
            {
                throw new IllegalArgumentException("duplicates");
            }
            this.newNode = null;
        }
    }
    public void remove(T element)
    {
        BinaryNode<T> newTree;
        if (this.root.left == this.nullNode)
        {
            newTree = this.root.right;
        }
        else
        {
            newTree = this.root.left;
            newTree = splay(element, newTree);
            newTree.right = this.root.right;
        }
        this.root = newTree;
    }
    public T find(T element)
    {
        this.root = splay(element, this.root);
        if (isEmpty() || this.root.data.compareTo(element) != 0)
        {
            return null;
        }
        return this.root.data;
    }
    public T findMax()
    {
        BinaryNode<T> ptr = this.root;
        while (ptr.right != this.nullNode)
        {
            ptr = ptr.right;
        }
        root = splay(ptr.data, this.root);
        return ptr.data;
    }
    public T findMin()
    {
        BinaryNode<T> ptr = this.root;
        while (ptr.left != this.nullNode)
        {
            ptr = ptr.left;
        }
        root = splay(ptr.data, this.root);
        return ptr.data;
    }
    public void makeEmpty()
    {
        this.root = this.nullNode;
    }
    public boolean isEmpty()
    {
        return this.root == this.nullNode;
    }
    private BinaryNode<T> splay(T element, BinaryNode<T> root)
    {
        BinaryNode<T> leftTreeMax, rightTreeMin;
        this.header.left = this.header.right = this.nullNode;
        leftTreeMax = rightTreeMin = this.header;
        this.nullNode.data = element;   // guarantee a match

        for ( ; ; )
        {
            if (element.compareTo(root.data) < 0)
            {
                if (element.compareTo(root.left.data) < 0)
                {
                    root = rightRotation(root);
                }
                if (root.left == this.nullNode)
                {
                    break;
                }
                // link right
                rightTreeMin.left = root;
                rightTreeMin = root;
                root = root.left;
            }
            else if (element.compareTo(root.data) > 0)
            {
                if (element.compareTo(root.right.data) > 0)
                {
                    root = leftRotation(root);
                }
                if (root.right == this.nullNode)
                {
                    break;
                }
                // link left
                leftTreeMax.right = root;
                leftTreeMax = root;
                root = root.left;
            }
            else
            {
                break;
            }
        }
        leftTreeMax.right = root.left;
        rightTreeMin.left = root.right;
        root.left = this.header.right;
        root.right = this.header.left;
        return root;
    }
    private BinaryNode<T> rightRotation(BinaryNode<T> k2)   // rotation with left child
    {
        BinaryNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    private BinaryNode<T> leftRotation(BinaryNode<T> k1)    // rotation with right child
    {
        BinaryNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
}
