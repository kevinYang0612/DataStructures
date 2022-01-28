public class BinarySearchTree<T extends Comparable<T>>
{
    private int nodeCount = 0;
    private Node root = null;

    private class Node
    {
        T data;
        Node left;
        Node right;

        public Node(Node left, Node right, T element)
        {
            this.data = element;
            this.left = left;
            this.right = right;
        }
    }
    public boolean isEmpty(){return size() == 0;}
    public int size(){ return nodeCount;}

    /**
     * add an element to binary tree return true if successfully added
     * */
    public boolean add(T element)
    {
        if (contains(element))
            return false; // already existed, cannot add in
        else
        {
            this.root = add(this.root, element);
            nodeCount++;
            return true;
        }
    }
    private Node add(Node root, T element)
    {
        if (root == null)  // if root.left or root.right becomes the new root, and it is null, instantiate the node
        {
            root = new Node(null, null, element);
        }
        else                // keep searching until found the spot to instantiate
        {
            if (element.compareTo(root.data) < 0)       // element is smaller than root, go to left
            {
                root.left = add(root.left, element);     // updating the root
            }
            else                                        // element is smaller than root, go to left
            {
                root.right = add(root.right, element);  // updating
            }
        }
        return root;
    }
    public boolean contains(T element)
    {
        return contains(this.root, element);
    }
    private boolean contains(Node root, T element)
    {
        if (root == null) return false;

        int test = element.compareTo(this.root.data); // if element is smaller, test is negative, be at left subtree

        if (test < 0) return contains(root.left, element); // keep search at left
        else if (test > 0) return contains(root.right, element); // search at right
        else return true;
    }
    public boolean remove(T element)
    {
        if (!contains(element)) return false;

        this.root = remove(this.root, element);
        nodeCount--;
        return true;
    }
    private Node remove(Node root, T element)
    {
        if (root != null)
        {
            int test = element.compareTo(root.data);
            if (test < 0)       // This is searching at the left
            {
                root.left = remove(root.left, element);
            }
            else if (test > 0)  // searching at the right
            {
                root.right = remove(root.right, element);
            }
            else                // Found it
            {
                if (root.left == null) // the removing element has right subtree
                {
                    root = root.right;  // update the root
                }
                else if (root.right == null)    // the removing element has left subtree
                {
                    root = root.left;
                }
                else
                {
                    root.data = findMin(root.right);    // overwrite the removing data with Min of right subtree
                    root.right = remove(root.right, root.data); // now, delete the overriding data.

                    /*
                    * Or we can find the Max in the left subtree to replace
                    * the spot of removing element
                    * root.data = findMax(root.left);
                    * root.left = remove(root.left, root.data);
                    * */
                }
            }
        }
        return root;
    }
    public T findMin()
    {
        if (this.root == null) throw new java.util.NoSuchElementException();
        return findMin(this.root);
    }
    private T findMin(Node root)
    {
        while (root.left != null) root = root.left;
        return root.data;
    }
    /*
    * or
    * private T findMin(Node root)
    * {
    *       if (this.left != null)
    *       {
    *           return findMin(root.left);
    *       }
    *       else
    *       {
    *           return root.data;
    *       }
    * }
    *
    * */
    public T findMax()
    {
        if (this.root == null) throw new java.util.NoSuchElementException();
        return findMax(this.root);
    }
    private T findMax(Node root)
    {
        while (root.right != null) root = root.right;
        return root.data;
    }
    public int height()
    {
        return height(this.root);
    }
    private int height(Node root)
    {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    public void print()
    {
        printInOrder(this.root);
        System.out.println();
    }
    private void printInOrder(Node root)    // <left>   <root>  <right>
    {
        if (root != null)
        {
            printInOrder(root.left);
            System.out.println(root.data + " ");
            printInOrder(root.right);
        }
    }
    public void printPreOrder()
    {
        printPreOrder(this.root);
        System.out.println();
    }
    private void printPreOrder(Node root)       // <root> <left> <right>
    {
        if (root != null)
        {
            System.out.println(root.data + " ");
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }
    public void printPostOrder()
    {
        printPostOrder(this.root);
        System.out.println();
    }
    private void printPostOrder(Node root)      // <left> <right> <root>
    {
        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.println(root.data + " ");
    }

}
