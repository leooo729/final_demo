package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.NfaInfoResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.model.NfaRepository;
import com.practice.springsecondphrasepractice.model.entity.Nfa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NfaService {
    private final NfaRepository nfaRepository;

    public List<NfaInfoResponse> getAllNfa() {
        List<Nfa> allNfaList = nfaRepository.findAll();
        List<NfaInfoResponse> nfaInfoResponseList =changeNfaResponse(allNfaList) ;
        return nfaInfoResponseList;
    }

    public StatusResponse createNfa(CreateNfaRequest request) {
        Nfa nfa = new Nfa();
        nfa.setNfaUuid(DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS").format(LocalDateTime.now()));
        nfa.setNfaSubject(request.getSubject());
        nfa.setNfaContent(request.getContent());
        nfa.setNfaEnable(request.getEnable());
        nfa.setNfaSTime(request.getStartDate());
        nfa.setNfaETime(request.getEndDate());
        nfa.setNfaUTime(LocalDateTime.now());
        nfaRepository.save(nfa);
        return new StatusResponse("新增成功");
    }

    public StatusResponse updateNfa(String nfaUuid, UpdateNfaRequest request) {
        Nfa nfa = nfaRepository.findByNfaUuid(nfaUuid);
        nfa.setNfaSubject(request.getSubject());
        nfa.setNfaContent(request.getContent());
        nfa.setNfaEnable(request.getEnable());
        nfa.setNfaSTime(request.getStartDate());
        nfa.setNfaETime(request.getEndDate());
        nfa.setNfaUTime(LocalDateTime.now());
        nfaRepository.save(nfa);
        return new StatusResponse("異動成功");
    }

    public StatusResponse deleteNfa(String nfaUuid, DeleteNfaRequest request) {
        Nfa nfa = nfaRepository.findByNfaUuid(nfaUuid);
        nfa.setNfaEnable(request.getEnable());
        nfaRepository.save(nfa);
        return new StatusResponse("撤銷成功");
    }

    //------------------------------------------------------------------Method
    public List<NfaInfoResponse> changeNfaResponse(List<Nfa> nfaList) {
        List<NfaInfoResponse> nfaInfoResponseList = new ArrayList<>();
        for (Nfa nfa : nfaList) {
            NfaInfoResponse nfaInfoResponse = new NfaInfoResponse();

            nfaInfoResponse.setNfaUuid(nfa.getNfaUuid());
            nfaInfoResponse.setNfaSubject(nfa.getNfaSubject());
            nfaInfoResponse.setNfaContent(nfa.getNfaContent());
            nfaInfoResponse.setNfaEnable(nfa.getNfaEnable());
            nfaInfoResponse.setNfaUTime(LocalDateTime.now());

            nfaInfoResponseList.add(nfaInfoResponse);
        }
        return nfaInfoResponseList;
    }
}
