public class UnionFind
{
    // the number of elements in this union find
    private int size;

    // used to track the sizes of each of the components
    private int[] sizes;

    // id[i] points to the parent of i, if id[i] = i is a root node
    private int[] id; // like A -> A

    // tracks the numbers of components in the union find
    private int numComponents;

    public UnionFind(int size)
    {
        if (size <= 0)
            throw new IllegalArgumentException("Size is not allowed");
        this.size = this.numComponents = size;
        this.sizes = new int[size];
        this.id = new int[size];

        for (int i = 0; i < size; i++)
        {
            this.id[i] = i;     // every node parent is itself, eg. A -> A
            this.sizes[i] = 1;  // Each component is originally of size 1
        }
        /*
        E to 0, F to 1... should use HashMap
          E, F, I, D, C, A, J, L, G, K, B, H
         [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]  id
         [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]    sizes initially
          0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11   index
        * */
    }

    /**
     * given a note p, it finds which component belongs to it also does path compression
     * along the way.
     * */
    public int find(int p)
    {
        int root = p;
        while (root != this.id[root])
            root = this.id[root];

        // Path compression leading back the root
        while (p != root)
        {
            int next = this.id[p];
            this.id[p] = root;
            p = next;
        }
        return root;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }
    public int componentSize(int p)
    {
        return this.sizes[find(p)];
    }
    public int size()
    {
        return this.size;
    }
    public int components()
    {
        return this.numComponents;
    }
    public void unify(int p, int q)
    {
        int root1 = find(p);
        int root2 = find(q);

        // these elements are already in the same group
        if (root1 == root2) return;

        // Merge two components/sets together
        // Merge smaller component/set into the larger one

        // eg: this is linking the C <- K
        /*
          E, F, I, D, C, A, J, L, G, K, B, H
         [0, 1, 2, 3, 4, 5, 6, 7, 8, 4, 10, 11]  this is id
          0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11   this is index
        * */
        // note c is 4 and k is 4, C <- K
        if (this.sizes[root1] < this.sizes[root2])
        {
            this.sizes[root2] += this.sizes[root1];
            this.id[root1] = root2;
        }
        else
        {
            this.sizes[root1] += this.sizes[root2];
            this.id[root2] = root1;
        }

        // since the roots found are different we know that the number of component/sets has decreased by one
        this.numComponents--;
    }
}
