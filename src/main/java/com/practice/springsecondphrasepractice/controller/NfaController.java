package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateProdRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.NfaInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.service.NfaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nfa")
@RequiredArgsConstructor
public class NfaController {
    private final NfaService nfaService;

    @GetMapping
    private List<NfaInfoResponse> getAllNfa(){
        List<NfaInfoResponse> nfaInfoResponseList=nfaService.getAllNfa();
        return  nfaInfoResponseList;
    }

    @PostMapping
    private StatusResponse createNfa(@RequestBody CreateNfaRequest createNfaRequest) {
        StatusResponse response = nfaService.createNfa(createNfaRequest);
        return response;
    }
    @PutMapping("/{nfaUuid}")
    private StatusResponse updateNfa(@PathVariable String nfaUuid, @RequestBody UpdateNfaRequest updateNfaRequest){
        StatusResponse response=nfaService.updateNfa(nfaUuid,updateNfaRequest);
        return response;
    }
    @PostMapping("/{nfaUuid}")
    private StatusResponse deleteNfa(@PathVariable String nfaUuid,@RequestBody DeleteNfaRequest deleteNfaRequest) {
        StatusResponse response = nfaService.deleteNfa(nfaUuid,deleteNfaRequest);
        return response;
    }

}
