package GenericCollections.MiniMaxTrees;

public class Main {

	public static void main(String... args) {
		// Here you can test your code. As long as your code compiles, this method will be ignored by the Artemis tests.
		MinMaxBSTree<Integer> tree = new MinMaxBSTree<>();
		tree.insert(1);
		tree.insert(2);
		tree.insert(4);
		tree.insert(-1);
		System.out.println(tree.contains(5));
	}
}
