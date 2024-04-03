package GenericCollections.MiniMaxTrees;

public class BSTree<T extends Comparable<T>> extends BinaryTree<T> {

	public void insert(T value) {
		if (isEmpty()) {
			root = new BSTNode<>(value);
		} else {
			((BSTNode<T>) root).insert(value);
		}
	}

	protected class BSTNode<B extends T> extends BinaryNode<B> {

		protected BSTNode(B value) {
			super(value);
		}

		public <G extends T> Node<G> createNode(G value) {
			return new BSTNode<G>(value);
		}

		public void insert(B value) {
			if (value.compareTo(getValue()) < 0) {
				if (getLeftChild() != null) {
					((BSTNode<B>) getLeftChild()).insert(value);
				} else {
					setLeftChild((BinaryTree<T>.BinaryNode<B>) createNode(value));
				}
			} else {
				if (getRightChild() != null) {
					((BSTNode<B>) getRightChild()).insert(value);
				} else {
					setRightChild((BinaryTree<T>.BinaryNode<B>) createNode(value));
				}
			}
		}

		@Override
		public boolean contains(B value) {
			if (value == null) {
				return false;
			}

			if (value.compareTo(getValue()) == 0) {
				return true;
			} else if (value.compareTo(getValue()) < 0) {
				return getLeftChild() != null && getLeftChild().contains(value);
			} else {
				return getRightChild() != null && getRightChild().contains(value);
			}
		}
	}
}
