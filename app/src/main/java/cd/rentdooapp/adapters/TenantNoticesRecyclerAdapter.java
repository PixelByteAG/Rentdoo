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
import cd.rentdooapp.model.Notice;

/**
 * Created by shuge on 2017-10-21.
 */

public class TenantNoticesRecyclerAdapter extends RecyclerView.Adapter<TenantNoticesRecyclerAdapter.NoticeViewHolder> {
    private List<Notice> listNotices;

    public TenantNoticesRecyclerAdapter(List<Notice> listNotices) {
        this.listNotices = listNotices;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_notice, parent, false);

        return new NoticeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        holder.textViewNotice.setText(listNotices.get(position).getName());
    }

    @Override
    public int getItemCount() {
        Log.v(TenantNoticesRecyclerAdapter.class.getSimpleName(),""+listNotices.size());
        return listNotices.size();
    }


    /**
     * ViewHolder class
     */
    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewNotice;
        public ImageButton imageViewNoticeOptions;

        public NoticeViewHolder(View view) {
            super(view);
            textViewNotice = (AppCompatTextView) view.findViewById(R.id.textViewNotice);

            //add button to edit
            imageViewNoticeOptions = (ImageButton) view.findViewById(R.id.imageViewNoticeOptions);

            imageViewNoticeOptions.setOnClickListener(new View.OnClickListener() {
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
