package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.model.entity.Prod;
import com.practice.springsecondphrasepractice.service.ProdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/prod")
@RequiredArgsConstructor
public class ProdController {

    private final ProdService prodService;

    @GetMapping
    private List<Map<String,ProdInfoResponse>> getAllProd(){
        List<Map<String,ProdInfoResponse>>allProdList=prodService.getAllProd();
        return  allProdList;
    }

    @PostMapping
    private StatusResponse createNewProd(@RequestBody CreateProdRequest createProdRequest) {
        StatusResponse response = prodService.createNewProd(createProdRequest);
        return response;
    }
}
