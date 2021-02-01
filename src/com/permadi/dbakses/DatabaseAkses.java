package com.permadi.dbakses;

//import java sql semua package
import java.sql.*;

public class DatabaseAkses {
	//	var koneksi, statement, resultset	
	private static Connection con = null;
	private static Statement st = null;
	private static ResultSet rs = null;
		
	//	method database akses	
	public DatabaseAkses() {
		//	url localhost, port mysql, database		
		String url = "jdbc:mysql://localhost:3306/query_latihan1";
		//	username localhost		
		String user = "root";
		//	password localhost		
		String password = "";
		//	exception koneksi		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {			
			System.out.println(e);
		}
	}
	
	//	method view / select data tabel	
	public static void view() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT A.ID_Anggota, A.NM_Anggota, B.NM_Buku, B.Harga, P.Tgl_Pinjam "
				+ "FROM TB_Anggota A, TB_Buku B, TB_Pinjam P "
				+ "WHERE P.ID_Anggota = A.ID_Anggota AND P.ID_Buku = B.ID_Buku");
		
		System.out.println("Daftar anggota yang telah meminjam buku :");
		
		
		while(rs.next()) {
			System.out.println("\nID Anggota : " + rs.getString(1) + " ");
			System.out.println("Nama ANggota : " + rs.getString(2) + " ");
			System.out.println("Nama Buku : " + rs.getString(3) + " ");
			System.out.println("Harga : " + rs.getString(4) + " ");
			System.out.println("Tanggal Pinjam : " + rs.getString(5) + "");
		}
	}
	
	public static void view1() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT TB_Anggota.ID_Anggota, TB_Anggota.NM_Anggota, TB_Buku.NM_Buku "
				+ "FROM TB_Anggota INNER JOIN tb_pinjam ON tb_pinjam.ID_Anggota = tb_anggota.ID_Anggota "
				+ "INNER JOIN tb_buku ON tb_pinjam.ID_Buku = tb_buku.ID_Buku "
				+ "WHERE id_pinjam NOT IN (SELECT id_pinjam FROM tb_kembali)");
		
		System.out.println("Daftar anggota yang telah meminjam buku namun belum mengembalikan :");
				
		while(rs.next()) {
			System.out.println("\nID Anggota : " + rs.getString(1) + "");
			System.out.println("Nama Anggota : " + rs.getString(2) + "");
			System.out.println("Nama Buku : " + rs.getString(3) + "");
		}
	}
	
	public static void view2() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_buku`.`ID_Buku`,  `tb_buku`.`NM_Buku` "
				+ "FROM  `tb_buku` "
				+ "INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "WHERE id_Pinjam NOT IN (SELECT id_pinjam FROM tb_kembali) AND harga <25000");
		
		System.out.println("Daftar buku yang pernah di pinjam, yang harganya di bawah 25000, yang belum di kembalikan :");
		
		while(rs.next()) {
			System.out.println("\nID Buku : " + rs.getString(1));
			System.out.println("Nama Buku : " + rs.getString(2));
		}
		
	}
	
	public static void view3() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_buku`.`ID_Buku`,  `tb_buku`.`NM_Buku` "
				+ "FROM  `tb_buku` "
				+ "INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "WHERE tb_buku.ID_Buku IN (SELECT id_buku FROM tb_pinjam WHERE YEAR(tgl_pinjam)<2010)");
		
		System.out.println("Daftar buku yang tidak pernah di pinjam di bawah tahun 2010 yang kemudian di pinjam di pada tahun 2010 ke atas :");
		
		while(rs.next()) {
			System.out.println("\nID Buku : " + rs.getString(1));
			System.out.println("Nama Buku : " + rs.getString(2));
		}
		
	}
	
	public static void view4() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_buku`.`ID_Buku`,  `tb_buku`.`NM_Buku`,  COUNT(tb_pinjam.ID_Buku) AS jumlah_pinjam "
				+ "FROM  `tb_buku` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "GROUP BY tb_buku.ID_Buku");		
		
		System.out.println("Daftar Buku berapa kali dipinjam");
		
		while(rs.next()) {
			System.out.println("\nID Buku : " + rs.getString(1));
			System.out.println("Nama Buku : " + rs.getString(2));
			System.out.println("Jumlah Pinjam : " + rs.getString(3));
		}
		
	}
	
	public static void view5() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_anggota`.`ID_Anggota`,  `tb_anggota`.`NM_Anggota`, COUNT(tb_pinjam.ID_Anggota) AS jumlah_pinjam "
				+ "FROM  `tb_anggota` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Anggota` = `tb_anggota`.`ID_Anggota` "
				+ "ORDER BY jumlah_pinjam DESC LIMIT 1");	
		
		System.out.println("Daftar anggota paling banyak meminjam buku");
		
		while(rs.next()) {
			System.out.println("ID Anggota : " + rs.getString(1));
			System.out.println("Nama Anggota : " + rs.getString(2));
		}
		
	}
	
	public static void view6() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_anggota`.`ID_Anggota`,  `tb_anggota`.`NM_Anggota`, COUNT(tb_pinjam.ID_Anggota) AS jumlah_pinjam "
				+ "FROM  `tb_anggota` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Anggota` = `tb_anggota`.`ID_Anggota` "
				+ "ORDER BY jumlah_pinjam ASC LIMIT 1");
		
		System.out.println("Daftar anggota paling sedikit meminjam buku");
				
		while(rs.next()) {
			System.out.println("ID Anggota : " + rs.getString(1));
			System.out.println("Nama Anggota : " + rs.getString(2));
		}
		
	}
	
	public static void view7() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_buku`.`ID_Buku`,  `tb_buku`.`NM_Buku`, COUNT(tb_pinjam.ID_Anggota) AS jumlah_pinjam "
				+ "FROM  `tb_buku` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "ORDER BY jumlah_pinjam DESC LIMIT 1");
		
		System.out.println("Daftar Buku yang paling sering di pinjam");
				
		while(rs.next()) {
			System.out.println("ID Buku : " + rs.getString(1));
			System.out.println("Nama Buku : " + rs.getString(2));
		}
		
	}
	
	public static void view8() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_buku`.`ID_Buku`,  `tb_buku`.`NM_Buku`, COUNT(tb_pinjam.ID_Anggota) AS jumlah_pinjam "
				+ "FROM  `tb_buku` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "ORDER BY jumlah_pinjam ASC LIMIT 1");
		
		System.out.println("Daftar Buku yang paling sedikit di pinjam");
				
		while(rs.next()) {
			System.out.println("ID Buku : " + rs.getString(1));
			System.out.println("Nama Buku : " + rs.getString(2));
		}
		
	}
	
	public static void view9() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_anggota`.`ID_Anggota`,  `tb_anggota`.`NM_Anggota`, `tb_buku`.`NM_Buku` "
				+ "FROM  `tb_anggota` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Anggota` = `tb_anggota`.`ID_Anggota` "
				+ "INNER JOIN `tb_buku` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "ORDER BY tb_anggota.NM_Anggota ASC");
		
		System.out.println("Daftar data Anggota beserta buku2 yang pernah dan sedang di pinjam");
		
		while(rs.next()) {
			System.out.println("\nID Anggota : " + rs.getString(1));
			System.out.println("Nama Anggota : " + rs.getString(2));
			System.out.println("Nama Buku : " + rs.getString(3));
		}
		
	}
	
	public static void view10() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_anggota`.`ID_Anggota`,  `tb_anggota`.`NM_Anggota`, `tb_buku`.`NM_Buku` "
				+ "FROM  `tb_anggota` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Anggota` = `tb_anggota`.`ID_Anggota` "
				+ "INNER JOIN `tb_buku` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "WHERE tb_buku.ID_Buku NOT IN (SELECT id_pinjam FROM tb_pinjam) "
				+ "ORDER BY tb_anggota.NM_Anggota ASC");
		
		System.out.println("Daftar data Anggota beserta buku2 yang dipinjam namun belum dikembalikan");
		
		while(rs.next()) {
			System.out.println("\nID Anggota : " + rs.getString(1));
			System.out.println("Nama Anggota : " + rs.getString(2));
			System.out.println("Nama Buku : " + rs.getString(3));
		}
		
	}
	
	public static void view11() throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery("SELECT  `tb_buku`.`Pengarang`,  Count(`tb_pinjam`.`ID_Buku`) AS `jumlah_pinjam` "
				+ "FROM  `tb_buku` INNER JOIN `tb_pinjam` ON `tb_pinjam`.`ID_Buku` = `tb_buku`.`ID_Buku` "
				+ "GROUP BY  `tb_buku`.`Pengarang` "
				+ "ORDER BY  `tb_buku`.`Pengarang` DESC LIMIT 1");
		
		System.out.println("Nama pengarang buku yang sering dipinjam");
		
		while(rs.next()) {
			System.out.println("Pengarang : " + rs.getString(1));
		}
		
	}
	
}

























