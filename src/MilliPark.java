import java.util.List;

public class MilliPark { // milli park sınıfı
    String parkAdi;
    String ilAdi;
    int alan;
    String tarih;
    List<String> cumleler;

    public MilliPark(String parkAdi, String ilAdi, int alan, String tarih, List<String> cumleler) {
        this.parkAdi = parkAdi;
        this.ilAdi = ilAdi;
        this.alan = alan;
        this.tarih = tarih;
        this.cumleler = cumleler;


    }

    @Override
    public String toString() {
        return
                "Milli Park Adı: " + parkAdi + '\n' +
                        "İl Adı: " + ilAdi +'\n'+
                        "Park Alanı: " + alan + '\n'+
                        "Tarih: " + tarih + '\n' +
                        "Özellikler: " + cumleler +'\n'
                ;
    }


}

