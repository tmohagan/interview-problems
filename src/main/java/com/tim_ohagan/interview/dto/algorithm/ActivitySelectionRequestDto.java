package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ActivitySelectionRequestDto {

    @NotNull(message = "Start times array cannot be null")
    @Size(min = 1, message = "Start times array must contain at least one element")
    private int[] start;

    @NotNull(message = "Finish times array cannot be null")
    @Size(min = 1, message = "Finish times array must contain at least one element")
    private int[] finish;

    // Constructors
    public ActivitySelectionRequestDto() {}

    public ActivitySelectionRequestDto(int[] start, int[] finish) {
        this.start = start;
        this.finish = finish;
    }

    // Getters and setters
    public int[] getStart() {
        return start;
    }

    public void setStart(int[] start) {
        this.start = start;
    }

    public int[] getFinish() {
        return finish;
    }

    public void setFinish(int[] finish) {
        this.finish = finish;
    }
}