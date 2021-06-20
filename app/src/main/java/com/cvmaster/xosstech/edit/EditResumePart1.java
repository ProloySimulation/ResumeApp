package com.cvmaster.xosstech.edit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cvmaster.xosstech.InputActivities.BuildResumePart7;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

import java.io.File;
import java.io.IOException;

public class EditResumePart1 extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_Image;
    private String currentPhotoPath;

    private Button button_PictureFromGallery;

    private EditText editText_Name;
    private EditText editText_ContactNumber;
    private EditText editText_Email;
    private EditText editText_PresentAddress;

    private Button button_Update;

    private final int GALLERY_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume_part1);

        imageView_Image = (ImageView) findViewById(R.id.imageView_EditResumePart1_Image);

        button_PictureFromGallery = (Button) findViewById(R.id.button_EditResumePart1_SelectFromGallery);
        button_PictureFromGallery.setOnClickListener(this);

        editText_Name = (EditText) findViewById(R.id.edittext_EditResumePart1_Name);
        editText_ContactNumber = (EditText) findViewById(R.id.editText_EditResumePart1_ContactNumber);
        editText_Email = (EditText) findViewById(R.id.editText_EditResumePart1_Email);
        editText_PresentAddress = (EditText) findViewById(R.id.editText_EditResumePart1_PresentAddress);

        button_Update = (Button) findViewById(R.id.button_EditResumePart1_Update);
        button_Update.setOnClickListener(this);

        currentPhotoPath = ResumeProfilePart1.getImagePath();
        File file = new File(currentPhotoPath);
        imageView_Image.setImageURI(Uri.fromFile(file));

        editText_Name.setText(ResumeProfilePart1.getName());
        editText_ContactNumber.setText(ResumeProfilePart1.getContact_number());
        editText_Email.setText(ResumeProfilePart1.getEmail());
        editText_PresentAddress.setText(ResumeProfilePart1.getPresent_address());



    }

    @Override
    public void onClick(View view) {
        if (view == button_Update){
            CheckValidity();
        }
        if (view == button_PictureFromGallery){
            takeImageFromGallery();
        }
    }

    private void takeImageFromGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,GALLERY_REQUEST_CODE);
    }

    private void CheckValidity(){
        String imagePath= null;
        String name = null;
        String contact_number = null;
        String email = null;
        String present_address = null;

        imagePath = currentPhotoPath;
        name = editText_Name.getText().toString().trim();
        contact_number = editText_ContactNumber.getText().toString().trim();
        email = editText_Email.getText().toString().toLowerCase().trim();
        present_address = editText_PresentAddress.getText().toString().trim();

        if (imagePath.isEmpty()){
            Toast.makeText(this,"Please Select Image From Your Gallery",Toast.LENGTH_LONG).show();
            return;
        }

        if (name.isEmpty()){
            editText_Name.setError("ENTER NAME!");
            editText_Name.requestFocus();
            return;
        }

        if (contact_number.isEmpty()){
            editText_ContactNumber.setError("ENTER CONTACT NUMBER!");
            editText_ContactNumber.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editText_Email.setError("ENTER EMAIL!");
            editText_Email.requestFocus();
            return;
        }

        if (present_address.isEmpty()){
            editText_PresentAddress.setError("ENTER PRESENT ADDRESS!");
            editText_PresentAddress.requestFocus();
            return;
        }

        SaveData(imagePath,name,contact_number,email,present_address);


        GoToNextIntent();

    }

    private void GoToNextIntent(){
        finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart7.class);
        startActivity(intent);
    }

    private void SaveData(String imagePath,String name,String contact_number,String email,String present_address){
        ResumeProfilePart1.setImagePath(imagePath);
        ResumeProfilePart1.setName(name);
        ResumeProfilePart1.setEmail(email);
        ResumeProfilePart1.setContact_number(contact_number);
        ResumeProfilePart1.setPresent_address(present_address);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri,filePath,null,null,null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePath[0]);
                String myPath = cursor.getString(columnIndex);
                cursor.close();

                float abs_width = 0.0f;
                float abs_height = 0.0f;
                try {
                    Image image = Image.getInstance(myPath);
                    abs_width = image.getPlainWidth();
                    abs_height = image.getPlainHeight();
                    //Toast.makeText(this,"Height: "+abs_height+" Width: "+abs_width,Toast.LENGTH_LONG).show();

                } catch (BadElementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(this,"Height: "+abs_height+" Width: "+abs_width,Toast.LENGTH_LONG).show();
                if (abs_height > 600 || abs_width > 500){
                    Toast.makeText(this, "Please choose Passport Size Photo!",Toast.LENGTH_LONG).show();
                    return;
                }


                currentPhotoPath = myPath;
                File file = new File(currentPhotoPath);
                imageView_Image.setImageURI(Uri.fromFile(file));

            }
        }
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
