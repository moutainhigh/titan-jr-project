package com.titanjr.fop.api;

import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.response.FopResponse;
import com.titanjr.fop.request.FopRequest;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public interface FopClient {

    <T extends FopResponse> T execute(FopRequest<T> fopRequest) throws ApiException;

    <T extends FopResponse> T execute(FopRequest<T> fopRequest, String session) throws ApiException;
}
