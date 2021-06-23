package com.cvmaster.xosstech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.fragment_template.Template5Fragment;
import com.cvmaster.xosstech.fragment_template.Templete1Fragment;
import com.cvmaster.xosstech.fragment_template.Templete2Fragment;
import com.cvmaster.xosstech.fragment_template.Templete3Fragment;
import com.cvmaster.xosstech.fragment_template.Templete4Fragment;

public class FragmentShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_show);
        FragmentManager fragmentManager = getSupportFragmentManager();
        int value = getIntent().getIntExtra("t", 1);

        if (value == 1) {
            fragmentManager.beginTransaction().replace(R.id.containerFragemtID, new Templete1Fragment()).commit();

        }
        if (value == 2) {
            fragmentManager.beginTransaction().replace(R.id.containerFragemtID, new Templete2Fragment()).commit();

        }
        if (value == 3) {
            fragmentManager.beginTransaction().replace(R.id.containerFragemtID, new Templete3Fragment()).commit();

        }
        if (value == 4) {
            fragmentManager.beginTransaction().replace(R.id.containerFragemtID, new Templete4Fragment()).commit();

        }

        if (value == 5){
            fragmentManager.beginTransaction().replace(R.id.containerFragemtID, new Template5Fragment()).commit();
        }
    }
}