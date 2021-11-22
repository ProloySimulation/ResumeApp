package com.cvmaster.xosstech.InputActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cvmaster.xosstech.PermissionsUtil;
import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.ResumeProfilePart1;
import com.cvmaster.xosstech.SharedPreferenceManager;
import com.cvmaster.xosstech.UserProfileActivity;
import com.cvmaster.xosstech.UserSignInPart1;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BuildResumePart1 extends AppCompatActivity implements View.OnClickListener {


    private ImageView imageView_Image;
    private String currentPhotoPath;

    private Button button_PictureFromGallery;
    private EditText editText_Name;
    private EditText editText_ContactNumber;
    private EditText editText_Email;
    private EditText editText_PresentAddress;
    private EditText etFacebookId;
    private EditText etLinkedinId;
    private EditText etCurrentJobTitle;
    private Button button_Next;

    private String imagePath = null;
    private String name = null;
    private String contact_number = null;
    private String email = null;
    private String present_address = null;
    private String facebook_id = null;
    private String linkedin_id = null;
    private String jobTitle = null;
    private String token = null;
    private String encodeImageString;
    private Bitmap bitmap;


    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 101;
    private final int GALLERY_REQUEST_CODE = 102;
    private String uploadUrl = "http://6267-103-147-163-123.ngrok.io/cv-master/public/api/info";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_resume_part1);


        token = SharedPreferenceManager.getInstance(getApplicationContext()).GetUserToken();
        imageView_Image = (ImageView) findViewById(R.id.imageView_BuildResumePart1_Image);
        currentPhotoPath = "";

        button_PictureFromGallery = (Button) findViewById(R.id.button_BuildResumePart1_SelectFromGallery);
        button_PictureFromGallery.setOnClickListener(this);

        etFacebookId = findViewById(R.id.editText_BuildResumePart1_Facebook);
        etLinkedinId = findViewById(R.id.editText_BuildResumePart1_Linkedin);

        editText_Name = (EditText) findViewById(R.id.edittext_BuildResumePart1_Name);
        editText_ContactNumber = (EditText) findViewById(R.id.editText_BuildResumePart1_ContactNumber);
        editText_Email = (EditText) findViewById(R.id.editText_BuildResumePart1_Email);
        editText_PresentAddress = (EditText) findViewById(R.id.editText_BuildResumePart1_PresentAddress);
        etCurrentJobTitle = findViewById(R.id.editText_BuildResumePart1_Position);

        button_Next = (Button) findViewById(R.id.button_BuildResumePart1_Next);
        button_Next.setOnClickListener(this);

        //Populate Data
        /*
        editText_Name.setText("Mah Dian Drovo");
        editText_ContactNumber.setText("+8801676946820");
        editText_Email.setText("mahdian.drovo@gmail.com");
        editText_PresentAddress.setText("20/2, Block: E, Gulshan, Dhaka");
        */

    }

    private void CheckValidity() {

        imagePath = currentPhotoPath;
        name = editText_Name.getText().toString().trim();
        contact_number = editText_ContactNumber.getText().toString().trim();
        email = editText_Email.getText().toString().toLowerCase().trim();
        present_address = editText_PresentAddress.getText().toString().trim();
        facebook_id = etFacebookId.getText().toString().trim();
        linkedin_id = etLinkedinId.getText().toString().trim();
        jobTitle = etCurrentJobTitle.getText().toString().trim();

        if (imagePath.isEmpty()) {
            Toast.makeText(this, "Please Select Image From Your Gallery", Toast.LENGTH_LONG).show();
            return;
        }

        if (name.isEmpty()) {
            editText_Name.setError("ENTER NAME!");
            editText_Name.requestFocus();
            return;
        }

        if (contact_number.isEmpty()) {
            editText_ContactNumber.setError("ENTER CONTACT NUMBER!");
            editText_ContactNumber.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editText_Email.setError("ENTER EMAIL!");
            editText_Email.requestFocus();
            return;
        }

        if (present_address.isEmpty()) {
            editText_PresentAddress.setError("ENTER PRESENT ADDRESS!");
            editText_PresentAddress.requestFocus();
            return;
        }

        if (jobTitle.isEmpty()) {
            etCurrentJobTitle.setError("ENTER PRESENT ADDRESS!");
            etCurrentJobTitle.requestFocus();
            return;
        }

        SaveData(imagePath, name, contact_number, email, present_address, facebook_id, linkedin_id, jobTitle);


        GoToNextIntent();

    }


    private void SaveData(String imagePath, String name, String contact_number, String email, String present_address,
                          String facebookId, String linkediId, String jobTitle) {
        ResumeProfilePart1.setImagePath(imagePath);
        ResumeProfilePart1.setName(name);
        ResumeProfilePart1.setEmail(email);
        ResumeProfilePart1.setContact_number(contact_number);
        ResumeProfilePart1.setPresent_address(present_address);
        ResumeProfilePart1.setFacebook(facebookId);
        ResumeProfilePart1.setLinkedin(linkediId);
        ResumeProfilePart1.setJobTitle(jobTitle);
    }

    private void GoToNextIntent() {
        Upload();
        /*finish();
        Intent intent = new Intent(getApplicationContext(), BuildResumePart2.class);
        startActivity(intent);*/
    }

    @Override
    public void onClick(View view) {
        if (view == button_Next) {
            CheckValidity();
        }
        if (view == button_PictureFromGallery) {
            openGallery();
        }
    }

    private void openGallery() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showPermissionExplanation();
            } else if (!PermissionsUtil.getInstance(this).checkReadExternalStoragePermissionPreference()) {
                PermissionsUtil.getInstance(this).updateReadExternalStoragePermissionPreference();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
            } else {
                Toast.makeText(this, "Please Allow Read External Storage Permission", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        } else {
            takeImageFromGallery();
        }

    }

    private void takeImageFromGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST_CODE);
    }

    private void showPermissionExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Read External Storage Permission Needed");
        builder.setMessage("CV Master needs to access your Gallery to pick a photo of yours. So please give permission to Read External Storage.");
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(BuildResumePart1.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, filePath, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePath[0]);
                String myPath = cursor.getString(columnIndex);
                cursor.close();


                String path = ResumeProfilePart1.getImagePath();
                //added part start

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
                //abs_height > 600 || abs_width > 500
                if (abs_height > 6000 || abs_width > 5000) {
                    Toast.makeText(this, "Please choose Passport Size Photo!", Toast.LENGTH_LONG).show();
                    return;
                }

                //added part end

                ResumeProfilePart1.setUri(uri);
                currentPhotoPath = myPath;
                File file = new File(currentPhotoPath);
                imageView_Image.setImageURI(Uri.fromFile(file));

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    encodeBitmapImage(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void clearResumeProfilePart1Memory() {
        ResumeProfilePart1.setImagePath("");
        ResumeProfilePart1.setName("");
        ResumeProfilePart1.setContact_number("");
        ResumeProfilePart1.setEmail("");
        ResumeProfilePart1.setPresent_address("");
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

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

    private void goToHomeIntent() {
        finish();
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    private void Upload() {
        StringRequest request = new StringRequest(Request.Method.POST, uploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("success");

                            if (status.equals("true")) {
                                Toast.makeText(BuildResumePart1.this, "Register Scucessfully", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BuildResumePart1.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuildResumePart1.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("name", name);
                params.put("image", "aa");
                params.put("mobile", contact_number);
                params.put("email", email);
                params.put("present_address", present_address);
                params.put("permanent_address", "Mugda");
                params.put("job_title", jobTitle);
                params.put("marital_status", "Unmarried");
                params.put("religion", "Hindu");
                params.put("nationality", "Bangladeshi");
                params.put("gender", "Male");
                params.put("dob", "1996");
                params.put("profile_summary", "Hello I'm an android developer");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}

