package com.fangcang.titanjr.pay.req;

/**
 * @author wengxitao
 * @Description 效验消息参数
 */

public class UrlParam {
    private String signature;
    private String timestamp;
    private String nonce;
    private String encrypt_type;
    private String msg_signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }


    public String getEncrypt_type() {
        return encrypt_type;
    }

    public void setEncrypt_type(String encrypt_type) {
        this.encrypt_type = encrypt_type;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    @Override
    public String toString() {
        return "UrlParam [signature=" + signature + ", timestamp="
                + timestamp + ", nonce=" + nonce + ", encrypt_type="
                + encrypt_type + ", msg_signature=" + msg_signature + "]";
    }

}