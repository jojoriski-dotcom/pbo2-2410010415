package komponengui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class P03KomponenGUI extends JFrame {

    JTextField txtKode, txtJudul;
    JTextArea areaData;

    ArrayList<String> daftarKode = new ArrayList<>();

    public P03KomponenGUI() {

        setTitle("P03 - Komponen GUI");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblJudul = new JLabel("DATA BUKU PERPUSTAKAAN");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblKode = new JLabel("Kode Buku");
        JLabel lblNama = new JLabel("Judul Buku");

        txtKode = new JTextField();
        txtJudul = new JTextField();

        JButton btnSimpan = new JButton("Simpan");
        JButton btnBersihkan = new JButton("Bersihkan");

        areaData = new JTextArea();
        areaData.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(lblJudul);
        panel.add(new JLabel(""));
        panel.add(lblKode);
        panel.add(txtKode);
        panel.add(lblNama);
        panel.add(txtJudul);
        panel.add(btnSimpan);
        panel.add(btnBersihkan);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(areaData), BorderLayout.CENTER);

        btnSimpan.addActionListener(e -> simpanData());

        btnBersihkan.addActionListener(e -> {
            txtKode.setText("");
            txtJudul.setText("");
        });
    }

    // Method untuk mengecek kode buku yang duplikat
    public boolean duplicateKey(String kode) {

        return daftarKode.contains(kode.toLowerCase());
    }

    // Method untuk menyimpan data buku
    public void simpanData() {

        String kode = txtKode.getText().trim();
        String judul = txtJudul.getText().trim();

        if (kode.isEmpty() || judul.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Data tidak boleh kosong!"
            );
            return;
        }

        if (duplicateKey(kode)) {
            JOptionPane.showMessageDialog(
                this, "Kode buku sudah terdaftar!"
            );
            return;
        }

        daftarKode.add(kode.toLowerCase());

        areaData.append(
            "Kode Buku : " + kode +
            "\nJudul Buku : " + judul +
            "\n--------------------------\n"
        );

        JOptionPane.showMessageDialog(
            this, "Data berhasil disimpan!"
        );

        txtKode.setText("");
        txtJudul.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new P03KomponenGUI().setVisible(true);
        });
    }
}