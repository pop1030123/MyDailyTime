package com.popfu.mydailytime.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.vo.TimeUnit;

import java.util.List;

/**
 * Created by pengfu on 08/07/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemHolder> {


    Context mContext ;

    List<TimeUnit> mTimeUnitList;

    private Callback mCallback ;

    public MainAdapter(Context context){
        mContext = context ;
    }

    public void setTimeUnitList(List<TimeUnit> data){
        mTimeUnitList = data ;
    }

    public void setCallback(Callback callback){
        mCallback = callback ;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.main_item ,parent,false) ;
        ItemHolder holder = new ItemHolder(rootView) ;
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemClick(Integer.parseInt(v.getTag().toString()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.titleView.setText(mTimeUnitList.get(position).getName());
        holder.durationView.setText(mTimeUnitList.get(position).getDurationString());
        // TODO: 08/07/2017
        holder.durationProgress.setProgress(50);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTimeUnitList.size() ;
    }


    class ItemHolder extends RecyclerView.ViewHolder{

        TextView titleView ,durationView ;
        ProgressBar durationProgress ;

        public ItemHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.name) ;
            durationView = (TextView) itemView.findViewById(R.id.duration) ;
            durationProgress = (ProgressBar) itemView.findViewById(R.id.duration_progress) ;
        }
    }

    public interface Callback{
        void onItemClick(int position) ;
    }
}
