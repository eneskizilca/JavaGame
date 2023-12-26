import java.io.*;
import java.util.Scanner;

public class Proje {
    
    // kenar köşe kontrolü yapıp dizi içinde kalmasını sağla

    public static void yonKontrol(int[][] harita, int str, int stn, int sayi) {
        if (str >= 0 && str < harita.length && stn >= 0 && stn < harita[0].length) {
            anaKontrol(harita, str, stn, sayi);
        }
    }
    
    public static void anaKontrol(int[][] harita, int str, int stn, int sayi) {
        
        if (harita[str][stn] != sayi) { // alt üst sağ soldan herhangi biri farklı ise orayı aramaya devam etme
            return;
        }

        harita[str][stn] = 0; // girilen kkordinattaki sayı ile aynıysa 0 ile değiştir

        // alt üst sağ solda sayı eşleşmesi kontrolü yap
        
        yonKontrol(harita, str, stn + 1, sayi);
        yonKontrol(harita, str, stn - 1, sayi);
        yonKontrol(harita, str + 1, stn, sayi);
        yonKontrol(harita, str - 1, stn, sayi);
    }
    
    // map'i yazdırma metodu
    
    public static void yazdir(int map[][]) {
        
        System.out.println(" -> İYİ OYUNLAR! <-" + "\n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 0) { // 0 olarak değişmiş değer  yerine X yaz
                    System.out.print("X ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Yolunu belirtilen txt dosyasını (oyundaki map değerlerini) map adındaki diziye ekle
        String dosyaYolu = "C:\\Users\\lenovo\\Desktop\\map.txt"; // Rakamların bulunduğu dosyanın yolu
        int[][] map = new int[10][10]; // 10x10'luk bir rakam dizisi oluştur

        Scanner input = new Scanner(System.in);
        BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaYolu));
        String txtSatir;
        int satirSayaci = 0;

        // Dosyanın sonuna gelene kadar her satırı oku ve rakam dizisine ekle
        while ((txtSatir = okuyucu.readLine()) != null && satirSayaci < 10) {
            String[] rakamlar = txtSatir.trim().split("\\s+");

            for (int i = 0; i < 10; i++) {
                map[satirSayaci][i] = Integer.parseInt(rakamlar[i]);
            }
            satirSayaci++;
        }

        // Oyunu 0 girilene kadar devam ettir
        while (true) {

            yazdir(map);
            
            System.out.println("(Herhangi birine \"0\" girmek oyunu sonlandıracak !)");
            System.out.print("[1-10] aralığında bir satır değeri giriniz: ");
            int satir = input.nextInt() - 1; // Satırın indisi 0'dan başladığı için 1 azalt
            System.out.print("[1-10] aralığında bir sütun değeri giriniz: ");
            int sutun = input.nextInt() - 1; // Satırın indisi 0'dan başladığı için 1 azalt

            while (satir < -1 || satir > 9 || sutun < -1 || sutun > 9) {
                System.out.print("Geçersiz değer girdiniz. Lütfen geçerli değerler giriniz: ");
                satir = input.nextInt() - 1;
                sutun = input.nextInt() - 1;
            }
            
            if (satir == -1 || sutun == -1) { // herhangi bir koordinatı 0 girilirse döngüyü bitir
                System.out.println("\n" + "Oyun Sonlandı !");
                break;
            }
            anaKontrol(map, satir, sutun, map[satir][sutun]);

        }
    }
}