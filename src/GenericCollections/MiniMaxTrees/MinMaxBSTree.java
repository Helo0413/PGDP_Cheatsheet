package GenericCollections.MiniMaxTrees;

import java.util.Arrays;

public class MinMaxBSTree<T extends Comparable<T>> extends BSTree<T> {

	@Override
	public void insert(T value) {
		if(isEmpty()){
			root = new MinMaxBSTNode<>(value);
		}else{
			((MinMaxBSTNode<T>)root).insert(value);
		}
	}

	protected class MinMaxBSTNode<B extends T> extends BSTNode<B> {

		protected B min;
		protected B max;

		protected MinMaxBSTNode(B value) {
			super(value);
			min = value;
			max = value;
		}

		@Override
		public <G extends T> Node<G> createNode(G value) {
			return new MinMaxBSTNode<>(value);
		}

		public void insert(B value) {
			if(value == null){
				return;
			}

			super.insert(value);
			updateMinMax(value);
		}

		private void updateMinMax(B value) {
			if (value.compareTo(min) < 0) {
				min = value;
			}
			if (max.compareTo(value) < 0) {
				max = value;
			}
		}

		protected boolean canContain(B value) {
			//yes, yes, now I see it. Value has to be BETWEEN min and max, so both if-statements must be true!!!
			return value != null && value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
		}

		@Override
		public boolean contains(B value) {
			if(canContain(value)){
				return super.contains(value);
			}else {
				return false;
			}
		}

		@Override
		public String toString() {
			return "{value = " + getValue() + ", min = " + min + ", max = " + max + " nodes = "
					+ Arrays.deepToString(getNodes()) + "}";
		}

		@Override
		public String toGraphviz() {
			StringBuilder sb = new StringBuilder();
			sb.append(getValue()).append(" [label=\"").append(getValue()).append(" : [").append(min).append(", ").append(max).append("]\"];\n");
			sb.append("subgraph {\n");
			childToGraphviz(sb, "left", getLeftChild());
			childToGraphviz(sb, "right", getRightChild());
			sb.append("}\n");
			return sb.toString();
		}
	}
}
