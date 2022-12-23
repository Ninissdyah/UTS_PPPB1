package com.example.uts_pppb1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class loginActivity extends AppCompatActivity {
//    String username="pakjoko";
//    String password="yangpentingcuan";
    EditText inputEmail, inputPassword;
    Button login;
    TextView noHaveAccount;
    String emailValidation = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.emailInput);
        inputPassword = findViewById(R.id.PassInput);
        login = findViewById(R.id.btn_login);
        noHaveAccount = findViewById(R.id.confirmAccountLogin);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        noHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });

        //ketika button login di klik
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(inputUsername.getText().toString().equalsIgnoreCase(username)&&inputPassword.getText().toString().equalsIgnoreCase(password)){
//                    startActivity(new Intent(loginActivity.this, detailDataUser.class));
//                }else{
//                    showAlertDialog();
//                }
                loginAccount();
                Intent intent = new Intent(loginActivity.this, detailDataUser.class);
//                startActivity(intent);
            }
        });
    }

    private void loginAccount(){
        String emailAddress = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (!emailAddress.matches(emailValidation)){
            inputEmail.setError("Email salah! Masukkan lagi email dengan benar!");
        }else if(password.isEmpty() || password.length()<6){
            inputPassword.setError("Password yang dimasukkan harus lebih dari 6 karakter!");
        }else{
            progressDialog.setMessage("Harap tunggu! Proses registrasi sedang berlangsung");
            progressDialog.setTitle("Proses Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        firebaseAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Intent intent = new Intent(loginActivity.this, detailDataUser.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                    showDetailDataUserActivity();
                    Toast.makeText(loginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(loginActivity.this, "" +task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void showDetailDataUserActivity(){
//        Intent intent = new Intent(loginActivity.this, addNews.class);
//        startActivity(intent);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//    }

    //alert dialog
//    public void showAlertDialog(){
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(loginActivity.this);
//        alertBuilder.setTitle("Username dan Password yang Anda masukkan salah!");
//        alertBuilder.setMessage("Silahkan coba masukkan username dan password yang benar!");
//
//        alertBuilder.setPositiveButton("Coba lagi", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                inputEmail.setText("");
//                inputPassword.setText("");
//            }
//        });
//        alertBuilder.show();
//    }
}