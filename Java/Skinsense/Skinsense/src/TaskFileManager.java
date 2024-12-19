import java.io.*;
import java.util.ArrayList;


public class TaskFileManager {
    private static final String FILE_PATH = "tasks.txt";


    public static ArrayList<ProductTask> loadTasks() {
        ArrayList<ProductTask> tasks = new ArrayList<>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(";");
                    if (parts.length != 6) {
                        continue;
                    }
                    String namaProd = parts[0];
                    String brandProd= parts[1];
                    String kategoriProd = parts[3];
                    double price = Double.parseDouble(parts[4]);
                    int stok = Integer.parseInt(parts[5]);


                    ProductTask task = new ProductTask(namaProd, brandProd, kategoriProd, price, stok);
                    tasks.add(task);
                } catch (Exception e) {
                    System.err.println("Error parsing task: " + e.getMessage()); 
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Task file not found. A new one will be created.");
        } catch (IOException e) {
            System.err.println("Error reading task file: " + e.getMessage()); 
        }
        return tasks; 
    }


    public static void saveTasks(ArrayList<ProductTask> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ProductTask task : tasks) {
                writer.write(String.format("%s;%s;%s;%s",
                        task.getNamaProd(),
                        task.getBrandProd(),
                        task.getKategoriProd(),
                        task.getPrice(),
                        task.getStok()));
                writer.newLine(); 
            }
        } catch (IOException e) {
            System.err.println("Error writing to task file: " + e.getMessage()); 
        }
    }
}