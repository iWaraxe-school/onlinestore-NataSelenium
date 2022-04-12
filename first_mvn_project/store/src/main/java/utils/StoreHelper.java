package utils;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {

    Store store;
    List<Product> allProductList;

    public StoreHelper(Store store)
    {
        this.store = store;
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
        RandomStorePopulator populate = new RandomStorePopulator();
        Map<Category,Integer> categoryProductsMap = createProductListToAdd();
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
