package ListsWithoutGenerics;

public class List {

	private Element head;
	private Element tail;
	private int size;

	public List() {
		head = null;
		tail = null;
		size = 0;
	}

	/*
	 * returns size/length of the list
	 */
	public int size() {
		return size;
	}

	/*
	 * returns <true> if the list is empty, otherwise <false>
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/*
	 * removes all elements from the list
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/*
	 * adds an element at the end of the list
	 */
	public void add(int element) {
		if(tail==null){
			head = new Element(element);
			tail = head;
		}else{
			tail.next = new Element(element);
			tail = tail.next;
		}
		size++;
	}

	/*
	 * adds an element at the specified index
	 */
	public boolean add(int index, int element) {
		if (index < 0 || size < index) {
			return false;
		}
		if(head == null){
			head = new Element(element);
			tail = head;
		}else if(index == 0){
			head = new Element(element, head);
		}else if(index == size){
			tail.next = new Element(element);
		}else{
			Element wishedIndex = head;
			while(index>1){
				wishedIndex = wishedIndex.next;
				index--;
			}
			wishedIndex.next = new Element(element, wishedIndex.next);
		}

		size++;
		return true;

	}

	/*
	 * returns the value of the element at the specified index returns default value
	 * (minimum value of an integer) iff. such an element does not exist.
	 */
	public int get(int index) {
		if(size < index || index < 0 ||  head == null){
			return Integer.MIN_VALUE;
		}
		Element wishedIndex = head;
		while(index > 0){
			wishedIndex = wishedIndex.next;
			index--;
		}

		return wishedIndex.value;
	}

	/*
	 * removes the element at the specified index
	 */
	public void remove(int index) {
		if (index < 0 || size <= index || isEmpty()) {
			// size smaller OR equals index since index starts at 0, while size starts at one!
			return;
		}

		if(size == 1){
			head = null;
			tail = null;
		}if(index == 0){
			head = head.next;
		}else{
			Element wishedIndex = head;
			while(index>1){
				wishedIndex = wishedIndex.next;
				index--;
			}
			wishedIndex.next = wishedIndex.next.next;

			if(index == size-1){
				tail = wishedIndex;
			}
		}
		size--;
	}

	/*
	 * returns String representation of the list
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("[ ");
		Element current = head;
		for (int i = 0; i < size; i++) {
			out.append(current.toString());
			if (i != size - 1) {
				out.append(", ");
			}
			current = current.next;
		}
		out.append(" ]");
		return out.toString();
	}

	private static class Element {
		private int value;
		private Element next;

		Element(int value) {
			this.value = value;
			next = null;
		}

		Element(int value, Element next) {
			this.value = value;
			this.next = next;
		}

		/*
		 * returns String representation of the element
		 */
		@Override
		public String toString() {
			return "" + value;
		}
	}

}