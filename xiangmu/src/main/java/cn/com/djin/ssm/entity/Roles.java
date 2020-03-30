package cn.com.djin.ssm.entity;

import java.util.Date;

public class Roles {
    private Integer id;

    private String roleName;

    private Date createDate;

    private String status;

    private String flag;

    //角色拥有的一级权限的字符串
    private String authNames;

    public String getAuthNames() {
        return authNames;
    }

    public void setAuthNames(String authNames) {
        this.authNames = authNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}