public class ForthStack extends ArrayStack implements Forth {

    public ForthStack(int size) {
        super(size);
    }

    public void add() throws Exception {
        double a = pop();
        double b = pop();
        push(a + b);
    }

    public void sub() throws Exception {
        double a = pop();
        double b = pop();
        push(b - a);
    }

    public void multi() throws Exception {
        double a = pop();
        double b = pop();
        push(a * b);
    }

    public void div() throws Exception {
        double a = pop();
        double b = pop();
        push(b / a);
    }

    public void duplicate() throws Exception {
        double a = peek();
        push(a);
    }

    public void doubleDuplicate() throws Exception {
        if (top < 2) {
            throw new IllegalStateException("At least 2 items must exist");
        }
        double first = peek(0);
        double second = peek(1);

        push(first);
        push(second);
    }

}
