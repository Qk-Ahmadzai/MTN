package com.qkahmadzai.mtn.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qkahmadzai.mtn.MainActivity;
import com.qkahmadzai.mtn.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {


    CardView cvInternetB, cvSmsB, cvVoiceB, cvSocial;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_main, parent, false);

    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        cvInternetB = (CardView) view.findViewById(R.id.cvIB);
        cvVoiceB = (CardView) view.findViewById(R.id.cvVS);
        cvSmsB = (CardView) view.findViewById(R.id.cvSB);
        cvSocial = (CardView) view.findViewById(R.id.cvSocialB);

        cvInternetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Pressed Internet Bundles", Toast.LENGTH_LONG).show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new BundleType());
                ft.commit();
            }
        });

        cvVoiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Pressed Internet Bundles", Toast.LENGTH_LONG).show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new BundleType());
                ft.commit();
            }
        });

        cvSmsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Pressed Internet Bundles", Toast.LENGTH_LONG).show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new BundleType());
                ft.commit();
            }
        });

        cvSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Pressed Internet Bundles", Toast.LENGTH_LONG).show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new BundleType());
                ft.commit();
            }
        });

    }

    public void btnInternetBundles(){
       // Toast.makeText(this.getActivity(), "Pressed btnInternetBundles ", Toast.LENGTH_LONG).show();
    }

}
