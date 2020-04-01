package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (ListView) findViewById(R.id.menu);


    }

    @Override
    protected void onResume()
    {

        final String[] items = getResources().getStringArray(R.array.menu);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        menu.setAdapter(adapter);

        super.onResume();

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(),WordList.class);
                        startActivity(intent);
                        break;
//                    case 1:
//                        Intent intent = new Intent(getApplicationContext(),.class);
//                        startActivity(intent);
//                        break;
//                    case 2:
//                        Intent intent = new Intent(getApplicationContext(),.class);
//                        startActivity(intent);
//                        break;
//                    case 3:
//                        Intent intent = new Intent(getApplicationContext(),.class);
//                        startActivity(intent);
//                        break;
//                    case 4:
//                        Intent intent = new Intent(getApplicationContext(),.class);
//                        startActivity(intent);
//                        break;
//                    case 5:
//                        Intent intent = new Intent(getApplicationContext(),.class);
//                        startActivity(intent);
//                        break;
                }
                //intent.putExtra("name",items[i]);
            }
        });
    }
}
