package cd.rentdooapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


public class UserSmsActivity extends AppCompatActivity {
    private LinearLayout users_list;
    private AppCompatActivity activity = UserSmsActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private int groupFromIntent;
    private String masterEmail;
    String allNumbers = "";

    //for the edit buttons
    /*public void editUser(RecyclerView view) {
        // Do something in response to button click
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_edit_list);
        //getSupportActionBar().setTitle("");

        Bundle bundle = getIntent().getExtras();
        groupFromIntent = bundle.getInt("groupID");
        masterEmail = bundle.getString("masterEmail");

        initViews();
        initObjects();

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        //recreate();
        //finish();
        //startActivity(getIntent());
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
        User user = new User();
        databaseHelper = new DatabaseHelper(activity);
        listUsers = databaseHelper.getGroupUser(groupFromIntent);

        users_list.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<listUsers.size(); i++){
            Button text_name = new Button(this);
            final int passingInt = i;
            text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = listUsers.get(passingInt).getName();
                    String number = "+1" + listUsers.get(passingInt).getNumber();

                    allNumbers = allNumbers + number;
                    if(!(passingInt+1 == listUsers.size())){
                        allNumbers = allNumbers + ", ";
                    }
                    String strMessage = "Hey " + name + "\nRent is due soon!\nPlease check Rentdoo!";

                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    sendIntent.putExtra("address", number);
                    sendIntent.putExtra("sms_body", strMessage);
                    startActivity(sendIntent);
                }
            });

            text_name.setTextSize(30.0f);
            text_name.setText(listUsers.get(i).getName());
            users_list.addView(text_name);

            /*View v = new View(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    5
            ));
            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

            users_list.addView(v);*/
        }

        /*Button text_all = new Button(this);
        text_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "All Roommates";
                String number = allNumbers;

                String strMessage = "Hey, Rent is due soon!\nPlease check Rentdoo!";

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("vnd.android-dir/mms-sms");
                sendIntent.putExtra("address", allNumbers);
                sendIntent.putExtra("sms_body", strMessage);
                startActivity(sendIntent);
            }
        });

        text_all.setTextSize(30.0f);
        text_all.setText("All Roommates");
        users_list.addView(text_all);

        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        ));
        v.setBackgroundColor(Color.parseColor("#B3B3B3"));

        users_list.addView(v);*/
    }
}
