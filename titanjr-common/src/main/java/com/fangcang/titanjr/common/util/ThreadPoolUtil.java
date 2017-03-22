package com.fangcang.titanjr.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author luoqinglong
 * @2016年5月5日
 */
public class ThreadPoolUtil {
	private static ExecutorService cachedThreadPool = new ThreadPoolExecutor(10,10,10,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
	
	public static void excute(Runnable task){
		cachedThreadPool.execute(task);
	}
}
