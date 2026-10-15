public class TransactionStack {
    private char[] stackArray;
    private int top;
    private int capacity;

    public TransactionStack(int size) {
        capacity = size;
        stackArray = new char[capacity];
        top = -1;
    }
}