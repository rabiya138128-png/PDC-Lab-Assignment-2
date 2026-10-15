public class TransactionStack {
    private char[] stackArray;
    private int top;
    private int capacity;

    public TransactionStack(int size) {
        capacity = size;
        stackArray = new char[capacity];
        top = -1;
    }

    public void push(char c) {
        if (top < capacity - 1) {
            stackArray[++top] = c;
        }
    }
}