package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SearchRequestDto {

    @NotNull(message = "Array to search cannot be null")
    @Size(min = 1, message = "Array must contain at least one element")
    private int[] array;

    @NotNull(message = "Target value cannot be null")
    private Integer target;

    // Constructors
    public SearchRequestDto() {}

    public SearchRequestDto(int[] array, Integer target) {
        this.array = array;
        this.target = target;
    }

    // Getters and setters
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }
}