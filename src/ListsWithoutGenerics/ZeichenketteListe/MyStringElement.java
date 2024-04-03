package ListsWithoutGenerics.ZeichenketteListe;

public class MyStringElement {
    private char[] data;
    private MyStringElement next;

    public MyStringElement(char[] data) {
        this.data = data;
    }

    public char[] getData() {
        return data;
    }

    public MyStringElement getNext() {
        return next;
    }

    public void setNext(MyStringElement next) {
        this.next = next;
    }

    public void concat(char[] data) {
        if(next == null){
            next = new MyStringElement(data);
        } else {
            next.concat(data);
        }
    }
}
