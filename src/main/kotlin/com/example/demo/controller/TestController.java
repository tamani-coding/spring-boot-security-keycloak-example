package com.example.demo.controller;

import com.example.demo.service.TestService;
import com.example.test.api.v1.TestApi;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements TestApi {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Override
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok(testService.test());
    }
}
