package com.fangcang.titanjr.service;

/**
 * 5分钟间隔任务
 * @author luoqinglong
 * @date   2017年3月2日
 */
public interface TitanFiveMinuteJobService {
	/**
	 * 运行量少，运行时间短的业务
	 */
	void runLessTimeAsynTask();
}
