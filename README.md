## Kurulum AŞAMALARI

---

### Dosya Yapısı
>Graduation (Main Directory) 
> * GraduationProject (backend) **(Spring Boot-MySQL)**
> * graduationFronted (frontend) **(React)**
> * docker-compose.yml

Proje docker üzerinde çalışabilir haldedir. Projeyi sisteminize indirdikten sonra işletim sisteminize göre docker için bazı ayarlar yapmanız gerekmedir. Bunlardan sırasıyla aşağıda bahsedilmiştir. 

Not: Her 2 kurulum içinde **BIOS** üzerinden sanallaştırma(virtualization) aktif hale getirilmesi gerekmektedir.

### Docker Kurulumu (Window 10 Home Edition için)
**Docker for Window** Windows 10 işletim sistemine göre tasarlanmıştır. Ve çalışmalarını gerçekleştirmek için Hyper-V sanallaştırma kullanır. Fakat bildiğiniz üzere Window 10 Home Edition için Hyper-V desteği bulunmamaktadır. Docker'ı Windows 10 Home Edition versiyonunda çalıştırmak için normalde farklı sanallaştırma teknolojileriden yararlanılmaktaydı (Virtul Box). Fakat **WSL2**' nin piyasaya sürülmesiyle birlikte artık bu işletim sistemi üzerinde de yerel olarak çalıştıralabilmesi mümkün hale geldi.
Bu şekilde çalıştırabilmek için ise bazı ön koşullar gerekmektedir. **Windows için 2004 sürümünde** olmanız gerekiyor. 
* Sistem sürümünüzü öğrenmek için 
  * **(Windows + R) -> winver** çalıştırarak mevcut sürümüzü görebilirsiniz. 
  
Eğer sistem sürümünüz 2004 olarak görünmüyorsa Windows Güncellemelerini yapmanız gerekmektedir.

Eğer buraya kadar bir sorun görünmüyorsa artık **WSL' yi yükleyebiliriz**.

#### Wsl Kurulum Aşamaları

1. Window Powershell yönetici modunda çalıştırın.
<br><br>
2. Aşağıdaki komutu çalıştırın. 
> **dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart**
3. Aşağıdaki komutu çalıştırın.
> **dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart**
4. Bilgisayarınızı yeniden başlatın. <br><br>
5. Wsl2 Linux kerneli için aşağıdaki kurulumu gerçekleştiriniz.
> https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi

Bu işlemleri tamamladıktan sonra artık **docker kurulumana** geçebiliriz.

#### Docker Kurulum Aşamaları
1. https://docs.docker.com/desktop/windows/install/ sitesinden kurulum dosyasını indirebilirsiniz.
2. Kurulum bittikten bilgisayarınızı yeniden başlatmanız gerekmektedir.

Artık proje çalıştırma aşamasına geçebiliriz.

---

### Docker Kurulumu (Window 10 Pro ve Üzeri için)
Bu versiyonlarda yapmamız gereken ilk şey **Hyper-V** sanallaştırmayı etkinleştirmemiz olacaktır. 

* Bunun için Denetim Masası -> Programlar -> Windows Özelliklerini aç veya kapat -> Hyper-V (aktifleştir.) 

