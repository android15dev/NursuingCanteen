package com.android15dev.nursuingcanteen.views.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android15dev.nursuingcanteen.R;

/**
 * Created by krishnauser on 10-Feb-17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private final String[] arr;
    Activity activity;

    public ListAdapter (Activity activity, String[] arr) {
        this.activity = activity;
        this.arr = arr;
    }

    @Override
    public ListViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_list, null);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder (ListViewHolder holder, final int position) {
        holder.txtName.setText(arr[position]);
    }

    @Override
    public int getItemCount () {
        return arr.length;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;

        public ListViewHolder (View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
        }
    }

}
