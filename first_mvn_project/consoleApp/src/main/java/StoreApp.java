import by.issoft.domain.Product;
import store.Store;
import utils.Consumer;
import utils.Producer;
import utils.StoreHelper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class StoreApp {
    public static void main(String[] args) throws IOException, InterruptedException {


        Store store = new Store();
        StoreHelper storeHelper = StoreHelper.getInstance(store);
        List<Product> allProductsList = storeHelper.getAllProductsStoreList();

        final List<Product> orderList = new CopyOnWriteArrayList<>();
        Thread producerThread = new Thread(new Producer(orderList, allProductsList));
        Thread consumerThread = new Thread(new Consumer(orderList));

            producerThread.start();
            consumerThread.start();

            Thread.sleep(2000);
    }
}
