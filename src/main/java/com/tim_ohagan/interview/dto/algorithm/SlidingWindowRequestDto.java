package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

public class SlidingWindowRequestDto {

    @NotNull(message = "Numbers array cannot be null")
    @Size(min = 1, message = "Numbers array must contain at least one element")
    private int[] numbers;

    @NotNull(message = "Window size cannot be null")
    @Positive(message = "Window size must be positive")
    private Integer k;

    // Constructors
    public SlidingWindowRequestDto() {}

    public SlidingWindowRequestDto(int[] numbers, Integer k) {
        this.numbers = numbers;
        this.k = k;
    }

    // Getters and setters
    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }
}