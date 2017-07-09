package com.popfu.mydailytime.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.event.EventAddUnit;
import com.popfu.mydailytime.event.EventUpdateUnit;
import com.popfu.mydailytime.presenter.TimePresenter;
import com.popfu.mydailytime.util.DeviceUtil;
import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.util.toast.ToastUtil;
import com.popfu.mydailytime.vo.TimeUnit;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

import static android.view.View.GONE;

/**
 * Created by pengfu on 09/07/2017.
 */

@EActivity(R.layout.act_time)
public class TimeActivity extends Activity implements View.OnClickListener {


    public static final String KEY_TIME_UNIT = "key_time_unit" ;

    @ViewById(R.id.start)
    TextView mStartView ;
    @ViewById(R.id.stop)
    TextView mStopView ;
    @ViewById(R.id.resume)
    TextView mResumeView ;

    private static final int TYPE_START = 1 ;
    private static final int TYPE_STOP = 2 ;
    private static final int TYPE_RESUME = 3 ;

    private TimePresenter mPresenter ;

    private TimeUnit mTimeUnit ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TimePresenter() ;
        mTimeUnit = (TimeUnit) getIntent().getSerializableExtra(KEY_TIME_UNIT) ;
    }

    @AfterViews
    void afterViews(){

        mStartView.setOnClickListener(this);
        mStopView.setOnClickListener(this);
        mResumeView.setOnClickListener(this);
        if(mTimeUnit == null){
            showView(TYPE_START);
        }else{
            showView(TYPE_RESUME);
        }

    }

    private void showView(int type){

        switch (type){
            case TYPE_START:
                mStartView.setVisibility(View.VISIBLE);
                mStopView.setVisibility(GONE);
                mResumeView.setVisibility(GONE);
                break ;
            case TYPE_STOP:
                mStartView.setVisibility(GONE);
                mStopView.setVisibility(View.VISIBLE);
                mResumeView.setVisibility(GONE);
                break ;
            case TYPE_RESUME:
                mStartView.setVisibility(GONE);
                mStopView.setVisibility(GONE);
                mResumeView.setVisibility(View.VISIBLE);
                break ;
        }
    }

    /**
     * 是否是新创建的Unit
     */
    private boolean isNewUnit  ;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.start:
                isNewUnit = true ;
                showView(TYPE_STOP);
                // create a time unit
                mTimeUnit = new TimeUnit() ;
                mTimeUnit.setStartTime(System.currentTimeMillis());
                mPresenter.addUnit(mTimeUnit);
                break ;
            case R.id.stop:
                if(TextUtils.isEmpty(mTimeUnit.getName())){
                    // to create a name
                    final Dialog dlg = new Dialog(this) ;
                    dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    View contentView = getLayoutInflater().inflate(R.layout.dlg_input_name ,null ,false) ;
                    final EditText inputEditText = (EditText) contentView.findViewById(R.id.edit_name) ;
                    TextView titleView = (TextView) contentView.findViewById(R.id.title_view) ;
                    TextView ConfirmView = (TextView) contentView.findViewById(R.id.confirm) ;
                    int dlg_width = (int) (DeviceUtil.getScreenWidth()/1.5f);
                    int dlg_height = WindowManager.LayoutParams.WRAP_CONTENT;

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams() ;
                    lp.width = dlg_width;
                    lp.height = dlg_height ;
                    contentView.setPadding(dlg_width/6 ,DeviceUtil.dip2px(20) ,dlg_width/6 ,DeviceUtil.dip2px(20));
                    dlg.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
                    dlg.setContentView(contentView ,lp);
                    dlg.setCanceledOnTouchOutside(false);
                    dlg.setCancelable(false);
                    dlg.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String input_name = inputEditText.getText().toString() ;
                            if(TextUtils.isEmpty(input_name)){
                                ToastUtil.show("请输入标题");
                            }else{
                                mTimeUnit.setName(input_name);
                                mTimeUnit.setDuration(500);
                                mPresenter.updateUnit(mTimeUnit) ;
                                dlg.dismiss();
                                exitPage(isNewUnit) ;
                            }
                        }
                    });
                    dlg.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(inputEditText, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });
                    dlg.show();
                }else{
                    exitPage(isNewUnit) ;
                }
                break ;
            case R.id.resume:
                isNewUnit = false ;
                showView(TYPE_STOP);
                break ;
        }
    }

    private void exitPage(boolean isNewUnit){
        if(isNewUnit){
            EventBus.getDefault().post(new EventAddUnit(mTimeUnit));
        }else{
            EventBus.getDefault().post(new EventUpdateUnit(mTimeUnit));
        }
        finish();
    }
}
