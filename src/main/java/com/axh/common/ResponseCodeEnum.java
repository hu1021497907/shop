package com.axh.common;

public enum ResponseCodeEnum {
   SUCCESS(0,"成功"),
    ERROR(1,"失败");
    private  Integer status;
      private String message;

    ResponseCodeEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
