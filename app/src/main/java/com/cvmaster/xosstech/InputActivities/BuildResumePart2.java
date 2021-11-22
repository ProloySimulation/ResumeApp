package com.cvmaster.xosstech.InputActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart2;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.model.EducationQualification_Model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildResumePart2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private LinearLayout linearLayout_EducationalQualification_1;
    private EditText textView_QualificationName_1;
    private EditText editText_InstituteName_1;
    private TextView textView_BoardName_1;
    private Spinner spinner_Board_1;
    private EditText textView_SubjectGroupName_1;
    private TextView textView_PassingYear_1;
    private Spinner spinner_PassingYear_1;
    private EditText editText_Result_1;
    private Button button_AddField_EducationalQualification_1;
    private LinearLayout layoutOtherSubject1 ;
    private EditText etOtherSubject1;
    private String id1 = null;

    private TextView tvBoard1,tvPassYear1,tvGroup1,tvResult1;


    private LinearLayout linearLayout_EducationalQualification_2;
    private EditText textView_QualificationName_2;
    private EditText editText_InstituteName_2;
    private TextView textView_BoardName_2;
    private EditText textView_SubjectGroupName_2;
    private Spinner spinner_Board_2;
    private TextView textView_PassingYear_2;
    private Spinner spinner_PassingYear_2;
    private EditText textView_Result_2;
    private Button button_AddField_EducationalQualification_2;
    private Button button_DeleteField_EducationalQualification_2;
    private LinearLayout layoutOtherSubject2 ;
    private EditText etOtherSubject2;
    private String id2 = null;

    private LinearLayout linearLayout_EducationalQualification_3;
    private EditText textView_QualificationName_3;
    private EditText editText_InstituteName_3;
    private TextView textView_BoardName_3;
    private Spinner spinner_Board_3;
    private EditText textView_SubjectGroupName_3;
    private TextView textView_PassingYear_3;
    private Spinner spinner_PassingYear_3;
    private EditText textView_Result_3;
    private Button button_AddField_EducationalQualification_3;
    private Button button_DeleteField_EducationalQualification_3;
    private LinearLayout layoutOtherSubject3 ;
    private EditText etOtherSubject3;
    private TextView tvGradeEducation3 ;
    private String id3 = null;

    private LinearLayout linearLayout_EducationalQualification_4;
    private EditText textView_QualificationName_4;
    private EditText editText_InstituteName_4;
    private TextView textView_BoardName_4;
    private Spinner spinner_Board_4;
    private EditText textView_SubjectGroupName_4;
    private TextView textView_PassingYear_4;
    private Spinner spinner_PassingYear_4;
    private EditText textView_Result_4;
    private Button button_AddField_EducationalQualification_4;
    private Button button_DeleteField_EducationalQualification_4;
    private LinearLayout layoutOtherSubject4 ;
    private EditText etOtherSubject4;
    private String id4 = null;

    private LinearLayout linearLayout_EducationalQualification_5;
    private EditText textView_QualificationName_5;
    private EditText editText_InstituteName_5;
    private TextView textView_BoardName_5;
    private Spinner spinner_Board_5;
    private EditText textView_SubjectGroupName_5;
    private TextView textView_PassingYear_5;
    private Spinner spinner_PassingYear_5;
    private EditText textView_Result_5;
    private Button button_DeleteField_EducationalQualification_5;
    private String id5 = null;

    private TextView tvSave ;

    private Button button_Next;
    private Button button_Data;
    private String token = null;

    private String uploadUrl = "http://xosstech.com/cvm/api/public/api/education";
    private String updateUrl = "http://xosstech.com/cvm/api/public/api/education/update/";
    private String board = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part2);

        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();

        tvSave = findViewById(R.id.tvEduDataSave);
        tvSave.setOnClickListener(this);

        linearLayout_EducationalQualification_1 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_1);
        button_AddField_EducationalQualification_1 = (Button) findViewById(R.id.button_AddField_EducationalQualification_1);
        button_AddField_EducationalQualification_1.setOnClickListener(this);

        tvBoard1 = findViewById(R.id.tvEdu_Quality_Board_1);
        tvPassYear1 = findViewById(R.id.tvEdu_Quality_Pass_1);

        textView_QualificationName_1 = findViewById(R.id.textView_BuildResumePart2_QualificationName_1);
        spinner_Board_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_1);
