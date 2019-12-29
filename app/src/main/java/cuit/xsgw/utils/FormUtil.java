package cuit.xsgw.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cuit.xsgw.bean.BaseForm;
import cuit.xsgw.bean.CheckBoxBean;
import cuit.xsgw.bean.CheckboxForm;
import cuit.xsgw.bean.ChooseAreaForm;
import cuit.xsgw.bean.ChoosePictureForm;
import cuit.xsgw.bean.ChooseStringForm;
import cuit.xsgw.bean.DateForm;
import cuit.xsgw.bean.DiseaseHistoryBean;
import cuit.xsgw.bean.DiseaseHistoryForm;
import cuit.xsgw.bean.EditInputType;
import cuit.xsgw.bean.FormType;
import cuit.xsgw.bean.MoreEditBean;
import cuit.xsgw.bean.MoreEditForm;
import cuit.xsgw.bean.MoreRadioBean;
import cuit.xsgw.bean.MoreRadioForm;
import cuit.xsgw.bean.NewItemForm;
import cuit.xsgw.bean.RadioForm;
import cuit.xsgw.bean.RadioStringBean;
import cuit.xsgw.bean.SingleEditForm;
import cuit.xsgw.net.bean.BaseRecordQuest;
import cuit.xsgw.net.bean.DiabeteQuest;
import cuit.xsgw.net.bean.FollowRecordQuest;
import cuit.xsgw.net.bean.HighBloodQuest;
import cuit.xsgw.net.bean.Medical;
import cuit.xsgw.net.bean.MedicalUserQuest;
import cuit.xsgw.net.bean.Symptom;
import cuit.xsgw.utils.date.DateUtils;

public class FormUtil {
    //糖尿病随访表
    public static List<BaseForm> getDiabetesFollowRecordForm(DiabeteQuest data) {
        List<BaseForm> form = new ArrayList<>();

        //表头
        getBeginForm(form, data);

        //症状
        BaseForm tabF1 = new BaseForm();
        tabF1.setTitle("症状");
        tabF1.setType(FormType.TYPE_TAB);
        form.add(tabF1);
        CheckboxForm zzF0 = new CheckboxForm();
        zzF0.setId(FormType.FormID.BASE_ID_RECORE_SYMPTOM);
        zzF0.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        zzF0.setSpancount(3);
        List<CheckBoxBean> zzF0data = new ArrayList<>();
        zzF0data.add(new CheckBoxBean("无症状", false));
        zzF0data.add(new CheckBoxBean("多饮", false));
        zzF0data.add(new CheckBoxBean("多食", false));
        zzF0data.add(new CheckBoxBean("多尿", false));
        zzF0data.add(new CheckBoxBean("视力模糊", false));
        zzF0data.add(new CheckBoxBean("感染", false));
        zzF0data.add(new CheckBoxBean("手脚麻木", false));
        zzF0data.add(new CheckBoxBean("下肢浮肿", false));
        zzF0data.add(new CheckBoxBean("体重明显下降", false));
        zzF0data.add(new CheckBoxBean("其他", false));
        if (data.getRecordSymptoms() != null) {
            List<Symptom> symptomList = data.getRecordSymptoms();
            for (int s = 0; s < symptomList.size(); s++) {
                zzF0data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    zzF0.setOtherValue(symptomList.get(s).getValue());
                }
            }
        }
        zzF0.setCheckData(zzF0data);
        form.add(zzF0);

