package com.wechat.demo.commons;

import org.apache.http.HttpStatus;

import java.util.Arrays;

public class RestfulResponseBody {
    /**
     * 提示信息
     */
    private String message;
    /**
     * 状态
     */
    private HttpStatus status;
    /**
     * 存储数据
     */
    private Object[] data;

    public RestfulResponseBody() {
    }

    public RestfulResponseBody(String message, HttpStatus status, Object[] data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestfulResponseBody{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
