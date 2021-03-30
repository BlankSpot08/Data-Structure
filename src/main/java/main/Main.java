package main;

import stack.Stack;
import tree.binarysearchtree.BinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public void start() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        binarySearchTree.add(80);
        binarySearchTree.add(21);
        binarySearchTree.add(62);
        binarySearchTree.add(12);
        binarySearchTree.add(73);
        binarySearchTree.add(11);
        binarySearchTree.add(92);
        binarySearchTree.add(85);

        binarySearchTree.inorder();
        binarySearchTree.remove(21);
        binarySearchTree.inorder();
    }
}
