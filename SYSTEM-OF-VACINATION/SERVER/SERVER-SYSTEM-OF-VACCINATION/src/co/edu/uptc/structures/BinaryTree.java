package co.edu.uptc.structures;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;
    
    public BinaryTree() {
        root = null;
    }
    
    public boolean add(T value) {
        if (isEmpty()) {
            root = new Node<>(value);
            size++;
            return true;
        }
        Node<T> current = root;
        Node<T> parent = null;
        while (current != null) {
            parent = current;
            int comparison = value.compareTo(current.getValue());
            if (comparison < 0) {
                current = current.getLeft();
            } else if (comparison > 0) {
                current = current.getRight();
            } else return false;
        }

        int comparison = value.compareTo(parent.getValue());
        if (comparison < 0) parent.setLeft(new Node<T>(value));
        else parent.setRight(new Node<T>(value));
        size++;
        return true;
    }
    
    public boolean contains(T value) {
        if (isEmpty()) return false;
        Node<T> currrent = root;
        while (currrent != null) {
            int comparison = value.compareTo(currrent.getValue());
            if (comparison > 0) {
                currrent = currrent.getRight();
            } else if (comparison < 0) {
                currrent = currrent.getLeft();
            } else {
                return true;
            }
        }
        return false;
    }

    public T getWord (T value) {
        if (isEmpty()) return null;
        Node<T> currrent = root;
        while (currrent != null) {
            int comparison = value.compareTo(currrent.getValue());
            if (comparison > 0) {
                currrent = currrent.getRight();
            } else if (comparison < 0) {
                currrent = currrent.getLeft();
            } else {
                return currrent.getValue();
            }
        }
        return null;
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> resultList = new ArrayList<>();
        resultList.clear();
        inOrder(root, resultList);
        return resultList;
    } 

    private void inOrder(Node<T> node, ArrayList<T> resultList) {
        if (node != null) {
            inOrder(node.getLeft(), resultList);
            resultList.add(node.getValue());
            inOrder(node.getRight(), resultList);
        }
    }

    public ArrayList<T> postOrder() {
        ArrayList<T> resultList = new ArrayList<>();
        resultList.clear();
        postOrder(root, resultList);
        return resultList;
    } 

    private ArrayList<T> postOrder(Node<T> node, ArrayList<T> resultList) {
        if (node != null) {
            resultList.add(node.getValue());
            postOrder(node.getLeft(), resultList);
            postOrder(node.getRight(), resultList);
        } return resultList;
    }

    public ArrayList<T> preOrder() {
        ArrayList<T> resultList = new ArrayList<>();
        resultList.clear();
        preOrder(root, resultList);
        return resultList;
    } 

    private void preOrder(Node<T> node, ArrayList<T> resultList) {
        if (node != null) {
            preOrder(node.getLeft(), resultList);
            preOrder(node.getRight(), resultList);
            resultList.add(node.getValue());
        }
    }

    public boolean remove(T value) {
        if (isEmpty()) return false;
        if (!contains(value)) return false;
        root = removeRecursive(root, value);
        size--;
        return true;
    }

    private Node<T> removeRecursive(Node<T> node, T value) {
        if (node == null) return null;
        int comparison = value.compareTo(node.getValue());
        if (comparison < 0) {
            node.setLeft(removeRecursive(node.getLeft(), value));
        } else if (comparison > 0) {
            node.setRight(removeRecursive(node.getRight(), value));
        } else {
            // Caso 1: sin hijos
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            // Caso 2: un hijo
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Caso 3: dos hijos
            Node<T> successor = findMin(node.getRight());
            node.setValue(successor.getValue());
            node.setRight(removeRecursive(node.getRight(), successor.getValue()));
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    public String getTreeString() {
        return getTreeString(root, "", true);
    }

    private String getTreeString(Node<T> node, String prefix, boolean isLeft) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getTreeString(node.getRight(), prefix + (isLeft ? "│   " : "    "), false));
        
        sb.append(prefix);
        sb.append(isLeft ? "└── " : "┌── ");
        sb.append(node.getValue());
        sb.append("\n");

        sb.append(getTreeString(node.getLeft(), prefix + (isLeft ? "    " : "│   "), true));

        return sb.toString();
    }

    public int getSize() {
        return size;
    }
}