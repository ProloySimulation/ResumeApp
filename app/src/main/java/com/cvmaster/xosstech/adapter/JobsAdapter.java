package com.cvmaster.xosstech.adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SingleJob;
import com.cvmaster.xosstech.model.Cv_Model;
import com.cvmaster.xosstech.model.Jobs;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter <JobsAdapter.ViewHolder>{

    private Context context;
    private List<Jobs> jobsList;

    public JobsAdapter(Context context, List<Jobs> jobList) {
        this.context = context;
        this.jobsList = jobList;
    }

    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_jobs, parent, false);
        return new JobsAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(JobsAdapter.ViewHolder holder, int position) {

        Jobs jobs = jobsList.get(position);
        holder.tvJobTittle.setText(jobs.getJobTitle());
        holder.jobOfficeName.setText(jobs.getOfficeName());
        holder.jobEndDate.setText(jobs.getEndDate());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jobs.setFile(jobs.getFile());
                Jobs.setEndDate(jobs.getEndDate());
                Jobs.setApplyLink(jobs.getApplyLink());
                Jobs.setJobTitle(jobs.getJobTitle());
                Jobs.setOfficeName(jobs.getOfficeName());

                Intent myactivity = new Intent(context.getApplicationContext(), SingleJob.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJobTittle,jobOfficeName,jobEndDate;
        private CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            tvJobTittle = itemView.findViewById(R.id.tvJobTitle);
            jobOfficeName = itemView.findViewById(R.id.officName);
            jobEndDate = itemView.findViewById(R.id.endDate);
            parentLayout = itemView.findViewById(R.id.parentCardJobs);
        }
    }
}
