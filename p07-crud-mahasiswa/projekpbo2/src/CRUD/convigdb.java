/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;
    import java.sql.Connection;
    import java.sql.Driver;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class convigdb {
    private String user="root";
    private String pass="";
    private String urldb="jdbc:mysql://localhost/praktekpbo2";
    private Connection Koneksi;
    
    public convigdb(){
        try {
            Driver dbDriver=new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbDriver);
            this.Koneksi = DriverManager.getConnection(urldb,user,pass);
            System.out.print("Database Berhasil Dikoneksikan!");
        } catch (Exception e) {
        }
 
    }
}
