package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateAndUpdateNfaRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.DeleteNfaRequest;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NfaService {
    private final NfaRepository nfaRepository;

    public List<NfaInfoResponse> getAllNfa() {
        List<Nfa> allNfaList = nfaRepository.findAll();
        List<NfaInfoResponse> nfaInfoResponseList = changeNfaResponse(allNfaList);
        return nfaInfoResponseList;
    }

    public List<NfaInfoResponse> getFilteredNfaList(String subject, String startDate, String endDate) {
        List<Nfa> enabledNfaList = nfaRepository.getEnabledNfa();
        List<NfaInfoResponse> filteredNfaList=filteredNfa(enabledNfaList,subject,startDate,endDate);
        return filteredNfaList;
    }
    public StatusResponse createNfa(CreateAndUpdateNfaRequest request) {
        Nfa nfa = new Nfa();
        nfa.setNfaUuid(DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS").format(LocalDateTime.now()));
        nfa = nfaInfoSetter(nfa, request);
        nfaRepository.save(nfa);
        return new StatusResponse("新增成功");
    }

    public StatusResponse updateNfa(String nfaUuid, CreateAndUpdateNfaRequest request) {
        Nfa nfa = nfaRepository.findByNfaUuid(nfaUuid);
        nfa = nfaInfoSetter(nfa, request);
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

    private Nfa nfaInfoSetter(Nfa nfa, CreateAndUpdateNfaRequest request) {
        nfa.setNfaSubject(request.getSubject());
        nfa.setNfaContent(request.getContent());
        nfa.setNfaEnable(request.getEnable());
        nfa.setNfaSTime(request.getStartDate());
        nfa.setNfaETime(request.getEndDate());
        nfa.setNfaUTime(LocalDateTime.now());
        return nfa;
    }
    private List<NfaInfoResponse> filteredNfa(List<Nfa> nfaList,String subject, String startDate, String endDate){
        if (!subject.isEmpty()) {
            nfaList = nfaList.stream().filter(nfa -> nfa.getNfaSubject().contains(subject)).collect(Collectors.toList());
        }
        if (!startDate.isEmpty()) {
            nfaList = nfaList.stream().filter(nfa -> nfa.getNfaSTime().compareTo(startDate) >= 0).collect(Collectors.toList());
        }
        if (!endDate.isEmpty()) {
            nfaList = nfaList.stream().filter(nfa -> nfa.getNfaETime().compareTo(endDate) <= 0).collect(Collectors.toList());
        }
        List<NfaInfoResponse> nfaInfoResponseList = changeNfaResponse(nfaList);
        return nfaInfoResponseList;
    }
}
