public class StringTree{ // cümleleri kelimelere ayırıp diğer ağaca eklemek için başka bir class açtık

    class StringTreeNode {
        public String kelime;
        public int sayi = 0;
        public StringTreeNode rightChild;
        public StringTreeNode leftChild;

        public void disPlayNode() {
            System.out.println(kelime + ": " + sayi);
        } // yazdırma metodu
    }


    private StringTreeNode root;

    public StringTreeNode getRoot() {
        return root;
    }

    public void insert(String kelime) { // kelimeleri eklemek için metod
        StringTreeNode newNode = new StringTreeNode();
        newNode.kelime = kelime;

        if (root == null) {
            root = newNode;
        } else {
            StringTreeNode current = root;
            StringTreeNode parent;
            while (true) {
                parent = current;
                if (Main.sortStringsWithTurkceKarakterDestegi(Main.trCollator, kelime, current.kelime) < 0) { //kelime < current ise
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        parent.leftChild.sayi += 1;
                        return;
                    }
                } else if (Main.sortStringsWithTurkceKarakterDestegi(Main.trCollator, kelime, current.kelime) > 0){ //kelime < current ise
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        parent.rightChild.sayi += 1;
                        return;
                    }
                }
                else { //Kelimeler aynı
                    current.sayi += 1;
                    return;
                }

            }
        }
    }

    public void inOrder(StringTreeNode localroot) { // kelimeleri yazdırmak için metod
        if (localroot != null) {
            inOrder(localroot.leftChild);
            localroot.disPlayNode();
            inOrder(localroot.rightChild);
        }
    }
}
