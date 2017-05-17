package com.son.reply;

/**
 * Created by kiost on 2017-05-17.
 */
public class ReplyVo {
    private String brdNo;
    private String reNo;
    private String reWriter;
    private String reDeleteFlag;
    private String reMemo;
    private String reDate;
    private String reParent;
    private String reDepth;
    private String reOrder;

    public ReplyVo() {
        /* default constructor */
    }

    public String getBrdNo() {
        return brdNo;
    }

    public void setBrdNo(String brdNo) {
        this.brdNo = brdNo;
    }

    public String getReNo() {
        return reNo;
    }

    public void setReNo(String reNo) {
        this.reNo = reNo;
    }

    public String getReWriter() {
        return reWriter;
    }

    public void setReWriter(String reWriter) {
        this.reWriter = reWriter;
    }

    public String getReDeleteFlag() {
        return reDeleteFlag;
    }

    public void setReDeleteFlag(String reDeleteFlag) {
        this.reDeleteFlag = reDeleteFlag;
    }

    public String getReMemo() {
        return reMemo;
    }

    public void setReMemo(String reMemo) {
        this.reMemo = reMemo;
    }

    public String getReDate() {
        return reDate;
    }

    public void setReDate(String reDate) {
        this.reDate = reDate;
    }

    public String getReParent() {
        return reParent;
    }

    public void setReParent(String reParent) {
        this.reParent = reParent;
    }

    public String getReDepth() {
        return reDepth;
    }

    public void setReDepth(String reDepth) {
        this.reDepth = reDepth;
    }

    public String getReOrder() {
        return reOrder;
    }

    public void setReOrder(String reOrder) {
        this.reOrder = reOrder;
    }
}
