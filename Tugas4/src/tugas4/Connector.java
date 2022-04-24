package tugas4;

import java.sql.*;

public class Connector {
    String DBurl = "jdbc:mysql://localhost/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";
    
    Connection koneksi;
    Statement statement;
    static final String driver = "com.mysql.cj.jdbc.Driver";
    public Connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            System.out.println("Koneksi gagal");
        }
    }
    
    
    
}
