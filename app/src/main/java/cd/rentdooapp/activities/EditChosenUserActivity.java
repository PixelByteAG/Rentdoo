package cd.rentdooapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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


public class EditChosenUserActivity extends AppCompatActivity {
    private LinearLayout users_list;
    private AppCompatActivity activity = EditChosenUserActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private int groupFromIntent;

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        //getSupportActionBar().setTitle("");
        //initViews();
        //initObjects();

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
        //usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);
        User user = new User();
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //recyclerViewUsers.setLayoutManager(mLayoutManager);
        //recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        //recyclerViewUsers.setHasFixedSize(true);
        //recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);
        listUsers = databaseHelper.getAllUser();
        /*String message = "";
        for(int i=0; i<listUsers.size(); i++){
            message = message + listUsers.get(i).getName() + ", ";
        }

        //String message = "";
        Log.d("DB: ", message);*/

        //Bundle b = getIntent().getExtras();
        /*String emailFromIntent = getIntent().getStringExtra("EMAIL");
        user = databaseHelper.returnUser(emailFromIntent);
        groupFromIntent = user.getGroup();
        textViewName.setText(emailFromIntent);*/

        String[] textArray = {"one", "two", "three", "four"};
        //LinearLayout linearLayout = new LinearLayout(this);
        //setContentView(users_list);
        users_list.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<listUsers.size(); i++){
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
        }



        //textViewName.setText(emailFromIntent);

        //textViewNumber.setText("hello");
        //getDataFromSQLite();*/
    }
}
