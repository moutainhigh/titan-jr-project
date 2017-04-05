package com.fangcang.titanjr.dto.bean;

import java.util.Date;

/**
 * 贷款包房贷规格
 * 
 * @author wengxitao
 *
 */
public class LoanRoomPackSpecBean extends LoanSpecBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hotleName;// 酒店名称

	private Date beginDate;// 包房开始时间

	private Date endDate;// 包房结束时间

	private int roomNights;// 包房数量(间夜)

	
	public String getHotleName() {
		return hotleName;
	}

	public void setHotleName(String hotleName) {
		this.hotleName = hotleName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getRoomNights() {
		return roomNights;
	}

	public void setRoomNights(int roomNights) {
		this.roomNights = roomNights;
	}

	
}
