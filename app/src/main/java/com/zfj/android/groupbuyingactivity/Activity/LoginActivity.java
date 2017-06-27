package com.zfj.android.groupbuyingactivity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zfj.android.groupbuyingactivity.R;

public class LoginActivity extends Activity {
    private TextView freeRegister;
    private EditText loginUsername;
    private EditText loginPwd;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        initView();
        bindEvents();
    }

    private void initView() {
        freeRegister = (TextView) findViewById(R.id.free_register);
        loginUsername = (EditText) findViewById(R.id.login_user_text);
        loginPwd = (EditText) findViewById(R.id.login_pwd_text);
        loginBtn = (Button) findViewById(R.id.login_btn);
    }

    private void bindEvents() {
        freeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginUsername.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, R.string.plz_enter_username,
                            Toast.LENGTH_SHORT).show();
                }else if(loginPwd.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, R.string.plz_enter_pwd,
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    String getUsername = intent.getStringExtra("username");
                    String getPwd = intent.getStringExtra("pwd");
                    if(loginUsername.getText().toString().equals(getUsername)
                            &&loginPwd.getText().toString().equals(getPwd)){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, R.string.err_login,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
