package com.example.bianca.myevents;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.regex.Pattern;

import io.realm.Realm;

public class SignUpActivity extends AppCompatActivity {

    TextView passwordCondition;
    TextInputEditText passwordtext, emailText, userNametext, passwordconfirmtext;
    Button signUpButton;
    ImageView goBack;
    Realm realm;
    private static final Pattern passwordPattern = Pattern.compile("/^[0-9a-zA-Z]+$/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        passwordCondition = findViewById(R.id.password_conditions_text);
        emailText = findViewById(R.id.emailText);
        userNametext = findViewById(R.id.userNameText);
        passwordtext = findViewById(R.id.passwordTextsignUp);
        passwordconfirmtext = findViewById(R.id.passwordConfirmText);
        goBack = findViewById(R.id.go_back);
        signUpButton = findViewById(R.id.sign_up_button);
        final Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        //Instancia de Realm
        realm = Realm.getDefaultInstance();

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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userNametext.getText().length() > 0 && emailText.getText().length() > 0 && passwordtext.getText().length() > 0 && passwordconfirmtext.getText().length() > 0) {
                    if (passwordtext.getText().length() > 3) {
                        if ((passwordtext.getText().toString()).equals(passwordconfirmtext.getText().toString())) {
                            String username = userNametext.getText().toString();
                            String email = emailText.getText().toString();
                            String password = passwordtext.getText().toString();
                            saveUser(username, email, password);
                            Snackbar.make(view, R.string.new_account_snackbar_text, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.sign_in_button_text, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent signInActivity = new Intent(getApplicationContext(), SignInActivity.class);
                                            startActivity(signInActivity);
                                        }
                                    }).show();
                        }else{
                            passwordconfirmtext.setError(getString(R.string.not_matching_passwords_error_Text));
                        }
                    }else{
                        passwordtext.setError(getString(R.string.password_error));
                        passwordCondition.setTextColor(R.color.error);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), R.string.incomplete_fields_toast_text, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void saveUser(final String username, final String email, final String password) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = new User(username, email, password);
                realm.copyToRealm(user);
            }
        });

    }
}

