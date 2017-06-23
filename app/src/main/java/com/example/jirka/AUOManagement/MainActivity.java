package com.example.jirka.AUOManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    public static RMQConnector connector;

    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* boolean cState = false;
        int i = 0;
        do {
            try {
                if (connector.getState()) {
                    setContentView(R.layout.activity_main);

                    b2 = (Button) findViewById(R.id.open);
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("conn", String.valueOf(connector.getState()));

                    Intent i = new Intent(v.getContext(), Menu.class);
                    startActivity(i);
                        }
                    });
                    cState = true;
                }
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        } while (!cState && i<6);*/
    }
}
