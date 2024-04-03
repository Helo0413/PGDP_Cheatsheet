package ListsWithoutGenerics.InsertSortedList;

public class List {

    //DO NOT FORGET TO CHANGE LENGTH; HEAD AND TAIL IN EDGE CASES!
    private Element head;
    private Element tail;
    private int size;

    List() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertIntoSorted(int value) {
        Element tmp = head;
        int index = 0;
        while(tmp != null && tmp.value < value){
            tmp = tmp.next;
            index++;
        }
        add(index, value);

    }



    public static void main(String[] args) {
        List l = new List();
        l.add(36);
        l.add(38);
        l.add(40);
        l.insertIntoSorted(7);
        System.out.println(l.head.value);
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
        return head == null; // alternatives: size() == 0, or tail == null
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
        if (head == null) {
            head = new Element(element);
            tail = head;
        } else {
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

        if (head == null) {
            head = new Element(element);
            tail = head;
        } else if (index == 0) {
            head = new Element(element, head);
        } else if (index == size) {
            tail.next = new Element(element);
            tail = tail.next;
        } else {
            Element current = head;
            int currentIndex = 0;
            while (currentIndex < index - 1) {
                current = current.next;
                currentIndex++;
            }
            current.next = new Element(element, current.next);
        }
        size++;
        return true;
    }

    /*
     * returns the value of the element at the specified index returns default value
     * (minimum value of an integer) iff. such an element does not exist.
     */
    public int get(int index) {
        if (index < 0 || size <= index) {
            return Integer.MIN_VALUE;
        }

        Element current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    /*
     * removes the element at the specified index
     */
    public void remove(int index) {
        if (index < 0 || size <= index) {
            return;
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
        } else {
            Element current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (index == size - 1) {
                tail = current;
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
            this.next = null;
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