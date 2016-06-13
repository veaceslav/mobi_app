package interview.mobiquinty.com.productcatalog.exceptions;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class MainExceptionHandler extends ExceptionHandlerAbstract {

    public MainExceptionHandler(Context context) {
        super(context);
    }

    @Override
    public void handleException(final Exception e) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Toast.makeText(mContext, e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
