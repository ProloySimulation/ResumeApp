package com.cvmaster.xosstech.Storyboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cvmaster.xosstech.HomePage;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.SignUp.UserSignInPart1;

public class StoryBoard extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private TextView[] dots;
    private LinearLayout dotLayout;
    private Button btnSignUp ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (SharedPreferenceManager.getInstance(this).IsUserLoggedIn()){
            finish();
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

        dotLayout = findViewById(R.id.inDicatiorLayout);
        viewPager = findViewById(R.id.viewPagerSplash);
        btnSignUp = findViewById(R.id.btnMakeCv);
        btnSignUp.setOnClickListener(this);
        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        viewPager.addOnPageChangeListener(viewListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position){

        dots = new TextView[3];
        dotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            dotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 1){

                btnSignUp.setVisibility(View.VISIBLE);

            }else {

                btnSignUp.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View view) {
        if(view == btnSignUp)
        {
            Intent intent = new Intent(getApplicationContext(), UserSignInPart1.class);
            startActivity(intent);
        }
    }
}