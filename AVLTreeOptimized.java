public class AVLTreeOptimized<T extends Comparable<T>>
{
    public class AVLNode
    {
        public int bf; // balance factor
        public T data;
        public int height;
        public AVLNode left, right;
        public AVLNode(T value)
        {
            this.data = value;
        }
    }
    private AVLNode root;
    private int nodeCount = 0;
    public int height()
    {
        if (root == null) return 0;
        return this.root.height;
    }
    public int size()
    {
        return nodeCount;
    }
    public boolean isEmpty()
    {
        return size() == 0;
    }
    public boolean contains(T value)
    {
        return contains(this.root, value);
    }
    private boolean contains(AVLNode root, T value)
    {
        if (root == null) return false;

        if (value.compareTo(root.data) < 0) return contains(root.left, value);
        if (value.compareTo(root.data) > 0) return contains(root.right, value);
        return true;
    }
    public boolean insert(T value)
    {
        if (value == null) return false;
        if (!contains(this.root, value))
        {
            this.root = insert(this.root, value);
            this.nodeCount++;
            return true;
        }
        return false;
    }
    private AVLNode insert(AVLNode root, T value)
    {
        if (root == null) return new AVLNode(value);
        if (value.compareTo(root.data) < 0)
        {
            root.left = insert(root.left, value);
        }
        if (value.compareTo(root.data) > 0)
        {
            root.right = insert(root.right, value);
        }
        // update balance factor and height values
        update(root);
        return balance(root);
    }
    public boolean remove(T value)
    {
        if (value == null) return false;
        if (contains(this.root, value))
        {
            this.root = remove(this.root, value);
            this.nodeCount--;
            return true;
        }
        return false;
    }
    private AVLNode remove(AVLNode root, T value)
    {
        if (root == null) return null;
        if (value.compareTo(root.data) < 0)
        {
            root.left = remove(root.left, value);
        }
        else if (value.compareTo(root.data) > 0)
        {
            root.right = remove(root.right, value);
        }
        else
        {
            if (root.left.height > root.right.height)   // we choose from the left subtree
            {
                T max =  findMax(root.left);
                root.data = max;
                root.left = remove(root.left, max);
            }
            else                                       // we choose from the right subtree
            {
                T min = findMin(root.right);
                root.data = min;
                root.right = remove(root.right, min);
            }
        }
        update(root);
        return balance(root);
    }
    private T findMin(AVLNode root)
    {
        while (root.left != null) root = root.left;
        return root.data;
    }
    private T findMax(AVLNode root)
    {
        while (root.right != null) root = root.right;
        return root.data;
    }
    private void update(AVLNode root)
    {
        // get height in the left subtree or right subtree
        int leftNodeHeight = (root.left == null) ? -1 : root.left.height;
        int rightNodeHeight = (root.right == null) ? -1 : root.right.height;

        // update root's height
        root.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

        root.bf = rightNodeHeight - leftNodeHeight;
    }
    private AVLNode balance(AVLNode root)
    {
        if (root.bf == -2) // it means right height - left height = -2, left height is too much
        {
            if (root.left.bf <= 0)  // left subtree heavy with left child
            {
                return rightRotation(root); // or rotate with left child
            }
            else        // left subtree heavy with right child
            {
                return leftRightRotation(root);    // double rotation with first left then right
            }
        }
        else if (root.bf == 2)
        {
            if (root.right.bf >= 0)   // right subtree heavy with right child
            {
                return leftRotation(root);    // rotate with right child
            }
            else                    // right subtree heavy with left child
            {
                return rightLeftRotation(root); // double rotation with first right then left
            }
        }
        // this is when balance factor if 0, -1 or +1. which is fine
        return root;
    }
    private AVLNode rightRotation(AVLNode root) // tree is going to tilt to right, with a left child
    {
        AVLNode newNode = root.left;
        root.left = newNode.right;
        newNode.right = root;
        update(root);
        update(newNode);
        return newNode;
    }
    private AVLNode leftRotation(AVLNode root)  // tree is going to tilt to left, with a right child
    {
        AVLNode newNode = root.right;
        root.right = newNode.left;
        newNode.left = root;
        update(root);
        update(newNode);
        return newNode;
    }
    private AVLNode leftRightRotation(AVLNode root) // tree is first tilt to left and then right with right child
    {
        root.left = leftRotation(root.left);
        return rightRotation(root);
    }
    private AVLNode rightLeftRotation(AVLNode root) // tree is first tilt to right and then left with left child
    {
        root.right = rightRotation(root.right);
        return leftRotation(root);
    }
}
