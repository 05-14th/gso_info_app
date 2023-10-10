package com.example.gsoinfoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> imageResourceIds = new ArrayList<>();
    private int imageWidth = 200; // Set your desired width in pixels
    private int imageHeight = 200; // Set your desired height in pixels

    public ImagePagerAdapter(Context context) {
        this.context = context;

        // Dynamically retrieve all drawable resource IDs in the package
        String packageName = context.getPackageName();
        for (int i = 1; ; i++) {
            int resourceId = context.getResources().getIdentifier("a" + i, "drawable", packageName);
            if (resourceId == 0) {
                break; // No more images found
            }
            imageResourceIds.add(resourceId);
        }
    }

    @Override
    public int getCount() {
        return imageResourceIds.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageResourceIds.get(position));

        // Set fixed dimensions for the ImageView
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(imageWidth, imageHeight);
        imageView.setLayoutParams(layoutParams);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
