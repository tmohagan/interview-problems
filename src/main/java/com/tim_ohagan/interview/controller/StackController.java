package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.*;
import com.tim_ohagan.interview.service.StackService;
import com.tim_ohagan.interview.dto.ParenthesisRequest;

@RestController
@RequestMapping("/stack")
public class StackController {
    private final StackService stackService;

    public StackController(StackService stackService) {
        this.stackService = stackService;
    }

    /*
     * The characters { and } are not allowed in a URL without proper encoding.
     * Instead, of using @RequestParam, send the data as a POST requests with a JSON body.
     *
     * In Spring MVC, when you use @RequestBody, you're indicating that the entire request body should be deserialized into a single object.
     * You can't directly extract a single field from the JSON body without first mapping it to an object.
     *
     * The purpose of this endpoint is only to check for matching parenthesis when provided a RequestBody.
     * Don't want to complicate it by adding additional functionality to extract the data to check, since that is not part of the purpose.
     * Instead, use the ParenthesisRequest dto to encapsulate the input data sent in the request body.
     * Then this endpoint can remain focused only on checking for matching parenthesis.
     */
    @PostMapping("/check-parenthesis")
    public boolean checkParenthesis(@RequestBody ParenthesisRequest request) {
        if (request.getItem() == null) {
            throw new IllegalArgumentException("No valid input provided");
        }
        return stackService.checkParenthesis(request.getItem());
    }
}