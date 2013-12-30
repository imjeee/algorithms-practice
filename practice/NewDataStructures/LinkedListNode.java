package NewDataStructures;

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
  
  public LinkedListNode<E> insert(LinkedListNode<E> head, E data) {
    LinkedListNode<E> node = head;
    while (node.next != null) {
      node = node.next;
    }
    LinkedListNode<E> endNode = new LinkedListNode<E>(data);
    node.next = endNode;
    return head;
  }
  
  public LinkedListNode<E> delete(LinkedListNode<E> head, E data) {
    LinkedListNode<E> node = head;
    if (head.value.equals(data)) {
      return head.next;
    }
    while (node.next != null && !node.next.value.equals(data)) {
      node = node.next;
    }
    
    if (node.next != null) {
      node.next = node.next.next;
    }
    
    return head;
  }
  
}
