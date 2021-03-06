package cd.rentdooapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alexi on 2017-11-18.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.UsersRecyclerAdapter;
import cd.rentdooapp.model.User;
import cd.rentdooapp.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class UserEditActivity extends AppCompatActivity {
    private LinearLayout users_list;
    private AppCompatActivity activity = UserEditActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private int groupFromIntent;
    private String masterEmail;

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_edit_list);
        //getSupportActionBar().setTitle("");

        Bundle bundle = getIntent().getExtras();
        groupFromIntent = bundle.getInt("groupID");
        masterEmail = bundle.getString("masterEmail");

        initViews();
        initObjects();

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        //recreate();
        //finish();
        //startActivity(getIntent());
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        //textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        //recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        //textViewNumber = (AppCompatTextView) findViewById(R.id.textViewNumber);
        users_list = (LinearLayout) findViewById(R.id.user_list_layout);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        databaseHelper = new DatabaseHelper(activity);
        listUsers = databaseHelper.getGroupUser(groupFromIntent);

        users_list.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<listUsers.size(); i++){
            Button textButton = new Button(this);
            final int passingInt = i;
            textButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String testMsg = listUsers.get(passingInt).getName();
                    Log.d("User Clicked: ", testMsg);

                    Intent editUserIntent = new Intent(UserEditActivity.this, EditChosenUserActivity.class);
                    editUserIntent.putExtra("UserIndex", passingInt);
                    editUserIntent.putExtra("UserName", listUsers.get(passingInt).getName());
                    editUserIntent.putExtra("masterEmail", masterEmail);
                    //finish();
                    startActivity(editUserIntent);
                }
            });

            textButton.setTextSize(30.0f);
            textButton.setText(listUsers.get(i).getName());
            users_list.addView(textButton);

            /*View v = new View(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    5
            ));
            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

            users_list.addView(v);*/
        }
    }
}
