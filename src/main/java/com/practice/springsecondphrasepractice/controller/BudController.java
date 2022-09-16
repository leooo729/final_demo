package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.service.BudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bud")
@RequiredArgsConstructor

public class BudController {
    private final BudService budService;

}
