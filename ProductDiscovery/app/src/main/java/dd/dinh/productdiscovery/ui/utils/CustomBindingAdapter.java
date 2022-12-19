package dd.dinh.productdiscovery.ui.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import dd.dinh.productdiscovery.R;

public class CustomBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.img_product_default)
            .into(imageView);
    }
}
