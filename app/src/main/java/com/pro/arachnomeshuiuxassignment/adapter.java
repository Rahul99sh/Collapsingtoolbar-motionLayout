package com.pro.arachnomeshuiuxassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> list;

    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((viewHolder) holder).name.setText(list.get(position));
        ((viewHolder) holder).title.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class viewHolder extends  RecyclerView.ViewHolder{

        TextView name, title;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
        }
    }
}
