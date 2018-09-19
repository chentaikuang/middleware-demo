package com.xiaochen.middleware.activemq;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMsg implements Serializable {

    private String curDateStr;
    private Date curDate;
    private int indexNo;

    private final Integer nm = null;

    public TestMsg(int indexNo) {
        Date curDate = new Date();
        this.curDate = curDate;
        this.curDateStr = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(curDate);
        this.indexNo = indexNo;
    }

    public String getCurDateStr() {
        return curDateStr;
    }

    public void setCurDateStr(String curDateStr) {
        this.curDateStr = curDateStr;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    @Override
    public String toString() {
        return "TestMsg{" +
                "curDateStr='" + curDateStr + '\'' +
                ", curDate=" + curDate +
                ", indexNo=" + indexNo +
                '}';
    }
}
