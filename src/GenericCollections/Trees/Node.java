package GenericCollections.Trees;


import java.util.List;

public abstract class Node<T> {

	private T value;
	private final Node<T>[] children;

	@SafeVarargs
	public Node(T value, Node<T>... nodes) {
		this.value = value;
		children = nodes;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	// you will need this method for a bonus-exercise in week 10
	//	public Node<T>[] getChildren() {
	//		return children;
	//	}

	public Node<T> getChild(int i) {

		return isValidIndex(i)? children[i] : null;
	}

	public void setChild(int i, Node<T> c) {
		if(isValidIndex(i)){
			children[i] = c;
		}
	}

	private boolean isValidIndex(int i){
		return !(i >= children.length || i < 0);
	}

	public boolean isLeaf() {
		for (Node<T> node : children) {
			if (node != null) {
				return false;
			}
		}
		return true;
	}

	public int height() {
		if(isLeaf()){
			return 1;
		} else{
			int tallest = 0;
			for(Node<T> child : children){
				int tmp = child.height();
				if(tmp > tallest){
					tallest = tmp;
				}
			}

			return tallest + 1;
		}

	}

	public int size() {

		if(isLeaf()){
			return 1;
		} else {
			int sum = 1;
			for(Node<T> child : children){
				if(child != null){
					sum += child.size();
				}
			}

			return sum;
		}
		//return children.length+1;
	}

	public abstract List<T> toList(Order order);
}
