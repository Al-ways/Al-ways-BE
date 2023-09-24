package com.project.always.controller.api;

import com.project.always.controller.api.request.SampleRequest;
import com.project.always.controller.api.response.SampleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleControllerApi {

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public SampleResponse returnNameAndAge(@RequestBody SampleRequest sampleRequest) {
        return new SampleResponse(sampleRequest.getName(), sampleRequest.getAge());
    }
}


