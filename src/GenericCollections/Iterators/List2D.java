package GenericCollections.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List2D<T> implements Iterable<T> {

    private List<List<T>> lists;

    public List2D() {
        lists = new List<>();
    }

    void insert(List<T> value) {
        lists.insert(value);
    }

    @Override
    public Iterator<T> iterator() {

        return new List2DIterator(lists);
    }

    public class List2DIterator implements Iterator<T> {

        private Iterator<List<T>> listIterator;
        private Iterator<T> elementListIterator;

        public List2DIterator(List<List<T>> lists) {

            listIterator = lists.iterator();

            while(listIterator.hasNext()){
                elementListIterator = listIterator.next().iterator();
                //since listIterator goes through a list of lists, the next() element itself also has its own iterator

                if(elementListIterator.hasNext()) {
                    break;
                }

            }

        }

        @Override
        public boolean hasNext() {
            return elementListIterator!= null && (listIterator.hasNext() || elementListIterator.hasNext());
            // if the iterator of the current list is not null (since if it is null, then you can not call hasNext)
            // AND either the iterator of the list of references has a next (think a[][] -> a[++index])
            // OR the current list iterator still has a next (meaning there are still elements to iterate through!)
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            //if there is not a next element to be evaluated, throw exception

            // if there are still elements left to iterate through in the current list, do that.
            if(elementListIterator.hasNext()){

                return elementListIterator.next();

            } else{

                // otherwise, we go through the references of the list (think y-Axis in a 2D array) and
                // "change the coordinates" of the elementListIterator, meaning which of the lists it iterates through,
                // for as long as said iterator does not have anything to iterate through.

                while (listIterator.hasNext() && !elementListIterator.hasNext()){

                    elementListIterator = listIterator.next().iterator();

                }

                // And then iterates through this next iterable, the return value is taken care of by the list iterator.
                // -> The iterator travels through the list without altering its structure!
                return elementListIterator.next();
            }
        }
        /*
         public T next() {
            T current = elementListIterator.next();
            while (!elementListIterator.hasNext() && listIterator.hasNext()) {
                elementListIterator = listIterator.next().iterator();
            }
            return current;
        } */
    }
}
