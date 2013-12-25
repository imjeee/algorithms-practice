package NewDataStructures;

public class CircularQueue {
    private int[] content;
    private int maxSize;
    private int size;
    private int front;
    private int back;
    
    public CircularQueue(int num) {
        initialize(num);
    }
    
    private void initialize(int num) {
        this.maxSize = num;
        this.content = new int[num];
        this.size = 0;
        this.front = 0;
        this.back = 0;
    }
    
    public void enqueue(int num) {
        content[this.back] = num;
        this.back++;
        if (this.size == this.maxSize)
            this.front++;
        else
            this.size++;
        lookFrontAndBackIfNecessary();
    }
    
    public int dequeue() throws Exception {
        if (this.size == 0) {
            String error = "this queue is empty";
            throw new Exception(error);
        }
        
        int result = content[this.front];
        front++;
        size--;
        lookFrontAndBackIfNecessary();
        return result;
    }
    
    public int size() {
        return this.size;
    }
    
    private void lookFrontAndBackIfNecessary() {
        if (this.back == this.maxSize)
            this.back = 0;
        else if (this.front == this.maxSize)
            this.front = 0;
    }
}
