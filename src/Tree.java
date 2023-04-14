
public class Tree {

    class TreeNode {
        public MilliPark data;
        public TreeNode leftChild;
        public TreeNode rightChild;

        public void disPlayNode() {
            System.out.println(data);
        }
    }

    private TreeNode root;

    public Tree() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }


    public void insert(MilliPark milliPark) {
        TreeNode newNode = new TreeNode();
        newNode.data = milliPark;

        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (Main.sortStringsWithTurkceKarakterDestegi(Main.trCollator, milliPark.parkAdi, current.data.parkAdi) < 0) { //park < current ise
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    /*

    1.B

     */

    public int maxDepth(TreeNode root) {// ağacın derinliğini bulduran metod
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.leftChild);
        int rightDepth = maxDepth(root.rightChild);

        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        } else {
            return rightDepth + 1;
        }
    }

    public void inOrder(TreeNode localroot) { // ağacı inOrder dolaşma metodu
        if (localroot != null) {
            inOrder(localroot.leftChild);
            localroot.disPlayNode();
            inOrder(localroot.rightChild);
        }
    }


    public int totalNodes(TreeNode root) {// toplam düğüm sayısını bulduran metod
        if (root == null)
            return 0;

        int l = totalNodes(root.leftChild);
        int r = totalNodes(root.rightChild);

        return 1 + l + r;
    }

    // Week 8 Binary Trees Sayfa 13 kaynaklı yöntem
    // Ek olarak, https://stackoverflow.com/questions/9837891/balanced-binary-trees-depth

    public void countBalancedDepth(int totalNodeCount) { // Ağaç dengeli olsaydı derinlik kaç olurdu metodu
        System.out.println("Ağaç dengeli olsaydı maksimum " + (int) (Math.floor((Math.log(totalNodeCount)) / (Math.log(2)))) + ". düzeyi olurdu.");
    }

    /*

    1.C

     */
    public void searchNode(TreeNode temp, String value) {// ilk üç harfi verilen milli parkı yazdırma
        if (temp != null) {
            String str = temp.data.parkAdi.substring(0, 3);
            if (str.equals(value)) {
                System.out.println("Parkın bulunduğu il adı: " + temp.data.ilAdi);
                return;
            }
            searchNode(temp.leftChild, value);

            searchNode(temp.rightChild, value);

        }
    }

    /*

    1.D

     */
    public void addTreeToStringTreeInOrder(TreeNode localroot, StringTree stringTree) {// Ağacı gezerken cümleleri kelimelere ayırıp stringTree ağacına ekleyen metod
        if (localroot != null) {
            addTreeToStringTreeInOrder(localroot.leftChild, stringTree);
            //Ağaca diğer ağacı ekle

            for (String cumle : localroot.data.cumleler) {
                String[] kelimeler = cumle.split(" ");
                for (String kelime : kelimeler) {
                    stringTree.insert(kelime);
                }
            }

            addTreeToStringTreeInOrder(localroot.rightChild, stringTree);
        }
    }


}
