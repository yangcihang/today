package com.hrsoft.today.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hrsoft.today.R;

import static com.hrsoft.today.util.ScreenUtil.sp2px;

/**
 * @author YangCihang
 * @since 17/11/7.
 * email yangcihang@hrsoft.net
 */

public class DialogUtils {

    private Context mContext;
    private TextView titleTxt = null;
    private TextView inputHintTxt = null;
    private AppCompatEditText inputEdit = null;
    private TextView hintTxt = null;
    private ProgressBar progressBar = null;
    private View customView;
    private boolean needNegativeButton = false;
    private boolean needPositiveButton = false;
    private boolean isCancelable = false;
    private LinearLayout layout;//Dialog总布局
    private LinearLayout.LayoutParams hintTextViewParams, editTextViewParams, hintInputTextViewParams, titleTextViewParams;
    private OnButtonListener onPositiveButtonListener;
    private OnButtonListener onNegativeButtonListener;

    public DialogUtils(Context context) {
        mContext = context;
        layout = new LinearLayout(mContext);
    }

    /**
     * 设置title
     */
    public DialogUtils setTitleText(String titleText) {
        titleTxt = new TextView(mContext);
        titleTxt.setText(titleText);
        titleTxt.setGravity(Gravity.START);
        titleTextViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        titleTxt.setTextColor(mContext.getResources().getColor(R.color.text_primary));
        titleTxt.setGravity(Gravity.CENTER);
        titleTxt.setTextSize(mContext.getResources().getInteger(R.integer.dialog_title_txt_size));
        return this;
    }

    public DialogUtils setInputHintText(String inputHintText) {
        inputHintTxt = new TextView(mContext);
        inputHintTxt.setText(inputHintText);
        inputHintTxt.setTextColor(mContext.getResources().getColor(R.color.text_secondary));
        inputHintTxt.setTextSize(mContext.getResources().getInteger(R.integer.dialog_input_hint_txt_size));
        inputHintTxt.setGravity(Gravity.CENTER);
        hintInputTextViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        return this;
    }

    /**
     * 设置输入框
     *
     * @param inputType 输入样式
     */
    public DialogUtils setInputEditView(int inputType) {
        inputEdit = new AppCompatEditText(mContext);
        editTextViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        inputEdit.setGravity(Gravity.CENTER);
        inputEdit.setInputType(inputType);
        editTextViewParams.setMargins(20, 0, 20, 0);
        return this;
    }

    public DialogUtils setCustomView(int resId, @NonNull OnViewListener listener) {
        customView = View.inflate(mContext, resId, null);
        listener.onViewEventSetting(customView);
        return this;
    }
//    public DialogUtils setHintText(String hintText) {
//        hintTxt = new TextView(mContext);
//        hintTxt.setText(hintText);
//        hintTxt.setTextColor(mContext.getResources().getColor(R.color.text_secondary));
//        hintTextViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        hintTxt.setTextSize(mContext.getResources().getInteger(R.integer.dialog_hint_txt_size));
//        hintTxt.setGravity(Gravity.CENTER);
//        return this;
//    }

    /**
     * 设置ProgressBar
     */
    public DialogUtils setProgressBar() {
        progressBar = new ProgressBar(mContext);
        return this;
    }


    public String getInputText() {
        if (inputEdit != null) {
            return inputEdit.getText().toString().trim();
        } else {
            return null;
        }
    }

    public DialogUtils setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
        return this;
    }

    /**
     * 设置取消键
     */
    public DialogUtils setNegativeButton(OnButtonListener onNegativeButtonListener) {
        needNegativeButton = true;
        this.onNegativeButtonListener = onNegativeButtonListener;
        return this;
    }

    /**
     * 设置确认键
     */
    public DialogUtils setPositiveButton(OnButtonListener onButtonListener) {
        needPositiveButton = true;
        this.onPositiveButtonListener = onButtonListener;
        return this;
    }

    /**
     * 展示AlertDialog
     */
    public DialogUtils showAlertDialog() {
        layout.removeAllViews();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        int marginLeft = sp2px(mContext.getResources().getInteger(R.integer.dialog_margin_abroad));
        int marginTop = sp2px(mContext.getResources().getInteger(R.integer.dialog_margin_top));
        int marginRight = sp2px(mContext.getResources().getInteger(R.integer.dialog_margin_abroad));
        layout.setPadding(marginLeft, marginTop, marginRight, 0);
        layout.setOrientation(LinearLayout.VERTICAL); //设置布局走向
        if (titleTxt != null) {
            layout.addView(titleTxt, titleTextViewParams);
        }
        if (inputHintTxt != null) {
            layout.addView(inputHintTxt, hintInputTextViewParams);
        }
        if (inputEdit != null) {
            layout.addView(inputEdit);
        }
        if (hintTxt != null) {
            layout.addView(hintTxt, hintTextViewParams);
        }
        if (customView != null) {
            layout.addView(customView);
        }
        if (progressBar != null) {
            layout.addView(progressBar);
        }

        builder.setView(layout);
        if (needPositiveButton) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (onPositiveButtonListener != null) {
                        onPositiveButtonListener.onButtonClicked(DialogUtils.this);
                    }
                }
            });
        }
        if (needNegativeButton) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (onNegativeButtonListener != null) {
                        onNegativeButtonListener.onButtonClicked(DialogUtils.this);
                    }
                }
            });
        }
        if (isCancelable) {
            builder.setCancelable(true);
        } else {
            builder.setCancelable(false);
        }
        AlertDialog dialog = builder.create();
        dialog.show();
        if (needNegativeButton) {
            Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(mContext.getResources().getColor(R.color.text_primary));
            negativeButton.setTextSize(mContext.getResources().getInteger(R.integer.dialog_button_txt_size));
        }
        if (needPositiveButton) {
            Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setTextColor(mContext.getResources().getColor(R.color.text_primary));
            positiveButton.setTextSize(mContext.getResources().getInteger(R.integer.dialog_button_txt_size));
        }
        return this;
    }


    public interface OnButtonListener {
        void onButtonClicked(DialogUtils dialogUtils);
    }

    /**
     * 自定义view点击的接口.接收view
     */
    public interface OnViewListener {
        void onViewEventSetting(View view);
    }
}
