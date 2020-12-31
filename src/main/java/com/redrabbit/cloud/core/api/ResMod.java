package com.redrabbit.cloud.core.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "通用返回实体类")
public class ResMod<T> implements Serializable {
    @ApiModelProperty(value = "系统状态码", required = true)
    private String code;
    @ApiModelProperty(value = "是否成功标识", required = true)
    private Boolean success;
    @ApiModelProperty(value = "返回信息", required = true)
    private String msg;
    @ApiModelProperty(value = "承载数据")
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