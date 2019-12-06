package cuit.xsgw;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.net.image.AppImageLoader;
import com.android.zxb.engine.util.BigDecimalUtil;
import com.android.zxb.engine.util.MoneyTextWatcher;
import com.android.zxb.engine.util.ToastUtils;
import com.android.zxb.engine.util.date.DateStyle;
import com.android.zxb.engine.util.date.DateUtils;
import com.android.zxb.engine.view.RadioPopupWindow;
import com.baidu.ocr.sdk.model.IDCardResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cuit.xsgw.adapter.CheckBoxOneOtherTextAdapter;
import cuit.xsgw.adapter.CheckBoxOtherTextAdapter;
import cuit.xsgw.adapter.CheckBoxTextAdapter;
import cuit.xsgw.adapter.DiseaseHistoryAdapter;
import cuit.xsgw.adapter.MoreEditAdapter;
import cuit.xsgw.adapter.MoreRadioAdapter;
import cuit.xsgw.adapter.NewItemAdapter;
import cuit.xsgw.adapter.RadioAdapter;
import cuit.xsgw.bean.BaseForm;
import cuit.xsgw.bean.CheckboxForm;
import cuit.xsgw.bean.ChooseAreaForm;
import cuit.xsgw.bean.ChoosePictureForm;
import cuit.xsgw.bean.ChooseStringForm;
import cuit.xsgw.bean.DateForm;
import cuit.xsgw.bean.DiseaseHistoryForm;
import cuit.xsgw.bean.EditInputType;
import cuit.xsgw.bean.FormType;
import cuit.xsgw.bean.MoreEditBean;
import cuit.xsgw.bean.MoreEditForm;
import cuit.xsgw.bean.MoreRadioForm;
import cuit.xsgw.bean.NewItemForm;
import cuit.xsgw.bean.RadioForm;
import cuit.xsgw.bean.RadioStringBean;
import cuit.xsgw.bean.SingleEditForm;
import cuit.xsgw.net.req.Medical;
import cuit.xsgw.pickerview.listener.OnTimeSelectListener;
import cuit.xsgw.utils.ShowTimeUtil;
import cuit.xsgw.views.ChooseAreaPopupWindow;

import static cuit.xsgw.config.GongWeiConfig.minZuData;


