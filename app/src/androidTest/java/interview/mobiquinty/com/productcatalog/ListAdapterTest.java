package interview.mobiquinty.com.productcatalog;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import interview.mobiquinty.com.productcatalog.utils.Product;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class ListAdapterTest extends AndroidTestCase{

    private  CustomListAdapter mAdapter;


    private Product bread;
    private Product milk; ;

    public ListAdapterTest() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();
        ArrayList<Product> data = new ArrayList<>();

        bread= new Product();
        bread.name = "Bread";

        milk = new Product();
        milk.name = "Milk";
        data.add(bread);
        data.add(milk);
        mAdapter = new CustomListAdapter(getContext(),R.layout.listitem_layout,  data);
    }


    public void testGetItem() {
        assertEquals("Bread was expected.", bread.name,
                ((Product) mAdapter.getItem(0)).name);

        assertEquals("Milk was expected.", bread.name,
                ((Product) mAdapter.getItem(1)).name);
    }

    public void testGetItemId() {
        assertEquals("Wrong ID.", 0, mAdapter.getItemId(0));
    }

    public void testGetCount() {
        assertEquals("Product amount incorrect.", 2, mAdapter.getCount());
    }

    // I have 3 views on my adapter, name, number and photo
    public void testGetView() {
        View view = mAdapter.getView(0, null, null);

        TextView name = (TextView) view
                .findViewById(R.id.toptext);

        //On this part you will have to test it with your own views/data
        assertNotNull("View is null. ", view);
        assertNotNull("Name TextView is null. ", name);

        assertEquals("Names doesn't match.", bread.name, name.getText());
    }

}
