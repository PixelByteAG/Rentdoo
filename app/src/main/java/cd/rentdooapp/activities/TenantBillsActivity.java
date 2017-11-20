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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.adapters.TenantBillsRecyclerAdapter;
import cd.rentdooapp.model.Bill;

/**
 * Created by krashton1 on 11/18/2017.
 */

public class TenantBillsActivity extends AppCompatActivity {
    private AppCompatActivity activity = TenantBillsActivity.this;
    private List<Bill> listBills;
    private TenantBillsRecyclerAdapter tenantBillsRecyclerAdapter;
    private RecyclerView recyclerViewBills;

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bills);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle("Bills");

        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewBills = (RecyclerView) findViewById(R.id.recyclerViewBills);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listBills = new ArrayList<>();
        for(int i=1;i<=5;i++){
            Bill tempBill=new Bill();
            tempBill.setAmount(100*i);
            tempBill.setDate(new Date(2017,10,i));
            tempBill.setId(i);
            tempBill.setName("Example Bill "+i);
            tempBill.setPaid((i%2 == 0) ? true : false);
            listBills.add(tempBill);
        }
        System.out.println(listBills);

        tenantBillsRecyclerAdapter = new TenantBillsRecyclerAdapter(listBills);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        recyclerViewBills.setLayoutManager(mLayoutManager);
        recyclerViewBills.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBills.setHasFixedSize(true);
        recyclerViewBills.setAdapter(tenantBillsRecyclerAdapter);
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
