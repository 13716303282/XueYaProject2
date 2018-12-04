package com.qc.cn.xueya.bean;

/**
 * Created by smz on 2018/11/8.
 */

public class GetSmsFirstBean extends SerObject{

    /**
     * id : 37nfqf9ubrt65l4n6m3vjrpvq4
     * code : 10000
     * error :
     */

    private String id;
    private int code;
    private String error;


    //自己下面的东西
    private String phone;
    private  String name;
    private String  pass;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
