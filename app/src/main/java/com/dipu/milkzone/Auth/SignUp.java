package com.dipu.milkzone.Auth;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dipu.milkzone.Model.UserModel;
import com.dipu.milkzone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;
    DatabaseReference db_users;
    StorageReference mStorage;
    TextView doclogintaxt;
    //userimage pickup
    private ImageView userImage;
    //end
    private CheckBox checkBox;
    private EditText uname, uemail, uphone, upassword;
    private Button btncountinu;
    private FirebaseAuth mAuth;
    private ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logomilkzone);


        mAuth = FirebaseAuth.getInstance();
        loadingProgress = findViewById(R.id.regProgressBar);


        checkBox = findViewById(R.id.i_agree);
        uname = findViewById(R.id.edit_name);
        uemail = findViewById(R.id.edt_email);
        uphone = findViewById(R.id.edt_phoneNo);
        upassword = findViewById(R.id.edt_password);
        userImage = findViewById(R.id.regUserPhoto);
        btncountinu = findViewById(R.id.btnSignUpContinue);
        doclogintaxt = findViewById(R.id.docLogin);
        btncountinu.setOnClickListener(this);
        doclogintaxt.setOnClickListener(this);
        userImage.setOnClickListener(this);
        loadingProgress.setVisibility(View.INVISIBLE);


        db_users = FirebaseDatabase.getInstance().getReference("UsersInfo");
        mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSignUpContinue:
                final String name = uname.getText().toString().trim();
                final String email = uemail.getText().toString().trim();
                final String phone = uphone.getText().toString().trim();
                final String password = upassword.getText().toString().trim();
                //checking the validity of the email
                if (TextUtils.isEmpty(name)) {
                    uname.setError("Enter your name");
                    uname.requestFocus();
                } else if (TextUtils.isEmpty(email)) {

                    uemail.setError("Enter a valid email address");
                    uemail.requestFocus();

                }

                //checking the validity of the password
                else if (TextUtils.isEmpty(phone)) {
                    uphone.setError("Enter your phone No");
                    uphone.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    upassword.setError("Enter your password");
                    upassword.requestFocus();
                } else if (!checkBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "You are not agree with our trams & conditions.", Toast.LENGTH_LONG).show();

                } else {
                    btncountinu.setVisibility(View.INVISIBLE);
                    loadingProgress.setVisibility(View.VISIBLE);

                    CreateUserAccount(email, name, password);
                }

                break;
            case R.id.docLogin:
                startActivity(new Intent(SignUp.this, LogInActivity.class));
                break;
            case R.id.regUserPhoto:
                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                } else {
                    openGallery();
                }

        }


    }


    //create user accountmathod

    private void CreateUserAccount(final String email, final String name, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // progressBarsignup .setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    saveandupdateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());
                    clear();

                } else {
                    // If sign in fails, display a message to the user.

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();

                    }
                    btncountinu.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);

                }


            }
        });
    }

    //email verify with firebase
    private void chackEmailVerify() {
        final String useremail = uemail.getText().toString().trim();
        final String userpassword = upassword.getText().toString().trim();
        Intent intent = new Intent(SignUp.this, Email_Verify_Activity.class);
        intent.putExtra("email", useremail);
        intent.putExtra("pass", userpassword);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }


    // update user photo and name
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveandupdateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {
        final String username = uname.getText().toString().trim();
        final String email = uemail.getText().toString().trim();
        final String phone = uphone.getText().toString().trim();
        final String password = upassword.getText().toString().trim();
        // first we need to upload user photo to firebase storage and get url


        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        if (pickedImgUri != null) {
            //get user image from firestore
            final StorageReference imageFilePath = mStorage.child(Objects.requireNonNull(pickedImgUri.getLastPathSegment()));


            imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // image uploaded succesfully
                    // now we can get our image url
                    imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // uri contain user image url
                            //save data to database with image id
                            String imageUrl = String.valueOf(uri);
                            String userid = currentUser.getUid();


                            UserModel userModel = new UserModel(userid, username, phone, email, password, imageUrl);
                            db_users.child(userid).setValue(userModel);
                            //user profile build with user image
                            UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .setPhotoUri(uri)
                                    .build();
                            //update profile with user image
                            currentUser.updateProfile(profleUpdate)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                // user info updated successfully
                                                //and go email verification activity
                                                showMessage();
                                                chackEmailVerify();
                                            }

                                        }
                                    });


                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this, "Upload Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //save data without user image
            String imageUrl = "default";
            String userid = currentUser.getUid();
            UserModel userModel = new UserModel(userid, username, phone, email, password, imageUrl);

            db_users.child(userid).setValue(userModel);

            //build profile without user image
            UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();

            //update user profile without user image

            currentUser.updateProfile(profleUpdate)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                // user info updated successfully
                                showMessage();
                                chackEmailVerify();


                            }

                        }
                    });

        }

    }


    private void openGallery() {

        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(SignUp.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SignUp.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(SignUp.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(SignUp.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        } else
            openGallery();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData();
            userImage.setImageURI(pickedImgUri);


        }


    }

    // simple method to show toast message
    private void showMessage() {

        Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();

    }
//user login mathod

    public void clear() {
        uname.setText("");
        uemail.setText("");
        uphone.setText("");
        upassword.setText("");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
