package team.YongAndJoe.NewsTodayBackend.util;

import team.YongAndJoe.NewsTodayBackend.entity.User;

import java.io.Serializable;

public class AjaxResponseBody implements Serializable {
    private boolean success;
    private String msg;
    private Object result;
    private String jwtToken;

    private AjaxResponseBody(boolean success, String msg, Object result) {
        this.success = success;
        this.msg = msg;
        this.result = result;
    }

    private AjaxResponseBody(boolean success, String msg, Object result, String jwtToken) {
        this(success, msg, result);
        this.jwtToken = jwtToken;
    }

    public static AjaxResponseBody SUCCESS(String msg, Object result) {
        return new AjaxResponseBody(true, msg, result);
    }

    public static AjaxResponseBody SUCCESS(String msg, Object result, String jwtToken) {
        return new AjaxResponseBody(true, msg, result, jwtToken);
    }

    public static AjaxResponseBody FAIL(String msg, Object result) {
        return new AjaxResponseBody(false, msg, result);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}