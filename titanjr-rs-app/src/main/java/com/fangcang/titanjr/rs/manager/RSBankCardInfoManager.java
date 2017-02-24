package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.*;
import com.fangcang.titanjr.rs.response.*;

/**
 * Created by zhaoshan on 2016/4/12.
 * 账户绑定卡，绑定银行卡信息查询
 * 校验账号是否绑定，修改提现卡
 * 对公校验失败卡修改
 * 删除绑定卡
 */
public interface RSBankCardInfoManager {

    /**
     * 绑定银行卡信息
     * 机构账户绑定银行卡
     * @param bankCardBindRequest
     * @return
     */
    public BankCardBindResponse bindBankCard(BankCardBindRequest bankCardBindRequest);

    /**
     * 查询绑定卡的信息
     * 会返回绑定卡的绑定状态
     * @param bankCardQueryRequest
     * @return
     */
    public BankCardQueryResponse queryBindCard(BankCardQueryRequest bankCardQueryRequest);

    /**
     * 校验绑定卡的绑定状态
     * @param bankCardBindCheckRequest
     * @return
     */
    public BankCardBindCheckResponse checkBindCardBindStatus(BankCardBindCheckRequest bankCardBindCheckRequest);

    /**
     * 修改提现卡
     * 我们暂时不需要实现此接口
     * @param withDrawCardModifyRequest
     * @return
     */
    public WithDrawCardModifyResponse modityWithDrawCard(WithDrawCardModifyRequest withDrawCardModifyRequest);

    /**
     * 调用该接口的时候要每天跑一次定时任务。
     * 
     * 对公校验失败卡修改==可能暂时不用
     * 对公绑卡需要一分钱代付校验，每日3点生成代付交易； 第二天7点 汇总交易到代收付表;
     * 11点55 读取代收付表上传通联
     * 每日13点，18点读入结果文件更新绑卡状态。
     * 针对绑卡失败的，需要有修改对公卡绑定的接口，对公卡不能删除
     * @param invalidPubCardModifyRequest
     * @return
     */
    public InvalidPubCardModifyResponse modifyInvalidPublicCard(InvalidPubCardModifyRequest invalidPubCardModifyRequest);

    /**
     * 删除绑定卡
     * 只能删除对私绑定卡,只有个人机构可以绑定对私提现卡
     * @param deletePersonCardRequest
     * @return
     */
    public DeletePersonCardResponse deletePersonCard(DeletePersonCardRequest deletePersonCardRequest);
}
