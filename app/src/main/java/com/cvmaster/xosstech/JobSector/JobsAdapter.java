package com.cvmaster.xosstech.JobSector;

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
import com.cvmaster.xosstech.model.Jobs;

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

        String tittle = jobs.getJobTitle();
        String office = jobs.getOfficeName();
        String endDate = jobs.getEndDate();
        String applyLink = jobs.getApplyLink();
        String circularPath = jobs.getFile();

        holder.tvJobTittle.setText(tittle);
        holder.jobOfficeName.setText(office);
        holder.jobEndDate.setText(endDate);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myactivity = new Intent(context.getApplicationContext(), SingleJob.class);
                myactivity.putExtra("jobtittle",tittle);
                myactivity.putExtra("joboffice",office);
                myactivity.putExtra("jobenddate",endDate);
                myactivity.putExtra("applylink",applyLink);
                myactivity.putExtra("circualrpath",circularPath);
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
