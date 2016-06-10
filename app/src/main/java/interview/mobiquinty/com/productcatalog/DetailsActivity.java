package interview.mobiquinty.com.productcatalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import interview.mobiquinty.com.productcatalog.external.GlideImageLoader;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Product product = (Product)getIntent().getSerializableExtra("product");

        TextView tw1 = (TextView)findViewById(R.id.detailsTitle);
        tw1.setText(product.name);

        TextView tw2 = (TextView)findViewById(R.id.detailsPrice);
        tw2.setText("Price: " + product.price + " " + product.currency);

        ImageView imgView = (ImageView)findViewById(R.id.detailsImage);

        GlideImageLoader.loadImage(this,imgView, Constants.FETCH_URL + product.url);
    }
}