        //体征
        BaseForm tabF2 = new BaseForm();
        tabF2.setTitle("体征");
        tabF2.setType(FormType.TYPE_TAB);
        form.add(tabF2);
        MoreEditForm tzF0 = new MoreEditForm();
        tzF0.setId(FormType.FormID.BASE_ID_RECORE_BLOOD);
        tzF0.setTitle("血压");
        tzF0.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> tzF0data = new ArrayList<>();
        tzF0data.add(new MoreEditBean("收缩压", "mmHg", EditInputType.TYPE_NUMBER_DECIMAL));
        tzF0data.add(new MoreEditBean("舒张压", "mmHg", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getPressureMin() > 0) {
            tzF0data.get(0).setCurValue(data.getPressureMin() + "");
        }
        if (data.getPressureMax() > 0) {
            tzF0data.get(1).setCurValue(data.getPressureMax() + "");
        }
        tzF0.setMoreEditData(tzF0data);
        form.add(tzF0);
        SingleEditForm tzF1 = new SingleEditForm();
        tzF1.setId(FormType.FormID.BASE_ID_RECORE_HEIGHT);
        tzF1.setTitle("身高");
        tzF1.setType(FormType.TYPE_SINGLE_EDIT);
        tzF1.setInputType(EditInputType.TYPE_NUMBER_DECIMAL);
        tzF1.setUnitData("cm");
        if (data.getHeight() > 0) {
            tzF1.setCurValue(data.getHeight() + "");
        }
        form.add(tzF1);
        MoreEditForm tzF2 = new MoreEditForm();
        tzF2.setId(FormType.FormID.BASE_ID_RECORE_WEIGHT);
        tzF2.setTitle("体重");
        tzF2.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> tzF2data = new ArrayList<>();
        tzF2data.add(new MoreEditBean("目前情况", "kg", EditInputType.TYPE_NUMBER_DECIMAL));
        tzF2data.add(new MoreEditBean("目标体重", "kg", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getWeight() > 0) {
            tzF2data.get(0).setCurValue(data.getWeight() + "");
        }
        if (data.getGoalWeight() > 0) {
            tzF2data.get(1).setCurValue(data.getGoalWeight() + "");
        }
        tzF2.setMoreEditData(tzF2data);
        form.add(tzF2);
        MoreEditForm tzF3 = new MoreEditForm();
        tzF3.setId(FormType.FormID.BASE_ID_RECORE_BIM);
        tzF3.setTitle("体质指数");
        tzF3.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> tzF3data = new ArrayList<>();
        tzF3data.add(new MoreEditBean("目前情况", "kg/m²", EditInputType.TYPE_NULL));
        tzF3data.add(new MoreEditBean("下次目标", "kg/m²", EditInputType.TYPE_NULL));
        if (data.getHeight() > 0 && data.getWeight() > 0) {
            tzF3data.get(0).setCurValue(data.getWeight() / (data.getHeight() * data.getHeight() / 10000) + "");
        }
        if (data.getHeight() > 0 && data.getGoalWeight() > 0) {
            tzF3data.get(1).setCurValue(data.getGoalWeight() / (data.getHeight() * data.getHeight() / 10000) + "");
        }
        tzF3.setMoreEditData(tzF3data);
        form.add(tzF3);
        RadioForm tzF4 = new RadioForm();
        tzF4.setId(FormType.FormID.DIABETES_ID_RECORE_FOOT_PUL);
        tzF4.setTitle("足背动脉搏动");
        tzF4.setType(FormType.TYPE_RADIO);
        tzF4.setSpancount(3);
        List<RadioStringBean> tzF4list = new ArrayList<>();
        tzF4list.add(new RadioStringBean(10, "触及正常", data.getFootPulsation() == 10, false));
        tzF4list.add(new RadioStringBean(20, "双侧减弱", data.getFootPulsation() == 20, false));
        tzF4list.add(new RadioStringBean(21, "左侧减弱", data.getFootPulsation() == 21, false));
        tzF4list.add(new RadioStringBean(22, "右侧减弱", data.getFootPulsation() == 22, false));
        tzF4list.add(new RadioStringBean(30, "双侧消失", data.getFootPulsation() == 30, false));
        tzF4list.add(new RadioStringBean(31, "左侧消失", data.getFootPulsation() == 31, false));
        tzF4list.add(new RadioStringBean(32, "右侧消失", data.getFootPulsation() == 32, false));
        tzF4.setRadioData(tzF4list);
        form.add(tzF4);
        SingleEditForm tzF5 = new SingleEditForm();
        tzF5.setId(FormType.FormID.BASE_ID_RECORE_OTHER);
        tzF5.setTitle("其它");
        tzF5.setType(FormType.TYPE_SINGLE_PORT_EDIT);
        tzF5.setInputType(EditInputType.TYPE_TEXT);
        tzF5.setCurValue(data.getOther());
        form.add(tzF5);

        //生活方式指导
        BaseForm tabF3 = new BaseForm();
        tabF3.setTitle("生活方式指导");
        tabF3.setType(FormType.TYPE_TAB);
        form.add(tabF3);
        MoreEditForm lsF0 = new MoreEditForm();
        lsF0.setId(FormType.FormID.BASE_ID_RECORE_SMOKE);
        lsF0.setTitle("日吸烟量");
        lsF0.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF0data = new ArrayList<>();
        lsF0data.add(new MoreEditBean("目前情况", "支", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF0data.add(new MoreEditBean("下次目标", "支", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getDaySmoke() > 0) {
            lsF0data.get(0).setCurValue(data.getDaySmoke() + "");
        }
        if (data.getGoalDaySmoke() > 0) {
            lsF0data.get(1).setCurValue(data.getGoalDaySmoke() + "");
        }
        lsF0.setMoreEditData(lsF0data);
        form.add(lsF0);
        MoreEditForm lsF1 = new MoreEditForm();
        lsF1.setId(FormType.FormID.BASE_ID_RECORE_WINE);
        lsF1.setTitle("日饮酒量");
        lsF1.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF1data = new ArrayList<>();
        lsF1data.add(new MoreEditBean("目前情况", "两", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF1data.add(new MoreEditBean("下次目标", "两", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getDayWine() > 0) {
            lsF1data.get(0).setCurValue(data.getDayWine() + "");
        }
        if (data.getGoalDayWine() > 0) {
            lsF1data.get(1).setCurValue(data.getGoalDayWine() + "");
        }
        lsF1.setMoreEditData(lsF1data);
        form.add(lsF1);
        MoreEditForm lsF2 = new MoreEditForm();
        lsF2.setId(FormType.FormID.BASE_ID_RECORE_SPORT);
        lsF2.setTitle("运动");
        lsF2.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF2data = new ArrayList<>();
        lsF2data.add(new MoreEditBean("目前情况", "次/周", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF2data.add(new MoreEditBean(null, "分钟/次", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF2data.add(new MoreEditBean("下次目标", "次/周", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF2data.add(new MoreEditBean(null, "分钟/次", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getSportRate() > 0) {
            lsF2data.get(0).setCurValue(data.getSportRate() + "");
        }
        if (data.getSportTime() > 0) {
            lsF2data.get(1).setCurValue(data.getSportTime() + "");
        }
        if (data.getGoalSportRate() > 0) {
            lsF2data.get(2).setCurValue(data.getGoalSportRate() + "");
        }
        if (data.getGoalSportTime() > 0) {
            lsF2data.get(3).setCurValue(data.getGoalSportTime() + "");
        }
        lsF2.setMoreEditData(lsF2data);
        form.add(lsF2);
        MoreEditForm lsF3 = new MoreEditForm();
        lsF3.setId(FormType.FormID.DIABETES_ID_RECORE_FOOD);
        lsF3.setTitle("主食");
        lsF3.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF3data = new ArrayList<>();
        lsF3data.add(new MoreEditBean("目前情况", "克/天", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF3data.add(new MoreEditBean("下次目标", "克/天", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getStapleFood() > 0) {
            lsF3data.get(0).setCurValue(data.getStapleFood() + "");
        }
        if (data.getGoalStapleFood() > 0) {
            lsF3data.get(1).setCurValue(data.getGoalStapleFood() + "");
        }
        lsF3.setMoreEditData(lsF3data);
        form.add(lsF3);
        RadioForm lsF4 = new RadioForm();
        lsF4.setId(FormType.FormID.BASE_ID_RECORE_MENTAL);
        lsF4.setTitle("心理调整");
        lsF4.setType(FormType.TYPE_RADIO);
        lsF4.setSpancount(3);
        List<RadioStringBean> lsF4list = new ArrayList<>();
        lsF4list.add(new RadioStringBean(1, "良好", data.getMental() == 1, false));
        lsF4list.add(new RadioStringBean(2, "一般", data.getMental() == 2, false));
        lsF4list.add(new RadioStringBean(3, "差", data.getMental() == 3, false));
        lsF4.setRadioData(lsF4list);
        form.add(lsF4);
        RadioForm lsF5 = new RadioForm();
        lsF5.setId(FormType.FormID.BASE_ID_RECORE_FOLLOW_DOC);
        lsF5.setTitle("遵医行为");
        lsF5.setType(FormType.TYPE_RADIO);
        lsF5.setSpancount(3);
        List<RadioStringBean> lsF5list = new ArrayList<>();
        lsF5list.add(new RadioStringBean(1, "良好", data.getFollowDoc() == 1, false));
        lsF5list.add(new RadioStringBean(2, "一般", data.getFollowDoc() == 2, false));
        lsF5list.add(new RadioStringBean(3, "差", data.getFollowDoc() == 3, false));
        lsF5.setRadioData(lsF5list);
        form.add(lsF5);

        //辅助检查
        BaseForm tabF4 = new BaseForm();
        tabF4.setTitle("辅助检查");
        tabF4.setType(FormType.TYPE_TAB);
        form.add(tabF4);
        SingleEditForm fzF1 = new SingleEditForm();
        fzF1.setId(FormType.FormID.DIABETES_ID_RECORE_LIMOSIS);
        fzF1.setTitle("空腹血糖");
        fzF1.setType(FormType.TYPE_SINGLE_EDIT);
        fzF1.setInputType(EditInputType.TYPE_NUMBER_DECIMAL);
        fzF1.setUnitData("mmol/L");
        if (data.getLimosis() > 0) {
            fzF1.setCurValue(data.getLimosis() + "");
        }
        form.add(fzF1);
        SingleEditForm fzF2 = new SingleEditForm();
        fzF2.setId(FormType.FormID.DIABETES_ID_RECORE_SUGAR_RED);
        fzF2.setTitle("糖化血红蛋白");
        fzF2.setType(FormType.TYPE_SINGLE_EDIT);
        fzF2.setInputType(EditInputType.TYPE_NUMBER_DECIMAL);
        fzF2.setUnitData("%");
        if (data.getSugarBloodRed() > 0) {
            fzF2.setCurValue(data.getSugarBloodRed() + "");
        }
        form.add(fzF2);
        SingleEditForm fzF3 = new SingleEditForm();
        fzF3.setId(FormType.FormID.BASE_ID_RECORE_ASSIST);
        fzF3.setTitle("其它检查");
        fzF3.setType(FormType.TYPE_SINGLE_PORT_EDIT);
        fzF3.setInputType(EditInputType.TYPE_TEXT);
        fzF3.setCurValue(data.getAssistCheck());
        form.add(fzF3);
        DateForm fzF4 = new DateForm();
        fzF4.setId(FormType.FormID.DIABETES_ID_RECORE_CHECK_TIME);
        fzF4.setTitle("检查日期");
        fzF4.setType(FormType.TYPE_DATE);
        fzF4.setCurDate(data.getCheckTime());
        form.add(fzF4);

        //药物及反应
        BaseForm tabF5 = new BaseForm();
        tabF5.setTitle("药物及反应");
        tabF5.setType(FormType.TYPE_TAB);
        form.add(tabF5);
        RadioForm ywfyF0 = new RadioForm();
        ywfyF0.setId(FormType.FormID.BASE_ID_RECORE_RELY);
        ywfyF0.setTitle("服药依从性");
        ywfyF0.setType(FormType.TYPE_RADIO);
        ywfyF0.setSpancount(3);
        List<RadioStringBean> ywfyF0list = new ArrayList<>();
        ywfyF0list.add(new RadioStringBean(1, "规律", data.getRely() == 1, false));
        ywfyF0list.add(new RadioStringBean(2, "间断", data.getRely() == 2, false));
        ywfyF0list.add(new RadioStringBean(3, "不服药", data.getRely() == 3, false));

        ywfyF0.setRadioData(ywfyF0list);
        form.add(ywfyF0);
        RadioForm ywfyF1 = new RadioForm();
        ywfyF1.setId(FormType.FormID.BASE_ID_RECORE_BADNESS);
        ywfyF1.setTitle("药物不良反应");
        ywfyF1.setType(FormType.TYPE_RADIO);
        ywfyF1.setSpancount(3);
        List<RadioStringBean> ywfyF1list = new ArrayList<>();
        ywfyF1list.add(new RadioStringBean(1, "无", data.getBadness() == null, false));
        ywfyF1list.add(new RadioStringBean(2, "有", data.getBadness() != null, true));
        if (data.getBadness() != null) {
            ywfyF1list.get(1).setDetail(data.getBadness());
        }
        ywfyF1.setRadioData(ywfyF1list);
        form.add(ywfyF1);
        RadioForm ywfyF2 = new RadioForm();
        ywfyF2.setId(FormType.FormID.DIABETES_ID_RECORE_LOW_SUGAR);
        ywfyF2.setTitle("低血糖反应");
        ywfyF2.setType(FormType.TYPE_RADIO);
        ywfyF2.setSpancount(3);
        List<RadioStringBean> ywfyF2list = new ArrayList<>();
        ywfyF2list.add(new RadioStringBean(1, "无", data.getLowSugarResponse() == 1, false));
        ywfyF2list.add(new RadioStringBean(2, "偶尔", data.getLowSugarResponse() == 2, false));
        ywfyF2list.add(new RadioStringBean(3, "频繁", data.getLowSugarResponse() == 3, false));
        ywfyF2.setRadioData(ywfyF2list);
        form.add(ywfyF2);
        NewItemForm<Medical> ywfyF3 = new NewItemForm<>();
        ywfyF3.setId(FormType.FormID.BASE_ID_RECORE_MEDICINES);
        ywfyF3.setTitle("药品");
        ywfyF3.setType(FormType.TYPE_NEW_ITEM);
        if (data.getRecordMedicines() != null) {
            ywfyF3.setList(data.getRecordMedicines());
        }
        form.add(ywfyF3);
        MoreEditForm ywfyF4 = new MoreEditForm();
        ywfyF4.setId(FormType.FormID.DIABETES_ID_RECORE_INSULIN);
        ywfyF4.setTitle("胰岛素");
        ywfyF4.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> ywfyF4data = new ArrayList<>();
        ywfyF4data.add(new MoreEditBean("种类", null, EditInputType.TYPE_TEXT));
        ywfyF4data.add(new MoreEditBean("用法用量", null, EditInputType.TYPE_TEXT));
        ywfyF4data.get(0).setCurValue(data.getInsulinClass());
        ywfyF4data.get(1).setCurValue(data.getInsulinMedicine());
        ywfyF4.setMoreEditData(ywfyF4data);
        form.add(ywfyF4);

        //表尾
        getEndForm(form, data);

        return form;
    }

    //高血压随访表
    public static List<BaseForm> getHighBloodFollowRecordForm(HighBloodQuest data) {
        List<BaseForm> form = new ArrayList<>();

        //表头
        getBeginForm(form, data);

        //症状
        BaseForm tabF1 = new BaseForm();
        tabF1.setTitle("症状");
        tabF1.setType(FormType.TYPE_TAB);
        form.add(tabF1);
        CheckboxForm zzF0 = new CheckboxForm();
        zzF0.setId(FormType.FormID.BASE_ID_RECORE_SYMPTOM);
        zzF0.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        List<CheckBoxBean> zzF0data = new ArrayList<>();
        zzF0.setSpancount(3);
        zzF0data.add(new CheckBoxBean("无症状", false));
        zzF0data.add(new CheckBoxBean("头痛头晕", false));
        zzF0data.add(new CheckBoxBean("恶心呕吐", false));
        zzF0data.add(new CheckBoxBean("眼花耳鸣", false));
        zzF0data.add(new CheckBoxBean("呼吸困难", false));
        zzF0data.add(new CheckBoxBean("心悸胸闷", false));
        zzF0data.add(new CheckBoxBean("鼻衄出血不止", false));
        zzF0data.add(new CheckBoxBean("四肢发麻", false));
        zzF0data.add(new CheckBoxBean("下肢水肿", false));
        zzF0data.add(new CheckBoxBean("其他", false));
        if (data.getRecordSymptoms() != null) {
            List<Symptom> symptomList = data.getRecordSymptoms();
            for (int s = 0; s < symptomList.size(); s++) {
                zzF0data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    zzF0.setOtherValue(symptomList.get(s).getValue());
                }
            }
        }
        zzF0.setCheckData(zzF0data);
        form.add(zzF0);

        //体征
        BaseForm tabF2 = new BaseForm();
        tabF2.setTitle("体征");
        tabF2.setType(FormType.TYPE_TAB);
        form.add(tabF2);
        MoreEditForm tzF0 = new MoreEditForm();
        tzF0.setId(FormType.FormID.BASE_ID_RECORE_BLOOD);
        tzF0.setTitle("血压");
        tzF0.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> tzF0data = new ArrayList<>();
        tzF0data.add(new MoreEditBean("收缩压", "mmHg", EditInputType.TYPE_NUMBER_DECIMAL));
        tzF0data.add(new MoreEditBean("舒张压", "mmHg", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getPressureMin() > 0) {
            tzF0data.get(0).setCurValue(data.getPressureMin() + "");
        }
        if (data.getPressureMax() > 0) {
            tzF0data.get(1).setCurValue(data.getPressureMax() + "");
        }
        tzF0.setMoreEditData(tzF0data);
        form.add(tzF0);
        SingleEditForm tzF1 = new SingleEditForm();
        tzF1.setId(FormType.FormID.BASE_ID_RECORE_HEIGHT);
        tzF1.setTitle("身高");
        tzF1.setType(FormType.TYPE_SINGLE_EDIT);
        tzF1.setInputType(EditInputType.TYPE_NUMBER_DECIMAL);
        tzF1.setUnitData("cm");
        if (data.getHeight() > 0) {
            tzF1.setCurValue(data.getHeight() + "");
        }
        form.add(tzF1);
        MoreEditForm tzF2 = new MoreEditForm();
        tzF2.setId(FormType.FormID.BASE_ID_RECORE_WEIGHT);
        tzF2.setTitle("体重");
        tzF2.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> tzF2data = new ArrayList<>();
        tzF2data.add(new MoreEditBean("目前情况", "kg", EditInputType.TYPE_NUMBER_DECIMAL));
        tzF2data.add(new MoreEditBean("目标体重", "kg", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getWeight() > 0) {
            tzF2data.get(0).setCurValue(data.getWeight() + "");
        }
        if (data.getGoalWeight() > 0) {
            tzF2data.get(1).setCurValue(data.getGoalWeight() + "");
        }
        tzF2.setMoreEditData(tzF2data);
        form.add(tzF2);
        MoreEditForm tzF3 = new MoreEditForm();
        tzF3.setId(FormType.FormID.BASE_ID_RECORE_BIM);
        tzF3.setTitle("体质指数");
        tzF3.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> tzF3data = new ArrayList<>();
        tzF3data.add(new MoreEditBean("目前情况", "kg/m²", EditInputType.TYPE_NULL));
        tzF3data.add(new MoreEditBean("下次目标", "kg/m²", EditInputType.TYPE_NULL));
        if (data.getHeight() > 0 && data.getWeight() > 0) {
            tzF3data.get(0).setCurValue(data.getWeight() / (data.getHeight() * data.getHeight() / 10000) + "");
        }
        if (data.getHeight() > 0 && data.getGoalWeight() > 0) {
            tzF3data.get(1).setCurValue(data.getGoalWeight() / (data.getHeight() * data.getHeight() / 10000) + "");
        }
        tzF3.setMoreEditData(tzF3data);
        form.add(tzF3);
        SingleEditForm tzF4 = new SingleEditForm();
        tzF4.setId(FormType.FormID.BLOOD_ID_RECORE_HEART);
        tzF4.setTitle("心率");
        tzF4.setType(FormType.TYPE_SINGLE_EDIT);
        tzF4.setInputType(EditInputType.TYPE_NUMBER_DECIMAL);
        tzF4.setUnitData("次/分钟");
        if (data.getHeartRate() > 0) {
            tzF4.setCurValue(data.getHeartRate() + "");
        }
        form.add(tzF4);
        SingleEditForm tzF5 = new SingleEditForm();
        tzF5.setId(FormType.FormID.BASE_ID_RECORE_OTHER);
        tzF5.setTitle("其它");
        tzF5.setType(FormType.TYPE_SINGLE_PORT_EDIT);
        tzF5.setInputType(EditInputType.TYPE_TEXT);
        tzF5.setCurValue(data.getOther());
        form.add(tzF5);

        //生活方式
        BaseForm tabF3 = new BaseForm();
        tabF3.setTitle("生活方式");
        tabF3.setType(FormType.TYPE_TAB);
        form.add(tabF3);
        MoreEditForm lsF0 = new MoreEditForm();
        lsF0.setId(FormType.FormID.BASE_ID_RECORE_SMOKE);
        lsF0.setTitle("日吸烟量");
        lsF0.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF0data = new ArrayList<>();
        lsF0data.add(new MoreEditBean("目前情况", "支/天", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF0data.add(new MoreEditBean("下次目标", "支/天", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getDaySmoke() > 0) {
            lsF0data.get(0).setCurValue(data.getDaySmoke() + "");
        }
        if (data.getGoalDaySmoke() > 0) {
            lsF0data.get(1).setCurValue(data.getGoalDaySmoke() + "");
        }
        lsF0.setMoreEditData(lsF0data);
        form.add(lsF0);
        MoreEditForm lsF1 = new MoreEditForm();
        lsF1.setId(FormType.FormID.BASE_ID_RECORE_WINE);
        lsF1.setTitle("日饮酒量");
        lsF1.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF1data = new ArrayList<>();
        lsF1data.add(new MoreEditBean("目前情况", "两/天", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF1data.add(new MoreEditBean("下次目标", "两/天", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getDayWine() > 0) {
            lsF1data.get(0).setCurValue(data.getDayWine() + "");
        }
        if (data.getGoalDayWine() > 0) {
            lsF1data.get(1).setCurValue(data.getGoalDayWine() + "");
        }
        lsF1.setMoreEditData(lsF1data);
        form.add(lsF1);
        MoreEditForm lsF2 = new MoreEditForm();
        lsF2.setId(FormType.FormID.BASE_ID_RECORE_SPORT);
        lsF2.setTitle("运动");
        lsF2.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> lsF2data = new ArrayList<>();
        lsF2data.add(new MoreEditBean("目前情况", "次/周", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF2data.add(new MoreEditBean(null, "分钟/次", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF2data.add(new MoreEditBean("下次目标", "次/周", EditInputType.TYPE_NUMBER_DECIMAL));
        lsF2data.add(new MoreEditBean(null, "分钟/次", EditInputType.TYPE_NUMBER_DECIMAL));
        if (data.getSportRate() > 0) {
            lsF2data.get(0).setCurValue(data.getSportRate() + "");
        }
        if (data.getSportTime() > 0) {
            lsF2data.get(1).setCurValue(data.getSportTime() + "");
        }
        if (data.getGoalSportRate() > 0) {
            lsF2data.get(2).setCurValue(data.getGoalSportRate() + "");
        }
        if (data.getGoalSportTime() > 0) {
            lsF2data.get(3).setCurValue(data.getGoalSportTime() + "");
        }
        lsF2.setMoreEditData(lsF2data);
        form.add(lsF2);
        MoreRadioForm lsF3 = new MoreRadioForm();
        lsF3.setId(FormType.FormID.BLOOD_ID_RECORE_SALT);
        lsF3.setTitle("摄盐情况(咸淡)");
        lsF3.setType(FormType.TYPE_MORE_RADIO);
        List<MoreRadioBean> lsF3data = new ArrayList<>();
        MoreRadioBean lsF3d0 = new MoreRadioBean("目前情况");
        List<RadioStringBean> lsF3d0list = new ArrayList<>();
        lsF3d0list.add(new RadioStringBean(1, "轻", data.getSalt() == 1, false));
        lsF3d0list.add(new RadioStringBean(2, "中", data.getSalt() == 2, false));
        lsF3d0list.add(new RadioStringBean(3, "重", data.getSalt() == 3, false));
        lsF3d0.setList(lsF3d0list);
        lsF3data.add(lsF3d0);
        MoreRadioBean lsF3d1 = new MoreRadioBean("下次目标");
        List<RadioStringBean> lsF3d1list = new ArrayList<>();
        lsF3d1list.add(new RadioStringBean(1, "轻", data.getGoalSalt() == 1, false));
        lsF3d1list.add(new RadioStringBean(2, "中", data.getGoalSalt() == 2, false));
        lsF3d1list.add(new RadioStringBean(3, "重", data.getGoalSalt() == 3, false));
        lsF3d1.setList(lsF3d1list);
        lsF3data.add(lsF3d1);
        lsF3.setMoreRadioData(lsF3data);
        form.add(lsF3);
        RadioForm lsF4 = new RadioForm();
        lsF4.setId(FormType.FormID.BASE_ID_RECORE_MENTAL);
        lsF4.setTitle("心理调整");
        lsF4.setType(FormType.TYPE_RADIO);
        lsF4.setSpancount(3);
        List<RadioStringBean> lsF4list = new ArrayList<>();
        lsF4list.add(new RadioStringBean(1, "良好", data.getMental() == 1, false));
        lsF4list.add(new RadioStringBean(2, "一般", data.getMental() == 2, false));
        lsF4list.add(new RadioStringBean(3, "差", data.getMental() == 3, false));
        lsF4.setRadioData(lsF4list);
        form.add(lsF4);
        RadioForm lsF5 = new RadioForm();
        lsF5.setId(FormType.FormID.BASE_ID_RECORE_FOLLOW_DOC);
        lsF5.setTitle("遵医行为");
        lsF5.setType(FormType.TYPE_RADIO);
        lsF5.setSpancount(3);
        List<RadioStringBean> lsF5list = new ArrayList<>();
        lsF5list.add(new RadioStringBean(1, "良好", data.getFollowDoc() == 1, false));
        lsF5list.add(new RadioStringBean(2, "一般", data.getFollowDoc() == 2, false));
        lsF5list.add(new RadioStringBean(3, "差", data.getFollowDoc() == 3, false));
        lsF5.setRadioData(lsF5list);
        form.add(lsF5);

        //辅助检查
        BaseForm tabF4 = new BaseForm();
        tabF4.setTitle("辅助检查");
        tabF4.setType(FormType.TYPE_TAB);
        form.add(tabF4);
        SingleEditForm fzF0 = new SingleEditForm();
        fzF0.setId(FormType.FormID.BASE_ID_RECORE_ASSIST);
        fzF0.setTitle("辅助检查");
        fzF0.setType(FormType.TYPE_SINGLE_PORT_EDIT);
        fzF0.setInputType(EditInputType.TYPE_TEXT);
        fzF0.setCurValue(data.getAssistCheck());
        form.add(fzF0);

        //用药情况
        BaseForm tabF5 = new BaseForm();
        tabF5.setTitle("用药情况");
        tabF5.setType(FormType.TYPE_TAB);
        form.add(tabF5);
        NewItemForm<Medical> ywfyF0 = new NewItemForm<>();
        ywfyF0.setId(FormType.FormID.BASE_ID_RECORE_MEDICINES);
        ywfyF0.setTitle("药品");
        ywfyF0.setType(FormType.TYPE_NEW_ITEM);
        if (data.getRecordMedicines() != null) {
            ywfyF0.setList(data.getRecordMedicines());
        }
        form.add(ywfyF0);
        RadioForm ywfyF1 = new RadioForm();
        ywfyF1.setId(FormType.FormID.BASE_ID_RECORE_RELY);
        ywfyF1.setTitle("服药依从性");
        ywfyF1.setType(FormType.TYPE_RADIO);
        ywfyF1.setSpancount(3);
        List<RadioStringBean> ywfyF1list = new ArrayList<>();
        ywfyF1list.add(new RadioStringBean(1, "规律", data.getRely() == 1, false));
        ywfyF1list.add(new RadioStringBean(2, "间断", data.getRely() == 1, false));
        ywfyF1list.add(new RadioStringBean(3, "不服药", data.getRely() == 1, false));
        ywfyF1.setRadioData(ywfyF1list);
        form.add(ywfyF1);
        RadioForm ywfyF2 = new RadioForm();
        ywfyF2.setId(FormType.FormID.BASE_ID_RECORE_BADNESS);
        ywfyF2.setTitle("药物不良反应");
        ywfyF2.setType(FormType.TYPE_RADIO);
        ywfyF2.setSpancount(3);
        List<RadioStringBean> ywfyF2list = new ArrayList<>();
        ywfyF2list.add(new RadioStringBean(1, "无", data.getBadness() == null, false));
        ywfyF2list.add(new RadioStringBean(2, "有", data.getBadness() != null, true));
        if (data.getBadness() != null) {
            ywfyF2list.get(1).setDetail(data.getBadness());
        }
        ywfyF2.setRadioData(ywfyF2list);
        form.add(ywfyF2);

        //表尾
        getEndForm(form, data);

        return form;
    }

    //表头
    private static void getBeginForm(List<BaseForm> form, FollowRecordQuest data) {
        BaseForm tabF0 = new BaseForm();
        tabF0.setTitle("表头");
        tabF0.setType(FormType.TYPE_TAB);
        form.add(tabF0);
        DateForm btF0 = new DateForm();
        btF0.setId(FormType.FormID.BASE_ID_RECORE_TIME);
        btF0.setTitle("随访日期");
        btF0.setType(FormType.TYPE_DATE);
        if (data.getVisitTime() > 0) {
            btF0.setCurDate(data.getVisitTime());
        }
        form.add(btF0);
        RadioForm btF1 = new RadioForm();
        btF1.setId(FormType.FormID.BASE_ID_RECORE_METHOD);
        btF1.setTitle("随访方式");
        btF1.setType(FormType.TYPE_RADIO);
        btF1.setSpancount(3);
        List<RadioStringBean> btF2list = new ArrayList<>();
        btF2list.add(new RadioStringBean(1, "门诊", data.getMethod() == 1, false));
        btF2list.add(new RadioStringBean(2, "家庭", data.getMethod() == 2, false));
        btF2list.add(new RadioStringBean(3, "电话", data.getMethod() == 3, false));

        btF1.setRadioData(btF2list);
        form.add(btF1);
    }

    //表尾
    private static void getEndForm(List<BaseForm> form, FollowRecordQuest data) {
        BaseForm tabF6 = new BaseForm();
        tabF6.setTitle("表尾");
        tabF6.setType(FormType.TYPE_TAB);
        form.add(tabF6);
        RadioForm sfflF0 = new RadioForm();
        sfflF0.setId(FormType.FormID.BASE_ID_RECORE_CLASSIFY);
        sfflF0.setTitle("此次随访分类");
        sfflF0.setType(FormType.TYPE_RADIO);
        sfflF0.setSpancount(2);
        List<RadioStringBean> sfflF0list = new ArrayList<>();
        sfflF0list.add(new RadioStringBean(1, "控制满意", data.getClassify() == 1, false));
        sfflF0list.add(new RadioStringBean(2, "控制不满意", data.getClassify() == 2, false));
        sfflF0list.add(new RadioStringBean(3, "不良反应", data.getClassify() == 3, false));
        sfflF0list.add(new RadioStringBean(4, "并发症", data.getClassify() == 4, false));
        sfflF0.setRadioData(sfflF0list);
        form.add(sfflF0);
        MoreEditForm zzhenF1 = new MoreEditForm();
        zzhenF1.setId(FormType.FormID.BASE_ID_RECORE_TRANSFER);
        zzhenF1.setTitle("转诊");
        zzhenF1.setType(FormType.TYPE_MORE_EDIT);
        List<MoreEditBean> zzhenF1data = new ArrayList<>();
        zzhenF1data.add(new MoreEditBean("转诊原因", null, EditInputType.TYPE_TEXT));
        zzhenF1data.add(new MoreEditBean("机构/科别", null, EditInputType.TYPE_TEXT));
        zzhenF1data.get(0).setCurValue(data.getTransferReason());
        zzhenF1data.get(1).setCurValue(data.getTransferOrg());
        zzhenF1.setMoreEditData(zzhenF1data);
        form.add(zzhenF1);
        DateForm nextSfF2 = new DateForm();
        nextSfF2.setId(FormType.FormID.BASE_ID_RECORE_NEXT_TIME);
        nextSfF2.setTitle("下次随访日期");
        nextSfF2.setType(FormType.TYPE_DATE);
        if (data.getNextTime() > 0) {
            nextSfF2.setCurDate(data.getNextTime());
        }
        form.add(nextSfF2);

        getDoctorAndUser(form, data);
    }

    private static void getDoctorAndUser(List<BaseForm> form, BaseRecordQuest data) {
        /*ChooseStringForm ysF3 = new ChooseStringForm();
        ysF3.setId(FormType.FormID.BASE_ID_RECORE_DOCTOR);
        ysF3.setTitle("随访医生");
        ysF3.setType(FormType.TYPE_CHOOSE_STRING);
        ysF3.setCurId(data.getDoctorId());
        ysF3.setCurValue(null);//医生名字  暂无数据
        form.add(ysF3);
        SingleEditForm userF4 = new SingleEditForm();
        userF4.setId(FormType.FormID.BASE_ID_CUSTOMER_SIGNATURE);
        userF4.setTitle("客户签名");
        userF4.setType(FormType.TYPE_SINGLE_EDIT);
        userF4.setInputType(EditInputType.TYPE_TEXT);
        form.add(userF4);*/
    }

    //居民档案表  基本信息
    public static List<BaseForm> getMedicalUserBaseForm(MedicalUserQuest data) {
        List<BaseForm> form = new ArrayList<>();

        BaseForm tabF0 = new BaseForm();
        tabF0.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_TAB);
        tabF0.setTitle("基本信息");
        tabF0.setType(FormType.TYPE_TAB);
        form.add(tabF0);

        SingleEditForm seF0 = new SingleEditForm();
        seF0.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_IDCARD);
        seF0.setTitle("身份证");
        seF0.setType(FormType.TYPE_ZBAR);
        seF0.setInputType(EditInputType.TYPE_TEXT);
        seF0.setCurValue(data.getIdCard());
        form.add(seF0);

        ChoosePictureForm cpF1 = new ChoosePictureForm();
        cpF1.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_PICTURE);
        cpF1.setTitle("照片");
        cpF1.setType(FormType.TYPE_PIC);
        cpF1.setCurPath(data.getPic());
        form.add(cpF1);

        SingleEditForm seF1 = new SingleEditForm();
        seF1.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_NAME);
        seF1.setTitle("姓名");
        seF1.setType(FormType.TYPE_SINGLE_EDIT);
        seF1.setInputType(EditInputType.TYPE_TEXT);
        seF1.setCurValue(data.getName());
        form.add(seF1);

        RadioForm radioF0 = new RadioForm();
        radioF0.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_SEX);
        radioF0.setTitle("性别");
        radioF0.setType(FormType.TYPE_RADIO);
        radioF0.setSpancount(3);
        int sexIndex = 0;
        if (data.getIdCard() != null && data.getIdCard().trim().length() > 0) {
            sexIndex = Integer.parseInt(data.getIdCard().substring(16, 17));
        }
        List<RadioStringBean> radioF0list = new ArrayList<>();
        radioF0list.add(new RadioStringBean(1, "男", sexIndex % 2 != 0, false));
        radioF0list.add(new RadioStringBean(2, "女", sexIndex % 2 == 0, false));
        radioF0.setRadioData(radioF0list);
        form.add(radioF0);

        DateForm dateF2 = new DateForm();
        dateF2.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_BIRTHDAY);
        dateF2.setTitle("出生日期");
        dateF2.setType(FormType.TYPE_DATE);
        if (data.getIdCard() != null && data.getIdCard().trim().length() > 0) {
            int year = Integer.parseInt(data.getIdCard().substring(6, 10));
            int month = Integer.parseInt(data.getIdCard().substring(10, 12));
            int day = Integer.parseInt(data.getIdCard().substring(12, 14));
            Calendar calendar = DateUtils.getCalendar();
            calendar.set(year, month - 1, day);
            dateF2.setCurDate(calendar.getTimeInMillis() / 1000);
        }
        form.add(dateF2);

        SingleEditForm seF2 = new SingleEditForm();
        seF2.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_AGE);
        seF2.setTitle("年龄");
        seF2.setType(FormType.TYPE_SINGLE_EDIT);
        seF2.setUnitData("岁");
        seF2.setInputType(EditInputType.TYPE_NULL);
        int age = -1;
        if (data.getIdCard() != null && data.getIdCard().trim().length() > 0) {
            int year = Integer.parseInt(data.getIdCard().substring(6, 10));
            age = DateUtils.getYear() - year;
        }
        seF2.setCurValue(age > 0 ? String.valueOf(age) : null);
        form.add(seF2);

        ChooseStringForm csF0 = new ChooseStringForm();
        csF0.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_NATION);
        csF0.setTitle("民族");
        csF0.setType(FormType.TYPE_CHOOSE_STRING);
        if (data.getEthnic() == null) {
            data.setEthnic("汉族");
        }
        csF0.setCurValue(data.getEthnic());
        form.add(csF0);

        RadioForm radioF1 = new RadioForm();
        radioF1.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_STAY_TYPE);
        radioF1.setTitle("常驻类型");
        radioF1.setType(FormType.TYPE_RADIO);
        radioF1.setSpancount(3);
        List<RadioStringBean> radioF1list = new ArrayList<>();
        radioF1list.add(new RadioStringBean(1, "户籍", data.getCensus() == 1, false));
        radioF1list.add(new RadioStringBean(2, "非户籍", data.getCensus() == 2, false));
        radioF1.setRadioData(radioF1list);
        form.add(radioF1);

        SingleEditForm seF3 = new SingleEditForm();
        seF3.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_PHONE);
        seF3.setTitle("电话");
        seF3.setType(FormType.TYPE_SINGLE_EDIT);
        seF3.setInputType(EditInputType.TYPE_JUST_NUMBER);
        seF3.setCurValue(data.getPhone());
        form.add(seF3);

        ChooseAreaForm caF0 = new ChooseAreaForm();
        caF0.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_COMMUNITY);
        caF0.setTitle("村(社区)");
        caF0.setType(FormType.TYPE_CHOOSE_AREA);
        caF0.setCurAreaCode(data.getAreaCode());
        //caF0.setCurAreaName("");
        form.add(caF0);

        SingleEditForm seF4 = new SingleEditForm();
        seF4.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_STAY_NOW);
        seF4.setTitle("现住址");
        seF4.setType(FormType.TYPE_SINGLE_EDIT);
        seF4.setInputType(EditInputType.TYPE_TEXT);
        seF4.setCurValue(data.getAddress());
        form.add(seF4);

        SingleEditForm seF5 = new SingleEditForm();
        seF5.setId(FormType.ResidentFormID.RESIDENT_ID_BASE_DOMICILE);
        seF5.setTitle("户籍地址");
        seF5.setType(FormType.TYPE_SINGLE_EDIT);
        seF5.setInputType(EditInputType.TYPE_TEXT);
        seF5.setCurValue(data.getCensusAddress());
        form.add(seF5);

        BaseForm tabF1 = new BaseForm();
        tabF1.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_TAB);
        tabF1.setTitle("更多信息(以下选填)");
        tabF1.setType(FormType.TYPE_TAB);
        form.add(tabF1);

        SingleEditForm seF6 = new SingleEditForm();
        seF6.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_WORK_UNIT);
        seF6.setTitle("工作单位");
        seF6.setType(FormType.TYPE_SINGLE_EDIT);
        seF6.setInputType(EditInputType.TYPE_TEXT);
        seF6.setCurValue(data.getEmployer());
        form.add(seF6);

        SingleEditForm seF7 = new SingleEditForm();
        seF7.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_CONTACT_NAME);
        seF7.setTitle("联系人姓名");
        seF7.setType(FormType.TYPE_SINGLE_EDIT);
        seF7.setInputType(EditInputType.TYPE_TEXT);
        seF7.setCurValue(data.getContactName());
        form.add(seF7);

        SingleEditForm seF8 = new SingleEditForm();
        seF8.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_CONTACT_PHONE);
        seF8.setTitle("联系人电话");
        seF8.setType(FormType.TYPE_SINGLE_EDIT);
        seF8.setInputType(EditInputType.TYPE_JUST_NUMBER);
        seF8.setCurValue(data.getContactPhone());
        form.add(seF8);

        RadioForm radioF2 = new RadioForm();
        radioF2.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_BLOOD_TYPE);
        radioF2.setTitle("血型");
        radioF2.setType(FormType.TYPE_RADIO);
        radioF2.setSpancount(3);
        List<RadioStringBean> radioF2list = new ArrayList<>();
        radioF2list.add(new RadioStringBean(1, "A型", data.getBloodType() == 1, false));
        radioF2list.add(new RadioStringBean(2, "B型", data.getBloodType() == 2, false));
        radioF2list.add(new RadioStringBean(3, "O型", data.getBloodType() == 3, false));
        radioF2list.add(new RadioStringBean(4, "AB型", data.getBloodType() == 4, false));
        radioF2list.add(new RadioStringBean(5, "不详", data.getBloodType() == 5, false));
        radioF2.setRadioData(radioF2list);
        form.add(radioF2);

        RadioForm radioF3 = new RadioForm();
        radioF3.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_BLOOD_RH_TYPE);
        radioF3.setTitle("血型(RH)");
        radioF3.setType(FormType.TYPE_RADIO);
        radioF3.setSpancount(3);
        List<RadioStringBean> radioF3list = new ArrayList<>();
        radioF3list.add(new RadioStringBean(1, "阳性", data.getBloodRh() == 1, false));
        radioF3list.add(new RadioStringBean(2, "阴性", data.getBloodRh() == 2, false));
        radioF3list.add(new RadioStringBean(3, "不详", data.getBloodRh() == 3, false));
        radioF3.setRadioData(radioF3list);
        form.add(radioF3);

        RadioForm radioF4 = new RadioForm();
        radioF4.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_EDUCATION);
        radioF4.setTitle("文化程度");
        radioF4.setType(FormType.TYPE_RADIO);
        radioF4.setSpancount(3);
        List<RadioStringBean> radioF4list = new ArrayList<>();
        radioF4list.add(new RadioStringBean(1, "研究生", data.getEducationType() == 1, false));
        radioF4list.add(new RadioStringBean(2, "大学本科", data.getEducationType() == 2, false));
        radioF4list.add(new RadioStringBean(3, "专科", data.getEducationType() == 3, false));
        radioF4list.add(new RadioStringBean(4, "中专", data.getEducationType() == 4, false));
        radioF4list.add(new RadioStringBean(5, "技工学校", data.getEducationType() == 5, false));
        radioF4list.add(new RadioStringBean(6, "高中", data.getEducationType() == 6, false));
        radioF4list.add(new RadioStringBean(7, "初中", data.getEducationType() == 7, false));
        radioF4list.add(new RadioStringBean(8, "小学", data.getEducationType() == 8, false));
        radioF4list.add(new RadioStringBean(9, "文盲/半文盲", data.getEducationType() == 9, false));
        radioF4list.add(new RadioStringBean(10, "不详", data.getEducationType() == 10, false));
        radioF4.setRadioData(radioF4list);
        form.add(radioF4);

        RadioForm radioF5 = new RadioForm();
        radioF5.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_WORK_TYPE);
        radioF5.setTitle("职业");
        radioF5.setType(FormType.TYPE_RADIO);
        radioF5.setSpancount(1);
        List<RadioStringBean> radioF5list = new ArrayList<>();
        radioF5list.add(new RadioStringBean(1, "国家机关、党群组织、企事业单位负责人", data.getJobType() == 1, false));
        radioF5list.add(new RadioStringBean(2, "专业技术人员", data.getJobType() == 2, false));
        radioF5list.add(new RadioStringBean(3, "办事人员和有关人员", data.getJobType() == 3, false));
        radioF5list.add(new RadioStringBean(4, "商业、服务业人员", data.getJobType() == 4, false));
        radioF5list.add(new RadioStringBean(5, "农、林、牧、渔、水利业生产人员", data.getJobType() == 5, false));
        radioF5list.add(new RadioStringBean(6, "生产、运输设备操作人员及有关人员", data.getJobType() == 6, false));
        radioF5list.add(new RadioStringBean(7, "军人", data.getJobType() == 7, false));
        radioF5list.add(new RadioStringBean(8, "不便分类的其他从业人员", data.getJobType() == 8, false));
        radioF5list.add(new RadioStringBean(9, "无职业", data.getJobType() == 9, false));
        radioF5.setRadioData(radioF5list);
        form.add(radioF5);

        RadioForm radioF6 = new RadioForm();
        radioF6.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_MARITAL_STATUS);
        radioF6.setTitle("婚姻状况");
        radioF6.setType(FormType.TYPE_RADIO);
        radioF6.setSpancount(3);
        List<RadioStringBean> radioF6list = new ArrayList<>();
        radioF6list.add(new RadioStringBean(1, "未婚", data.getMarriageType() == 1, false));
        radioF6list.add(new RadioStringBean(2, "已婚", data.getMarriageType() == 2, false));
        radioF6list.add(new RadioStringBean(3, "丧偶", data.getMarriageType() == 3, false));
        radioF6list.add(new RadioStringBean(4, "离婚", data.getMarriageType() == 4, false));
        radioF6list.add(new RadioStringBean(6, "未说明", data.getMarriageType() == 5, false));
        radioF6.setRadioData(radioF6list);
        form.add(radioF6);

        CheckboxForm cbF0 = new CheckboxForm();
        cbF0.setTitle("医疗费用支付方式");
        cbF0.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_MEDICAL_PAY_TYPE);
        cbF0.setType(FormType.TYPE_CHECKBOX_OTHER);
        cbF0.setSpancount(2);
        List<CheckBoxBean> cbF0data = new ArrayList<>();
        cbF0data.add(new CheckBoxBean("城镇职工基本医疗保险", false));
        cbF0data.add(new CheckBoxBean("城镇居民基本医疗保险", false));
        cbF0data.add(new CheckBoxBean("新型农村合作医疗", false));
        cbF0data.add(new CheckBoxBean("贫困救助", false));
        cbF0data.add(new CheckBoxBean("商业医疗保险", false));
        cbF0data.add(new CheckBoxBean("全公费", false));
        cbF0data.add(new CheckBoxBean("全自费", false));
        cbF0data.add(new CheckBoxBean("其他", false));
        if (data.getMedicinePays() != null) {
            List<Symptom> symptomList = data.getMedicinePays();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF0data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF0.setOtherValue(symptomList.get(s).getValue());
                }
            }
        } else {
            cbF0data.get(1).setSelected(true);//默认第二条选中
        }
        cbF0.setCheckData(cbF0data);
        form.add(cbF0);

        CheckboxForm cbF1 = new CheckboxForm();
        cbF1.setTitle("药物过敏史");
        cbF1.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_ALLERGY_HISTORY);
        cbF1.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF1.setSpancount(3);
        List<CheckBoxBean> cbF1data = new ArrayList<>();
        cbF1data.add(new CheckBoxBean("无", false));
        cbF1data.add(new CheckBoxBean("青霉素", false));
        cbF1data.add(new CheckBoxBean("磺胺", false));
        cbF1data.add(new CheckBoxBean("链霉素", false));
        cbF1data.add(new CheckBoxBean("其他", false));
        if (data.getMedicineAllergies() != null) {
            List<Symptom> symptomList = data.getMedicineAllergies();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF1data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF1.setOtherValue(symptomList.get(s).getValue());
                }
            }
        } else {
            cbF1data.get(0).setSelected(true);//默认选中 无
        }
        cbF1.setCheckData(cbF1data);
        form.add(cbF1);

        CheckboxForm cbF2 = new CheckboxForm();
        cbF2.setTitle("暴露史");
        cbF2.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_EXPOSURE_HISTORY);
        cbF2.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF2.setSpancount(3);
        List<CheckBoxBean> cbF2data = new ArrayList<>();
        cbF2data.add(new CheckBoxBean("无", false));
        cbF2data.add(new CheckBoxBean("化学品", false));
        cbF2data.add(new CheckBoxBean("毒物", false));
        cbF2data.add(new CheckBoxBean("射线", false));
        cbF2data.add(new CheckBoxBean("其他", false));
        if (data.getUserExposes() != null) {
            List<Symptom> symptomList = data.getUserExposes();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF2data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF2.setOtherValue(symptomList.get(s).getValue());
                }
            }
        } else {
            cbF2data.get(0).setSelected(true);//默认选中 无
        }
        cbF2.setCheckData(cbF2data);
        form.add(cbF2);

        BaseForm tabF2 = new BaseForm();
        tabF2.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_TAB_PAST_HISTORY);
        tabF2.setTitle("以往疾病史");
        tabF2.setType(FormType.TYPE_TAB);
        form.add(tabF2);

        DiseaseHistoryForm dhF0 = new DiseaseHistoryForm();
        dhF0.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_MEDICAL);
        dhF0.setTitle("疾病史");
        dhF0.setType(FormType.TYPE_CHECKBOX_DISEASE_HISTORY);
        List<DiseaseHistoryBean> dhF0data = new ArrayList<>();
        dhF0data.add(new DiseaseHistoryBean("无", null, false));
        dhF0data.add(new DiseaseHistoryBean("高血压", null, false));
        dhF0data.add(new DiseaseHistoryBean("糖尿病", null, false));
        dhF0data.add(new DiseaseHistoryBean("冠心病", null, false));
        dhF0data.add(new DiseaseHistoryBean("慢性阻塞性肺疾病", null, false));
        dhF0data.add(new DiseaseHistoryBean("恶性肿瘤", "肿瘤名称", false));
        dhF0data.add(new DiseaseHistoryBean("脑卒中", null, false));
        dhF0data.add(new DiseaseHistoryBean("严重精神病障碍", null, false));
        dhF0data.add(new DiseaseHistoryBean("肺结核", null, false));
        dhF0data.add(new DiseaseHistoryBean("肝炎", null, false));
        dhF0data.add(new DiseaseHistoryBean("其它法定传染病", null, false));
        dhF0data.add(new DiseaseHistoryBean("职业病", "职业病名", false));
        dhF0data.add(new DiseaseHistoryBean("其他", "其它疾病名", false));
        if (data.getUserBeforeDiseases() != null) {
            List<Symptom> symptomList = data.getUserBeforeDiseases();
            for (int s = 0; s < symptomList.size(); s++) {
                dhF0data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                dhF0data.get(symptomList.get(s).getTag() - 1).setSubValue(symptomList.get(s).getValue());
                dhF0data.get(symptomList.get(s).getTag() - 1).setSureTime(symptomList.get(s).getTime());
            }
        }
        dhF0.setCheckData(dhF0data);
        form.add(dhF0);

        RadioForm radioF7 = new RadioForm();
        radioF7.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_OPERATION);
        radioF7.setTitle("手术");
        radioF7.setType(FormType.TYPE_RADIO);
        radioF7.setSpancount(3);
        List<RadioStringBean> radioF7list = new ArrayList<>();
        radioF7list.add(new RadioStringBean(1, "无", data.getOperation() == null, false));
        radioF7list.add(new RadioStringBean(2, "有", data.getOperation() != null, true));
        if (data.getOperation() != null) {
            radioF7list.get(1).setDetail(data.getOperation());
        }
        radioF7.setRadioData(radioF7list);
        form.add(radioF7);

        RadioForm radioF8 = new RadioForm();
        radioF8.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_TRAUMA);
        radioF8.setTitle("外伤");
        radioF8.setType(FormType.TYPE_RADIO);
        radioF8.setSpancount(3);
        List<RadioStringBean> radioF8list = new ArrayList<>();
        radioF8list.add(new RadioStringBean(1, "无", data.getTraumatic() == null, false));
        radioF8list.add(new RadioStringBean(2, "有", data.getTraumatic() != null, true));
        if (data.getTraumatic() != null) {
            radioF8list.get(1).setDetail(data.getTraumatic());
        }
        radioF8.setRadioData(radioF8list);
        form.add(radioF8);

        RadioForm radioF9 = new RadioForm();
        radioF9.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_TRANSFUSION);
        radioF9.setTitle("输血");
        radioF9.setType(FormType.TYPE_RADIO);
        radioF9.setSpancount(3);
        List<RadioStringBean> radioF9list = new ArrayList<>();
        radioF9list.add(new RadioStringBean(1, "无", data.getTransfusion() == null, false));
        radioF9list.add(new RadioStringBean(2, "有", data.getTransfusion() != null, true));
        if (data.getTransfusion() != null) {
            radioF9list.get(1).setDetail(data.getTransfusion());
        }
        radioF9.setRadioData(radioF9list);
        form.add(radioF9);

        BaseForm tabF3 = new BaseForm();
        tabF3.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_TAB_FAMILY_HISTORY);
        tabF3.setTitle("家族疾病史");
        tabF3.setType(FormType.TYPE_TAB);
        form.add(tabF3);

        CheckboxForm cbF3 = new CheckboxForm();
        cbF3.setTitle("父亲");
        cbF3.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_FATHER);
        cbF3.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF3.setSpancount(3);
        List<CheckBoxBean> cbF3data = new ArrayList<>();
        getFamilyHistory(cbF3data);
        if (data.getFamilyFathers() != null) {
            List<Symptom> symptomList = data.getFamilyFathers();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF3data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF3.setOtherValue(symptomList.get(s).getValue());
                }
            }
        }
        cbF3.setCheckData(cbF3data);
        form.add(cbF3);

        CheckboxForm cbF4 = new CheckboxForm();
        cbF4.setTitle("母亲");
        cbF4.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_MOTHER);
        cbF4.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF4.setSpancount(3);
        List<CheckBoxBean> cbF4data = new ArrayList<>();
        getFamilyHistory(cbF4data);
        if (data.getFamilyMothers() != null) {
            List<Symptom> symptomList = data.getFamilyMothers();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF4data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF4.setOtherValue(symptomList.get(s).getValue());
                }
            }
        }
        cbF4.setCheckData(cbF4data);
        form.add(cbF4);

        CheckboxForm cbF5 = new CheckboxForm();
        cbF5.setTitle("兄弟姐妹");
        cbF5.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_BROTHERS_SISTERS);
        cbF5.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF5.setSpancount(3);
        List<CheckBoxBean> cbF5data = new ArrayList<>();
        getFamilyHistory(cbF5data);
        if (data.getFamilyBrothers() != null) {
            List<Symptom> symptomList = data.getFamilyBrothers();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF5data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF5.setOtherValue(symptomList.get(s).getValue());
                }
            }
        }
        cbF5.setCheckData(cbF5data);
        form.add(cbF5);

        CheckboxForm cbF6 = new CheckboxForm();
        cbF6.setTitle("子女");
        cbF6.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_HISTORY_CHILREN);
        cbF6.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF6.setSpancount(3);
        List<CheckBoxBean> cbF6data = new ArrayList<>();
        getFamilyHistory(cbF6data);
        if (data.getFamilyChildren() != null) {
            List<Symptom> symptomList = data.getFamilyChildren();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF6data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF6.setOtherValue(symptomList.get(s).getValue());
                }
            }
        }
        cbF6.setCheckData(cbF6data);
        form.add(cbF6);

        BaseForm tabF4 = new BaseForm();
        tabF4.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_TAB_OTHER);
        tabF4.setTitle("其他信息");
        tabF4.setType(FormType.TYPE_TAB);
        form.add(tabF4);

        RadioForm radioF10 = new RadioForm();
        radioF10.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_GENETIC_HISTORY);
        radioF10.setTitle("遗传病史");
        radioF10.setType(FormType.TYPE_RADIO);
        radioF10.setSpancount(3);
        List<RadioStringBean> radioF10list = new ArrayList<>();
        radioF10list.add(new RadioStringBean(1, "无", data.getInheritance() == null, false));
        radioF10list.add(new RadioStringBean(2, "有", data.getInheritance() != null, true));
        if (data.getInheritance() != null) {
            radioF10list.get(1).setDetail(data.getInheritance());
        }
        radioF10.setRadioData(radioF10list);
        form.add(radioF10);

        CheckboxForm cbF7 = new CheckboxForm();
        cbF7.setTitle("残疾情况");
        cbF7.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_DISABILITY_CONDITION);
        cbF7.setType(FormType.TYPE_CHECKBOX_ONE_OTHER);
        cbF7.setSpancount(3);
        List<CheckBoxBean> cbF7data = new ArrayList<>();
        cbF7data.add(new CheckBoxBean("无", false));
        cbF7data.add(new CheckBoxBean("视力残疾", false));
        cbF7data.add(new CheckBoxBean("听力残疾", false));
        cbF7data.add(new CheckBoxBean("言语残疾", false));
        cbF7data.add(new CheckBoxBean("肢体残疾", false));
        cbF7data.add(new CheckBoxBean("智力残疾", false));
        cbF7data.add(new CheckBoxBean("精神残疾", false));
        cbF7data.add(new CheckBoxBean("其他", false));
        if (data.getDiseaseConditions() != null) {
            List<Symptom> symptomList = data.getDiseaseConditions();
            for (int s = 0; s < symptomList.size(); s++) {
                cbF7data.get(symptomList.get(s).getTag() - 1).setSelected(true);
                if (symptomList.get(s).getValue() != null) {
                    cbF7.setOtherValue(symptomList.get(s).getValue());
                }
            }
        } else {
            cbF7data.get(0).setSelected(true);//默认选中 无
        }
        cbF7.setCheckData(cbF7data);
        form.add(cbF7);

        RadioForm radioF11 = new RadioForm();
        radioF11.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_KITCHEN_DEVICE);
        radioF11.setTitle("厨房排风设施");
        radioF11.setType(FormType.TYPE_RADIO);
        radioF11.setSpancount(2);
        List<RadioStringBean> radioF11list = new ArrayList<>();
        radioF11list.add(new RadioStringBean(1, "无", data.getKitchenDevice() == 1, false));
        radioF11list.add(new RadioStringBean(2, "油烟机", data.getKitchenDevice() == 2, false));
        radioF11list.add(new RadioStringBean(3, "换气扇", data.getKitchenDevice() == 3, false));
        radioF11list.add(new RadioStringBean(4, "烟囱", data.getKitchenDevice() == 4, false));
        radioF11.setRadioData(radioF11list);
        form.add(radioF11);

        RadioForm radioF12 = new RadioForm();
        radioF12.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_FUEL_TYPE);
        radioF12.setTitle("燃料类型");
        radioF12.setType(FormType.TYPE_RADIO);
        radioF12.setSpancount(3);
        List<RadioStringBean> radioF12list = new ArrayList<>();
        radioF12list.add(new RadioStringBean(1, "液化气", data.getFuel() == 1, false));
        radioF12list.add(new RadioStringBean(2, "煤", data.getFuel() == 2, false));
        radioF12list.add(new RadioStringBean(3, "天然气", data.getFuel() == 3, false));
        radioF12list.add(new RadioStringBean(4, "沼气", data.getFuel() == 4, false));
        radioF12list.add(new RadioStringBean(5, "柴火", data.getFuel() == 5, false));
        radioF12.setRadioData(radioF12list);
        form.add(radioF12);

        RadioForm radioF13 = new RadioForm();
        radioF13.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_DRINKING_WATER);
        radioF13.setTitle("饮水");
        radioF13.setType(FormType.TYPE_RADIO);
        radioF13.setSpancount(3);
        List<RadioStringBean> radioF13list = new ArrayList<>();
        radioF13list.add(new RadioStringBean(1, "自来水", data.getWater() == 1, false));
        radioF13list.add(new RadioStringBean(2, "净化过滤水", data.getWater() == 2, false));
        radioF13list.add(new RadioStringBean(3, "井水", data.getWater() == 3, false));
        radioF13list.add(new RadioStringBean(4, "河湖水", data.getWater() == 4, false));
        radioF13list.add(new RadioStringBean(5, "塘水", data.getWater() == 5, false));
        radioF13list.add(new RadioStringBean(5, "其他", data.getWater() == 6, true));
        radioF13.setRadioData(radioF13list);
        form.add(radioF13);

        RadioForm radioF14 = new RadioForm();
        radioF14.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_TOILET);
        radioF14.setTitle("厕所");
        radioF14.setType(FormType.TYPE_RADIO);
        radioF14.setSpancount(3);
        List<RadioStringBean> radioF14list = new ArrayList<>();
        radioF14list.add(new RadioStringBean(1, "卫生厕所", data.getWashroom() == 1, false));
        radioF14list.add(new RadioStringBean(3, "马桶", data.getWashroom() == 2, false));
        radioF14list.add(new RadioStringBean(4, "露天粪坑", data.getWashroom() == 3, false));
        radioF14list.add(new RadioStringBean(2, "一/二格粪池式", data.getWashroom() == 4, false));
        radioF14list.add(new RadioStringBean(5, "简易", data.getWashroom() == 5, false));
        radioF14.setRadioData(radioF14list);
        form.add(radioF14);

        RadioForm radioF15 = new RadioForm();
        radioF15.setId(FormType.ResidentFormID.RESIDENT_ID_MORE_ANIMAL_PEN);
        radioF15.setTitle("禽兽栏");
        radioF15.setType(FormType.TYPE_RADIO);
        radioF15.setSpancount(2);
        List<RadioStringBean> radioF15list = new ArrayList<>();
        radioF15list.add(new RadioStringBean(1, "无", data.getBeast() == 1, false));
        radioF15list.add(new RadioStringBean(2, "单设", data.getBeast() == 2, false));
        radioF15list.add(new RadioStringBean(3, "室内", data.getBeast() == 3, false));
        radioF15list.add(new RadioStringBean(4, "室外", data.getBeast() == 4, false));
        radioF15.setRadioData(radioF15list);
        form.add(radioF15);

        getDoctorAndUser(form, data);

        return form;
    }

    private static void getFamilyHistory(List<CheckBoxBean> cbFdata) {
        cbFdata.add(new CheckBoxBean("无", false));
        cbFdata.add(new CheckBoxBean("高血压", false));
        cbFdata.add(new CheckBoxBean("糖尿病", false));
        cbFdata.add(new CheckBoxBean("冠心病", false));
        cbFdata.add(new CheckBoxBean("慢性阻塞性肺疾病", false));
        cbFdata.add(new CheckBoxBean("恶性肿瘤", false));
        cbFdata.add(new CheckBoxBean("脑卒中", false));
        cbFdata.add(new CheckBoxBean("严重精神障碍", false));
        cbFdata.add(new CheckBoxBean("结核病", false));
        cbFdata.add(new CheckBoxBean("肝炎", false));
        cbFdata.add(new CheckBoxBean("先天畸形", false));
        cbFdata.add(new CheckBoxBean("其他", false));
    }
}
