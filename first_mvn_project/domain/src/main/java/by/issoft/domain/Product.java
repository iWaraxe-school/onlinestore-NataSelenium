package by.issoft.domain;


public class Product {
    private String name;
    private int rate;
    private Double price;
    private String category;

    public Product(String name, int rate, Double price)
    {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public Product(String categoryName, String name, int rate, Double price)
    {
        this.category = categoryName;
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public Double getPrice() {
        return price;
    }

    public void printProductInformation(Product product)
    {
        String info = "Category: " + category + ", Name: " +
                product.getName() + ", Rate: " +
                product.getRate() + ", Price:  " +
                product.getPrice();
        System.out.println(info);
    }
}
