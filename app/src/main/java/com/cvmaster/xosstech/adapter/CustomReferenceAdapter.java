package com.cvmaster.xosstech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.model.Reference_Model;

import java.util.List;

public class CustomReferenceAdapter extends BaseAdapter {

    private List<Reference_Model> reference_models;
    private Context context;

    private LayoutInflater inflater;

    public CustomReferenceAdapter(Context context, List<Reference_Model> reference_models) {
        this.reference_models = reference_models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reference_models.size();
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
            view = inflater.inflate(R.layout.sample_reference_view,viewGroup,false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView designation = (TextView) view.findViewById(R.id.designation);
        TextView organization_name = (TextView) view.findViewById(R.id.organization_name);
        TextView email = (TextView) view.findViewById(R.id.email);
        TextView contact = (TextView) view.findViewById(R.id.contact_number);

        Reference_Model reference_model;
        reference_model = reference_models.get(i);

        name.setText(reference_model.getName());
        designation.setText(reference_model.getDesignation());
        organization_name.setText(reference_model.getOrganization_name());
        email.setText(reference_model.getEmail());
        contact.setText(reference_model.getMobile_number());

        return view;
    }
}
