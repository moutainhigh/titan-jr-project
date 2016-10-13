package com.fangcang.titanjr.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanHelpDao;
import com.fangcang.titanjr.dao.TitanHelpTypeDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.HelpWordDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpTypeDTO;
import com.fangcang.titanjr.dto.request.HelpRequest;
import com.fangcang.titanjr.dto.request.HelpTypeRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpTypeRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpWordRequest;
import com.fangcang.titanjr.dto.response.QueryHelpDetailResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpTypeResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpWordResponse;
import com.fangcang.titanjr.entity.TitanHelp;
import com.fangcang.titanjr.entity.TitanHelpType;
import com.fangcang.titanjr.entity.parameter.TitanHelpParam;
import com.fangcang.titanjr.entity.parameter.TitanHelpTypeParam;
import com.fangcang.titanjr.service.TitanHelpService;

/**
 * 帮助
 * @author luoqinglong
 * @2016年9月23日
 */
@Service("titanHelpService")
public class TitanHelpServiceImpl implements TitanHelpService {
	private static final Logger LOGGER = Logger.getLogger(TitanHelpServiceImpl.class);
	@Resource
	private TitanHelpDao titanHelpDao;
	@Resource
	private TitanHelpTypeDao titanHelpTypeDao;
	
	
	
	@Override
	public BaseResponseDTO addHelp(HelpRequest helpRequest) throws GlobalServiceException {
		BaseResponseDTO response = new BaseResponseDTO();
		int orderno = 1;
		if(helpRequest.getOrderNo()!=null){
			orderno = helpRequest.getOrderNo();
		}else{
			//当前分类中帮组最大的排序号
			TitanHelpParam condition = new TitanHelpParam();
			condition.setHelpType(helpRequest.getHelpType());
			PaginationSupport<TitanHelpDTO> paginationSupport = new PaginationSupport<TitanHelpDTO>();
			paginationSupport.setCurrentPage(1);
			paginationSupport.setPageSize(1);
			paginationSupport.setOrderBy(" orderno desc ");
			paginationSupport = titanHelpDao.selectForPage(condition, paginationSupport);
			if(paginationSupport.getItemList().size()>0){
				orderno = paginationSupport.getItemList().get(0).getOrderNo()+1;
			}
		}
		
		TitanHelp entity = new TitanHelp();
		entity.setHelpTitle(helpRequest.getHelpTitle());
		entity.setHelpContent(helpRequest.getHelpContent());
		entity.setHelpType(helpRequest.getHelpType());
		entity.setIsShow(helpRequest.getIsShow());
		entity.setOrderNo(orderno);
		entity.setCreator(helpRequest.getOperator());
		entity.setCreateTime(new Date());
		try {
			titanHelpDao.insert(entity);
			response.putSuccess();
		} catch (Exception e) {
			LOGGER.error("addHelp, helpRequest:"+Tools.gsonToString(helpRequest), e);
			throw new GlobalServiceException("新增帮助失败,param:"+Tools.gsonToString(helpRequest), e);
		}
		
		return response;
	}

	@Override
	public BaseResponseDTO updateHelp(HelpRequest helpRequest) throws GlobalServiceException {
		BaseResponseDTO response = new BaseResponseDTO();
		TitanHelp entity = new TitanHelp();
		entity.setHelpId(helpRequest.getHelpId());
		entity.setHelpTitle(helpRequest.getHelpTitle());
		entity.setHelpContent(helpRequest.getHelpContent());
		entity.setHelpType(helpRequest.getHelpType());
		entity.setIsShow(helpRequest.getIsShow());
		entity.setOrderNo(helpRequest.getOrderNo());
		entity.setModifior(helpRequest.getOperator());
		entity.setModifyTime(new Date());
		try {
			titanHelpDao.update(entity);
			response.putSuccess();
		} catch (Exception e) {
			LOGGER.error("addHelpType, helpTypeRequest:"+Tools.gsonToString(helpRequest), e);
			throw new GlobalServiceException("修改帮助失败,param:"+Tools.gsonToString(helpRequest), e);
		}
		return response;
	}

	@Override
	public BaseResponseDTO addHelpType(HelpTypeRequest helpTypeRequest) throws GlobalServiceException {
		BaseResponseDTO response = new BaseResponseDTO();
		TitanHelpType entity = new TitanHelpType();
		entity.setIconimg(helpTypeRequest.getIconimg());
		entity.setName(helpTypeRequest.getName());
		entity.setOrderNo(helpTypeRequest.getOrderNo());
		entity.setIsShow(helpTypeRequest.getIsShow());
		entity.setModifior(helpTypeRequest.getOperator());
		entity.setModifyTime(new Date());
		try {
			titanHelpTypeDao.insert(entity);
			response.putSuccess();
		} catch (Exception e) {
			LOGGER.error("addHelpType, helpTypeRequest:"+Tools.gsonToString(helpTypeRequest), e);
			throw new GlobalServiceException("添加帮助类别失败,param:"+Tools.gsonToString(helpTypeRequest), e);
		}
		return response;
	}

