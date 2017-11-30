package com.fangcang.titanjr.job.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.lts.common.constants.Constants;
import com.fangcang.lts.common.custom.FangCangJobClient;
import com.fangcang.lts.common.enums.JobValidEnvEnum;
import com.fangcang.lts.common.utils.CommonUtils;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.domain.Response;

/**
 * Created by wd on 2016/8/16.
 */
@Service
public class InitSubmitJob {

    private static Logger logger = LoggerFactory.getLogger(InitSubmitJob.class);

    @Autowired
    private FangCangJobClient jobClient;

    @PostConstruct
    public void initSubmitJob(){

        // 启动jobClient并提交job
        jobClient.start();

        // 提交任务
        submitBindBankCardJob();
        //暂时不用该补偿任务，防止重复转账
        //submitRepairGDPJob();
        
        submitUnFreezeTransferOrderJob();
        
        submitRefundConfirmJob();
        
        submitLoanCreditSynJob();
       
        submitLoanOrderSynJob();
      
        submitLoanCreditAmountEvaluatingJob();
        
        submitNotifyCoopOrgInfoJob();
        
        submitNotifyTradeStatusJob();
    }

    /**
     * 提交周期性任务
     */
    public void submitBindBankCardJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("bind_bankCard");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.bindBankCard);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);
        // cron表达式
        job.setCronExpression("0 0 9 * * ?");
        
        job.setRelyOnPrevCycle(false);

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }

    public void submitRepairGDPJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("repair_gdp");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.repairGDPTransfer);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);
        
        job.setRelyOnPrevCycle(false);

        // cron表达式
        job.setCronExpression("0 */5 * * * ?");

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }

    public void submitUnFreezeTransferOrderJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("unFreeze_transfer_order");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.unFreezeTransOrder);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);

        job.setRelyOnPrevCycle(false);
        // cron表达式
        job.setCronExpression("0 0 2 * * ?");

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
    
    
    //退款的定时器
    public void submitRefundConfirmJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("refund_confirm");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.refundConfirm);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);
        // cron表达式
        job.setCronExpression("0 0 0/1 * * ?");
        
        job.setRelyOnPrevCycle(false);

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
    
    public void submitLoanCreditSynJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("loanCreditSyn");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.loanCreditSyn);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);
        // cron表达式
        job.setCronExpression("0 */59 * * * ?");
        
        job.setRelyOnPrevCycle(false);

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
    public void submitLoanOrderSynJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("loanOrderSyn");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.loanOrderSyn);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);
        // cron表达式
        job.setCronExpression("0 */30 * * * ?");
        
        job.setRelyOnPrevCycle(false);

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
    
    public void submitLoanCreditAmountEvaluatingJob() {
        Job job = new Job();
        // 任务id
        job.setTaskId("loanCreditAmountEvaluating");

        // 模拟参数
//        job.setParam("shopId", "11111");
        // 扩展参数：任务执行环境，默认为ONLINE
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        // 扩展参数：任务执行JobRunner
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.loanCreditAmountEvaluating);

        // 执行要执行该任务的taskTracker的节点组名称
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        // 是否需要将执行结果反馈给JobClient
        job.setNeedFeedback(true);
        // 在同一个TaskTracker节点组中，当任务队列中存在这个任务的时候（任务id一样），是否替换更新
        job.setReplaceOnExist(true);
        // cron表达式
        job.setCronExpression("0 0 1 * * ?");
        
        job.setRelyOnPrevCycle(false);

        // 提交任务  response.success = true 表示提交成功
        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
    
    /**
     * 通知第三方金融注册机构信息
     * 
     */
    public void submitNotifyCoopOrgInfoJob(){
    	Job job = new Job();
        job.setTaskId(InitJobRunner.notifyCoopOrgInfo);
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.notifyCoopOrgInfo);
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);
        job.setCronExpression("0 */5 * * * ?");
        
        job.setRelyOnPrevCycle(false);

        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
    
    /***
     * 通知第三方交易状态
     *
     **/ 
    public void submitNotifyTradeStatusJob(){
    	Job job = new Job();
        job.setTaskId(InitJobRunner.notifyTradeStatus);
        job.setParam(Constants.JOB_VALID_ENV_KEY, JobValidEnvEnum.ONLINE.toString());
        job.setParam(Constants.JOB_RUNNER_KEY, InitJobRunner.notifyTradeStatus);
        job.setTaskTrackerNodeGroup(CommonUtils.getTaskTrackerNodeGroup(jobClient.getServerName()));
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);
        job.setCronExpression("0 */3 * * * ?");
        
        job.setRelyOnPrevCycle(false);

        Response response = jobClient.submitJob(job);

        logger.info("response:{}" , response);
    }
}
