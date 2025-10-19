package com.jgfontes.receipt_service.dto;

public enum Status {
    PENDING(0),
    COMPLETED(1),
    FAILED(2);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
