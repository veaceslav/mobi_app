package interview.mobiquinty.com.productcatalog.utils;

import java.util.ArrayList;
import java.util.HashMap;

import interview.mobiquinty.com.productcatalog.utils.Product;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */

public class ProductContainer {

    public HashMap<String, ArrayList<Product> > productMap = new HashMap<>();

    public void putProducts(String category, ArrayList<Product> products){
        productMap.put(category, products);
    }

    public ArrayList<Product> getProducts(String category){
        return productMap.get(category);
    }

}
