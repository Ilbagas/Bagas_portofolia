package showroom;


import java.util.Scanner;



public class main {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        login y = new login();
        admin z = new admin();
        fitur x = new fitur();
        
        while (true) {
            System.out.println("=".repeat(50));
            System.out.println("SELAMAT DATANG DI SHOWROOM GAZOO RACING");
            System.out.println("=".repeat(50));
            System.out.println("Masuk Sebagai : ");
            System.out.println(" ");
            System.out.println("1.Pelanggan");
            System.out.println("2.Admin");
            System.out.print("Enter (1/2): ");
            int decision = input.nextInt();
            if (decision==1) {
                System.out.println("=".repeat(50));
                System.out.println("1.Belum punya akun ?");
                System.out.println("2.Sudah punya akun!");
                System.out.println("=".repeat(50));
                System.out.print("Enter (1/2) : ");
                int decison2 = input.nextInt();
                if (decison2==1) {
                    System.out.println("=".repeat(50));
                    y.insert_data();
                    System.out.println("=".repeat(50));
                }else if (decison2==2) {
                    System.out.println("=".repeat(50));
                    y.signIn();
                    System.out.println();
                    x.fitur();
                }
            }else if (decision==2){
                z.admin();
            }
            
        }
            
    }
}
    
