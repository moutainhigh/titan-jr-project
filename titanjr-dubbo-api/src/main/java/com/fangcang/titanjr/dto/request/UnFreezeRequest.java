package com.fangcang.titanjr.dto.request;

import java.io.Serializable;
import java.util.Date;

import com.fangcang.titanjr.common.util.DateUtil;

public class UnFreezeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date unFreezeDate ;
	
	private int offset;
	
	private int rows;

	public Date getUnFreezeDate() {
		return unFreezeDate;
	}

	public void setUnFreezeDate(Date unFreezeDate) {
		this.unFreezeDate = unFreezeDate;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
}
