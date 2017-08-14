package com.qkahmadzai.mtn.Adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qkahmadzai.mtn.Database.DBHelper;
import com.qkahmadzai.mtn.Database.Packages;
import com.qkahmadzai.mtn.R;

import java.util.List;

/**
 * Created by Qiyamuddin Ahmadzai on 8/4/2017.
 */

public class InternetBundles_Rv_Adapter extends RecyclerView.Adapter<InternetBundles_Rv_Adapter.ViewHolder> {

    private List<Packages> mContacts;

    private Context mContext;

    public InternetBundles_Rv_Adapter(Context context, List<Packages> contacts) {
        mContacts = contacts;
        mContext = context;

    }

    private Context getContext() {
        return mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.rv_internet_bundles_custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Packages pk = mContacts.get(position);

        TextView tvBundleType = holder.tvBundleType;
        TextView tvVolume = holder.tvVolume;
        TextView tvValidation = holder.tvValidation;
        TextView tvCharges = holder.tvCharges;
        TextView tvSubMethod = holder.tvSubMethod;

        tvBundleType.setText(pk.getBundleType());
        tvVolume.setText(pk.getVolume());
        tvValidation.setText(pk.getValidation());
        tvCharges.setText(pk.getPrice());
        tvSubMethod.setText(pk.getSubMethod());

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    //////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvBundleType, tvVolume, tvValidation, tvCharges, tvSubMethod;
        public Button btnActive, btnDeactive;


        public ViewHolder(View itemView) {
            super(itemView);
            final List<Packages> pk;
            DBHelper db;

            db = new DBHelper(getContext());
            pk = db.getAllPackages();

            tvBundleType = (TextView) itemView.findViewById(R.id.tvBundleType);
            tvVolume = (TextView) itemView.findViewById(R.id.tvVolume);
            tvValidation = (TextView) itemView.findViewById(R.id.tvValidation);
            tvCharges = (TextView) itemView.findViewById(R.id.tvCharges);
            tvSubMethod = (TextView) itemView.findViewById(R.id.tvSubMethod);

            btnActive = (Button) itemView.findViewById(R.id.btnActive);
            btnDeactive = (Button) itemView.findViewById(R.id.btnDeactive);


            btnActive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Pressed Active!"+ pk.get(getAdapterPosition()).getSubMethod() , Toast.LENGTH_LONG).show();
                }
            });

            btnDeactive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Pressed DeActive!", Toast.LENGTH_LONG).show();
                }
            });


        }



}

////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////

}
