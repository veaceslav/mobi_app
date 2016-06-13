package interview.mobiquinty.com.productcatalog;

import android.content.Context;

import interview.mobiquinty.com.productcatalog.exceptions.ExceptionHandlerAbstract;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class TestExceptionHandler  extends ExceptionHandlerAbstract{

    Exception e;

    TestExceptionHandler(Context context) {
        super(context);
    }

    @Override
    public void handleException(Exception e) {

    }

    @Override
    public String getLastExceptionErrorMessage() {
        if(this.e != null)
            return e.getMessage();
        else
            return new String();
    }
}
