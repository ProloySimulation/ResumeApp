package com.cvmaster.xosstech.InputActivities.reference.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Reference;

import java.util.List;


public class ReferenceAdapter extends RecyclerView.Adapter<ReferenceAdapter.MyViewHolder>{

    private Context context;
    private List<Reference> referenceList;
    private ReferenceAdapter.ClickListener clickListener;

    public ReferenceAdapter(List<Reference> referenceList, ReferenceAdapter.ClickListener clickListener) {
        this.referenceList = referenceList;
        this.clickListener = clickListener;
    }

    public void setExperienceList(List<Reference> referenceList) {
        this.referenceList = referenceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReferenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reference, parent, false);
        return new ReferenceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferenceAdapter.MyViewHolder holder, final int position) {
        holder.etName.setText(this.referenceList.get(position).getName().toString());
        holder.etDesignation.setText(this.referenceList.get(position).getDesignation().toString());
        holder.etOrganizationName.setText(this.referenceList.get(position).getOrganization().toString());
        holder.etEmail.setText(this.referenceList.get(position).getEmail().toString());
        holder.etMobile.setText(this.referenceList.get(position).getMobile().toString());

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
        if(this.referenceList != null) {
            return this.referenceList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText etName, etDesignation, etOrganizationName, etEmail, etMobile;
        Button btnSubmit,btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            btnSubmit = itemView.findViewById(R.id.btnEditReference);
            btnDelete = itemView.findViewById(R.id.btnDeleteReference);

            etName = (EditText)itemView.findViewById(R.id.editText_BuildResumePart5_Reference1_Name);
            etDesignation = (EditText)itemView.findViewById(R.id.editText_BuildResumePart5_Reference1_Designation);
            etOrganizationName = (EditText)itemView.findViewById(R.id.editText_BuildResumePart5_Reference1_OrganizationName);
            etEmail = (EditText)itemView.findViewById(R.id.editText_BuildResumePart5_Reference1_Email);
            etMobile = (EditText)itemView.findViewById(R.id.editText_BuildResumePart5_Reference1_MobileNumber);
        }
    }

    public interface ClickListener {
        void itemClick(int position);
        void deleteItem(int position);
    }
}
