package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Topic;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<Topic> topic;
    NavController navController;



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //  for (position = 0; position <= getItemCount(); position++) {
        final Topic test = topic.get(position);
        holder.txt1.setText(test.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            int a = test.getId();
            bundle.putInt("id",a);
            navController = Navigation.findNavController(v);
            navController.navigate(R.id.examFragment,bundle);
        }
    });
    }


    @Override
    public int  getItemCount() {
        if (topic == null)
            return 0;
        return topic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt1;
        public View view;
        public RelativeLayout layout;
        public ViewHolder(View v)
        {
            super(v);
            view = v;
            txt1  = (TextView) v.findViewById(R.id.txt_name);
            layout =(RelativeLayout) v.findViewById(R.id.layout_item);
        }
        public void addItem(int position,Topic test )
        {
            topic.add(position, test);
            notifyItemInserted(position);
        }
    }

    public List<Topic>getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }
}
