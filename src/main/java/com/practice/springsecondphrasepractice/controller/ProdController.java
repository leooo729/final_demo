package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.service.ProdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prod")
@RequiredArgsConstructor
public class ProdController {

    private final ProdService prodService;
    @GetMapping
    public List<ProdInfoResponse> getProdByMethod(@RequestParam(required = false) String kind,
                                                   @RequestParam(required = false) String ccy){
        List<ProdInfoResponse>prodInfoResponseList=prodService.getProdByMethod(kind,ccy);
        return prodInfoResponseList;
    }

    @GetMapping("/{prodId}")
    public ProdInfoResponse getTargetProd(@PathVariable String prodId) {
        ProdInfoResponse prodInfoRequest = prodService.getTargetProd(prodId);
        return prodInfoRequest;
    }

    @PostMapping
    public StatusResponse createNewProd(@RequestBody CreateProdRequest createProdRequest) {
        StatusResponse response = prodService.createProd(createProdRequest);
        return response;
    }

    @PutMapping("/{prodId}")
    public StatusResponse updateProdById(@PathVariable String prodId, @RequestBody UpdateProdRequest updateProdRequest) {
        StatusResponse response = prodService.updateProdById(prodId, updateProdRequest);
        return response;
    }

    @PostMapping("/{prodId}")
    public StatusResponse deleteProdById(@PathVariable String prodId, @RequestBody DeleteProdRequest deleteProdRequest) {
        StatusResponse response = prodService.deleteProdById(prodId, deleteProdRequest);
        return response;
    }
}


//    @GetMapping
//    private List<ProdInfoResponse> getAllProd() {
//        List<ProdInfoResponse> allProdList = prodService.getAllProd();
//        return allProdList;
//    }
//
//    @GetMapping
//    private List<ProdInfoResponse> getEnableProdByKind(@RequestParam String kind) {
//        List<ProdInfoResponse> filteredProdList = prodService.getEnableProdByKind(kind);
//        return filteredProdList;
//    }
//
//    @GetMapping
//    private List<ProdInfoResponse> getEnableProdByCcy(@RequestParam String ccy) {
//        List<ProdInfoResponse> filteredProdList = prodService.getEnableProdByCcy(ccy);
//        return filteredProdList;
//    }