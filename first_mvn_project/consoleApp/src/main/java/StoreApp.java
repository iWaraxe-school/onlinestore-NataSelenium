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
        StoreHelper storeHelper = StoreHelper.getInstance(store);
        SortHelper sortHelper = new SortHelper();
        List<Product> allProductsList = storeHelper.getAllProductsStoreList();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean state = true;

            while (state)
            {
                System.out.println("Enter any following actions: sort, top5 or quit for stop: ");
                String  action = br.readLine();
                switch (action) {
                    case "sort":
                        storeHelper.printStoreProductsList(sortHelper.sortProductsListWithXMLFileOrder(allProductsList));
                        break;
                    case "top5":
                        storeHelper.printStoreProductsList(sortHelper.getTopStoreProducts(allProductsList));
                        break;
                    case "quit":
                        state = false;
                        break;
                    default:
                        System.out.println("You entered wrong command");

                }
            }
            br.close();
    }
}
