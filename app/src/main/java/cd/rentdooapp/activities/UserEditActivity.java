package cd.rentdooapp.activities;

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

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_edit_list);
        //getSupportActionBar().setTitle("");
        initViews();
        initObjects();

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
            textView.setText(listUsers.get(i).getName());
            users_list.addView(textView);
        }



        //textViewName.setText(emailFromIntent);

        //textViewNumber.setText("hello");
        //getDataFromSQLite();*/
    }
}
