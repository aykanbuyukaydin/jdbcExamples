package techproed.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.*; // onerilmez ama butun sql deki importlari yapar

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1)ilgili driveri yuklemeliyiz
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2)baglanti olusturmaliyiz
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain","esadeymen","12345");
		
		// 3) SQL komutlari icin bir Statement nesnesi olustur  // sql ifadeleri yazmak icin kullaniyoruz
		Statement st = con.createStatement();
		
	    // 4)SQL ifadeleri yazabiliriz
		//Sorgu ifadesini calistir. ( personelab tablosundaki id=7369 olan personelin adını sorgula)
		ResultSet isim = st.executeQuery("SELECT personel_isim FROM personelab WHERE personel_id=7369");
				                                  //maas bide eklenmis olsa idi ve int olsun
		// 5)Sonuclari aldik ve isledik
		while(isim.next()) {
		System.out.println("Personel Adi: " + isim.getString("personel_isim"));
		//veya // ustte bide maas eklenmis olsaydi
		//System.out.println("Personel Adi: " + isim.getString(1) + "Maas: " + isim.getInt(2)); yapabilirdik
		}
				
		// 6) Olusturulan nesneleri bellekten kaldir.
		con.close();
		st.close();
		isim.close();
		

	}

}
