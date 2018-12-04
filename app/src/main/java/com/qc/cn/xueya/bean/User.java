package com.qc.cn.xueya.bean;

/**
 * Created by smz on 2018/11/7.
 */

public class User {


    /**
     * state : 200
     * userid : 160286937  //就代表你入到标识
     * isregister : 1
     * phonenum : 15649030280
     * height : 0
     * sex : 女
     * birthday : 2000-01-01
     */

    private int state;
    private String userid;
    private int isregister;
    private String phonenum;
    private int height;
    private String sex;
    private String birthday;

    //新加字段
    private String email;
    private  int pushable;
    private String avatar;
    private String accountstr;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPushable() {
        return pushable;
    }

    public void setPushable(int pushable) {
        this.pushable = pushable;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccountstr() {
        return accountstr;
    }

    public void setAccountstr(String accountstr) {
        this.accountstr = accountstr;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getIsregister() {
        return isregister;
    }

    public void setIsregister(int isregister) {
        this.isregister = isregister;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
