package com.example.mydemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp_Activity extends AppCompatActivity {

    TextView btn;
    private EditText inputPersonName,TextPhone,inputEmailAddress,inputTextPassword;
    Button btn_Register;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn=findViewById(R.id.textView12);
        inputPersonName=findViewById(R.id.inputPersonName);
        TextPhone=findViewById(R.id.TextPhone);
        inputEmailAddress=findViewById(R.id.inputEmailAddress);
        inputTextPassword=findViewById(R.id.inputTextPassword);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(signUp_Activity.this);
        btn_Register=findViewById(R.id.btn_Register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();


            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(signUp_Activity.this,Signin_Activity.class));
            }
        });


    }

    private void checkCredentials() {

        String Username=inputPersonName.getText().toString();
        String email=inputEmailAddress.getText().toString();
        String phoneNumber=TextPhone.getText().toString();
        String password=inputTextPassword.getText().toString();

        if(Username.isEmpty() || Username.length()<6){
            showError(inputPersonName,"Your Username is not valid!");

        }
        else if(email.isEmpty() || !email.contains("@")){

            showError(inputEmailAddress,"Email is not valid");

        }
        else if(phoneNumber.isEmpty() || phoneNumber.length()<=10){

            showError(TextPhone,"Phone Number is not Valid");
        }
        else if(password.isEmpty() || password.length()<6){

            showError(inputTextPassword,"password is too Weak");
        }
        else {

            mLoadingBar.setTitle("Registeration");
            mLoadingBar.setMessage("Please wait ,While checking Your Credential");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();



            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull  Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(signUp_Activity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                        mLoadingBar.dismiss();
                        Intent intent=new Intent(signUp_Activity.this,Content_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(signUp_Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();


    }
}