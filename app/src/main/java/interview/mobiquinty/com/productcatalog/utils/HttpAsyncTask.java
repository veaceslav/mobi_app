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
            url = new java.net.URL(Constants.FETCH_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read the response
        try {
            System.out.println("Response Code: " + conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = stringFromInputStream(in);
            Log.d(TAG, response);
        } catch (IOException e) {
            e.printStackTrace();
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
