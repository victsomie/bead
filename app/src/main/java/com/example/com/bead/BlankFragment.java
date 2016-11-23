
package com.example.com.bead;


import android.widget.FrameLayout;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FrameLayout mainLayout;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainLayout = (FrameLayout) inflater.inflate(R.layout.fragment_blank, container, false);


        // Inflate the layout for this fragment
        final FrameLayout main = (FrameLayout) mainLayout.findViewById(R.id.main);

        int numViews = 32;
        for(int i = 1; i <= numViews; i++)
        {
            // Create some quick TextViews that can be placed.
            //TextView v = new TextView(this);
            TextView v = new TextView(getActivity());


            // Set a text and center it in each view.
            //v.setText("");

            v.setGravity(Gravity.CENTER);

            if (i==1){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(getResources().getDrawable(R.drawable.bead_bg_notsafe));
                }
            }else if (i<8){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(getResources().getDrawable(R.drawable.bead_bg_safe));
                    //v.setBackground(getResources().getDrawable(R.drawable.bead_bg_safe));
                }
            }else if (i<19){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(getResources().getDrawable(R.drawable.bead_bg));
                    //v.setBackground(getResources().getDrawable(R.drawable.bead_bg_safe));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(getResources().getDrawable(R.drawable.bead_bg));
                }
            }

            //Add listener to get the string
            final String j = v.getText().toString();


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(CircleView.this,  j, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),  j, Toast.LENGTH_SHORT).show();
                }
            });
            v.setPadding(1,1, 1,1);
            // Force the views to a nice size (150x100 px) that fits my display.
            // This should of course be done in a display size independent way.
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(70, 70);
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
        }

        //return inflater.inflate(R.layout.fragment_blank, container, false);
        return mainLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}