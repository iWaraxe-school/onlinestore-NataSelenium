package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public String categoryName;
    public List<Product> productList;

    public Category(String categoryName)
    {

        this.categoryName = categoryName;
        productList = new ArrayList<Product>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void addProduct(Product product)
    {
        productList.add(product);
    }

    public List<Product> getProductList() {return productList;}

    public void printProductInfo(Product product)
    {
        String info = "Category: " + getCategoryName() + ", Name: " +
            product.getName() + ", Rate: " +
            product.getRate() + ", Price:  " +
            product.getPrice();
        System.out.println(info);
    }
}
