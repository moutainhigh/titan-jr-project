package com.titanjr.checkstand.util;


import com.fangcang.titanjr.redis.service.RedisService;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.AccessLimitConfig;
import com.titanjr.checkstand.constants.OperateTypeEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/11/15.
 */
@Service("rateLimiter")
public class AccessLimiter {

    private final static Logger logger = LoggerFactory.getLogger(AccessLimiter.class);

    private long intervalInMills;
    private long limit;
    private double intervalPerPermit;

    @Resource
    private RedisService redisService;

    public AccessLimiter() {
        intervalInMills = 1000;
    }

    /**
     * 针对接口名的访问频次限制
     * 比如：不允许一个支付接口，一秒钟请求次数超过200次
     * 参考备注：单线程操作下才能保证正确性,需要这些操作原子性的话，最好使用 redis 的 lua script
     * @param operateType 操作类型
     * @return
     */
    public boolean accessFrequency(OperateTypeEnum operateType) {
        if (AccessLimitConfig.frequencyConfigMap.containsKey(operateType)) {
            Long frequency = AccessLimitConfig.frequencyConfigMap.get(operateType);
            limit = frequency;//限时访问的总次数
            intervalPerPermit = intervalInMills / limit;
        } else {
            logger.error("当前操作{}未配置的访问{}频次数据", operateType,132456);
            return false;
        }
        String key = genKey(operateType,null);
        Map<String, String> counter = redisService.hmGetAll(key);
        if (counter.size() == 0) {
            TokenBucket tokenBucket = new TokenBucket(System.currentTimeMillis(), limit - 1);
            redisService.hmSetValue(key, tokenBucket.toHash());
            return true;
        } else {
            TokenBucket tokenBucket = TokenBucket.fromHash(counter);

            long lastRefillTime = tokenBucket.getLastRefillTime();
            long refillTime = System.currentTimeMillis();
            long intervalSinceLast = refillTime - lastRefillTime;//间隔时间

            long currentTokensRemaining;
            if (intervalSinceLast > intervalInMills) {
                currentTokensRemaining = limit;
            } else {
                long grantedTokens = (long) (intervalSinceLast / intervalPerPermit);//间隔时间所消耗的次数
                currentTokensRemaining = tokenBucket.getTokensRemaining() - grantedTokens;
            }
            tokenBucket.setLastRefillTime(refillTime);
            if (currentTokensRemaining > 0) {
                tokenBucket.setTokensRemaining(currentTokensRemaining);
                redisService.hmSetValue(key, tokenBucket.toHash());
                return true;
            } else {
                tokenBucket.setTokensRemaining(limit - 1);
                redisService.hmSetValue(key, tokenBucket.toHash());
                return false;
            }
        }
    }

    /**
     * 针对同一张单+同一个接口的操作间隔限制
     * 比如：不允许一张单在3s内连续提交两次支付
     * @param operateType 操作类型
     * @param mainKey 需要间隔限制的key
     * @return
     */
    public boolean accessInterval(OperateTypeEnum operateType, String mainKey){
        Long interval;
        if (AccessLimitConfig.intervalConfigMap.containsKey(operateType)) {
            interval = AccessLimitConfig.intervalConfigMap.get(operateType);
        } else {
            return false;
        }
        String key = genKey(operateType,mainKey);
        Object result = redisService.getValue(key);
        if (null != result ){
            Long lastInterval = Long.parseLong(result.toString());
            if (System.currentTimeMillis() - lastInterval > interval){
                redisService.setValue(key, System.currentTimeMillis());
                return true;
            } else {
                return false;
            }
        } else {
            redisService.setValue(key, System.currentTimeMillis());
            return true;
        }
    }

    private String genKey(OperateTypeEnum operateType, String mainKey) {
        String basekey = "access:limiter:" + limit + ":" + operateType.toString();
        if (StringUtil.isValidString(mainKey)){
            return basekey + ":" + mainKey;
        } else {
            return basekey;
        }
    }


    public static class TokenBucket {

        //上次重置时间
        private long lastRefillTime;
        //剩余访问次数
        private long tokensRemaining;

        public TokenBucket(long lastRefillTime, long tokensRemaining) {
            this.lastRefillTime = lastRefillTime;
            this.tokensRemaining = tokensRemaining;
        }

        public static TokenBucket fromHash(Map<String, String> hash) {
            long lastRefillTime = Long.parseLong(hash.get("lastRefillTime"));
            int tokensRemaining = Integer.parseInt(hash.get("tokensRemaining"));
            return new TokenBucket(lastRefillTime, tokensRemaining);
        }

        public Map<String, String> toHash() {
            Map<String, String> hash = new HashMap<String, String>();
            hash.put("lastRefillTime", String.valueOf(lastRefillTime));
            hash.put("tokensRemaining", String.valueOf(tokensRemaining));
            return hash;
        }

        public long getLastRefillTime() {
            return lastRefillTime;
        }

        public void setLastRefillTime(long lastRefillTime) {
            this.lastRefillTime = lastRefillTime;
        }

        public long getTokensRemaining() {
            return tokensRemaining;
        }

        public void setTokensRemaining(long tokensRemaining) {
            this.tokensRemaining = tokensRemaining;
        }
    }

}