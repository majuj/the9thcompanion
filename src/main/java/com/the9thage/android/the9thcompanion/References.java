package com.the9thage.android.the9thcompanion;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class References extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);
    }

    public void showRef1(View view) {
        showImage(getDrawable(R.drawable.refcc1));
    }

    public void showRef2(View view) {
        showImage(getDrawable(R.drawable.refcc2));
    }

    public void showRef3(View view) {
        showImage(getDrawable(R.drawable.refcc3));
    }

    public void showRef4(View view) {
        showImage(getDrawable(R.drawable.refcc4));
    }

    public void showRef5(View view) {
        showImage(getDrawable(R.drawable.refcc5));
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
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }
}
