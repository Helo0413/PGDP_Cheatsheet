package ListsWithoutGenerics.RecursiveDoublyLinkedList;

public class RecIntListElement {
	private int value;
	private RecIntListElement next;
	private RecIntListElement prev;

	public RecIntListElement(int value) {
		this(value, null);
	}

	public RecIntListElement(int value, RecIntListElement prev) {
		this.value = value;
		next = null;
		this.prev = prev;
	}

	public RecIntListElement append(int value) {
		if (next != null) {
			return next.append(value);
		} else {
			next = new RecIntListElement(value, this);
			return next;
		}
	}

	public int get(int idx) {
		if (idx == 0) {
			return value;
		}
		if (next == null) {
			System.out.println("Invalid index: list is to short!");
			return Integer.MIN_VALUE;
		}
		return next.get(idx - 1);
	}

	public int size() {
		if (next == null) {
			return 1;
		}
		return 1 + next.size();
	}

	public boolean insert(int value, int idx) {
		if (idx < 0) {
			System.out.println("Cannot insert at negative index!");
			return false;
		}
		if (idx <= 1) {
			RecIntListElement n = new RecIntListElement(value, this);
			n.next = next;
			if (next != null) {
				next.prev = n;
			}
			next = n;
			if (idx == 0) {
				next.value = this.value;
				this.value = value;
			}
			return true;
		}
		if (next == null) {
			System.out.println("List is to short to insert at given index!");
			return false;
		}
		return next.insert(value, idx - 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		RecIntListElement tmp = this;
		do {
			sb.append(tmp.value).append(", ");
			tmp = tmp.next;
		} while (tmp != null);
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	public String toConnectionString() {
		StringBuilder sb = new StringBuilder();
		RecIntListElement tmp = this;
		do {
			if (tmp.prev != null) {
				sb.append("<-");
			}
			sb.append(tmp.value);
			if (tmp.next != null) {
				sb.append("->");
			}
			tmp = tmp.next;
		} while (tmp != null);
		return sb.toString();
	}

	public long countSmallerThanTresh(int tresh) {
		if(next == null){
			if(value < tresh){
				return value;
			}
			return 0L;

		}

		if(value < tresh){
			return (long)value + next.countSmallerThanTresh(tresh);
		} else {
			return next.countSmallerThanTresh(tresh);
		}

	}

	public long countEqualsTresh(int tresh) {
		if(next == null){
			if(tresh == value){
				return value;
			} else {
				return 0L;
			}
		}

		if(tresh == value){
			return (long)value + next.countEqualsTresh(tresh);
		} else {
			return next.countEqualsTresh(tresh);
		}
	}

	public long countBiggerThanTresh(int tresh) {
		if(next == null){
			if(value > tresh){
				return value;
			} else{
				return 0L;
			}
		}

		if (tresh < value) {
			return (long) value + next.countBiggerThanTresh(tresh);
		} else{
			return next.countBiggerThanTresh(tresh);
		}

	}

	public void kingSortAscend() {
		if(next == null){
			return;
		}

		if(next.value < this.value){
			next.prev = null;
			next = next.next;
			if(next != null){
				next.prev = this;
			}
			this.kingSortAscend();
		} else {
			next.kingSortAscend();
		}

	}

	public void kingSortDescend() {
		if(next == null){
			return;
		}

		if(next.value > this.value){
			next.prev = null;
			next = next.next;
			if(next != null){
				next.prev = this;
			}
			this.kingSortDescend();
		} else {
			next.kingSortDescend();
		}

	}


	public RecIntListElement reverse() {
		RecIntListElement tmp = next;
		next = prev;
		prev = tmp;
		if(tmp != null){
			return prev.reverse();
			// you must alter the reference of head in the list, therefore you need to return the tail element.
			// Once you get to the tail element, meaning tmp == null, you return the object itself with return this.
			// That value must "traverse back" in the Stack unchanged. Therefor when calling for return reverse() you
			// not only reverse the list, but creates a Stack that awaits for the reference of the tail, creating
			// a pathway for it to traverse back to main.
		}else{
			return this;
		}
	}

	public void zip(RecIntListElement l1, RecIntListElement m2){
		if(l1 == null || m2 == null){
			return;
		}

		RecIntListElement tmp = l1.next;
		l1.next = m2;
		m2.prev = l1;

		zip(m2, tmp);

	}
}
