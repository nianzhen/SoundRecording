package com.nianzhen.soundrecording.bean;

import org.xutils.db.annotation.Column;

/**
 * Created by Administrator on 2016/1/5.
 */
public class BaseDb {
    @Column(name = "id", isId = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseDb{" +
                "id=" + id +
                '}';
    }
}
