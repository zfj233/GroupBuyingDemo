package com.zfj.android.groupbuyingactivity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zfj.android.groupbuyingactivity.R;

/**
 * Created by zfj_ on 2017/5/29.
 */

public class RegisterActivity extends Activity {
    private EditText registerUsername;
    private EditText registerPwd;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_ui);
        initView();
        bindEvents();
    }

    private void initView() {
        registerUsername = (EditText) findViewById(R.id.register_user_text);
        registerPwd = (EditText) findViewById(R.id.register_pwd_text);
        registerBtn = (Button) findViewById(R.id.register_btn);
    }

    private void bindEvents() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerUsername.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.plz_enter_username,
                            Toast.LENGTH_SHORT).show();
                } else if(registerPwd.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.plz_enter_pwd,
                            Toast.LENGTH_SHORT).show();
                }else{
                    String username = registerUsername.getText().toString();
                    String pwd = registerPwd.getText().toString();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("pwd", pwd);
                    startActivity(intent);
                }

            }
        });
    }

}
