package com.fangcang.titanjr.service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.request.HelpRequest;
import com.fangcang.titanjr.dto.request.HelpTypeRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpTypeRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpWordRequest;
import com.fangcang.titanjr.dto.response.QueryHelpDetailResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpTypeResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpWordResponse;

/**
 * 帮助
 * @author luoqinglong
 * @2016年9月22日
 */
public interface TitanHelpService {
	/**
	 * 增加帮助
	 * @param helpRequest
	 * @return
	 */
	BaseResponseDTO addHelp(HelpRequest helpRequest) throws GlobalServiceException;
	/***
	 * 修改帮助
	 * @param helpRequest
	 * @return
	 */
	BaseResponseDTO updateHelp(HelpRequest helpRequest) throws GlobalServiceException;
	/**
	 * 新增类别
	 * @param helpTypeRequest
	 * @return
	 */
	BaseResponseDTO addHelpType(HelpTypeRequest helpTypeRequest) throws GlobalServiceException;
	/**
	 * 修改类别
	 * @param helpTypeRequest
	 * @return
	 */
	BaseResponseDTO updateHelpType(HelpTypeRequest helpTypeRequest) throws GlobalServiceException;
	
	/**
	 * 关键查询，标题和内容都查
	 * @param queryPageHelpWordRequest
	 * @return
	 */
	QueryPageHelpWordResponse queryPageHelpWord(QueryPageHelpWordRequest queryPageHelpWordRequest);
	/***
	 * 帮助分页查询
	 * @param helpRequest
	 * @return
	 */
	QueryPageHelpResponse queryPageHelp(HelpRequest helpRequest);
	
	/**
	 * 帮助类别分页查询
	 * @param queryPageHelpTypeRequest
	 * @return
	 */
	QueryPageHelpTypeResponse queryPageHelpType(QueryPageHelpTypeRequest queryPageHelpTypeRequest);
	/**
	 * 帮助详情
	 * @param helpRequest
	 * @return
	 */
	QueryHelpDetailResponse queryHelpDetail(HelpRequest helpRequest);
	
	
	
}
