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
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    public List<ProdInfoResponse> getProdByMethod(@RequestParam(required = false) @Pattern(regexp = "[A-Z]{3}", message = "類別 格式錯誤") String kind,
                                                  @RequestParam(required = false) @Pattern(regexp = "[A-Z]{3}", message = "幣別 格式錯誤") String ccy) throws DataNotFoundException, ParamInvalidException {

        if (kind != null && prodRepository.findByProdKind(kind) == null) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("類別不存在");
            throw new ParamInvalidException(errMessageList);
        }
        if (ccy != null && prodRepository.findByProdCcy(ccy) == null) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("幣別不存在");
            throw new ParamInvalidException(errMessageList);
        }

        List<ProdInfoResponse> prodInfoResponseList = prodService.getProdByMethod(kind, ccy);
        if (prodInfoResponseList.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return prodInfoResponseList;
    }

    @GetMapping("/{prodId}")
    public ProdInfoResponse getTargetProd(@PathVariable  @Pattern(regexp = "(EAT|USE)_[A-Z]{3}", message = "prodId 格式錯誤") String prodId) throws DataNotFoundException {
        if (prodRepository.findByProdId(prodId) == null) {
            throw new DataNotFoundException("資料不存在");
        }
        ProdInfoResponse prodInfoResponse = prodService.getTargetProd(prodId);
        return prodInfoResponse;
    }

    @PostMapping
    public StatusResponse createNewProd(@RequestBody @Valid CreateProdRequest createProdRequest) {
        StatusResponse response = prodService.createProd(createProdRequest);
        return response;
    }

    @PutMapping("/{prodId}")
    public StatusResponse updateProdById(@PathVariable @Pattern(regexp = "(EAT|USE)_[A-Z]{3}", message = "prodId 格式錯誤") String prodId, @RequestBody @Valid UpdateProdRequest updateProdRequest) throws ParamInvalidException {
        if (prodRepository.findByProdId(prodId) == null) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("資料不存在");
            throw new ParamInvalidException(errMessageList);
        }
        StatusResponse response = prodService.updateProdById(prodId, updateProdRequest);
        return response;
    }

    @PostMapping("/{prodId}")
    public StatusResponse deleteProdById(@PathVariable  @Pattern(regexp = "(EAT|USE)_[A-Z]{3}", message = "prodId 格式錯誤")String prodId, @RequestBody @Valid DeleteProdRequest deleteProdRequest) throws DataNotFoundException, ParamInvalidException {
        if (prodRepository.findByProdId(prodId) == null) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("資料不存在");
            throw new ParamInvalidException(errMessageList);
        }
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