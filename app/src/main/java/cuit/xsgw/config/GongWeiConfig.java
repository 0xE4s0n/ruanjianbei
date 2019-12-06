package cuit.xsgw.config;

import com.android.zxb.engine.config.AppConfig;

/**
 * 配置类
 */

public interface GongWeiConfig extends AppConfig {

    String[] tizhiNames = {"平和质", "气虚质", "阳虚质", "气郁质", "阴虚质", "痰湿质", "湿热质", "血瘀质", "特禀质"};
    String[] suggestNames = {"情志调摄", "饮食调养", "起居调摄", "运动保健", "穴位保健", "其他"};
    String[] minZuData = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族"
            , "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "僳僳族", "佤族", "畲族", "高山族"
            , "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族"
            , "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族"
            , "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族"
            , "珞巴族", "基诺族"};

    /**
     * 事件通知总线TAG
     */
    interface EventBusTag extends AppConfig.EventBusTag {
    }

    interface LocationConfig {
        String LOCATION_CHANGED = "location_change";
    }

    interface MediaConfig {
        int UPDATE_PIC_GRIDVIEW = 995;
        int UPDATE_VID_GRIDVIEW = 996;
        int UPDATE_PIC_DATA = 997;
    }

    interface WorkConfig {
        int CN_MEDICINE = 998;
        int CHOOSE_USER = 999;
    }
}
