package GenericCollections.MiniMaxTrees;

import java.util.HashSet;
import java.util.Set;

public class Forest<T> {

    protected final Set<Tree<T>> trees = new HashSet<>();

    public void addTree(Tree<T> tree) {
       trees.add(tree);
    }

    public boolean contains(T value) {

        for( Tree<T> tree : trees){
            if(tree.contains(value)){
                return true;
            }
        }
        return false;
    }
}
