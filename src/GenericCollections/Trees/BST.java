package GenericCollections.Trees;

import java.util.LinkedList;
import java.util.List;

public class BST<T extends Comparable<T>> {
    private BSTNode<T> root;

    public BST(){
        root = null;
    }


    public void insert(T value){
        if(root !=  null){
            root.insert(value);
        } else {
            root = new BSTNode<>(value);
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        if(isEmpty()){
            return 0;
        }
        return root.size();
    }

    public boolean contains(T value){
        if(isEmpty()){
            return false;
        }
        return root.contains(value);
    }

    public List<T> toList(){
        if(isEmpty()){
            return new LinkedList<>();
        }
        return root.toList(Order.IN);
    }

    @Override
    public String toString() {
       if(isEmpty()){
           return "[]";
       }
        return root.toString();
    }
}
