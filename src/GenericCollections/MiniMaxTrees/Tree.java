package GenericCollections.MiniMaxTrees;

import java.util.Arrays;

public class Tree<T> {

	protected Node<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		if(isEmpty()){
			return 0;
		}
		return root.size();
	}

	public int height() {
		if(isEmpty()){
			return 0;
		}
		return root.height();
	}

	public boolean contains(T value) {
		return value != null && !isEmpty() && root.contains(value);
	}

	@Override
	public String toString() {
		return isEmpty() ? "{}" : root.toString();
	}

	public String toGraphviz() {
		return "digraph {\n" + (!isEmpty() ? root.toGraphviz() : "") + "}";
	}

	protected class Node<B extends T> {

		private final B value;
		private final Node<B>[] nodes;

		@SafeVarargs
		public Node(B value, Node<B>... nodes) {
			this.value = value;
			this.nodes = nodes;
		}

		public <G extends T> Node<G> createNode(G value, Node<G>... nodes) {
			return new Node<>(value, nodes);
		}

		public B getValue() {
			return value;
		}

		public Node<B>[] getNodes() {
			return nodes;
		}

		public Node<B> getNode(int i) {
			return nodes[i];
		}

		public void setNode(int i, Node<B> node) {
			nodes[i] = node;
		}


		public int size() {
			if(isLeaf()){
				return 1;
			} else {
				int sum = 1;
				for(Node<B> child : nodes){
					if(child != null){
						sum += child.size();
					}
				}

				return sum;
			}
		}

		private boolean isLeaf() {
			for (Node<B> node : nodes) {
				if (node != null) {
					return false;
				}
			}
			return true;
		}

		public int height() {
			if(isLeaf()){
				return 1;
			} else {
				int tallest = 0;
				for(Node<B> child : nodes){
					int tmp = child.height();
					if(tmp > tallest){
						tallest = tmp;
					}
				}

				return tallest + 1;
			}

		}

		public boolean contains(B value) {
			if (value == null) {
				return false;
			}
			if (value == getValue()) {
				return true;
			}
			for(var node : nodes) {
				if(node.contains(value)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "{value = " + value + ", nodes = " + Arrays.deepToString(nodes) + "}";
		}

		public String toGraphviz() {
			StringBuilder sb = new StringBuilder();
			sb.append("subgraph {\n");
			for(int i = 0; i < nodes.length; i++) {
				if (nodes[i] != null) {
					sb.append(value).append(" -> ").append(nodes[i].value).append(" [label=\"").append(i).append("\"];\n");
					sb.append(nodes[i].toGraphviz());
				}
			}
			sb.append("}\n");
			return sb.toString();
		}
	}
}
