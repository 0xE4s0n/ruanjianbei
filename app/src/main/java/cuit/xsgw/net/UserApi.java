package cuit.xsgw.net;

import com.android.zxb.engine.net.entity.BaseResponse;

import java.util.List;

import cuit.xsgw.net.req.DiabeteQuest;
import cuit.xsgw.net.req.HighBloodQuest;
import cuit.xsgw.net.req.LoginRequest;
import cuit.xsgw.net.req.MedicalUserQuest;
import cuit.xsgw.net.res.AreaData;
import cuit.xsgw.net.res.CNMediQuestion;
import cuit.xsgw.net.res.ListData;
import cuit.xsgw.net.res.LoginResult;
import cuit.xsgw.net.res.MedicalUser;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface UserApi {

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @POST("app/login")
    Observable<BaseResponse<LoginResult>> login(@Body LoginRequest request);

    /**
     * 获取 中医体质问卷表
     *
     * @param authorizationKey
     * @return
     */
    @GET("app/chart/questions")
    Observable<BaseResponse<List<CNMediQuestion>>> requestCnQuestions(@Header("authorization") String authorizationKey,
                                                                      @Query("page") int page);

    /**
     * 获取用户列表
     *
     * @param authorizationKey
     * @param name
     * @param idCard
     * @param page
     * @return
     */
    @GET("app/users/list")
    Observable<BaseResponse<ListData<MedicalUser>>> requestGetUsers(@Header("authorization") String authorizationKey,
                                                                    @Query("name") String name,
                                                                    @Query("idCard") String idCard,
                                                                    @Query("page") int page);

    /**
     * 发布 修改 2型糖尿病随访记录表
     *
     * @param authorizationKey
     * @param request
     * @return
     */
    @POST("app/diabetes/new")
    Observable<BaseResponse> requestDiabeteRecord(@Header("authorization") String authorizationKey,
                                                  @Body DiabeteQuest request);

    /**
     * 发布 修改 高血压随访记录表
     *
     * @param authorizationKey
     * @param request
     * @return
     */
    @POST("app/blood/new")
    Observable<BaseResponse> requestBloodRecord(@Header("authorization") String authorizationKey,
                                                @Body HighBloodQuest request);

    /**
     * 新建 修改 居民用户信息
     *
     * @param authorizationKey
     * @param request
     * @return
     */
    @POST("app/user/info")
    Observable<BaseResponse> requestMedicalUserInfo(@Header("authorization") String authorizationKey,
                                                    @Body MedicalUserQuest request);

    /**
     * 获取地区列表
     *
     * @param authorizationKey
     * @param areaCode
     * @return
     */
    @GET("app/area/list")
    Observable<BaseResponse<List<AreaData>>> requestAreaList(@Header("authorization") String authorizationKey,
                                                             @Query("areaCode") String areaCode);
}
