package com.fangcang.titanjr.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * 流水号生成服务
 * @author HX1401028
 *
 */
public class OrderGenerateService {
	/**
	 * 流水号共用拼接符
	 */
	private static String comStr = "TJ";
	private static int random_range = 10;
	private static int random_digit = 3;
	
	private static String genOrderByType(String orderType){
		return orderDateTimePart(orderType);
	}
	
	/**
	 * 给流水号添加时间段
	 * @param orderType
	 * @return
	 */
	private static String orderDateTimePart(String orderType){
		StringBuffer order = new StringBuffer(orderType);
		order.append(DateUtil.formatDatayyMMDDHHMMSS(new Date()));
		return orderRandomPart(order);
	}
	
	
	
	/**
	 * 给流水号添加随机数部分
	 * @param order
	 * @return
	 */
	private static String orderRandomPart(StringBuffer order){
		Random rd = new Random();
		for(int i=0; i < random_digit; i ++){
			order.append(rd.nextInt(random_range));
		}
		return order.toString();
	}
	
	public static String getOrderNum()
	{
		 StringBuffer str = new StringBuffer(comStr);
		 Calendar now = Calendar.getInstance();
		 String year = now.get(Calendar.YEAR)+"";
		 int month = now.get(Calendar.MONTH)+1;
		 int day = now.get(Calendar.DATE);
		 str.append(year.substring(2,year.length())).append(month<10?"0"+month:month).append(day<10?"0"+day:day);
		 Random rd = new Random();
		 for(int i=0; i < 7; i ++){
			 str.append(rd.nextInt(9));
		 }
		return str.toString();
	}
	
	public static String genLocalOrderNo(){
		StringBuffer order = new StringBuffer("L");
		order.append(DateUtil.formatDataYYMMDDHHMMSS(new Date()));
		Random rd = new Random();
		for(int i=0; i < 4; i ++){
			order.append(rd.nextInt(random_range));
		}
		return order.toString();
	}
	
	public static String genLoacalPayOrderNo(){
		StringBuffer order = new StringBuffer("LR");
		order.append(DateUtil.formatDataYYMMDD(new Date()));
		Random rd = new Random();
		for(int i=0; i < 4; i ++){
			order.append(rd.nextInt(random_range));
		}
		return order.toString();
	}
	
	/**
	 * 生成贷款申请单号,每日生成1000个
	 * @return
	 */
	public static String genLoanApplyOrderNo(){
		StringBuffer order = new StringBuffer("ln");
		order.append(DateUtil.formatDataYYMMDDHHMMSS(new Date()));
		Random rd = new Random();
		for(int i=0; i < 4; i ++){
			order.append(rd.nextInt(random_range));
		}
		return order.toString();
	}
	
	/**
	 * 生成交易工单流水号
	 * @return
	 */
	public static String genSyncUserOrderId(){
		return genOrderByType(comStr + "O");
	}
	
	/**
	 * 生成请求号
	 * @return
	 */
	public static String genResquestNo(){
		return genOrderByType(comStr + "R");
	}
	
	/**
	 * 解冻账户
	 * @return
	 */
	public static String genUnfreezeResquestNo(){
		return genOrderByType("uf");
	}
	
	/**
	 * 冻结账户
	 * @return
	 */
	public static String genFreeAccountBalanceRequestNo(){
		return genOrderByType("tjrf");
	}
	
	/**
	 * 提现
	 * @return
	 */
	public static String genFreeWithDrawNo(){
		return genOrderByType("wd");
	}
	
	
	/**
	 * 生成同步资金到新浪账户的流水号
	 * @return String   
	 */
	public static String genSyncDiffToSinaOrder(){
		return genOrderByType(comStr + "lts");
	}
	
}
