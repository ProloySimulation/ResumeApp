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
import com.cvmaster.xosstech.InputActivities.BuildResumeTrainings;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Training;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingAdapter extends RecyclerView.Adapter <TrainingAdapter.ViewHolder>{

    private Context context;
    private List<Training> trainingList;
    private String token = null;
    private String trainingDeleteUrl = "http://xosstech.com/cvm/api/public/api/training/delete/";

    public TrainingAdapter(Context context, List<Training> trainingList) {
        this.context = context;
        this.trainingList = trainingList;
    }

    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_training, parent, false);
        return new TrainingAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(TrainingAdapter.ViewHolder holder, int position) {

        Training training = trainingList.get(position);
        holder.tvTrainingName.setText(training.getTrainingName());
        holder.tvTrainingSummary.setText(training.getTrainingSummary());
        holder.tvTrainingEndDate.setText(training.getEndDate());
        holder.btnDeleteTraining.setOnClickListener(new View.OnClickListener() {
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
                                String trainingId = training.getId();
                                deleteInformation(trainingId);
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

        holder.btnUpdateTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myactivity = new Intent(context.getApplicationContext(), BuildResumeTrainings.class);
                myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(myactivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTrainingName,tvTrainingSummary,tvTrainingEndDate;
        private Button btnDeleteTraining,btnUpdateTraining;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTrainingName = itemView.findViewById(R.id.tvTrainingName);
            tvTrainingSummary = itemView.findViewById(R.id.tvTrainingSummary);
            tvTrainingEndDate = itemView.findViewById(R.id.tvTrainingEndDate);
            btnDeleteTraining = itemView.findViewById(R.id.btnTrainingDelete);
            btnUpdateTraining = itemView.findViewById(R.id.btnTrainingUpdate);
        }
    }

    private void deleteInformation(String id) {

        token = SharedPreferenceManager.getInstance(context).GetUserToken();

        StringRequest request = new StringRequest(Request.Method.DELETE, trainingDeleteUrl+id,
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
