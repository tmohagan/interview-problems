package com.tim_ohagan.interview.dto.datastructure;

import jakarta.validation.constraints.NotNull;

public class PutInMapRequestDto {

    @NotNull(message = "Key cannot be null")
    private String key;

    @NotNull(message = "Value cannot be null")
    private Integer value;

    // Constructors
    public PutInMapRequestDto() {}

    public PutInMapRequestDto(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    // Getters and setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}