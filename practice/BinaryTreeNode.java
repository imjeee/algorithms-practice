
public abstract class BinaryTreeNode {
  private int value;
  private BinarySearchTreeNode left;
  private BinarySearchTreeNode right;
  
  public BinarySearchTreeNode left() {
    return this.left;
  }
  
  public int value() {
    return this.value;
  }
  
  public void value(int value) {
    this.value = value;
  }
  
  public void left(BinarySearchTreeNode left) {
    this.left = left;
  }
  
  public BinarySearchTreeNode right() {
    return this.left;
  }
  
  public void right(BinarySearchTreeNode right) {
    this.right = right;
  }

  public abstract boolean insert(int value);
}
