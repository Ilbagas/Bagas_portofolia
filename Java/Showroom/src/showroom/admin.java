package showroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class admin {
      public void admin(){
        String url = "jdbc:mysql://localhost:3306/toyota";
        String user = "root";
        String password = "";
        Scanner input = new Scanner(System.in);
        String passwordbenar = "toyota";
        int maxtry = 5;
        boolean benar = false;

        System.out.println("=".repeat(50));
        System.out.println("Welcome to admin menu!");
        System.out.println("=".repeat(50));
        for (int peluang = 0;peluang<=maxtry; peluang++){
            System.out.print("Please enter admin password : ");
            String password1 = input.nextLine();
            if (password1.equals(passwordbenar)){
                benar = true;
                break;
            }else if (peluang==maxtry){
                System.out.println("Login anda gagal coba lagi lain kali !");
                break;
            }else if (peluang<=maxtry){
                System.out.println("Masukkan password anda yang benar !");
            }
        }
        if (benar == true){
            while (true) {
                System.out.println("=".repeat(50));
                System.out.println("Menu :");
                System.out.println("1. Masukkan data");
                System.out.println("2. Hapus data");
                System.out.println("3. Update data");
                System.out.println("4. Pencarian data");
                
                System.out.print("Masukkan pilihan : ");
                int decision = input.nextInt();
                
                if (decision==1){
                    System.out.println("1.Data Pelanggan : ");
                    System.out.println("2.Data Mobil");
                    System.out.println("3.Data Kredit");
                    System.out.print("Masukkan pilihan menu : ");
                    
                    int decision2 = input.nextInt();
                    input.nextLine();

                    if (decision2 == 1 ){
                        String sql = "INSERT INTO username(ID_pelanggan,email,nama,no_telp,alamat,password) VALUES (?, ?, ?, ?, ?, ?)";
                        
                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement prepare = conn.prepareStatement(sql)){
                            
                                System.out.print("Masukkan ID pelanggan : ");
                                String ID_pelanggan = input.nextLine();

                                System.out.println("Masukkan email : ");
                                String email = input.nextLine();

                                System.out.print("Masukkan nama pelanggan : ");
                                String nama = input.nextLine();

                                System.out.print("Masukkan nomor telepon pelanggan : ");
                                String no_telp = input.nextLine();

                                System.out.print("Masukkan alamat pelanggan : ");
                                String alamat = input.nextLine();

                                System.out.println("Masukkan Password : ");
                                String pass = input.nextLine();

                                prepare.setString(1, ID_pelanggan);
                                prepare.setString(2, email);
                                prepare.setString(3, nama);
                                prepare.setString(4, no_telp);
                                prepare.setString(5, alamat);
                                prepare.setString(6, pass);

                                int rows = prepare.executeUpdate();
                                System.out.println("Data pelanggan berhasil ditambahkan "+rows);

                        } catch (Exception e) {
                             System.out.println("error"+e.getMessage());
                        }
                    }else if (decision2 == 2){
                        String sql = "INSERT INTO mobil(ID_mobil,model,tahun,spesifikasi,link_review)VALUES(?, ?, ?, ?, ?)";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                                PreparedStatement prepare = conn.prepareStatement(sql)){
                                    
                                    System.out.print("Masukkan ID mobil : ");
                                    String ID_mobil = input.nextLine();
                                    
                                    System.out.print("Masukkan model mobil : ");
                                    String model = input.nextLine();

                                    System.out.print("Masukkan tahun mobil : ");
                                    int tahun = input.nextInt();
                                    input.nextLine();

                                    System.out.print("Masukkan spesifikasi mobil : ");
                                    String spesifikasi = input.nextLine();

                                    System.out.print("Masukkan Link review : ");
                                    String link_review = input.nextLine();

                                    prepare.setString(1, ID_mobil);
                                    prepare.setString(2, model);
                                    prepare.setInt(3, tahun);
                                    prepare.setString(4, spesifikasi);
                                    prepare.setString(5, link_review);

                                    prepare.executeUpdate();

                                    System.out.println("Data anda sukses terinput!");
                            
                        } catch (Exception e) {
                            System.out.println("Error "+e.getMessage());
                        }
                    }else if (decision2 == 3 ){
                        String sql = "INSERT INTO kredit(ID_kredit,ID_pelanggan,harga,dp,jangka_waktu,opsi_pembayaran,ID_mobil) VALUES (?, ?, ?, ?, ?, ?, ?)";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.print("Masukkan ID_kredit pelanggan : ");
                                String ID_kredit = input.nextLine();

                                System.out.print("Masukkan ID_pelanggan : ");
                                String ID_pelanggan = input.nextLine();

                                System.out.print("Masukkan harga mobil : ");
                                int harga = input.nextInt();

                                System.out.print("Masukkan uang muka pemesanan : ");
                                int dp = input.nextInt();
                                input.nextLine();

                                System.out.print("Masukkan jangka waktu : ");
                                String jangka_waktu = input.nextLine();
                                

                                System.out.print("Masukkan opsi pembayaran Transfer/Debit : ");
                                String opsi_pembayaran = input.nextLine();

                                System.out.print("Masukkan ID_mobil yang akan di beli : ");
                                String ID_mobil = input.nextLine();

                                x.setString(1, ID_kredit);
                                x.setString(2, ID_pelanggan);
                                x.setInt(3, harga);
                                x.setInt(4, dp);
                                x.setString(5, jangka_waktu);
                                x.setString(6, opsi_pembayaran);
                                x.setString(7, ID_mobil);

                                x.executeUpdate();
                                System.out.println("Data anda sukses di update!");
                            
                        } catch (Exception e) {
                            System.out.println("Error "+e.getMessage());
                        }
                    }
                }else if (decision == 2){
                    System.out.println("1.Data Pelanggan : ");
                    System.out.println("2.Data Mobil");
                    System.out.println("3.Data Kredit"); 
                    System.out.print("Masukkan pilihan menu : ");
                    int decision2 = input.nextInt();
                    input.nextLine();

                    if (decision2 == 1){
                        String sql = "DELETE FROM username WHERE nama LIKE ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.print("Masukkan nama yang ingin di hapus : ");
                                String nama = input.nextLine();

                                x.setString(1,nama );

                                x.executeUpdate();
                                System.out.println("Pelanggan dengan nama "+nama+" berhasil di hapus");
                            
                        } catch (Exception e) {
                            System.out.println("Error "+e.getMessage());
                        }
                    }else if (decision2 == 2){
                        String sql = "DELETE FROM mobil WHERE model LIKE ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.print("Masukkan model mobil yang akan di hapus : ");
                                String model = input.nextLine();

                                x.setString(1, model);

                                x.executeUpdate();
                                System.out.println("Model mobil "+ model + " berhasil di hapus");
                            
                        } catch (Exception e) {
                            System.out.println("error"+ e.getMessage());
                        }
                    }else if (decision2 == 3 ){
                        String sql = "DELETE FROM kredit WHERE ID_kredit = ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.print("Masukkan ID kredit yang akan di hapus : ");
                                String ID_kredit = input.nextLine();

                                x.setString(1, ID_kredit);
                                x.executeUpdate();

                                System.out.println("Data kredit dari "+ ID_kredit +" berhasil di hapus");
                        } catch (Exception e) {
                            System.out.println("error"+ e.getMessage());
                        }
                    }
                }else if (decision == 3){
                    System.out.println("1.Data Pelanggan : ");
                    System.out.println("2.Data Mobil");
                    System.out.println("3.Data Kredit");
                    System.out.print("Masukkan pilihan menu : ");
                    int decision2 = input.nextInt();
                    input.nextLine();
                    if (decision2==1) {
                        String sql = "UPDATE username SET email= ?,nama = ?,no_telp = ?,alamat = ?,password = ? WHERE ID_pelanggan = ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.print("Masukkan ID pelanggan : ");
                                String ID_pelanggan = input.nextLine();

                                System.out.println("Masukkan email anda : ");
                                String email = input.nextLine();

                                System.out.print("Masukkan nama pelanggan : ");
                                String nama = input.nextLine();

                                System.out.print("Masukkan nomor telepon pelanggan : ");
                                String no_telp = input.nextLine();

                                System.out.print("Masukkan alamat pelanggan : ");
                                String alamat = input.nextLine();

                                System.out.println("Masukkan Password anda : ");
                                String pass = input.nextLine();

                                x.setString(1, nama);
                                x.setString(2, email);
                                x.setString(3, no_telp);
                                x.setString(4, alamat);
                                x.setString(5,ID_pelanggan );
                                x.setString(6, pass);

                                x.executeUpdate();

                                System.out.println("berhasil di ubah");

                            
                        } catch (Exception e) {
                            System.out.println("error "+ e.getMessage());
                        }
                    }else if (decision2 == 2 ){
                        String sql = "UPDATE mobil SET model = ?,tahun = ?,spesifikasi = ?,link_review = ? WHERE ID_mobil =?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.println("Masukkan ID mobil yang akan dirubah : ");
                                String ID_mobil = input.nextLine();

                                System.out.println("Masukkan nama model : ");
                                String model = input.nextLine();

                                System.out.println("Masukkan tahun keluaran mobil : ");
                                int tahun = input.nextInt();
                                input.nextLine();

                                System.out.println("Masukkan Spesifikasi mobil : ");
                                String spesifikasi = input.nextLine();

                                System.out.println("Masukkan link review mobil : ");
                                String link_review = input.nextLine();

                                x.setString(1, model);
                                x.setInt(2,tahun );
                                x.setString(3, spesifikasi);
                                x.setString(4, link_review);
                                x.setString(5, ID_mobil);

                                x.executeUpdate();
                                System.out.println("Data "+ID_mobil+" berhasi diperbarui");

                        } catch (Exception e) {
                            System.out.println("Error "+e.getMessage());
                        }

                    }else if (decision2 == 3){
                        String sql = "UPDATE kredit SET ID_pelanggan = ?,harga = ?,dp = ?,jangka_waktu = ?,opsi_pembayaran = ?,ID_mobil = ? WHERE ID_kredit = ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){
                                
                                System.out.print("Masukkan ID_kredit pelanggan : ");
                                String ID_kredit = input.nextLine();

                                System.out.print("Masukkan ID_pelanggan : ");
                                String ID_pelanggan = input.nextLine();

                                System.out.print("Masukkan harga mobil : ");
                                int harga = input.nextInt();

                                System.out.print("Masukkan uang muka pemesanan : ");
                                int dp = input.nextInt();
                                input.nextLine();

                                System.out.print("Masukkan jangka waktu : ");
                                String jangka_waktu = input.nextLine();
                                
                                System.out.print("Masukkan opsi pembayaran Transfer/Debit : ");
                                String opsi_pembayaran = input.nextLine();

                                System.out.print("Masukkan ID_mobil yang akan di beli : ");
                                String ID_mobil = input.nextLine();

                                x.setString(1, ID_pelanggan);
                                x.setInt(2, harga);
                                x.setInt(3, dp);
                                x.setString(4, jangka_waktu);
                                x.setString(5, opsi_pembayaran);
                                x.setString(6, ID_mobil);
                                x.setString(7, ID_kredit);

                                x.executeUpdate();
                                System.out.println("Data kredit "+ ID_kredit+" berhasil diperbarui");


                        } catch (Exception e) {
                            System.out.println("Error "+ e.getMessage());
                        }
                    }
                }else if (decision == 4 ){
                    System.out.println("1.Data Pelanggan : ");
                    System.out.println("2.Data Mobil");
                    System.out.println("3.Data Kredit");
                    System.out.print("Masukkan pilihan menu : ");

                    int decision2 = input.nextInt();
                    input.nextLine();

                    if (decision2 == 1 ) {
                        String sql = "SELECT * FROM username WHERE ID_pelanggan =  ?";
                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){

                                System.out.print("Masukkan nama yang dicari : ");
                                String ID_pelanggan = input.nextLine();

                                x.setString(1, ID_pelanggan);
                                ResultSet rs = x.executeQuery();

                                while (rs.next()) {
                                    System.out.println("ID pelanggan: " + rs.getString("ID_pelanggan") + ", Nama pelanggan : " + rs.getString("nama")+",Nomor Telepon : "+rs.getString("no_telp")+", Alamat : "+rs.getString("alamat"));
                                }
                                rs.close();
                                x.close();
                                conn.close();
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (decision2 == 2){
                        String sql = "SELECT * FROM mobil WHERE ID_mobil = ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){
                                System.out.print("Masukkan ID_mobil yang akan di cari : ");
                                String ID_mobil = input.nextLine();

                                x.setString(1, ID_mobil);
                                ResultSet rs = x.executeQuery();
                                
                                while (rs.next()) {
                                    System.out.println("ID_mobil : "+rs.getString("ID_mobil")+" Model : "+rs.getString("model")+" Tahun : "+rs.getInt("tahun")+" Spesifikasi : "+rs.getString("spesifikasi")+" Link Youtube : "+rs.getString("link_review"));
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (decision2 == 3){
                        String sql = "SELECT * FROM kredit WHERE ID_kredit = ?";

                        try (Connection conn = DriverManager.getConnection(url, user, password);
                            PreparedStatement x = conn.prepareStatement(sql)){
                                System.out.print("Masukkan ID_kredit anda : ");
                                String ID_kredit = input.nextLine();

                                x.setString(1, ID_kredit);
                                ResultSet rs = x.executeQuery();

                                while (rs.next()) {
                                    System.out.println("ID_kredit : "+rs.getString("ID_kredit")+" ID_pelanggan : "+rs.getString("ID_pelanggan")+" Harga mobil : "+rs.getInt("harga")+" Dp mobil : "+rs.getInt("dp")+" Opsi Pembayaran : "+rs.getString("opsi_pembayaran")+" ID mobil : "+rs.getString("ID_mobil"));
                                }
                                rs.close();
                                x.close();
                                conn.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}

