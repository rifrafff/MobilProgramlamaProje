package com.rose.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail,edPassword,edConfirmPassword;
    Button btn;
    TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername=findViewById(R.id.editTextRegUserName);
        edEmail=findViewById(R.id.editTextRegEmail);
        edPassword=findViewById(R.id.editTextRegPassword);
        edConfirmPassword=findViewById(R.id.editTextRegConfirmPassword);
        btn=findViewById(R.id.buttonReg);
        tv=findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirmpassword=edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmpassword)){
                    if(password.compareTo(confirmpassword)==0){
                        if(password.length()>=6 && password.length()<=15){
                            if (!isValidEmail(email)) {
                                Toast.makeText(RegisterActivity.this, "Geçerli Bir E-posta Giriniz", Toast.LENGTH_SHORT).show();
                            }else{
                                db.Register(username,email,password);
                                Toast.makeText(RegisterActivity.this, "Başarıyla Kayıt Oldunuz", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Şifre Karakter Sayısı 6-15 Olmalıdır", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Şifreler Aynı Olmalı", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Tüm Bilgilerin Dolu Olduğundan Emin Olunuz!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}