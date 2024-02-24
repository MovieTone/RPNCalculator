import java.util.EmptyStackException;

public class ArrayStack extends AbstractStack {

    private final double[] stackArray;
    protected int maxSize;
    protected int top;

    public ArrayStack(int size) {
        maxSize = size;
        stackArray = new double[maxSize];
        top = 0;
    }

    @Override
    public void push(double item) throws Exception {
        if (top < maxSize) {
            stackArray[top] = item;
            top++;
        } else {
            throw new Exception("Stack Overflow");
        }
    }

    @Override
    public double pop() throws EmptyStackException {
        try {
            return stackArray[--top];
        } catch (ArrayIndexOutOfBoundsException error) {
            top = 0;
            throw new EmptyStackException();
        }
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    public double peek(int n) throws EmptyStackException {
        if (isEmpty() || n < 0 || n > top) {
            throw new EmptyStackException();
        }

        return stackArray[top - n - 1];
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < top; i++) {
                stackArray[i] = 0.0;
            }
            top = 0;
        }
    }

    @Override
    public double peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[top - 1];
    }

}
