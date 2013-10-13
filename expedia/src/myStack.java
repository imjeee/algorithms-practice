import java.util.LinkedList;

public class myStack {
	
	LinkedList<Object> ll;
	
	public myStack() {
		ll = new LinkedList<Object>();
	}
	
	public Object pop() throws Exception{
		if (ll.isEmpty()){
			throw new Exception();
		} else {
			return ll.removeFirst();
		}
	}
	
	public void push(Object o){
		ll.addFirst(o);
	}
}

	