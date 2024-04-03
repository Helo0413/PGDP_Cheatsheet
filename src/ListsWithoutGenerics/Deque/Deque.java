package ListsWithoutGenerics.Deque;

public class Deque {

	private Element head;
	private Element tail;

	private int size = 0;
	public Deque() {
		head = null;
		tail = null;

	}

	public int size(){
		return size;
	}

	public void addFirst(int value){
		if(isEmpty()){
			head = new Element(value);
			tail = head;
		}else {
			Element add = new Element(value, head);
			//set the next reference of head to add, which has a prev reference to head
			//before moving the pointer of head from the previous head to the newly added element
			head.prev = add;
			head = add;

		}
		size++;
	}

	public int removeFirst(){
		if(isEmpty()){
			return Integer.MIN_VALUE;
		}
		int pop = getFirst();
		if(head == tail){
			tail = null;
			head = null;
		}else{
			head = head.next;
			head.prev = null;
		}
		size--;
		return pop;
	}

	public int getFirst(){
		if(isEmpty()){
			return Integer.MIN_VALUE;
		}
		return head.val;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public void addLast(int value){
		if(isEmpty()){
			tail = new Element(value);
			head = tail;
		}else{
			tail = new Element(tail, value);
			//add reference on the previous element to tail, "warn" it has a next element.
			tail.prev.next = tail;
		}
		size++;

	}
	public int removeLast(){
		if(isEmpty()){
			return Integer.MIN_VALUE;
		}
		int pop = getLast();
		if(head == tail){
			head = null;
			tail = null;
		}else {
			tail = tail.prev;
			tail.next = null;
		}

		size--;
		return pop;
	}

	public int getLast(){
		if(isEmpty()){
			return  Integer.MIN_VALUE;
		}
		return tail.val;
	}


	@Override
	public String toString() {
		if (isEmpty()) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Element current = head; current != null; current = current.next) {
			sb.append(current.val).append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append("}");
		return sb.toString();
	}


	private class Element {
		Element prev;
		Element next;
		int val;

		Element(int v) {
			val = v;
		}

		Element(int v, Element n) {
			val = v;
			next = n;
		}

		Element(Element p, int v) {
			val = v;
			prev = p;
		}
	}

}
