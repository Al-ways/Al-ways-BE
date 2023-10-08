package com.project.always.bar.controller;

import com.project.always.bar.service.UserBarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/userbar")
public class UserBarController {
    private final UserBarService userBarService;
}
