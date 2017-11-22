package com.example.bianca.myevents;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SignUpActivity extends AppCompatActivity {

    TextView passwordCondition;
    TextInputEditText passwordtext, emailText, userNametext;
    Button signUpButton;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        passwordCondition = findViewById(R.id.password_conditions_text);
        emailText = findViewById(R.id.emailText);
        userNametext = findViewById(R.id.userNameText);
        passwordtext = findViewById(R.id.passwordTextsignUp);
        goBack = findViewById(R.id.go_back);
        final Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        passwordCondition.setVisibility(View.GONE);

        passwordtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                passwordCondition.setVisibility(View.VISIBLE);
            }
        });

        emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                passwordCondition.setVisibility(View.GONE);
            }
        });

        userNametext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                passwordCondition.setVisibility(View.GONE);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivity);
            }
        });
    }

}
