package com.permadi.main;

//import scanner
import java.util.Scanner;

// import dbakses
import com.permadi.dbakses.DatabaseAkses;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//	scanner option perintah		
		Scanner input = new Scanner(System.in);
		System.out.println("0. View anggota yang telah meminjam buku");
		System.out.println("1. Soal Nomor 1");
		System.out.println("2. Soal Nomor 2");
		System.out.println("3. Soal Nomor 3");
		System.out.println("4. Soal Nomor 4");
		System.out.println("5. Soal Nomor 5");
		System.out.println("6. Soal Nomor 6");
		System.out.println("7. Soal Nomor 7");
		System.out.println("8. Soal Nomor 8");
		System.out.println("9. Soal Nomor 9");
		System.out.println("10. Soal Nomor 10");
		System.out.println("11. Soal Nomor 11");
		System.out.print("Masukkan Perintah SQL : ");
		
		//	menangkap int perintah		
		int p = input.nextInt();
		//	object baru database akses
		DatabaseAkses db = new DatabaseAkses();
		//	switch case berdasarkan nilai p (nilai yang user pilih)		
		switch (p) {
		//	case 1 insert pelanggan
		
		case 0:
			try {
				//	view tabel station
				db.view();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case 1:
			try {
				db.view1();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 2:
			try {
				db.view2();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 3:
			try {
				db.view3();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 4:
			try {
				db.view4();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 5:
			try {
				db.view5();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 6:
			try {
				db.view6();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 7:
			try {
				db.view7();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case 8:
			try {
				db.view8();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case 9:
			try {
				db.view9();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 10:
			try {
				db.view10();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case 11:
			try {
				db.view11();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		//	jika user memasukkan nilai selain yang ditentukan
		default:
			System.out.println("perintah tidak terdaftar");
			break;
		}
	
	}

}
