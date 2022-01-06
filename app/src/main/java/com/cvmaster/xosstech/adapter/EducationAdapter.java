package com.cvmaster.xosstech.adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import com.cvmaster.xosstech.InputActivities.BuildResumePart6;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.EducationQualification_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EducationAdapter extends RecyclerView.Adapter <EducationAdapter.ViewHolder>{

    private Context context;
    private List<EducationQualification_Model> educationQualification_modelList;
    private String token = null;
    private String eduDeleteUrl = "http://xosstech.com/cvm/api/public/api/education/delete/";

    public EducationAdapter(Context context, List<EducationQualification_Model> educationQualification_modelList) {
        this.context = context;
        this.educationQualification_modelList = educationQualification_modelList;
    }

    @Override
    public EducationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_edu, parent, false);
        return new EducationAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(EducationAdapter.ViewHolder holder, int position) {

        EducationQualification_Model educationQualification_model = educationQualification_modelList.get(position);
        holder.tvCertificateName.setText(educationQualification_model.getQualification_name());
        holder.tvInstitutionName.setText(educationQualification_model.getInstitute_name());
        holder.tvDept.setText(educationQualification_model.getGroupsubject_name());
        holder.tvBoardName.setText(educationQualification_model.getBoard_name());
        holder.tvPassaYear.setText(educationQualification_model.getPassing_year());
        holder.tvResult.setText(educationQualification_model.getResult());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myactivity = new Intent(context.getApplicationContext(), BuildResumePart2.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getRootView().getContext());
                builder1.setMessage("Are you Sure, You Want To Delete It ?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                String eduId = educationQualification_model.getId();
                                deleteInformation(eduId);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                //startActivity(new Intent(ChargingActivity.this,UserProfileActivity.class));
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return educationQualification_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCertificateName,tvInstitutionName,tvDept,tvBoardName,tvPassaYear,tvResult,tvCount;
        private Button btnDelete,btnUpdate;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCertificateName = itemView.findViewById(R.id.tvCertificateName);
            tvCertificateName = itemView.findViewById(R.id.tvCertificateName);
            tvInstitutionName = itemView.findViewById(R.id.tvInstName);
            tvDept = itemView.findViewById(R.id.tvDepartmentName);
            tvBoardName = itemView.findViewById(R.id.tvBoardName);
            tvPassaYear = itemView.findViewById(R.id.tvPassingYear);
            tvResult = itemView.findViewById(R.id.tvResult);
            tvCount = itemView.findViewById(R.id.tvEduCount);
            btnDelete = itemView.findViewById(R.id.btnEduDelete);
            btnUpdate = itemView.findViewById(R.id.btnEduUpdate);
        }
    }

    private void deleteInformation(String id) {

        token = SharedPreferenceManager.getInstance(context).GetUserToken();

        StringRequest request = new StringRequest(Request.Method.DELETE, eduDeleteUrl+id,
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
