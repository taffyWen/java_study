package arithmetic.BinaryTree;

/**
 * 二叉查找树
 */
public class BinarySearchTree {

    public Node tree;

    /**
     * 查找
     * @param data
     * @return
     */
    public Node find(int data){

        Node p = tree;
        while (p != null){
            if (data < p.data){
                p = p.left;
            }else if (data > p.data){
                p = p.right;
            }else {
                return p;
            }
        }
        return null;
    }

    /**
     * 插入
     * @param data
     */
    public boolean insert(int data){
        if (tree == null){
            tree = new Node(data);
            return true;
        }
        Node p = tree;
        while (p != null){
            if (data < p.data){
                if (p.left == null){
                    p.left = new Node(data);
                    return true;
                }
                p = p.left; //部满足条件，递归
            }else {
                if (p.right == null){
                    p.right = new Node(data);
                    return true;
                }
                p = p.right;
            }
        }
        return false;
    }

    public boolean delete(int data){
        Node p = tree;//p指向要删除的节点，初始化指向根节点
        Node pp = null;//pp记录的是p的父节点
        //查找出要删除的节点
        while (p != null && p.data != data){
            pp = p; //递归p的父节点
            if (data > p.data){
                p = p.right;
            }else {
                p = p.left;
            }
        }
        //以下是p = null 和 p.data == data 情况
        if (p == null) return false;//没有找到
        //删除的节点有两个子节点 ,查找右子树中最小的节点
        if (p.left != null && p.right != null){
            Node minP = p.right;
            Node minPP = p; //minPP表示minP的父节点
            while (minP.left != null){
                minPP = minP;
                minP = minP.left;//递归while条件
            }
            p.data = minP.data;//将minP的数据替换p中data，此时minP就是右子树中最小的节点
            //删除minP
            p = minP; //疑问：那原来p.right的关系怎么维护的？--》原来p的位置的数已经改过了，此时只是去除掉原来minP的位置。
            pp = minPP; //此两步，只是把minP，minPP对以下方法可见，才重新赋值。原来的p、pp已经不再是tree中的查找到的节点，而成了上面的minP
        }
        //删除节点是叶节点或者仅有一个子节点
        Node child ;//p子节点,为了把p的子节点，直接给到minPP（minP的父节点）的相应位置
        if (p.left != null){
            child = p.left;
        }else if (p.right != null){
            child = p.right;
        }else {
            child = null;
        }
        if (pp == null){
            tree = child; //删除的是根节点
        }else if (pp.left == p){
            pp.left = child;
        }else {
            pp.right = child;
        }
        return true;
    }

    /**
     * Node静态内部类
     */
    public static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        /**
         * 中序排列
         */
        public void inOrder(){
            if (this.left != null){
                this.left.inOrder();
            }
            System.out.print(this.data + " ");
            if (this.right != null){
                this.right.inOrder();
            }
        }
    }

}
