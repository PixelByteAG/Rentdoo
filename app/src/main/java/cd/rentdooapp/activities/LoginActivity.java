package cd.rentdooapp.activities;

/**
 * Created by shuge on 2017-10-21.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;

import cd.rentdooapp.R;
import cd.rentdooapp.helpers.InputValidation;
import cd.rentdooapp.sql.DatabaseHelper;
import cd.rentdooapp.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                //finish();
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        //fixed a typo here "error_message_email" to "error_message_password"
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            User user;

            user = databaseHelper.returnUser(textInputEditTextEmail.getText().toString().trim());

            if((user.getRole().equals("Leaseholder"))){
                Intent accountsIntent = new Intent(activity, UsersListActivity.class);


                accountsIntent.putExtra("EMAIL", user.getEmail());//textInputEditTextEmail.getText().toString().trim());
                //Bundle b = new Bundle();
                //b.putString("EMAIL", user.getEmail());
                //b.putInt("GROUP", user.getGroup());//Integer.toString(databaseHelper.returnGroup(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim()))
                //accountsIntent.putExtra();
                //maybe add number here? textInputEditTextNumber
                emptyInputEditText();

                /*String message = "Testing 1 2 3";
                String phoneNumber = "16139838738";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);*/

                startActivity(accountsIntent);
            }

            if(user.getRole().equals("Roommate")){
                Intent accountsIntent = new Intent(activity, RoommateActivity.class);


                accountsIntent.putExtra("EMAIL", user.getEmail());//textInputEditTextEmail.getText().toString().trim());
                //Bundle b = new Bundle();
                //b.putString("EMAIL", user.getEmail());
                //b.putInt("GROUP", user.getGroup());//Integer.toString(databaseHelper.returnGroup(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim()))
                //accountsIntent.putExtra();
                //maybe add number here? textInputEditTextNumber
                emptyInputEditText();
                startActivity(accountsIntent);
            }





        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}