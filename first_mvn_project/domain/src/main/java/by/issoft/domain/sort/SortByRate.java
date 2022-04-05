package by.issoft.domain.sort;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortByRate implements Comparator<Product> {

    @Override
    public int compare(Product prod1, Product prod2) {
        if (prod1.getRate() == prod2.getRate())
        return 0;
        if (prod1.getRate() > prod2.getRate())
            return 1;
        else {
            return -1;
        }
    }
}
