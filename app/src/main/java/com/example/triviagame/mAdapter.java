package com.example.triviagame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {

    private String[] data;
    private String[] cap;
    private Context context;
    private OnItemListener onItemListener;

    mAdapter(Context cntxt, String[] country, String[] capital, OnItemListener onItemListener) {
        context = cntxt;
        data = country;
        cap = capital;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rows, viewGroup, false);
        return new ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.textView1.setText(data[i]);
        recyclerViewHolder.textView2.setText(cap[i]);
    }


    @Override
    public int getItemCount() {
        return 5;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //private final OnItemListener onItemListener;
        TextView textView1;
        TextView textView2;

        ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.country_text);
            textView2 =itemView.findViewById(R.id.capital_text);

            itemView.setOnClickListener(this);
            //this.onItemListener = onItemListener;
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

}
