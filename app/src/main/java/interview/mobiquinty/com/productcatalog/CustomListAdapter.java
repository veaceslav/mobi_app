package interview.mobiquinty.com.productcatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import interview.mobiquinty.com.productcatalog.external.GlideImageLoader;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class CustomListAdapter extends ArrayAdapter<Product> {

    public CustomListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomListAdapter(Context context, int resource, List<Product> items) {
        super(context, resource, items);
    }

    public void updateItems(List<Product> items){
        super.clear();
        super.addAll(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listitem_layout, null);
        }

        Product p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.toptext);
            ImageView imgView = (ImageView) v.findViewById(R.id.imageView);

            if (tt1 != null) {
                tt1.setText(p.name);
            }

            if(imgView != null){
                GlideImageLoader.loadCicleImage(getContext(), imgView, Constants.FETCH_URL + p.url);
            }
        }

        return v;
    }
}
