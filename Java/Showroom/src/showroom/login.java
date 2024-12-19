package showroom;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login {
    Scanner input = new Scanner(System.in);
    public void insert_data(){
        String url = "jdbc:mysql://localhost:3306/toyota";
        String user = "root";
        String password = "";

        System.out.println("Silahkan masukkan data anda!");
        System.out.println();
        String sql = "INSERT INTO username(ID_pelanggan,email,nama,no_telp,alamat,password) VALUES (?, ?, ?, ?, ?, ?)";
                        
        try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement prepare = conn.prepareStatement(sql)){
            System.out.print("Masukkan ID anda : ");
            String ID_pelanggan = input.nextLine();

            System.out.print("Masukkan email anda : ");
            String email = input.nextLine();

            System.out.print("Masukkan nama anda : ");
            String nama = input.nextLine();

            System.out.print("Masukkan nomor telepon anda : ");
            String no_telp = input.nextLine();
            
            System.out.print("Masukkan alamat anda : ");
            String alamat = input.nextLine();

            System.out.print("Masukkan Password anda : ");
            String pass = input.nextLine();

            prepare.setString(1, ID_pelanggan);
            prepare.setString(2, email);
            prepare.setString(3, nama);
            prepare.setString(4, no_telp);
            prepare.setString(5, alamat);
            prepare.setString(6, pass);

            prepare.executeUpdate();
            System.out.println();
            System.out.println("Selamat datang "+nama);

        } catch (Exception e) {
            System.out.println("error"+e.getMessage());
        }              
                                

    }
    public void signIn(){
        String url = "jdbc:mysql://localhost:3306/toyota";
        String user = "root";
        String password = "";

        System.out.print("Masukkan username anda : ");
        String username = input.nextLine();
        System.out.print("Masukkan Password anda : ");
        String pass = input.nextLine();
        System.out.println("=".repeat(50));
        String sql = "SELECT * FROM username WHERE ID_pelanggan='"+username+"' AND password='"+pass+"'";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement x = conn.prepareStatement(sql)){
                ResultSet rs = x.executeQuery();
                if (rs.next()){
                    System.out.println("Hello "+username+"!");
                }else{
                    System.out.println("Username dan Password anda salah");
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        login x = new login();
        x.signIn();
    }
}
    