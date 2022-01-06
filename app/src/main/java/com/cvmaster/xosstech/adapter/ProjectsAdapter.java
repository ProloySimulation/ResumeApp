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
import com.cvmaster.xosstech.InputActivities.BuildResumeProjects;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Projects_model;
import com.cvmaster.xosstech.model.Reference_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectsAdapter extends RecyclerView.Adapter <ProjectsAdapter.ViewHolder>{

    private Context context;
    private List<Projects_model> projects_models;
    private String token = null;
    private String projectDeleteUrl = "http://xosstech.com/cvm/api/public/api/project/delete/";

    public ProjectsAdapter(Context context, List<Projects_model> projects_models) {
        this.context = context;
        this.projects_models = projects_models;
    }

    @Override
    public ProjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_projects, parent, false);
        return new ProjectsAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ProjectsAdapter.ViewHolder holder, int position) {

        Projects_model projects_model = projects_models.get(position);
        holder.tvProjectName.setText(projects_model.getProjectName());
        holder.tvProjectSummary.setText(projects_model.getProjectSummary());
        holder.tvStartDate.setText(projects_model.getStartDate());
        holder.tvEndDate.setText(projects_model.getEndDate());
        holder.btnDeleteProject.setOnClickListener(new View.OnClickListener() {
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
                                String projectId = projects_model.getId();
                                deleteInformation(projectId);
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

        holder.btnUpdateProjetc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myactivity = new Intent(context.getApplicationContext(), BuildResumeProjects.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return projects_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProjectName,tvProjectSummary,tvStartDate,tvEndDate;
        private Button btnDeleteProject,btnUpdateProjetc;

        public ViewHolder(View itemView) {
            super(itemView);

            tvProjectName = itemView.findViewById(R.id.tvProjectName);
            tvProjectSummary = itemView.findViewById(R.id.tvProjectSummary);
            tvStartDate = itemView.findViewById(R.id.tvProjectStartDate);
            tvEndDate = itemView.findViewById(R.id.tvProjectEndDate);
            btnDeleteProject = itemView.findViewById(R.id.btnProjectDelete);
            btnUpdateProjetc = itemView.findViewById(R.id.btnProjectUpdate);

        }
    }

    private void deleteInformation(String id) {

        token = SharedPreferenceManager.getInstance(context).GetUserToken();

        StringRequest request = new StringRequest(Request.Method.DELETE, projectDeleteUrl+id,
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
