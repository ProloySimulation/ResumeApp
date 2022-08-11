package com.cvmaster.xosstech.InputActivities.training.adapter;

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
import com.cvmaster.xosstech.model.Training;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.MyViewHolder>{

    private Context context;
    private List<Training> trainingList;
    private TrainingAdapter.ClickListener clickListener;

    public TrainingAdapter(List<Training> trainingList, TrainingAdapter.ClickListener clickListener) {
        this.trainingList = trainingList;
        this.clickListener = clickListener;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrainingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training, parent, false);
        return new TrainingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.etTrainingName.setText(this.trainingList.get(position).getTraining_name());
        holder.etTrainingSummary.setText(this.trainingList.get(position).getTraining_summary());
        holder.etEndDate.setText(this.trainingList.get(position).getEnd());

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
        if(this.trainingList != null) {
            return this.trainingList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText etTrainingName, etTrainingSummary,etEndDate;
        Button btnSubmit,btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            btnSubmit = itemView.findViewById(R.id.btnEditTraining);
            btnDelete = itemView.findViewById(R.id.btnTrainingDelete);
            etTrainingName = (EditText)itemView.findViewById(R.id.editText_BuildResumeTraining_name_1);
            etTrainingSummary = (EditText)itemView.findViewById(R.id.editText_BuildResumeTraining_Summary_1);
            etEndDate = (EditText)itemView.findViewById(R.id.editText_BuildResumeTraining_endDate_1);
        }
    }

    public interface ClickListener {
        void itemClick(int position);
        void deleteItem(int position);
    }
}
