package techproed.jdbcExamples;

import java.sql.*; //tum jdbc methodlarini eklemek icin

public class Jdbc2Query02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String yol = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(yol,"esadeymen","12345");
		
		Statement st = con.createStatement();
		
		//bolumler tablosundaki tum kayitlari listeleyen sorguyu yaziniz
		String selectQuery = "SELECT * FROM bolumlerab";
		ResultSet tablo1 = st.executeQuery(selectQuery);
		
		while(tablo1.next()) {
			System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3));
		}
		
		System.out.println("===========================");
		
		//SATIS ve MUHASABE bolumlerinde calisan personelin isimlerini ve 
 		 //maaslarini maas ters sirali olarak listeleyiniz
		
		String q2="SELECT personel_isim, maas "
				+ "FROM personelab "
				+ "WHERE bolum_id IN(10,30) "
				+ "ORDER BY maas DESC";
		
		ResultSet sonuc = st.executeQuery(q2);
		
		while(sonuc.next()) {
			System.out.println("personel isim: " + sonuc.getString(1) + "\t" + "maas: " + sonuc.getInt(2));
		}
		
		System.out.println("===========");
		//Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini 
		 //ve maaslarini bolum ve maas sirali listeleyiniz. NOT: calisani olmasa 
		 //bile bolum ismi gosterilmelidir.
		
		String q3 ="SELECT b.bolum_isim, p.personel_isim, p.maas"
				+ " FROM personelab p"
				+ " FULL JOIN bolumlerab b"
				+ " ON b.bolum_id=p.bolum_id"
				+ " ORDER BY b.bolum_isim, p.maas"; 
		
		ResultSet sonuc1=st.executeQuery(q3);
		
		while(sonuc1.next()) {
			System.out.println(sonuc1.getString(1)+ "\t" + sonuc1.getString(2) + "\t" + sonuc1.getInt(3));
		}
		
		System.out.println("==============");
		// Maasi en yuksek 10 kisiyinin bolumunu,adini ve maasini listeyiniz
		String q4 = "SELECT b.bolum_isim,p.personel_isim, p.maas"
				+ " FROM personelab p"
				+ " JOIN bolumlerab b"
				+ " ON b.bolum_id=p.bolum_id"
				+ " ORDER BY p.maas DESC"
				+ " FETCH NEXT 10 ROWS ONLY";
		
         ResultSet sonuc2=st.executeQuery(q4);
		
		 while(sonuc2.next()) {
			System.out.println(sonuc2.getString(1)+ " " + sonuc2.getString(2) + " " + sonuc2.getInt(3));
		 }
		
		
		
		con.close();
		st.close();
		tablo1.close();
		sonuc.close();
		sonuc1.close();
		sonuc2.close();
	}

}
