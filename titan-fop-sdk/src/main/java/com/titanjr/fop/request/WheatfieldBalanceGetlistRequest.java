package com.titanjr.fop.request;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldBalanceGetlistResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public class WheatfieldBalanceGetlistRequest extends BaseRequest implements FopRequest<WheatfieldBalanceGetlistResponse> {

    private final static Logger logger = LoggerFactory.getLogger(WheatfieldBalanceGetlistRequest.class);

    private String userid;
    private String rootinstcd;

    @Override
    public String getApiMethodName() {
        return "ruixue.wheatfield.balance.getlist";
    }

    @Override
    public Map<String, String> getTextParams() {
        FopHashMap textParams = new FopHashMap();

        textParams.put("userid", this.userid);
        textParams.put("rootinstcd", this.rootinstcd);

        return textParams;
    }

    @Override
    public Class<WheatfieldBalanceGetlistResponse> getResponseClass() {

        return WheatfieldBalanceGetlistResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {
        if (!StringUtil.isValidString(userid) || !StringUtil.isValidString(rootinstcd)) {
            logger.error("参数校验失败,userid:{},rootinstcd:{}", userid, rootinstcd);
            throw new ApiRuleException(ReturnCodeEnum.CODE_USERID_ERROR.getCode(), ReturnCodeEnum.CODE_USERID_ERROR.getMsg());
        }
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRootinstcd() {
        return rootinstcd;
    }

    public void setRootinstcd(String rootinstcd) {
        this.rootinstcd = rootinstcd;
    }
}
