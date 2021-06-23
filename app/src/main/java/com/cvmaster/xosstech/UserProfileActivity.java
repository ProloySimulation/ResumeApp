package com.cvmaster.xosstech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.InputActivities.BuildResumePart1;
import com.cvmaster.xosstech.model.ResumeTemplate;
import com.cvmaster.xosstech.showallpdf.ShowMyPDF;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView_UserMobileNumber;
    private Button button_MakeResume;
    private Button button_ShowMyPDFs;
    private Button button_SignOut;
    public CardView ll_BT1;
    public CardView ll_BT2;
    public CardView ll_BT3;
    public CardView ll_BT4;
    public CardView ll_BT5;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    private long backPressedTime;
    private Toast backToast;
    NavigationView navigationView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        drawerLayout = findViewById(R.id.Drawer);
        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);

        if (!SharedPreferenceManager.getInstance(this).IsUserLoggedIn()){
            finish();
            Intent intent = new Intent(this,UserSignInPart1.class);
            startActivity(intent);
        }
        navigationView2 = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView2.getHeaderView(0);
        TextView textView = header.findViewById(R.id.profile_phone_number);
        textView.setText("+88"+SharedPreferenceManager.getInstance(this).GetUserMobileNumber());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch (id){
                    case R.id.menu_Saved_Items:
                        finish();
                        Intent i = new Intent(UserProfileActivity.this,ShowMyPDF.class);
                        startActivity(i);
                        break;
                    case R.id.menu_Log_Out:
                        checkLogout();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        ll_BT1 = (CardView) findViewById(R.id.card_temp1);
        ll_BT1.setOnClickListener(this);

        ll_BT2 = (CardView) findViewById(R.id.card_temp2);
        ll_BT2.setOnClickListener(this);

        ll_BT3 = (CardView) findViewById(R.id.card_temp3);
        ll_BT3.setOnClickListener(this);

        ll_BT4 = (CardView) findViewById(R.id.card_temp4);
        ll_BT4.setOnClickListener(this);

        ll_BT5 = findViewById(R.id.card_temp5);
        ll_BT5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view== ll_BT1){
            ResumeTemplate.templateName = "template1";
            finish();
            Intent intent = new Intent(getApplicationContext(), BuildResumePart1.class);
            startActivity(intent);
        }

        if (view == ll_BT2){
            ResumeTemplate.templateName = "template2";
            finish();
            Intent intent = new Intent(getApplicationContext(),BuildResumePart1.class);
            startActivity(intent);
        }

        if (view == ll_BT3){
            ResumeTemplate.templateName = "template3";
            finish();
            Intent intent = new Intent(getApplicationContext(),BuildResumePart1.class);
            startActivity(intent);
        }
        if (view == ll_BT4){
            ResumeTemplate.templateName = "template4";
            finish();
            Intent intent = new Intent(getApplicationContext(),BuildResumePart1.class);
            startActivity(intent);

        }
        if (view == ll_BT5){
            ResumeTemplate.templateName = "template5";
            finish();
            Intent intent = new Intent(getApplicationContext(),BuildResumePart1.class);
            startActivity(intent);

        }
    }



    private void checkLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("LOGOUT!");
        builder.setMessage("By pressing YES you will be log out. Are you sure?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferenceManager.getInstance(getApplicationContext()).LogOut();
                finish();
                Intent intent = new Intent(getApplicationContext(),UserSignInPart1.class);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getApplicationContext(), "Press Back Angain To Exit!", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();

    }
}
