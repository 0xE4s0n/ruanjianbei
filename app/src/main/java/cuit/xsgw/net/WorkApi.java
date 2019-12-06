package cuit.xsgw.net;

import com.android.zxb.engine.net.entity.BaseResponse;
import com.android.zxb.engine.net.frame.Api;
import com.android.zxb.engine.net.frame.ApiWrapper;

import java.util.List;

import cuit.xsgw.base.account.AccountManager;
import cuit.xsgw.net.req.DiabeteQuest;
import cuit.xsgw.net.req.HighBloodQuest;
import cuit.xsgw.net.req.LoginRequest;
import cuit.xsgw.net.req.MedicalUserQuest;
import cuit.xsgw.net.res.AreaData;
import cuit.xsgw.net.res.CNMediQuestion;
import cuit.xsgw.net.res.ListData;
import cuit.xsgw.net.res.LoginResult;
import cuit.xsgw.net.res.MedicalUser;
import rx.Subscription;
import rx.functions.Action1;


public class WorkApi {

    private static WorkApi sWorkApi;

    public static WorkApi get() {
        if (sWorkApi == null) {
            sWorkApi = new WorkApi();
        }
        return sWorkApi;
    }

    /**
     * 登录
     *
     * @param request
     * @param action
     * @return
     */
    public Subscription login(LoginRequest request, Action1<BaseResponse<LoginResult>> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .login(request),
                BaseResponse.class,
                action);
    }

    /**
     * 获取 中医体质问卷表
     *
     * @param action
     * @return
     */
    public Subscription requestCnQuestions(int page, Action1<BaseResponse<List<CNMediQuestion>>> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .requestCnQuestions(AccountManager.get().getUser().getToken(), page),
                BaseResponse.class,
                action);
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @param action
     * @return
     */
    public Subscription requestGetUsers(String name, String idcard, int page, Action1<BaseResponse<ListData<MedicalUser>>> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .requestGetUsers(AccountManager.get().getUser().getToken(), name, idcard, page),
                BaseResponse.class,
                action);
    }

    /**
     * 发布 修改 2型糖尿病随访记录表
     *
     * @param request
     * @param action
     * @return
     */
    public Subscription requestDiabeteRecord(DiabeteQuest request, Action1<BaseResponse> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .requestDiabeteRecord(AccountManager.get().getUser().getToken(), request),
                BaseResponse.class,
                action);
    }

    /**
     * 发布 修改 高血压随访记录表
     *
     * @param request
     * @param action
     * @return
     */
    public Subscription requestBloodRecord(HighBloodQuest request, Action1<BaseResponse> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .requestBloodRecord(AccountManager.get().getUser().getToken(), request),
                BaseResponse.class,
                action);
    }

    /**
     * 新建 修改 居民用户信息
     *
     * @param request
     * @param action
     * @return
     */
    public Subscription requestMedicalUserInfo(MedicalUserQuest request, Action1<BaseResponse> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .requestMedicalUserInfo(AccountManager.get().getUser().getToken(), request),
                BaseResponse.class,
                action);
    }

    /**
     * 获取地区列表
     *
     * @param areaCode
     * @param action
     * @return
     */
    public Subscription requestAreaList(String areaCode, Action1<BaseResponse<List<AreaData>>> action) {
        return ApiWrapper.wrap(
                Api.get().getApi(UserApi.class)
                        .requestAreaList(AccountManager.get().getUser().getToken(), areaCode),
                BaseResponse.class,
                action);
    }
}
