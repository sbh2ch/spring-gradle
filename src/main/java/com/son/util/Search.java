package com.son.util;

/**
 * Created by kiost on 2017-05-17.
 */
public class Search extends Page {
    private String bgno;
    private String searchKeyword;
    private String searchType;
    private String searchTypeArr;

    public String getBgno() {
        return bgno;
    }

    public void setBgno(String bgno) {
        this.bgno = bgno;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String[] getSearchTypeArr() {
        return searchType.split(",");
    }

    public void setSearchTypeArr(String searchTypeArr) {
        this.searchTypeArr = searchTypeArr;
    }
}
