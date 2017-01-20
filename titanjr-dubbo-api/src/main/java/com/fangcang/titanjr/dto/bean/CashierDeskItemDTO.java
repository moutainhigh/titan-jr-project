package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class CashierDeskItemDTO implements Serializable,Comparable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer itemId;

    private String itemName;

    private Integer itemType;

    private List<CashierItemBankDTO> cashierItemBankDTOList;

    private List<RateConfigDTO> rateConfigDTOList;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public List<CashierItemBankDTO> getCashierItemBankDTOList() {
        return cashierItemBankDTOList;
    }

    public void setCashierItemBankDTOList(List<CashierItemBankDTO> cashierItemBankDTOList) {
        this.cashierItemBankDTOList = cashierItemBankDTOList;
    }
    public List<RateConfigDTO> getRateConfigDTOList() {
        return rateConfigDTOList;
    }

    public void setRateConfigDTOList(List<RateConfigDTO> rateConfigDTOList) {
        this.rateConfigDTOList = rateConfigDTOList;
    }

	@Override
	public int compareTo(Object o) {
		CashierDeskItemDTO item = (CashierDeskItemDTO)o;
		Integer thisType = this.getItemType();
		Integer itemType = item.getItemType();
		return thisType.compareTo(itemType);
	}
}
