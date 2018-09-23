package com.example.kylechan.plastickarma;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kylechan.plastickarma.R;

public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mimageIds = new int[] {R.drawable.pie1, R.drawable.pie2};

    ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mimageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mimageIds[position]);
        container.addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
