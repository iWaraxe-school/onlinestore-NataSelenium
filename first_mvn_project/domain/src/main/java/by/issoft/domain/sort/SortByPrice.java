package by.issoft.domain.sort;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortByPrice implements Comparator<Product> {

    @Override
    public int compare(Product prod1, Product prod2) {
        double delta = prod1.getPrice() - prod2.getPrice();
            if(delta > 0.00001) return 1;
            if(delta < -0.00001) return -1;
           return 0;
    }
}
