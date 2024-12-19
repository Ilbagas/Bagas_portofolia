package showroom;

import java.util.Scanner;

public class coba {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan angka pertama : ");
        int x = input.nextInt();

        System.out.print("Masukkan angka kedua : ");
        int y = input.nextInt();
        
        System.out.print("Masukkan angka ketiga : ");
        int z = input.nextInt();

        if (x>=y && x>=z) {
            System.out.println("X adalah terbesar");
        }else if (y>=x && y>=z){
            System.out.println("Y adalah terbesar");
        }else if (z>=y && z>=x){
            System.out.println("Z adalah terbesar");
        }
    }
}