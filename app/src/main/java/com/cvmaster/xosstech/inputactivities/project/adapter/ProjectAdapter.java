package com.cvmaster.xosstech.inputactivities.project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder>{

    private Context context;
    private List<Project> projectList;
    private ProjectAdapter.ClickListener clickListener;

    public ProjectAdapter(List<Project> projectList, ProjectAdapter.ClickListener clickListener) {
        this.projectList = projectList;
        this.clickListener = clickListener;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.etProjectName.setText(this.projectList.get(position).getProject_name().toString());
        holder.etProjectSummary.setText(this.projectList.get(position).getProject_summary().toString());
        holder.etStartDate.setText(this.projectList.get(position).getStart().toString());
        holder.etEndDate.setText(this.projectList.get(position).getEnd().toString());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(position);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.deleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.projectList != null) {
            return this.projectList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private EditText etProjectName,etProjectSummary,etStartDate,etEndDate;
        Button btnEdit,btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);

            etProjectName = itemView.findViewById(R.id.etItemProjectName);
            etProjectSummary = itemView.findViewById(R.id.etItemProjectSummary);
            etStartDate = itemView.findViewById(R.id.etItemProjectStart);
            etEndDate = itemView.findViewById(R.id.etItemProjectEnd);

            btnDelete = itemView.findViewById(R.id.btnDeleteProject);
            btnEdit = itemView.findViewById(R.id.btnEditProject);
        }
    }

    public interface ClickListener {
        void itemClick(int position);
        void deleteItem(int position);
    }
}