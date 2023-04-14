// node clası
class MilliParkHeapNode {
    private MilliPark heapdata;

    public MilliParkHeapNode(MilliPark heapdata) {
        this.heapdata = heapdata;
    }

    public MilliPark getHeapdata() {
        return heapdata;
    }

    public void setHeapdata(MilliPark heapdata) {
        this.heapdata = heapdata;
    }
}

// Milli park heap sınıfı başlangıcı
public class MilliParkHeap {
    private MilliParkHeapNode[] heapArray;
    private int maxSize;
    private int currentSize;

    public MilliParkHeap(int mx) // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new MilliParkHeapNode[maxSize]; // create array
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(MilliPark key) { // insert metodu
        if (currentSize == maxSize) {
            return false;
        }

        MilliParkHeapNode newNode = new MilliParkHeapNode(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;

    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        MilliParkHeapNode bottom = heapArray[index];
        while (index > 0 && heapArray[parent].getHeapdata().alan < bottom.getHeapdata().alan) {
            heapArray[index] = heapArray[parent]; //Yukarı taşı
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;


    }

    public MilliParkHeapNode remove() {// remove metodu
        MilliParkHeapNode root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        MilliParkHeapNode top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[leftChild].getHeapdata().alan < heapArray[rightChild].getHeapdata().alan) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.getHeapdata().alan >= heapArray[largerChild].getHeapdata().alan) {
                break;
            }

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public boolean change(int index, MilliPark newValue) {
        if (index < 0) {
            return false;
        }
        int oldValue = heapArray[index].getHeapdata().alan;
        heapArray[index].setHeapdata(newValue);
        if (oldValue < newValue.alan) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }
        return true;
    }

    public void displayHeap() {
        System.out.print("MilliParkHeapArray: ");
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.println(heapArray[m].getHeapdata());
            }
        }
        System.out.println();

        int nBlanks = maxSize + 1;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...............................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(' ');
                }
            }
            System.out.print(heapArray[j].getHeapdata().parkAdi);
            if (++j == currentSize) {
                break;
            }

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');
                }
            }

        }
        System.out.println("\n" + dots + dots);
    }
}
