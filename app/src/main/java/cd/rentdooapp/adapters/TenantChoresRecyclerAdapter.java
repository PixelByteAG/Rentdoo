package cd.rentdooapp.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.model.Chore;

/**
 * Created by shuge on 2017-10-21.
 */

public class TenantChoresRecyclerAdapter extends RecyclerView.Adapter<TenantChoresRecyclerAdapter.ChoreViewHolder> {
    private List<Chore> listChores;

    public TenantChoresRecyclerAdapter(List<Chore> listChores) {
        this.listChores = listChores;
    }

    @Override
    public ChoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_chore, parent, false);

        return new ChoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChoreViewHolder holder, int position) {
        holder.textViewChore.setText(listChores.get(position).getName());

        if(listChores.get(position).getDone()){
            holder.imageViewChoreDoneCheck.setImageResource(android.R.drawable.presence_online);
            holder.imageViewChoreDoneCheck.setTag("presence_online");
        }else{
            holder.imageViewChoreDoneCheck.setImageResource(android.R.drawable.ic_delete);
            holder.imageViewChoreDoneCheck.setTag("ic_delete");
        }

        holder.textViewChoreAssigned.setText(listChores.get(position).getAssigned());
    }

    @Override
    public int getItemCount() {
        Log.v(TenantChoresRecyclerAdapter.class.getSimpleName(),""+listChores.size());
        return listChores.size();
    }


    /**
     * ViewHolder class
     */
    public class ChoreViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewChore;
        public AppCompatTextView textViewChoreAssigned;
        public ImageButton imageViewChoreDoneCheck;

        public ChoreViewHolder(View view) {
            super(view);
            textViewChore = (AppCompatTextView) view.findViewById(R.id.textViewChore);

            //add button to set completion
            imageViewChoreDoneCheck = (ImageButton) view.findViewById(R.id.imageViewChoreDoneCheck);

            textViewChoreAssigned = (AppCompatTextView) view.findViewById(R.id.textViewChoreAssigned);

            imageViewChoreDoneCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imageViewChoreDoneCheck.getTag()=="presence_online"){
                        imageViewChoreDoneCheck.setImageResource(android.R.drawable.ic_delete);
                        imageViewChoreDoneCheck.setTag("ic_delete");
                    }else if(imageViewChoreDoneCheck.getTag()=="ic_delete"){
                        imageViewChoreDoneCheck.setImageResource(android.R.drawable.presence_online);   
                        imageViewChoreDoneCheck.setTag("presence_online");
                    }

                }
            });


        }
    }


}
