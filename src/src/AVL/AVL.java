package src.AVL;
import java.util.Comparator;

public class AVL<E extends Comparable<E>> implements AVLTDA<E> {
	private final Comparator<E> comp;
    private NodoAVL<E> root;

    public AVL(Comparator<E> comp) {
        this.comp = comp;
        this.root = null;
    }

    private int getHeight(NodoAVL<E> node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(NodoAVL<E> node) {
        return (node == null) ? 0 : getHeight(node.right) - getHeight(node.left);
    }

    private NodoAVL<E> rightRotate(NodoAVL<E> y) {
        NodoAVL<E> x = y.left;
        NodoAVL<E> t2 = x.right;
        x.right = y;
        y.left = t2;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    private NodoAVL<E> leftRotate(NodoAVL<E> x) {
        NodoAVL<E> y = x.right;
        NodoAVL<E> t2 = y.left;
        y.left = x;
        x.right = t2;
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        return y;
    }

    private NodoAVL<E> insert(NodoAVL<E> node, E data) {
        if (node == null) {
            return new NodoAVL<>(data);
        }
        int cmp = comp.compare(data, node.data);
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } else if (cmp > 0) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return rebalance(node, data);
    }

    private NodoAVL<E> delete(NodoAVL<E> node, E data) {
        if (node == null) {
            return null;
        }
        int cmp = comp.compare(data, node.data);
        if (cmp < 0) {
            node.left = delete(node.left, data);
        } 
        else 
        	if (cmp > 0) {
        		node.right = delete(node.right, data);
        	} 
        	else {
	            if (node.left == null) {
	                return node.right;
	            }
	            if (node.right == null) {
	                return node.left;
	            }
	            NodoAVL<E> min = minValueNode(node.right);
	            node.data = min.data;
	            node.right = delete(node.right, min.data);
        	}
        node.height = 1 + Math.max(getHeight(node.right), getHeight(node.left));
        return rebalance(node, data);
    }

    private NodoAVL<E> rebalance(NodoAVL<E> node, E data) {
        int balance = getBalance(node);
        if (balance > 1 && comp.compare(data, node.right.data) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && comp.compare(data, node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        if (balance < -1 && comp.compare(data, node.left.data) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && comp.compare(data, node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        return node;
    }

    public void insert(E data) {
        root = insert(root, data);
    }

    public void delete(E data) {
        root = delete(root, data);
    }

    public boolean exists(E value) {
        return exists(root, value);
    }

    private boolean exists(NodoAVL<E> node, E value) {
        if (node == null) return false;
        int cmp = comp.compare(value, node.data);
        if (cmp == 0) return true;
        return (cmp < 0) ? exists(node.left, value) : exists(node.right, value);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(NodoAVL<E> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    private NodoAVL<E> minValueNode(NodoAVL<E> node) {
        NodoAVL<E> current = node;
        while (current.left != null) current = current.left;
        return current;
    }
    
    public String toString() {
    	String salida=inOrderTraversalString(root);
    	return salida.substring(0,salida.length()-2);
    }
    private String inOrderTraversalString(NodoAVL<E> node) {
        if (node != null) {
            return "( "+inOrderTraversalString(node.left)+node.data + " "+inOrderTraversalString(node.right) + " )";
        }
        else return "";
    }
}