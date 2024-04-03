package ListsWithoutGenerics.Queues;

public class Queue {

    //REMEMBER! First-In-Last-Out! When u push an element you are adding it to the BACK of the queue
    // and so setting a new LAST. When you are popping an element, you are returning the value
    // of the FIRST person in queue and hence defining a new FIRST!
    private QueueElement first;
    private QueueElement last;
    private int size;

    public void push(int number) {
        if(first == null) {
            first = new QueueElement(number);
            last = first;
        }else{
           last.setNext(new QueueElement(number));
           last = last.getNext();
        }

        size++;
    }

    public int pop() {
        if(first == null){
            return Integer.MIN_VALUE;
        }

        int value = first.getValue();
        first = first.getNext();
        size--;
        return value;
    }

    public int[] toArray() {
        int[] queueAsArray = new int[size];
        QueueElement current = first;
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
