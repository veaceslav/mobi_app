package interview.mobiquinty.com.productcatalog.external;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import interview.mobiquinty.com.productcatalog.R;

/**
 * Created by Veaceslav Munteanu on 6/10/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class GlideImageLoader {

    public  static void loadCicleImage(final Context context, ImageView view, String url){

        try {
            Glide.with(context).load(url).asBitmap().centerCrop().placeholder(R.drawable.no_image).into(new BitmapImageViewTarget(view) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    view.setImageDrawable(circularBitmapDrawable);
                }
            });
        } catch (IllegalArgumentException e){
            Log.d("GLide", "This exception should only be thrown in test cases");
        }
    }

    public  static void loadImage(final Context context, ImageView view, String url){

        Glide.with(context).load(url).asBitmap().placeholder(R.drawable.no_image).into(view);
    }
}
