package utils;

import by.issoft.domain.Product;
import by.issoft.domain.sort.OrderType;
import by.issoft.domain.sort.SortByName;
import by.issoft.domain.sort.SortByPrice;
import by.issoft.domain.sort.SortByRate;

import java.util.*;

public class SortHelper {
    Map<String, String > sortMap = new LinkedHashMap<>();

    public List<Product> getTopStoreProducts(List<Product> list)
        {
            sortProductsListCustom(list, new SortByPrice(), OrderType.DESC);
            List<Product> topList =  list.subList(0,5);
                return topList;
        }

    public void sortProductsListCustom(List<Product> list, Comparator<Product> comparator, OrderType order) {
        if (order == OrderType.ASC)
        {
            Collections.sort(list, comparator);
        }
        else {
            Collections.sort(list, comparator.reversed());
        }
    }
    public List<Product> sortProductsListWithXMLFileOrder(List<Product> list)
    {
        sortMap = XMLDomParser.parseXMLFile();
        for (Map.Entry<String,String> entry: sortMap.entrySet())
            {
                switch(entry.getKey())
                {
                    case "name":
                        sortProductsListCustom(list,new SortByName(), OrderType.valueOf(entry.getValue().toUpperCase()));
                        break;
                    case "rate":
                        sortProductsListCustom(list, new SortByRate(), OrderType.valueOf(entry.getValue().toUpperCase()));
                        break;
                    case "price":
                        sortProductsListCustom(list, new SortByPrice(), OrderType.valueOf(entry.getValue().toUpperCase()));
                        break;
                    default:
                        System.out.println("Someting is wrong with sorting");
                }
           }
        return list;
    }
}
