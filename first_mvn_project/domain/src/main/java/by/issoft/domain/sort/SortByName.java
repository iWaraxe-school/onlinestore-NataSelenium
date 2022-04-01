package by.issoft.domain.sort;

import by.issoft.domain.Product;

import java.util.Comparator;

public class SortByName implements Comparator<Product> {

    @Override
    public int compare(Product prod1, Product prod2) {
        return prod1.getName().compareTo(prod2.getName());
    }
}
