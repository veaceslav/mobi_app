package interview.mobiquinty.com.productcatalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import interview.mobiquinty.com.productcatalog.exceptions.CatalogException;
import interview.mobiquinty.com.productcatalog.exceptions.ExceptionManagerSingleton;
import interview.mobiquinty.com.productcatalog.exceptions.MainExceptionHandler;
import interview.mobiquinty.com.productcatalog.utils.HttpAsyncTask;
import interview.mobiquinty.com.productcatalog.utils.JsonParser;
import interview.mobiquinty.com.productcatalog.utils.Product;
import interview.mobiquinty.com.productcatalog.utils.ProductContainer;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    CustomListAdapter listAdapter;
    ArrayAdapter spinnerAdapter;

    ListView lw;
    Spinner sp;
    ProductContainer productContainer = new ProductContainer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the exception handle to show error message as toasts
        ExceptionManagerSingleton.getInstance().setExceptionHandler(new MainExceptionHandler(getApplicationContext()));

        listAdapter = new CustomListAdapter(this,R.layout.listitem_layout, new ArrayList<Product>());
        spinnerAdapter = new ArrayAdapter(this,
                                          android.R.layout.simple_dropdown_item_1line,
                                          new ArrayList<String>());

        lw = (ListView)findViewById(R.id.list);
        lw.setAdapter(listAdapter);

        sp = (Spinner)findViewById(R.id.spinner);
        sp.setAdapter(spinnerAdapter);

        addListenerOnSpinnerItemSelection();
        addListernerOnListViewItemSelection();

        populate();
    }

    /**
     * Download the jSon file and create the list of items in the
     */
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
                        ExceptionManagerSingleton.getInstance()
                                .getExceptionHandler().handleException(new CatalogException("We were unable to parse data from server"));
                    }

                } else {
                    ExceptionManagerSingleton.getInstance()
                            .getExceptionHandler().handleException(new CatalogException("We got the wrong data format"));
                }

            }
        }).execute(Constants.FETCH_URL);

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

    /**
     * Add listener to the spinner, update list view if item selection changed
     */
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

    private void addListernerOnListViewItemSelection() {
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appInfo = new Intent(MainActivity.this, DetailsActivity.class);
                appInfo.putExtra("product",  listAdapter.getItem(position));
                startActivity(appInfo);
            }
        });
    }
}
