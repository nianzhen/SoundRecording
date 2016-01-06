package com.nianzhen.soundrecording.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/1/5.
 */
@Table(name = "password")
public class Password extends BaseDb {
    @Column(name = "item")
    private String item;

    @Column(name = "password")
    private String password;

    @Column(name = "creatTime")
    private String creatTime;

    @Column(name = "hint")
    private String hint;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id='" + getId() + '\'' +
                "item='" + item + '\'' +
                ", password='" + password + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", hint='" + hint + '\'' +
                '}';
    }
}
