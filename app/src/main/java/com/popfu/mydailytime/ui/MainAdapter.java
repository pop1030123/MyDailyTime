package com.popfu.mydailytime.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.vo.TimeUnit;

import java.util.List;

/**
 * Created by pengfu on 08/07/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemHolder> {


    Context mContext;

    List<TimeUnit> mTimeUnitList;

    private Callback mCallback;

    public MainAdapter(Context context) {
        mContext = context;
    }

    public void setTimeUnitList(List<TimeUnit> data) {
        mTimeUnitList = data;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false);
        ItemHolder holder = new ItemHolder(rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemClick(((TimeUnit)v.getTag()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        TimeUnit thisUnit = mTimeUnitList.get(position) ;
        holder.titleView.setText(thisUnit.getName());
        holder.durationView.setText(thisUnit.getDurationString());
        // TODO: 08/07/2017
        holder.durationProgress.setProgress(50);
        holder.itemView.setTag(thisUnit);
    }

    @Override
    public int getItemCount() {
        return mTimeUnitList.size();
    }

    public TimeUnit getItem(int position) {
        L.d("getItem:"+position);
        return mTimeUnitList.get(position);
    }

    public void addItem(TimeUnit unit) {
        mTimeUnitList.add(getItemCount(), unit);
        notifyItemInserted(getItemCount() - 1);
    }

    public void updateItem(TimeUnit updateUnit) {
        int index = getIndex(updateUnit);
        if (index != -1) {
            mTimeUnitList.remove(index);
            mTimeUnitList.add(index, updateUnit);
            notifyItemChanged(index);
        }
    }

    public void deleteItem(TimeUnit deleteUnit) {
        int index = getIndex(deleteUnit);
        L.d("deleteItem:index:"+index+"::"+deleteUnit);
        if (index != -1) {
            mTimeUnitList.remove(index);
            notifyItemRemoved(index);
        }
    }

    private int getIndex(TimeUnit unit) {
        TimeUnit tempUnit = null;
        for (TimeUnit unit_temp : mTimeUnitList) {
            if (unit_temp.getId() == unit.getId()) {
                tempUnit = unit_temp;
                break;
            }
        }
        if (tempUnit != null) {
            return mTimeUnitList.indexOf(tempUnit);
        } else {
            return -1;
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView titleView, durationView;
        ProgressBar durationProgress;

        public ItemHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.name);
            durationView = (TextView) itemView.findViewById(R.id.duration);
            durationProgress = (ProgressBar) itemView.findViewById(R.id.duration_progress);
        }
    }

    public interface Callback {
        void onItemClick(TimeUnit unit);
    }
}
