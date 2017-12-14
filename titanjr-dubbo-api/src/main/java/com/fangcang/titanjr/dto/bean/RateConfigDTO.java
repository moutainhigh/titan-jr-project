package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class RateConfigDTO  implements Serializable {
    /** 
	 * 
	 */
	private static final long serialVersionUID = 1520575388832272899L;
	private Integer rateConfigId;
    private String userId;
	private String deskId;
	private String usedfor;
    private Integer rateType;
    private Float standRate;
    private Float executionRate;
    private String description;
	private Float minfee;
	private Float maxfee;
    private Date expiration;

    public Integer getRateConfigId() {
        return rateConfigId;
    }

    public void setRateConfigId(Integer rateConfigId) {
        this.rateConfigId = rateConfigId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getUsedfor() {
		return usedfor;
	}

	public void setUsedfor(String usedfor) {
		this.usedfor = usedfor;
	}

	public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Float getStandRate() {
        return standRate;
    }

    public void setStandRate(Float standRate) {
        this.standRate = standRate;
    }

    public Float getExecutionRate() {
        return executionRate;
    }

    public void setExecutionRate(Float executionRate) {
        this.executionRate = executionRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Float getMinfee() {
		return minfee;
	}

	public void setMinfee(Float minfee) {
		this.minfee = minfee;
	}

	public Float getMaxfee() {
		return maxfee;
	}

	public void setMaxfee(Float maxfee) {
		this.maxfee = maxfee;
	}

	public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
