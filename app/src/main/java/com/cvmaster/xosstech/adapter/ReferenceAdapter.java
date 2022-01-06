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
import com.cvmaster.xosstech.InputActivities.BuildResumePart5;
import com.cvmaster.xosstech.InputActivities.BuildResumePart6;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceAdapter extends RecyclerView.Adapter <ReferenceAdapter.ViewHolder>{

    private Context context;
    private List<Reference_Model> reference_models;
    private String token = null;
    private String eduDeleteUrl = "http://xosstech.com/cvm/api/public/api/reference/delete/";

    public ReferenceAdapter(Context context, List<Reference_Model> reference_models) {
        this.context = context;
        this.reference_models = reference_models;
    }

    @Override
    public ReferenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_reference, parent, false);
        return new ReferenceAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ReferenceAdapter.ViewHolder holder, int position) {

        Reference_Model reference_model = reference_models.get(position);
        holder.tvRefName.setText(reference_model.getName());
        holder.tvRefCompanyName.setText(reference_model.getOrganization_name());
        holder.tvRefDesignation.setText(reference_model.getDesignation());
        holder.tvRefEmail.setText(reference_model.getEmail());
        holder.tvRefMobile.setText(reference_model.getMobile_number());
        holder.btnDeleteRef.setOnClickListener(new View.OnClickListener() {
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
                                String referenceId = reference_model.getId();
                                deleteInformation(referenceId);
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

        holder.btnUpdateRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myactivity = new Intent(context.getApplicationContext(), BuildResumePart5.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reference_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRefName,tvRefCompanyName,tvRefDesignation,tvRefEmail,tvRefMobile;
        private Button btnDeleteRef,btnUpdateRef;

        public ViewHolder(View itemView) {
            super(itemView);

            tvRefName = itemView.findViewById(R.id.tvRefName);
            tvRefCompanyName = itemView.findViewById(R.id.tvRefCompanyName);
            tvRefDesignation = itemView.findViewById(R.id.tvRefDesignation);
            tvRefEmail = itemView.findViewById(R.id.tvRefEmail);
            tvRefMobile = itemView.findViewById(R.id.tvRefPhone);
            btnDeleteRef = itemView.findViewById(R.id.btnRefDelete);
            btnUpdateRef = itemView.findViewById(R.id.btnRefUpdate);
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
