package http.handlers;

import JSON.StoreJSONObjects;
import by.issoft.domain.Product;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.json.JSONArray;
import store.Store;
import utils.OrderCart;
import utils.StoreHelper;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CategoryHandler implements HttpHandler {
    private HttpHandler next;
    Store store = new Store();
    StoreHelper storeHelper = StoreHelper.getInstance(store);
    final List<Product> allProductsList = storeHelper.getAllProductsStoreList();

    public CategoryHandler(HttpHandler next) {
        this.next = next;
    }
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }

        JSONArray jsonArray = StoreJSONObjects.getJSONProducts(allProductsList);
        byte[] sendData = jsonArray.toString().getBytes(StandardCharsets.UTF_8);

        OutputStream out = null;
        exchange.startBlocking();


            exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseHeaders().add(Headers.CONTENT_LENGTH, Long.toString(sendData.length));
            out = exchange.getOutputStream();
            out.write(sendData.length);
            exchange.setStatusCode(200);
            exchange.getResponseSender().send("\n Everything is ok!");
            out.close();

        exchange.dispatch(startProducerThread());
    }


    public HttpHandler startProducerThread()
    {
        final List<Product> orderList = new CopyOnWriteArrayList<>();
        Thread orderCartThread = new Thread(new OrderCart(orderList, allProductsList));
        orderCartThread.run();
        return null;
    }
}
