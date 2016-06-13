package interview.mobiquinty.com.productcatalog.utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import interview.mobiquinty.com.productcatalog.Constants;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class JsonParser {
    public static ArrayList<Product> parseProducts(JSONObject categoryObject){

        ArrayList<Product> products = new ArrayList<>();
        try {
            JSONArray ja = categoryObject.getJSONArray(Constants.PRODUCT_ARRAY);
            for(int i = 0; i < ja.length(); i++ ){
                JSONObject tmp = (JSONObject)ja.get(i);
                Product product = new Product();
                product.id = tmp.getInt(Constants.PRODUCT_ID);
                product.name= tmp.getString(Constants.PRODUCT_NAME);
                product.url = tmp.getString(Constants.PRODUCT_URL);
                //product.description = tmp.getString(Constants.PRODUCT_DESCRIPTION);
                product.categoryID = tmp.getInt(Constants.PRODUCT_CATEGORY_ID);

                JSONObject salePrice = tmp.getJSONObject(Constants.PRODUCT_SALEPRICE);
                product.price = salePrice.getDouble(Constants.PRODUCT_AMOUNT);
                product.currency = salePrice.getString(Constants.PRODUCT_CURRENCY);

                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }
}

