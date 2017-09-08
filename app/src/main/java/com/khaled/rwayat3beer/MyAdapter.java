package com.khaled.rwayat3beer;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
// import android.widget.ImageView;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    /*ArrayList<StoryiesArray> mData = new ArrayList<>();
        MyAdapter(ArrayList<StoryiesArray> mData); */
    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MyAdapter(Context context, List<String> data) {
        this.mInflater= LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View itemLayoutView = mInflater.inflate(R.layout.row,parent,false);


        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        String Stories = mData.get(position);

        viewHolder.txtViewTitle.setText(Stories);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public /*static*/ class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtViewTitle;

        private final Context context;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.TextViewRowID);

            itemView.setOnClickListener(this);
            context=itemLayoutView.getContext();


        }



        @Override
        public void onClick(View view){

            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition(), getItemId());
            //Toast.makeText(view.getContext(),"position =" +getPosition()  ,Toast.LENGTH_SHORT).show();

            // txtViewTitle.setTextColor(0xFFF08D2F);

            // final   Intent intent = new Intent(context, WebViewHtml.class);
            //context.startActivity(intent);




        }

    }

    public String getItem(int id){
        return mData.get(id);
    }

    public void setmClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position, long id);
    }




/*public void setFilter(ArrayList<String> newList){


    mData.addAll(newList);
    notifyDataSetChanged();
}*/


}
