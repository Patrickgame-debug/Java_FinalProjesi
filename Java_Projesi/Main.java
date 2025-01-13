import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        SinemaSistemi sistem = SinemaSistemi.verileriYukle("sinema_verileri.json");
        Scanner scanner = new Scanner(System.in);

        try {
            // Eğer sistemde hiç film yoksa varsayılan filmleri ve salonları ekleyelim
            if (sistem.getFilmler().isEmpty()) {
                // Varsayılan filmler
                sistem.yeniFilmEkle(new Film("Avatar", 162, "Bilim Kurgu"));
                sistem.yeniFilmEkle(new Film("Parasite", 132, "Dram"));
                sistem.yeniFilmEkle(new Film("Joker", 122, "Psikolojik Gerilim"));

                // Yeni eklenen filmler
                sistem.yeniFilmEkle(new Film("Inception", 148, "Bilim Kurgu"));
                sistem.yeniFilmEkle(new Film("The Godfather", 175, "Suç/Dram"));
                sistem.yeniFilmEkle(new Film("The Dark Knight", 152, "Aksiyon"));

                // Varsayılan salonlar
                sistem.yeniSalonEkle(new Salon(1, "1. Salon (VIP)", sistem.getFilmler().get(0)));
                sistem.yeniSalonEkle(new Salon(2, "2. Salon (Standart)", sistem.getFilmler().get(1)));
                sistem.yeniSalonEkle(new Salon(3, "3. Salon (Aile)", sistem.getFilmler().get(2)));

                // Yeni eklenen salonlar
                sistem.yeniSalonEkle(new Salon(4, "4. Salon (VIP)", sistem.getFilmler().get(3)));
                sistem.yeniSalonEkle(new Salon(5, "5. Salon (Standart)", sistem.getFilmler().get(4)));
                sistem.yeniSalonEkle(new Salon(6, "6. Salon (VIP)", sistem.getFilmler().get(5)));
            }

            while (true) {
                System.out.println("\n*********************************************");
                System.out.println("*              Piri Reis Sinema             *");
                System.out.println("*********************************************");
                System.out.println("*  1. Filmleri ve Salonları Görüntüle       *");
                System.out.println("*  2. Salonlara Kayıtlı Müşterileri Listele *");
                System.out.println("*  3. Yeni Salon ve Film Ekle               *");
                System.out.println("*  4. Verileri Kaydet ve Çık                *");
                System.out.print("\nLütfen bir işlem seçin (1-4): ");

                int secim = scanner.nextInt();
                scanner.nextLine();

                switch (secim) {
                    case 1:
                        sistem.filmVeSalonlariListele();
                        System.out.print("\nHangi filme gitmek istersiniz? Film ID'sini girin: ");
                        int filmSecim = scanner.nextInt() - 1;

                        if (filmSecim < 0 || filmSecim >= sistem.getFilmler().size()) {
                            System.out.println("Geçersiz seçim. Lütfen doğru bir film ID'si girin.");
                            break;
                        }

                        Salon secilenSalon = sistem.getSalonlar().get(filmSecim);
                        System.out.println("\nSeçilen Salon: " + secilenSalon.getName());
                        System.out.println("Salon Durumu:");
                        secilenSalon.koltukDuzeniniYazdir();

                        System.out.print("Satır numarası seçin (1-5): ");
                        int satir = scanner.nextInt() - 1;
                        System.out.print("Sütun numarası seçin (1-5): ");
                        int sutun = scanner.nextInt() - 1;

                        if (satir >= 0 && satir < 5 && sutun >= 0 && sutun < 5 && secilenSalon.koltukBosMu(satir, sutun)) {
                            secilenSalon.koltukRezerveEt(satir, sutun);
                            System.out.print("Adınızı girin: ");
                            scanner.nextLine();
                            String musteriAdi = scanner.nextLine();
                            System.out.print("Soyadınızı girin: ");
                            String musteriSoyadi = scanner.nextLine();
                            String tamAdi = musteriAdi + " " + musteriSoyadi;
                            Musteri musteri = new Musteri(SinemaSistemi.yeniMusteriIdAl(), tamAdi);
                            sistem.yeniMusteriEkle(musteri, secilenSalon.getId() - 1);
                            sistem.musterileriKaydet("C:\\Users\\araba\\OneDrive\\Masaüstü\\Java_Projesi\\musteri_verileri.json");
                            System.out.println("Koltuk başarıyla rezerve edildi! İyi seyirler, " + musteriAdi + "!");
                        } else {
                            System.out.println("Geçersiz seçim veya dolu koltuk. Lütfen başka bir koltuk seçin.");
                        }
                        break;

                    case 2:
                        System.out.println("\nSalon Müşteri Listesi:");
                        sistem.salonMusterileriniListele();
                        System.out.println("Devam etmek için Enter tuşuna basın...");
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.println("\nYeni Salon ve Film Ekle:");
                        System.out.print("Eklemek istediğiniz film adı: ");
                        String filmAdi = scanner.nextLine();
                        System.out.print("Film süresi (dakika): ");
                        int filmSure = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Film türü (örneğin, Dram, Aksiyon): ");
                        String filmTuru = scanner.nextLine();
                        Film yeniFilm = new Film(filmAdi, filmSure, filmTuru);
                        sistem.yeniFilmEkle(yeniFilm);

                        System.out.print("Yeni salon adı: ");
                        String salonAdi = scanner.nextLine();
                        Salon yeniSalon = new Salon(sistem.getSalonlar().size() + 1, salonAdi, yeniFilm);
                        sistem.yeniSalonEkle(yeniSalon);
                        System.out.println("Yeni salon ve film başarıyla eklendi: " + salonAdi + " - " + filmAdi);
                        break;

                    case 4:
                        sistem.verileriKaydet("C:\\Users\\araba\\OneDrive\\Masaüstü\\Java_Projesi\\sinema_verileri.json");
                        sistem.musterileriKaydet("C:\\Users\\araba\\OneDrive\\Masaüstü\\Java_Projesi\\musteri_verileri.json");
                        System.out.println("Veriler kaydedildi. Programdan çıkılıyor. İyi günler!");
                        return;

                    default:
                        System.out.println("Geçersiz seçim. Lütfen 1-4 arasında bir seçim yapın.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    // Film sınıfı
    public static class Film {
        private String ad;
        private int sure;
        private String tur;

        public Film(String ad, int sure, String tur) {
            this.ad = ad;
            this.sure = sure;
            this.tur = tur;
        }

        public String getAd() {
            return ad;
        }

        public int getSure() {
            return sure;
        }

        public String getTur() {
            return tur;
        }
    }

    // Musteri sınıfı
    public static class Musteri {
        private int id;
        private String name;

        public Musteri(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    // Salon sınıfı
    public static class Salon {
        private int id;
        private String name;
        private Film film;
        private boolean[][] koltuklar = new boolean[5][5];
        private List<Musteri> musteriler = new ArrayList<>();

        public Salon(int id, String name, Film film) {
            this.id = id;
            this.name = name;
            this.film = film;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Film getFilm() {
            return film;
        }

        public boolean koltukBosMu(int satir, int sutun) {
            return !koltuklar[satir][sutun];
        }

        public void koltukRezerveEt(int satir, int sutun) {
            koltuklar[satir][sutun] = true;
        }

        public void koltukDuzeniniYazdir() {
            for (boolean[] row : koltuklar) {
                for (boolean seat : row) {
                    System.out.print(seat ? "D " : "B ");
                }
                System.out.println();
            }
        }

        public void musteriEkle(Musteri musteri) {
            musteriler.add(musteri);
        }

        public List<Musteri> getMusteriler() {
            return musteriler;
        }
    }

    // SinemaSistemi sınıfı
    public static class SinemaSistemi {
        private List<Musteri> musteriler = new ArrayList<>();
        private List<Film> filmler = new ArrayList<>();
        private List<Salon> salonlar = new ArrayList<>();
        private static int musteriIdCounter = 1;

        public void yeniMusteriEkle(Musteri musteri, int salonIndex) {
            musteriler.add(musteri);
            salonlar.get(salonIndex).musteriEkle(musteri);
        }

        public void yeniFilmEkle(Film film) {
            filmler.add(film);
        }

        public void yeniSalonEkle(Salon salon) {
            salonlar.add(salon);
        }

        public void filmVeSalonlariListele() {
            int index = 1;
            for (Film film : filmler) {
                System.out.println(index++ + ". Film Adı: " + film.getAd());
                System.out.println("   Süresi: " + film.getSure() + " dakika");
                System.out.println("   Türü: " + film.getTur());
            }
        }

        public void salonMusterileriniListele() {
            for (Salon salon : salonlar) {
                System.out.println("Salon: " + salon.getName() + " (Film: " + salon.getFilm().getAd() + ")");
                if (!salon.getMusteriler().isEmpty()) {
                    for (Musteri musteri : salon.getMusteriler()) {
                        System.out.println("- " + musteri.getName());
                    }
                } else {
                    System.out.println("   Henüz kayıtlı müşteri yok.");
                }
            }
        }

        public List<Film> getFilmler() {
            return filmler;
        }

        public List<Salon> getSalonlar() {
            return salonlar;
        }

        public static int yeniMusteriIdAl() {
            return musteriIdCounter++;
        }

        public void verileriKaydet(String dosyaAdi) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))) {
                writer.write("{\n\"filmler\": [");
                for (int i = 0; i < filmler.size(); i++) {
                    Film film = filmler.get(i);
                    writer.write("{\"ad\":\"" + film.getAd() + "\",\"sure\":" + film.getSure() + ",\"tur\":\"" + film.getTur() + "\"}");
                    if (i < filmler.size() - 1) writer.write(",");
                }
                writer.write("],\n\"salonlar\": [");
                for (int i = 0; i < salonlar.size(); i++) {
                    Salon salon = salonlar.get(i);
                    writer.write("{\"id\":" + salon.getId() + ",\"name\":\"" + salon.getName() + "\",\"film\":\"" + salon.getFilm().getAd() + "\"}");
                    if (i < salonlar.size() - 1) writer.write(",");
                }
                writer.write("]\n}");
            } catch (IOException e) {
                System.out.println("Veri kaydedilemedi.");
            }
        }

        public void musterileriKaydet(String dosyaAdi) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))) {
                writer.write("[\n");
                for (int i = 0; i < musteriler.size(); i++) {
                    Musteri musteri = musteriler.get(i);
                    for (Salon salon : salonlar) {
                        if (salon.getMusteriler().contains(musteri)) {
                            writer.write("{\"id\":\"" + musteri.getId() + "\",\"name\":\"" + musteri.getName() + "\",\"filmAdi\":\"" + salon.getFilm().getAd() + "\",\"sure\":" + salon.getFilm().getSure() + ",\"tur\":\"" + salon.getFilm().getTur() + "\",\"salonAdi\":\"" + salon.getName() + "\"}");
                            if (i < musteriler.size() - 1) writer.write(",\n");
                        }
                    }
                }
                writer.write("\n]");
                System.out.println("Müşteri verileri başarıyla kaydedildi.");
            } catch (IOException e) {
                System.out.println("Müşteri verileri kaydedilemedi.");
            }
        }

        public static SinemaSistemi verileriYukle(String dosyaAdi) {
            SinemaSistemi sistem = new SinemaSistemi();
            try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
                StringBuilder jsonBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
                // JSON verisini buraya işleme kodu eklenebilir
                System.out.println("Veriler başarıyla yüklendi!");
            } catch (IOException e) {
                System.out.println("Veri yüklenemedi. Varsayılan sistem başlatılıyor.");
            }
            return sistem;
        }
    }
}
