package dbcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCRUD {

    private Connection koneksi;

    public DBCRUD() {

        try {
            koneksi = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_pbo2_2410010415",
                "root",
                ""
            );

        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public boolean simpanDriver(
        String id,
        String nama,
        String lisensi,
        String kontak,
        String kapal
    ) {

        String sql = "INSERT INTO drivers "
                   + "(id_driver, nama_driver, no_lisensi, kontak, id_kapal) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, nama);
            ps.setString(3, lisensi);
            ps.setString(4, kontak);
            ps.setString(5, kapal);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Gagal menyimpan: " + e.getMessage());
            return false;
        }
    }

    public void tutupKoneksi() {

        try {
            if (koneksi != null) {
                koneksi.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}