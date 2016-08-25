package com.fangcang.titanjr.pay.task;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.pay.util.TitanThreadPool;

/**
 * 付款结果通知
 * 
 * @author wengxitao
 *
 */
public final class TitanPayResultNotifyTask implements Runnable {

	private static final Log log = LogFactory
			.getLog(TitanPayResultNotifyTask.class);

	// 内部计数器
	private AtomicInteger resetNum = new AtomicInteger(0);

	private String taskId;
	// 回调地址
	private String url;

	private Map<String, String> bussInfos = null;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setBussInfos(Map<String, String> bussInfos) {
		this.bussInfos = bussInfos;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public void run() {
		try {
			log.info("begin execute taskId=" + taskId);

			HttpPost post = HttpUtils.buildPostForm(url, bussInfos);
			post.setConfig(HttpUtils.getDefRequestConfig());
			HttpResponse response = HttpUtils.getHttpClientFactory().execute(
					post);
			
			log.info("execute taskId=" + taskId + " buss responseCode= "
					+ response.getStatusLine().getStatusCode());
			// 如果状态码为200,就是正常返回
			if (response.getStatusLine().getStatusCode() == 200) {
				log.info("execute taskId=" + taskId + " is ok");
			} else {
				log.info("reset execute taskId=" + taskId);
				if (resetNum.incrementAndGet() < 3) {
					TitanThreadPool.executeTask(this);
				} else {
					log.error("execute taskId=" + taskId + " is fail.");
				}
			}
		} catch (Exception ex) {
			log.error("", ex);
		}
	}
}
