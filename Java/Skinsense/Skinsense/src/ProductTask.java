public class ProductTask {
    private String namaProd,brandProd,kategoriProd;
    private int stok;
    private double price;

    public ProductTask(String namaProd, String brandProd, String kategoriProd, double price, int stok){
        this.namaProd = namaProd;
        this.brandProd = brandProd;
        this.kategoriProd = kategoriProd;
        this.price = price;
        this.stok = stok;
    }
    public String getNamaProd() {
        return namaProd;
    }

    public String getBrandProd() {
        return brandProd;
    }

    public String getKategoriProd() {
        return kategoriProd;
    }

    public double getPrice() {
        return price;
    }

    public int getStok() {
        return stok;
    }
}
