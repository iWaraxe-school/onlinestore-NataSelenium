package store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import utils.RandomStorePopulator;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private Map<Category,Integer> categoryProductsMap = new HashMap<>();

    public Store() {}

    public Map<Category, Integer> fillStoreWithProducts(Map<Category, Integer> categoryProductsMap)
    {
        RandomStorePopulator populate = new RandomStorePopulator();
        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet())
        {
            for (int i = 0; i < entry.getValue(); i++)
            {
                Product product = new Product(
                        populate.getProductName(entry.getKey().getCategoryName()),
                        populate.getProductRate(),
                        populate.getProductPrice());
                        entry.getKey().addProduct(product);
            }
        }
        return categoryProductsMap;
    }
}
