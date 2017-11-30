package com.hrsoft.today.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lwkandroid.imagepicker.utils.IImagePickerDisplayer;

/**
 * @author YangCihang
 * @since 17/11/12.
 * email yangcihang@hrsoft.net
 */

public class ImagePickerUtil implements IImagePickerDisplayer {

    @Override
    public void display(Context context, String url, ImageView imageView, int maxWidth, int maxHeight) {
        Glide.with(context)
                .load(url)
                .override(maxWidth, maxHeight)
                .into(imageView);
    }

    @Override
    public void display(Context context, String url, ImageView imageView, int placeHolder, int errorHolder, int maxWidth, int maxHeight) {
        Glide.with(context)
                .load(url)
                .placeholder(placeHolder)
                .error(errorHolder)
                .override(maxWidth, maxHeight)
                .into(imageView);
    }
}
