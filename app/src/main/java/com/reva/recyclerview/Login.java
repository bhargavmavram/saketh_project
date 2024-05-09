package com.reva.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText etUserName, etPassword;
    Button loginButton, registerButton, forgotPassButton;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new DBHelper(this);
        etUserName=findViewById(R.id.loginUsername);
        etPassword=findViewById(R.id.loginPassword);
        loginButton=findViewById(R.id.buttonLogin);
        registerButton=findViewById(R.id.btnReg);
        forgotPassButton=findViewById(R.id.btnForgotPass);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,password;
                username=etUserName.getText().toString();
                password=etPassword.getText().toString();
                boolean isLoggedIn=dbHelper.checkUser(username, password);
                if(dbHelper.checkUserName(username)){
                    if(!isLoggedIn){
                        Toast.makeText(Login.this, "Incorrect Password!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if(isLoggedIn){
                    Toast.makeText(Login.this, "Successfully Logged In!", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Login.this, WelcomePage.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "User Doesn't Exist!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forgotPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Forgot_Password.class);
                startActivity(intent);
            }
        });
    }
}