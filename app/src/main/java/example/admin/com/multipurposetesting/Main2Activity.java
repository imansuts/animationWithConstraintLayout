package example.admin.com.multipurposetesting;

import android.animation.LayoutTransition;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    ImageView img_one, img_two, img_three;
    FloatingActionButton fab;
    LinearLayout linearLayout;
    ConstraintLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        fab = findViewById(R.id.fab);
        linearLayout = findViewById(R.id.linearLayout);
        llRoot = findViewById(R.id.llRoot);
        img_one = findViewById(R.id.img_one);
        img_two = findViewById(R.id.img_two);
        img_three = findViewById(R.id.img_three);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.getWidth()<50) {
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(llRoot);
                    constraintSet.constrainWidth(linearLayout.getId(), ConstraintSet.WRAP_CONTENT);
                    constraintSet.applyTo(llRoot);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ((ViewGroup) findViewById(R.id.llRoot)).getLayoutTransition()
                                .enableTransitionType(LayoutTransition.CHANGING);
                    }
                }else {
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(llRoot);
                    constraintSet.constrainWidth(linearLayout.getId(), 0);
                    constraintSet.applyTo(llRoot);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ((ViewGroup) findViewById(R.id.llRoot)).getLayoutTransition()
                                .enableTransitionType(LayoutTransition.CHANGING);
                    }
                }
            }
        });

        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(llRoot);
                constraintSet.constrainWidth(linearLayout.getId(), 0);
                constraintSet.applyTo(llRoot);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ((ViewGroup) findViewById(R.id.llRoot)).getLayoutTransition()
                            .enableTransitionType(LayoutTransition.CHANGING);
                }
            }
        });

        img_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Option One", Toast.LENGTH_SHORT).show();
            }
        });
        img_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Option Two", Toast.LENGTH_SHORT).show();
            }
        });
        img_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Option Three", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
