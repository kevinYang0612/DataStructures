import java.util.Stack;

/**
 * This class is special stack that support in findMin in O(1) time
 * */
public class SpecialStack
{
    Stack<Integer> stack;
    Integer minElement;

    public SpecialStack(){ this.stack = new Stack<Integer>(); }

    public Integer findMin()
    {
        if (this.stack.isEmpty()) throw new RuntimeException("Stack is empty");
        return this.minElement;
    }
    public Integer pop()
    {
        if (this.stack.isEmpty()) throw new RuntimeException("Stack is empty");
        Integer result = null;
        Integer t = this.stack.pop();
        if (t < this.minElement)        // check if popping element is smaller than min
        {
            result = this.minElement;
            this.minElement = 2 * this.minElement - t;  // update the min element
        }                                               // unique way to unraveling
        return result;
    }
    public void push(int t)
    {
        if (this.stack.isEmpty())
        {
            this.minElement = t;
            this.stack.push(t);
            return;
        }
        if (t < this.minElement)        // if pushing in element is smaller
        {
            this.stack.push(2 * t - this.minElement);       // unique way to store
            this.minElement = t;            // keep tracking the smaller element
        }
        else
        {
            this.stack.push(t);
        }
    }
    public static void main(String[] args)
    {
        SpecialStack stack = new SpecialStack();
        stack.push(-6);
        stack.push(3);
        stack.push(0);
        stack.push(0);
        stack.push(12);
        stack.push(-7);
        System.out.println(stack.findMin());
        System.out.println(stack.pop());
        System.out.println(stack.findMin());
    }
}
