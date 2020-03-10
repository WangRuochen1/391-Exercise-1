package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7;
    Button button8, button9, button0, reset, del;
    TextView billnum, textView7, pplnum,payAmount, tipAmount, pplAmount;
    ImageButton min1, min2, add1, add2;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define all the component id here
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        reset = (Button) findViewById(R.id.reset);
        del = (Button) findViewById(R.id.del);
        billnum = (TextView) findViewById(R.id.billnum);
        textView7 = (TextView) findViewById(R.id.textView7);
        pplnum = (TextView) findViewById(R.id.pplnum);
        payAmount = (TextView) findViewById(R.id.payAmount);
        tipAmount = (TextView) findViewById(R.id.tipAmount);
        pplAmount = (TextView) findViewById(R.id.pplAmount);
        min1 = (ImageButton) findViewById(R.id.min1);
        min2 = (ImageButton) findViewById(R.id.min2);
        add1 = (ImageButton) findViewById(R.id.add1);
        add2 = (ImageButton) findViewById(R.id.add2);
    }

    public void NumButtonClick (View view){
        int number = 0;
        double currentBill = 0;
        double totalToPay = 0;
        int tipPercent, split;
        double totalTips = 0;
        double personalBill = 0;

        //setup the Total Bill
        try {
            String Numstr = ((Button) view).getText().toString();
            number = Integer.parseInt(Numstr);
            String Billtext = billnum.getText().toString().substring(1);
            System.out.println(Billtext);
            currentBill = Double.parseDouble(Billtext);
            currentBill = currentBill * 10 + number * 0.01;
            Billtext = String.format("$%.2f",currentBill);
            billnum.setText(Billtext);
        }catch (NumberFormatException ex){
           number = 0;
        }

        //get tips and split bill
        tipPercent = Integer.parseInt(textView7.getText().toString());
        split = Integer.parseInt(pplnum.getText().toString());

        display (currentBill, tipPercent, split);
    }

    public void getInt (View view){
        int tip;
        int split;
        double currentBill;
        try {
            tip = Integer.parseInt(textView7.getText().toString());
            split = Integer.parseInt(pplnum.getText().toString());
            currentBill = Double.parseDouble(billnum.getText().toString().substring(1));
        }catch(NumberFormatException ex){
            tip = 0;
            split = 0;
            currentBill = 0.0;
        }

        switch (view.getId()) {
            case R.id.add1:
                tip ++;
                textView7.setText(String.format("%d",tip));
                display (currentBill, tip, split);
                break;
            case R.id.min1:
                tip --;
                textView7.setText(String.format("%d",tip));
                display(currentBill, tip, split);
                break;
            case R.id.add2:
                split ++;
                pplnum.setText(String.format("%d",split));
                display (currentBill, tip, split);
                break;
            case R.id.min2:
                split --;
                pplnum.setText(String.format("%d",split));
                display (currentBill,tip,split);
                break;
        }
    }

    public void display (double currentBill, int tipPercent, int split){
        double totalToPay;
        double totalTips;
        double personalBill;
        //calculate total payment
        totalToPay = currentBill + tipPercent * 0.01 * currentBill;
        payAmount.setText(String.format("$%.2f",totalToPay));

        //calculate total Tips
        totalTips = tipPercent * 0.01 * currentBill;
        tipAmount.setText(String.format("$%.2f",totalTips));

        //calculate total per person
        personalBill = totalToPay / split;
        pplAmount.setText(String.format("$%.2f",personalBill));
    }

    public void delete (View view){
        double currentBill;
        int tip;
        int split;

        try {
            tip = Integer.parseInt(textView7.getText().toString());
            split = Integer.parseInt(pplnum.getText().toString());
            currentBill = Double.parseDouble(billnum.getText().toString().substring(1));
        }catch (NumberFormatException ex) {
            currentBill = 0;
            tip = 0;
            split = 0;
        }

        currentBill = currentBill / 10;
        billnum.setText(String.format("$%.2f",currentBill));
        display(currentBill, tip, split);
    }

    public void Reset (View view){
        display(0, 0, 1);
        String totalBill = "$0.00";
        String tip = "0";
        String splitnum = "1";
        billnum.setText(totalBill);
        textView7.setText(tip);
        pplnum.setText(splitnum);
    }

}
