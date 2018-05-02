package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2018/3/8.
 */
public enum WithDrawChannelEnum {

    TL_CHANNEL("TL","通联提现渠道"),
    RB_CHANNEL("RB","融宝提现渠道");
    private String channelKey;
    private String channelName;

    WithDrawChannelEnum(String channelKey,String channelName){
        this.channelKey = channelKey;
        this.channelName = channelName;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public String getChannelName() {
        return channelName;
    }
}
