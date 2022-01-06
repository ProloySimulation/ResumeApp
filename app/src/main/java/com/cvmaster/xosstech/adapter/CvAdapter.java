package com.cvmaster.xosstech.adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ShowPdf;
import com.cvmaster.xosstech.SingleJob;
import com.cvmaster.xosstech.model.Cv_Model;
import com.cvmaster.xosstech.model.Jobs;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CvAdapter extends RecyclerView.Adapter <CvAdapter.ViewHolder>{

    private Context context;
    private List<Cv_Model> cv_modelList;

    public CvAdapter(Context context, List<Cv_Model> cv_modelList) {
        this.context = context;
        this.cv_modelList = cv_modelList;
    }

    @Override
    public CvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cv, parent, false);
        return new CvAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(CvAdapter.ViewHolder holder, int position) {

        Cv_Model cv_model = cv_modelList.get(position);

        Picasso.with(context).load(cv_model.getImage()).into(holder.imvCv);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myactivity = new Intent(context.getApplicationContext(), ShowPdf.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cv_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imvCv;
        private CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imvCv = itemView.findViewById(R.id.imvCv);
            parentLayout = itemView.findViewById(R.id.parentLayoutCv);
        }
    }
}
