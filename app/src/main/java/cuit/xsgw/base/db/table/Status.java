package cuit.xsgw.base.db.table;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * 状态表.
 * <p>
 * 该表为了保存系统的一些信息:上次登录版本号/上次是否登录/上次登录账户id等.
 * <p>
 * 可以根据版本号判断是否启动引导页面,根据上次是否登录标记确定是否需要默认登录等.
 */
@Entity(nameInDb = "gongweiOS_status")
public class Status {

    @Id(autoincrement = true)
    private Long id;//默认自增id

    @NotNull
    @Property(nameInDb = "version")
    private String version;

    @NotNull
    @Property(nameInDb = "status")
    private int status;

    @Property(nameInDb = "accountType")
    private String accountType;//登录的账号类型

    @Property(nameInDb = "account_id")
    private String accountId;

    @Generated(hash = 1243589302)
    public Status(Long id, @NotNull String version, int status, String accountType,
                  String accountId) {
        this.id = id;
        this.version = version;
        this.status = status;
        this.accountType = accountType;
        this.accountId = accountId;
    }

    @Generated(hash = 1855832530)
    public Status() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
