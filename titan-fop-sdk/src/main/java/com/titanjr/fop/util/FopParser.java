package com.titanjr.fop.util;

import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.response.FopResponse;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public interface FopParser<T extends FopResponse> {
    T parse(String resultJson) throws ApiException;

    Class<T> getResponseClass() throws ApiException;
}

