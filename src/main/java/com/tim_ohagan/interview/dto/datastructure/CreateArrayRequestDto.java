package com.tim_ohagan.interview.dto.datastructure;

import jakarta.validation.constraints.Positive;

public class CreateArrayRequestDto {

    @Positive(message = "Capacity must be positive")
    private int capacity;

    // Constructors
    public CreateArrayRequestDto() {}

    public CreateArrayRequestDto(int capacity) {
        this.capacity = capacity;
    }

    // Getter and setter
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}