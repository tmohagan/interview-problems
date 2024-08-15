package com.tim_ohagan.interview.dto;

/*
 * Encapsulate the input data sent in the request body.
 * Clearly separating the data transfer objects from the controller logic.
 */
public class ParenthesisRequest {
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}