package cd.rentdooapp.activities;

import android.app.Activity;
import android.content.Intent;
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

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.TenantCalendarRecyclerAdapter;
import cd.rentdooapp.adapters.UsersRecyclerAdapter;
import cd.rentdooapp.model.Notice;
import cd.rentdooapp.model.User;
import cd.rentdooapp.sql.DatabaseHelper;

/**
 * Created by krashton1 on 11/18/2017.
 */

public class TenantCalendarActivity extends AppCompatActivity {
    private AppCompatActivity activity = TenantCalendarActivity.this;
    private AppCompatTextView textViewName;
    private MaterialCalendarView recyclerViewTenantCalendar;
    private List<Notice> listNotices;
    private TenantCalendarRecyclerAdapter tenantCalendarRecyclerAdapter;

//for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_calendar);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Calendar");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewTenantCalendar = (MaterialCalendarView) findViewById(R.id.calendarView);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listNotices = new ArrayList<>();
        tenantCalendarRecyclerAdapter = new TenantCalendarRecyclerAdapter(listNotices);
        User user = new User();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerViewTenantCalendar.setLayoutManager(mLayoutManager);
//        recyclerViewTenantCalendar.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewTenantCalendar.setHasFixedSize(true);
//        recyclerViewTenantCalendar.setAdapter(tenantCalendarRecyclerAdapter);


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
