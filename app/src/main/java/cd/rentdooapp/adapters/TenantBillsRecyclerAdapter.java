package cd.rentdooapp.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

import cd.rentdooapp.R;
import cd.rentdooapp.model.Bill;

/**
 * Created by shuge on 2017-10-21.
 */

public class TenantBillsRecyclerAdapter extends RecyclerView.Adapter<TenantBillsRecyclerAdapter.BillViewHolder> {
    private List<Bill> listBills;

    public TenantBillsRecyclerAdapter(List<Bill> listBills) {
        this.listBills = listBills;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_bill, parent, false);

        return new BillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        holder.textViewBill.setText(listBills.get(position).getName());
        holder.textViewBillAmount.setText("$"+listBills.get(position).getAmount());
        if(listBills.get(position).getPaid()){
            holder.imageViewBillPaidCheck.setImageResource(android.R.drawable.checkbox_on_background);
        }else{
            holder.imageViewBillPaidCheck.setImageResource(android.R.drawable.checkbox_off_background);
        }
    }

    @Override
    public int getItemCount() {
        Log.v(TenantBillsRecyclerAdapter.class.getSimpleName(),""+listBills.size());
        return listBills.size();
    }


    /**
     * ViewHolder class
     */
    public class BillViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewBill;
        public AppCompatTextView textViewBillAmount;
        public ImageButton imageViewBillOptions;
        public ImageView imageViewBillPaidCheck;

        public BillViewHolder(View view) {
            super(view);
            textViewBill = (AppCompatTextView) view.findViewById(R.id.textViewBill);
            textViewBillAmount = (AppCompatTextView) view.findViewById(R.id.textViewBillAmount);



            //add button to edit
            imageViewBillOptions = (ImageButton) view.findViewById(R.id.imageViewBillOptions);

            imageViewBillPaidCheck =(ImageView) view.findViewById(R.id.imageViewBillPaidCheck);

            imageViewBillOptions.setOnClickListener(new View.OnClickListener() {
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
