package com.popfu.mydailytime.ui;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.util.TimeUtil;
import com.popfu.mydailytime.vo.TimeUnit;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.popfu.mydailytime.util.TimeUtil.FORMAT_yyyyMMdd;

/**
 * Created by pengfu on 08/07/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemHolder> {


    Context mContext;

    List<TimeUnit> mTimeUnitList;

    private Callback mCallback;

    private long mMaxMillis ;

    public MainAdapter(Context context) {
        mContext = context;
    }

    public void setTimeUnitList(List<TimeUnit> data) {
        mTimeUnitList = data;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void setMaxMillis(long millis){
        this.mMaxMillis = millis ;
    }

    public void resortDataSet(){
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_yyyyMMdd);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String lastDateStr = null ;
        for(TimeUnit unit : mTimeUnitList){
            String unitDate = formatter.format(unit.getStartTime()) ;
            L.d("unitDate:"+unitDate);
            if(unitDate.equals(lastDateStr)){
                unit.setShowDateTime(false);
            }else{
                unit.setShowDateTime(true);
                lastDateStr = unitDate ;
            }
        }
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
        if(thisUnit.isShowDateTime()){
            holder.dateTimeView.setVisibility(VISIBLE);
            holder.dateTimeView.setText(TimeUtil.getDateString(thisUnit.getStartTime()));
        }else{
            holder.dateTimeView.setVisibility(GONE);
        }
        holder.titleView.setText(thisUnit.getName());
        holder.durationView.setText(TimeUtil.getDuration(thisUnit.getDuration()));
        int progress = 0 ;
        if(mMaxMillis > 0){
            progress = (int)(100 * (thisUnit.getDuration() / (mMaxMillis*1f))) ;
        }
        holder.durationProgress.setProgress(progress);
//        LayerDrawable drawable = (LayerDrawable)holder.durationProgress.getProgressDrawable() ;
//        L.d("drawable:num:"+drawable.getNumberOfLayers());
//        ClipDrawable clipDrawable =(ClipDrawable) drawable.findDrawableByLayerId(android.R.id.progress) ;
//        L.d("drawable:d1:"+clipDrawable.getCurrent());
//        L.d("drawable:d:"+clipDrawable.getConstantState());
        holder.itemView.setTag(thisUnit);
    }

    @Override
    public int getItemCount() {
        return mTimeUnitList.size();
    }

    public void addItem(TimeUnit unit) {
        mTimeUnitList.add(0, unit);
//        notifyItemInserted(getItemCount() - 1);
    }

    public void updateItem(TimeUnit updateUnit) {
        int index = getIndex(updateUnit);
        if (index != -1) {
            mTimeUnitList.remove(index);
            mTimeUnitList.add(index, updateUnit);
//            notifyItemChanged(index);
        }
    }

    public void deleteItem(TimeUnit deleteUnit) {
        int index = getIndex(deleteUnit);
        L.d("deleteItem:index:"+index+"::"+deleteUnit);
        if (index != -1) {
            mTimeUnitList.remove(index);
//            notifyItemRemoved(index);
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

        TextView titleView, durationView ,dateTimeView ;
        ProgressBar durationProgress;

        public ItemHolder(View itemView) {
            super(itemView);
            dateTimeView = (TextView)  itemView.findViewById(R.id.datetime_view) ;
            titleView = (TextView) itemView.findViewById(R.id.name);
            durationView = (TextView) itemView.findViewById(R.id.duration);
            durationProgress = (ProgressBar) itemView.findViewById(R.id.duration_progress);
        }
    }

    public interface Callback {
        void onItemClick(TimeUnit unit);
    }
}
