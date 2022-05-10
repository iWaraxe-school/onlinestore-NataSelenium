import SQL.SQLqueries;
import by.issoft.domain.Product;
import store.Store;
import utils.StoreHelper;

import java.util.List;


public class StoreApp {
    public static void main(String[] args)  {

        Store store = new Store();
        List<Product> allProductsList = null;

        StoreHelper storeHelper = StoreHelper.getInstance(store);
        SQLqueries.createTables();
        allProductsList = storeHelper.getAllProductsStoreList();
        SQLqueries.addProducts(allProductsList);
        SQLqueries.getProducts();
    }
}
