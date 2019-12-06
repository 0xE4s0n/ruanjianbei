package cuit.xsgw.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.zxb.engine.emotionapp.utils.DisplayUtils;

import java.util.Calendar;

import cuit.xsgw.R;
import cuit.xsgw.pickerview.builder.TimePickerBuilder;
import cuit.xsgw.pickerview.listener.CustomListener;
import cuit.xsgw.pickerview.listener.OnTimeSelectListener;
import cuit.xsgw.pickerview.view.TimePickerView;

public class ShowTimeUtil {
    private static TimePickerView pvCustomTime = null;

    public static void showTimePicker(Context context, OnTimeSelectListener listener) {
        if (pvCustomTime != null) {
            pvCustomTime.dismiss();
            pvCustomTime = null;
        }
        pvCustomTime = new TimePickerBuilder(context, listener)
                .setDate(Calendar.getInstance())
                //.setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.plugin_datepicker_custopm_options, new CustomListener() {
                    @Override
                    public void customLayout(View v1) {
                        final TextView tvTitle = (TextView) v1.findViewById(R.id.tv_title);
                        final TextView tvSubmit = (TextView) v1.findViewById(R.id.dialog_custom_yes);
                        final TextView ivCancel = (TextView) v1.findViewById(R.id.dialog_custom_no);
                        tvTitle.setText("记录日期");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v12) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        if (AndroidWorkgroundUtil.checkDeviceHasNavigationBar(context)) {
            pvCustomTime.getDialogContainerLayout().setPadding(0, 0, 0, DisplayUtils.dp2px(context, 60));
        }
        pvCustomTime.show(false);
    }
}