	@Override
	public BaseResponseDTO updateHelpType(HelpTypeRequest helpTypeRequest) throws GlobalServiceException {
		BaseResponseDTO response = new BaseResponseDTO();
		TitanHelpType entity = new TitanHelpType();
		entity.setHelpType(helpTypeRequest.getHelpType());
		entity.setIconimg(helpTypeRequest.getIconimg());
		entity.setName(helpTypeRequest.getName());
		entity.setOrderNo(helpTypeRequest.getOrderNo());
		entity.setIsShow(helpTypeRequest.getIsShow());
		entity.setModifior(helpTypeRequest.getOperator());
		entity.setModifyTime(new Date());
		
		try {
			titanHelpTypeDao.update(entity);
			response.putSuccess();
		} catch (Exception e) {
			LOGGER.error("response.putSuccess();, helpTypeRequest:"+Tools.gsonToString(helpTypeRequest), e);
			throw new GlobalServiceException("修改帮助类别失败,param:"+Tools.gsonToString(helpTypeRequest), e);
		}
		return response;
	}

	@Override
	public QueryPageHelpWordResponse queryPageHelpWord(
			QueryPageHelpWordRequest queryPageHelpWordRequest) {
		QueryPageHelpWordResponse wordResponse = new QueryPageHelpWordResponse();
		PaginationSupport<HelpWordDTO> paginationSupport = new PaginationSupport<HelpWordDTO>();
		paginationSupport.setCurrentPage(queryPageHelpWordRequest.getCurrentPage());
		paginationSupport.setPageSize(queryPageHelpWordRequest.getPageSize());
		TitanHelpParam condition = new TitanHelpParam();
		condition.setHelpTitle(queryPageHelpWordRequest.getWord());
		condition.setHelpContent(queryPageHelpWordRequest.getWord());
		condition.setIsShow(queryPageHelpWordRequest.getIsShow());
		paginationSupport = titanHelpDao.selectForPageSearch(condition, paginationSupport);
		
		wordResponse.setPage(paginationSupport);
		wordResponse.putSuccess();
		return wordResponse;
	}

	@Override
	public QueryPageHelpResponse queryPageHelp(HelpRequest helpRequest) {
		
		QueryPageHelpResponse helpResponse = new QueryPageHelpResponse();
		PaginationSupport<TitanHelpDTO> paginationSupport = new PaginationSupport<TitanHelpDTO>();
		paginationSupport.setCurrentPage(helpRequest.getCurrentPage());
		paginationSupport.setPageSize(helpRequest.getPageSize());
		paginationSupport.setOrderBy(" orderno asc ");
		TitanHelpParam condition = new TitanHelpParam();
		condition.setHelpType(helpRequest.getHelpType());
		condition.setIsShow(helpRequest.getIsShow());
		paginationSupport = titanHelpDao.selectForPage(condition, paginationSupport);
		helpResponse.setPage(paginationSupport);
		helpResponse.putSuccess();
		
		return helpResponse;
	}

	@Override
	public QueryPageHelpTypeResponse queryPageHelpType(
			QueryPageHelpTypeRequest queryPageHelpTypeRequest) {
		QueryPageHelpTypeResponse helpTypeResponse = new QueryPageHelpTypeResponse();
		PaginationSupport<TitanHelpTypeDTO> paginationSupport = new PaginationSupport<TitanHelpTypeDTO>();
		paginationSupport.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		paginationSupport.setOrderBy(" orderno asc ");
		TitanHelpTypeParam condition = new TitanHelpTypeParam();
		condition.setIsShow(queryPageHelpTypeRequest.getIsShow());
		
		paginationSupport = titanHelpTypeDao.selectForPage(condition, paginationSupport);
		helpTypeResponse.setPage(paginationSupport);
		helpTypeResponse.putSuccess();
		return helpTypeResponse;
	}

	@Override
	public QueryHelpDetailResponse queryHelpDetail(HelpRequest helpRequest) {
		
		QueryHelpDetailResponse detailResponse = new QueryHelpDetailResponse();
		PaginationSupport<TitanHelpDTO> paginationSupport = new PaginationSupport<TitanHelpDTO>();
		paginationSupport.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		TitanHelpParam condition = new TitanHelpParam();
		condition.setHelpId(helpRequest.getHelpId());
		paginationSupport = titanHelpDao.selectForPage(condition, paginationSupport);
		
		TitanHelpDTO titanHelpDTO = null;
		if(paginationSupport.getItemList().size()>0){
			titanHelpDTO = paginationSupport.getItemList().get(0);
		}
		detailResponse.setTitanHelpDTO(titanHelpDTO);
		detailResponse.putSuccess();
		return detailResponse;
	}

}
