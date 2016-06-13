package interview.mobiquinty.com.productcatalog.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Objects;
import java.util.Scanner;

import interview.mobiquinty.com.productcatalog.AsyncResponse;
import interview.mobiquinty.com.productcatalog.Constants;
import interview.mobiquinty.com.productcatalog.exceptions.CatalogException;
import interview.mobiquinty.com.productcatalog.exceptions.ExceptionManagerSingleton;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class HttpAsyncTask extends AsyncTask<Object , Object, Object>{

    private final String TAG = "HttpAsynctask";

    public AsyncResponse delegate = null;//Call back interface

    public HttpAsyncTask(AsyncResponse asyncResponse) {
        delegate = asyncResponse;//Assigning call back interfacethrough constructor
    }

    @Override
    protected Object doInBackground(Object... params) {

        java.net.URL url = null;
        HttpURLConnection conn = null;
        String response = null;
        try {
            url = new java.net.URL((String)params[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            ExceptionManagerSingleton.getInstance()
                    .getExceptionHandler().handleException(new CatalogException("The server url is not valid"));
        } catch (IOException e) {
            ExceptionManagerSingleton.getInstance()
                    .getExceptionHandler().handleException(new CatalogException("We have troubles connecting to the server"));
        }

        // read the response
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = stringFromInputStream(in);
            Log.d(TAG, response);
        } catch (IOException e) {
            ExceptionManagerSingleton.getInstance()
                    .getExceptionHandler().handleException(new CatalogException("We have troubles connecting to the server"));
        }
        return response;

    } // protected Void doInBackground(String... params)

    @Override
    protected void onPostExecute(Object result) {
        delegate.processFinish(result);
    }

    String stringFromInputStream(InputStream inputStream){
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
