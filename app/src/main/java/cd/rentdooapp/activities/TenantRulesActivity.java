package cd.rentdooapp.activities;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.TenantNoticesRecyclerAdapter;
import cd.rentdooapp.adapters.TenantRulesRecyclerAdapter;
import cd.rentdooapp.model.Notice;
import cd.rentdooapp.model.Rule;

/**
 * Created by krashton1 on 11/18/2017.
 */

public class TenantRulesActivity extends AppCompatActivity {
    private AppCompatActivity activity = TenantRulesActivity.this;
    private List<Rule> listRules;
    private TenantRulesRecyclerAdapter tenantRulesRecyclerAdapter;
    private RecyclerView recyclerViewRules;
    private int groupFromIntent;

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rules);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Rules");

        Bundle bundle = getIntent().getExtras();
        groupFromIntent = bundle.getInt("groupID");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewRules = (RecyclerView) findViewById(R.id.recyclerViewRules);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listRules = new ArrayList<>();

        Rule tempRule;

        tempRule=new Rule();
        tempRule.setId(101);
        tempRule.setName("No guests can stay for more than 3 consecutive days");
        listRules.add(tempRule);

        tempRule=new Rule();
        tempRule.setId(102);
        tempRule.setName("No running in the hallways");
        listRules.add(tempRule);

        tempRule=new Rule();
        tempRule.setId(103);
        tempRule.setName("No touching the thermostat, let John know if the temperature need to be changed");
        listRules.add(tempRule);

        tempRule=new Rule();
        tempRule.setId(104);
        tempRule.setName("Dont touch the button");
        listRules.add(tempRule);

        tempRule=new Rule();
        tempRule.setId(105);
        tempRule.setName("The cake is a lie");
        listRules.add(tempRule);

        System.out.println(listRules);

        tenantRulesRecyclerAdapter = new TenantRulesRecyclerAdapter(listRules);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        recyclerViewRules.setLayoutManager(mLayoutManager);
        recyclerViewRules.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRules.setHasFixedSize(true);
        recyclerViewRules.setAdapter(tenantRulesRecyclerAdapter);
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
