package GenericCollections.MiniMaxTrees;

public class BinaryTree<T> extends Tree<T> {

	protected class BinaryNode<B extends T> extends Node<B> {

		protected BinaryNode(B value) {
			super(value, null, null);
		}

		protected BinaryNode(B value, BinaryNode<B> left, BinaryNode<B> right) {
			super(value, left, right);
		}

		public <G extends T> Node<G> createNode(G value) {
			return new BinaryNode<>(value);
		}

		public <G extends T> Node<G> createNode(G value, Node<B> left, Node<B> right) {
			return new BinaryNode<G>(value);
		}

		public BinaryNode<B> getLeftChild() {
			return (BinaryTree<T>.BinaryNode<B>) getNode(0);
		}

		public void setLeftChild(BinaryNode<B> left) {
			setNode(0, left);
		}

		public BinaryNode<B> getRightChild() {
			return (BinaryTree<T>.BinaryNode<B>) getNode(1);
		}

		public void setRightChild(BinaryNode<B> right) {
			setNode(1, right);
		}

		@Override
		public String toGraphviz() {
			StringBuilder sb = new StringBuilder();
			sb.append("subgraph {\n");
			childToGraphviz(sb, "left", getLeftChild());
			childToGraphviz(sb, "right", getRightChild());
			sb.append("}\n");
			return sb.toString();
		}

		protected void childToGraphviz(StringBuilder sb, String edgeLabel, BinaryNode<B> child) {
			if (child != null) {
				sb.append(getValue()).append(" -> ").append(child.getValue()).append(" [label=\"").append(edgeLabel).append("\"];\n");
				sb.append(child.toGraphviz());
			}
		}
	}
}
