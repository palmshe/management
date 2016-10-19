package com.genius.shiro.bean;

public class BaseQry {
    
    private Long beginIndex;//开始索引
    private Long resultSize;//条数
    
    public Long getBeginIndex() {
        return beginIndex;
    }
    public void setBeginIndex(Long beginIndex) {
        this.beginIndex = beginIndex;
    }
    public Long getResultSize() {
        return resultSize;
    }
    public void setResultSize(Long resultSize) {
        this.resultSize = resultSize;
    }
}
