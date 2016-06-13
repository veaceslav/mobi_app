package interview.mobiquinty.com.productcatalog.utils;

import java.io.Serializable;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class Product implements Serializable{
    public int id;
    public int categoryID;
    public String name;
    public String url;
    public String description;
    public double price;
    public String currency;

}
