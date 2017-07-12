package com.popfu.mydailytime.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.util.DeviceUtil;

/**
 * Created by pengfu on 12/07/2017.
 */

public class DLGCancelTask extends Dialog {
    public DLGCancelTask(@NonNull Context context) {
        super(context);
        init() ;
    }

    public DLGCancelTask(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init() ;
    }

    private View mConfirmView  ,mCancelView ;

    private void init(){

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View contentView = getLayoutInflater().inflate(R.layout.dlg_cancel_task ,null ,false) ;
        int dlg_width = WindowManager.LayoutParams.WRAP_CONTENT;
        int dlg_height = WindowManager.LayoutParams.WRAP_CONTENT;

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams() ;
        lp.width = dlg_width;
        lp.height = dlg_height ;
        contentView.setPadding(dlg_width/6 ,DeviceUtil.dip2px(20) ,dlg_width/6 ,DeviceUtil.dip2px(20));
        getWindow().setBackgroundDrawableResource(R.drawable.d_transparent);
        setContentView(contentView ,lp);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        mConfirmView = contentView.findViewById(R.id.confirm);
        mCancelView = contentView.findViewById(R.id.cancel);
    }

    public void setOnConfirmListener(View.OnClickListener listener){
        mConfirmView.setOnClickListener(listener);
    }

    public void setOnCancelListener(View.OnClickListener listener){
        mCancelView.setOnClickListener(listener) ;
    }
}
