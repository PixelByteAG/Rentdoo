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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.TenantNoticesRecyclerAdapter;
import cd.rentdooapp.model.Notice;

/**
 * Created by krashton1 on 11/18/2017.
 */

public class TenantNoticesActivity extends AppCompatActivity {
    private AppCompatActivity activity = TenantNoticesActivity.this;
    private List<Notice> listNotices;
    private TenantNoticesRecyclerAdapter tenantNoticesRecyclerAdapter;
    private RecyclerView recyclerViewNotices;

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notices);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Notices");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewNotices = (RecyclerView) findViewById(R.id.recyclerViewNotices);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listNotices = new ArrayList<>();
        for(int i=1;i<=6;i++){
            Notice tempNotice=new Notice();
            tempNotice.setDate(new Date(2017,10,i));
            tempNotice.setId(i);
            tempNotice.setName("Example Notice "+i);
            listNotices.add(tempNotice);
        }
        System.out.println(listNotices);

        tenantNoticesRecyclerAdapter = new TenantNoticesRecyclerAdapter(listNotices);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        recyclerViewNotices.setLayoutManager(mLayoutManager);
        recyclerViewNotices.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNotices.setHasFixedSize(true);
        recyclerViewNotices.setAdapter(tenantNoticesRecyclerAdapter);
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
