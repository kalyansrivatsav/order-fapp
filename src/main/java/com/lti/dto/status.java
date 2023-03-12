package com.lti.dto;

public enum status {
    ACCEPTED(501),
    LIMIT_EXCEEDED(502);

    public int getValue() {
        return value;
    }

    private int value;
    private status(int value) {
        this.value = value;
    }

}
