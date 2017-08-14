package com.qkahmadzai.mtn.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qkahmadzai.mtn.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BundleType.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BundleType#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BundleType extends Fragment {

    CardView cvHourly, cvDaily, cvWeekly, cvMonthly;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_bundle_type, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);


        cvHourly = (CardView) view.findViewById(R.id.cvHourly);
        cvDaily = (CardView) view.findViewById(R.id.cvDaily);
        cvWeekly = (CardView) view.findViewById(R.id.cvWeek);
        cvMonthly = (CardView) view.findViewById(R.id.cvMonthly);

        cvHourly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new PackagesList());
                ft.commit();
            }
        });

        cvDaily.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new PackagesList());
                ft.commit();
            }
        });

        cvWeekly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "cv cvWeekly", Toast.LENGTH_LONG).show();
            }
        });



        cvMonthly.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "cv cvMonthly", Toast.LENGTH_LONG).show();
            }
        });





        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK ) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }
}
