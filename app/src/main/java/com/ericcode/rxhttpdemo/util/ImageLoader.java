package com.ericcode.rxhttpdemo.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {

    public static void load(String url, ImageView imageView) {
        Glide.with(imageView)
                .load(url)
                .into(imageView);
    }
}
