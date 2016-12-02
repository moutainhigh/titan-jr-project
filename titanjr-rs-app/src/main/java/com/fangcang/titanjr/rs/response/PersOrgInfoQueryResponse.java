package com.fangcang.titanjr.rs.response;

import com.Rop.api.domain.OpenAccountPerson;
import com.fangcang.titanjr.rs.dto.PersonOrg;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class PersOrgInfoQueryResponse extends BaseResponse {

    private List<OpenAccountPerson> personOrgList;

	public List<OpenAccountPerson> getPersonOrgList() {
		return personOrgList;
	}

	public void setPersonOrgList(List<OpenAccountPerson> personOrgList) {
		this.personOrgList = personOrgList;
	}

}
