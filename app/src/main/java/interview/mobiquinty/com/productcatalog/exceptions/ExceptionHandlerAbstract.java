package interview.mobiquinty.com.productcatalog.exceptions;

import android.content.Context;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public abstract class ExceptionHandlerAbstract {

    protected Context mContext;

    ExceptionHandlerAbstract(Context context){
        this.mContext = context;
    }

    public abstract void handleException(Exception e);
}
