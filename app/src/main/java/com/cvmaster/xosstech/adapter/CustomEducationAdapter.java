package com.cvmaster.xosstech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.R;

import java.util.List;

public class CustomEducationAdapter extends BaseAdapter {

    private Context context;
    List<EducationQualification_Model> educationQualifications;
    private LayoutInflater inflater;

    public CustomEducationAdapter(Context context, List<EducationQualification_Model> educationQualifications) {
        this.context = context;
        this.educationQualifications = educationQualifications;
    }

    @Override
    public int getCount() {
        return educationQualifications.size();
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
            view = inflater.inflate(R.layout.sample_education_view,viewGroup,false);
        }

        TextView qualification_name = (TextView) view.findViewById(R.id.qualification_name);
        TextView institute_name = (TextView) view.findViewById(R.id.institute_name);
        TextView board_name = (TextView) view.findViewById(R.id.board_name);
        TextView groupsubject_name = (TextView) view.findViewById(R.id.groupsubject_name);
        TextView passing_year = (TextView) view.findViewById(R.id.passing_year);
        TextView result = (TextView) view.findViewById(R.id.result);

        EducationQualification_Model educationQualification_model;
        educationQualification_model = educationQualifications.get(i);

        qualification_name.setText(educationQualification_model.getQualification_name());
        institute_name.setText(educationQualification_model.getInstitute_name());
        board_name.setText(educationQualification_model.getBoard_name());
        groupsubject_name.setText(educationQualification_model.getGroupsubject_name());
        passing_year.setText(educationQualification_model.getPassing_year());
        result.setText(educationQualification_model.getResult());

        return view;
    }
}
