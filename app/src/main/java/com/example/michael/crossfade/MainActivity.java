package com.example.michael.crossfade;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        final View l1 = findViewById(R.id.l1);
        final View l2 = findViewById(R.id.l2);


        Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Bitmap test_blurred = FastBlur.fastblur(test, 1, 100);

        BitmapDrawable background1 = new BitmapDrawable(test);
        l1.setBackgroundDrawable(background1);

        //BitmapDrawable background2 = new BitmapDrawable(jellybeans_blurred);
        BitmapDrawable background2 = new BitmapDrawable(darkenBitMap(test_blurred));
        l2.setBackgroundDrawable(background2);

        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        findViewById(R.id.l1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clicked){

                    //do nothing

                } else {

                    clicked = true;

                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            //try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
                            l1.setVisibility(View.GONE);
                            l2.bringToFront();
                            Log.v("TAG", "L1 visibility set to gone");
                            //try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                            clicked = false;

                        }
                    });

                    l1.startAnimation(fadeOut);
                    l2.setVisibility(View.VISIBLE);
                    l2.startAnimation(fadeIn);
                    Log.v("TAG","L1 clicked");
                }
            }
        });

        findViewById(R.id.l2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clicked){

                    //do nothing

                } else {

                    clicked = true;

                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            //try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
                            l2.setVisibility(View.GONE);
                            l1.bringToFront();
                            Log.v("TAG", "L2 visibility set to gone");
                            clicked = false;


                        }
                    });
                    l2.startAnimation(fadeOut);
                    l1.setVisibility(View.VISIBLE);
                    l1.startAnimation(fadeIn);
                    Log.v("TAG", "L2 clicked");
                }

            }
        });
    }

    private Bitmap darkenBitMap(Bitmap bm) {

        Canvas canvas = new Canvas(bm);
        Paint p = new Paint(Color.RED);
        //ColorFilter filter = new LightingColorFilter(Color.RED, 1);
        //ColorFilter filter = new LightingColorFilter(0xFFFFFFFF , 0x00222222); // to lighten
        ColorFilter filter = new LightingColorFilter(0xFF7F7F7F, 0x00000000); // to darken
        p.setColorFilter(filter);
        canvas.drawBitmap(bm, new Matrix(), p);

        return bm;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
