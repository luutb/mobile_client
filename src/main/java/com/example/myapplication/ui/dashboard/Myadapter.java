package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Learned;
import com.example.myapplication.model.RecyclerBaseItem;
import com.example.myapplication.model.Topic;
import com.example.myapplication.untils.ImageLoadTash;

import java.util.List;


public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    public static List<RecyclerBaseItem> topic;
    NavController navController;

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            View v = inflater.inflate(R.layout.item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }
        if (viewType == 2) {
            View v = inflater.inflate(R.layout.item2, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        RecyclerBaseItem item = this.topic.get(position);
        if (item instanceof  Topic){
            return 1;
        }if(item instanceof Learned)
        {
            return 2;

        }else
            return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, final int position) {
        if (this.getItemViewType(position) == 1){
            //   ((Adapter.ViewHolder)holder).bind((Topic)topic.get(position));
            final Topic topics = (Topic)topic.get(position);
            holder.txt1.setText(topics.getName());
            if(topics.getPicture()==null)
            {
                holder.img.setImageResource(R.mipmap.ic_launcher);
            }
            else {
                new ImageLoadTash("http://172.19.200.214:8080/File?file=" + topics.getPicture().replaceAll("\\s", ""), holder.img).execute();

            }
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    int a = topics.getId();
                    bundle.putInt("id",a);
                    navController = Navigation.findNavController(v);
                    navController.navigate(R.id.examFragment,bundle);

                }
            });
        }if(this.getItemViewType(position) == 2){
            final Learned learned = (Learned) topic.get(position);
            holder.txt1.setText(learned.getWord());
            holder.txt2.setText(learned.getVietnamese());
            new ImageLoadTash("http://172.19.200.214:8080/File?file=" + learned.getPicture().replaceAll("\\s",""),holder.img).execute();

            //
        }

    }

    public int  getItemCount() {
        if (topic == null)
            return 0;
        return topic.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt1;
        public View view;
        public  TextView txt2;
        public ImageView img;
        public RelativeLayout layout;
        public ViewHolder(View v)
        {
            super(v);
            view = v;
            img = (ImageView) v.findViewById(R.id.img);
            txt1  = (TextView) v.findViewById(R.id.txt_name);
            txt2 =(TextView)v.findViewById(R.id.txt_vn);
            layout =(RelativeLayout) v.findViewById(R.id.layout_item);
        }
        public void bind(Topic topic){
            txt1.setText(topic.getName());
        }
        public void bind (Learned learned){

        }

    }

    public List<RecyclerBaseItem>getTopic(){
        return topic;

    }
    public void setTopic(List<RecyclerBaseItem> topic)
    {
        this.topic = topic;
    }
}
