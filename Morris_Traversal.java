public class Morris_Traversal
{
    class Node
    {
        int val;
        Node left;
        Node right;

        Node(int data)
        {
            val = data;
            left = null;
            right = null;
        }
    }
    class Tree
    {
        Node root;

        void Morris(Node root)
        {
            Node curr;
            Node prev;

            if (root == null)
                return;

            curr = root;
            while (curr != null)
            {
                if (curr.left == null)
                {
                    System.out.println(curr.val + " ");
                    curr = curr.right;
                }
                else
                {
                    prev = curr.left;
                    while (prev.right != null && prev.right != curr)
                    {
                        prev = prev.right;
                    }
                    if (prev.right == null)
                    {
                        prev.right = curr;
                        curr = curr.left;
                    }
                    else
                    {
                        prev.right = null;
                        System.out.println(curr.val + " ");
                        curr = curr.right;
                    }
                }
            }
        }
    }

}
