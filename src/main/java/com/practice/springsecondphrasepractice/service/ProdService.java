package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.model.ProdRepository;
import com.practice.springsecondphrasepractice.model.entity.Prod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProdService {

private final ProdRepository prodRepository;

    public List<Map<String,ProdInfoResponse>> getAllProd(){
        List<Map<String,ProdInfoResponse>>allProdList=prodRepository.getAllProd();
        return allProdList;
    }
    public StatusResponse createNewProd(CreateProdRequest request) {
        Prod prod = new Prod();
        prod.setProdId(request.getProdKind()+"_"+request.getProdCcy());
        prod.setProdKind(request.getProdKind());
        prod.setProdName(request.getProdName());
        prod.setProdEname(request.getProdEname());
        prod.setProdCcy(request.getProdCcy());
        prod.setProdEnable(request.getProdEnable());
        prod.setProdITime(LocalDateTime.now());
        prod.setProdUTime(LocalDateTime.now());

        prodRepository.save(prod);
        return new StatusResponse("新增成功");
    }
}
