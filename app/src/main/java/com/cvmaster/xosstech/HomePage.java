package com.cvmaster.xosstech;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.Profile.DashBoardActivity;
import com.cvmaster.xosstech.adapter.CvAdapter;
import com.cvmaster.xosstech.adapter.EducationAdapter;
import com.cvmaster.xosstech.model.Cv_Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private CardView cvDashboard,cvUpcomingJobs,cvInterviewTips,cvCoverLetter;
    private int PERMISSION_REQUEST = 0;
    private boolean allowSave = true;
    private String mobile = "01";
    private String image = null;
    private RecyclerView rvAllCv ;

    private RecyclerView.Adapter adapterCv;
    List<Cv_Model>cvLists;
    private LinearLayoutManager linearLayoutManageredu ;
    private DividerItemDecoration dividerItemDecoration;

    private String cvAPI = "http://xosstech.com/cvm/api/public/api/cv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        rvAllCv = findViewById(R.id.rvAllCv);
        cvDashboard = findViewById(R.id.cvProfileSection);
        cvDashboard.setOnClickListener(this);

        cvUpcomingJobs = findViewById(R.id.upcomingJobs);
        cvUpcomingJobs.setOnClickListener(this);

        cvInterviewTips = findViewById(R.id.cvInterViewTips);
        cvInterviewTips.setOnClickListener(this);

        cvCoverLetter = findViewById(R.id.cvCoverLatter);
        cvCoverLetter.setOnClickListener(this);


        cvLists = new ArrayList<>();
        adapterCv = new CvAdapter(HomePage.this,cvLists);

        linearLayoutManageredu = new LinearLayoutManager(getApplicationContext());
        linearLayoutManageredu.setOrientation(LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration = new DividerItemDecoration(rvAllCv.getContext(), linearLayoutManageredu.getOrientation());

        rvAllCv.setHasFixedSize(true);
        rvAllCv.setLayoutManager(linearLayoutManageredu);
        rvAllCv.addItemDecoration(dividerItemDecoration);
        rvAllCv.setAdapter(adapterCv);


        /*Intent intent = getIntent();
        mobile = intent.getStringExtra("MOBILE_NUMBER");*/

        checkPermission();

        showCv();

    }

    private void showCv()
    {
        StringRequest request = new StringRequest(Request.Method.GET, cvAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("cv");
                            for (int i = 0; i < dataArray.length(); i++)
                            {
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                image = dataobj.getString("image");

                                Cv_Model cv_model = new Cv_Model(image);
                                cvLists.add(cv_model);
                            }


                        }
                        catch (Exception e){
                            e.printStackTrace();
                            //    Toast.makeText(ActivityRegistration.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }

                        adapterCv.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    Toast.makeText(ActivityRegistration.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    private void checkPermission()
    {
        if (!allowSave)
            return;
        allowSave = false;

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PERMISSION_GRANTED) {

        }

        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults[Arrays.asList(permissions).indexOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)] == PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onClick(View view) {
        if(view == cvDashboard)
        {
            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);

            if(mobile.equals("01987982903"))
            {
                intent.putExtra("MOBILE_NUMBER",mobile);
            }

            startActivity(intent);
        }

        if (view == cvUpcomingJobs)
        {
            Intent intent = new Intent(getApplicationContext(), JobsActivity.class);
            startActivity(intent);
        }

        if (view == cvInterviewTips)
        {
            Intent intent = new Intent(getApplicationContext(), InterviewActivity.class);
            startActivity(intent);
        }

        if (view == cvCoverLetter)
        {
            Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
        }
    }

}