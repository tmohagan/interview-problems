package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TwoSumRequestDto {

    @NotNull(message = "Numbers array cannot be null")
    @Size(min = 2, message = "Numbers array must contain at least two elements")
    private int[] numbers;

    @NotNull(message = "Target sum cannot be null")
    private Integer target;

    // Constructors
    public TwoSumRequestDto() {}

    public TwoSumRequestDto(int[] numbers, Integer target) {
        this.numbers = numbers;
        this.target = target;
    }

    // Getters and setters
    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }
}