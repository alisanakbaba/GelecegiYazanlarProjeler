import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private int bakiye = 0;
    private int toplamYatirilanPara=0;
    private int toplamCekilenPara=0;
    private int toplamOdenenFatura=0;
    private final List<String> hesapOzeti= new ArrayList<>();
    private String islem;

    private void deposit(double amount){
        if (amount<=0){
            System.out.println("Yatırılan para negatif veya 0 olamaz");
            return;
        }
        toplamYatirilanPara+=(int)amount;
        bakiye+= (int) amount;
        islem="Banka hesabınıza yatırdığınız tutar: "+amount + "  Tarih: "+ LocalDateTime.now();
        hesapOzeti.add(islem);
        System.out.println(islem);
    }

    private void withdraw(double amount){
        if(amount<=0){
            System.out.println("Çekilmek istenen tutar 0 dan büyük olmalı");

        } else if (amount>bakiye) {
            islem="Banka hesabınızda bu kadar para bulunmamaktadır. Bakiyeniz: "+bakiye+"TL" + "Tarih: "+ LocalDateTime.now();
            System.out.println(islem);
        }else {
            double komisyon = amount > 5000 ? amount * 0.02 : 0;
            toplamCekilenPara+=(int)amount;
            bakiye-=(int) amount + (int)komisyon;
            islem="Banka hesabınızdan: "+(amount+komisyon)+ "TL çekilmiştir" + "  Tarih: "+ LocalDateTime.now();
            hesapOzeti.add(islem);
            System.out.println(islem);
        }
    }

    private  void payBill(int billType, double amount){
        if(amount<=0){
            System.out.println("Negatif para veya 0 tutarı!!!");

        } else if (billType<0 ||billType>3) {
            System.out.println("Böyle bir fatura bulunmamaktadır.");
        } else if (bakiye<amount) {
            System.out.println("Banka hesabınızda bu kadar para bulunmamaktadır.");
        } else if (billType==1 && bakiye>amount){
            toplamOdenenFatura+=1;
            double indirim = amount*0.05;
            bakiye -= (int)(amount-indirim);
            islem="Elektrik borcunuz: "+(amount-indirim) +"TL bakiyenizden düşüp yatırılmıştır." + "  Tarih: "+ LocalDateTime.now();
            hesapOzeti.add(islem);
            System.out.println(islem);
        }
        else if (billType==2 && bakiye>amount){
            toplamOdenenFatura+=1;
            double indirim = amount*0.03;
            bakiye -= (int)(amount-indirim);
            islem="Su borcunuz: "+(amount-indirim) +"TL bakiyenizden düşüp yatırılmıştır." + "  Tarih: "+ LocalDateTime.now();
            hesapOzeti.add(islem);
            System.out.println(islem);
        }
        else if (billType==3 && bakiye>amount){
            toplamOdenenFatura+=1;
            double indirim = amount*0.02;
            bakiye -= (int)(amount-indirim);
            islem="İnternet borcunuz: "+(amount-indirim) +"TL bakiyenizden düşüp yatırılmıştır." + "  Tarih: "+ LocalDateTime.now();
            hesapOzeti.add(islem);
            System.out.println(islem);
        }
    }

    private  void printSummary(){
        System.out.println("Hesabınızda bulunan para miktarı: "+bakiye+"TL dir");
        System.out.println("Hesabınızda yapılan işlemler aşağıdaki gibidir");
        System.out.println("------------------------------------");
        System.out.println("#" + "\t\tİşlemler\t\t\t\t\t\t\t\t\tTarih");
        int count=0;
        for (String s : hesapOzeti) {

            System.out.println(count + "-" + s);
            count++;
        }
        System.out.println("Toplam Yatırılan Para: "+toplamYatirilanPara);
        System.out.println("Toplam Çekilen Para: "+toplamCekilenPara);
        System.out.println("Toplam Ödenen Fatura: "+toplamOdenenFatura);
        System.out.println("------------------------------------");
    }

    private  boolean Auth(String userName,String password){
        String userNameValue = "user";
        String passwordValue = "1234";
        return userName.equals(userNameValue) && password.equals(passwordValue);
    }


    public static void main(String[] args) {
        Main app = new Main();
        Scanner input = new Scanner(System.in);

        String userName,password;
        short kalanHak = 3;
        while(kalanHak>0){
            System.out.print("Kullanıcı Adınızı giriniz: ");
            userName = input.nextLine();
            System.out.print("Şifrenizi giriniz: ");
            password = input.nextLine();
            if (app.Auth(userName,password)){
                app.islem = "Sisteme başarılı bir şekilde giriş yaptınız." + "  Tarih : "+LocalDateTime.now();
                app.hesapOzeti.add(app.islem);
                System.out.println(app.islem);
                break;
            }
            else{
                app.islem = "Kullanıcı adı veya parola hatalı" + "  Tarih : "+LocalDateTime.now();
                app.hesapOzeti.add(app.islem);
                System.out.println(app.islem);
                kalanHak--;
            }
        }
        short seciliIslem=0;
        double amount;
        short billType;
        if (kalanHak>0){
            while(seciliIslem!=5){
                System.out.println(
                        """
                                1- Para Yatır
                                2- Para Çek
                                3-Bakiye Görüntüle
                                4-Fatura Öde (Elektrik/Su/İnternet)
                                5-Çıkış""");
                System.out.print("Seçiminizi giriniz: ");
                seciliIslem = input.nextShort();
                switch (seciliIslem){
                    case 5:
                        System.out.println("Program sonlandırılıyor, iyi günler dileriz.");
                        break;
                    case 1:
                        System.out.print("Hesabınıza ne kadar para yatırmak istiyorsunuz: ");
                        amount=input.nextDouble();
                        app.deposit(amount);
                        break;
                    case 2:
                        System.out.print("Hesabınızdan ne kadar para çekmek istiyorsunuz: ");
                        amount=input.nextDouble();
                        app.withdraw(amount);
                        break;
                    case 3:
                        app.printSummary();
                        break;
                    case 4:
                        System.out.print("Hangi faturanızı yatırmak istiyorsunuz: ");
                        billType=input.nextShort();
                        System.out.print("Ne kadar para ödeyeceksiniz: ");
                        amount=input.nextDouble();
                        app.payBill(billType,amount);
                        break;
                    default:
                        System.out.println("Hatalı bir işlem seçtiniz lütfen tekrar deneyiniz!");
                }

            }
        }
    }
}