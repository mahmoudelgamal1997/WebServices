package com.example2017.android.webservices;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by M7moud on 02-Apr-18.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MovieHolder> {


    List<model> list;

    //cnstructr
    public Adapter(List<model> list){
        this.list=list;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_frame,parent,false);
        MovieHolder hold=new MovieHolder(row);


        return hold;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        model model=list.get(position);
        holder.WriterName.setText(model.writer);
        holder.Post.setText(model.post);





    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    class MovieHolder extends RecyclerView.ViewHolder{

        TextView WriterName,Post;

        public MovieHolder(View itemView) {
            super(itemView);

            WriterName=(TextView)itemView.findViewById(R.id.textView2);
            Post=(TextView)itemView.findViewById(R.id.textView3);


        }

}




}