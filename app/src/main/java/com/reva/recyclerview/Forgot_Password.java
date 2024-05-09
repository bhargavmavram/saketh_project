package com.reva.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgot_Password extends AppCompatActivity {
    EditText etUserName, etNewPassword, etNewRetypedPass;
    Button resetButton;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        etUserName=findViewById(R.id.resetEtUserName);
        etNewPassword=findViewById(R.id.resetPass);
        etNewRetypedPass=findViewById(R.id.retypeResetPass);
        dbHelper=new DBHelper(this);
        resetButton=findViewById(R.id.buttonReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, newPassword, retypedPassword;
                username=etUserName.getText().toString();
                newPassword=etNewPassword.getText().toString();
                retypedPassword=etNewRetypedPass.getText().toString();
                if(username.isEmpty()||newPassword.isEmpty()||retypedPassword.isEmpty()){
                    Toast.makeText(Forgot_Password.this, "Please Fill All The Fields!", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!newPassword.equals(retypedPassword)){
                    Toast.makeText(Forgot_Password.this, "Passwords Don't Match!", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    if(dbHelper.checkUserName(username)){
                        boolean updatedPassword=dbHelper.updatePassword(username, newPassword);
                        if(updatedPassword){
                            Toast.makeText(Forgot_Password.this, "Password Updated!", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Forgot_Password.this, Login.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Forgot_Password.this, "Failed To Create New Password!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
            }
        });
    }
}