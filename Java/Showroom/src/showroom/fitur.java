package showroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class fitur {
    public static void main(String[] args) {
        int decision = 1; // Misalkan decision diatur ke 1 untuk menjalankan blok kode

        // Ganti dengan URL, user, dan password database Anda
        String url = "jdbc:mysql://localhost:3306/toyota";
        String user = "root";
        String password = "";
        
        if (decision == 1) {
            String sql = "SELECT * FROM mobil";
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement x = conn.prepareStatement(sql)) {

                ResultSet rs = x.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();

                int column = metaData.getColumnCount();

                // Menampilkan nama kolom dengan lebar tertentu
                for (int i = 1; i <= column; i++) {
                    System.out.printf("%-15s", metaData.getColumnName(i));
                }
                System.out.println();

                // Menampilkan data baris per baris dengan format terstruktur
                while (rs.next()) {
                    for (int i = 1; i <= column; i++) {
                        System.out.printf("%-15s", rs.getString(i));
                    }
                    System.out.println();
                }

            } catch (Exception e) {
                e.printStackTrace(); // Menampilkan pesan error jika terjadi kesalaha
            }
        }
    }

}
    


   
