package com.zem.reggie.common;

public enum OrderStatusEnum {
    WAIT_PAY(1, "待付款"),
    WAIT_DISPATCH(2, "待派送"),
    DISPATCHING(3, "已派送"),
    COMPLETED(4, "已完成"),
    CANCELLED(5, "已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}