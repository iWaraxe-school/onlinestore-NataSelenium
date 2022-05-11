package utils;

import by.issoft.domain.Product;

import java.util.function.Supplier;

public class ProductFactory
{

    public RandomStorePopulator populator = new RandomStorePopulator();


    public Product getProduct(String category)
    {
        Supplier<Product> productSupplier =
                () -> new Product(category, populator.getProductName(category).replace("'", " "), populator.getProductRate(), populator.getProductPrice());
        return productSupplier.get();
    }
}