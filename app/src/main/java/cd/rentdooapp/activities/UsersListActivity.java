package cd.rentdooapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shuge on 2017-10-21.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.UsersRecyclerAdapter;
import cd.rentdooapp.model.User;
import cd.rentdooapp.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity implements View.OnClickListener{
    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatButton editUsersButton;
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
        setContentView(R.layout.activity_users_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Users");


        initViews();
        initListeners();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);

        editUsersButton = (AppCompatButton) findViewById(R.id.userEdit_button);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        editUsersButton.setOnClickListener(this);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userEdit_button:
                // Navigate to UserEditActivity.java
                Intent intentEdit = new Intent(getApplicationContext(), UserEditActivity.class);
                startActivity(intentEdit);
                break;
        }
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);
        User user = new User();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);



        //Bundle b = getIntent().getExtras();
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        user = databaseHelper.returnUser(emailFromIntent);
        groupFromIntent = user.getGroup();
        textViewName.setText(emailFromIntent);

        //textViewName.setText(emailFromIntent);

        //textViewNumber.setText("hello");
        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getGroupUser(groupFromIntent));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_toolbar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_logout:
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_bills:
                intent = new Intent(getApplicationContext(),TenantBillsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_calendar:
                intent = new Intent(getApplicationContext(),TenantCalendarActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_chores:
                intent = new Intent(getApplicationContext(),TenantChoresActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_notices:
                intent = new Intent(getApplicationContext(),TenantNoticesActivity.class);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
