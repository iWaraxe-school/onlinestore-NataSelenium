package utils;

import SQL.SQLqueries;
import by.issoft.domain.Category;
import by.issoft.domain.Product;
import store.Store;

import java.util.*;

public class StoreHelper {

    static Store store;
    private static StoreHelper instance;
    List<Product> allProductList;
    ProductFactory productFactory = new ProductFactory();

    private StoreHelper(Store store)
    {
        this.store = store;
    }

    public static StoreHelper getInstance(Store store)
    {
        if (instance == null)
        {
            instance = new StoreHelper(store);
        }
        return instance;
    }

    public void printStoreProductsList(List<Product> printList)
    {
        for (Product product: printList)
        {
            product.printProductInformation(product);
        }
    }

    public List<Product> getAllProductsStoreList()
    {
       allProductList = new ArrayList<Product>();
        List<Product> items = null;
        Map<Category,Integer> unsortedMap = fillStoreWithProducts();

        for (Map.Entry<Category, Integer> entry : unsortedMap.entrySet())
        {
            for (int i = 0; i < entry.getValue(); i++)
            {
                items = entry.getKey().getProductList();
                allProductList.add(new Product(entry.getKey().getCategoryName(),
                        items.get(i).getName(),
                        items.get(i).getRate(),
                        items.get(i).getPrice()
                ));
            }
        }

        return allProductList;
    }

    public Map<Category,Integer> fillStoreWithProducts()
    {
        Map<Category,Integer> categoryProductsMap = createProductListToAdd();
        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet())
        {
            for (int i = 0; i < entry.getValue(); i++)
            {
                Product product = productFactory.getProduct(entry.getKey().getCategoryName());
                entry.getKey().addProduct(product);
            }
            this.store.addCategoryItem(entry.getKey());
        }
        return categoryProductsMap;
    }

    public static Map<Category, Integer> createProductListToAdd() {
        Map<Category, Integer> productsToAdd = new HashMap<>();

        final List<String> list = SQLqueries.getCategories();
        for (String category: list)
        {
            Random random = new Random();
            productsToAdd.put(new Category(category), random.nextInt(20));
        }
        return productsToAdd;
    }
}
