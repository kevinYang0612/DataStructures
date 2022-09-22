import java.util.ArrayList;
import java.util.List;

/**
 * order traversals
 * iterative and recursive
 * */
public class OrderTraversal
{
    class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /**
     * inorder traversal: left, root, right
     * */
    public List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        dfsInOrder(root, list);
        return list;
    }
    private void dfsInOrder(TreeNode root, List<Integer> list)
    {
        if (root != null)
        {
            dfsInOrder(root.left, list);
            list.add(root.val);
            dfsInOrder(root.right, list);
        }
    }

    /**
     * Preorder: root, left, right
     * */
    public List<Integer> preorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        dfsPreOrder(root, list);
        return list;
    }
    private void dfsPreOrder(TreeNode root, List<Integer> list)
    {
        if (root != null)
        {
            list.add(root.val);
            dfsInOrder(root.left, list);
            dfsInOrder(root.right, list);
        }
    }

    /**
     * Postorder: left, right, root
     * */
    public List<Integer> postorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        dfsPostOrder(root, list);
        return list;
    }
    private void dfsPostOrder(TreeNode root, List<Integer> list)
    {
        if (root != null)
        {
            dfsInOrder(root.left, list);
            dfsInOrder(root.right, list);
            list.add(root.val);
        }
    }

    /**
     * iterative inorder traversal: left, root, right
     * */
    public List<Integer> inorderTraversalIterative(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null)
        {
            if (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            else
            {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * iterative preorder traversal: root, left, right
     * */
    public List<Integer> preorderTraversalIterative(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null)
        {
            if (root != null)
            {
                list.add(root.val);
                if (root.right != null)
                    stack.push(root.right);
                root = root.left;
            }
            else
                root = stack.pop();
        }
        return list;
    }

    /**
     * iterative post traversal: left, right, root
     * */
    public List<Integer> postorderTraversalIterative(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;

        while (!stack.isEmpty() || root != null)
        {
            if (root != null)
            {
                stack.push(root);
                root = root.left;
            }
            else
            {
                root = stack.peek();
                if (root.right == null || root.right == prev)
                {
                    list.add(root.val);
                    stack.pop();
                    prev = root;
                    root = null;
                }
                else
                    root = root.right;
            }
        }
        return list;
    }
}
