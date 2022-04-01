import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.sort.OrderType;
import by.issoft.domain.sort.SortByName;
import by.issoft.domain.sort.SortByPrice;
import by.issoft.domain.sort.SortByRate;
import com.github.javafaker.Cat;
import com.sun.org.apache.xpath.internal.operations.Or;
import store.Store;
import utils.StoreHelper;
import utils.XMLDomParser;

import java.util.*;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        Map<String ,String > sortMap;
        StoreHelper storeHelper = new StoreHelper(store);

        System.out.println("Start sorting product list: ");
        storeHelper.sortProductsListCustom(new SortByPrice(), OrderType.DESC);

        System.out.println("Start XML parsing: ");
        sortMap = XMLDomParser.parseXMLFile();
            for (Map.Entry<String,String> entry: sortMap.entrySet())
            {
                System.out.println("Sort: " + entry.getKey() + ", Order: " + entry.getValue());
            }
    }
}
