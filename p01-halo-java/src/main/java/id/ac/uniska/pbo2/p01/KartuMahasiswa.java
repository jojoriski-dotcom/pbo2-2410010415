package id.ac.uniska.pbo2.p01;

public class KartuMahasiswa {
    // Sesuaikan nama, prodi, dan alasan kamu sendiri di sini
    public String nama = "M. Rizky Ridho";
    public String npm = "2410010415";
    public String prodi = "Teknik Informatika";
    public int semester = 5;
    public String alasan = "Ngoding seru.";
    
    public KartuMahasiswa() {}
    
    public void tampilkanKartu() {
        System.out.println("=================================");
        System.out.println("KARTU MAHASISWA PBO 2");
        System.out.println("=================================");
        System.out.println("Nama        : " + nama);
        System.out.println("NPM         : " + npm);
        System.out.println("Prodi       : " + prodi);
        System.out.println("Semester    : " + semester);
        System.out.println("Alasan      : " + alasan);
        System.out.println("=================================");
    }
}