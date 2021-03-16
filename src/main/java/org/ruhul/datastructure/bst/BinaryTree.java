package org.ruhul.datastructure.bst;

import java.util.Random;

class Node {
	Node left, right;
	int value;

	public Node(int value) {
		this(null, null, value);
	}

	public Node(Node left, Node right, int value) {
		this.left = left;
		this.right = right;
		this.value = value;
	}
}

public class BinaryTree {
	Node root;

	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());
		TreePrinter<Node> treePrinter = new TreePrinter<>(node -> String.valueOf(node.value), node -> node.left, node -> node.right);


		BinaryTree binaryTree = new BinaryTree();

		int totalNode = random.nextInt(40);
		int values[] = new int[totalNode];

		for (int i = 0; i < totalNode; i++) {
			values[i] = random.nextInt(100);
			binaryTree.insert(values[i]);
		}

		treePrinter.printTree(binaryTree.root);

		System.out.print("Pre order:\t\t");
		binaryTree.preOrder(binaryTree.root);
		System.out.print("\nIn order:\t\t");
		binaryTree.inOrder(binaryTree.root);
		System.out.print("\nPost order:\t\t");
		binaryTree.postOrder(binaryTree.root);
		System.out.println();

		System.out.println(" #### After delete ###");

		for (int x : values) {
			binaryTree.delete(x);
			treePrinter.printTree(binaryTree.root);
		}
	}

	Node insert(Node node, int value) {
		if (node == null) return new Node(value);

		if (node.value > value) node.left = insert(node.left, value);
		else node.right = insert(node.right, value);
		return node;
	}

	void insert(int value) {
		System.out.println("Performing insert operation for value: " + value);
		root = insert(root, value);
	}

	void delete(int value) {
		System.out.println("Performing delete operation for value: " + value);
		root = delete(root, value);
	}

	Node delete(Node node, int value) {
		if (node == null) return null;
		if (value < node.value) node.left = delete(node.left, value);
		else if (value > node.value) node.right = delete(node.right, value);
		else {
			if (node.left == null) return node.right;
			else if (node.right == null) return node.left;
			// Node with two child.
			// replace with smallest value from right sub tree
			node.value = minValue(node.right);
			// delete the min value
			node.right = delete(node.right, node.value);
		}
		return node;
	}

	int minValue(Node root) {
		int minv = root.value;
		while (root.left != null) {
			minv = root.left.value;
			root = root.left;
		}
		return minv;
	}

	void process(Node node) {
		System.out.print("\t" + node.value);
	}

	void preOrder(Node root) {
		if (root == null) return;
		process(root);
		preOrder(root.left);
		preOrder(root.right);
	}

	void inOrder(Node root) {
		if (root == null) return;
		inOrder(root.left);
		process(root);
		inOrder(root.right);
	}

	void postOrder(Node root) {
		if (root == null) return;
		postOrder(root.left);
		postOrder(root.right);
		process(root);
	}

}