![hyperV](https://user-images.githubusercontent.com/72503092/152138312-17819582-b347-42b1-9b77-e1aff257713b.png)

Bu işlemleri tamamladıktan sonra artık **docker kurulumana** geçebiliriz.

#### Docker Kurulum Aşamaları
1. https://docs.docker.com/desktop/windows/install/ sitesinden kurulum dosyasını indirebilirsiniz.
2. Kurulum bittikten bilgisayarınızı yeniden başlatmanız gerekmektedir.
3. Docker Desktop Çalıştırın. Eğer Wsl hatası ile karşılaşırsanız. Aşağıdaki işlemi gerçekleştirin.

* Docker Settings -> General -> Use the WSL 2 based engine (kapalı hale getirin.)

![dockerSettings](https://user-images.githubusercontent.com/72503092/152138478-ba7f7b98-4e00-4348-8b24-24a6781aa361.png)

4. Apply & restart diyerek dockerı yeniden başlatın.

Artık proje çalıştırma aşamasına geçebiliriz.

---

### Proje Çalıştırma Aşaması

1. Tek yapmamız gereken Proje Ana dizinindeyken (Graduation) **komut istemini(cmd)** çalıştırmak. 
Bunu kısayoldan gerçekleştirmek için dosya yoluna cmd yazıp enter tıklayarak yapabilirsiniz.<br><br>
![cmd](https://user-images.githubusercontent.com/72503092/152138398-952ed166-5e14-4c6f-a90a-2ec5aae66fa6.png)
2. Komut isteminde aşağıdaki komutu çalıştırabilirsiniz. Komut bu proje için ilk defa çalıştırıldığında gerekli kurulum işlemleri biraz zaman almaktadır.(5-10 dk). Kurulum tamamlanınca aşağıdaki görseldeki yapıyla karşılaşacaksınız.

>**docker-compose up -d**
 
![run](https://user-images.githubusercontent.com/72503092/151660584-a3aef84e-1d08-43c0-907d-6081e7afc00d.png)

3. Çalışan servisleri görmek için aşağıdaki komut kullanılabilir.
> **docker ps**

![ps](https://user-images.githubusercontent.com/72503092/151660588-d1556a3d-9125-403d-813f-3ff8a2ce2d39.png)

4. Servisleri kapatmak için aşağıdaki komut kullanılabilir.
> **docker-compose down**

![down](https://user-images.githubusercontent.com/72503092/151660598-012ce358-db56-4fbe-a513-c656dfe9d852.png)

---

### Test Aşaması
1. Herhangi bir tarayıcı açınız.(Chrome, Opera)
2. **http://localhost/**  diyerek **fronted** kısmına erişebilirsiniz.<br><br>
![localhost](https://user-images.githubusercontent.com/72503092/151660610-5b7dffb5-b72d-474a-9093-c495ee294a41.png) <br><br>

   1. **http://localhost/users** diyerek sistemdeki kullanıcılara erişebilirsiniz.Varsayılan olarak sistemde 3 kullanıcı kayıtlı olarak gelecektir.<br><br>
   ![localhostUser](https://user-images.githubusercontent.com/72503092/151660617-7ac3bc27-81cd-43f2-8567-611d08395a1f.png)  <br><br>
   2. **http://localhost/login**  kullanıcı giriş sistemi için tasarlanmıştır. Projeye ilerleyen zamanlarda eklenebilir.(Aktif değildir!)<br><br>
    ![login](https://user-images.githubusercontent.com/72503092/151660619-37c6e927-134d-4a59-a6ab-9ef51e1fff67.png) <br><br>
3. **http://localhost:8080/swagger-ui/** diyerek **backend** kısmına erişebilirsiniz.<br><br>
   ![backend](https://user-images.githubusercontent.com/72503092/151660630-d1fa428e-6bfd-4921-bf08-3099fcad844a.png)

---

### Sms Servisi
Sms göndermek için **Twilio Servisi** kullanılmıştır. Bu servis deneme sürümünde sadece onaylanmış telefon numaralarına sms
göndermektedir. Bu nedenle test edilirken sms gelmeyecektir.
Eğer numara onaylanırsa beklenen sms örneği aşağıda gösterilmiştir. 

![sms](https://user-images.githubusercontent.com/72503092/151660632-561cfdcf-c18a-4ee9-aee6-ecd6a164bbcd.jpg)

---

### Ekstra Notlar
* **Kredi Scoru** hesaplanırken **kimlik numarasının son 3 hanesine** göre işlem gerçekleştirmektedir.Basitçe söylemek gerekirse son 3 hane 500 değerinin altında ise 
olduğu gibi kalmaktadır. Eğer 500'ün üzerinde ise random bir kaysayı ile çarpılarak işlem gerçekleştirilmektedir.
Proje içerisindeki ilgili lokasyondan incelenebilir.(Util/CreditScoreCalculator)
<br><br>
* Kimlik numarası girişi için ileriye yönelik olması amacıyla Custom Validasyon(CustomValidation/IdentificationNumberValidator) dosyası oluşturulmuştur. Fakat sadece **11 haneli olması ve son hanenin çift olma** durumu aktif haldedir. Bu nedenle kayıt işlemlerinde
sonu çift hane ile biten kimlik numaraları ile işlem gerçekleştiriniz.
<br><br>
* spring.jpa.hibernate.ddl-auto bileşeni **create** modunda kullanılmaktadır.
<br><br>
* Sadece ilk çaıştırma esnasında aşağıdaki hatayla karşılaşılacak fakat sorunsuz çalışacaktır. Bunun nedeni veritabanı hali hazırda bulunmadığı için
yapmaya çalıştığı drop işlemini gerçekleştirememesidir. Sonraki çalıştırma işlemlerinde bu hata görülmeyecektir.
<br><br>
![foreign](https://user-images.githubusercontent.com/72503092/151660639-f09c6529-9e87-43cc-9e00-8f8882cfd144.png)
<br><br>
* Kimlik numarası **unique** olduğu için aynı kimlik numarası ile işlem yapıldığında **fronted** tarafının **console penceresinden** oluşan hata görülebilir.
