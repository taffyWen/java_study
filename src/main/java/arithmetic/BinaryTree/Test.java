package arithmetic.BinaryTree;

public class Test {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        //int[] array = new int[]{33,16,13,18,50,34,58,15,17,25,51,66,19,27,55};
        int[] array = {11,12,27,81,90,91,94,33,77,9};
        for (int i = 0; i < array.length; i++) {
            binarySearchTree.insert(array[i]);
        }
        binarySearchTree.tree.inOrder();
        //binarySearchTree.delete(13);
        binarySearchTree.delete(18);
        binarySearchTree.delete(55);
        System.out.println();
        binarySearchTree.tree.inOrder();
    }
}
