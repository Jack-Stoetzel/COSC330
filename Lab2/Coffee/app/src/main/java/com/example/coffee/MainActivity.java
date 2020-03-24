package com.example.coffee;

import androidx.appcompat.app.AppCompatActivity;

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

    public void printSummary(View view) {

        TextView summary = findViewById(R.id.textView4);

        summary.setText(R.string.order_sum);

        summary.append("\n\nAdd whipped cream? ");
        if(order.getCream()) {
            summary.append("yes \n");
        }
        else {
            summary.append("no \n");
        }

        summary.append("Add chocolate? ");
        if(order.getChoco()) {
            summary.append("yes \n");
        }
        else {
            summary.append("no \n");
        }

        summary.append("Quantity: " + order.getCount() + "\n\n");

        summary.append("Price: $" + order.getPrice() + "\n" + "THANK YOU!");
    }
}
