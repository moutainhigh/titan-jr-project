package com.fangcang.titanjr.job.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.lts.common.custom.FangCangTaskTracker;
import com.fangcang.lts.common.holder.JobRunnerHolder;
import com.fangcang.titanjr.job.runner.BindBankCardRunner;
import com.fangcang.titanjr.job.runner.FiveMinutesRunner;
import com.fangcang.titanjr.job.runner.LoanCreditAmountEvaluatingRunner;
import com.fangcang.titanjr.job.runner.LoanCreditSynRunner;
import com.fangcang.titanjr.job.runner.LoanOrderSynRunner;
import com.fangcang.titanjr.job.runner.RefundConfirmRunner;
import com.fangcang.titanjr.job.runner.RepairGDPTransferRunner;
import com.fangcang.titanjr.job.runner.UnFreezeTransOrderRunner;

/**
 *
 * 初始化JobRunner并启动TaskTracker执行任务
 *
 * Created by wd on 2016/8/16.
 */
@Service
public class InitJobRunner {

    public static String bindBankCard = "bindBankCard";
    public static String repairGDPTransfer = "repairGDPTransfer";
    public static String unFreezeTransOrder = "unFreezeTransOrder";
    public static String refundConfirm = "refundConfirm";
    public static String loanCreditSyn = "loanCreditSyn";
    public static String loanOrderSyn = "loanOrderSyn";
    public static String loanCreditAmountEvaluating = "loanCreditAmountEvaluating";
    public static String fiveMinutes= "fiveMinutes";

    @Autowired
    private BindBankCardRunner bindBankCardRunner;
    
    @Autowired
    private RepairGDPTransferRunner repairGDPTransferRunner;
    
    @Autowired
    private UnFreezeTransOrderRunner unFreezeTransOrderRunner;
    
    @Autowired
    private RefundConfirmRunner refundConfirmRunner;
    
    @Autowired
    private LoanOrderSynRunner loanOrderSynRunner;
    
    @Autowired
    private LoanCreditSynRunner loanCreditSynRunner;
    
    @Autowired
    private LoanCreditAmountEvaluatingRunner loanCreditAmountEvaluatingRunner;

    @Autowired
    private FangCangTaskTracker fangCangTaskTracker;
    
    @Autowired
    private FiveMinutesRunner fiveMinuteRunner;

    @PostConstruct
    public void initJobRunner(){

        // 将业务功能JobRunner注册到JobRunnerHolder.jobRunnerMap
        JobRunnerHolder.jobRunnerMap.putIfAbsent(bindBankCard,bindBankCardRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(repairGDPTransfer,repairGDPTransferRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(unFreezeTransOrder,unFreezeTransOrderRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(refundConfirm,refundConfirmRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(loanOrderSyn,loanOrderSynRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(loanCreditSyn,loanCreditSynRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(loanCreditAmountEvaluating,loanCreditAmountEvaluatingRunner);
        
        JobRunnerHolder.jobRunnerMap.putIfAbsent(fiveMinutes,fiveMinuteRunner);
        // 启动taskTracker
        fangCangTaskTracker.start();
    }
}
