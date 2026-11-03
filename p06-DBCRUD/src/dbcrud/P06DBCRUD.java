package dbcrud;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class P06DBCRUD extends JFrame {

    JTextField txtId, txtNama, txtLisensi;
    JTextField txtKontak, txtKapal;

    public P06DBCRUD() {

        setTitle("P06 - Perbandingan DB CRUD");
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
        JButton btnCRUD = new JButton("Simpan DB CRUD");
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
        panel.add(btnCRUD);
        panel.add(btnBersihkan);

        add(panel);

        btnManual.addActionListener(e -> simpanManual());

        btnCRUD.addActionListener(e -> simpanCRUD());

        btnBersihkan.addActionListener(e -> bersihkan());
    }

    // Penyimpanan menggunakan Statement secara manual
    public void simpanManual() {

        if (!validasi()) return;

        String url = "jdbc:mysql://localhost:3306/db_pbo2_2410010415";

        String id = txtId.getText().trim();
        String nama = txtNama.getText().trim();
        String lisensi = txtLisensi.getText().trim();
        String kontak = txtKontak.getText().trim();
        String kapal = txtKapal.getText().trim();

        String sql = "INSERT INTO drivers VALUES ('"
                   + id + "', '"
                   + nama + "', '"
                   + lisensi + "', '"
                   + kontak + "', '"
                   + kapal + "')";

        try (Connection conn =
                 DriverManager.getConnection(url, "root", "");

             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);

            JOptionPane.showMessageDialog(
                this, "Data berhasil disimpan secara manual!"
            );

            bersihkan();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this, "Gagal: " + e.getMessage()
            );
        }
    }

    // Penyimpanan menggunakan class DBCRUD
    public void simpanCRUD() {

        if (!validasi()) return;

        DBCRUD db = new DBCRUD();

        boolean berhasil = db.simpanDriver(
            txtId.getText().trim(),
            txtNama.getText().trim(),
            txtLisensi.getText().trim(),
            txtKontak.getText().trim(),
            txtKapal.getText().trim()
        );

        db.tutupKoneksi();

        if (berhasil) {

            JOptionPane.showMessageDialog(
                this, "Data berhasil disimpan melalui DB CRUD!"
            );

            bersihkan();

        } else {

            JOptionPane.showMessageDialog(
                this, "Data gagal disimpan!"
            );
        }
    }

    // Validasi input
    public boolean validasi() {

        if (txtId.getText().trim().isEmpty()
            || txtNama.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "ID Driver dan Nama Driver wajib diisi!"
            );

            return false;
        }

        return true;
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
            new P06DBCRUD().setVisible(true);
        });
    }
}