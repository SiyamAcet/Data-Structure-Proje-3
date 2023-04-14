import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.text.Collator;

public class Main {
    public static Collator trCollator = Collator.getInstance(new Locale("tr", "TR"));


    public static void main(String[] args) {

        //Bölüm 4.a
        int[] dizi = new int[]{64, 25, 12, 22, 11};
        selectionSort(dizi);

        //Bölüm 1 ve devamı
        Tree agac = new Tree();
        Hashtable<String, MilliPark> milliParkHashtable = new Hashtable<>();
        MilliParkHeap yuzOlcumuHeap = new MilliParkHeap(48);

        try {
            Scanner reader = new Scanner(new FileReader("milli parklar.txt"));

            while (reader.hasNextLine()) {

                //Milli parkları metin dosyasından okuma kısmı
                String[] parkOzellikleri = reader.nextLine().split("____");

                String parkAdi = parkOzellikleri[0];
                String ilAdi = parkOzellikleri[1];
                int alan = Integer.parseInt(parkOzellikleri[2]);
                String tarih = parkOzellikleri[3];
                List<String> cumleler = Arrays.asList(parkOzellikleri[4].split("\\. "));
                MilliPark milliPark = new MilliPark(parkAdi, ilAdi, alan, tarih, cumleler);

                //Bölüm 1.a
                agac.insert(milliPark);

                //Bölüm 2.a
                milliParkTablosunuDoldurma(milliParkHashtable, milliPark);

                //Bölüm 3.b
                yuzOlcumuHeap.insert(milliPark);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı...");
        }

        //Bölüm 1.b
        System.out.println("Ağacın Derinliği: " + agac.maxDepth(agac.getRoot()));
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Ağacın İçindeki Veriler InOrder Şeklinde Sırasıyla: ");
        agac.inOrder(agac.getRoot());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Ağacın Düğüm Sayısı: " + agac.totalNodes(agac.getRoot()));
        agac.countBalancedDepth(agac.totalNodes(agac.getRoot()));

        //Bölüm 1.c

        Scanner scanner = new Scanner(System.in);
        System.out.print("Aratmak istediğiniz park adının ilk üç harfini giriniz: ");
        String parkName = scanner.nextLine();
        agac.searchNode(agac.getRoot(), parkName);

        //Bölüm 1.d
        StringTree stringTree = new StringTree();
        agac.addTreeToStringTreeInOrder(agac.getRoot(), stringTree);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Kelime Ağacı İçindeki Veriler InOrder Şeklinde Sırasıyla: ");
        stringTree.inOrder(stringTree.getRoot());
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //Bölüm 2.b
        milliParkTarihDegistirme(milliParkHashtable);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Milli Parkların Bulunduğu HashTable Verisi");
        System.out.println(milliParkHashtable);
        System.out.println("");
        System.out.println("");
        System.out.println("");


        //Bölüm 3.a
        Heap theHeap = new Heap(31);
        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);
        theHeap.remove();
        //yuzOlcumuHeap.displayHeap();

        //Bölüm 3.c
        heaptenEnBuyukUcParkiSil(yuzOlcumuHeap);
    }

    // Bölüm 1.a
    public static int sortStringsWithTurkceKarakterDestegi(Collator collator, String str1, String str2) {
        return Integer.compare(collator.compare(str1, str2), 0); //Türkçe karakterlerin sorun çıkarmaması için böyle karşılaştırıyoruz.
    }

    // Bölüm 2.a
    public static void milliParkTablosunuDoldurma(Hashtable<String, MilliPark> hashtable, MilliPark park) {
        hashtable.put(park.parkAdi, park);
    }

    // Bölüm 2.b
    public static void milliParkTarihDegistirme(Hashtable<String, MilliPark> milliParkHashtable) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("İlan tarihini değiştirmek istediğiniz park adını giriniz: ");
        String parkName = scanner.nextLine();

        if (!milliParkHashtable.containsKey(parkName)) {
            System.out.println("Park bulunamadı.");
            return;
        }

        MilliPark park = milliParkHashtable.get(parkName);

        System.out.print("Yeni ilan tarihini girin: ");
        park.tarih = scanner.nextLine();
        System.out.println("Park güncellendi: " + park);
    }

    // Bölüm 3.c
    public static void heaptenEnBuyukUcParkiSil(MilliParkHeap milliParkHeap) {
        System.out.println("En Büyük 3 Milli Park:");

        for (int i = 0; i < 3; i++) {
            System.out.println(milliParkHeap.remove().getHeapdata());
        }
    }

    public static void selectionSort(int[] dizi) {
        System.out.println("SelectionSort - Dizinin En Baştaki Hali:");
        printArray(dizi);

        int sayiMiktari = dizi.length;
        for (int i = 0; i < sayiMiktari - 1; i++) {

            System.out.println();
            System.out.println("Tur " + i + " Sonu");

            int enKucukSayininIndeksi = i;

            for (int j = i + 1; j < sayiMiktari; j++) {
                if (dizi[j] < dizi[enKucukSayininIndeksi]) {
                    enKucukSayininIndeksi = j;
                }
            }
            int geciciDegisken = dizi[enKucukSayininIndeksi];
            dizi[enKucukSayininIndeksi] = dizi[i];
            dizi[i] = geciciDegisken;
            printArray(dizi);

        }
        System.out.println();
        System.out.println("Son Hal");
        printArray(dizi);
    }

    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

}