//        spinner_Board_1.setEnabled(false);

        editText_InstituteName_1 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_1);
        textView_BoardName_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_1);

        ArrayAdapter<CharSequence> arrayAdapterBoardName1 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_1.setPrompt("Dhaka");
        spinner_Board_1.setAdapter(arrayAdapterBoardName1);
        spinner_Board_1.setOnItemSelectedListener(this);

        textView_SubjectGroupName_1 = findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_1);

        ArrayAdapter<CharSequence> arrayAdapterSubjectGroupName1 = ArrayAdapter.createFromResource(this,R.array.subject_Group_Names,android.R.layout.simple_spinner_item);
        arrayAdapterSubjectGroupName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        textView_PassingYear_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_1);

        spinner_PassingYear_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_1);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear1 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_1.setAdapter(arrayAdapterPassingYear1);
        spinner_PassingYear_1.setOnItemSelectedListener(this);

        editText_Result_1 = findViewById(R.id.textView_BuildResumePart2_Result_1);

        ArrayAdapter<CharSequence> arrayAdapterGradeDivision1 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> arrayAdapterDivision1 = ArrayAdapter.createFromResource(this,R.array.division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterDivision1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        layoutOtherSubject1 = findViewById(R.id.linearLayout_Others_Subject_1);
        etOtherSubject1 = findViewById(R.id.etOthersSub1);

        linearLayout_EducationalQualification_2 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2);
        button_AddField_EducationalQualification_2 = (Button) findViewById(R.id.button_AddField_EducationalQualification_2);
        button_AddField_EducationalQualification_2.setOnClickListener(this);
        button_DeleteField_EducationalQualification_2 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_2);
        button_DeleteField_EducationalQualification_2.setOnClickListener(this);
        button_AddField_EducationalQualification_2.setEnabled(false);

        textView_QualificationName_2 =  findViewById(R.id.textView_BuildResumePart2_QualificationName_2);
        spinner_Board_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_2);
        spinner_Board_2.setEnabled(false);

        editText_InstituteName_2 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_2);

        textView_BoardName_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_2);

        ArrayAdapter<CharSequence> arrayAdapterBoardName2 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_2.setAdapter(arrayAdapterBoardName2);
        spinner_Board_2.setOnItemSelectedListener(this);

        textView_SubjectGroupName_2 = findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_2);
        textView_PassingYear_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_2);

        spinner_PassingYear_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_2);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear2 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_2.setAdapter(arrayAdapterPassingYear2);
        spinner_PassingYear_2.setOnItemSelectedListener(this);

        textView_Result_2 = findViewById(R.id.textView_BuildResumePart2_Result_2);

        ArrayAdapter<CharSequence> arrayAdapterGradeDivision2 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        layoutOtherSubject2 = findViewById(R.id.linearLayout_Others_Subject_2);
        etOtherSubject2 = findViewById(R.id.etOthersSub2);

        tvGradeEducation3 = findViewById(R.id.tvGradeEducation3);
        linearLayout_EducationalQualification_3 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_3);
        button_AddField_EducationalQualification_3 = (Button) findViewById(R.id.button_AddField_EducationalQualification_3);
        button_AddField_EducationalQualification_3.setOnClickListener(this);
        button_DeleteField_EducationalQualification_3 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_3);
        button_DeleteField_EducationalQualification_3.setOnClickListener(this);

        textView_QualificationName_3 = findViewById(R.id.textView_BuildResumePart2_QualificationName_3);
        spinner_Board_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_3);
        spinner_Board_3.setEnabled(false);


        editText_InstituteName_3 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_3);
        textView_BoardName_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_3);

        ArrayAdapter<CharSequence> arrayAdapterBoardName3 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_3.setAdapter(arrayAdapterBoardName3);
        spinner_Board_3.setOnItemSelectedListener(this);

        textView_SubjectGroupName_3 = findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_3);

        textView_PassingYear_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_3);

        spinner_PassingYear_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_3);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear3 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_3.setAdapter(arrayAdapterPassingYear3);
        spinner_PassingYear_3.setOnItemSelectedListener(this);

        textView_Result_3 = findViewById(R.id.textView_BuildResumePart2_Result_3);

        layoutOtherSubject3 = findViewById(R.id.linearLayout_Others_Subject_3);
        etOtherSubject3 = findViewById(R.id.etOthersSub3);

        linearLayout_EducationalQualification_4 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_4);
        button_AddField_EducationalQualification_4 = (Button) findViewById(R.id.button_AddField_EducationalQualification_4);
        button_AddField_EducationalQualification_4.setOnClickListener(this);
        button_DeleteField_EducationalQualification_4 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_4);
        button_DeleteField_EducationalQualification_4.setOnClickListener(this);

        textView_QualificationName_4 = findViewById(R.id.textView_BuildResumePart2_QualificationName_4);
        spinner_Board_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_4);
        spinner_Board_4.setEnabled(false);

        editText_InstituteName_4 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_4);

        textView_BoardName_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_4);

        ArrayAdapter<CharSequence> arrayAdapterBoardName4 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_4.setAdapter(arrayAdapterBoardName4);
        spinner_Board_4.setOnItemSelectedListener(this);

        textView_SubjectGroupName_4 = findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_4);
        textView_PassingYear_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_4);

        spinner_PassingYear_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_4);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear4 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_4.setAdapter(arrayAdapterPassingYear4);
        spinner_PassingYear_4.setOnItemSelectedListener(this);
        textView_Result_4 = findViewById(R.id.textView_BuildResumePart2_Result_4);

        layoutOtherSubject4 = findViewById(R.id.linearLayout_Others_Subject_4);
        etOtherSubject4 = findViewById(R.id.etOthersSub4);

        linearLayout_EducationalQualification_5 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_5);
        button_DeleteField_EducationalQualification_5 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_5);
        button_DeleteField_EducationalQualification_5.setOnClickListener(this);


        textView_QualificationName_5 = findViewById(R.id.textView_BuildResumePart2_QualificationName_5);
        spinner_Board_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_5);
        spinner_Board_5.setEnabled(false);

        editText_InstituteName_5 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_5);
        textView_BoardName_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_5);

        ArrayAdapter<CharSequence> arrayAdapterBoardName5 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_5.setAdapter(arrayAdapterBoardName5);
        spinner_Board_5.setOnItemSelectedListener(this);

        textView_SubjectGroupName_5 = findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_5);
        textView_PassingYear_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_5);

        spinner_PassingYear_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_5);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear5 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_5.setAdapter(arrayAdapterPassingYear5);
        spinner_PassingYear_5.setOnItemSelectedListener(this);

        textView_Result_5 = findViewById(R.id.textView_BuildResumePart2_Result_5);

        button_Next = (Button) findViewById(R.id.button_BuildResumePart2_Next);
        button_Next.setOnClickListener(this);

        button_Data = (Button) findViewById(R.id.button_BuildResumePart2_Show);
        button_Data.setOnClickListener(this);


        List<EducationQualification_Model> edu_model = ResumeProfilePart2.educationQualification;

        if (ResumeProfilePart2.educationQualification.size() > 0){
            spinner_Board_1.setEnabled(true);

            EducationQualification_Model edu_mod = edu_model.get(0);
            textView_QualificationName_1.setText(edu_mod.getQualification_name());
            editText_InstituteName_1.setText(edu_mod.getInstitute_name());
            spinner_Board_1.setPrompt(edu_mod.getBoard_name());
            textView_PassingYear_1.setText(edu_mod.getPassing_year());
            textView_BoardName_1.setText(edu_mod.getBoard_name());
            spinner_PassingYear_1.setPrompt(edu_mod.getPassing_year());
            textView_SubjectGroupName_1.setText(edu_mod.getGroupsubject_name());
            editText_Result_1.setText(edu_mod.getResult());
            id1 = edu_mod.getId();
        }

        if (ResumeProfilePart2.educationQualification.size() > 1){
            EducationQualification_Model edu_mod = edu_model.get(1);
            linearLayout_EducationalQualification_2.setVisibility(View.VISIBLE);

            editText_InstituteName_2.setText(edu_mod.getInstitute_name());
            textView_QualificationName_2.setText(edu_mod.getQualification_name());
            spinner_Board_2.setPrompt(edu_mod.getBoard_name());
            textView_BoardName_2.setText(edu_mod.getBoard_name());
            textView_PassingYear_2.setText(edu_mod.getPassing_year());
            textView_SubjectGroupName_2.setText(edu_mod.getGroupsubject_name());
            spinner_PassingYear_2.setPrompt(edu_mod.getPassing_year());
            textView_Result_2.setText(edu_mod.getResult());
            id2 = edu_mod.getId();
        }

        if (ResumeProfilePart2.educationQualification.size() > 2){
            EducationQualification_Model edu_mod = edu_model.get(2);

            linearLayout_EducationalQualification_3.setVisibility(View.VISIBLE);
            editText_InstituteName_3.setText(edu_mod.getInstitute_name());
            textView_QualificationName_3.setText(edu_mod.getQualification_name());
            spinner_Board_3.setPrompt(edu_mod.getBoard_name());
            textView_PassingYear_3.setText(edu_mod.getPassing_year());
            textView_BoardName_3.setText(edu_mod.getBoard_name());
            textView_SubjectGroupName_3.setText(edu_mod.getGroupsubject_name());
            spinner_PassingYear_3.setPrompt(edu_mod.getPassing_year());
            textView_Result_3.setText(edu_mod.getResult());
            id3 = edu_mod.getId();
        }

        if (ResumeProfilePart2.educationQualification.size() > 3){
            EducationQualification_Model edu_mod = edu_model.get(3);

            linearLayout_EducationalQualification_4.setVisibility(View.VISIBLE);
            editText_InstituteName_4.setText(edu_mod.getInstitute_name());
            textView_QualificationName_4.setText(edu_mod.getQualification_name());
            spinner_Board_4.setPrompt(edu_mod.getBoard_name());
            textView_PassingYear_4.setText(edu_mod.getPassing_year());
            textView_BoardName_4.setText(edu_mod.getBoard_name());
            textView_SubjectGroupName_4.setText(edu_mod.getGroupsubject_name());
            spinner_PassingYear_4.setPrompt(edu_mod.getPassing_year());
            textView_Result_4.setText(edu_mod.getResult());
            id4 = edu_mod.getId();
        }

        if (ResumeProfilePart2.educationQualification.size() > 4){
            EducationQualification_Model edu_mod = edu_model.get(4);

            linearLayout_EducationalQualification_5.setVisibility(View.VISIBLE);
            editText_InstituteName_5.setText(edu_mod.getInstitute_name());
            textView_QualificationName_5.setText(edu_mod.getQualification_name());
            spinner_Board_5.setPrompt(edu_mod.getBoard_name());
            textView_PassingYear_5.setText(edu_mod.getPassing_year());
            textView_BoardName_5.setText(edu_mod.getBoard_name());
            textView_SubjectGroupName_5.setText(edu_mod.getGroupsubject_name());
            spinner_PassingYear_4.setPrompt(edu_mod.getPassing_year());
            textView_Result_5.setText(edu_mod.getResult());
            id5 = edu_mod.getId();
        }

        editText_InstituteName_1.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            button_AddField_EducationalQualification_2.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            button_AddField_EducationalQualification_2.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onClick(View view) {

        if(view == tvSave)
        {
            CheckValidity_Final();
        }
        if(view == button_AddField_EducationalQualification_1){
            if (CheckValidity_Reference_1()){
                button_AddField_EducationalQualification_1.setVisibility(View.GONE);
                linearLayout_EducationalQualification_2.setVisibility(View.VISIBLE);
            }

        }
        if (view == button_DeleteField_EducationalQualification_2){
            button_AddField_EducationalQualification_1.setVisibility(View.VISIBLE);
            linearLayout_EducationalQualification_2.setVisibility(View.GONE);
        }
        if(view == button_AddField_EducationalQualification_2){
            if (CheckValidity_Reference_2()){
                button_AddField_EducationalQualification_2.setVisibility(View.GONE);
                button_DeleteField_EducationalQualification_2.setVisibility(View.GONE);
                linearLayout_EducationalQualification_3.setVisibility(View.VISIBLE);
            }
        }
        if(view == button_DeleteField_EducationalQualification_3){
            linearLayout_EducationalQualification_3.setVisibility(View.GONE);
            button_AddField_EducationalQualification_2.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_2.setVisibility(View.VISIBLE);
        }
        if (view == button_AddField_EducationalQualification_3){
            if (CheckValidity_Reference_3()){
                button_AddField_EducationalQualification_3.setVisibility(View.GONE);
                button_DeleteField_EducationalQualification_3.setVisibility(View.GONE);
                linearLayout_EducationalQualification_4.setVisibility(View.VISIBLE);
            }
        }
        if(view == button_DeleteField_EducationalQualification_4){
            linearLayout_EducationalQualification_4.setVisibility(View.GONE);
            button_AddField_EducationalQualification_3.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_3.setVisibility(View.VISIBLE);
        }
        if(view == button_AddField_EducationalQualification_4){
            if (CheckValidity_Reference_4()){
                button_AddField_EducationalQualification_4.setVisibility(View.GONE);
                button_DeleteField_EducationalQualification_4.setVisibility(View.GONE);
                linearLayout_EducationalQualification_5.setVisibility(View.VISIBLE);
            }
        }
        if(view == button_DeleteField_EducationalQualification_5){
            linearLayout_EducationalQualification_5.setVisibility(View.GONE);
            button_AddField_EducationalQualification_4.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_4.setVisibility(View.VISIBLE);
        }

        if (view == button_Data){
        }

    }

    private boolean CheckValidity_Reference_1(){
        String qualification_name;
        String institute_name;
        String board_name;
        String subjectgroup_name;
        String passing_year;
        String result_grade;
        String result_division;

        qualification_name = textView_QualificationName_1.getText().toString().trim();
        institute_name = editText_InstituteName_1.getText().toString().trim();
        subjectgroup_name = textView_SubjectGroupName_1.getText().toString().trim();
        board_name = spinner_Board_1.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_1.getSelectedItem().toString().trim();
        result_grade = editText_Result_1.getText().toString().trim();

        if (qualification_name.compareTo("Select") == 0){
            textView_QualificationName_1.setError("SELECT EDUCATIONAL QUALIFICATION!");
            textView_QualificationName_1.requestFocus();
            return false;
        }
        if (institute_name.isEmpty()){
            editText_InstituteName_1.setError("ENTER INSTITUTE NAME!");
            editText_InstituteName_1.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            board_name = null;
        }*/
        if (subjectgroup_name.isEmpty()){
            textView_SubjectGroupName_1.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_1.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_1.setError("SELECT PASSING YEAR!");
            textView_PassingYear_1.requestFocus();
            return false;
        }

        return true;
    }

    private boolean CheckValidity_Reference_2(){
        String qualification_name;
        String institute_name;
        String board_name;
        String subjectgroup_name;
        String passing_year;
        String gradedivision;
        String result_grade;
        String result_division;

        qualification_name = textView_QualificationName_2.getText().toString().trim();
        institute_name = editText_InstituteName_2.getText().toString().trim();
        subjectgroup_name = textView_SubjectGroupName_2.getText().toString().trim();
        board_name = spinner_Board_2.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_2.getSelectedItem().toString().trim();

        if(subjectgroup_name.equals("Others"))
        {

        }

        if (qualification_name.compareTo("Select") == 0){
            textView_QualificationName_2.setError("SELECT EDUCATIONAL QUALIFICATION!");
            textView_QualificationName_2.requestFocus();
            return false;
        }
        if (institute_name.isEmpty()){
            editText_InstituteName_2.setError("ENTER INSTITUTE NAME!");
            editText_InstituteName_2.requestFocus();
            return false;
        }
       /* if (board_name.compareTo("Select") == 0){
            textView_BoardName_2.setError("SELECT BOARD NAME!");
            textView_BoardName_2.requestFocus();
            return false;
        }*/
        if (subjectgroup_name.isEmpty()){
            textView_SubjectGroupName_2.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_2.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_2.setError("SELECT PASSING YEAR!");
            textView_PassingYear_2.requestFocus();
            return false;
        }

        return true;
    }

    private boolean CheckValidity_Reference_3(){
        String qualification_name;
        String institute_name;
        String board_name;
        String subjectgroup_name;
        String passing_year;
        String gradedivision;
        String result_grade;
        String result_division;

        qualification_name = textView_QualificationName_3.getText().toString().trim();
        institute_name = editText_InstituteName_3.getText().toString().trim();
        subjectgroup_name = textView_SubjectGroupName_3.getText().toString().trim();
        board_name = spinner_Board_3.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_3.getSelectedItem().toString().trim();


        if (qualification_name.compareTo("Select") == 0){
            textView_QualificationName_3.setError("SELECT EDUCATIONAL QUALIFICATION!");
            textView_QualificationName_3.requestFocus();
            return false;
        }
        if (institute_name.isEmpty()){
            editText_InstituteName_3.setError("ENTER INSTITUTE NAME!");
            editText_InstituteName_3.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            textView_BoardName_3.setError("SELECT BOARD NAME!");
            textView_BoardName_3.requestFocus();
            return false;
        }*/
        if (subjectgroup_name.isEmpty()){
            textView_SubjectGroupName_3.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_3.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_3.setError("SELECT PASSING YEAR!");
            textView_PassingYear_3.requestFocus();
            return false;
        }

        return true;
    }

    private boolean CheckValidity_Reference_4(){
        String qualification_name;
        String institute_name;
        String board_name;
        String subjectgroup_name;
        String passing_year;
        String gradedivision;
        String result_grade;
        String result_division;

        qualification_name = textView_QualificationName_4.getText().toString().trim();
        institute_name = editText_InstituteName_4.getText().toString().trim();
        board_name = spinner_Board_4.getSelectedItem().toString().trim();
        subjectgroup_name = textView_SubjectGroupName_4.getText().toString().trim();
        passing_year = spinner_PassingYear_4.getSelectedItem().toString().trim();

        if (qualification_name.compareTo("Select") == 0){
            textView_QualificationName_4.setError("SELECT EDUCATIONAL QUALIFICATION!");
            textView_QualificationName_4.requestFocus();
            return false;
        }
        if (institute_name.isEmpty()){
            editText_InstituteName_4.setError("ENTER INSTITUTE NAME!");
            editText_InstituteName_4.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            textView_BoardName_4.setError("SELECT BOARD NAME!");
            textView_BoardName_4.requestFocus();
            return false;
        }*/
        if (subjectgroup_name.isEmpty()){
            textView_SubjectGroupName_4.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_4.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_4.setError("SELECT PASSING YEAR!");
            textView_PassingYear_4.requestFocus();
            return false;
        }

        return true;
    }

    private boolean CheckValidity_Reference_5(){
        String qualification_name;
        String institute_name;
        String board_name;
        String subjectgroup_name;
        String passing_year;
        String gradedivision;
        String result_grade;
        String result_division;

        qualification_name = textView_QualificationName_5.getText().toString().trim();
        institute_name = editText_InstituteName_5.getText().toString().trim();
        board_name = spinner_Board_5.getSelectedItem().toString().trim();
        subjectgroup_name = textView_SubjectGroupName_5.getText().toString().trim();
        passing_year = spinner_PassingYear_5.getSelectedItem().toString().trim();

        if (qualification_name.compareTo("Select") == 0){
            textView_QualificationName_5.setError("SELECT EDUCATIONAL QUALIFICATION!");
            textView_QualificationName_5.requestFocus();
            return false;
        }
        if (institute_name.isEmpty()){
            editText_InstituteName_5.setError("ENTER INSTITUTE NAME!");
            editText_InstituteName_5.requestFocus();
            return false;
        }
        /*if (board_name.compareTo("Select") == 0){
            textView_BoardName_5.setError("SELECT BOARD NAME!");
            textView_BoardName_5.requestFocus();
            return false;
        }*/
        if (subjectgroup_name.isEmpty()){
            textView_SubjectGroupName_5.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_5.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_5.setError("SELECT PASSING YEAR!");
            textView_PassingYear_5.requestFocus();
            return false;
        }

        return true;
    }

    private void SaveEducationalQualification(){
        if (linearLayout_EducationalQualification_1.getVisibility() == View.VISIBLE){
            String qualification_name;
            String institute_name;
            String board_name;
            String subjectgroup_name;
            String passing_year;
            String gradedivision;
            String result_grade;

            qualification_name = editText_InstituteName_1.getText().toString().trim();
            institute_name = editText_InstituteName_1.getText().toString().trim();
            board_name = spinner_Board_1.getSelectedItem().toString().trim();
            subjectgroup_name = textView_SubjectGroupName_1.getText().toString().trim();
            passing_year = spinner_PassingYear_1.getSelectedItem().toString().trim();
            result_grade = editText_Result_1.getText().toString().trim();

            if(id1!=null)
            {
                updateInformation(id1,institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
            else {
                UploadInformation(institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }

        }
        if (linearLayout_EducationalQualification_2.getVisibility() == View.VISIBLE){
            String qualification_name;
            String institute_name;
            String board_name;
            String subjectgroup_name;
            String passing_year;
            String gradedivision;
            String result_grade;
            String result_division;

            qualification_name = textView_QualificationName_2.getText().toString().trim();
            institute_name = editText_InstituteName_2.getText().toString().trim();
            board_name = spinner_Board_2.getSelectedItem().toString().trim();
            subjectgroup_name = textView_SubjectGroupName_2.getText().toString().trim();
            passing_year = spinner_PassingYear_2.getSelectedItem().toString().trim();
            result_grade = textView_Result_2.getText().toString().trim();

            if(id2!=null)
            {
                updateInformation(id2,institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
            else {
                UploadInformation(institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
        }

        if (linearLayout_EducationalQualification_3.getVisibility() == View.VISIBLE){
            String qualification_name;
            String institute_name;
            String board_name;
            String subjectgroup_name;
            String passing_year;
            String gradedivision;
            String result_grade;
            String result_division;

            qualification_name = textView_QualificationName_3.getText().toString().trim();
            institute_name = editText_InstituteName_3.getText().toString().trim();
            board_name = spinner_Board_3.getSelectedItem().toString().trim();
            subjectgroup_name = textView_SubjectGroupName_3.getText().toString().trim();
            passing_year = spinner_PassingYear_3.getSelectedItem().toString().trim();
            result_grade = textView_Result_3.getText().toString().trim();

            if(id3!=null)
            {
                updateInformation(id1,institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
            else {
                UploadInformation(institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
        }
        if (linearLayout_EducationalQualification_4.getVisibility() == View.VISIBLE){
            String qualification_name;
            String institute_name;
            String board_name;
            String subjectgroup_name;
            String passing_year;
            String gradedivision;
            String result_grade;
            String result_division;

            qualification_name = textView_QualificationName_4.getText().toString().trim();
            institute_name = editText_InstituteName_4.getText().toString().trim();
            subjectgroup_name = textView_SubjectGroupName_4.getText().toString().trim();
            board_name = spinner_Board_4.getSelectedItem().toString().trim();
            passing_year = spinner_PassingYear_4.getSelectedItem().toString().trim();
            result_grade = textView_Result_4.getText().toString().trim();

            if(id4!=null)
            {
                updateInformation(id4,institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
            else {
                UploadInformation(institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
        }

        if (linearLayout_EducationalQualification_5.getVisibility() == View.VISIBLE){
            String qualification_name;
            String institute_name;
            String board_name;
            String subjectgroup_name;
            String passing_year;
            String gradedivision;
            String result_grade;
            String result_division;

            qualification_name = textView_QualificationName_5.getText().toString().trim();
            institute_name = editText_InstituteName_5.getText().toString().trim();
            board_name = spinner_Board_5.getSelectedItem().toString().trim();
            subjectgroup_name = textView_SubjectGroupName_5.getText().toString().trim();
            passing_year = spinner_PassingYear_5.getSelectedItem().toString().trim();
            result_grade = textView_Result_5.getText().toString().trim();

            if(id5!=null)
            {
                updateInformation(id5,institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
            else {
                UploadInformation(institute_name,qualification_name,subjectgroup_name,passing_year,result_grade);
            }
        }
    }
    private boolean CheckValidity_Final(){

        if (linearLayout_EducationalQualification_1.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_2.getVisibility() == View.GONE && linearLayout_EducationalQualification_3.getVisibility() == View.GONE && linearLayout_EducationalQualification_4.getVisibility() == View.GONE && linearLayout_EducationalQualification_5.getVisibility() == View.GONE){
            if (CheckValidity_Reference_1()){
                SaveEducationalQualification();
                GoToNextIntent();
                return true;
            }
        }

        if (linearLayout_EducationalQualification_1.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_2.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_3.getVisibility() == View.GONE && linearLayout_EducationalQualification_4.getVisibility() == View.GONE && linearLayout_EducationalQualification_5.getVisibility() == View.GONE){
            if (CheckValidity_Reference_1() && CheckValidity_Reference_2()){
                SaveEducationalQualification();
                GoToNextIntent();
                return true;
            }
        }

        if (linearLayout_EducationalQualification_1.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_2.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_3.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_4.getVisibility() == View.GONE && linearLayout_EducationalQualification_5.getVisibility() == View.GONE){
            if (CheckValidity_Reference_1() && CheckValidity_Reference_2() && CheckValidity_Reference_3()){
                SaveEducationalQualification();
                GoToNextIntent();
                return true;
            }
        }

        if (linearLayout_EducationalQualification_1.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_2.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_3.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_4.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_5.getVisibility() == View.GONE){
            if (CheckValidity_Reference_1() && CheckValidity_Reference_2() && CheckValidity_Reference_3() && CheckValidity_Reference_4()){
                SaveEducationalQualification();
                GoToNextIntent();
                return true;
            }
        }
        if (linearLayout_EducationalQualification_1.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_2.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_3.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_4.getVisibility() == View.VISIBLE && linearLayout_EducationalQualification_5.getVisibility() == View.VISIBLE){
            if (CheckValidity_Reference_1() && CheckValidity_Reference_2() && CheckValidity_Reference_3() && CheckValidity_Reference_4() && CheckValidity_Reference_5()){
                SaveEducationalQualification();
                GoToNextIntent();
                return true;
            }
        }
        return false;
    }

    private void GoToNextIntent(){
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void UploadInformation(String institution,String degree,String department,String endYear,String result) {

        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumePart2.this, "Data Input Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart2.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart2.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

         /*   @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inst_name", institution);
                params.put("degree", degree);
                params.put("dept",department);
                params.put("start_year", "2000");
                params.put("pass_year", endYear);
                params.put("result", result);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void updateInformation(String id,String institution,String degree,String department,String endYear,String result) {

        StringRequest request = new StringRequest(Request.Method.POST, updateUrl+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumePart2.this, "Data Input Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart2.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart2.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
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

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inst_name", institution);
                params.put("degree", degree);
                params.put("dept",department);
                params.put("start_year", "2000");
                params.put("pass_year", endYear);
                params.put("result", result);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

}
