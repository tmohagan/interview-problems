package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class KWayMergeRequestDto {

    @NotNull(message = "Sorted arrays list cannot be null")
    @Size(min = 2, message = "Must provide at least two sorted arrays")
    private List<List<Integer>> sortedArrays;

    // Constructors
    public KWayMergeRequestDto() {}

    public KWayMergeRequestDto(List<List<Integer>> sortedArrays) {
        this.sortedArrays = sortedArrays;
    }

    // Getter and setter
    public List<List<Integer>> getSortedArrays() {
        return sortedArrays;
    }

    public void setSortedArrays(List<List<Integer>> sortedArrays) {
        this.sortedArrays = sortedArrays;
    }
}