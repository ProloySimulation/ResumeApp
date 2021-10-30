package com.cvmaster.xosstech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import com.cvmaster.xosstech.adapter.CvAdapter;
import com.cvmaster.xosstech.model.Cv_Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private CardView cvDashboard;
    private RecyclerView rvProfessional ;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Cv_Model> cvLists;
    private RecyclerView.Adapter adapter;
    String id,category,image,download,price;
    private String cvUrl = "http://xosstech.com/cvm/api/public/api/cv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        rvProfessional = findViewById(R.id.rvProfessional);
        cvDashboard = findViewById(R.id.cvProfileSection);
        cvDashboard.setOnClickListener(this);

        cvLists = new ArrayList<>();
        adapter = new CvAdapter(getApplicationContext(),cvLists);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration = new DividerItemDecoration(rvProfessional.getContext(), linearLayoutManager.getOrientation());

        rvProfessional.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        rvProfessional.setHasFixedSize(true);
        rvProfessional.setLayoutManager(linearLayoutManager);
        rvProfessional.addItemDecoration(dividerItemDecoration);
        rvProfessional.setAdapter(adapter);


        showCv();
    }

    @Override
    public void onClick(View view) {
        if(view == cvDashboard)
        {
            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
            startActivity(intent);
        }
    }

    private void showCv() {

        StringRequest request = new StringRequest(Request.Method.GET, cvUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(HomePage.this, "Data Input Successfully", Toast.LENGTH_SHORT).show();
                                JSONArray cvArray = jsonObject.getJSONArray("cv");

                                for(int j=0;j<cvArray.length();j++)
                                {
                                    JSONObject commentobj = cvArray.getJSONObject(j);
                                    id = commentobj.getString("id");
                                    category = commentobj.getString("category");
                                    image = commentobj.getString("image");
                                    download = commentobj.getString("download");
                                    price = commentobj.getString("price");

                                    Cv_Model cv_model = new Cv_Model(id,category,image,download,price);
                                    cvLists.add(cv_model);

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(HomePage.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomePage.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

         /*   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+"73|0zxBcVO1MOhwZO6KNYdy1drjK11aZMfyXT8naLhn");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

}