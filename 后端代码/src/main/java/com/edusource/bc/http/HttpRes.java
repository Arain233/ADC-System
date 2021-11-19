package com.edusource.bc.http;

public class HttpRes {

    private int code = 207;
    private String msg;
    private Object data;

    public static HttpRes error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static HttpRes error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static HttpRes error(int code, String msg) {
        HttpRes r = new HttpRes();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpRes ok(Object data) {
        HttpRes r = new HttpRes();
        r.setCode(200);
        r.setData(data);
        return r;
    }

    public static HttpRes partOk(Object data) {
        HttpRes r = new HttpRes();
        r.setData(data);
        return r;
    }

    public static HttpRes ok() {
        return new HttpRes();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
