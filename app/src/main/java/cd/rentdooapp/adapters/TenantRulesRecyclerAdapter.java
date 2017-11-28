package cd.rentdooapp.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.model.Notice;
import cd.rentdooapp.model.Rule;

/**
 * Created by shuge on 2017-10-21.
 */

public class TenantRulesRecyclerAdapter extends RecyclerView.Adapter<TenantRulesRecyclerAdapter.RuleViewHolder> {
    private List<Rule> listRules;

    public TenantRulesRecyclerAdapter(List<Rule> listRules) {
        this.listRules = listRules;
    }

    @Override
    public RuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_rule, parent, false);

        return new RuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RuleViewHolder holder, int position) {
        holder.textViewRule.setText(listRules.get(position).getName());
    }

    @Override
    public int getItemCount() {
        Log.v(TenantRulesRecyclerAdapter.class.getSimpleName(),""+listRules.size());
        return listRules.size();
    }


    /**
     * ViewHolder class
     */
    public class RuleViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewRule;

        public RuleViewHolder(View view) {
            super(view);
            textViewRule = (AppCompatTextView) view.findViewById(R.id.textViewRule);

        }
    }


}
