
 Piri Reis Sinema Sistemi - Java_FinalProjesi - Ümit Güldalı

Bu proje, sinema işletmeleri için kullanıcı dostu bir rezervasyon ve yönetim sistemi sunar. Sinema salonlarını, filmleri ve müşterileri kolayca yönetmenizi sağlar.
Bu proje, bir sinema işletmesinde çalışan bilet satış görevlisinin ihtiyaçlarını göz önünde bulundurarak geliştirilmiştir.

Özellikler
- Filmleri ve salonları listeleyebilme
- Seçilen salonda koltuk rezervasyonu yapma
- Salonlardaki müşteri listelerini görüntüleme
- Yeni salon ve film ekleme
- Verileri JSON dosyasına kaydetme ve yükleme
- Koltuk düzenini görüntüleme ve rezervasyon yapabilme

 Kullanım
1. Uygulama Başlatma: 
   Projeyi çalıştırdığınızda bir menü ekranı karşınıza gelir.

2. Menü Seçenekleri:
   - **1. Filmleri ve Salonları Görüntüle**: Tüm filmleri ve salonları listeler. İstediğiniz filmi seçip koltuk rezervasyonu yapabilirsiniz.
   - **2. Salonlara Kayıtlı Müşterileri Listele**: Her salon için müşteri listelerini görüntüler.
   - **3. Yeni Salon ve Film Ekle**: Yeni bir film ve salon ekleyerek sisteminizi genişletebilirsiniz.
   - **4. Verileri Kaydet ve Çık**: Tüm verileri kaydeder ve uygulamadan güvenli bir şekilde çıkış yapar.

 Gereksinimler
- Java Development Kit (JDK) 8 veya üstü
- JSON işleme için dahili I/O kütüphaneleri kullanılır.

 Veri Dosyaları
- Filmler ve salonlar için: `sinema_verileri.json`
- Müşteri bilgileri için: `musteri_verileri.json`

Varsayılan Veri
Eğer sistemde kayıtlı film ve salon yoksa, başlangıçta şu varsayılan veriler otomatik olarak eklenir:
- Filmler: 
  - "Avatar", "Parasite", "Joker", "Inception", "The Godfather", "The Dark Knight"
- Salonlar: 
  - 1. Salon (VIP), 2. Salon (Standart), 3. Salon (Aile)

Çalıştırma
1. Proje dosyalarını derleyin ve çalıştırın.
2. JSON dosyalarının doğru bir şekilde yazma/okuma izinlerine sahip olduğundan emin olun.
3. Menüyü takip ederek işlemlerinizi gerçekleştirin.

