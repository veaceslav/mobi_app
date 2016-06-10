package interview.mobiquinty.com.productcatalog;

import java.io.Serializable;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class Product implements Serializable{
    int id;
    int categoryID;
    String name;
    String url;
    String description;
    double price;
    String currency;

}
