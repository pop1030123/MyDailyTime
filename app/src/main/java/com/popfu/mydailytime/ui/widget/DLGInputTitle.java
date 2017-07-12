package com.popfu.mydailytime.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.popfu.mydailytime.R;
import com.popfu.mydailytime.util.DeviceUtil;

/**
 * Created by pengfu on 12/07/2017.
 */

public class DLGInputTitle extends Dialog {
    public DLGInputTitle(@NonNull Context context) {
        super(context);
        init() ;
    }

    public DLGInputTitle(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init() ;
    }

    private EditText mInputEditText ;

    private TextView mConfirmBtn ;

    private void init(){

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View contentView = getLayoutInflater().inflate(R.layout.dlg_input_name ,null ,false) ;
        mInputEditText = (EditText) contentView.findViewById(R.id.edit_name) ;
        int dlg_width = WindowManager.LayoutParams.WRAP_CONTENT;
        int dlg_height = WindowManager.LayoutParams.WRAP_CONTENT;

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams() ;
        lp.width = dlg_width;
        lp.height = dlg_height ;
        contentView.setPadding(dlg_width/6 ,DeviceUtil.dip2px(20) ,dlg_width/6 ,DeviceUtil.dip2px(20));
        getWindow().setBackgroundDrawableResource(R.drawable.d_transparent);
        setContentView(contentView ,lp);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        mConfirmBtn = (TextView) contentView.findViewById(R.id.confirm);
    }

    public EditText getInputEditText(){
        return mInputEditText ;
    }

    public void setOnClickListener(View.OnClickListener listener){
        mConfirmBtn.setOnClickListener(listener) ;
    }
}
