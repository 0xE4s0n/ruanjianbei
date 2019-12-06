package cuit.xsgw.bean;

public interface FormType {
    String TYPE_TAB = "type_tab";//单元格title
    String TYPE_DATE = "type_date";//选择时间类型
    String TYPE_CHOOSE_STRING = "type_choose_string";//选择字符串类型
    String TYPE_ZBAR = "type_zbar";//条形码/二维码扫描类型
    String TYPE_PIC = "type_pic";//选择图片类型
    String TYPE_CHECKBOX = "type_checkbox";//多选类型
    String TYPE_CHECKBOX_OTHER = "type_checkbox_other";//多选类型 other
    String TYPE_CHECKBOX_ONE_OTHER = "type_checkbox_one_other";//多选类型 one + other
    String TYPE_CHECKBOX_DISEASE_HISTORY = "type_checkbox_disease_history";//仅针对疾病史类型
    String TYPE_RADIO = "type_radio";//单选类型
    String TYPE_MORE_RADIO = "type_more_radio";//多个单选类型
    String TYPE_SINGLE_EDIT = "type_single_edit";//单输入框类型
    String TYPE_SINGLE_PORT_EDIT = "type_single_port_edit";//下排单输入框类型
    String TYPE_MORE_EDIT = "type_more_edit";//多输入框类型
    String TYPE_NEW_ITEM = "type_new_item";//新增条目型
    String TYPE_CHOOSE_AREA = "type_choose_area";//地区选择型

    interface FormID {
        int BASE_ID_RECORE_METHOD = 1;//随访方式
        int BASE_ID_RECORE_SYMPTOM = 2;//症状
        int BASE_ID_RECORE_BLOOD = 3;//血压
        int BASE_ID_RECORE_HEIGHT = 4;//身高
        int BASE_ID_RECORE_WEIGHT = 5;//体重
        int BASE_ID_RECORE_BIM = 6;//体质指数
        int BASE_ID_RECORE_OTHER = 7;//其他体征
        int BASE_ID_RECORE_SMOKE = 8;//日吸烟量
        int BASE_ID_RECORE_WINE = 9;//日饮酒量
        int BASE_ID_RECORE_SPORT = 10;//运动情况
        int BASE_ID_RECORE_MENTAL = 11;//心理调整
        int BASE_ID_RECORE_FOLLOW_DOC = 12;//遵医行为
        int BASE_ID_RECORE_ASSIST = 13;//辅助检查
        int BASE_ID_RECORE_MEDICINES = 14;//药品
        int BASE_ID_RECORE_RELY = 15;//服药依从性
        int BASE_ID_RECORE_BADNESS = 16;//药物不良反应
        int BASE_ID_RECORE_CLASSIFY = 17;//此次随访分类
        int BASE_ID_RECORE_TRANSFER = 18;//转诊
        int BASE_ID_RECORE_NEXT_TIME = 19;//下次随访日期
        int BASE_ID_RECORE_DOCTOR = 20;//随访医生
        int BASE_ID_CUSTOMER_SIGNATURE = 21;//病人签名
        int BASE_ID_RECORE_TIME = 22;//随访日期

        int DIABETES_ID_RECORE_FOOT_PUL = 23;//足背动脉搏动
        int DIABETES_ID_RECORE_FOOD = 24;//主食
        int DIABETES_ID_RECORE_LIMOSIS = 25;//空腹血糖
        int DIABETES_ID_RECORE_SUGAR_RED = 26;//糖化血红蛋白
        int DIABETES_ID_RECORE_CHECK_TIME = 27;//检查日期
        int DIABETES_ID_RECORE_LOW_SUGAR = 28;//低血糖反应
        int DIABETES_ID_RECORE_INSULIN = 29;//胰岛素

        int BLOOD_ID_RECORE_HEART = 30;//心率
        int BLOOD_ID_RECORE_SALT = 31;//摄盐情况
    }

    interface ResidentFormID {
        int RESIDENT_ID_BASE_TAB = 32;//基本信息

