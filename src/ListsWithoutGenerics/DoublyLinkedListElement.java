package ListsWithoutGenerics;

public class DoublyLinkedListElement {

    private int info;
    public DoublyLinkedListElement next;
    public DoublyLinkedListElement prev;

    public DoublyLinkedListElement(int startInfo){
        info = startInfo;

        next = null;
        prev = null;

    }

    public boolean equals(DoublyLinkedListElement element){
        return this.info == element.info;
    }

    public int getInfo() {
        return info;
    }
}
