package com.qkahmadzai.mtn.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qkahmadzai.mtn.Adpaters.InternetBundles_Rv_Adapter;
import com.qkahmadzai.mtn.Database.DBHelper;
import com.qkahmadzai.mtn.Database.Packages;
import com.qkahmadzai.mtn.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackagesList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackagesList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackagesList extends Fragment {

    RecyclerView rv;
    public List<Packages> pk;
    DBHelper db;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_packages_list, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        db = new DBHelper(getContext());
        rv = (RecyclerView) view.findViewById(R.id.rvPackagesList);
        pk = db.getAllPackages();

        InternetBundles_Rv_Adapter adapter = new InternetBundles_Rv_Adapter(getActivity(), pk);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));



    }
}
