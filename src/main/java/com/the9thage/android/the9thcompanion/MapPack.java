package com.the9thage.android.the9thcompanion;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;


public class MapPack extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_pack);
        getActionBar().setTitle("Map Pack");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void showMap1(View view) {
        // General Map
        Random r = new Random();
        int selectObj = r.nextInt(16) +1;
        String viewId = "mapgeneral" + selectObj;
        int resId = getResources().getIdentifier(viewId, "drawable", getPackageName());
        showImage(getDrawable(resId));
    }

    public void showMap2(View view) {
        // Counterthrust
        Random r = new Random();
        int selectObj = r.nextInt(4) +1;
        String viewId = "mapcounter" + selectObj;
        int resId = getResources().getIdentifier(viewId, "drawable", getPackageName());
        showImage(getDrawable(resId));
    }

    public void showMap3(View view) {
        // Encircle
        Random r = new Random();
        int selectObj = r.nextInt(4) +1;
        String viewId = "mapencircle" + selectObj;
        int resId = getResources().getIdentifier(viewId, "drawable", getPackageName());
        showImage(getDrawable(resId));
    }

    public void showMap4(View view) {
        // Frontline
        Random r = new Random();
        int selectObj = r.nextInt(4) +1;
        String viewId = "mapfrontline" + selectObj;
        int resId = getResources().getIdentifier(viewId, "drawable", getPackageName());
        showImage(getDrawable(resId));
    }

    public void showMap5(View view) {
        // Refused Flank
        Random r = new Random();
        int selectObj = r.nextInt(4) +1;
        String viewId = "maprefused" + selectObj;
        int resId = getResources().getIdentifier(viewId, "drawable", getPackageName());
        showImage(getDrawable(resId));
    }

    public void showImage(Drawable image) {
        Dialog builder = new Dialog(this, android.R.style.Theme);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        TouchImageView imageView = new TouchImageView(this);
        imageView.setImageDrawable(image);
        // image is rotated to be readable on a phone
        //imageView.setRotation(90);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }
}
