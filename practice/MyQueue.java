import sun.misc.Queue;


public class MyQueue<E> {
  private LinkedListNode<E> front;
  private LinkedListNode<E> end;
  private int size;
  
  public MyQueue() {
    front = null;
  }
  
  public E peek() {
    if (front == null) {
      return null;
    } else {
      return front.value();
    }
  }
  
  public boolean isEmpty() {
    return front == null;
  }
  
  public int size() {
    return this.size;
  }
  
  @Override
  public void enqueue(E e) {
    this.size++;
    if (this.front == null) {
      this.front = new LinkedListNode<E>(e);
      this.end = this.front;
    } else {
      this.end.next(new LinkedListNode<E>(e));
      this.end = this.end.next();
    }
  }
  
  public E dequeue() {
    this.size--;
    if (this.front == null) {
      return null;
    } else {
      E e = this.front.value();
      this.front = this.front.next();
      return e; 
    }
  }
}
