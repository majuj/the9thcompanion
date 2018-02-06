package com.the9thage.android.the9thcompanion;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Color.CYAN;
import static android.graphics.Color.WHITE;

public class Deployment extends Activity  {

    private TextView mSelectObj;
    private FrameLayout mFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deployment);
        getActionBar().setTitle("Deployment");
    }

    public void RandomDeployment(View view) {
        // reset the highlight first
        resetHighlight(view);

        // maximum number of secondary objectives = 6
        Random r = new Random();
        int selectObj = r.nextInt(6) +1;
        String viewId = "textDep" + selectObj;
        int resId = getResources().getIdentifier(viewId, "id", getPackageName());
        mSelectObj = (TextView) findViewById(resId);
        mSelectObj.setBackgroundColor(CYAN);
    }

    public void resetHighlight(View view) {
        // every textViews get their background back to white
        for (int i = 1; i < 7; i++) {
            String viewId = "textDep" + i;
            int resId = getResources().getIdentifier(viewId, "id", getPackageName());
            mSelectObj = (TextView) findViewById(resId);
            mSelectObj.setBackgroundColor(WHITE);
        }
    }

    public void showDep1(View view) {
        showImage(getDrawable(R.drawable.dep1frontline));
    }

    public void showDep2(View view) {
        showImage(getDrawable(R.drawable.dep2dawn));
    }

    public void showDep3(View view) {
        showImage(getDrawable(R.drawable.dep3counter));
    }

    public void showDep4(View view) {
        showImage(getDrawable(R.drawable.dep4encircle));
    }

    public void showDep5(View view) {
        showImage(getDrawable(R.drawable.dep5refused));
    }

    public void showDep6(View view) {
        showImage(getDrawable(R.drawable.dep6columns));
    }

    public void showImage(Drawable image) {
        Dialog builder = new Dialog(this, android.R.style.Theme);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.WHITE));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        TouchImageView imageView = new TouchImageView(this);
        imageView.setImageDrawable(image);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

}
