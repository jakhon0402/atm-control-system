# atm-control-system
Bankomatlardan pul yechilishini va bankomat orqali plastik kartalarga pul o’tkazish ishlarini online nazorat qiluvchi tizim.

<br> <br> 
<h1 align="center">Texnik topshiriq</h1>
<p align="center"> <i>
Ushbu tizim bankomatlardan pul yechilishini va bankomat orqali plastic card ga pul o’tkazish ishlarini online nazorat qiluvchi tizim hisobalandi. Bitta bankning bankomatlari uchun xizmat qiladi.</i> <br> <br> 
</p>

<h2 align="center">CARD</h2>
<p>
 Xar bir card ning:<br> 
-	maxsus 16 xonali raqami;<br> 
-	Qaysi bankka tegishli ekanligi<br> 
-	3 xonali CVV code<br> 
-	Mijozning familya va ismi<br> 
-	card ning amal qilish muddati<br> 
-	4 xonali maxsus paroli<br> 
-	Plastik turi (HUMO, UZCARD, VISA)<br> 
Agar cardning amal qilish muddati tugagan bo’lsa undan foydalana olmaslik kerak. Aktivlashtirish uchun bank oficedagi mas’ul bu ishni qila oladi.
 <br> <br> 
</p>

<h2 align="center">BANKOMAT</h2>
<p>
Bankomatda quyidagi xususiyatlari bo’ladi:
-	qanday turdagi card lar uchun mo’ljallanganligi (UZCARD, HUMO, VISA)<br> 
-	yechiladigan max pul miqdori (Bu bank tomonidan o’rnatiladi. Bank tomonidan har bir bankomat uchun alohida yoki hamma bankomalatlar uchun bir-xil qo’yilishi mumkin)<br> 
-	Pul yechayotgan card bankomatni o’rnatgan bankga tegishli bo’lsa pul yechayotgandagi commission miqdori va card hisobi to’ldirilayotgandagi comission miqdori<br> 
-	Pul yechayotgan card bankomatni o’rnatgan bankga tegishli bo’lmasa pul yechayotgandagi commission miqdori va card hisobi to’ldirilayotgandagi comission miqdori<br> 
-	Bankomatda minimum qancha mablag’ qolsa bankning mas’ul xodimiga xabar borishligi uchun miqdor (Masalan: 20 000 000 dan kam qolsa emailga kelsin yoki 10 000 000)<br> 
-	Bankomat joylashgan manzil<br> <br> 
  Bankomatda mablag’lar kupyuralar bo’yicha turadi (1000 so’mlik, 5000 so’mlik, 10 000 so’mlik, 50 000 ming so’mlik, 100 000 ming so’mlik, 1$, 5$, 10$, 20$, 50$,100$  kabi alohida qutilarda saqlanadi).<br> 
  Mijoz pul bankomatda har qanday amal bajarishidan oldin cardning pin kodini kiritadi. Agar parol va login to’g’ri bo’lsa, card sistemaga “Basic” authentication orqali kiradi. Login card ning 16 xonali raqami hisoblanadi, uni bankomatni o’zi o’qib oladi, parol card ning pin kodi hisoblanadi.<br> 
  Mijozga pul berilganda qaysi kupyuradan nechi dona berilganligi, card hisobi to’ldirilganda esa qaysi kupyuradan nechta solinganligi saqlanib boriladi.<br> 
  Bankda bankomatlarni hisobini haqiqatan real naqd pul bilan to’ldiriladi va sistemaga bu haqida ma’lumot kiritadi. Ya’ni bankomat hisobi to’ldirildi deb. Bu jarayon uchun mas’ul xodim biriktiriladi. Bunda qaysi kupyuradan nechta solinganligi kiritiladi.<br> 
  Agar pul yechish jarayonida card ning pin kodi 2 martadan ortiq noto’g’ri kiritilsa card block holatiga o’tkaziladi va card ning egasini bank officega borib bu blokdan chiqaradi.<br> 
  Agar mijoz yechmoqchi bo’lgan summa kupyuralar bo’yicha hisob-kitob qilinganda bunday miqdorda kupyura chiqmasa, mijzoga bu haqida xabar qaytarishi kerak.<br> 
  Agar mijoz cardni to’ldirmoqchi bo’lganda, kiritgan kupyurasi uchun bankomatda bunday kupyura qutisi bo’lmasa, mijzoga bu haqida xabar qaytarishi kerak va kupyura mijozga qaytariladi.<br> 

</p>
<br><br>
<h2 align="center">USERS</h2>
<p>
Tizimda ikkita foydalanuvchi bo’ladi:<br>
-	Bank direktori;<br>
-	Bankomatlarning hisobini to’ldirish uchun mas’ul xodim<br>
Bankomatlarda balance miqdori bankomat uchun belgilangan miqdordan kam qolganda mas’ul xodimning email manziliga ogohlantiruvchi xabar jo’natiladi.<br>
Bank direktori bankomatlarning hisobotlarini kuzatib boradi:<br>
-	Kirim-chiqimlar ro’yxati (Mijozlar tomonidan yechilgan va cardga solingan pullar. Bunda bankomat bo’yicha ko’riladi);<br>
-	Kunlik kirim miqdori (cardga solingan pullar. Bunda bankomat bo’yicha ko’riladi);<br>
-	Kunlik chiqim miqdori (Mijozlar tomonidan yechilgan. Bunda bankomat bo’yicha ko’riladi) ;<br>
-	Bankomatga biriktirilgan mas’ul tomonidan to’ldirilganlik ro’yxati (Bunda bankomat bo’yicha ko’riladi. ).<br>
-	Bankomatda mavjud bo’lgan kupyuralar ro’yxati va miqdori (Bunda bankomat bo’yicha ko’riladi).<br>
<br><br>
</p>

<h2 align="center">Atm control system Entity Relationship (ER) Diagram</h2>

![atm-control-system](atm-control-system.png)

