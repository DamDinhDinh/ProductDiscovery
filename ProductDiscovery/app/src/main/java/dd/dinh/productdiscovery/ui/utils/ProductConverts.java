package dd.dinh.productdiscovery.ui.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dd.dinh.productdiscovery.MyApplication;
import dd.dinh.productdiscovery.R;

public class ProductConverts {
    public static String getPrice(double price) {
        return price + "đ";
    }

    public static Spanned highlightWord(List<String> words, String target){
        SpannableStringBuilder result = new SpannableStringBuilder(target);
        for (String word: words) {
            Pattern p = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(target);
            while (m.find()){
                result.setSpan(new BackgroundColorSpan(MyApplication.getAppContext().getColor(R.color.tomato)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }

        return result;
    }
}
