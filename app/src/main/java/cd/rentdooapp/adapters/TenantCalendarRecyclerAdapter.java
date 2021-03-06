package cd.rentdooapp.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.model.Notice;
import cd.rentdooapp.model.User;

/**
 * Created by shuge on 2017-10-21.
 */

public class TenantCalendarRecyclerAdapter extends RecyclerView.Adapter<TenantCalendarRecyclerAdapter.UserViewHolder> {
    private List<Notice> listNotices;

    public TenantCalendarRecyclerAdapter(List<Notice> listNotices) {
        this.listNotices = listNotices;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Log.v(TenantCalendarRecyclerAdapter.class.getSimpleName(),""+listNotices.size());
        return listNotices.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public AppCompatTextView textViewNumber;
        public AppCompatTextView textViewRole;
        public AppCompatTextView textViewRent;
        public Button editButton;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            textViewNumber = (AppCompatTextView) view.findViewById(R.id.textViewNumber);
            textViewRole = (AppCompatTextView) view.findViewById(R.id.textViewRole);
            textViewRent = (AppCompatTextView) view.findViewById(R.id.textViewRent);



            //add button to edit
            editButton = (Button) view.findViewById(R.id.userEdit_button);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test", "Button clicked");
                }
            });

            /*button = (ImageButton)v.findViewById(R.id.imageButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test", "Button clicked");
                }
            });*/
        }
    }


}
