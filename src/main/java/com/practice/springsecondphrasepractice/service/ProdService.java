package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.model.ProdRepository;
import com.practice.springsecondphrasepractice.model.entity.Prod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdService {

    private final ProdRepository prodRepository;

    public List<ProdInfoResponse> getAllProd() {
        List<Prod> allProdsList = prodRepository.findAll();
        List<ProdInfoResponse> prodResponseList = changeProdResponse(allProdsList);
        return prodResponseList;
    }

    public List<ProdInfoResponse> getEnableProdByKind(String prodKind) {
        List<Prod> filteredProdList = prodRepository.getEnableProdByKind(prodKind);
        List<ProdInfoResponse> prodResponseList = changeProdResponse(filteredProdList);
        return prodResponseList;
    }

    public List<ProdInfoResponse> getEnableProdByCcy(String prodCcy) {
        List<Prod> filteredProdList = prodRepository.getEnableProdByCcy(prodCcy);
        List<ProdInfoResponse> prodResponseList = changeProdResponse(filteredProdList);
        return prodResponseList;
    }

    public ProdInfoResponse getTargetProd(String prodId) {
        Prod prod = prodRepository.findByProdId(prodId);
        ProdInfoResponse prodInfoRequest =changeProdResponse(prod);
        return prodInfoRequest;
    }

    public StatusResponse createProd(CreateProdRequest request) {
        Prod prod = new Prod();
        prod.setProdId(request.getProdKind() + "_" + request.getProdCcy());
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

    public StatusResponse updateProdById(String prodId, UpdateProdRequest request) {
        Prod prod = prodRepository.findByProdId(prodId);

        prod.setProdName(request.getProdName());
        prod.setProdEname(request.getProdEname());
        prod.setProdEnable(request.getProdEnable());
        prod.setProdUTime(LocalDateTime.now());

        prodRepository.save(prod);
        return new StatusResponse("異動成功");
    }

    public StatusResponse deleteProdById(String prodId, DeleteProdRequest request) {
        Prod prod = prodRepository.findByProdId(prodId);
        prod.setProdEnable(request.getProdEnable());
        prod.setProdUTime(LocalDateTime.now());

        prodRepository.save(prod);
        return new StatusResponse("註銷成功");
    }
//----------------------------------------------------------------------------------method
    private List<ProdInfoResponse> changeProdResponse(List<Prod> prodList) {
        List<ProdInfoResponse> prodInfoResponseList = new ArrayList<>();
        for (Prod prod : prodList) {
            ProdInfoResponse prodInfoResponse = new ProdInfoResponse();
            prodInfoResponse.setProdId(prod.getProdId());
            prodInfoResponse.setProdKind(prod.getProdKind());
            prodInfoResponse.setProdName(prod.getProdName());
            prodInfoResponse.setProdEname(prod.getProdEname());
            prodInfoResponse.setProdCcy(prod.getProdCcy());
            prodInfoResponse.setProdEnable(prod.getProdEnable());
            prodInfoResponseList.add(prodInfoResponse);
        }
        return prodInfoResponseList;
    }

    private ProdInfoResponse changeProdResponse(Prod prod) {
        ProdInfoResponse prodInfoResponse = new ProdInfoResponse();
        prodInfoResponse.setProdId(prod.getProdId());
        prodInfoResponse.setProdKind(prod.getProdKind());
        prodInfoResponse.setProdName(prod.getProdName());
        prodInfoResponse.setProdEname(prod.getProdEname());
        prodInfoResponse.setProdCcy(prod.getProdCcy());
        prodInfoResponse.setProdEnable(prod.getProdEnable());

        return prodInfoResponse;
    }

}
