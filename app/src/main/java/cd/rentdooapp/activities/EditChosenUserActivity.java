package cd.rentdooapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.UsersRecyclerAdapter;
import cd.rentdooapp.model.User;
import cd.rentdooapp.sql.DatabaseHelper;

/**
 * Created by Alexi on 2017-11-18.
 */

/*
Name
Email
Phone
Rent
Chores
 */
public class EditChosenUserActivity extends AppCompatActivity {
    private LinearLayout users_list;
    private AppCompatActivity activity = EditChosenUserActivity.this;

    private EditText textViewName;
    private EditText textViewEmail;
    private EditText textViewPhone;
    private EditText textViewRent;
    private EditText textViewChores;

    private List<User> listUsers;
    private DatabaseHelper databaseHelper;
    private int userIndex;
    private String userName;




    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        //getSupportActionBar().setTitle("");

        Bundle bundle = getIntent().getExtras();
        userIndex = bundle.getInt("UserIndex");
        userName = bundle.getString("UserName");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (EditText) findViewById(R.id.inputText_name);
        textViewEmail = (EditText) findViewById(R.id.inputText_email);
        textViewPhone = (EditText) findViewById(R.id.inputText_phone);
        textViewRent = (EditText) findViewById(R.id.inputText_rent);
        textViewChores = (EditText) findViewById(R.id.inputText_chores);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        User user = new User();

        databaseHelper = new DatabaseHelper(activity);
        user = databaseHelper.getAllUser().get(userIndex);

        textViewName.setText(userName);
        textViewEmail.setText(user.getEmail());
        textViewPhone.setText(user.getNumber());
        double rent = user.getRent();
        String rentStr = new Double(rent).toString();
        textViewRent.setText(rentStr);
        textViewChores.setText(user.choresToString());

        /*for(int i=0; i<listUsers.size(); i++){
            TextView textView = new TextView(this);
            final int passingInt = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String testMsg = listUsers.get(passingInt).getName();
                    Log.d("User Clicked: ", testMsg);

                    Intent editUserIntent = new Intent(EditChosenUserActivity.this, EditChosenUserActivity.class);
                }
            });

            textView.setTextSize(60.0f);
            textView.setText(listUsers.get(i).getName());
            users_list.addView(textView);
        }*/
    }
}
