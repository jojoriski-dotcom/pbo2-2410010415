package perpustakaanmini;

import java.util.ArrayList;
import java.util.Scanner;

public class PerpustakaanMini {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<String> daftarBuku = new ArrayList<>();

        int pilihan;

        do {
            System.out.println("\n================================");
            System.out.println("       PERPUSTAKAAN MINI");
            System.out.println("================================");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Daftar Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    System.out.print("Masukkan judul buku: ");
                    String judul = input.nextLine();

                    daftarBuku.add(judul);

                    System.out.println("Buku berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.println("\nDAFTAR BUKU");

                    if (daftarBuku.isEmpty()) {
                        System.out.println("Belum ada buku.");
                    } else {
                        for (int i = 0; i < daftarBuku.size(); i++) {
                            System.out.println((i + 1) + ". " + daftarBuku.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Masukkan judul buku: ");
                    String cari = input.nextLine();

                    boolean ditemukan = false;

                    for (String buku : daftarBuku) {
                        if (buku.toLowerCase().contains(cari.toLowerCase())) {
                            System.out.println("Buku ditemukan: " + buku);
                            ditemukan = true;
                        }
                    }

                    if (!ditemukan) {
                        System.out.println("Buku tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan Perpustakaan Mini!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 4);

        input.close();
    }
}