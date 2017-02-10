package com.android15dev.nursuingcanteen.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android15dev.nursuingcanteen.R;
import com.android15dev.nursuingcanteen.views.activities.ListActivity;

/**
 * Created by krishnauser on 10-Feb-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final String[] arr;
    Activity activity;

    public HomeAdapter (Activity activity) {
        this.activity = activity;
        arr = activity.getResources().getStringArray(R.array.categories);
    }

    @Override
    public HomeViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_home, null);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder (HomeViewHolder holder, int position) {
        holder.txtName.setText(arr[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                activity.startActivity(new Intent(activity, ListActivity.class));
            }
        });

    }

    @Override
    public int getItemCount () {
        return arr.length;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;

        public HomeViewHolder (View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
        }
    }

}
