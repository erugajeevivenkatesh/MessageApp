package com.venkatesh.messageapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ContentViewholder> {

        List<Reciverdata> mess;
    public class  ContentViewholder extends RecyclerView.ViewHolder
    {
        TextView ContentHead,Body;
        public ContentViewholder(View itemView) {
            super(itemView);
            ContentHead=itemView.findViewById(R.id.Head);
            Body=itemView.findViewById(R.id.contentdata);
        }
    }
    public InformationAdapter(List<Reciverdata> list)
    {
        this.mess=list;
    }
    @NonNull
    @Override
    public ContentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.contentdata,parent,false);
      return new ContentViewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewholder holder, int position) {
            Reciverdata reciverdata=mess.get(position);
            holder.ContentHead.setText(reciverdata.getName());
            holder.Body.setText(reciverdata.getBody());
    }

    @Override
    public int getItemCount() {
        return mess.size();
    }
}
