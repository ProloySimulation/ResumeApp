package com.cvmaster.xosstech.inputactivities.experience.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Experience;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>{

    private Context context;
    private List<Experience> experienceList;
    private ClickListener clickListener;

    public ExperienceAdapter(List<Experience> experienceList,ClickListener clickListener) {
        this.experienceList = experienceList;
        this.clickListener = clickListener;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExperienceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceAdapter.MyViewHolder holder, final int position) {
        holder.tvDesignation.setText(this.experienceList.get(position).getPosition().toString());
        holder.tvCompanyAddress.setText(this.experienceList.get(position).getLocation().toString());
        holder.tvCompanyName.setText(this.experienceList.get(position).getCompany_name().toString());
        holder.tvStartDate.setText(this.experienceList.get(position).getStart().toString());
        holder.tvEndDate.setText(this.experienceList.get(position).getEnd().toString());
        holder.tvWorkDetail.setText(this.experienceList.get(position).getWork_summary().toString());

        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(position);
//                notifyItemChanged(position);
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
        if(this.experienceList != null) {
            return this.experienceList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText tvDesignation,tvWorkDetail,tvStartDate,tvEndDate,tvCompanyName,tvCompanyAddress;
        Button btnSubmit,btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            btnSubmit = itemView.findViewById(R.id.btnSubmit);
            btnDelete = itemView.findViewById(R.id.btnDeleteExperience);
            tvDesignation = (EditText)itemView.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_Designation);
            tvWorkDetail = (EditText)itemView.findViewById(R.id.editText_BuildResumePart6_WorkExperience_Description_1);
            tvStartDate = (EditText)itemView.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_start);
            tvEndDate = (EditText)itemView.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_end);
            tvCompanyName = (EditText)itemView.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationName);
            tvCompanyAddress = (EditText)itemView.findViewById(R.id.editText_BuildResumePart6_WorkExperience1_OrganizationAddress1);

            tvDesignation.setEnabled(false);
            tvWorkDetail.setEnabled(false);
            tvStartDate.setEnabled(false);
            tvEndDate.setEnabled(false);
            tvCompanyName.setEnabled(false);
            tvCompanyAddress.setEnabled(false);
        }
    }

    public interface ClickListener {
        void itemClick(int position);
        void deleteItem(int position);
    }
}
