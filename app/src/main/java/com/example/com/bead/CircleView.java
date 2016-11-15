package com.example.com.bead;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
//import android.support.v4.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class CircleView extends AppCompatActivity
        implements ColorCodeFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener
{


    ColorCodeFragment fragmentColorCode;
    AboutFragment fragmentAbout;
    BlankFragment fragmentBlank;
    //FragmentManager fm;
    FragmentManager fragmentManager;

    FragmentTransaction ft;

    List<Fragment> activeCenterFragments;


    FragmentManager ftTry;


    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);

        fragmentColorCode = new ColorCodeFragment();
        fragmentAbout = new AboutFragment();
        fragmentBlank =  new BlankFragment();
        //fm = getFragmentManager();
        fragmentManager = getFragmentManager();




        final FrameLayout main = (FrameLayout)findViewById(R.id.main);

        int numViews = 32;
        /*
        for(int i = 1; i <= numViews; i++)
        {
            // Create some quick TextViews that can be placed.
            TextView v = new TextView(this);


            // Set a text and center it in each view.
            v.setText("" + i);

            v.setGravity(Gravity.CENTER);

           if (i<5){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(getResources().getDrawable(R.drawable.bead_bg_safe));
                }
            } else if (i>20) {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                   v.setBackground(getResources().getDrawable(R.drawable.bead_bg_notsafe));
               }
           }else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(getResources().getDrawable(R.drawable.bead_bg));
                }
            }

            //Add listener to get the string
            final String j = v.getText().toString();


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CircleView.this,  j, Toast.LENGTH_SHORT).show();
                }
            });
            v.setPadding(2,2, 2,2);
            // Force the views to a nice size (150x100 px) that fits my display.
            // This should of course be done in a display size independent way.
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(100, 100);
            // Place all views in the center of the layout. We'll transform them
            // away from there in the code below.
            //lp.gravity = Gravity.CENTER;
            lp.gravity = Gravity.CENTER;
            // Set layout params on view.
            v.setLayoutParams(lp);

            // Calculate the angle of the current view. Adjust by 90 degrees to
            // get View 0 at the top. We need the angle in degrees and radians.
            float angleDeg = i * 360.0f / numViews - 90.0f;
            float angleRad = (float)(angleDeg * Math.PI / 180.0f);
            // Calculate the position of the view, offset from center (300 px from
            // center). Again, this should be done in a display size independent way.
            v.setTranslationX(400 * (float)Math.cos(angleRad));
            v.setTranslationY(400 * (float)Math.sin(angleRad));
            // Set the rotation of the view.
            v.setRotation(angleDeg + 90.0f);
            main.addView(v);
            /
        }
        */


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);



        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {


                ftTry = getFragmentManager();


                ft = fragmentManager.beginTransaction();

                if (tabId == R.id.tab_home){
                    /*
                    ft.replace(R.id.major, fragmentColorCode);
                    ft.commit();
                    if (ft!= null ){
                        Toast.makeText(CircleView.this, "No null", Toast.LENGTH_SHORT).show();
                    }

                    */
                    fragment = fragmentBlank;
                    ftTry.beginTransaction().replace(R.id.major, fragment).commit();

                }else if (tabId == R.id.tab_colorkey){

                    /*
                    if (fragmentColorCode.isAdded()){
                        return;
                    }

                    ft.replace(R.id.major, fragmentColorCode);
                    //activeCenterFragments.add(colorFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                    */
                    fragment = fragmentColorCode;
                    ftTry.beginTransaction().replace(R.id.major, fragment).commit();

                }else if (tabId == R.id.tab_about){
                    /*
                    ft.replace(R.id.major, fragmentAbout);
                    ft.commit();
                    */

                    fragment = fragmentAbout;
                    ftTry.beginTransaction().replace(R.id.major, fragment).commit();

                }else if (tabId == R.id.tab_directions){

                }


            }
        });




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    //Removing all fragments

    private void removeActiveCenterFragments() {
        activeCenterFragments = new ArrayList<Fragment>();
        if (activeCenterFragments.size() > 0) {
            //fragmentTransaction = fragmentManager.beginTransaction();
            for (Fragment activeFragment : activeCenterFragments) {
                ft.remove(activeFragment);
            }
            activeCenterFragments.clear();
            ft = fragmentManager.beginTransaction();
            ft.addToBackStack(null);
            ft.commit();
        }
    }


}
