package cd.rentdooapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by shuge on 2017-11-01.
 */
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.UsersRecyclerAdapter;
import cd.rentdooapp.model.User;
import cd.rentdooapp.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class RoommateActivity extends AppCompatActivity{
    private AppCompatActivity activity = RoommateActivity.this;
    private AppCompatTextView textViewName;
    private AppCompatTextView textViewName2;
    private AppCompatTextView textViewEmail;
    private AppCompatTextView textViewPhone;
    private AppCompatTextView textViewRole;
    private AppCompatTextView textViewRent;
    private AppCompatTextView textViewChores;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private int groupFromIntent;
    //private AppCompatTextView textViewNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //getSupportActionBar().setTitle("Rentdoo");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        textViewName2 = (AppCompatTextView) findViewById(R.id.textViewName2);
        textViewEmail = (AppCompatTextView) findViewById(R.id.textViewEmail);
        textViewPhone = (AppCompatTextView) findViewById(R.id.textViewNumber);
        textViewRole = (AppCompatTextView) findViewById(R.id.textViewRole);
        textViewRent = (AppCompatTextView) findViewById(R.id.textViewRent);
        textViewChores = (AppCompatTextView) findViewById(R.id.textViewChores);

        //recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        //textViewNumber = (AppCompatTextView) findViewById(R.id.textViewNumber);
    }

    private void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);
        User user = new User();

        databaseHelper = new DatabaseHelper(activity);



        //Bundle b = getIntent().getExtras();
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        user = databaseHelper.returnUser(emailFromIntent);
        groupFromIntent = user.getGroup();


        textViewName.setText(emailFromIntent);
        textViewName2.setText(user.getName());
        textViewEmail.setText(user.getEmail());
        textViewPhone.setText(user.getNumber());
        textViewRole.setText(user.getRole());
        textViewRent.setText(Double.toString(user.getRent()));
        textViewChores.setText(user.getChores());



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
            case R.id.action_logout:
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            case R.id.action_rules:
                intent = new Intent(getApplicationContext(),TenantRulesActivity.class);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
