package utils;

import by.issoft.domain.Product;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class OrderCart implements Runnable {

        private final List<Product> orderList;
        public List<Product> prodList;

    public OrderCart(List<Product> orderList, List<Product> list) {
        this.orderList = orderList;
        this.prodList = list;
    }
    private Product getItemFromProductList()
    {
        Product item = prodList.get(new Random().nextInt(prodList.size()));
        return item;
    }
    @SneakyThrows
    @Override
    public void run() {
        while (true)
        {
            TimeUnit.MILLISECONDS.sleep(2000);
            synchronized (orderList)
            {
                IntStream.range(0, 5).forEach(i ->
                        orderList.add(getItemFromProductList())
                );
                System.out.println("Order List contains: " + orderList.size() + " products");
                orderList.notify();
            }
        }
    }
}
