package com.titanjr.fop.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum.UserType;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.dao.TitanMainOrgDao;
import com.titanjr.fop.dto.MainOrgDTO;
import com.titanjr.fop.dto.server.request.OptOrgRequest;
import com.titanjr.fop.dto.server.response.OptOrgResponse;
import com.titanjr.fop.dto.server.response.UpdateOrgReponse;
import com.titanjr.fop.entity.TitanMainOrg;
import com.titanjr.fop.enums.StatusIdMainOrgEnum;
import com.titanjr.fop.service.MainOrgService;

public class MainOrgServiceImpl implements MainOrgService {
	
	@Resource
	private TitanMainOrgDao mainOrgDao ;

	@Override
	public OptOrgResponse optOrg(OptOrgRequest request) {
		OptOrgResponse reponse = new OptOrgResponse();
		//通用检查
		String v = nullValidate(request);
		if(StringUtil.isValidString(v)){
			reponse.putParamError(v+"不能为空");
			return reponse;
		}
		//可行性(重复)检查
		MainOrgDTO mainOrgDTO = new MainOrgDTO();
		mainOrgDTO.setOrgCode(request.getUserid());
		List<TitanMainOrg> list = mainOrgDao.queryList(mainOrgDTO);
		if(CollectionUtils.isNotEmpty(list)){
			reponse.putParamError("该机构号已经注册["+request.getUserid()+"]");
			return reponse;
		}
		
		//开始注册
		TitanMainOrg entity= new TitanMainOrg();
		entity.setOrgCode(request.getUserid());
		entity.setOrgName(request.getUsername());
		entity.setProductId(request.getProductid());
		entity.setUserType(request.getUsertype());
		if(StringUtil.isValidString(request.getCertificatetype())){
			entity.setCertificatetype(Integer.parseInt(request.getCertificatetype()));
		}
		entity.setCertificateNumber(request.getCertificatenumber());
		entity.setBuslince(request.getBuslince());
		entity.setMobileTel(request.getMobiletel());
		entity.setConnect(request.getConnect());
		entity.setStatusId(StatusIdMainOrgEnum.NORMAL.getType());
		
		entity.setCreateTime(new Date());
		mainOrgDao.insert(entity);
		reponse.putSuccess();
		
		return reponse;
	}
	/**
	 * 新增空值检查
	 * @param request
	 * @return
	 */
	private String nullValidate(OptOrgRequest request){
		//公共校验
		if(!StringUtil.isValidString(request.getUserid())){
			return "userid";	
		}
		if(!StringUtil.isValidString(request.getUsername())){
			return "username";	
		}
		
		if(UserType.PERSONAL.getKey()==request.getUsertype()){//个人
			if(request.getCertificatetype()==null){
				return "certificatetype";
			}
			if(!StringUtil.isValidString(request.getCertificatenumber())){
				return "certificatenumber";	
			}
		}else if(UserType.ENTERPRISE.getKey()==request.getUsertype()){//企业
			if(!StringUtil.isValidString(request.getBuslince())){
				return "buslince";	
			}
		}
		return "";
	}
	
	
	@Override
	public UpdateOrgReponse updateOrg(OptOrgRequest request) {
		UpdateOrgReponse updateOrgReponse = new UpdateOrgReponse();
		
		if(!StringUtil.isValidString(request.getUserid())){
			updateOrgReponse.putParamError("userid不能为空");
			return updateOrgReponse;
		}
		TitanMainOrg entity = new TitanMainOrg();
		entity.setOrgCode(request.getUserid());
		if(StringUtil.isValidString(request.getCertificatetype())){
			entity.setCertificatetype(Integer.parseInt(request.getCertificatetype()));
		}
		entity.setCertificateNumber(request.getCertificatenumber());
		entity.setBuslince(request.getBuslince());
		entity.setConnect(request.getConnect());
		entity.setMobileTel(request.getMobiletel());
		entity.setOrgName(request.getUsername());
		entity.setStatusId(StatusIdMainOrgEnum.NORMAL.getType());
		
		mainOrgDao.update(entity);
		
		updateOrgReponse.putSuccess();
		return updateOrgReponse;
		
	}
	
	@Override
	public List<TitanMainOrg> queryOrg(OptOrgRequest request) {
		if(request==null){
			return null;
		}
		MainOrgDTO param = new MainOrgDTO();
		param.setOrgCode(request.getUserid());
		param.setOrgName(request.getUsername());
		param.setUserType(request.getUsertype());
		if(StringUtil.isValidString(request.getCertificatetype())){
			param.setCertificatetype(Integer.parseInt(request.getCertificatetype()));
		}
		param.setCertificateNumber(request.getCertificatenumber());
		
		return mainOrgDao.queryList(param);
	}

}
