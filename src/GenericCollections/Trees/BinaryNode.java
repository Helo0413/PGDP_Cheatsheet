package GenericCollections.Trees;

import java.util.LinkedList;
import java.util.List;

public class BinaryNode<T> extends Node<T> {

	public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
		super(value, left, right);
	}

	public BinaryNode(T value) {
		super(value, null, null);
	}

	public BinaryNode<T> getLeft() {
		return (BinaryNode<T>) getChild(0);
	}

	public BinaryNode<T> getRight() {
		return (BinaryNode<T>) getChild(1);
	}

	public void setLeft(BinaryNode<T> left) {
		setChild(0, left);
	}

	public void setRight(BinaryNode<T> right) {
		setChild(1, right);
	}

	public String toString(Order order) {
		if (isLeaf()) {
			return "[" + getValue().toString() + "]";
		}

		String strLeft = "-";
		String strRight = "-";

		if(getLeft() != null){
			strLeft = getLeft().toString(order);
		}

		if(getRight() != null){
			strRight = getRight().toString(order);
		}

		switch (order){
			case IN -> {
				return "[" + strLeft + ", " + getValue().toString() + ", " + strRight + "]";
			}
			case POST -> {
				return "[" + strLeft + ", " + strRight + ", " + getValue().toString() + "]";
			}
			case PRE -> {
				return "[" + getValue().toString() + ", " + strLeft + ", " + strRight + "]";
			}
			default -> {
				return "BANANAS ARE CRAZY BUT AVOCADOS ARE BETTER";
			}
		}

	}

	@Override
	public String toString() {
		return toString(Order.IN);
	}

	@Override
	public List<T> toList(Order order) {
		if(size() == 0) {
			return new LinkedList<>();
		}

		LinkedList<T> list = new LinkedList<>();
		switch (order){
			case PRE -> {
				//list = new LinkedList<>();
				list.add(getValue());
				if(getLeft() != null){
					list.addAll(getLeft().toList(order));
				}
				if(getRight() != null){
					list.addAll(getRight().toList(order));
				}
			}
			case POST -> {
				if(getLeft() != null){
					list.addAll(getLeft().toList(order));
				}
				if(getRight() != null){
					list.addAll(getRight().toList(order));
				}
				list.add(getValue());
			}
			case IN -> {
				if(getLeft() != null){
					list.addAll(getLeft().toList(order));
				}
				list.add(getValue());
				if(getRight()!= null){
					list.addAll(getRight().toList(order));
				}
			}default -> System.out.println("SOMETHING WENT INCREDIBLY BONKERS");
		}

		return list;
	}

	public static void main(String[] args) {

	}
}
