package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateAndUpdateNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.NfaInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.model.NfaRepository;
import com.practice.springsecondphrasepractice.service.NfaService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/nfa")
@RequiredArgsConstructor
@Validated
public class NfaController {
    private final NfaService nfaService;
    private final NfaRepository nfaRepository;


    @GetMapping
    public List<NfaInfoResponse> getNfaByMethod(@RequestParam(required = false) String subject,
                                                @RequestParam(required = false) @Pattern(regexp = "^$|[0-9]{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])", message = "startDate 格式錯誤") String startDate,
                                                @RequestParam(required = false) @Pattern(regexp = "^$|[0-9]{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])", message = "endDate 格式錯誤") String endDate) throws DataNotFoundException {
        List<NfaInfoResponse> nfaInfoResponseList= filteredNfaList(subject, startDate, endDate);
        if (nfaInfoResponseList.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return nfaInfoResponseList;
    }

    @PostMapping
    public StatusResponse createNfa(@RequestBody @Valid CreateAndUpdateNfaRequest request) throws ParamInvalidException {
        checkDateCorrection(request);
        StatusResponse response = nfaService.createNfa(request);
        return response;
    }

    @PutMapping("/{nfaUuid}")
    public StatusResponse updateNfa(@PathVariable String nfaUuid, @RequestBody @Valid CreateAndUpdateNfaRequest request) throws ParamInvalidException {
        checkDataExist(nfaUuid);
        checkDateCorrection(request);
        StatusResponse response = nfaService.updateNfa(nfaUuid, request);
        return response;
    }

    @PostMapping("/{nfaUuid}")
    public StatusResponse deleteNfa(@PathVariable String nfaUuid, @RequestBody @Valid DeleteNfaRequest deleteNfaRequest) throws ParamInvalidException {
        checkDataExist(nfaUuid);
        StatusResponse response = nfaService.deleteNfa(nfaUuid, deleteNfaRequest);
        return response;
    }

    //-----------------------------------------------------Method
    private List<NfaInfoResponse> filteredNfaList(String subject, String startDate, String endDate){
        List<NfaInfoResponse> nfaInfoResponseList;
        if (subject==null && startDate==null && endDate==null) {
            nfaInfoResponseList = nfaService.getAllNfa();
        } else {
            nfaInfoResponseList = nfaService.getFilteredNfaList(subject, startDate, endDate);
        }
        return nfaInfoResponseList;
    }

    private boolean checkDateCorrection(CreateAndUpdateNfaRequest request) throws ParamInvalidException {
        if (request.getStartDate().compareTo(request.getEndDate()) > 0) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("起始日期 不能大於 結束日期");
            throw new ParamInvalidException(errMessageList);
        }
        return true;
    }

    private boolean checkDataExist(String nfaUuid) throws ParamInvalidException {
        if (nfaRepository.findByNfaUuid(nfaUuid) == null) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("資料不存在");
            throw new ParamInvalidException(errMessageList);
        }
        return true;
    }
}
