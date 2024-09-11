package com.tim_ohagan.interview.dto.datastructure;

import jakarta.validation.constraints.NotNull;

public class AddToListRequestDto {

    @NotNull(message = "Element cannot be null")
    private Integer element;

    // Constructors
    public AddToListRequestDto() {}

    public AddToListRequestDto(Integer element) {
        this.element = element;
    }

    // Getter and setter
    public Integer getElement() {
        return element;
    }

    public void setElement(Integer element) {
        this.element = element;
    }
}