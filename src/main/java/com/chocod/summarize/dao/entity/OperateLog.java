package com.chocod.summarize.dao.entity;

import java.util.Date;

public class OperateLog {
    private Long id;

    private String storeNo;

    private String operateType;

    private String operateAccount;

    private String operateEmployee;

    private String masterInfo;

    private String slaveInfo;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public String getOperateAccount() {
        return operateAccount;
    }

    public void setOperateAccount(String operateAccount) {
        this.operateAccount = operateAccount == null ? null : operateAccount.trim();
    }

    public String getOperateEmployee() {
        return operateEmployee;
    }

    public void setOperateEmployee(String operateEmployee) {
        this.operateEmployee = operateEmployee == null ? null : operateEmployee.trim();
    }

    public String getMasterInfo() {
        return masterInfo;
    }

    public void setMasterInfo(String masterInfo) {
        this.masterInfo = masterInfo == null ? null : masterInfo.trim();
    }

    public String getSlaveInfo() {
        return slaveInfo;
    }

    public void setSlaveInfo(String slaveInfo) {
        this.slaveInfo = slaveInfo == null ? null : slaveInfo.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}