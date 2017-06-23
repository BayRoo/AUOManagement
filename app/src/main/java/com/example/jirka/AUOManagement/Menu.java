package com.example.jirka.AUOManagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Menu extends AppCompatActivity {



    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_act);
        b1 = (Button) findViewById(R.id.send);
        b1.setOnClickListener(handler);




    }
    View.OnClickListener handler = new View.OnClickListener() {
        public void onClick(View v) {
           //MainActivity.connector.publishToAMQP();



           // b1.setTextColor(Color.BLACK);
        }
    };

}
