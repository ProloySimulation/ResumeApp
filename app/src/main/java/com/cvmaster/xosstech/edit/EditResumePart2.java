package com.cvmaster.xosstech.edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cvmaster.xosstech.InputActivities.BuildResumePart7;
import com.cvmaster.xosstech.model.EducationQualification_Model;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart2;

public class EditResumePart2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editText_CareerObjective;

    private LinearLayout linearLayout_EducationalQualification_1;
    private TextView textView_QualificationName_1;
    private Spinner qualification_Names_1;
    private EditText editText_InstituteName_1;
    private TextView textView_BoardName_1;
    private Spinner spinner_Board_1;
    private TextView textView_SubjectGroupName_1;
    private Spinner subjectGroup_Name_1;
    private TextView textView_PassingYear_1;
    private Spinner spinner_PassingYear_1;
    private TextView textView_Result_1;
    private Spinner spinner_GradeDivision_1;
    private Spinner spinner_Division_1;
    private LinearLayout linearLayout_Division_1;
    private LinearLayout linearLayout_Grade_1;
    private EditText editText_Grade_1;
    private Button button_AddField_EducationalQualification_1;


    private LinearLayout linearLayout_EducationalQualification_2;
    private TextView textView_QualificationName_2;
    private Spinner qualification_Names_2;
    private EditText editText_InstituteName_2;
    private TextView textView_BoardName_2;
    private TextView textView_SubjectGroupName_2;
    private Spinner spinner_Board_2;
    private Spinner subjectGroup_Name_2;
    private TextView textView_PassingYear_2;
    private Spinner spinner_PassingYear_2;
    private TextView textView_Result_2;
    private Spinner spinner_GradeDivision_2;
    private Spinner spinner_Division_2;
    private LinearLayout linearLayout_Division_2;
    private LinearLayout linearLayout_Grade_2;
    private EditText editText_Grade_2;
    private Button button_AddField_EducationalQualification_2;
    private Button button_DeleteField_EducationalQualification_2;

    private LinearLayout linearLayout_EducationalQualification_3;
    private TextView textView_QualificationName_3;
    private Spinner qualification_Names_3;
    private EditText editText_InstituteName_3;
    private TextView textView_BoardName_3;
    private Spinner spinner_Board_3;
    private TextView textView_SubjectGroupName_3;
    private Spinner subjectGroup_Name_3;
    private TextView textView_PassingYear_3;
    private Spinner spinner_PassingYear_3;
    private TextView textView_Result_3;
    private Spinner spinner_GradeDivision_3;
    private Spinner spinner_Division_3;
    private LinearLayout linearLayout_Division_3;
    private LinearLayout linearLayout_Grade_3;
    private EditText editText_Grade_3;
    private Button button_AddField_EducationalQualification_3;
    private Button button_DeleteField_EducationalQualification_3;

    private LinearLayout linearLayout_EducationalQualification_4;
    private TextView textView_QualificationName_4;
    private Spinner qualification_Names_4;
    private EditText editText_InstituteName_4;
    private TextView textView_BoardName_4;
    private Spinner spinner_Board_4;
    private TextView textView_SubjectGroupName_4;
    private Spinner subjectGroup_Name_4;
    private TextView textView_PassingYear_4;
    private Spinner spinner_PassingYear_4;
    private TextView textView_Result_4;
    private Spinner spinner_GradeDivision_4;
    private Spinner spinner_Division_4;
    private LinearLayout linearLayout_Division_4;
    private LinearLayout linearLayout_Grade_4;
    private EditText editText_Grade_4;
    private Button button_AddField_EducationalQualification_4;
    private Button button_DeleteField_EducationalQualification_4;


    private LinearLayout linearLayout_EducationalQualification_5;
    private TextView textView_QualificationName_5;
    private Spinner qualification_Names_5;
    private EditText editText_InstituteName_5;
    private TextView textView_BoardName_5;
    private Spinner spinner_Board_5;
    private TextView textView_SubjectGroupName_5;
    private Spinner subjectGroup_Name_5;
    private TextView textView_PassingYear_5;
    private Spinner spinner_PassingYear_5;
    private TextView textView_Result_5;
    private Spinner spinner_GradeDivision_5;
    private Spinner spinner_Division_5;
    private LinearLayout linearLayout_Division_5;
    private LinearLayout linearLayout_Grade_5;
    private EditText editText_Grade_5;
    private Button button_DeleteField_EducationalQualification_5;

    private Button button_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume_part2);

        editText_CareerObjective = (EditText) findViewById(R.id.editText_BuildResumePart2_CareerObjective_ER2);

        linearLayout_EducationalQualification_1 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_1_ER2);
        button_AddField_EducationalQualification_1 = (Button) findViewById(R.id.button_AddField_EducationalQualification_1_ER2);
        button_AddField_EducationalQualification_1.setOnClickListener(this);
        linearLayout_Division_1 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Division_1_ER2);
        linearLayout_Grade_1 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Grade_1_ER2);

        textView_QualificationName_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_QualificationName_1_ER2);

        qualification_Names_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_QualificationName_1_ER2);
        ArrayAdapter<CharSequence> arrayAdapterQualificationName1 = ArrayAdapter.createFromResource(this,R.array.qualification_Names,android.R.layout.simple_spinner_item);
        arrayAdapterQualificationName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification_Names_1.setAdapter(arrayAdapterQualificationName1);
        qualification_Names_1.setOnItemSelectedListener(this);

        editText_InstituteName_1 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_1_ER2);

        textView_BoardName_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_1_ER2);

        spinner_Board_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_1_ER2);
        ArrayAdapter<CharSequence> arrayAdapterBoardName1 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_1.setAdapter(arrayAdapterBoardName1);
        spinner_Board_1.setOnItemSelectedListener(this);

        textView_SubjectGroupName_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_1_ER2);

        subjectGroup_Name_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GroupSubjectName_1_ER2);
        ArrayAdapter<CharSequence> arrayAdapterSubjectGroupName1 = ArrayAdapter.createFromResource(this,R.array.subject_Group_Names,android.R.layout.simple_spinner_item);
        arrayAdapterSubjectGroupName1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectGroup_Name_1.setAdapter(arrayAdapterSubjectGroupName1);
        subjectGroup_Name_1.setOnItemSelectedListener(this);

        textView_PassingYear_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_1_ER2);

        spinner_PassingYear_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_1_ER2);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear1 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_1.setAdapter(arrayAdapterPassingYear1);
        spinner_PassingYear_1.setOnItemSelectedListener(this);

        textView_Result_1 = (TextView) findViewById(R.id.textView_BuildResumePart2_Result_1_ER2);

        spinner_GradeDivision_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GradeDivision_1_ER2);
        ArrayAdapter<CharSequence> arrayAdapterGradeDivision1 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_GradeDivision_1.setAdapter(arrayAdapterGradeDivision1);
        spinner_GradeDivision_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String text = adapterView.getItemAtPosition(i).toString();

                if(text.compareTo("Division") == 0){
                    linearLayout_Division_1.setVisibility(View.VISIBLE);
                    linearLayout_Grade_1.setVisibility(View.GONE);
                }
                else if(text.compareTo("Grade") == 0){
                    linearLayout_Division_1.setVisibility(View.GONE);
                    linearLayout_Grade_1.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_Division_1 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_Division_1_ER2);
        ArrayAdapter<CharSequence> arrayAdapterDivision1 = ArrayAdapter.createFromResource(this,R.array.division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterDivision1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Division_1.setAdapter(arrayAdapterDivision1);
        spinner_Division_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editText_Grade_1 = (EditText) findViewById(R.id.editText_BuildResumePart2_Grade_1_ER2);

        linearLayout_EducationalQualification_2 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_ER2);
        button_AddField_EducationalQualification_2 = (Button) findViewById(R.id.button_AddField_EducationalQualification_2_ER2);
        button_AddField_EducationalQualification_2.setOnClickListener(this);
        button_DeleteField_EducationalQualification_2 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_2_ER2);
        button_DeleteField_EducationalQualification_2.setOnClickListener(this);
        linearLayout_Division_2 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Division_2_ER2);
        linearLayout_Grade_2 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Grade_2_ER2);

        textView_QualificationName_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_QualificationName_2_ER2);

        qualification_Names_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_QualificationName_2_ER2);
        ArrayAdapter<CharSequence> arrayAdapterQualificationName2 = ArrayAdapter.createFromResource(this,R.array.qualification_Names,android.R.layout.simple_spinner_item);
        arrayAdapterQualificationName2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification_Names_2.setAdapter(arrayAdapterQualificationName2);
        qualification_Names_2.setOnItemSelectedListener(this);

        editText_InstituteName_2 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_2_ER2);

        textView_BoardName_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_2_ER2);

        spinner_Board_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_2_ER2);
        ArrayAdapter<CharSequence> arrayAdapterBoardName2 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_2.setAdapter(arrayAdapterBoardName2);
        spinner_Board_2.setOnItemSelectedListener(this);

        textView_SubjectGroupName_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_2_ER2);

        subjectGroup_Name_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GroupSubjectName_2_ER2);
        ArrayAdapter<CharSequence> arrayAdapterSubjectGroupName2 = ArrayAdapter.createFromResource(this,R.array.subject_Group_Names,android.R.layout.simple_spinner_item);
        arrayAdapterSubjectGroupName2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectGroup_Name_2.setAdapter(arrayAdapterSubjectGroupName2);
        subjectGroup_Name_2.setOnItemSelectedListener(this);

        textView_PassingYear_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_2_ER2);

        spinner_PassingYear_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_2_ER2);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear2 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_2.setAdapter(arrayAdapterPassingYear2);
        spinner_PassingYear_2.setOnItemSelectedListener(this);

        textView_Result_2 = (TextView) findViewById(R.id.textView_BuildResumePart2_Result_2_ER2);

        spinner_GradeDivision_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GradeDivision_2_ER2);
        ArrayAdapter<CharSequence> arrayAdapterGradeDivision2 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_GradeDivision_2.setAdapter(arrayAdapterGradeDivision2);
        spinner_GradeDivision_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String text = adapterView.getItemAtPosition(i).toString();

                if(text.compareTo("Division") == 0){
                    linearLayout_Division_2.setVisibility(View.VISIBLE);
                    linearLayout_Grade_2.setVisibility(View.GONE);
                }
                else if(text.compareTo("Grade") == 0){
                    linearLayout_Division_2.setVisibility(View.GONE);
                    linearLayout_Grade_2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_Division_2 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_Division_2_ER2);
        ArrayAdapter<CharSequence> arrayAdapterDivision2 = ArrayAdapter.createFromResource(this,R.array.division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterDivision2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Division_2.setAdapter(arrayAdapterDivision2);
        spinner_Division_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editText_Grade_2 = (EditText) findViewById(R.id.editText_BuildResumePart2_Grade_2_ER2);

        linearLayout_EducationalQualification_3 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_3_ER2);
        button_AddField_EducationalQualification_3 = (Button) findViewById(R.id.button_AddField_EducationalQualification_3_ER2);
        button_AddField_EducationalQualification_3.setOnClickListener(this);
        button_DeleteField_EducationalQualification_3 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_3_ER2);
        button_DeleteField_EducationalQualification_3.setOnClickListener(this);
        linearLayout_Division_3 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Division_3_ER2);
        linearLayout_Grade_3 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Grade_3_ER2);

        textView_QualificationName_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_QualificationName_3_ER2);

        qualification_Names_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_QualificationName_3_ER2);
        ArrayAdapter<CharSequence> arrayAdapterQualificationName3 = ArrayAdapter.createFromResource(this,R.array.qualification_Names,android.R.layout.simple_spinner_item);
        arrayAdapterQualificationName3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification_Names_3.setAdapter(arrayAdapterQualificationName3);
        qualification_Names_3.setOnItemSelectedListener(this);

        editText_InstituteName_3 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_3_ER2);

        textView_BoardName_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_3_ER2);

        spinner_Board_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_3_ER2);
        ArrayAdapter<CharSequence> arrayAdapterBoardName3 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_3.setAdapter(arrayAdapterBoardName3);
        spinner_Board_3.setOnItemSelectedListener(this);

        textView_SubjectGroupName_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_3_ER2);

        subjectGroup_Name_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GroupSubjectName_3_ER2);
        ArrayAdapter<CharSequence> arrayAdapterSubjectGroupName3 = ArrayAdapter.createFromResource(this,R.array.subject_Group_Names,android.R.layout.simple_spinner_item);
        arrayAdapterSubjectGroupName3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectGroup_Name_3.setAdapter(arrayAdapterSubjectGroupName3);
        subjectGroup_Name_3.setOnItemSelectedListener(this);

        textView_PassingYear_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_3_ER2);

        spinner_PassingYear_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_3_ER2);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear3 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_3.setAdapter(arrayAdapterPassingYear3);
        spinner_PassingYear_3.setOnItemSelectedListener(this);

        textView_Result_3 = (TextView) findViewById(R.id.textView_BuildResumePart2_Result_3_ER2);

        spinner_GradeDivision_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GradeDivision_3_ER2);
        ArrayAdapter<CharSequence> arrayAdapterGradeDivision3 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_GradeDivision_3.setAdapter(arrayAdapterGradeDivision3);
        spinner_GradeDivision_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String text = adapterView.getItemAtPosition(i).toString();

                if(text.compareTo("Division") == 0){
                    linearLayout_Division_3.setVisibility(View.VISIBLE);
                    linearLayout_Grade_3.setVisibility(View.GONE);
                }
                else if(text.compareTo("Grade") == 0){
                    linearLayout_Division_3.setVisibility(View.GONE);
                    linearLayout_Grade_3.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_Division_3 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_Division_3_ER2);
        ArrayAdapter<CharSequence> arrayAdapterDivision3 = ArrayAdapter.createFromResource(this,R.array.division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterDivision3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Division_3.setAdapter(arrayAdapterDivision3);
        spinner_Division_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editText_Grade_3 = (EditText) findViewById(R.id.editText_BuildResumePart2_Grade_3_ER2);

        linearLayout_EducationalQualification_4 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_4_ER2);
        button_AddField_EducationalQualification_4 = (Button) findViewById(R.id.button_AddField_EducationalQualification_4_ER2);
        button_AddField_EducationalQualification_4.setOnClickListener(this);
        button_DeleteField_EducationalQualification_4 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_4_ER2);
        button_DeleteField_EducationalQualification_4.setOnClickListener(this);
        linearLayout_Division_4 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Division_4_ER2);
        linearLayout_Grade_4 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Grade_4_ER2);

        textView_QualificationName_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_QualificationName_4_ER2);

        qualification_Names_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_QualificationName_4_ER2);
        ArrayAdapter<CharSequence> arrayAdapterQualificationName4 = ArrayAdapter.createFromResource(this,R.array.qualification_Names,android.R.layout.simple_spinner_item);
        arrayAdapterQualificationName4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification_Names_4.setAdapter(arrayAdapterQualificationName4);
        qualification_Names_4.setOnItemSelectedListener(this);

        editText_InstituteName_4 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_4_ER2);

        textView_BoardName_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_4_ER2);

        spinner_Board_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_4_ER2);
        ArrayAdapter<CharSequence> arrayAdapterBoardName4 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_4.setAdapter(arrayAdapterBoardName4);
        spinner_Board_4.setOnItemSelectedListener(this);

        textView_SubjectGroupName_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_4_ER2);

        subjectGroup_Name_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GroupSubjectName_4_ER2);
        ArrayAdapter<CharSequence> arrayAdapterSubjectGroupName4 = ArrayAdapter.createFromResource(this,R.array.subject_Group_Names,android.R.layout.simple_spinner_item);
        arrayAdapterSubjectGroupName4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectGroup_Name_4.setAdapter(arrayAdapterSubjectGroupName4);
        subjectGroup_Name_4.setOnItemSelectedListener(this);

        textView_PassingYear_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_4_ER2);

        spinner_PassingYear_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_4_ER2);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear4 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_4.setAdapter(arrayAdapterPassingYear4);
        spinner_PassingYear_4.setOnItemSelectedListener(this);

        textView_Result_4 = (TextView) findViewById(R.id.textView_BuildResumePart2_Result_4_ER2);

        spinner_GradeDivision_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GradeDivision_4_ER2);
        ArrayAdapter<CharSequence> arrayAdapterGradeDivision4 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_GradeDivision_4.setAdapter(arrayAdapterGradeDivision4);
        spinner_GradeDivision_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String text = adapterView.getItemAtPosition(i).toString();

                if(text.compareTo("Division") == 0){
                    linearLayout_Division_4.setVisibility(View.VISIBLE);
                    linearLayout_Grade_4.setVisibility(View.GONE);
                }
                else if(text.compareTo("Grade") == 0){
                    linearLayout_Division_4.setVisibility(View.GONE);
                    linearLayout_Grade_4.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_Division_4 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_Division_4_ER2);
        ArrayAdapter<CharSequence> arrayAdapterDivision4 = ArrayAdapter.createFromResource(this,R.array.division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterDivision4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Division_4.setAdapter(arrayAdapterDivision4);
        spinner_Division_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editText_Grade_4 = (EditText) findViewById(R.id.editText_BuildResumePart2_Grade_4_ER2);

        linearLayout_EducationalQualification_5 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_5_ER2);
        button_DeleteField_EducationalQualification_5 = (Button) findViewById(R.id.button_DeleteField_EducationalQualification_5_ER2);
        button_DeleteField_EducationalQualification_5.setOnClickListener(this);
        linearLayout_Division_5 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Division_5_ER2);
        linearLayout_Grade_5 = (LinearLayout) findViewById(R.id.linearLayout_EducationalQualification_2_Grade_5_ER2);

        textView_QualificationName_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_QualificationName_5_ER2);

        qualification_Names_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_QualificationName_5_ER2);
        ArrayAdapter<CharSequence> arrayAdapterQualificationName5 = ArrayAdapter.createFromResource(this,R.array.qualification_Names,android.R.layout.simple_spinner_item);
        arrayAdapterQualificationName5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification_Names_5.setAdapter(arrayAdapterQualificationName5);
        qualification_Names_5.setOnItemSelectedListener(this);

        editText_InstituteName_5 = (EditText) findViewById(R.id.editText_BuildResumePart2_InstituteName_5_ER2);

        textView_BoardName_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_BoardName_5_ER2);

        spinner_Board_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart2_BoardName_5_ER2);
        ArrayAdapter<CharSequence> arrayAdapterBoardName5 = ArrayAdapter.createFromResource(this,R.array.board_Names,android.R.layout.simple_spinner_item);
        arrayAdapterBoardName5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Board_5.setAdapter(arrayAdapterBoardName5);
        spinner_Board_5.setOnItemSelectedListener(this);

        textView_SubjectGroupName_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_GroupSubjectName_5_ER2);

        subjectGroup_Name_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GroupSubjectName_5_ER2);
        ArrayAdapter<CharSequence> arrayAdapterSubjectGroupName5 = ArrayAdapter.createFromResource(this,R.array.subject_Group_Names,android.R.layout.simple_spinner_item);
        arrayAdapterSubjectGroupName5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectGroup_Name_5.setAdapter(arrayAdapterSubjectGroupName5);
        subjectGroup_Name_5.setOnItemSelectedListener(this);

        textView_PassingYear_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_PassingYear_5_ER2);

        spinner_PassingYear_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_PassingYear_5_ER2);
        ArrayAdapter<CharSequence> arrayAdapterPassingYear5 = ArrayAdapter.createFromResource(this,R.array.passing_Years,android.R.layout.simple_spinner_item);
        arrayAdapterPassingYear5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_PassingYear_5.setAdapter(arrayAdapterPassingYear5);
        spinner_PassingYear_5.setOnItemSelectedListener(this);

        textView_Result_5 = (TextView) findViewById(R.id.textView_BuildResumePart2_Result_5_ER2);

        spinner_GradeDivision_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_GradeDivision_5_ER2);
        ArrayAdapter<CharSequence> arrayAdapterGradeDivision5 = ArrayAdapter.createFromResource(this,R.array.grade_Division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterGradeDivision5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_GradeDivision_5.setAdapter(arrayAdapterGradeDivision5);
        spinner_GradeDivision_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String text = adapterView.getItemAtPosition(i).toString();

                if(text.compareTo("Division") == 0){
                    linearLayout_Division_5.setVisibility(View.VISIBLE);
                    linearLayout_Grade_5.setVisibility(View.GONE);
                }
                else if(text.compareTo("Grade") == 0){
                    linearLayout_Division_5.setVisibility(View.GONE);
                    linearLayout_Grade_5.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_Division_5 = (Spinner) findViewById(R.id.spinner_BuildResumePart3_Division_5_ER2);
        ArrayAdapter<CharSequence> arrayAdapterDivision5 = ArrayAdapter.createFromResource(this,R.array.division_Names,android.R.layout.simple_spinner_item);
        arrayAdapterDivision5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Division_5.setAdapter(arrayAdapterDivision5);
        spinner_Division_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editText_Grade_5 = (EditText) findViewById(R.id.editText_BuildResumePart2_Grade_5_ER2);

        button_Update = (Button) findViewById(R.id.button_EditResumePart2_Update);
        button_Update.setOnClickListener(this);



        //Set datas

        editText_CareerObjective.setText(ResumeProfilePart2.career_objective);

        if (ResumeProfilePart2.educationQualification.size()>0){
            EducationQualification_Model educationQualification_model;
            educationQualification_model = ResumeProfilePart2.educationQualification.get(0);

            qualification_Names_1.setSelection(((ArrayAdapter<CharSequence>)qualification_Names_1.getAdapter()).getPosition(educationQualification_model.getQualification_name()));
            editText_InstituteName_1.setText(educationQualification_model.getInstitute_name());
            spinner_Board_1.setSelection(((ArrayAdapter<CharSequence>)spinner_Board_1.getAdapter()).getPosition(educationQualification_model.getBoard_name()));
            subjectGroup_Name_1.setSelection(((ArrayAdapter<CharSequence>)subjectGroup_Name_1.getAdapter()).getPosition(educationQualification_model.getGroupsubject_name()));
            spinner_PassingYear_1.setSelection(((ArrayAdapter<CharSequence>)spinner_PassingYear_1.getAdapter()).getPosition(educationQualification_model.getPassing_year()));

            //result part
            spinner_GradeDivision_1.setSelection(((ArrayAdapter<CharSequence>)spinner_GradeDivision_1.getAdapter()).getPosition(educationQualification_model.getGradediorvision()));
            if (educationQualification_model.getGradediorvision().equals("Division")){
                spinner_Division_1.setSelection(((ArrayAdapter<CharSequence>)spinner_Division_1.getAdapter()).getPosition(educationQualification_model.getResult()));
            }
            else {
                editText_Grade_1.setText(educationQualification_model.getResult());
            }

        }
        if (ResumeProfilePart2.educationQualification.size()>1){

            button_AddField_EducationalQualification_1.setVisibility(View.GONE);
            linearLayout_EducationalQualification_2.setVisibility(View.VISIBLE);
            button_AddField_EducationalQualification_2.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_2.setVisibility(View.VISIBLE);

            EducationQualification_Model educationQualification_model;
            educationQualification_model = ResumeProfilePart2.educationQualification.get(1);

            qualification_Names_2.setSelection(((ArrayAdapter<CharSequence>)qualification_Names_2.getAdapter()).getPosition(educationQualification_model.getQualification_name()));
            editText_InstituteName_2.setText(educationQualification_model.getInstitute_name());
            spinner_Board_2.setSelection(((ArrayAdapter<CharSequence>)spinner_Board_2.getAdapter()).getPosition(educationQualification_model.getBoard_name()));
            subjectGroup_Name_2.setSelection(((ArrayAdapter<CharSequence>)subjectGroup_Name_1.getAdapter()).getPosition(educationQualification_model.getGroupsubject_name()));
            spinner_PassingYear_2.setSelection(((ArrayAdapter<CharSequence>)spinner_PassingYear_2.getAdapter()).getPosition(educationQualification_model.getPassing_year()));

            //result part will be done later
            spinner_GradeDivision_2.setSelection(((ArrayAdapter<CharSequence>)spinner_GradeDivision_2.getAdapter()).getPosition(educationQualification_model.getGradediorvision()));
            if (educationQualification_model.getGradediorvision().equals("Division")){
                spinner_Division_2.setSelection(((ArrayAdapter<CharSequence>)spinner_Division_2.getAdapter()).getPosition(educationQualification_model.getResult()));
            }
            else {
                editText_Grade_2.setText(educationQualification_model.getResult());
            }

        }
        if (ResumeProfilePart2.educationQualification.size()>2){

            button_AddField_EducationalQualification_2.setVisibility(View.GONE);
            button_DeleteField_EducationalQualification_2.setVisibility(View.GONE);
            linearLayout_EducationalQualification_3.setVisibility(View.VISIBLE);
            button_AddField_EducationalQualification_3.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_3.setVisibility(View.VISIBLE);

            EducationQualification_Model educationQualification_model;
            educationQualification_model = ResumeProfilePart2.educationQualification.get(2);

            qualification_Names_3.setSelection(((ArrayAdapter<CharSequence>)qualification_Names_3.getAdapter()).getPosition(educationQualification_model.getQualification_name()));
            editText_InstituteName_3.setText(educationQualification_model.getInstitute_name());
            spinner_Board_3.setSelection(((ArrayAdapter<CharSequence>)spinner_Board_3.getAdapter()).getPosition(educationQualification_model.getBoard_name()));
            subjectGroup_Name_3.setSelection(((ArrayAdapter<CharSequence>)subjectGroup_Name_3.getAdapter()).getPosition(educationQualification_model.getGroupsubject_name()));
            spinner_PassingYear_3.setSelection(((ArrayAdapter<CharSequence>)spinner_PassingYear_3.getAdapter()).getPosition(educationQualification_model.getPassing_year()));

            //result part will be done later
            spinner_GradeDivision_3.setSelection(((ArrayAdapter<CharSequence>)spinner_GradeDivision_3.getAdapter()).getPosition(educationQualification_model.getGradediorvision()));
            if (educationQualification_model.getGradediorvision().equals("Division")){
                spinner_Division_3.setSelection(((ArrayAdapter<CharSequence>)spinner_Division_3.getAdapter()).getPosition(educationQualification_model.getResult()));
            }
            else {
                editText_Grade_3.setText(educationQualification_model.getResult());
            }
        }
        if (ResumeProfilePart2.educationQualification.size()>3){
            button_AddField_EducationalQualification_3.setVisibility(View.GONE);
            button_DeleteField_EducationalQualification_3.setVisibility(View.GONE);
            linearLayout_EducationalQualification_4.setVisibility(View.VISIBLE);
            button_AddField_EducationalQualification_4.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_4.setVisibility(View.VISIBLE);

            EducationQualification_Model educationQualification_model;
            educationQualification_model = ResumeProfilePart2.educationQualification.get(3);

            qualification_Names_4.setSelection(((ArrayAdapter<CharSequence>)qualification_Names_4.getAdapter()).getPosition(educationQualification_model.getQualification_name()));
            editText_InstituteName_4.setText(educationQualification_model.getInstitute_name());
            spinner_Board_4.setSelection(((ArrayAdapter<CharSequence>)spinner_Board_4.getAdapter()).getPosition(educationQualification_model.getBoard_name()));
            subjectGroup_Name_4.setSelection(((ArrayAdapter<CharSequence>)subjectGroup_Name_4.getAdapter()).getPosition(educationQualification_model.getGroupsubject_name()));
            spinner_PassingYear_4.setSelection(((ArrayAdapter<CharSequence>)spinner_PassingYear_4.getAdapter()).getPosition(educationQualification_model.getPassing_year()));

            //result part will be done later
            spinner_GradeDivision_4.setSelection(((ArrayAdapter<CharSequence>)spinner_GradeDivision_4.getAdapter()).getPosition(educationQualification_model.getGradediorvision()));
            if (educationQualification_model.getGradediorvision().equals("Division")){
                spinner_Division_4.setSelection(((ArrayAdapter<CharSequence>)spinner_Division_4.getAdapter()).getPosition(educationQualification_model.getResult()));
            }
            else {
                editText_Grade_4.setText(educationQualification_model.getResult());
            }
        }
        if (ResumeProfilePart2.educationQualification.size()>4){

            button_AddField_EducationalQualification_4.setVisibility(View.GONE);
            button_DeleteField_EducationalQualification_4.setVisibility(View.GONE);
            linearLayout_EducationalQualification_5.setVisibility(View.VISIBLE);
            button_DeleteField_EducationalQualification_5.setVisibility(View.VISIBLE);

            EducationQualification_Model educationQualification_model;
            educationQualification_model = ResumeProfilePart2.educationQualification.get(0);

            qualification_Names_5.setSelection(((ArrayAdapter<CharSequence>)qualification_Names_5.getAdapter()).getPosition(educationQualification_model.getQualification_name()));
            editText_InstituteName_5.setText(educationQualification_model.getInstitute_name());
            spinner_Board_5.setSelection(((ArrayAdapter<CharSequence>)spinner_Board_5.getAdapter()).getPosition(educationQualification_model.getBoard_name()));
            subjectGroup_Name_5.setSelection(((ArrayAdapter<CharSequence>)subjectGroup_Name_5.getAdapter()).getPosition(educationQualification_model.getGroupsubject_name()));
            spinner_PassingYear_5.setSelection(((ArrayAdapter<CharSequence>)spinner_PassingYear_5.getAdapter()).getPosition(educationQualification_model.getPassing_year()));

            //result part will be done later
            spinner_GradeDivision_5.setSelection(((ArrayAdapter<CharSequence>)spinner_GradeDivision_5.getAdapter()).getPosition(educationQualification_model.getGradediorvision()));
            if (educationQualification_model.getGradediorvision().equals("Division")){
                spinner_Division_5.setSelection(((ArrayAdapter<CharSequence>)spinner_Division_5.getAdapter()).getPosition(educationQualification_model.getResult()));
            }
            else {
                editText_Grade_5.setText(educationQualification_model.getResult());
            }
        }

    }

    @Override
    public void onClick(View view) {
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

        if(view == button_Update){
            clearResumeProfilePart2Memory();
            SaveCareerObjective();
            CheckValidity_Final();
        }
    }

    private boolean CheckValidity_Reference_1(){
        String qualification_name;
        String institute_name;
        String board_name;
        String subjectgroup_name;
        String passing_year;
        String gradedivision;
        String result_grade;
        String result_division;

        qualification_name = qualification_Names_1.getSelectedItem().toString().trim();
        institute_name = editText_InstituteName_1.getText().toString().trim();
        board_name = spinner_Board_1.getSelectedItem().toString().trim();
        subjectgroup_name = subjectGroup_Name_1.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_1.getSelectedItem().toString().trim();
        gradedivision = spinner_GradeDivision_1.getSelectedItem().toString().trim();
        result_grade = editText_Grade_1.getText().toString().trim();
        result_division = spinner_Division_1.getSelectedItem().toString().trim();

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
        if (board_name.compareTo("Select") == 0){
            textView_BoardName_1.setError("SELECT BOARD NAME!");
            textView_BoardName_1.requestFocus();
            return false;
        }
        if (subjectgroup_name.compareTo("Select") == 0){
            textView_SubjectGroupName_1.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_1.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_1.setError("SELECT PASSING YEAR!");
            textView_PassingYear_1.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Select") == 0){
            textView_Result_1.setError("ENTER YOUR RESULT!");
            textView_Result_1.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Grade") == 0 && result_grade.isEmpty()){
            editText_Grade_1.setError("ENTER YOUR GRADE!");
            editText_Grade_1.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Division") == 0 && result_division.compareTo("Select") == 0){
            textView_Result_1.setError("ENTER YOUR RESULT!");
            textView_Result_1.requestFocus();
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

        qualification_name = qualification_Names_2.getSelectedItem().toString().trim();
        institute_name = editText_InstituteName_2.getText().toString().trim();
        board_name = spinner_Board_2.getSelectedItem().toString().trim();
        subjectgroup_name = subjectGroup_Name_2.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_2.getSelectedItem().toString().trim();
        gradedivision = spinner_GradeDivision_2.getSelectedItem().toString().trim();
        result_grade = editText_Grade_2.getText().toString().trim();
        result_division = spinner_Division_2.getSelectedItem().toString().trim();

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
        if (board_name.compareTo("Select") == 0){
            textView_BoardName_2.setError("SELECT BOARD NAME!");
            textView_BoardName_2.requestFocus();
            return false;
        }
        if (subjectgroup_name.compareTo("Select") == 0){
            textView_SubjectGroupName_2.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_2.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_2.setError("SELECT PASSING YEAR!");
            textView_PassingYear_2.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Select") == 0){
            textView_Result_2.setError("ENTER YOUR RESULT!");
            textView_Result_2.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Grade") == 0 && result_grade.isEmpty()){
            editText_Grade_2.setError("ENTER YOUR GRADE!");
            editText_Grade_2.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Division") == 0 && result_division.compareTo("Select") == 0){
            textView_Result_2.setError("ENTER YOUR RESULT!");
            textView_Result_2.requestFocus();
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

        qualification_name = qualification_Names_3.getSelectedItem().toString().trim();
        institute_name = editText_InstituteName_3.getText().toString().trim();
        board_name = spinner_Board_3.getSelectedItem().toString().trim();
        subjectgroup_name = subjectGroup_Name_3.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_3.getSelectedItem().toString().trim();
        gradedivision = spinner_GradeDivision_3.getSelectedItem().toString().trim();
        result_grade = editText_Grade_3.getText().toString().trim();
        result_division = spinner_Division_3.getSelectedItem().toString().trim();

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
        if (board_name.compareTo("Select") == 0){
            textView_BoardName_3.setError("SELECT BOARD NAME!");
            textView_BoardName_3.requestFocus();
            return false;
        }
        if (subjectgroup_name.compareTo("Select") == 0){
            textView_SubjectGroupName_3.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_3.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_3.setError("SELECT PASSING YEAR!");
            textView_PassingYear_3.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Select") == 0){
            textView_Result_3.setError("ENTER YOUR RESULT!");
            textView_Result_3.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Grade") == 0 && result_grade.isEmpty()){
            editText_Grade_3.setError("ENTER YOUR GRADE!");
            editText_Grade_3.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Division") == 0 && result_division.compareTo("Select") == 0){
            textView_Result_3.setError("ENTER YOUR RESULT!");
            textView_Result_3.requestFocus();
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

        qualification_name = qualification_Names_4.getSelectedItem().toString().trim();
        institute_name = editText_InstituteName_4.getText().toString().trim();
        board_name = spinner_Board_4.getSelectedItem().toString().trim();
        subjectgroup_name = subjectGroup_Name_4.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_4.getSelectedItem().toString().trim();
        gradedivision = spinner_GradeDivision_4.getSelectedItem().toString().trim();
        result_grade = editText_Grade_4.getText().toString().trim();
        result_division = spinner_Division_4.getSelectedItem().toString().trim();

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
        if (board_name.compareTo("Select") == 0){
            textView_BoardName_4.setError("SELECT BOARD NAME!");
            textView_BoardName_4.requestFocus();
            return false;
        }
        if (subjectgroup_name.compareTo("Select") == 0){
            textView_SubjectGroupName_4.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_4.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_4.setError("SELECT PASSING YEAR!");
            textView_PassingYear_4.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Select") == 0){
            textView_Result_4.setError("ENTER YOUR RESULT!");
            textView_Result_4.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Grade") == 0 && result_grade.isEmpty()){
            editText_Grade_4.setError("ENTER YOUR GRADE!");
            editText_Grade_4.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Division") == 0 && result_division.compareTo("Select") == 0){
            textView_Result_4.setError("ENTER YOUR RESULT!");
            textView_Result_4.requestFocus();
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

        qualification_name = qualification_Names_5.getSelectedItem().toString().trim();
        institute_name = editText_InstituteName_5.getText().toString().trim();
        board_name = spinner_Board_5.getSelectedItem().toString().trim();
        subjectgroup_name = subjectGroup_Name_5.getSelectedItem().toString().trim();
        passing_year = spinner_PassingYear_5.getSelectedItem().toString().trim();
        gradedivision = spinner_GradeDivision_5.getSelectedItem().toString().trim();
        result_grade = editText_Grade_5.getText().toString().trim();
        result_division = spinner_Division_5.getSelectedItem().toString().trim();

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
        if (board_name.compareTo("Select") == 0){
            textView_BoardName_5.setError("SELECT BOARD NAME!");
            textView_BoardName_5.requestFocus();
            return false;
        }
        if (subjectgroup_name.compareTo("Select") == 0){
            textView_SubjectGroupName_5.setError("SELECT SUBJECT OR GROUP!");
            textView_SubjectGroupName_5.requestFocus();
            return false;
        }
        if (passing_year.compareTo("Select") == 0){
            textView_PassingYear_5.setError("SELECT PASSING YEAR!");
            textView_PassingYear_5.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Select") == 0){
            textView_Result_5.setError("ENTER YOUR RESULT!");
            textView_Result_5.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Grade") == 0 && result_grade.isEmpty()){
            editText_Grade_5.setError("ENTER YOUR GRADE!");
            editText_Grade_5.requestFocus();
            return false;
        }
        if (gradedivision.compareTo("Division") == 0 && result_division.compareTo("Select") == 0){
            textView_Result_5.setError("ENTER YOUR RESULT!");
            textView_Result_5.requestFocus();
            return false;
        }

        return true;
    }

    private void SaveCareerObjective(){
        String career_objective;
        career_objective = editText_CareerObjective.getText().toString().trim();
        ResumeProfilePart2.career_objective = career_objective;
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
            String result_division;

            qualification_name = qualification_Names_1.getSelectedItem().toString().trim();
            institute_name = editText_InstituteName_1.getText().toString().trim();
            board_name = spinner_Board_1.getSelectedItem().toString().trim();
            subjectgroup_name = subjectGroup_Name_1.getSelectedItem().toString().trim();
            passing_year = spinner_PassingYear_1.getSelectedItem().toString().trim();
            gradedivision = spinner_GradeDivision_1.getSelectedItem().toString().trim();
            result_grade = editText_Grade_1.getText().toString().trim();
            result_division = spinner_Division_1.getSelectedItem().toString().trim();

            EducationQualification_Model educationQualification = new EducationQualification_Model();
            educationQualification.setQualification_name(qualification_name);
            educationQualification.setInstitute_name(institute_name);
            educationQualification.setBoard_name(board_name);
            educationQualification.setGroupsubject_name(subjectgroup_name);
            educationQualification.setPassing_year(passing_year);
            educationQualification.setGradediorvision(gradedivision);
            if (gradedivision.compareTo("Grade") == 0){
                educationQualification.setResult(result_grade);
            }
            if (gradedivision.compareTo("Division") == 0){
                educationQualification.setResult(result_division);
            }
            ResumeProfilePart2.educationQualification.add(educationQualification);

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

            qualification_name = qualification_Names_2.getSelectedItem().toString().trim();
            institute_name = editText_InstituteName_2.getText().toString().trim();
            board_name = spinner_Board_2.getSelectedItem().toString().trim();
            subjectgroup_name = subjectGroup_Name_2.getSelectedItem().toString().trim();
            passing_year = spinner_PassingYear_2.getSelectedItem().toString().trim();
            gradedivision = spinner_GradeDivision_2.getSelectedItem().toString().trim();
            result_grade = editText_Grade_2.getText().toString().trim();
            result_division = spinner_Division_2.getSelectedItem().toString().trim();

            EducationQualification_Model educationQualification = new EducationQualification_Model();
            educationQualification.setQualification_name(qualification_name);
            educationQualification.setInstitute_name(institute_name);
            educationQualification.setBoard_name(board_name);
            educationQualification.setGroupsubject_name(subjectgroup_name);
            educationQualification.setPassing_year(passing_year);
            educationQualification.setGradediorvision(gradedivision);
            if (gradedivision.compareTo("Grade") == 0){
                educationQualification.setResult(result_grade);
            }
            if (gradedivision.compareTo("Division") == 0){
                educationQualification.setResult(result_division);
            }
            ResumeProfilePart2.educationQualification.add(educationQualification);
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

            qualification_name = qualification_Names_3.getSelectedItem().toString().trim();
            institute_name = editText_InstituteName_3.getText().toString().trim();
            board_name = spinner_Board_3.getSelectedItem().toString().trim();
            subjectgroup_name = subjectGroup_Name_3.getSelectedItem().toString().trim();
            passing_year = spinner_PassingYear_3.getSelectedItem().toString().trim();
            gradedivision = spinner_GradeDivision_3.getSelectedItem().toString().trim();
            result_grade = editText_Grade_3.getText().toString().trim();
            result_division = spinner_Division_3.getSelectedItem().toString().trim();


            EducationQualification_Model educationQualification = new EducationQualification_Model();
            educationQualification.setQualification_name(qualification_name);
            educationQualification.setInstitute_name(institute_name);
            educationQualification.setBoard_name(board_name);
            educationQualification.setGroupsubject_name(subjectgroup_name);
            educationQualification.setPassing_year(passing_year);
            educationQualification.setGradediorvision(gradedivision);
            if (gradedivision.compareTo("Grade") == 0){
                educationQualification.setResult(result_grade);
            }
            if (gradedivision.compareTo("Division") == 0){
                educationQualification.setResult(result_division);
            }
            ResumeProfilePart2.educationQualification.add(educationQualification);
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

            qualification_name = qualification_Names_4.getSelectedItem().toString().trim();
            institute_name = editText_InstituteName_4.getText().toString().trim();
            board_name = spinner_Board_4.getSelectedItem().toString().trim();
            subjectgroup_name = subjectGroup_Name_4.getSelectedItem().toString().trim();
            passing_year = spinner_PassingYear_4.getSelectedItem().toString().trim();
            gradedivision = spinner_GradeDivision_4.getSelectedItem().toString().trim();
            result_grade = editText_Grade_4.getText().toString().trim();
            result_division = spinner_Division_4.getSelectedItem().toString().trim();


            EducationQualification_Model educationQualification = new EducationQualification_Model();
            educationQualification.setQualification_name(qualification_name);
            educationQualification.setInstitute_name(institute_name);
            educationQualification.setBoard_name(board_name);
            educationQualification.setGroupsubject_name(subjectgroup_name);
            educationQualification.setPassing_year(passing_year);
            educationQualification.setGradediorvision(gradedivision);
            if (gradedivision.compareTo("Grade") == 0){
                educationQualification.setResult(result_grade);
            }
            if (gradedivision.compareTo("Division") == 0){
                educationQualification.setResult(result_division);
            }
            ResumeProfilePart2.educationQualification.add(educationQualification);
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

            qualification_name = qualification_Names_5.getSelectedItem().toString().trim();
            institute_name = editText_InstituteName_5.getText().toString().trim();
            board_name = spinner_Board_5.getSelectedItem().toString().trim();
            subjectgroup_name = subjectGroup_Name_5.getSelectedItem().toString().trim();
            passing_year = spinner_PassingYear_5.getSelectedItem().toString().trim();
            gradedivision = spinner_GradeDivision_5.getSelectedItem().toString().trim();
            result_grade = editText_Grade_5.getText().toString().trim();
            result_division = spinner_Division_5.getSelectedItem().toString().trim();

            EducationQualification_Model educationQualification = new EducationQualification_Model();
            educationQualification.setQualification_name(qualification_name);
            educationQualification.setInstitute_name(institute_name);
            educationQualification.setBoard_name(board_name);
            educationQualification.setGroupsubject_name(subjectgroup_name);
            educationQualification.setPassing_year(passing_year);
            educationQualification.setGradediorvision(gradedivision);
            if (gradedivision.compareTo("Grade") == 0){
                educationQualification.setResult(result_grade);
            }
            if (gradedivision.compareTo("Division") == 0){
                educationQualification.setResult(result_division);
            }
            ResumeProfilePart2.educationQualification.add(educationQualification);
        }
    }

    private boolean CheckValidity_Final(){

        if (editText_CareerObjective.getText().toString().trim().isEmpty()){
            editText_CareerObjective.setError("ENTER CAREER OBJECTIVE!");
            editText_CareerObjective.requestFocus();
            return false;
        }

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
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart7.class);
        startActivity(intent);
    }

    private void clearResumeProfilePart2Memory(){
        ResumeProfilePart2.career_objective = "";
        ResumeProfilePart2.educationQualification.clear();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GO BACK WITHOUT UPDATING DATA!");
        builder.setMessage("If you press Yes data will not be up updated. Are you agree?");
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToBuildResumePart7Intent();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void goToBuildResumePart7Intent(){
        finish();
        Intent intent = new Intent(this, BuildResumePart7.class);
        startActivity(intent);
    }

}
