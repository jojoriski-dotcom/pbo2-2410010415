package p05.configdb;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class P05ConfigDB extends JFrame {

    JTextField txtId, txtNama, txtLisensi;
    JTextField txtKontak, txtKapal;

    public P05ConfigDB() {

        setTitle("P05 - Perbandingan Koneksi Database");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel judul = new JLabel("DATA DRIVER");
        judul.setFont(new Font("Arial", Font.BOLD, 20));

        txtId = new JTextField();
        txtNama = new JTextField();
        txtLisensi = new JTextField();
        txtKontak = new JTextField();
        txtKapal = new JTextField();

        JButton btnManual = new JButton("Simpan Manual");
        JButton btnConfig = new JButton("Simpan ConfigDB");
        JButton btnBersihkan = new JButton("Bersihkan");

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));

        panel.add(judul);
        panel.add(new JLabel(""));
        panel.add(new JLabel("ID Driver"));
        panel.add(txtId);
        panel.add(new JLabel("Nama Driver"));
        panel.add(txtNama);
        panel.add(new JLabel("No Lisensi"));
        panel.add(txtLisensi);
        panel.add(new JLabel("Kontak"));
        panel.add(txtKontak);
        panel.add(new JLabel("ID Kapal"));
        panel.add(txtKapal);
        panel.add(btnManual);
        panel.add(btnConfig);
        panel.add(btnBersihkan);

        add(panel);

        btnManual.addActionListener(e -> simpanManual());

        btnConfig.addActionListener(e -> simpanConfigDB());

        btnBersihkan.addActionListener(e -> bersihkan());
    }

    // Koneksi manual langsung di dalam JFrame
    public void simpanManual() {

        String url = "jdbc:mysql://localhost:3306/db_pbo2_2410010415";
        String user = "root";
        String pass = "";

        try (Connection conn =
                 DriverManager.getConnection(url, user, pass)) {

            simpanData(conn);

        } catch (SQLException e) {

            tampilError(e);
        }
    }

    // Koneksi menggunakan class ConfigDB
    public void simpanConfigDB() {

        try (Connection conn = ConfigDB.getConnection()) {

            simpanData(conn);

        } catch (SQLException e) {

            tampilError(e);
        }
    }

    // Method untuk menyimpan data
    public void simpanData(Connection conn) throws SQLException {

        String id = txtId.getText().trim();
        String nama = txtNama.getText().trim();
        String lisensi = txtLisensi.getText().trim();
        String kontak = txtKontak.getText().trim();
        String kapal = txtKapal.getText().trim();

        if (id.isEmpty() || nama.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "ID dan Nama Driver wajib diisi!"
            );
            return;
        }

        String sql = "INSERT INTO drivers "
                   + "(id_driver, nama_driver, no_lisensi, kontak, id_kapal) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, nama);
            ps.setString(3, lisensi);
            ps.setString(4, kontak);
            ps.setString(5, kapal);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                this, "Data berhasil disimpan!"
            );

            bersihkan();
        }
    }

    public void tampilError(SQLException e) {

        JOptionPane.showMessageDialog(
            this, "Gagal menyimpan data: " + e.getMessage()
        );
    }

    public void bersihkan() {

        txtId.setText("");
        txtNama.setText("");
        txtLisensi.setText("");
        txtKontak.setText("");
        txtKapal.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new P05ConfigDB().setVisible(true);
        });
    }
}