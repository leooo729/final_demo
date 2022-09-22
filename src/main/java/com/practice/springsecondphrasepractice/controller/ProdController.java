package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.model.ProdRepository;
import com.practice.springsecondphrasepractice.service.ProdService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prod")
@RequiredArgsConstructor
@Validated
public class ProdController {

    private final ProdService prodService;
    private final ProdRepository prodRepository;

    @GetMapping
    public List<ProdInfoResponse> getProdByMethod(@RequestParam(required = false) @Pattern(regexp = "[A-Z]{3}", message = "類別 格式錯誤") String kind,//@Pattern(regexp = "(USE|EAT)",message = "類別不存在")
                                                  @RequestParam(required = false) @Pattern(regexp = "[A-Z]{3}", message = "幣別 格式錯誤") String ccy) throws DataNotFoundException, ParamInvalidException {
        checkGetProdByMethod(kind, ccy);
        List<ProdInfoResponse> prodInfoResponseList = prodService.getProdByMethod(kind, ccy);
        if (prodInfoResponseList.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return prodInfoResponseList;
    }


    @GetMapping("/{prodId}")
    public ProdInfoResponse getTargetProd(@PathVariable @Pattern(regexp = "(EAT|USE)_[A-Z]{3}", message = "prodId 格式錯誤") String prodId) throws DataNotFoundException {
        if (prodRepository.findByProdId(prodId) == null) {
            throw new DataNotFoundException("資料不存在");
        }
        ProdInfoResponse prodInfoResponse = prodService.getTargetProd(prodId);
        return prodInfoResponse;
    }

    @PostMapping
    public StatusResponse createNewProd(@RequestBody @Valid CreateProdRequest createProdRequest) throws ParamInvalidException {
        if(prodRepository.findByProdId(createProdRequest.getProdKind()+"_"+createProdRequest.getProdCcy())!=null){
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("資料已存在");
            throw new ParamInvalidException(errMessageList);
        }
        StatusResponse response = prodService.createProd(createProdRequest);
        return response;
    }

    @PutMapping("/{prodId}")
    public StatusResponse updateProdById(@PathVariable @Pattern(regexp = "(EAT|USE)_[A-Z]{3}", message = "prodId 格式錯誤") String prodId, @RequestBody @Valid UpdateProdRequest updateProdRequest) throws ParamInvalidException {
        checkProdIdExist(prodId);
        StatusResponse response = prodService.updateProdById(prodId, updateProdRequest);
        return response;
    }


    @PostMapping("/{prodId}")
    public StatusResponse deleteProdById(@PathVariable @Pattern(regexp = "(EAT|USE)_[A-Z]{3}", message = "prodId 格式錯誤") String prodId, @RequestBody @Valid DeleteProdRequest deleteProdRequest) throws ParamInvalidException {
        checkProdIdExist(prodId);
        StatusResponse response = prodService.deleteProdById(prodId, deleteProdRequest);
        return response;
    }
//----------------------------------------------------------------Method
private boolean checkGetProdByMethod(String kind, String ccy) throws ParamInvalidException {
    if (kind != null && prodRepository.findByProdKind(kind).isEmpty()) {
        List<String> errMessageList = new ArrayList<>();
        errMessageList.add("類別不存在");
        throw new ParamInvalidException(errMessageList);
    }
    if (ccy != null && prodRepository.findByProdCcy(ccy).isEmpty()) {
        List<String> errMessageList = new ArrayList<>();
        errMessageList.add("幣別不存在");
        throw new ParamInvalidException(errMessageList);
    }
    return true;
}
private boolean checkProdIdExist(String prodId) throws ParamInvalidException {
    if (prodRepository.findByProdId(prodId) == null) {
        List<String> errMessageList = new ArrayList<>();
        errMessageList.add("資料不存在");
        throw new ParamInvalidException(errMessageList);
    }
    return true;
}

}

