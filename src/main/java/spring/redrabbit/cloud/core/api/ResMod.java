package spring.redrabbit.cloud.core.api;

import java.io.Serializable;

public class ResMod<T> implements Serializable {
    private String code;
    private Boolean success;
    private String msg;
    private T data;

    public ResMod() {
    }

    public ResMod(String code, Boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static ResMod success(){
        ResMod r = new ResMod();
        r.setCode("0");
        r.setSuccess(true);
        r.setMsg("操作成功");
        return r;
    }

    public static ResMod fail(){
        ResMod r = new ResMod();
        r.setCode("999");
        r.setSuccess(false);
        r.setMsg("失败");
        return r;
    }

    public static ResMod data(Object data){
        ResMod r = new ResMod();
        r.setCode("0");
        r.setSuccess(true);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static ResMod status(Boolean b){
        ResMod r = new ResMod();
        r.setCode( b ? "0" : "9999" );
        r.setMsg( b ? "操作成功" : "操作失败" );
        r.setSuccess(b);
        r.setData(b);
        return r;
    }

    public ResMod msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return this.data;
    }

    public ResMod setCode(String code) {
        this.code = code;
        return this;
    }

    public ResMod setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public ResMod setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResMod setData(T data) {
        this.data = data;
        return this;
    }

}