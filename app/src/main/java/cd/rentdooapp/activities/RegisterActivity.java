package cd.rentdooapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by shuge on 2017-10-21.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CheckBox;

import cd.rentdooapp.R;
import cd.rentdooapp.helpers.InputValidation;
import cd.rentdooapp.sql.DatabaseHelper;
import cd.rentdooapp.model.User;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutNumber;
    private TextInputLayout textInputLayoutGroup;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextNumber;
    private TextInputEditText textInputEditTextGroup;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private CheckBox appCheckBox;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutNumber = (TextInputLayout) findViewById(R.id.textInputLayoutNumber);
        textInputLayoutGroup = (TextInputLayout) findViewById(R.id.textInputLayoutGroup);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextNumber = (TextInputEditText) findViewById(R.id.textInputEditTextNumber);
        textInputEditTextGroup = (TextInputEditText) findViewById(R.id.textInputEditTextGroup);

        appCheckBox = (CheckBox) findViewById(R.id.checkLeaseHolder);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    public void onCheckboxClicked(){
        boolean checked = appCheckBox.isChecked();

        if(checked){
            textInputLayoutGroup.setVisibility(View.GONE);
        }

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        appCheckBox.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
            case R.id.checkLeaseHolder:
                boolean checked = appCheckBox.isChecked();

                if(checked){
                    textInputEditTextGroup.setHint("Create a Group ID");
                }else{
                    textInputEditTextGroup.setHint("Enter a Group ID to join!");
                }
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextNumber, textInputLayoutNumber, getString(R.string.error_message_number))){
            return;
        }
        if (!inputValidation.isInputEditTextNumbers(textInputEditTextNumber, textInputLayoutNumber, getString(R.string.error_message_number))){
            return;
        }

        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if((!inputValidation.isInputEditTextFilled(textInputEditTextGroup, textInputLayoutGroup, "Enter a valid group")) && (!appCheckBox.isChecked())){
            return;
        }

        if ((!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) && (!appCheckBox.isChecked()) && (databaseHelper.checkGroup(Integer.parseInt(textInputEditTextGroup.getText().toString().trim())))) {
            //roommate registration successful
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setNumber(textInputEditTextNumber.getText().toString().trim());
            user.setGroup(Integer.parseInt(textInputEditTextGroup.getText().toString().trim()));

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else if ((!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) && (appCheckBox.isChecked()) && (!databaseHelper.checkGroup(Integer.parseInt(textInputEditTextGroup.getText().toString().trim())))) {
            //leaseholder registration successful
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setNumber(textInputEditTextNumber.getText().toString().trim());
            user.setGroup(Integer.parseInt(textInputEditTextGroup.getText().toString().trim()));

            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

        }else if ((databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()))) {
            //email in use
            Snackbar.make(nestedScrollView, "Email already exists", Snackbar.LENGTH_LONG).show();
            textInputEditTextEmail.setText(null);
        }else if ((!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) && (appCheckBox.isChecked()) && (databaseHelper.checkGroup(Integer.parseInt(textInputEditTextGroup.getText().toString().trim())))) {
            //email not in use, leaseholder registration, ID Already Exists
            Snackbar.make(nestedScrollView, "Group ID Already Exists, Choose Another!", Snackbar.LENGTH_LONG).show();
            textInputEditTextGroup.setText(null);
        }else if((!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) && (!appCheckBox.isChecked()) && (!databaseHelper.checkGroup(Integer.parseInt(textInputEditTextGroup.getText().toString().trim())))) {
            //email not in use, roommate registration, ID does not exist in database so registration fails
            Snackbar.make(nestedScrollView, "Group ID does not Exist!", Snackbar.LENGTH_LONG).show();
            textInputEditTextGroup.setText(null);
        }else{
            //Show common Error
            Snackbar.make(nestedScrollView, "Unknown Error", Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextNumber.setText(null);
        textInputEditTextGroup.setText(null);
    }
}
