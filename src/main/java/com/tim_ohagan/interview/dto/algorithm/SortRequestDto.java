package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Arrays;

public class SortRequestDto {

    @NotNull(message = "Array to sort cannot be null")
    @Size(min = 1, message = "Array must contain at least one element")
    private int[] array;

    // Optional: sorting order
    private boolean ascending = true;

    // Default constructor
    public SortRequestDto() {
    }

    // Parameterized constructor
    public SortRequestDto(int[] array) {
        this.array = array;
    }

    // Getters and setters
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    // Utility method to get array length
    public int getArrayLength() {
        return array != null ? array.length : 0;
    }

    // toString method for logging and debugging
    @Override
    public String toString() {
        return "SortRequestDto{" +
                "array=" + Arrays.toString(array) +
                ", ascending=" + ascending +
                '}';
    }

    // equals and hashCode methods for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortRequestDto that = (SortRequestDto) o;
        return ascending == that.ascending && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(array);
        result = 31 * result + (ascending ? 1 : 0);
        return result;
    }
}