public class RecordFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BaseForm> mDatas;
    private RadioPopupWindow mRadioPopupW;
    private ChooseAreaPopupWindow mChooseAreaPopupW;

    public RecordFormAdapter(Context context, List<BaseForm> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    private onClickLisitener mClickLisitener;

    public interface onClickLisitener {
        void doSaoMiao(onCallback callback);

        void doGetPic(int position);
    }

    public void setDoClickLisitener(onClickLisitener lisitener) {
        this.mClickLisitener = lisitener;
    }

    public interface onCallback {
        void doSaoMiaoResult(IDCardResult result);
    }

    /**
     * 清空地区列表数据
     */
    public void clearData() {
        if (mChooseAreaPopupW != null) {
            mChooseAreaPopupW.clearData();
        }
    }

    @Override
    public int getItemViewType(int position) {
        final String uiType = mDatas.get(position).getType();
        if (uiType.equals(FormType.TYPE_DATE)) {
            return 0;
        } else if (uiType.equals(FormType.TYPE_CHECKBOX_ONE_OTHER)) {
            return 1;
        } else if (uiType.equals(FormType.TYPE_MORE_EDIT)) {
            return 2;
        } else if (uiType.equals(FormType.TYPE_RADIO)) {
            return 3;
        } else if (uiType.equals(FormType.TYPE_SINGLE_EDIT)) {
            return 4;
        } else if (uiType.equals(FormType.TYPE_SINGLE_PORT_EDIT)) {
            return 5;
        } else if (uiType.equals(FormType.TYPE_MORE_RADIO)) {
            return 6;
        } else if (uiType.equals(FormType.TYPE_NEW_ITEM)) {
            return 7;
        } else if (uiType.equals(FormType.TYPE_CHOOSE_STRING)) {
            return 8;
        } else if (uiType.equals(FormType.TYPE_CHECKBOX)) {
            return 9;
        } else if (uiType.equals(FormType.TYPE_TAB)) {
            return 10;
        } else if (uiType.equals(FormType.TYPE_PIC)) {
            return 11;
        } else if (uiType.equals(FormType.TYPE_CHOOSE_AREA)) {
            return 12;
        } else if (uiType.equals(FormType.TYPE_CHECKBOX_OTHER)) {
            return 13;
        } else if (uiType.equals(FormType.TYPE_CHECKBOX_DISEASE_HISTORY)) {
            return 14;
        } else if (uiType.equals(FormType.TYPE_ZBAR)) {
            return 15;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab, parent, false);
        switch (viewType) {
            case 0: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_string_edit, parent, false);
                return new DatePickerViewHolder(view);//uiType = TYPE_DATE
            }
            case 1: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_checkbox_edit, parent, false);
                return new CheckBoxOneOtherViewHolder(view);//uiType = TYPE_CHECKBOX_ONE_OTHER
            }
            case 2: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_more_edit, parent, false);
                return new MoreEditViewHolder(view);//uiType = TYPE_MORE_EDIT
            }
            case 3: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_radio_edit, parent, false);
                return new RadioViewHolder(view);//uiType = TYPE_RADIO
            }
            case 4: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_single_edit, parent, false);
                return new SingleEditViewHolder(view);//uiType = TYPE_SINGLE_EDIT
            }
            case 5: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_single_port_edit, parent, false);
                return new SinglePortEditViewHolder(view);//uiType = TYPE_SINGLE_PORT_EDIT
            }
            case 6: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_more_radio_edit, parent, false);
                return new MoreRadioViewHolder(view);//uiType = TYPE_MORE_RADIO
            }
            case 7: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_new_item_edit, parent, false);
                return new NewItemViewHolder(view);//uiType = TYPE_NEW_ITEM
            }
            case 8: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_string_edit, parent, false);
                return new ChooseStringViewHolder(view);//uiType = TYPE_CHOOSE_STRING
            }
            case 9: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_checkbox_edit, parent, false);
                return new CheckBoxViewHolder(view);//uiType = TYPE_CHECKBOX
            }
            case 10: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_tab, parent, false);
                return new TabViewHolder(view);//uiType = TYPE_TAB
            }
            case 11: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_picture, parent, false);
                return new ChoosePictureViewHolder(view);//uiType = TYPE_PIC
            }
            case 12: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_string_edit, parent, false);
                return new ChooseAreaViewHolder(view);//uiType = TYPE_CHOOSE_AREA
            }
            case 13: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_checkbox_edit, parent, false);
                return new CheckBoxOtherViewHolder(view);//uiType = TYPE_CHECKBOX_OTHER
            }
            case 14: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_disease_history_edit, parent, false);
                return new DiseaseHistoryViewHolder(view);//uiType = TYPE_CHECKBOX_DISEASE_HISTORY
            }
            case 15: {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_single_edit, parent, false);
                return new SingleEditViewHolder(view);//uiType = TYPE_ZBAR
            }
            default:
                ;
        }

        return new TabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof TabViewHolder) {
            TabViewHolder holder = (TabViewHolder) viewHolder;
            BaseForm data = mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
        } else if (viewHolder instanceof DatePickerViewHolder) {
            final DatePickerViewHolder holder = (DatePickerViewHolder) viewHolder;
            final DateForm data = (DateForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            if (data.getCurDate() != 0) {
                holder.itemValue.setText(DateUtils.formatSecondTimestamp(data.getCurDate(), DateStyle.YYYY_MM_DD));
            } else {
                holder.itemValue.setText(null);
            }
            holder.itemValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowTimeUtil.showTimePicker(mContext, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            holder.itemValue.setText(DateUtils.getDateFormatYYYYMMDD(date));
                            data.setCurDate(date.getTime() / 1000);
                            if (data.getId() == FormType.ResidentFormID.RESIDENT_ID_BASE_BIRTHDAY) {
                                long age = (DateUtils.systemCurrentDateTime() - date.getTime() / 1000) / 31556926;
                                SingleEditForm ageForm = (SingleEditForm) mDatas.get(position + 1);
                                ageForm.setCurValue(String.valueOf(age));
                                notifyItemChanged(position + 1);
                            }
                        }
                    });
                }
            });
        } else if (viewHolder instanceof ChooseStringViewHolder) {
            final ChooseStringViewHolder holder = (ChooseStringViewHolder) viewHolder;
            final ChooseStringForm data = (ChooseStringForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemValue.setText(data.getCurValue());

            holder.itemValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDatas.get(position).getId() == FormType.ResidentFormID.RESIDENT_ID_BASE_NATION) {
                        if (mRadioPopupW != null) {
                            mRadioPopupW.dismiss();
                            mRadioPopupW = null;
                        }
                        mRadioPopupW = new RadioPopupWindow((Activity) mContext, minZuData);
                        mRadioPopupW.setAnimationStyle(R.style.dialog_up_down_anim);
                        mRadioPopupW.setOnItemClickListener(new RadioPopupWindow.OnItemClickListener() {
                            @Override
                            public void onItemClick(int index) {
                                data.setCurValue(minZuData[index]);
                                holder.itemValue.setText(minZuData[index]);
                            }
                        });
                        mRadioPopupW.showAtLocation(holder.itemValue, Gravity.BOTTOM, 0, 0);
                    }
                }
            });
        } else if (viewHolder instanceof CheckBoxOneOtherViewHolder) {
            final CheckBoxOneOtherViewHolder holder = (CheckBoxOneOtherViewHolder) viewHolder;
            final CheckboxForm data = (CheckboxForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            if (data.getTitle() != null && data.getTitle().length() > 0) {
                holder.itemTitle.setVisibility(View.VISIBLE);
            } else {
                holder.itemTitle.setVisibility(View.GONE);
            }
            holder.itemList.setLayoutManager(new GridLayoutManager(mContext, data.getSpancount()));
            holder.itemAdapter = new CheckBoxOneOtherTextAdapter(mContext, data.getCheckData());
            holder.itemList.setAdapter(holder.itemAdapter);
            holder.itemAdapter.setDoClickLisitener(new CheckBoxOneOtherTextAdapter.onClickLisitener() {
                @Override
                public void doClick(int position1) {
                    if (position1 == data.getCheckData().size() - 1) {
                        if (data.getCheckData().get(position1).isSelected()) {
                            holder.itemOther.setVisibility(View.VISIBLE);
                        } else {
                            holder.itemOther.setVisibility(View.GONE);
                            holder.itemOther.setText(null);
                        }
                    } else if (position1 == 0) {
                        if (data.getCheckData().get(position1).isSelected()) {
                            holder.itemOther.setVisibility(View.GONE);
                            holder.itemOther.setText(null);
                        }
                    }
                }
            });
            holder.itemOther.removeTextChangedListener((MoneyTextWatcher) holder.itemOther.getTag());
            holder.itemOther.setText(data.getOtherValue());
            MoneyTextWatcher watcher = new MoneyTextWatcher(holder.itemOther) {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().trim().length() < 1) {
                        data.setOtherValue(null);
                    } else {
                        data.setOtherValue(s.toString());
                    }
                }
            };
            holder.itemOther.addTextChangedListener(watcher);
            holder.itemOther.setTag(watcher);
        } else if (viewHolder instanceof CheckBoxOtherViewHolder) {
            final CheckBoxOtherViewHolder holder = (CheckBoxOtherViewHolder) viewHolder;
            final CheckboxForm data = (CheckboxForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            if (data.getTitle() != null && data.getTitle().length() > 0) {
                holder.itemTitle.setVisibility(View.VISIBLE);
            } else {
                holder.itemTitle.setVisibility(View.GONE);
            }
            holder.itemList.setLayoutManager(new GridLayoutManager(mContext, data.getSpancount()));
            holder.itemAdapter = new CheckBoxOtherTextAdapter(mContext, data.getCheckData());
            holder.itemList.setAdapter(holder.itemAdapter);
            holder.itemAdapter.setDoClickLisitener(new CheckBoxOtherTextAdapter.onClickLisitener() {
                @Override
                public void doClick(int position1) {
                    if (position1 == data.getCheckData().size() - 1) {
                        if (data.getCheckData().get(position1).isSelected()) {
                            holder.itemOther.setVisibility(View.VISIBLE);
                        } else {
                            holder.itemOther.setVisibility(View.GONE);
                            holder.itemOther.setText(null);
                        }
                    }
                }
            });
            holder.itemOther.removeTextChangedListener((MoneyTextWatcher) holder.itemOther.getTag());
            holder.itemOther.setText(data.getOtherValue());
            MoneyTextWatcher watcher = new MoneyTextWatcher(holder.itemOther) {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().trim().length() < 1) {
                        data.setOtherValue(null);
                    } else {
                        data.setOtherValue(s.toString());
                    }
                }
            };
            holder.itemOther.addTextChangedListener(watcher);
            holder.itemOther.setTag(watcher);
        } else if (viewHolder instanceof CheckBoxViewHolder) {
            CheckBoxViewHolder holder = (CheckBoxViewHolder) viewHolder;
            CheckboxForm data = (CheckboxForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            if (data.getTitle() != null && data.getTitle().length() > 0) {
                holder.itemTitle.setVisibility(View.VISIBLE);
            } else {
                holder.itemTitle.setVisibility(View.GONE);
            }
            holder.itemList.setLayoutManager(new GridLayoutManager(mContext, data.getSpancount()));
            holder.itemAdapter = new CheckBoxTextAdapter(mContext, data.getCheckData());
            holder.itemList.setAdapter(holder.itemAdapter);
        } else if (viewHolder instanceof NewItemViewHolder) {
            final NewItemViewHolder holder = (NewItemViewHolder) viewHolder;
            final NewItemForm<Medical> data = (NewItemForm<Medical>) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.mNewItemAdapter = new NewItemAdapter(mContext, data.getList());
            holder.itemList.setAdapter(holder.mNewItemAdapter);
            holder.itemAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.getList().size() < 11) {
                        Medical medical = new Medical();
                        medical.setRate(3);//默认每天三次
                        data.getList().add(medical);
                        holder.mNewItemAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showToast(mContext, "最多添加10种");
                    }
                }
            });
        } else if (viewHolder instanceof MoreEditViewHolder) {
            MoreEditViewHolder holder = (MoreEditViewHolder) viewHolder;
            final MoreEditForm data = (MoreEditForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.mMoreAdapter = new MoreEditAdapter(mContext, data.getMoreEditData());
            holder.itemList.setAdapter(holder.mMoreAdapter);
            holder.mMoreAdapter.setDoComputeLisitener(new MoreEditAdapter.onComputeLisitener() {
                @Override
                public void doCompute(int index) {
                    if (data.getId() == FormType.FormID.BASE_ID_RECORE_WEIGHT) {
                        RecordFormAdapter.this.computeBIM(position - 1);
                    }
                }
            });
        } else if (viewHolder instanceof RadioViewHolder) {
            final RadioViewHolder holder = (RadioViewHolder) viewHolder;
            final RadioForm data = (RadioForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemList.setLayoutManager(new GridLayoutManager(mContext, data.getSpancount()));
            holder.mRadioAdapter = new RadioAdapter(mContext, data.getRadioData());
            holder.mRadioAdapter.setDoClickLisitener(new RadioAdapter.onClickLisitener() {
                @Override
                public void doShow(int index, boolean show) {
                    holder.itemDetail.setText(data.getRadioData().get(index).getDetail());
                    holder.itemDetail.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
            holder.itemList.setAdapter(holder.mRadioAdapter);
            holder.itemDetail.removeTextChangedListener((MoneyTextWatcher) holder.itemDetail.getTag());
            MoneyTextWatcher watcher = new MoneyTextWatcher(holder.itemDetail) {
                @Override
                public void afterTextChanged(Editable s) {
                    for (RadioStringBean bean : data.getRadioData()) {
                        if (bean.isSelected()) {
                            bean.setDetail(s.toString());
                            break;
                        }
                    }
                }
            };
            holder.itemDetail.addTextChangedListener(watcher);
            holder.itemDetail.setTag(watcher);
        } else if (viewHolder instanceof MoreRadioViewHolder) {
            MoreRadioViewHolder holder = (MoreRadioViewHolder) viewHolder;
            MoreRadioForm data = (MoreRadioForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemList.setLayoutManager(new LinearLayoutManager(mContext));
            holder.mMoreRadioAdapter = new MoreRadioAdapter(mContext, data.getMoreRadioData());
            holder.itemList.setAdapter(holder.mMoreRadioAdapter);
        } else if (viewHolder instanceof SingleEditViewHolder) {
            SingleEditViewHolder holder = (SingleEditViewHolder) viewHolder;
            final SingleEditForm data = (SingleEditForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemUnit.setText(data.getUnitData());
            if (data.getUnitData() == null || data.getUnitData().trim().length() == 0) {
                holder.itemUnit.setVisibility(View.GONE);
            } else {
                holder.itemUnit.setVisibility(View.VISIBLE);
            }
            if (data.getType() != null && data.getType().equals(FormType.TYPE_ZBAR)) {
                holder.itemSaoMiao.setVisibility(View.VISIBLE);
            } else {
                holder.itemSaoMiao.setVisibility(View.GONE);
            }
            holder.itemValue.removeTextChangedListener((MoneyTextWatcher) holder.itemValue.getTag());
            holder.itemValue.setText(data.getCurValue());
            setInputType(holder.itemValue, data.getInputType());
            MoneyTextWatcher watcher = new MoneyTextWatcher(holder.itemValue) {
                @Override
                public void afterTextChanged(Editable s) {
                    data.setCurValue(s.toString());
                    if (s != null) {
                        if (data.getId() == FormType.FormID.BASE_ID_RECORE_HEIGHT) {
                            computeBIM(position);
                        } else if (data.getId() == FormType.ResidentFormID.RESIDENT_ID_BASE_IDCARD && s.length() == 18) {
                            //身份证
                            computeInfo(position);
                        }
                    }
                }
            };
            holder.itemValue.addTextChangedListener(watcher);
            holder.itemValue.setTag(watcher);

            holder.itemSaoMiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (mClickLisitener != null) mClickLisitener.doSaoMiao(new onCallback() {
//                        @Override
//                        public void doSaoMiaoResult(IDCardResult result) {
//                            holder.itemValue.setText(result.getIdNumber().toString());
//
//                            SingleEditForm nameForm = (SingleEditForm) mDatas.get(3);
//                            nameForm.setCurValue(result.getName().toString());
//                            notifyItemChanged(3);
//
//                            SingleEditForm areaForm = (SingleEditForm) mDatas.get(12);
//                            areaForm.setCurValue(result.getAddress().toString());
//                            notifyItemChanged(12);
//                        }
//                    });
                }
            });
        } else if (viewHolder instanceof SinglePortEditViewHolder) {
            SinglePortEditViewHolder holder = (SinglePortEditViewHolder) viewHolder;
            final SingleEditForm data = (SingleEditForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemValue.removeTextChangedListener((MoneyTextWatcher) holder.itemValue.getTag());
            holder.itemValue.setText(data.getCurValue());
            setInputType(holder.itemValue, data.getInputType());
            MoneyTextWatcher watcher = new MoneyTextWatcher(holder.itemValue) {
                @Override
                public void afterTextChanged(Editable s) {
                    data.setCurValue(s.toString());
                }
            };
            holder.itemValue.addTextChangedListener(watcher);
            holder.itemValue.setTag(watcher);
        } else if (viewHolder instanceof ChoosePictureViewHolder) {
            ChoosePictureViewHolder holder = (ChoosePictureViewHolder) viewHolder;
            ChoosePictureForm data = (ChoosePictureForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            AppImageLoader.displayImageWithOptions(holder.itemValue, data.getCurPath(), 1);
            holder.itemValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickLisitener != null) mClickLisitener.doGetPic(position);
                }
            });
        } else if (viewHolder instanceof ChooseAreaViewHolder) {
            final ChooseAreaViewHolder holder = (ChooseAreaViewHolder) viewHolder;
            final ChooseAreaForm data = (ChooseAreaForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemValue.setText(data.getCurAreaName());

            holder.itemValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecordFormAdapter.this.showChooseAreaPopupWindow(holder.itemValue, data);
                }
            });
        } else if (viewHolder instanceof DiseaseHistoryViewHolder) {
            DiseaseHistoryViewHolder holder = (DiseaseHistoryViewHolder) viewHolder;
            DiseaseHistoryForm data = (DiseaseHistoryForm) mDatas.get(position);
            holder.itemTitle.setText(data.getTitle());
            holder.itemAdapter = new DiseaseHistoryAdapter(mContext, data.getCheckData());
            holder.itemList.setAdapter(holder.itemAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class TabViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;

        public TabViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_tab_title);
        }
    }

    class DatePickerViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemValue;

        public DatePickerViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_data_title);
            itemValue = itemView.findViewById(R.id.item_data_value);
        }
    }

    class ChooseStringViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemValue;

        public ChooseStringViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_data_title);
            itemValue = itemView.findViewById(R.id.item_data_value);
        }
    }

    class CheckBoxOneOtherViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        CheckBoxOneOtherTextAdapter itemAdapter;
        EditText itemOther;

        public CheckBoxOneOtherViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_checkbox_title);
            itemList = itemView.findViewById(R.id.item_checkbox_group);
            itemOther = itemView.findViewById(R.id.item_checkbox_other);
        }
    }

    class CheckBoxOtherViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        CheckBoxOtherTextAdapter itemAdapter;
        EditText itemOther;

        public CheckBoxOtherViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_checkbox_title);
            itemList = itemView.findViewById(R.id.item_checkbox_group);
            itemOther = itemView.findViewById(R.id.item_checkbox_other);
        }
    }

    class CheckBoxViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        CheckBoxTextAdapter itemAdapter;
        EditText itemOther;

        public CheckBoxViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_checkbox_title);
            itemList = itemView.findViewById(R.id.item_checkbox_group);
            itemOther = itemView.findViewById(R.id.item_checkbox_other);
        }
    }

    class MoreEditViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        MoreEditAdapter mMoreAdapter;

        public MoreEditViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_more_title);
            itemList = itemView.findViewById(R.id.item_more_list);
            itemList.setLayoutManager(new LinearLayoutManager(mContext));
        }
    }

    class RadioViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        RadioAdapter mRadioAdapter;
        EditText itemDetail;

        public RadioViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_radio_title);
            itemList = itemView.findViewById(R.id.item_radio_list);
            itemDetail = itemView.findViewById(R.id.item_radio_detail);
        }
    }

    class NewItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        TextView itemAdd;
        NewItemAdapter mNewItemAdapter;

        public NewItemViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_new_item_title);
            itemList = itemView.findViewById(R.id.item_new_item_list);
            itemList.setLayoutManager(new LinearLayoutManager(mContext));
            itemAdd = itemView.findViewById(R.id.item_new_item_add);
        }
    }

    class MoreRadioViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        MoreRadioAdapter mMoreRadioAdapter;

        public MoreRadioViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_more_radio_title);
            itemList = itemView.findViewById(R.id.item_more_radio_list);
        }
    }

    class SingleEditViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemUnit;
        EditText itemValue;
        ImageView itemSaoMiao;

        public SingleEditViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_single_title);
            itemUnit = itemView.findViewById(R.id.item_single_unitdata);
            itemValue = itemView.findViewById(R.id.item_single_value);
            itemSaoMiao = itemView.findViewById(R.id.item_single_saomiao);
        }
    }

    class SinglePortEditViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        EditText itemValue;

        public SinglePortEditViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_single_port_title);
            itemValue = itemView.findViewById(R.id.item_single_port_value);
        }
    }

    class ChoosePictureViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ImageView itemValue;

        public ChoosePictureViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_choose_pic_title);
            itemValue = itemView.findViewById(R.id.item_choose_pic_value);
        }
    }

    class ChooseAreaViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemValue;

        public ChooseAreaViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_data_title);
            itemValue = itemView.findViewById(R.id.item_data_value);
        }
    }

    class DiseaseHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        RecyclerView itemList;
        DiseaseHistoryAdapter itemAdapter;

        public DiseaseHistoryViewHolder(View view) {
            super(view);
            itemTitle = itemView.findViewById(R.id.item_disease_history_title);
            itemList = itemView.findViewById(R.id.item_disease_history_group);
            itemList.setLayoutManager(new LinearLayoutManager(mContext));
        }
    }

    //限制输入类型
    private void setInputType(EditText view, int type) {
        if (type == EditInputType.TYPE_NUMBER_DECIMAL) {
            view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else if (type == EditInputType.TYPE_JUST_NUMBER) {
            view.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (type == EditInputType.TYPE_NULL) {
            view.setInputType(InputType.TYPE_NULL);
        } else {
            view.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    //计算体质指数--以身高的position为基准
    private void computeBIM(int hightPosition) {
        SingleEditForm dataHight = (SingleEditForm) mDatas.get(hightPosition);
        if (dataHight.getCurValue() != null && dataHight.getCurValue().trim().length() > 0) {
            MoreEditForm dataWeight = (MoreEditForm) mDatas.get(hightPosition + 1);
            if (dataWeight.getMoreEditData() != null) {
                if (dataWeight.getMoreEditData().get(0).getCurValue() != null &&
                        dataWeight.getMoreEditData().get(0).getCurValue().trim().length() > 0) {
                    float h = BigDecimalUtil.div(Float.parseFloat(dataHight.getCurValue()), 100).floatValue();
                    float w1 = Float.parseFloat(dataWeight.getMoreEditData().get(0).getCurValue());
                    MoreEditForm mBIM = (MoreEditForm) mDatas.get(hightPosition + 2);
                    if (mBIM.getMoreEditData() == null) {
                        mBIM.setMoreEditData(new ArrayList<MoreEditBean>());
                    }
                    mBIM.getMoreEditData().get(0).setCurValue("" + BigDecimalUtil.div(w1, BigDecimalUtil.mul(h, h).doubleValue()).floatValue());
                    notifyItemChanged(hightPosition + 2);
                } else {
                    MoreEditForm mBIM = (MoreEditForm) mDatas.get(hightPosition + 2);
                    if (mBIM.getMoreEditData() == null) {
                        mBIM.setMoreEditData(new ArrayList<MoreEditBean>());
                    }
                    mBIM.getMoreEditData().get(0).setCurValue(null);
                    notifyItemChanged(hightPosition + 2);
                }
                if (dataWeight.getMoreEditData().get(1).getCurValue() != null &&
                        dataWeight.getMoreEditData().get(1).getCurValue().trim().length() > 0) {
                    float h = BigDecimalUtil.div(Float.parseFloat(dataHight.getCurValue()), 100).floatValue();
                    float w2 = Float.parseFloat(dataWeight.getMoreEditData().get(1).getCurValue());
                    MoreEditForm mBIM = (MoreEditForm) mDatas.get(hightPosition + 2);
                    if (mBIM.getMoreEditData() == null) {
                        mBIM.setMoreEditData(new ArrayList<MoreEditBean>());
                    }
                    mBIM.getMoreEditData().get(1).setCurValue("" + BigDecimalUtil.div(w2, BigDecimalUtil.mul(h, h).doubleValue()).floatValue());
                    notifyItemChanged(hightPosition + 2);
                } else {
                    MoreEditForm mBIM = (MoreEditForm) mDatas.get(hightPosition + 2);
                    if (mBIM.getMoreEditData() == null) {
                        mBIM.setMoreEditData(new ArrayList<MoreEditBean>());
                    }
                    mBIM.getMoreEditData().get(1).setCurValue(null);
                    notifyItemChanged(hightPosition + 2);
                }
            } else {
                MoreEditForm mBIM = (MoreEditForm) mDatas.get(hightPosition + 2);
                if (mBIM.getMoreEditData() == null) {
                    mBIM.setMoreEditData(new ArrayList<MoreEditBean>());
                }
                mBIM.getMoreEditData().get(0).setCurValue(null);
                mBIM.getMoreEditData().get(1).setCurValue(null);
                notifyItemChanged(hightPosition + 2);
            }
        } else {
            MoreEditForm mBIM = (MoreEditForm) mDatas.get(hightPosition + 2);
            if (mBIM.getMoreEditData() == null) {
                mBIM.setMoreEditData(new ArrayList<MoreEditBean>());
            }
            mBIM.getMoreEditData().get(0).setCurValue(null);
            mBIM.getMoreEditData().get(1).setCurValue(null);
            notifyItemChanged(hightPosition + 2);
        }
    }

    /**
     * 计算 性别 出生日期 年龄
     *
     * @param idcardPosition
     */
    private void computeInfo(int idcardPosition) {
        SingleEditForm dataIdcard = (SingleEditForm) mDatas.get(idcardPosition);
        String idcardStr = dataIdcard.getCurValue();
        if (idcardStr != null && idcardStr.trim().length() > 0) {
            int sexIndex = Integer.parseInt(idcardStr.substring(16, 17));
            RadioForm sexForm = (RadioForm) mDatas.get(idcardPosition + 3);
            if (sexForm.getRadioData() != null) {
                sexForm.getRadioData().get(sexIndex % 2 == 0 ? 0 : 1).setSelected(false);
                sexForm.getRadioData().get(sexIndex % 2 != 0 ? 0 : 1).setSelected(true);
            }
            int year = Integer.parseInt(idcardStr.substring(6, 10));
            int month = Integer.parseInt(idcardStr.substring(10, 12));
            int day = Integer.parseInt(idcardStr.substring(12, 14));
            Calendar calendar = DateUtils.getCalendar();
            calendar.set(year, month - 1, day);
            DateForm brithdayForm = (DateForm) mDatas.get(idcardPosition + 4);
            brithdayForm.setCurDate(calendar.getTimeInMillis() / 1000);
            int age = DateUtils.getYear() - year;
            SingleEditForm ageForm = (SingleEditForm) mDatas.get(idcardPosition + 5);
            ageForm.setCurValue(String.valueOf(age));
            notifyItemRangeChanged(idcardPosition + 3, 3);
        }
    }

    private void showChooseAreaPopupWindow(final TextView valueTV, final ChooseAreaForm data) {
        if (mChooseAreaPopupW == null) {
            mChooseAreaPopupW = new ChooseAreaPopupWindow((Activity) mContext);
            mChooseAreaPopupW.setOnSetValueListener(new ChooseAreaPopupWindow.OnSetValueListener() {
                @Override
                public void onSetValue(String name, String code) {
                    valueTV.setText(name);
                    data.setCurAreaName(name);
                    data.setCurAreaCode(code);
                }
            });
        }
        mChooseAreaPopupW.setAnimationStyle(R.style.dialog_up_down_anim);
        mChooseAreaPopupW.showAtLocation(valueTV, Gravity.BOTTOM, 0, 0);
    }
}