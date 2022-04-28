package utils;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import store.Store;

import java.lang.reflect.InvocationTargetException;
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
        Map<Category,Integer> unsortedMap = fillStoreWithProducts();

        for (Map.Entry<Category, Integer> entry : unsortedMap.entrySet())
        {
            for (int i = 0; i < entry.getValue(); i++)
            {
            allProductList.add(productFactory.getProduct(entry.getKey().getCategoryName()));
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

        Reflections reflections = new Reflections("by.issoft.domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            try {
                Random random = new Random();
                productsToAdd.put(type.getConstructor().newInstance(), random.nextInt(10));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return productsToAdd;
    }
}