        int RESIDENT_ID_BASE_IDCARD = 33;//身份证
        int RESIDENT_ID_BASE_PICTURE = 34;//照片
        int RESIDENT_ID_BASE_NAME = 35;//姓名
        int RESIDENT_ID_BASE_SEX = 36;//性别
        int RESIDENT_ID_BASE_BIRTHDAY = 37;//出生日期
        int RESIDENT_ID_BASE_AGE = 38;//年龄
        int RESIDENT_ID_BASE_NATION = 39;//民族
        int RESIDENT_ID_BASE_STAY_TYPE = 40;//常驻类型
        int RESIDENT_ID_BASE_PHONE = 41;//电话
        int RESIDENT_ID_BASE_COMMUNITY = 42;//村(社区)
        int RESIDENT_ID_BASE_STAY_NOW = 43;//现住址
        int RESIDENT_ID_BASE_DOMICILE = 44;//户籍地址

        int RESIDENT_ID_MORE_TAB = 45;//更多信息(以下选填)
        int RESIDENT_ID_MORE_WORK_UNIT = 46;//工作单位
        int RESIDENT_ID_MORE_CONTACT_NAME = 47;//联系人姓名
        int RESIDENT_ID_MORE_CONTACT_PHONE = 48;//联系人电话
        int RESIDENT_ID_MORE_BLOOD_TYPE = 49;//血型
        int RESIDENT_ID_MORE_BLOOD_RH_TYPE = 50;//血型(RH)
        int RESIDENT_ID_MORE_EDUCATION = 51;//文化程度
        int RESIDENT_ID_MORE_WORK_TYPE = 52;//职业
        int RESIDENT_ID_MORE_MARITAL_STATUS = 53;//婚姻状况
        int RESIDENT_ID_MORE_MEDICAL_PAY_TYPE = 54;//医疗费用支付方式
        int RESIDENT_ID_MORE_ALLERGY_HISTORY = 55;//药物过敏史
        int RESIDENT_ID_MORE_EXPOSURE_HISTORY = 56;//暴露史

        int RESIDENT_ID_MORE_TAB_PAST_HISTORY = 57;//以往疾病史
        int RESIDENT_ID_MORE_HISTORY_MEDICAL = 58;//疾病史
        int RESIDENT_ID_MORE_HISTORY_OPERATION = 59;//手术
        int RESIDENT_ID_MORE_HISTORY_TRAUMA = 60;//外伤
        int RESIDENT_ID_MORE_HISTORY_TRANSFUSION = 61;//输血

        int RESIDENT_ID_MORE_TAB_FAMILY_HISTORY = 62;//家族疾病史
        int RESIDENT_ID_MORE_HISTORY_FATHER = 63;//父亲
        int RESIDENT_ID_MORE_HISTORY_MOTHER = 64;//母亲
        int RESIDENT_ID_MORE_HISTORY_BROTHERS_SISTERS = 65;//兄弟姐妹
        int RESIDENT_ID_MORE_HISTORY_CHILREN = 66;//子女

        int RESIDENT_ID_MORE_TAB_OTHER = 67;//其他信息
        int RESIDENT_ID_MORE_GENETIC_HISTORY = 68;//遗传病史
        int RESIDENT_ID_MORE_DISABILITY_CONDITION = 69;//残疾情况
        int RESIDENT_ID_MORE_KITCHEN_DEVICE = 70;//厨房排风设施
        int RESIDENT_ID_MORE_FUEL_TYPE = 71;//燃料类型
        int RESIDENT_ID_MORE_DRINKING_WATER = 72;//饮水
        int RESIDENT_ID_MORE_TOILET = 73;//厕所
        int RESIDENT_ID_MORE_ANIMAL_PEN = 74;//禽兽栏
        int RESIDENT_ID_RECORE_DOCTOR = 75;//录表医生
        int RESIDENT_ID_CUSTOMER_SIGNATURE = 76;//客户签名
    }
}
