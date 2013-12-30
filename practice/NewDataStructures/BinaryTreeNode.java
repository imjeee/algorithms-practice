package NewDataStructures;

public abstract class BinaryTreeNode {
  private BinaryTreeNode left;
  private BinaryTreeNode right;
  
  public abstract int value();
  
  public abstract void value(int value);
  
  public BinaryTreeNode left() {
    return this.left;
  }
  
  public void left(BinaryTreeNode left) {
    this.left = left;
  }
  
  public BinaryTreeNode right() {
    return this.right;
  }
  
  public void right(BinaryTreeNode right) {
    this.right = right;
  }

  public abstract boolean insert(int value);
}
