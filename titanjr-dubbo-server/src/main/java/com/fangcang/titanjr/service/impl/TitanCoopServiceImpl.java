package com.fangcang.titanjr.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanCoopDao;
import com.fangcang.titanjr.dao.TitanSynOrgInfoDao;
import com.fangcang.titanjr.dto.bean.CoopDTO;
import com.fangcang.titanjr.dto.request.SynOrgInfoRequest;
import com.fangcang.titanjr.entity.TitanSynOrgInfo;
import com.fangcang.titanjr.entity.parameter.TitanCoopParam;
import com.fangcang.titanjr.service.TitanCoopService;

@Service("titanCoopService")
public class TitanCoopServiceImpl implements TitanCoopService {
	private static final Log log = LogFactory.getLog(TitanCoopServiceImpl.class);
	private static final long TEN_MINUTES = 30*60*1000L;
	private SynOrgInfoWatch synOrgInfoWatch = new SynOrgInfoWatch();
	
	@Resource
	private TitanSynOrgInfoDao synOrgInfoDao;
	
	@Resource
	private TitanCoopDao coopDao;
	
	
	
	
	@Override
	public void notifyCoopOrgInfo() {
		log.info("通知合作方机构信息方法TitanCoopService.notifyCoopOrgInfo()"+synOrgInfoWatch.toString());
		if(synOrgInfoWatch.isDoing()){
			log.info("通知合作方机构信息方法TitanCoopService.notifyCoopOrgInfo()正在执行，不允许重复执行,30分钟后或者等本次线程执行完后才可以执行。");
			return;
		}
		int pageSize = 10;
		int pageNo = 1;
		synOrgInfoWatch.startWatch();
		PaginationSupport<TitanSynOrgInfo> page = new PaginationSupport<TitanSynOrgInfo>();
		page.setCurrentPage(pageNo);
		page.setPageSize(pageSize);
		page.setOrderBy(" synid ");
		
		page = synOrgInfoDao.selectForPage(null, page);
		List<TitanSynOrgInfo> itemList = page.getItemList();
		List<Integer> deleteList = new ArrayList<Integer>();
		while (itemList.size()>0) {//循环查询
			log.info("notify cooperate the reg info ,the query pageNo is :"+pageNo);
			for(TitanSynOrgInfo item : itemList){
				TitanCoopParam titanCoopParam = new TitanCoopParam();
				titanCoopParam.setCoopType(item.getCoopType());
				Map<String, String> notifyParam = Tools.unserializable2Map(item.getKvparam());
				notifyParam.put("reqtime", String.valueOf((new Date()).getTime()));
				try {
					CoopDTO coopDTO = coopDao.getEntity(titanCoopParam);
					String keyValue = MD5.generatorSignParam(notifyParam, coopDTO.getMd5Key());
					String sign = MD5.MD5Encode(keyValue).toUpperCase();
					notifyParam.put("sign", sign);
					String resultString;
				
					resultString = HttpUtils.postRequest(new URL(URLDecoder.decode(item.getNotifyUrl(), "UTF-8")), notifyParam);
					Map result = JsonConversionTool.toObject(resultString, Map.class);
					//如果对方同步成功，则删除队列
					if("1".equals(result.get("status"))){
						deleteList.add(item.getSynId());
						log.info("notify cooperate the reg info success,SynId is :"+item.getSynId()+",url is :"+item.getNotifyUrl()+",param:"+notifyParam.toString()+",result："+resultString);
					}else{
						log.error("notify cooperate the reg info error,SynId is :"+item.getSynId()+",url is :"+item.getNotifyUrl()+",param :"+notifyParam.toString()+",result："+resultString);
					}
						
				} catch (MalformedURLException e) {
					log.error("notify cooperate the reg info error,SynId is :"+item.getSynId()+",url is :"+item.getNotifyUrl()+",param:"+notifyParam.toString(),e);
				} catch (UnsupportedEncodingException e) {
					log.error("notify cooperate the reg info error,SynId is :"+item.getSynId()+",url is :"+item.getNotifyUrl()+",param:"+notifyParam.toString(),e);
				} catch (IOException e) {
					log.error("notify cooperate the reg info error,SynId is :"+item.getSynId()+",url is :"+item.getNotifyUrl()+",param:"+notifyParam.toString(),e);
				} catch (Exception e) {
					log.error("notify cooperate the reg info error,SynId is :"+item.getSynId()+",url is :"+item.getNotifyUrl()+",param:"+notifyParam.toString(),e);
				}
			}
			//删除通知成功的
			if(deleteList.size()>0){
				synOrgInfoDao.deleteById(deleteList);
			}else{
				//如果本页没有数据要删除，则取下一页数据，否则就再取本页数据
				page.setCurrentPage(++pageNo);
			}
			//清理资源，开始下一次查询
			deleteList.clear();
			page.setItemList(Collections.EMPTY_LIST);
			page = synOrgInfoDao.selectForPage(null, page);
			
			if(pageNo>page.getTotalPage()){
				itemList = Collections.EMPTY_LIST;
			}else{
				itemList = page.getItemList();
			}
		}
		synOrgInfoWatch.stopWatch();
	}
	
	
	
	/**
	 * 同步业务监视
	 */
	class SynOrgInfoWatch{
		private Date beginDate = null;
		private boolean synOrgInfoIsDoing = false;
		
		/**
		 * 同步业务是否正在运行
		 * @return
		 */
		public boolean isDoing(){
			if(synOrgInfoIsDoing==false){
				return false;
			}
			if(isTimeout()){
				return false;
			}
			return true;
		}
		/**
		 * 开始监视同步业务
		 */
		public void startWatch(){
			beginDate = new Date();
			synOrgInfoIsDoing = true;
			
		}
		/**
		 * 结束监视同步业务
		 */
		public void stopWatch(){
			synOrgInfoIsDoing = false;
		}
 
		/**
		 * 是否超时
		 * @return
		 */
		public boolean isTimeout(){
			Date now = new Date();
			if(now.getTime()-beginDate.getTime()>TEN_MINUTES){
				return true;
			}
			return false;
		}
	}



	@Override
	public void insertSynOrgInfo(SynOrgInfoRequest synOrgInfoRequest) {
		TitanSynOrgInfo entity = new TitanSynOrgInfo();
		entity.setCoopType(synOrgInfoRequest.getCoopType());
		entity.setNotifyUrl(synOrgInfoRequest.getNotifyUrl());
		entity.setKvparam(synOrgInfoRequest.getKvparam());
		entity.setCreateTime(new Date());
		try {
			synOrgInfoDao.insert(entity);
		} catch (Exception e) {
			log.error("插入通知信息失败",e);
		}
		
	}
	
}
