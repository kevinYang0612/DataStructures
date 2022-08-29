
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
        // if (!contains(element)) return false;

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
                else        // the removing element has 2 children
                {
                    // purpose is looking for the smallest element in the right subtree to replace removing element
                    Node current = root.right;
                    Node previous = root;
                    /*
                    * Going to right subtree but the smallest element should be at on left side
                    * */
                    while (current.left != null)
                    {
                        previous = current;         // update both pointers
                        current = current.left;     // current is the smallest element in the left subtree
                    }
                    // when found the smallest element
                    root.data = current.data;       // overwrite the removing element to smallest found in left subtree
                    if (previous.left == current)   // clear out the duplicates
                    {
                        previous.left = null;       // check left if equals to current, make prev.left to null
                    }
                    else if (previous.right == current) // only right child situation,
                    {
                        previous.right = current.right; // skip over the current, by setting to current.right
                    }
//                    root.data = findMin(root.right).data;    // overwrite the removing data with Min of right subtree
//                    root.right = remove(root.right, root.data); // now, delete the overriding data.

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
        return findMin(this.root).data;
    }
    private Node findMin(Node root)
    {
        while (root.left != null) root = root.left;
        return root;
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
    public void printInOrder()
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
        if (root != null)
        {
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.println(root.data + " ");
        }

    }
    public int numberOfOneChildNodes()
    {
        return numberOfOneChildNodes(this.root);
    }
    public int numberOfOneChildNodes(Node tree)
    {
        if (tree == null)
        {
            return 0;
        }
        else if (tree.left == null && tree.right != null)
        {
            return 1 + numberOfOneChildNodes(tree.right);
        }
        else if (tree.right == null && tree.left != null)
        {
            return 1 + numberOfOneChildNodes(tree.left);
        }
        else
        {
            return numberOfOneChildNodes(tree.right) + numberOfOneChildNodes(tree.left);
        }
    }
    /*
    * Below methods are from Mark Weiss
    * */
    public void insert(T element) { this.root = insert(this.root, element); }
    private Node insert(Node root, T element)
    {
        if (root == null)
        {
            root = new Node(null, null, element);
        }
        else if (element.compareTo(root.data) < 0)
        {
            root.left = insert(root.left, element);
        }
        else if (element.compareTo(root.data) > 0)
        {
            root.right = insert(root.right, element);
        }
        else
        {
            throw new RuntimeException("duplicates");
        }
        return root;
    }
    public void delete(T element)
    {
        this.root = remove(this.root, element);
    }
    private Node delete(Node root, T element)
    {
        if (root == null)
        {
            throw new RuntimeException("Not Found");
        }
        if (element.compareTo(root.data) < 0)
        {
            root.left = remove(root.left, element);
        }
        else if (element.compareTo(root.data) > 0)
        {
            root.right = remove(root.right, element);
        }
        else if (root.left != null && root.right != null)
        {
            root.data = findMin(root.right).data;
            root.right = deleteMin(root.right);
        }
        else
        {
            root = (root.left != null) ? root.left : root.right;
        }
        return root;
    }
    public Node deleteMin(Node root)    // min is gonna be at the left subtree
    {
        if (root == null)
        {
            throw new RuntimeException();
        }
        else if (root.left != null)
        {
            root.left = deleteMin(root.left);
            return root;
        }
        else
        {
            return root.right;
        }
    }
    public T getMin()
    {
        return elementAt(getMin(this.root));
        // It is equivalent to getMin(this.root).data;
    }
    private Node getMin(Node root)
    {
        if (root != null)
        {
            while (root.left != null)
            {
                root = root.left;
            }
        }
        return root;
    }
    public T getMax()
    {
        return elementAt(getMax(this.root));
        // It is equivalent to getMax(this.root).data;
    }
    private Node getMax(Node root)
    {
        if (root != null)
        {
            while (root.right != null)
            {
                root = root.right;
            }
        }
        return root;
    }
    public T find(T element)
    {
        return elementAt(find(this.root, element));
    }
    private Node find(Node root, T element)
    {
        while (root != null)
        {
            if (root.data.compareTo(element) > 0)
            {
                root = root.left;
            }
            else if (root.data.compareTo(element) < 0)
            {
                root = root.right;
            }
            else
            {
                return root;
            }
        }
        return null; // when not found
    }
    /**
     * This is a getter method that returns the node data
     * */
    private T elementAt(Node element)
    {
        return element == null ? null : element.data;
    }
    public static void main(String[] args)
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(20);
        bst.add(11);
        bst.add(21);
        bst.add(24);
        bst.add(22);
        bst.add(37);
        bst.add(40);
        bst.add(7);
        bst.add(1);
        bst.add(6);
        bst.add(4);
        System.out.println(bst.findMax());
        System.out.println(bst.findMin());
        System.out.println();
        bst.remove(6);
        bst.remove(37);

        bst.printInOrder();
        bst.printPreOrder();
        bst.printPostOrder();

        System.out.println(bst.numberOfOneChildNodes());



    }
}
