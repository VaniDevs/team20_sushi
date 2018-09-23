package com.example.kylechan.plastickarma;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ManualInputActivity extends AppCompatActivity {
    private static int position = 1;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    //update button
    protected void updateTotal(Float sum, TextView totalSum) {

        String total = totalSum.getText().toString();
        Float total_sum = Float.parseFloat(total);
        total_sum += sum;

        totalSum.setText(Float.toString(total_sum));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);


        Button complete = (Button)findViewById(R.id.complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(ManualInputActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        String[] products = getResources().getStringArray(R.array.products);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, products);
        final AutoCompleteTextView autotextView = (AutoCompleteTextView) findViewById(R.id.manual_input_field);
        autotextView.setAdapter(adapter);

        autotextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.e("TAG","Done pressed");
                    String selection = autotextView.getText().toString();
                    final TextView input1 = (TextView) findViewById(R.id.input1);
                    final TextView input2 = (TextView) findViewById(R.id.input2);
                    final TextView input3 = (TextView) findViewById(R.id.input3);
                    final TextView input4 = (TextView) findViewById(R.id.input4);
                    final TextView quantity1 = (TextView) findViewById(R.id.quantity1);
                    final TextView quantity2 = (TextView) findViewById(R.id.quantity2);
                    final TextView quantity3 = (TextView) findViewById(R.id.quantity3);
                    final TextView quantity4 = (TextView) findViewById(R.id.quantity4);
                    final TextView karma1 = (TextView) findViewById(R.id.karma1);
                    final TextView karma2 = (TextView) findViewById(R.id.karma2);
                    final TextView karma3 = (TextView) findViewById(R.id.karma3);
                    final TextView karma4 = (TextView) findViewById(R.id.karma4);
                    final TextView karmaText1 = (TextView) findViewById(R.id.karmaText1);
                    final TextView karmaText2 = (TextView) findViewById(R.id.karmaText2);
                    final TextView karmaText3 = (TextView) findViewById(R.id.karmaText3);
                    final TextView karmaText4 = (TextView) findViewById(R.id.karmaText4);
                    final TextView totalSum = (TextView) findViewById(R.id.totalSum);


                    Button dec1 = (Button)findViewById(R.id.dec1);
                    dec1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity1.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result--;
                            quantity1.setText(Integer.toString(result));

                            String karma = karma1.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res - 5f;
                            karma1.setText(Float.toString(karma_res));

                            updateTotal(-5f, totalSum);
                        }
                    });

                    Button inc1 = (Button)findViewById(R.id.inc1);
                    inc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity1.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result++;
                            quantity1.setText(Integer.toString(result));

                            String karma = karma1.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res + 5f;
                            karma1.setText(Float.toString(karma_res));

                            updateTotal(5f, totalSum);
                        }
                    });

                    Button dec2 = (Button)findViewById(R.id.dec2);
                    dec2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity2.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result--;
                            quantity2.setText(Integer.toString(result));

                            String karma = karma2.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res - 15f;
                            karma2.setText(Float.toString(karma_res));

                            updateTotal(-15f, totalSum);

                        }
                    });

                    Button inc2 = (Button)findViewById(R.id.inc2);
                    inc2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity2.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result++;
                            quantity2.setText(Integer.toString(result));

                            String karma = karma2.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res + 15f;
                            karma2.setText(Float.toString(karma_res));

                            updateTotal(15f, totalSum);
                        }
                    });

                    Button dec3 = (Button)findViewById(R.id.dec3);
                    dec3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity3.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result--;
                            quantity3.setText(Integer.toString(result));

                            String karma = karma3.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res - 10f;
                            karma3.setText(Float.toString(karma_res));

                            updateTotal(-10f, totalSum);

                        }
                    });

                    Button inc3 = (Button)findViewById(R.id.inc3);
                    inc3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity3.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result++;
                            quantity3.setText(Integer.toString(result));

                            String karma = karma3.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res + 10f;
                            karma3.setText(Float.toString(karma_res));

                            updateTotal(10f, totalSum);

                        }
                    });


                    Button dec4 = (Button)findViewById(R.id.dec4);
                    dec4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity4.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result--;
                            quantity4.setText(Integer.toString(result));

                            String karma = karma4.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res - 20f;
                            karma4.setText(Float.toString(karma_res));

                            updateTotal(-20f, totalSum);
                        }
                    });

                    Button inc4 = (Button)findViewById(R.id.inc4);
                    inc4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String quantity = quantity4.getText().toString();
                            int result = Integer.parseInt(quantity);
                            result++;
                            quantity4.setText(Integer.toString(result));

                            String karma = karma1.getText().toString();
                            float karma_res = Float.parseFloat(karma);
                            karma_res = karma_res + 20f;
                            karma1.setText(Float.toString(karma_res));

                            updateTotal(20f, totalSum);

                        }
                    });




                    switch (position){
                        case 4:
                            input4.setText(selection);
                            input4.setVisibility(TextView.VISIBLE);
                            dec4.setVisibility(View.VISIBLE);
                            quantity4.setVisibility(TextView.VISIBLE);
                            inc4.setVisibility(View.VISIBLE);
                            karma4.setVisibility(TextView.VISIBLE);
                            karmaText4.setVisibility(TextView.VISIBLE);
                            position = 1;
                            break;
                        case 3:
                            input3.setText(selection);
                            input3.setVisibility(TextView.VISIBLE);
                            dec3.setVisibility(View.VISIBLE);
                            quantity3.setVisibility(TextView.VISIBLE);
                            inc3.setVisibility(View.VISIBLE);
                            karma3.setVisibility(TextView.VISIBLE);
                            karmaText3.setVisibility(TextView.VISIBLE);
                            position = 4;
                            break;
                        case 2:
                            input2.setText(selection);
                            input2.setVisibility(TextView.VISIBLE);
                            dec2.setVisibility(View.VISIBLE);
                            quantity2.setVisibility(TextView.VISIBLE);
                            inc2.setVisibility(View.VISIBLE);
                            karma2.setVisibility(TextView.VISIBLE);
                            karmaText2.setVisibility(TextView.VISIBLE);
                            position = 3;
                            break;
                        case 1:
                            input1.setText(selection);
                            input1.setVisibility(TextView.VISIBLE);
                            dec1.setVisibility(View.VISIBLE);
                            quantity1.setVisibility(TextView.VISIBLE);
                            inc1.setVisibility(View.VISIBLE);
                            karma1.setVisibility(TextView.VISIBLE);
                            karmaText1.setVisibility(TextView.VISIBLE);
                            position = 2;
                            break;
                    }


                    System.out.println(selection);
                    autotextView.getText().clear();
                }
                return false;
            }
        });


        //set listener for buttons




        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
