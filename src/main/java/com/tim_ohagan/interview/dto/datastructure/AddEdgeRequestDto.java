package com.tim_ohagan.interview.dto.datastructure;

import jakarta.validation.constraints.NotNull;

public class AddEdgeRequestDto {

    @NotNull(message = "Source vertex cannot be null")
    private Integer source;

    @NotNull(message = "Destination vertex cannot be null")
    private Integer destination;

    // Constructors
    public AddEdgeRequestDto() {}

    public AddEdgeRequestDto(Integer source, Integer destination) {
        this.source = source;
        this.destination = destination;
    }

    // Getters and setters
    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }
}