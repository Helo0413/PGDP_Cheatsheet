package ListsWithoutGenerics;

public class DoublyLinkedList {

    private DoublyLinkedListElement head;
    private DoublyLinkedListElement tail;

    private int size = 0;

    public DoublyLinkedList(){
        tail = null;
        head = null;
    }
    public void append(int info){
        if(size==0){
            head = new DoublyLinkedListElement(info);
            tail = head;
        } else {
            tail.next = new DoublyLinkedListElement(info);
            tail = tail.next;
            if(size == 0){
                head = tail;
            }
            size++;
        }
    }

    public int get(int index){
        if(index < 0 || index >= size){
            System.out.println("This is not a valid index");
            return -1;
        }

        DoublyLinkedListElement tmp = head;

        if(index < size/2){

            while(index>0){
                tmp = tmp.next;
                index--;
            }
        }else {
            tmp = tail;
            while(size-1>index){
                tail = tail.prev;
                index++;
            }
        }
            return tmp.getInfo();


    }

    private boolean isEmpty(){
        return size == 0;
    }

    public void remove(int index){
        if(index < 0 || index >= size){
            System.out.println("This is not a valid index");
            return;
        }
        if(index == 0){
            if(size == 1){
                tail = null;
                head = null;
            }else{
                head = head.next;
                head.prev = null;
            }
            size--;
            return;
        }
        if(index == size-1){
            tail = tail.prev;
        }
        DoublyLinkedListElement tmp = head;
        if(index <= size/2){
            while(index>1){
                tmp = tmp.next;
                index--;
            }
        }else {
            tmp = tail;
            while(index<size-1){
                tmp = tmp.prev;
                index++;
            }
        }

        tmp.next = tmp.next.next;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder s  = new StringBuilder();
        DoublyLinkedListElement tmp = head;
        while(tmp != null){
            s.append(tmp.getInfo());
            tmp = tmp.next;
            if(tmp != null) {
                s.append(",");
            }
        }

        return s.toString();
    }

    public DoublyLinkedListElement getHead() {
        return head;
    }

    public DoublyLinkedListElement getTail() {
        return tail;
    }

    public void appendList(DoublyLinkedList l){
       if(size != 0) {
           //if this list is not e,pty, then the tail should be the other list's head.
           // in the case l is empty, the tail.next will still point to null
           tail.next = l.head;
       } else {
           //if this list is empty, simply use the other list BUT l may still be empty, so hold off defining tail
           //meaning head and tail are null, so we can not call head.prev!
           head = l.head;
       }

       if(l.size!= 0){
           //if the l is not empty, let the prev from head be this tail. If this list is empty,
           // head.prev will be assigned a null.
           l.head.prev = tail;
           tail = l.tail;
           size += l.size;
       }
    }

    public boolean isEqual(DoublyLinkedList list){
        if(list == null || list.size() != size){
            return false;
        }

        DoublyLinkedListElement tmpHere = head;
        DoublyLinkedListElement tmpThere = list.getHead();
        for (int i = 0; i < size; i++) {
            if(!(tmpHere.equals(tmpThere))){
                return false;
            }
            tmpThere = tmpThere.next;
            tmpHere = tmpHere.next;
        }

        return true;
    }

    public int sum(){
        DoublyLinkedListElement tmp = head;
        int sum = 0;
        while(tmp != null){
            sum += tmp.getInfo();
            tmp = tmp.next;
        }
        return sum;
    }

    public DoublyLinkedList copy(){
        DoublyLinkedList list = new DoublyLinkedList();
        DoublyLinkedListElement temp = head;
       while(temp != null){
           list.append(temp.getInfo());
           temp = temp.next;
       }
        return list;
    }

    public int size() {
        return size;
    }




}
