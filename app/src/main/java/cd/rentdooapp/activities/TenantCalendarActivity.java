package cd.rentdooapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
}
