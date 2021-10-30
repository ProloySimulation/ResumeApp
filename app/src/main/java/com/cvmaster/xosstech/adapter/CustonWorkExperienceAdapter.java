package com.cvmaster.xosstech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.WorkExperience_Model;

import java.util.List;

public class CustonWorkExperienceAdapter extends BaseAdapter {

    private List<WorkExperience_Model> workExperience;
    private Context context;

    private LayoutInflater inflater;

    public CustonWorkExperienceAdapter(Context context, List<WorkExperience_Model> workExperience) {
        this.workExperience = workExperience;
        this.context = context;
    }

    @Override
    public int getCount() {
        return workExperience.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sample_workexp_view,viewGroup,false);
        }
        TextView designation = (TextView) view.findViewById(R.id.designation);
        TextView duration = (TextView) view.findViewById(R.id.duration);
        TextView organizationname = (TextView) view.findViewById(R.id.organizationname);
        TextView organizationaddress = (TextView) view.findViewById(R.id.organizationaddress);

        WorkExperience_Model workExperience_model;
        workExperience_model = workExperience.get(i);


        designation.setText(workExperience_model.getDesignationName());
//        duration.setText(workExperience_model.getDurationTime());
        organizationname.setText(workExperience_model.getOrganizationName());
        organizationaddress.setText(workExperience_model.getOgganizationAddress());

        return view;
    }
}
