package utils;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class Producer extends Thread {
    private final List<Product> orderList;
    public List<Product> prodList;

    public Producer(List<Product> orderList, List<Product> list) {
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
    public void run()
    {
        while (true)
        {
            TimeUnit.MILLISECONDS.sleep(2000);
            synchronized (orderList)
            {
                IntStream.range(0, 5).forEach(i ->
                        orderList.add(getItemFromProductList())
                );
                log.info("Order List contains: " + orderList.size() + " products");
                orderList.notify();
            }

        }
    }
}
