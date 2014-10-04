package com.example.gordon.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editText);
        String input = myEditText.getText().toString();

        TextView fortyfive = (TextView) findViewById(R.id.fortyfive);
        TextView twentyfive = (TextView) findViewById(R.id.twentyfive);
        TextView ten = (TextView) findViewById(R.id.ten);
        TextView five = (TextView) findViewById(R.id.five);
        TextView twopointfive = (TextView) findViewById(R.id.twopointfive);

        RadioGroup g = (RadioGroup) findViewById(R.id.radioGroup);
        double bar = 0;
        switch (g.getCheckedRadioButtonId())
        {
            case R.id.Barbell:
                bar = 45;
                break;

            case R.id.Preacher:
                bar = 25;
                break;
        }

        if (input.length() != 0) {
            int[] plates = greedyCalc(Double.parseDouble(input), bar);
            fortyfive.setText("" + plates[0]);
            twentyfive.setText("" + plates[1]);
            ten.setText("" + plates[2]);
            five.setText("" + plates[3]);
            twopointfive.setText("" + plates[4]);

        }
    }

    static public int[] greedyCalc(double desiredWeight, double barWeight)
    {

        double[] sizeArray = {45,25,10,5,2.5};
        int[] weightArray = {0,0,0,0,0};
        double plateWeight = desiredWeight - barWeight;
        plateWeight/=2;
        double remainingWeight = plateWeight;

        for (int i=0;i<weightArray.length;i++)
        {
            while(sizeArray[i] <= remainingWeight)
            {
                weightArray[i]++;
                remainingWeight -= sizeArray[i];
            }
        }
        return weightArray;
    }

}
