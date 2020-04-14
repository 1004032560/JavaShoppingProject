package com.looper.domain;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    private static final long serialVersionUID = 5469644120824461069L;
    private int num;
    private String msg;
    private Object object;
    private List<Object> objects;

    public Data(int num,String msg, Object object, List<Object> objects) {
        this.num = num;
        this.msg = msg;
        this.object = object;
        this.objects = objects;
    }

    public Data(int num,String msg, Object object) {
        this.num = num;
        this.msg = msg;
        this.object = object;
    }

    public Data(String msg, List<Object> objects) {
        this.msg = msg;
        this.objects = objects;
    }

    public Data(String msg, Object object) {
        this.msg = msg;
        this.object = object;
    }

    public Data(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Data(Object object) {
        this.object = object;
    }

    public Data(List<Object> objects) {
        this.objects = objects;
    }

    public Data(String msg) {
        this.msg = msg;
    }

    public Data() {
    }

    @Override
    public String toString() {
        return "Data{" +
                "msg='" + msg + '\'' +
                ", object=" + object +
                ", objects=" + objects +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
