package com.example.coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Coffee order;
    CheckBox creamListener;
    CheckBox chocoListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order = new Coffee();

        creamListener = findViewById(R.id.creamBox);
        chocoListener = findViewById(R.id.chocoBox);

        registerChangeListener();
    }

    private void registerChangeListener() {
        creamListener.setOnClickListener(creamSwitch);
        chocoListener.setOnClickListener(chocoSwitch);
    }

    private View.OnClickListener creamSwitch = new View.OnClickListener() {
        public void onClick(View v) {
            order.editCream();
        }
    };

    private View.OnClickListener chocoSwitch = new View.OnClickListener() {
        public void onClick(View v) {

            order.editChoco();
        }
    };

    public void plusButton(View view)
    {
        order.increaseCount();

        TextView counter = findViewById(R.id.quantityText);
        counter.setText(String.valueOf(order.getCount()));
    }

    public void minusButton(View view)
    {
        order.decreaseCount();

        TextView counter = findViewById(R.id.quantityText);
        counter.setText(String.valueOf(order.getCount()));
    }

    public void printSummary(View view)
    {
        String summaryStr = "ORDER SUMMARY";

        summaryStr += "\n\nAdd whipped cream? ";
        if(order.getCream()) {
            summaryStr += "yes \n";
        }
        else {
            summaryStr += "no \n";
        }

        summaryStr += "Add chocolate? ";
        if(order.getChoco()) {
            summaryStr += "yes \n";
        }
        else {
            summaryStr += "no \n";
        }

        summaryStr += "Quantity: " + order.getCount() + "\n\n";
        summaryStr += "Price: $" + String.format("%.2f", order.getPrice()) + "\n" + "THANK YOU!";

        TextView summary = findViewById(R.id.textView4);
        summary.setText(summaryStr);

        Uri intentUri = Uri.parse("mailto:");
        Intent email = new Intent(Intent.ACTION_SENDTO, intentUri);

        email.putExtra(Intent.EXTRA_SUBJECT, "Your Coffee Order");  // Adds email subject
        //System.out.println("LOOK AT THIS \n\n" + summaryStr);
        email.putExtra(Intent.EXTRA_TEXT, summaryStr);  // Adds email body

        startActivity(email);
    }
}
