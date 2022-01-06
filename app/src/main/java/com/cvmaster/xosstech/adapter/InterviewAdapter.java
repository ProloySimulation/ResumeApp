package com.cvmaster.xosstech.adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SingleJob;
import com.cvmaster.xosstech.model.Interview;
import com.cvmaster.xosstech.model.Jobs;

import java.util.List;

public class InterviewAdapter extends RecyclerView.Adapter <InterviewAdapter.ViewHolder>{

    private Context context;
    private List<Interview> interviewList;

    public InterviewAdapter(Context context, List<Interview> interviewList) {
        this.context = context;
        this.interviewList = interviewList;
    }

    @Override
    public InterviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_tips, parent, false);
        return new InterviewAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(InterviewAdapter.ViewHolder holder, int position) {

        Interview interview = interviewList.get(position);
        holder.tvJobTittle.setText(interview.getTips());
    }

    @Override
    public int getItemCount() {
        return interviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJobTittle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJobTittle = itemView.findViewById(R.id.tvTips);
        }
    }
}
