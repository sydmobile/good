package com.syd.good.base;

/**
 * 说明：数据基类
 * <p>
 * date: 2020/6/8 13:47
 *
 * @author syd
 * @version 1.0
 */
public class BaseEntity<T> {
    private boolean success = true;
    private T data;
    private int code;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
