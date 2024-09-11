package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PrefixSumsRequestDto {

    @NotNull(message = "Numbers array cannot be null")
    @Size(min = 1, message = "Numbers array must contain at least one element")
    private int[] numbers;

    // Constructors
    public PrefixSumsRequestDto() {}

    public PrefixSumsRequestDto(int[] numbers) {
        this.numbers = numbers;
    }

    // Getter and setter
    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}