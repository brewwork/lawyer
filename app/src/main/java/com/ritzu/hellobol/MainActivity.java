package com.ritzu.hellobol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   Intent login = new Intent(MainActivity.this, com.ritzu.hellobol.login.class);
      //  startActivity(login);

        Intent dashBoard = new Intent(MainActivity.this,Dashboard.class);
        startActivity(dashBoard);

//        try {
//            new Runnable() {
//                @Override
//                public void run() {
//                    Intent login = new Intent(MainActivity.this, login1.class);
//                    startActivity(login);
//                }
//            }.wait(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

    private void RunAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        a.reset();
       // TextView tv = (TextView) findViewById(R.id.hello);
        ImageView img = (ImageView) findViewById(R.id.img);
        img.clearAnimation();
        img.startAnimation(a);
    }
}