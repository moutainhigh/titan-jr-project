package com.titanjr.fop.service;

import java.util.List;

import com.titanjr.fop.dto.server.request.OptOrgRequest;
import com.titanjr.fop.dto.server.response.OptOrgResponse;
import com.titanjr.fop.dto.server.response.UpdateOrgReponse;
import com.titanjr.fop.entity.TitanMainOrg;

/***
 * 机构
 * @author luoqinglong
 * @date 2018年1月26日
 */
public interface MainOrgService {
	/***
	 * 注册个人和企业机构
	 * @param request
	 * @return
	 */
	OptOrgResponse optOrg(OptOrgRequest request);
	
	/**
	 * 修改机构
	 * @param request
	 * @return
	 */
	UpdateOrgReponse updateOrg(OptOrgRequest request);
	
	/***
	 * 查询机构信息
	 * @param request
	 * @return
	 */
	List<TitanMainOrg> queryOrg(OptOrgRequest request);
	
	
}
