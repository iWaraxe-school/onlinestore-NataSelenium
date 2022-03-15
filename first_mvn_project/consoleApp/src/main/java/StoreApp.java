import by.issoft.domain.Category;
import categories.Book;
import categories.Food;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import store.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class StoreApp {
Store store;
private static Map<Category, Integer> createProductListToAdd()
{
    Map<Category, Integer> productsToAdd = new HashMap<>();

    Reflections reflections = new Reflections("by.issoft.domain.categories", new SubTypesScanner());
    Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

    for (Class<? extends Category> type : subTypes)
    {
    try {
        Random random = new Random();
        productsToAdd.put(type.getConstructor().newInstance(), random.nextInt(10));
        }
    catch (NoSuchMethodException e)
        { e.printStackTrace();     }
    catch (InvocationTargetException e)
        {e.printStackTrace();}
    catch (IllegalAccessException e)
        {e.printStackTrace();}
        }
}
