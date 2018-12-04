package com.qc.cn.xueya.bean;

import java.util.List;

/**
 * Created by smz on 2018/11/10.
 */

public class GetDetailUserInfo {


    /**
     * state : 200
     * email :
     * pushable : 1
     * avatar : http://xs3.op.xywy.com/api.iu1.xywy.com/ucenter/20180411/fb8176a44d05692d41b39b055359824397628.jpg
     * accounts : [{"accountid":0,"accountstr":"都好","sex":"男","height":"165","birthday":"1970-01-01"}]
     */

    private int state;
    private String email;
    private int pushable;
    private String avatar;
    private List<AccountsUser> accounts;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

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

    public List<AccountsUser> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsUser> accounts) {
        this.accounts = accounts;
    }

    public static class AccountsUser {
        /**
         * accountid : 0
         * accountstr : 都好
         * sex : 男
         * height : 165
         * birthday : 1970-01-01
         */

        private int accountid;
        private String accountstr;
        private String sex;
        private String height;
        private String birthday;

        public int getAccountid() {
            return accountid;
        }

        public void setAccountid(int accountid) {
            this.accountid = accountid;
        }

        public String getAccountstr() {
            return accountstr;
        }

        public void setAccountstr(String accountstr) {
            this.accountstr = accountstr;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
