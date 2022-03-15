package by.issoft.domain;

import com.github.javafaker.Cat;

import java.util.List;

public class Category {
    public String categoryName;
    public List<Product> productList;

    public Category(String categoryName)
    {
        this.categoryName = categoryName;
    }
public Category()
{

}
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addProduct(Product product)
    {
        final boolean add = productList.add(product);
    }

    public String printProductInfo(Product product)
    {
        return getCategoryName() +
            product.getName() + " " +
            product.getRate() + " " +
            product.getPrice();
    }
}
