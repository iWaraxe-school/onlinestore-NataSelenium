package by.issoft.domain;


public class Product {
    private String name;
    private int rate;
    private Double price;

    public Product(String name, int rate, Double price)
    {
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

}
