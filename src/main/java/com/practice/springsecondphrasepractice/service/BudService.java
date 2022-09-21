package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateBudTypeRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.PrevAndNextYmdResponse;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.model.BudRepository;
import com.practice.springsecondphrasepractice.model.entity.Bud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudService {
    private final BudRepository budRepository;

    public List<Bud> getBudByMethod(String startDate, String endDate, String year){

        if (startDate != null || endDate != null) {
            return getFilteredWorkDayBud(startDate, endDate);
        }
        if (year != null) {
            return getWorkDayBudByYear(year);
        }
        return getAllBud();
    }

    public List<Bud> getAllBud() {
        List<Bud> allBudList = budRepository.findAll();
        return allBudList;
    }

    public Bud getTargetBud(String budYmd) throws DataNotFoundException {
        Bud targetBud = budRepository.findByBudYmd(budYmd);
        return targetBud;
    }

    public List<Bud> getFilteredWorkDayBud(String startDate, String endDate) {
        List<Bud> filteredBudList = budRepository.getFilteredWorkDayBud(startDate, endDate);
        return filteredBudList;
    }

    public List<Bud> getWorkDayBudByYear(String targetYear) {
        List<Bud> workDayBudList = budRepository.getWorkDayBud();
        List<Bud> filterBudList = new ArrayList<>();

        for (Bud bud : workDayBudList) {
            String budYear = bud.getBudYmd().substring(0, 4);
            if (targetYear.equals(budYear)) {
                filterBudList.add(bud);
            }
        }
        return filterBudList;
    }

    public StatusResponse createBud(CreateBudRequest createBudRequest) {

        Bud bud = new Bud();
        bud.setBudYmd(createBudRequest.getBudYmd());
        bud.setBudType(createBudRequest.getBudType());
        bud.setBudUTime(LocalDateTime.now());
        budRepository.save(bud);
        return new StatusResponse("新增成功");
    }

    public PrevAndNextYmdResponse getPrevAndNextYmd(String budYmd) {
        PrevAndNextYmdResponse response = new PrevAndNextYmdResponse();
        response.setBudYmd(budYmd);
        response.setBudPrevYmd(budRepository.getPrevWorkDayBud(budYmd)!=null?budRepository.getPrevWorkDayBud(budYmd):"查無資料");
        response.setBudNextYmd(budRepository.getNextWorkDayBud(budYmd)!=null?budRepository.getNextWorkDayBud(budYmd):"查無資料");
        return response;
    }

    public StatusResponse updateBudType(String budYmd, UpdateBudTypeRequest updateBudTypeRequest) {

        Bud bud = budRepository.findByBudYmd(budYmd);
        bud.setBudType(updateBudTypeRequest.getBudType());
        bud.setBudUTime(LocalDateTime.now());
        budRepository.save(bud);
        return new StatusResponse("異動成功");
    }


}
