package interview.mobiquinty.com.productcatalog.exceptions;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 *
 * More user friendly exception, is is being thrown with
 * more meaningful message for user
 */
public class CatalogException  extends Exception{

    public CatalogException(String message){
        super(message);
    }
}
