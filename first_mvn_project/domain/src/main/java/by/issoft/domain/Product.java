package by.issoft.domain;

//import java.text.DecimalFormat;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
