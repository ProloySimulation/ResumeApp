package com.cvmaster.xosstech.InputActivities.education.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Education;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder>{

    private Context context;
    private List<Education> educationList;
    private EducationAdapter.ClickListener clickListener;

    public EducationAdapter(List<Education> educationList, EducationAdapter.ClickListener clickListener) {
        this.educationList = educationList;
        this.clickListener = clickListener;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EducationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_education, parent, false);
        return new EducationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationAdapter.MyViewHolder holder, final int position) {
        holder.etDegree.setText(this.educationList.get(position).getDegree().toString());
        holder.etDept.setText(this.educationList.get(position).getDept().toString());
        holder.etInstitution.setText(this.educationList.get(position).getInst_name().toString());
        holder.etResult.setText(this.educationList.get(position).getResult().toString());
        holder.etPassYear.setText(this.educationList.get(position).getPass_year().toString());
        holder.tvBoard.setText(this.educationList.get(position).getBoard().toString());

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
        if(this.educationList != null) {
            return this.educationList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText etDegree,etInstitution,etDept,etPassYear,etResult;
        TextView tvBoard;
        Button btnSubmit,btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            btnSubmit = itemView.findViewById(R.id.btnEditEducation);
            btnDelete = itemView.findViewById(R.id.btnDeleteEducation);

            etDegree = (EditText)itemView.findViewById(R.id.etItemDegreeName);
            etInstitution = (EditText)itemView.findViewById(R.id.etItemInstituionName);
            tvBoard = itemView.findViewById(R.id.tvItemBoardName);
            etDept = (EditText)itemView.findViewById(R.id.etItemGroupName);
            etPassYear = (EditText)itemView.findViewById(R.id.etItemEducationPassYear);
            etResult = (EditText)itemView.findViewById(R.id.etItemEducationResult);
        }
    }

    public interface ClickListener {
        void itemClick(int position);
        void deleteItem(int position);
    }
}