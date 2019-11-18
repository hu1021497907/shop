package com.axh.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  //2.6过时 所有值为null的，转json的时候就不包含这个属性了
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse implements Serializable {
    private Integer status;
    private String message;
    private Object data;

    public static ServerResponse success() {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeEnum.SUCCESS.getStatus());
        serverResponse.setMessage(ResponseCodeEnum.SUCCESS.getMessage());
        return serverResponse;
    }

    public static ServerResponse success(String message) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeEnum.SUCCESS.getStatus());
        serverResponse.setMessage(message);
        return serverResponse;
    }

    public static ServerResponse success(Object data) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeEnum.SUCCESS.getStatus());
        serverResponse.setMessage(ResponseCodeEnum.SUCCESS.getMessage());
        serverResponse.setData(data);
        return serverResponse;
    }

    public static ServerResponse success(String message, Object data) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeEnum.SUCCESS.getStatus());
        serverResponse.setMessage(message);
        serverResponse.setData(data);
        return serverResponse;
    }

    public static ServerResponse error(Integer status) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(status);
        serverResponse.setMessage(ResponseCodeEnum.ERROR.getMessage());
        return serverResponse;
    }

    public static ServerResponse error(Integer status, String message) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(status);
        serverResponse.setMessage(message);
        return serverResponse;
    }
    @JsonIgnore
    public boolean isSuccess() {
        return this.getStatus().equals(ResponseCodeEnum.SUCCESS.getStatus());
    }

}
