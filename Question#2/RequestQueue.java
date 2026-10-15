public class RequestQueue {
    private char[] queueArray;
    private int front;
    private int rear;
    private int capacity;
    private int size;

    public RequestQueue(int capacity) {
        this.capacity = capacity;
        queueArray = new char[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(char c) {
        if (size < capacity) {
            rear = (rear + 1) % capacity;
            queueArray[rear] = c;
            size++;
        }
    }

    public char dequeue() {
        if (size > 0) {
            char temp = queueArray[front];
            front = (front + 1) % capacity;
            size--;
            return temp;
        }
        return '\0';
    }
}