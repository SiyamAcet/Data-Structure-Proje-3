
// heap clası için node sınıfı
class HeapNode {
    private int heapdata;

    public HeapNode(int heapdata) {
        this.heapdata = heapdata;
    }

    public int getHeapdata() {
        return heapdata;
    }

    public void setHeapdata(int heapdata) {
        this.heapdata = heapdata;
    }
}

// Heap clası başlangıcı
public class Heap {
    private HeapNode[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new HeapNode[maxSize]; // create array
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(int key) {// heap clası için insert metodu
        if (currentSize == maxSize) {
            return false;
        }

        HeapNode newNode = new HeapNode(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        //displayHeap(); // Her eleman eklenmesinde ağacı gösteriyoruz.
        return true;

    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        HeapNode bottom = heapArray[index];
        while (index > 0 && heapArray[parent].getHeapdata() < bottom.getHeapdata()) {
            heapArray[index] = heapArray[parent]; //Yukarı taşı
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;


    }

    public HeapNode remove() { // heap clası için remove metodu
        HeapNode root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;


    }

    public void trickleDown(int index) {
        int largerChild;
        HeapNode top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[leftChild].getHeapdata() < heapArray[rightChild].getHeapdata()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.getHeapdata() >= heapArray[largerChild].getHeapdata()) {
                break;
            }

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public boolean change(int index,int newValue){ // indexi verilen değerin verilen değerle yer değiştirmesi metodu
        if (index<0){
            return false;
        }
        int oldValue =heapArray[index].getHeapdata();
        heapArray[index].setHeapdata(newValue);
        if (oldValue<newValue){
            trickleUp(index);
        }
        else {
            trickleDown(index);
        }
        return true;
    }

    public void displayHeap(){ // heapi yazdırmak için metod
        System.out.print("heapArray: ");
        for (int m = 0; m < currentSize; m++)
        {
            if(heapArray[m] != null){
                System.out.print(heapArray[m].getHeapdata() + " ");
            }
            else{
                System.out.print("-- ");
            }
        }
        System.out.println();

        int nBlanks = maxSize+1;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...............................";
        System.out.println(dots + dots);

        while (currentSize > 0)
        {
            if (column == 0)
            {
                for(int k = 0; k < nBlanks; k++){
                    System.out.print(' ');
                }
            }
            System.out.print(heapArray[j].getHeapdata());
            if (++j == currentSize)
            {
                break;
            }

            if (++column == itemsPerRow){
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }
            else {
                for(int k = 0; k < nBlanks*2 - 2; k++)
                {
                    System.out.print(' ');
                }
            }

        }
        System.out.println("\n"+dots+dots);
    }
}
