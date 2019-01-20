package com.myapps.daggermvvm;

import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

public class ImageUtil {
    @BindingAdapter("imageRes")
    public static void setImage(SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        simpleDraweeView.setBackground(null);
    }
}
