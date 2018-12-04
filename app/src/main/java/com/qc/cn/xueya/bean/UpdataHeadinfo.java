package com.qc.cn.xueya.bean;

/**
 * Created by smz on 2018/11/14.
 */

public class UpdataHeadinfo {

    /**
     * code : 10000
     * data : http://xs3.op.xywy.com/api.iu1.xywy.com/ucenter/20181114/9b4997a0d387ef43eda432307244e70e19436.jpg
     * msg : 上传成功
     * status : 2
     * message :
     */

    private int code;
    private String data;
    private String msg;
    private int status;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
