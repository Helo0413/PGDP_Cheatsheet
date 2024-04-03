package GenericCollections.Trees;

public class BSTNode<T extends Comparable<T>> extends BinaryNode<T> {
    public BSTNode(T value, BSTNode<T> left, BSTNode<T> right) {
        super(value, left, right);
    }

    public BSTNode(T value){
        super(value, null, null);
    }

    public boolean contains(T value){
        if(value.compareTo(getValue()) == 0){
            return true;
        }

        if(value.compareTo(getValue()) < 0 && getLeft() != null){
            return ((BSTNode<T>)getLeft()).contains(value);
        }

        if(value.compareTo(getValue()) > 0 && getRight() != null){
            return ((BSTNode<T>)getRight()).contains(value);
        }
        return false;
    }

    public void insert(T value){
        if(value.compareTo(getValue()) == 0){
            return;
        }

        if(value.compareTo(getValue()) < 0){
            if(getLeft()!= null){
                ((BSTNode<T>)getLeft()).insert(value);
            } else {
                setLeft(new BSTNode<>(value));
            }

        } else {
            if(getRight()!= null){
                ((BSTNode<T>)getRight()).insert(value);
            } else {
                setRight(new BSTNode<>(value));
            }

        }

    }



}
