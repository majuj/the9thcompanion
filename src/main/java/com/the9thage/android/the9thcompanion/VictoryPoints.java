package com.the9thage.android.the9thcompanion;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VictoryPoints extends Activity  {

    private EditText mTotalPointEdit;
    private EditText mArmy1PointEdit;
    private EditText mArmy2PointEdit;
    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_points);
        getActionBar().setTitle("Victory Points");
        mTotalPointEdit = (EditText) findViewById(R.id.editVp1);
        mArmy1PointEdit = (EditText) findViewById(R.id.editVp2);
        mArmy2PointEdit = (EditText) findViewById(R.id.editVp3);
        mResultText = (TextView) findViewById(R.id.textVp4);
    }

    public void CalculateResult(View view) {
        Float totalPts = 0.0f;
        Float army1Pts = 0.0f;
        Float army2Pts = 0.0f;
        String result = "";

        if ( (TextUtils.isEmpty(mTotalPointEdit.getText())) || (TextUtils.isEmpty(mArmy1PointEdit.getText())) || (TextUtils.isEmpty(mArmy2PointEdit.getText()))) {
            result = "Please enter numbers in every field";
        }
        else {
            totalPts = Float.parseFloat(mTotalPointEdit.getText().toString());
            army1Pts = Float.parseFloat(mArmy1PointEdit.getText().toString());
            army2Pts = Float.parseFloat(mArmy2PointEdit.getText().toString());
        }
        if ((totalPts.isNaN()) || (army1Pts.isNaN()) || (army2Pts.isNaN()) || (totalPts == 0.0f) || (army1Pts > totalPts) || (army2Pts > totalPts)){
            result = "Please enter valid numbers in every field";
        }
        else {
            Float ratio;
            ratio = ((army1Pts - army2Pts) * 100) / totalPts;
            if (ratio > 70 ) {
                result = "17 - 3";
            } else if (ratio > 50) {
                result = "16 - 4";
            } else if (ratio > 40) {
                result = "15 - 4";
            } else if (ratio > 30) {
                result = "14 - 6";
            } else if (ratio > 20) {
                result = "13 - 7";
            } else if (ratio > 10) {
                result = "12 - 8";
            } else if (ratio > 5) {
                result = "11 - 9";
            } else if (ratio > -5) {
                result = "10 - 10";
            }else if (ratio > -10) {
                result = "9 - 11";
            }else if (ratio > -20) {
                result = "8 - 12";
            }else if (ratio > -30) {
                result = "7 - 13";
            }else if (ratio > -40) {
                result = "6 - 14";
            }else if (ratio > -50) {
                result = "5 - 15";
            }else if (ratio > -60) {
                result = "4 - 16";
            }else {
                // if ratio > -70 or less
                result = "3 - 17";
            }
        }
        mResultText.setText(result);
    }
}
