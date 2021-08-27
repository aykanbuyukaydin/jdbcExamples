package techproed.jdbcExamples;

import java.sql.*; //tum jdbc methodlarini eklemek icin

public class Jdbc3DDL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
        String yol = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(yol,"esadeymen","12345");
		
		Statement st = con.createStatement();
		
/*
	 	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet) 
	 	 dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC’de 2 alternatif bulunmaktadir.  
	 	  1) execute() metodu 
	 	  2) excuteUpdate() metodu.  
	 	  
	 	B) - execute() metodu hertur SQL ifadesiyle kullanibilen genel bir komuttur. 
	 	 - execute(), Boolean bir deger dondurur. DDL islemlerin false dondururken, 
	 	 - DML islemlerinde true deger dondurur. 
	 	 - Ozellikle hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi 
	 	  durumlarda tercih edilmektedir.  
	 	   
	 	C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
	 	 - bu islemlerde islemden etkilenen satir sayisini dondurur.
	 	 - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
*/
		
		//ORNEK1: isciler adinda bir tablo olusturunuz id NUMBER(3), 
		 //birim VARCHAR2(10), maas NUMBER(5)
		
		String createQuery= "CREATE TABLE isciler1234"
		         		+ " (id NUMBER(3),"
				        + " birim VARCHAR2(10),"
				        + " maas NUMBER(5))";
		
		//1.Yontemv(excute methodu ile)
//		boolean s1 = st.execute(createQuery);  // fasle deger dondurur DDL
	//	System.out.println("isciler1234 tablosu olusturuldu" + s1);
		//false falan diye degere bakma nın bir anlamı yok
		//cunku 2 defa run edince hata verecek var olan tablop bi dah aolusturulmaz diye
	
	//st.execute(createQuery);
	//System.out.println("isciler1234 tablosu olusturuldu"); //yapabiliriz
		
		//2.Yonten(excuteUpdate methodu ile)
//        int s2 = st.executeUpdate(createQuery); //DDL islemleri icin 0 degeri dondurur
	//	System.out.println("isciler1234 tablosu olusturuldu" + s2);

//	st.executeUpdate(createQuery);
//	System.out.println("isciler tablosu olusruruldu");
		
		//ORNEK2:isciler tablosunu kalici olarak siliniz 	
		String dropQuery1 = "DROP TABLE isciler1234 PURGE";
//		st.execute(dropQuery1);
//		System.out.println("isciler1234 tablosu silindi");
		//tekrar olusturalım dersek mesela satır 55ve 56 // silip satır 60 ve 61 // koymak gerekiyor
		//exlips hepsini run ettigi icin kapatmak lazım
		
		//ORNEK3:isciler tablosuna yeni bir sutun {isim Varchar2(20)} ekleyiniz.  
		String alterQuery1 = "ALTER TABLE isciler1234"
				           + " ADD isim VARCHAR2(20)";
		
//		st.executeUpdate(alterQuery1);
//		System.out.println("isciler1234 tablosuna isim sutunu eklendi");
		
		// ORNEK4:isciler tablosuna soyisim VARCHAR2(20) ve sehir VARCHAR2(10)) 
		  // adinda 2 yeni sutun ekleyiniz.  
		String alterQuery2 = "ALTER TABLE isciler1234"
		           + " ADD (soyisim VARCHAR2(20), sehir VARCHAR2(20))";
		
//		st.executeUpdate(alterQuery2);
//		System.out.println("isciler1234 tablosuna soyisim ve sehir sutunu eklendi");
		
		// ORNEK5:isciler tablosundaki soyisim sutunu siliniz.
		String alterQuery3 = "ALTER TABLE isciler1234 DROP COLUMN soyisim";
		
//		st.executeUpdate(alterQuery3);
//		System.out.println("isciler1234 tablosundan soyisim sutunu silindi");
		
		// ORNEK6:isciler tablosununadini calisanlarx olarak degistiriniz.  
		String alterQuery4 = "ALTER TABLE isciler1234 RENAME TO calisanlarx";
		
//		st.executeUpdate(alterQuery4);
//		System.out.println("isciler1234 tablosunun adi calisanlarx olaark degistirildi");
		
		//calisanlarx tablosunu siliniz
        String dropQuery2 = "DROP TABLE calisanlarx PURGE";
		
		st.executeUpdate(dropQuery2);
		System.out.println("calisanlarx tablosu silindi");
		
		st.close();
		con.close();
		

	}

}
