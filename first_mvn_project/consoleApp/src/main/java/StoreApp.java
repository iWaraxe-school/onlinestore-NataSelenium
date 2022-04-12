import by.issoft.domain.Product;
import store.Store;
import utils.SortHelper;
import utils.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class StoreApp {
    public static void main(String[] args) throws IOException, InterruptedException {


        Store store = new Store();
        List<Product> allProductsList, sortedProductsList, top5ProductsList;
        StoreHelper storeHelper = new StoreHelper(store);
        SortHelper sortHelper = new SortHelper();
        allProductsList = storeHelper.getAllProductsStoreList();
        sortedProductsList = sortHelper.sortProductsListWithXMLFileOrder(allProductsList);
        top5ProductsList = sortHelper.getTopStoreProducts(allProductsList);



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean state = true;

            while (state)
            {
                System.out.println("Enter any following actions: sort, top5 or quit for stop: ");
                String  action = br.readLine();
                switch (action) {
                    case "sort":
                        storeHelper.printStoreProductsList(sortedProductsList);
                        break;
                    case "top5":
                        storeHelper.printStoreProductsList(top5ProductsList);
                        break;
                    case "quit":
                        state = false;
                        break;
                    default:
                        System.out.println("You entered wrong command");

                }
            }
    }
}
