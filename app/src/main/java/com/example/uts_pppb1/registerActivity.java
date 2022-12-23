package com.example.uts_pppb1;

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
import com.google.firebase.auth.FirebaseUser;

public class registerActivity extends AppCompatActivity {
    EditText email, pass, confirmPass;
    Button register;
    TextView haveAccount;
    String emailValidation = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailInputR);
        pass = findViewById(R.id.PassInputR);
        confirmPass = findViewById(R.id.ConfirmPassR);
        register = findViewById(R.id.btn_register);
        haveAccount = findViewById(R.id.confirmAccount);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth();
            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Auth() {
        String emailAddress = email.getText().toString();
        String password = pass.getText().toString();
        String confirmPassword = confirmPass.getText().toString();

        if (!emailAddress.matches(emailValidation)){
            email.setError("Email salah! Masukkan lagi email dengan benar!");
        }else if(password.isEmpty() || password.length()<6){
            pass.setError("Password yang dimasukkan harus lebih dari 6 karakter!");
        }else if(!password.equals(confirmPassword)){
            confirmPass.setError("Passwod yang dimasukkan tidak sama! Masukkan password dengan benar!");
        }else{
            firebaseAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        showDetailDataUserActivity();
                        Toast.makeText(registerActivity.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(registerActivity.this, "" +task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            progressDialog.setMessage("Harap tunggu! Proses registrasi sedang berlangsung");
            progressDialog.setTitle("Proses Registrasi");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }
    private void showDetailDataUserActivity(){
        Intent intent = new Intent(registerActivity.this, detailDataUser.class);
        startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}