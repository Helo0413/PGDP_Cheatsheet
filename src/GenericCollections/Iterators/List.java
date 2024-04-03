package GenericCollections.Iterators;

public class List<T> implements Iterable<T> {

    private Element first;
    private Element last;

    void insert(T value) {
        if (first == null) {
            first = new Element(value);
            last = first;
        } else {
            var n = new Element(value);
            last.setNext(n);
            last = n;
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {

        return new Iterator(first);

    }

    public class Element {
        private T value;
        private Element next;

        Element(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    public class Iterator implements java.util.Iterator<T> {

        private Element element;

        public void setElement(Element element) {
            this.element = element;
        }

        public Element getElement() {
            return element;
        }

        public Iterator(Element e) {
            this.element = e;

        }

        @Override
        public boolean hasNext() {
            return element!=null;
        }

        @Override
        public T next() {
            if(!hasNext()) return null;
            T value = element.value;
            element = element.next;
            return value;

        }
    }

}