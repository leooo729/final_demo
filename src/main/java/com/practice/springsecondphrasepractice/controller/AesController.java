package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.AesRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.AesResponse;
import com.practice.springsecondphrasepractice.service.AesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class AesController {
    private final AesService aesService;

@PostMapping("/encode/aes/ecb")
    public AesResponse ecbEncode(@Value("${key}") String key, @RequestBody AesRequest request) throws Exception {
    return aesService.ecbEncode(request,key);
}

    @PostMapping("/decode/aes/ecb")
    public AesResponse ecbDecode(@Value("${key}") String key, @RequestBody AesRequest request) throws Exception {
        return aesService.ecbDecode(request,key);
    }
    @PostMapping("/encode/aes/cbc")
    public AesResponse cbcEncode(@Value("${key}") String key, @RequestBody AesRequest request) throws Exception {
        return aesService.cbcEncode(request,key);
    }
    @PostMapping("/decode/aes/cbc")
    public AesResponse cbcDecode(@Value("${key}") String key, @RequestBody AesRequest request) throws Exception {
        return aesService.cbcDecode(request,key);
    }
}
