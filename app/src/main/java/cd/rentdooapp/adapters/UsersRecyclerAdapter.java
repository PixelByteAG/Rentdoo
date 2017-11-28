package cd.rentdooapp.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import cd.rentdooapp.model.User;
import cd.rentdooapp.R;

/**
 * Created by shuge on 2017-10-21.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
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
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewNumber.setText(listUsers.get(position).getNumber());
        holder.textViewRole.setText(listUsers.get(position).getRole());
        holder.textViewRent.setText(Double.toString(listUsers.get(position).getRent()));
        holder.textViewChores.setText(listUsers.get(position).getChores());
        String message = "" + listUsers.get(position).getChores();
        Log.d("Test", message);
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewNumber;
        public AppCompatTextView textViewRole;
        public AppCompatTextView textViewRent;
        public AppCompatTextView textViewChores;
        public Button editButton;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewNumber = (AppCompatTextView) view.findViewById(R.id.textViewNumber);
            textViewRole = (AppCompatTextView) view.findViewById(R.id.textViewRole);
            textViewRent = (AppCompatTextView) view.findViewById(R.id.textViewRent);
            textViewChores = (AppCompatTextView) view.findViewById(R.id.textViewChores);


            //add button to edit
            /*editButton = (Button) view.findViewById(R.id.userEdit_button);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity buttonActivity = new AppCompatActivity();
                    String userEmail = textViewEmail.getText().toString();
                    String message = "Button " + userEmail + " clicked";
                    Log.d("Test", message);

                    Intent i = new Intent(buttonActivity, UserEditListActivity.class);
                    //buttonActivity.startActivity(i);

                    //Intent accountsIntent = new Intent(activity, UsersListActivity.class);
                }
            });*/
        }
    }


}
