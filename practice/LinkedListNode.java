
public class LinkedListNode<E> {
  private E value;
  private LinkedListNode<E> next;

  public LinkedListNode(E value) {
    this.value = value;
  }
  
  public E value() {
    return this.value;
  }
  
  public void setValue(E value) {
    this.value = value;
  }
  
  public LinkedListNode<E> next() {
    return this.next;
  }
  
  public void next(LinkedListNode<E> node) {
    this.next = node;
  }
  
}
