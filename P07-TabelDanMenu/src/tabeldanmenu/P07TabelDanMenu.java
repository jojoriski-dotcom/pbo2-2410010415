package tabeldanmenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class P07TabelDanMenu extends JFrame {

    JTable tabel;
    DefaultTableModel model;

    public P07TabelDanMenu() {

        setTitle("P07 - Tabel dan Menu");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat menu
        JMenuBar menuBar = new JMenuBar();

        JMenu menuData = new JMenu("Data");
        JMenuItem itemRefresh = new JMenuItem("Refresh Data");

        JMenu menuKeluar = new JMenu("Keluar");
        JMenuItem itemExit = new JMenuItem("Tutup Aplikasi");

        menuData.add(itemRefresh);
        menuKeluar.add(itemExit);

        menuBar.add(menuData);
        menuBar.add(menuKeluar);

        setJMenuBar(menuBar);

        // Membuat tabel
        String[] kolom = {
            "ID Driver",
            "Nama Driver",
            "No Lisensi",
            "Kontak",
            "ID Kapal"
        };

        model = new DefaultTableModel(kolom, 0);
        tabel = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tabel);

        JButton btnRefresh = new JButton("Refresh Data");

        JPanel panelBawah = new JPanel();
        panelBawah.add(btnRefresh);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        // Aksi tombol
        btnRefresh.addActionListener(e -> tampilData());

        itemRefresh.addActionListener(e -> tampilData());

        itemExit.addActionListener(e -> dispose());

        tampilData();
    }

    // Method mengambil data dari database
    public void tampilData() {

        model.setRowCount(0);

        String url =
            "jdbc:mysql://localhost:3306/db_pbo2_2410010415";

        String sql = "SELECT * FROM drivers";

        try (
            Connection conn =
                DriverManager.getConnection(url, "root", "");

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Object[] data = {
                    rs.getString("id_driver"),
                    rs.getString("nama_driver"),
                    rs.getString("no_lisensi"),
                    rs.getString("kontak"),
                    rs.getString("id_kapal")
                };

                model.addRow(data);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Gagal mengambil data: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new P07TabelDanMenu().setVisible(true);
        });
    }
}