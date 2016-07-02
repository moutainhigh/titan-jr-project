package com.fangcang.titanjr.dto;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/3/30.
 */
public class BaseRequestDTO implements Serializable {
    /**
     * 操作人，可为空，一般需要有值
     */
    private String operator;

    /**
     * 操作时间，可为空
     */
    private String operateTime;
    
    private int currentPage = 1;
    private int pageSize = 15;
    private String orderBy;
    
    
    public int getCurrentPage() {
    	if(currentPage<=0){
    		currentPage = 1;
    	}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize>100){
			this.pageSize=20;
		}
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
