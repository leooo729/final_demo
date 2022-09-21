package com.practice.springsecondphrasepractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prod")
@RequiredArgsConstructor
@Validated
public class AesController {
    //Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );

}
