/*
package com.cvmaster.xosstech.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.InputActivities.BuildResumePart2;
import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Interview;
import com.cvmaster.xosstech.model.Item;
import com.cvmaster.xosstech.model.Reference_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;
    private Context context;
    private String id = null;
    private String token = null;
    private String eduDeleteUrl = "http://xosstech.com/cvm/api/public/api/education/delete/";
    private String refDeleteUrl = "http://xosstech.com/cvm/api/public/api/reference/delete/";

    public ProfileAdapter(List<Item> items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0)
        {
            return new EducationViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_edu,
                            parent,
                            false
                    )
            );
        }
        else {
            return new ReferenceViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_reference,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0)
        {
            EducationQualification_Model education =(EducationQualification_Model) items.get(position).getObject();
            ((EducationViewHolder)holder).setEducation(education,position);
        }
        if(getItemViewType(position)==1)
        {
            Reference_Model reference_model =(Reference_Model) items.get(position).getObject();
            ((ReferenceViewHolder)holder).setReference(reference_model);

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

     class EducationViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCertificateName,tvInstitutionName,tvDept,tvBoardName,tvPassaYear,tvResult,tvCount;
        private Button btnDelete;

        EducationViewHolder(@NonNull View itemView){
            super(itemView);

            tvCertificateName = itemView.findViewById(R.id.tvCertificateName);
            tvInstitutionName = itemView.findViewById(R.id.tvInstName);
            tvDept = itemView.findViewById(R.id.tvDepartmentName);
            tvBoardName = itemView.findViewById(R.id.tvBoardName);
            tvPassaYear = itemView.findViewById(R.id.tvPassingYear);
            tvResult = itemView.findViewById(R.id.tvResult);
            tvCount = itemView.findViewById(R.id.tvEduCount);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        void setEducation(EducationQualification_Model education,int position)
        {
            List<EducationQualification_Model> educationQualification_modelList;
            education = educationQualification_modelList.get(position);
            tvCertificateName.setText(education.getQualification_name());
            tvInstitutionName.setText(education.getInstitute_name());
            tvDept.setText(education.getGroupsubject_name());
            tvBoardName.setText(education.getBoard_name());
            tvPassaYear.setText(education.getPassing_year());
            tvResult.setText(education.getResult());
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = education.getId();
                    Toast.makeText(context.getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                    updateInformation(eduDeleteUrl,id);
                }
            });
            tvCount.setText(String.valueOf(i++));

        }
    }

     class ReferenceViewHolder extends RecyclerView.ViewHolder{

        private TextView tvRefName,tvRefCompanyName,tvRefDesignation,tvRefEmail,tvRefMobile;
        private Button btnDeleteRef;

        ReferenceViewHolder(@NonNull View itemView){
            super(itemView);

            tvRefName = itemView.findViewById(R.id.tvRefName);
            tvRefCompanyName = itemView.findViewById(R.id.tvRefCompanyName);
            tvRefDesignation = itemView.findViewById(R.id.tvRefDesignation);
            tvRefEmail = itemView.findViewById(R.id.tvRefEmail);
            tvRefMobile = itemView.findViewById(R.id.tvRefPhone);
            btnDeleteRef = itemView.findViewById(R.id.btnRefDelete);
        }

        void setReference(Reference_Model reference_model)
        {
            tvRefName.setText(reference_model.getName());
            tvRefCompanyName.setText(reference_model.getOrganization_name());
            tvRefDesignation.setText(reference_model.getDesignation());
            tvRefEmail.setText(reference_model.getEmail());
            tvRefMobile.setText(reference_model.getMobile_number());
            btnDeleteRef.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = reference_model.getId();
                    updateInformation(refDeleteUrl,id);
                }
            });
        }
    }

    private void updateInformation(String url,String id) {

        token = SharedPreferenceManager.getInstance(context).GetUserToken();

        StringRequest request = new StringRequest(Request.Method.DELETE, url+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(context, "Delete",Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };

  RequestQueue requestQueue = Volley.newRequestQueue(context);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

}
*/
