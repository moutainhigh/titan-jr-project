package com.fangcang.titanjr.pay.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池，负责执行TITAN收银台异步任务
 * 
 * @author wengxitao
 *
 */
public final class TitanThreadPool
{
	private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(
			2, 15, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

	public static synchronized void executeTask(Runnable runnable) {
		executor.execute(runnable);
	}
}
