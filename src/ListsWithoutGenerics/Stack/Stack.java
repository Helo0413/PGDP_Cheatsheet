package ListsWithoutGenerics.Stack;


public class Stack {
    // REMEMBER! Stacks follow the Last-In-First-Out principle. That means every time you push a new
    // element to the list, it becomes the FIRST element of the stack! When you pop an element, you are returning
    // the FIRST, top element of the stack. Think book piles! The first book to ever be pushed it the last in the stack!

    private StackElement first;
    private StackElement last;
    private int size;

    public void push(int number) {
        StackElement tmp = new StackElement(number);
       if(first == null) {
           first = tmp;
           last = first;
       }else {
           tmp.setNext(first);
           first = tmp;

       }
        size++;
    }

    public int pop() {
        if(first == null){
            return Integer.MIN_VALUE;
        }

        int popValue = first.getValue();
        if(last == first){
            last = null;
        }
        first = first.getNext();
        size--;
        return popValue;
    }

    public int[] toArray() {
        int[] queueAsArray = new int[size];
        StackElement current = first;
        for(int i = 0; current != null; current = current.getNext(), i++) {
            queueAsArray[i] = current.getValue();
        }
        return queueAsArray;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
