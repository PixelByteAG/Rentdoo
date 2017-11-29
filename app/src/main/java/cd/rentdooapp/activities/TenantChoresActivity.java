package cd.rentdooapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.TenantChoresRecyclerAdapter;
import cd.rentdooapp.model.Chore;
import cd.rentdooapp.model.User;
import cd.rentdooapp.sql.DatabaseHelper;

/**
 * Created by krashton1 on 11/18/2017.
 */

public class TenantChoresActivity extends AppCompatActivity {
    private AppCompatActivity activity = TenantChoresActivity.this;
    private List<User> listUsers;
    private DatabaseHelper databaseHelper;
    private List<Chore> listChores;
    private TenantChoresRecyclerAdapter tenantChoresRecyclerAdapter;
    private LinearLayout choresView;
    private RecyclerView recyclerViewChores;
    private int groupFromIntent;

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chores);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Chores");

        Bundle bundle = getIntent().getExtras();
        groupFromIntent = bundle.getInt("groupID");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewChores = (RecyclerView) findViewById(R.id.recyclerViewChores);
        choresView = (LinearLayout) findViewById(R.id.chores_view);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        listUsers = new ArrayList<>();
        listUsers = databaseHelper.getGroupUser(groupFromIntent);
        listChores = new ArrayList<>();

        //for loop: users
        ArrayList<Chore> userChores = new ArrayList<>();
        for(int a=0; a<listUsers.size(); a++){
            userChores = listUsers.get(a).chores_stringToArrayList(listUsers.get(a).getChores());

            for(int i=0; i<userChores.size(); i++){
                listChores.add(userChores.get(i));
            }

        }

        //for loop: chores
        /*for(int i=1;i<=6;i++){
            Chore tempChore=new Chore();
            tempChore.setDate(new Date(2017,10,i));
            tempChore.setId(i);
            tempChore.setName("Example Chore "+i);
            tempChore.setDone((i%2 == 0) ? true : false);
            tempChore.setAssigned("Example Person "+i);
            listChores.add(tempChore);
        }*/
        //System.out.println(listChores);

        tenantChoresRecyclerAdapter = new TenantChoresRecyclerAdapter(listChores);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        recyclerViewChores.setLayoutManager(mLayoutManager);
        recyclerViewChores.setItemAnimator(new DefaultItemAnimator());
        recyclerViewChores.setHasFixedSize(true);
        recyclerViewChores.setAdapter(tenantChoresRecyclerAdapter);
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
                intent.putExtra("groupID", groupFromIntent);
                startActivity(intent);
                return true;
            case R.id.action_calendar:
                intent = new Intent(getApplicationContext(),TenantCalendarActivity.class);
                intent.putExtra("groupID", groupFromIntent);
                startActivity(intent);
                return true;
            case R.id.action_chores:
                intent = new Intent(getApplicationContext(),TenantChoresActivity.class);
                intent.putExtra("groupID", groupFromIntent);
                startActivity(intent);
                return true;
            case R.id.action_notices:
                intent = new Intent(getApplicationContext(),TenantNoticesActivity.class);
                intent.putExtra("groupID", groupFromIntent);
                startActivity(intent);
                return true;
            case R.id.action_rules:
                intent = new Intent(getApplicationContext(),TenantRulesActivity.class);
                intent.putExtra("groupID", groupFromIntent);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
