package interview.mobiquinty.com.productcatalog;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    ArrayAdapter listAdapter;
    ArrayAdapter spinnerAdapter;

    ListView lw;
    Spinner sp;
    ProductContainer productContainer = new ProductContainer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>());
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, new ArrayList<String>());

        lw = (ListView)findViewById(R.id.list);
        lw.setAdapter(listAdapter);

        sp = (Spinner)findViewById(R.id.spinner);
        sp.setAdapter(spinnerAdapter);

        addListenerOnSpinnerItemSelection();

        populate();
    }

    private void populate() {

        new HttpAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                if(output instanceof String) {
                    String json = (String) output;
                    try {
                        JSONArray ja = new JSONArray(json);
                        ArrayList<String> categories = new ArrayList<String>();
                        for(int iter = 0; iter < ja.length(); iter++){
                            JSONObject jo = (JSONObject) ja.get(iter);
                            String category = jo.getString(Constants.CATEGORY_NAME);
                            categories.add(category);
                            ArrayList<Product> products = JsonParser.parseProducts(jo);
                            productContainer.putProducts(category,products);
                        }
                        setCategories(categories);
                        updateList();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e(TAG, "Object is not instance of string, we were supposed to get a json");
                }

            }
        }).execute();

    }

    public void setCategories(ArrayList<String> categories){
        spinnerAdapter.clear();
        spinnerAdapter.addAll(categories);
        sp.setSelection(0);
    }

    private void updateList(){
        String text = sp.getSelectedItem().toString();
        listAdapter.clear();
        listAdapter.addAll(productContainer.getProducts(text));
    }

    public void addListenerOnSpinnerItemSelection() {
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
