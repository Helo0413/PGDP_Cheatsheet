package ListsWithoutGenerics.RecursiveDoublyLinkedList;

import java.util.Arrays;

public class RecIntList {
	private RecIntListElement head;

	public RecIntList() {
		head = null;
	}

	public void append(int value) {
		if (head == null) {
			head = new RecIntListElement(value);
		} else {
			head.append(value);
		}
	}

	public int get(int idx) {
		if (head == null) {
			System.out.println("Invalid index: list is empty!");
			return Integer.MAX_VALUE;
		}
		return head.get(idx);
	}

	public int size() {
	    /**
		 * can be rewritten as
		 * if(head==null)
		 * return 0;
		 * else
		 * return head.size();
		*/
		return head == null ? 0 : head.size();
	}

	public boolean insert(int value, int idx) {
		if (head == null) {
			if (idx == 0) {
				append(value);
				return true;
			} else {
				System.out.println("You may only insert at index 0 to a empty list!");
				return false;
			}
		}
		return head.insert(value, idx);
	}

	@Override
	public String toString() {
		if (head != null) {
			return "List: [" + head.toString() + "]";
		} else {
			return "Empty list";
		}
	}

	public String toConnectionString() {
		if (head != null) {
			return "List: [" + head.toConnectionString() + "]";
		} else {
			return "Empty list";
		}
	}

	public long[] countThresh(int threshold) {
		if(size() == 0){
			return new long[]{0, 0, 0};
		}
		long[] count = new long[3];
		count[0] = head.countSmallerThanTresh(threshold);
		count[1] = head.countEqualsTresh(threshold);
		count[2] = head.countBiggerThanTresh(threshold);
		return count;
	}

	public void kinguinSort(boolean increasing) {
		if(size() == 0){
			return;
		}

		if(increasing){
			head.kingSortAscend();
		}else {
			head.kingSortDescend();
		}
	}

	public void reverse() {
		if(head == null) {
			return;
		} else {
			head = head.reverse();
		}
	}

	public static void zip(RecIntList l1, RecIntList l2) {
		if(l1.size() == 0){
			l1.head = l2.head;
			return;
		}
		if(l2.size() == 0){
			return;
		}
		l1.head.zip(l1.head, l2.head);

	}

	public static void main(String[] args) {
		// countThresh example
		RecIntList countThreshExample = new RecIntList();
		for (int i = 1; i <= 5; i++) {
			countThreshExample.append(i);
		}
		System.out.println(Arrays.toString(countThreshExample.countThresh(-2)));

		// kinguinSort example (1)
		RecIntList kinguinSortExample = new RecIntList();
		int[] kinguinSortvalues = new int[] { 3, 2, 4, 7, 1, 6, 5, 9, 8 };
		for (int i : kinguinSortvalues) {
			//kinguinSortExample.append(i);
		}
		kinguinSortExample.kinguinSort(false); // false for example (2)
		System.out.println(kinguinSortExample);

		// reverse example
		RecIntList reverseExample = new RecIntList();
		for (int i = 1; i < 6; i++) {
			reverseExample.append(i);
		}
		reverseExample.reverse();
		System.out.println(reverseExample);

		// zip example
		RecIntList l1 = new RecIntList();
		RecIntList l2 = new RecIntList();
		for (int i = 1; i <= 5; i += 2) {
			l2.append(i);
			l1.append(i + 1);
		}
		l2.append(7);
		l2.append(8);
		System.out.println("1. " + l1);
		System.out.println("2. " + l2);
		RecIntList.zip(l1, l2);
		System.out.println(l1);
	}
}
