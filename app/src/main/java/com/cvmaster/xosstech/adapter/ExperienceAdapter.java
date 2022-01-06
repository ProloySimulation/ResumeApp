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
import com.cvmaster.xosstech.InputActivities.BuildResumePart6;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.SingleJob;
import com.cvmaster.xosstech.model.WorkExperience_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExperienceAdapter extends RecyclerView.Adapter <ExperienceAdapter.ViewHolder> {

    private Context context;
    private List<WorkExperience_Model> workExperience_models;
    private String token = null;
    private String eduDeleteUrl = "http://xosstech.com/cvm/api/public/api/experience/delete/";

    public ExperienceAdapter(Context context, List<WorkExperience_Model> workExperience_models) {
        this.context = context;
        this.workExperience_models = workExperience_models;
    }

    @Override
    public ExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_work, parent, false);
        return new ExperienceAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ExperienceAdapter.ViewHolder holder, int position) {

        WorkExperience_Model workExperience_model = workExperience_models.get(position);
        holder.tvCompanyName.setText(workExperience_model.getOrganizationName());
        holder.tvPosition.setText(workExperience_model.getDesignationName());
        holder.tvStart.setText(workExperience_model.getStartDate());
        holder.tvEnd.setText(workExperience_model.getEndDate());
        holder.tvLocation.setText(workExperience_model.getOgganizationAddress());
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
                                String deleteId = workExperience_model.getId();
                                deleteInformation(deleteId);
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

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myactivity = new Intent(context.getApplicationContext(), BuildResumePart6.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workExperience_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCompanyName,tvPosition,tvStart,tvEnd,tvLocation;
        private Button btnDelete,btnUpdate;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCompanyName = itemView.findViewById(R.id.tvExpName);
            tvPosition = itemView.findViewById(R.id.tvExpDesignation);
            tvStart = itemView.findViewById(R.id.tvExpStart);
            tvEnd = itemView.findViewById(R.id.tvExpEnd);
            tvLocation = itemView.findViewById(R.id.tvExpLocation);
            btnDelete = itemView.findViewById(R.id.btnWorkDelete);
            btnUpdate = itemView.findViewById(R.id.btnWorkUpdate);
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
