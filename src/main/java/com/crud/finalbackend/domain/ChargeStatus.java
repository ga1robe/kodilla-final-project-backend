package com.crud.finalbackend.domain;

public enum ChargeStatus {

    FREE("NO CHARGE COST"),
    PAID("PAID");

    private String description;

    ChargeStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
