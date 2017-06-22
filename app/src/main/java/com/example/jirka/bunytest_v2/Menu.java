package com.example.jirka.bunytest_v2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.R.attr.bitmap;
import static android.R.attr.width;
import static com.example.jirka.bunytest_v2.R.attr.height;


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
