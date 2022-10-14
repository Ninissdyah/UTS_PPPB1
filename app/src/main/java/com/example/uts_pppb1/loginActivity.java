package com.example.uts_pppb1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    String username="pakjoko";
    String password="yangpentingcuan";
    EditText inputUsername, inputPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.usernameInput);
        inputPassword = findViewById(R.id.PassInput);
        login = findViewById(R.id.btn_login);

        //ketika button login di klik
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputUsername.getText().toString().equalsIgnoreCase(username)&&inputPassword.getText().toString().equalsIgnoreCase(password)){
                    startActivity(new Intent(loginActivity.this, detailDataUser.class));
                }else{
                    showAlertDialog();
                }
            }
        });
    }

    //alert dialog
    public void showAlertDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(loginActivity.this);
        alertBuilder.setTitle("Username dan Password yang Anda masukkan salah!");
        alertBuilder.setMessage("Silahkan coba masukkan username dan password yang benar!");

        alertBuilder.setPositiveButton("Coba lagi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                inputUsername.setText("");
                inputPassword.setText("");
            }
        });
        alertBuilder.show();
    }
}