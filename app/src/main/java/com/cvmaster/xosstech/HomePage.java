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

    private CardView cvDashboard,cvUpcomingJobs;
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

        cvDashboard = findViewById(R.id.cvProfileSection);
        cvDashboard.setOnClickListener(this);

        cvUpcomingJobs = findViewById(R.id.upcomingJobs);
        cvUpcomingJobs.setOnClickListener(this);

        cvLists = new ArrayList<>();
        adapter = new CvAdapter(getApplicationContext(),cvLists);
    }

    @Override
    public void onClick(View view) {
        if(view == cvDashboard)
        {
            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
            startActivity(intent);
        }

        if (view == cvUpcomingJobs)
        {
            Intent intent = new Intent(getApplicationContext(), JobsActivity.class);
            startActivity(intent);
        }
    }

}