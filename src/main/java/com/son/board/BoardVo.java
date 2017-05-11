package com.son.board;

/**
 * Created by kiost on 2017-05-11.
 */
public class BoardVo {
    private String brdNo;
    private String brdTitle;
    private String brdWriter;
    private String brdMemo;
    private String brdDate;
    private String brdHit;
    private String brdDeleteFlag;

    public BoardVo() {
    }

    public String getBrdNo() {
        return brdNo;
    }

    public void setBrdNo(String brdNo) {
        this.brdNo = brdNo;
    }

    public String getBrdTitle() {
        return brdTitle;
    }

    public void setBrdTitle(String brdTitle) {
        this.brdTitle = brdTitle;
    }

    public String getBrdWriter() {
        return brdWriter;
    }

    public void setBrdWriter(String brdWriter) {
        this.brdWriter = brdWriter;
    }

    public String getBrdMemo() {
        return brdMemo;
    }

    public void setBrdMemo(String brdMemo) {
        this.brdMemo = brdMemo;
    }

    public String getBrdDate() {
        return brdDate;
    }

    public void setBrdDate(String brdDate) {
        this.brdDate = brdDate;
    }

    public String getBrdHit() {
        return brdHit;
    }

    public void setBrdHit(String brdHit) {
        this.brdHit = brdHit;
    }

    public String getBrdDeleteFlag() {
        return brdDeleteFlag;
    }

    public void setBrdDeleteFlag(String brdDeleteFlag) {
        this.brdDeleteFlag = brdDeleteFlag;
    }
}
