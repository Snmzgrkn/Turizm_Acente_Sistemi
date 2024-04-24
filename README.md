#Turizm Acentesi Projesi
Bu proje, Patika+ Backend Programı için Bitirme Projesi olarak geliştirilmiştir. Proje, bir turizm acentesi yönetim sistemini simüle etmektedir.

#Kullanılan Teknolojiler
Java
Swing (GUI)
PostgreSQL (Veritabanı)
JDBC (Java Veritabanı Bağlantısı)
Maven (Bağımlılık Yönetimi)
Projeyi Çalıştırma
Proje Java ve Maven ile geliştirildiği için bilgisayarınızda Java ve Maven'in yüklü olması gerekmektedir. Ayrıca PostgreSQL veritabanı da kullanılmaktadır, bu nedenle PostgreSQL sunucusunun da kurulu olması gerekmektedir.

#Projenin kaynak kodlarını bilgisayarınıza klonlayın:
bash
Copy code
git clone https://github.com/kullanici/proje.git
PostgreSQL veritabanında otel adında bir veritabanı oluşturun.
src/main/resources dizinindeki config.properties dosyasını açarak PostgreSQL veritabanı ayarlarınızı yapın:
bash
Copy code
db.url=jdbc:postgresql://localhost:5432/otel
db.user=kullanici
db.password=sifre
Proje dizininde terminal veya komut istemcisini açın ve aşağıdaki komutu çalıştırarak projeyi derleyin ve çalıştırın:
bash
Copy code
mvn clean compile exec:java
Eksik Kısımlar
Müşteri yönetimi ekranı tamamlanmamıştır.
Raporlama modülü henüz eklenmemiştir.

24.04.2024 Readme 23:59 olmadan düzeltilecek
Yapılamıyanlar
- Arama Kısımları
- Rezervasyon Güncelleme Silme 
- Otel Ekleme(Tesis Özellikleri Eklenirken Sıkıntı Çıkıyor)
  
