package GenericCollections.Iterators;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Tree<T extends Comparable<T>> implements Iterable<T> {
    private Element root;

    public void insert(T value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Element(value);
        }
        else {
            root.insert(value);
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {

        return new Iterator(root);
    }

    public class Element {
        private T value;
        private Element left;
        private Element right;

        public Element(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element getLeft() {
            return left;
        }

        public void setLeft(Element left) {
            this.left = left;
        }

        public Element getRight() {
            return right;
        }

        public void insert(T newValue) {
            if (newValue.compareTo(this.value) <= 0) {
                if (left == null) {
                    left = new Element(newValue);
                }
                else {
                    left.insert(newValue);
                }
            }
            else if (newValue.compareTo(this.value) > 0) {
                if (right == null) {
                    right = new Element(newValue);
                }
                else {
                    right.insert(newValue);
                }
            }
        }

        public void setRight(Element right) {
            this.right = right;
        }
    }

    public class Iterator implements java.util.Iterator<T> {
        private Element element;
        private Iterator left;
        private Iterator right;

        private boolean yieldedOwnValue = false;

        public Iterator(Element e) {
            element = e;
            if(e != null){
                left = new Iterator(element.left);
                right = new Iterator(element.right);
            }
        }

        @Override
        public boolean hasNext() {
            if(element == null){
                return false;
            }

            // The current element has a next if either its left or right node have a next (and/or have to yield their
            // own values) OR if the iterator has not yet yielded its OWN element value;
            return left.hasNext() || right.hasNext() || !yieldedOwnValue;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();

            if(left.hasNext()){
                //if this element has a left element, you call the iterator for that branch, causing you to traverse
                // all the way down to the left most node before next statement is called
                // -> REMEMBER! The calls build up in the stack, so once that value is returned, it goes "up the ladder"
                // creating a "pathway" in the graph (think prev/parent node connection). Next time calling next(),
                // the left most node will already have yielded its own value, causing this step to be skipped
                return left.next();

            } else if(!yieldedOwnValue){
                // if a node has no left branch, then it should return its own value up the ladder. However, one node
                // should not return its own value more than once, so we add a yieldOwnValue boolean to the element
                // this iterator belongs to and set it to false after yielding this element to its parent node all the
                // way up to the root node.
                // REMEMBER -> We have one iterator per node, and they are communicating with one another!
                yieldedOwnValue = true;
                return element.value;

            } else {
                // lastly, we do the same thing for our right node. At the very end, the last next() calls
                // the right-most node, which will yield its own value in the second statement.
                // -> These if statements will no longer be called, since !hasNext()
                return right.next();
            }
        }
    }
}