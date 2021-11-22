package com.cvmaster.xosstech.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Cv_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CvAdapter extends RecyclerView.Adapter <CvAdapter.ViewHolder>{

    private Context context;
    private List<Cv_Model> cvList;

    public CvAdapter(Context context, List<Cv_Model> cvList) {
        this.context = context;
        this.cvList = cvList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cv, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Cv_Model question = cvList.get(position);
        Picasso.with(context).load(question.getImage()).into(holder.imvCv);
        
    }

    @Override
    public int getItemCount() {
        return cvList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvCv;

        public ViewHolder(View itemView) {
            super(itemView);

            imvCv = itemView.findViewById(R.id.imvCv);

        }
    }
}
