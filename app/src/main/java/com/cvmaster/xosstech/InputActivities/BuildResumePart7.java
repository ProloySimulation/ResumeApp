package com.cvmaster.xosstech.InputActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cvmaster.xosstech.BuildResumePDF_Part1;
import com.cvmaster.xosstech.PermissionsUtil;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.ResumeProfilePart3;
import com.cvmaster.xosstech.ResumeProfilePart4;
import com.cvmaster.xosstech.ResumeProfilePart5;
import com.cvmaster.xosstech.ResumeProfilePart6;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.adapter.CustomEducationAdapter;
import com.cvmaster.xosstech.adapter.CustomReferenceAdapter;
import com.cvmaster.xosstech.adapter.CustonWorkExperienceAdapter;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.model.Reference_Model;
import com.cvmaster.xosstech.model.Skills_Model;
import com.cvmaster.xosstech.model.WorkExperience_Model;
import com.cvmaster.xosstech.stored_resume_format.Language;
import com.cvmaster.xosstech.stored_resume_format.Personal_Information;
import com.cvmaster.xosstech.stored_resume_format.ResumeFormat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildResumePart7 extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_Name;
    private TextView textView_ContactNumber;
    private TextView textView_Email;
    private TextView textView_Address;
    private ImageView imageView_Photo;
    private Button button_Update1;

    private TextView textView_CareerObjective;
    private ListView listView_Education;
    private Button button_Update2;
    private LinearLayout linearLayout_Education;

    private TextView textView_Skills;
    private TextView textView_LanguageProficiencyBangla;
    private TextView textView_LanguageProficiencyEnglish;
    private Button button_Update3;


    private TextView textView_FullName;
    private TextView textView_FatherName;
    private TextView textView_MotherName;
    private TextView textView_Gender;
    private TextView textView_BirthDate;
    private TextView textView_MaritalStatus;
    private TextView textView_Nationality;
    private TextView textView_Religion;
    private TextView textView_PresentAddress;
    private TextView textView_PermanentAddress;
    private Button button_Update4;

    private TextView textView_WorkExperience;
    private ListView listView_WorkExperience;
    private Button button_Update5;
    private LinearLayout linearLayout_WorkExperience;

    private TextView textView_Reference;
    private ListView listView_Refrence;
    private Button button_Update6;
    private LinearLayout linearLayout_Reference;

    private Button button_MakeResume;

    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 101;
    private static final int RESULT_CODE_WRITE_EXTERNAL_STORAGE = 102;

    private StorageReference Folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part7);

        textView_Name = (TextView) findViewById(R.id.textView_Name_EditResumePart1);
        textView_ContactNumber = (TextView) findViewById(R.id.textView_ContactNumber_EditResumePart1);
        textView_Email = (TextView) findViewById(R.id.textView_Email_EditResumePart1);
        textView_Address = (TextView) findViewById(R.id.textView_Address_EditResumePart1);
        imageView_Photo = (ImageView) findViewById(R.id.imageView_Photo_EditResumePart1);

        Update1();

        textView_CareerObjective = (TextView) findViewById(R.id.careerObjective_BuildResumePart7);
        listView_Education = (ListView) findViewById(R.id.education_ListView_BuildResumePart7);
        button_Update2 = (Button) findViewById(R.id.button_Update2_EditResumePart1);
        button_Update2.setOnClickListener(this);

        linearLayout_Education = (LinearLayout) findViewById(R.id.linearLayout_Education);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout_Education.getLayoutParams();
        params.height = 420 * ResumeProfilePart2.educationQualification.size();
        linearLayout_Education.setLayoutParams(params);

        Update2();

        textView_Skills = (TextView) findViewById(R.id.textView_BuildResumePart7_Skills);
        textView_LanguageProficiencyBangla = (TextView) findViewById(R.id.textView_BuildResumePart7_LanguageProficiencyBangla);
        textView_LanguageProficiencyEnglish = (TextView) findViewById(R.id.textView_BuildResumePart7_LanguageProficiencyEnglish);



        Update3();

        textView_FullName = (TextView) findViewById(R.id.fullName_TextView_BuildResumePart7);
        textView_FatherName = (TextView) findViewById(R.id.fatherName_TextView_BuildResumePart7);
        textView_MotherName = (TextView) findViewById(R.id.motherName_TextView_BuildResumePart7);
        textView_Gender = (TextView) findViewById(R.id.gender_TextView_BuildResumePart7);
        textView_BirthDate = (TextView) findViewById(R.id.birthDate_TextView_BuildResumePart7);
        textView_MaritalStatus = (TextView) findViewById(R.id.maritalStatus_TextView_BuildResumePart7);
        textView_Nationality = (TextView) findViewById(R.id.nationality_TextView_BuildResumePart7);
        textView_Religion = (TextView) findViewById(R.id.religion_TextView_BuildResumePart7);
        textView_PresentAddress = (TextView) findViewById(R.id.presentAddress_TextView_BuildResumePart7);
        textView_PermanentAddress = (TextView) findViewById(R.id.permanentAddress_TextView_BuildResumePart7);
        button_Update4 = (Button) findViewById(R.id.button_Update4_EditResumePart1);
        button_Update4.setOnClickListener(this);

        Update4();

        Update5();

        Update6();

        button_MakeResume = (Button) findViewById(R.id.button_BuildResumePart7_MakeResume);
        button_MakeResume.setOnClickListener(this);

        Folder = FirebaseStorage.getInstance().getReference().child("Images");

    }

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePDF_Part1.class);
        //Intent intent = new Intent(getApplicationContext(),Template2_pdf.class);
        startActivity(intent);
    }

    private void Update1(){
        textView_Name.setText(ResumeProfilePart1.getName());
        textView_ContactNumber.setText("Contact: "+ResumeProfilePart1.getContact_number());
        textView_Email.setText("Email: "+ResumeProfilePart1.getEmail());
        textView_Address.setText("Address: "+ResumeProfilePart1.getPresent_address());

        File file = new File(ResumeProfilePart1.getImagePath());
        imageView_Photo.setImageURI(Uri.fromFile(file));

        button_Update1 = (Button) findViewById(R.id.button_Update1_EditResumePart1);
        button_Update1.setOnClickListener(this);
    }

    private void Update2(){
        textView_CareerObjective.setText(ResumeProfilePart2.career_objective);
        CustomEducationAdapter customEducationAdapter = new CustomEducationAdapter(this,ResumeProfilePart2.educationQualification);
        listView_Education.setAdapter(customEducationAdapter);

    }

    private void Update3(){
        String skills = "";

        for (int i = 0; i< ResumeProfilePart3.skills.size()-1; i++){
            Skills_Model skills_model = ResumeProfilePart3.skills.get(i);
            skills = skills + skills_model.getSkill() + ", ";
        }
        Skills_Model skills_model = ResumeProfilePart3.skills.get(ResumeProfilePart3.skills.size()-1);
        skills = skills+skills_model.getSkill();
        textView_Skills.setText(skills);

        textView_LanguageProficiencyBangla.setText(ResumeProfilePart3.bangla_skill_level);
        textView_LanguageProficiencyEnglish.setText(ResumeProfilePart3.english_skill_level);

        button_Update3 = (Button) findViewById(R.id.button_Update3_EditResumePart1);
        button_Update3.setOnClickListener(this);
    }

    private void Update4(){
        textView_FullName.setText(ResumeProfilePart4.getName());
        textView_FatherName.setText(ResumeProfilePart4.getFather_name());
        textView_MotherName.setText(ResumeProfilePart4.getMother_name());
        textView_Gender.setText(ResumeProfilePart4.getGender());
        textView_BirthDate.setText(ResumeProfilePart4.getBirth_date());
        textView_MaritalStatus.setText(ResumeProfilePart4.getMarital_status());
        textView_Nationality.setText(ResumeProfilePart4.getNationality());
        textView_Religion.setText(ResumeProfilePart4.getReligion());
        textView_PresentAddress.setText(ResumeProfilePart4.getPresent_address());
        textView_PermanentAddress.setText(ResumeProfilePart4.getPermanent_address());
    }

    private void Update5(){
        textView_WorkExperience = (TextView) findViewById(R.id.workExperience_TextView_BuildResumePart7);
        textView_WorkExperience.setVisibility(View.GONE);
        listView_WorkExperience = (ListView) findViewById(R.id.workExperience_ListView_BuildResumePart7);
        listView_WorkExperience.setVisibility(View.GONE);
        if (ResumeProfilePart6.workExperience.size()<=0){
            textView_WorkExperience.setVisibility(View.VISIBLE);
        } else {
            listView_WorkExperience.setVisibility(View.VISIBLE);
            linearLayout_WorkExperience = (LinearLayout) findViewById(R.id.linearLayout_WorkExperience);
            linearLayout_WorkExperience.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params_Work = (LinearLayout.LayoutParams) linearLayout_WorkExperience.getLayoutParams();
            params_Work.height = 300 * ResumeProfilePart6.workExperience.size();
            linearLayout_WorkExperience.setLayoutParams(params_Work);
            CustonWorkExperienceAdapter custonWorkExperienceAdapter = new CustonWorkExperienceAdapter(this,ResumeProfilePart6.workExperience);
            listView_WorkExperience.setAdapter(custonWorkExperienceAdapter);
        }

        button_Update5 = (Button) findViewById(R.id.button_Update5_EditResumePart1);
        button_Update5.setOnClickListener(this);
    }

    private void Update6(){
        textView_Reference = (TextView) findViewById(R.id.reference_TextView_BuildResumePart7);
        textView_Reference.setVisibility(View.GONE);

        listView_Refrence = (ListView) findViewById(R.id.reference_ListView_BuildResumePart7);
        listView_Refrence.setVisibility(View.GONE);




        if (ResumeProfilePart5.reference.size()<=0){
            textView_Reference.setVisibility(View.VISIBLE);
        } else {
            listView_Refrence.setVisibility(View.VISIBLE);
            linearLayout_Reference = (LinearLayout) findViewById(R.id.linearLayout_References);
            linearLayout_Reference.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params_Ref = (LinearLayout.LayoutParams) linearLayout_Reference.getLayoutParams();
            params_Ref.height = 320 * ResumeProfilePart5.reference.size();
            linearLayout_Reference.setLayoutParams(params_Ref);
            CustomReferenceAdapter customReferenceAdapter = new CustomReferenceAdapter(this,ResumeProfilePart5.reference);
            listView_Refrence.setAdapter(customReferenceAdapter);
        }

        button_Update6 = (Button) findViewById(R.id.button_Update6_EditResumePart1);
        button_Update6.setOnClickListener(this);
    }

    private void showPermissionExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Write External Storage Permission Needed");
        builder.setMessage("CV Master needs to access your External Storage to save PDFs. So please give permission to Write External Storage.");
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(BuildResumePart7.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void CheckReadOrWriteStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                showPermissionExplanation();
            }
            else if(!PermissionsUtil.getInstance(this).checkWriteExternalStoragePermissionPreference()){
                PermissionsUtil.getInstance(this).updateWriteExternalStoragePermissionPreference();
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            }
            else {
                Toast.makeText(this,"Please Allow Write External Storage Permission",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else {
            GoToNextIntent();
        }
    }

    public void saveData(){

        String name = ResumeProfilePart1.getName();
        String contact_number = ResumeProfilePart1.getContact_number();
        String email = ResumeProfilePart1.getEmail();
        String present_address = ResumeProfilePart1.getPresent_address();

        String career_objective = ResumeProfilePart2.career_objective;
        List<EducationQualification_Model> education = ResumeProfilePart2.educationQualification;

        List<Skills_Model> skills = ResumeProfilePart3.skills;
        Language language = new Language(ResumeProfilePart3.bangla_skill_level, ResumeProfilePart3.english_skill_level);

        Personal_Information personal_information = new Personal_Information(ResumeProfilePart4.getName(), ResumeProfilePart4.getFather_name(), ResumeProfilePart4.getMother_name(), ResumeProfilePart4.getGender(), ResumeProfilePart4.getBirth_date(), ResumeProfilePart4.getMarital_status(), ResumeProfilePart4.getNationality(), ResumeProfilePart4.getReligion(), ResumeProfilePart4.getPresent_address(), ResumeProfilePart4.getPermanent_address());

        List<WorkExperience_Model> work_exp = ResumeProfilePart6.workExperience;
        List<Reference_Model> reference = ResumeProfilePart5.reference;


        ResumeFormat resume = new ResumeFormat(
                name,
                contact_number,
                email,
                present_address,
                career_objective,
                education,
                skills,
                language,
                personal_information,
                work_exp,
                reference
        );

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        //String uid = auth.getCurrentUser().getUid();
        String uid = SharedPreferenceManager.getInstance(this).GetUserMobileNumber();
        DocumentReference documentReference = db.collection("resume_latest").document(uid);

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("contact_number", contact_number);
        user.put("email", email);
        user.put("present_address", present_address);
        user.put("career_objective", career_objective);
        user.put("education_qualification", education);
        user.put("skills", skills);
        user.put("bangla_laguage", ResumeProfilePart3.bangla_skill_level);
        user.put("english_laguage", ResumeProfilePart3.english_skill_level);

        Map<String, Object> personal_details = new HashMap<>();
        personal_details.put("full_name", ResumeProfilePart4.getName());
        personal_details.put("father_name",ResumeProfilePart4.getFather_name());
        personal_details.put("mother_name",ResumeProfilePart4.getMother_name());
        personal_details.put("gender",ResumeProfilePart4.gender);
        personal_details.put("birth_date",ResumeProfilePart4.getBirth_date());
        personal_details.put("marital_status",ResumeProfilePart4.marital_status);
        personal_details.put("nationality",ResumeProfilePart4.nationality);
        personal_details.put("religion",ResumeProfilePart4.religion);
        personal_details.put("present_address",ResumeProfilePart4.present_address);
        personal_details.put("permanent_address",ResumeProfilePart4.permanent_address);

        user.put("personal_details",personal_details);
        user.put("work_experience",ResumeProfilePart6.workExperience);
        user.put("reference",ResumeProfilePart5.reference);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG).show();
            }
        });

        Uri image = ResumeProfilePart1.getUri();
        StorageReference storageReference = Folder.child(""+uid);
    /*    storageReference.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });*/

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        String date =simpleDateFormatDate.format(calendar.getTime());
        String time = simpleDateFormatTime.format(calendar.getTime());

        String document_name = uid+date+time;

        DocumentReference documentRef = db.collection("resume").document(document_name);

        documentRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG).show();
            }
        });

        Uri img = ResumeProfilePart1.getUri();
        StorageReference storageRef = Folder.child(""+document_name);
       /* storageRef.putFile(img).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });*/

    }

    public void editDataInputted(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("resume_count").document("inputted_data");

        FirebaseFirestore.getInstance()
                .collection("resume_count")
                .document("inputted_data")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        int cv_num = Integer.parseInt(documentSnapshot.getString("inputted_data_num"));
                        Log.d("HELLO", "Number: "+cv_num);
                        cv_num++;

                        Map<String, Object> data = new HashMap<>();
                        String numb = Integer.toString(cv_num);
                        data.put("inputted_data_num",numb);

                        documentReference.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //inputted_data_num successfully increased
                                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }


    @Override
    public void onClick(View view) {

        if (view == button_MakeResume){
            saveData();
            editDataInputted();
            CheckReadOrWriteStoragePermission();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE_WRITE_EXTERNAL_STORAGE){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                GoToNextIntent();
            }
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT!");
        builder.setMessage("Do You Want To Exit From Make Resume?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToHomeIntent();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void goToHomeIntent(){
        finish();
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

}
