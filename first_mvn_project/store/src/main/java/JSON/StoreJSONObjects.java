package JSON;

import by.issoft.domain.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class StoreJSONObjects {

    public static JSONArray getJSONProducts(List<Product> productsList)
    {
        JSONArray jsonProductsList = new JSONArray();

        for (Product prod: productsList)
            {
                JSONObject productJSON = new JSONObject();
                productJSON.put("Category", prod.getCategory());
                productJSON.put("Product Name", prod.getName());
                productJSON.put("Rate", prod.getRate());
                productJSON.put("Price", prod.getPrice());
                jsonProductsList.put(productJSON);
            }

        return jsonProductsList;
    }
}
