package example.admin.com.multipurposetesting;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText tv;
    private ConstraintLayout llRoot;
    private TextView tvvvv, ttvv;
    boolean aBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tvText);
        llRoot = findViewById(R.id.llRoot);
        tvvvv = findViewById(R.id.tvvvv);
        ttvv= findViewById(R.id.ttvv);

        ttvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                tv.setBackgroundTintList(colorStateList);

                if (!aBoolean) {
                    tv.setText("");
                    aBoolean = true;
                }
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search_colour,0,0,0);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(llRoot);
                tv.setLayoutParams(new ConstraintLayout.LayoutParams(0,0));
                constraintSet.connect(tv.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,0);
//                constraintSet.connect(tv.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,0);
                constraintSet.connect(tv.getId(), ConstraintSet.START, ttvv.getId(), ConstraintSet.END,0);
                constraintSet.applyTo(llRoot);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ((ViewGroup) findViewById(R.id.llRoot)).getLayoutTransition()
                            .enableTransitionType(LayoutTransition.CHANGING);
                }
            }
        });

        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(MainActivity.this);
                if (TextUtils.isEmpty(tv.getText().toString().trim())){
//                    tv.setText("hint");

                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(llRoot);
                    tv.setLayoutParams(new ConstraintLayout.LayoutParams(0,0));
                    constraintSet.connect(tv.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,0);
//                    constraintSet.connect(tv.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,0);
                    constraintSet.clear(tv.getId(), ConstraintSet.START);
                    constraintSet.applyTo(llRoot);


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ((ViewGroup) findViewById(R.id.llRoot)).getLayoutTransition()
                                .enableTransitionType(LayoutTransition.CHANGING);
                    }



                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(android.R.color.transparent));
                            tv.setBackgroundTintList(colorStateList);
                            tv.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.search,0);

                        }
                    },250);


                }
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    
}
