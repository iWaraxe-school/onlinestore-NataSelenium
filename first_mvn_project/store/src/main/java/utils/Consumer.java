package utils;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Consumer extends Thread{
    private final List<Product> orderList;

    public Consumer(List<Product> orderList) {
        this.orderList = orderList;
    }
    @SneakyThrows
    @Override
    public void run()
    {
        while (true)
            {
                TimeUnit.MINUTES.sleep(2);
                Thread.sleep(2000);
                synchronized (orderList) {
                    orderList.clear();
                    if (orderList.size() == 0)
                    {
                        log.info("Order List is empty......");
                        orderList.wait();
                    }
                }
            }
    }
}
