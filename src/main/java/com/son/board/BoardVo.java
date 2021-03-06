package com.son.board;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private String fileCnt;
    private List<MultipartFile> uploadFiles;

    public BoardVo() {
        /* default constructor */
    }

    public List<MultipartFile> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<MultipartFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
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
        return brdMemo.replaceAll("(?i)<script", "&lt;script");
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

    public String getFileCnt() {
        return fileCnt;
    }

    public void setFileCnt(String fileCnt) {
        this.fileCnt = fileCnt;
    }
}
