package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateBudTypeRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.model.entity.Bud;
import com.practice.springsecondphrasepractice.service.BudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bud")
@RequiredArgsConstructor
public class BudController {
    private final BudService budService;

//    @GetMapping
//    private List<Bud> getAllBud() throws DataNotFoundException {
//        List<Bud> allBudList = budService.getAllBud();
//        if (allBudList.isEmpty()) {
//            throw new DataNotFoundException("資料不存在");
//        }
//        return allBudList;
//    }

    @GetMapping("/{budYmd}")
    private Bud getBudByDate(@PathVariable @NotEmpty String budYmd) throws DataNotFoundException {
        Bud targetBud = budService.getBudByDate(budYmd);

        if (budYmd == null || 8 != budYmd.length()) {
            throw new DataNotFoundException("日期 格式錯誤");
        }
        if (targetBud==null) {
            throw new DataNotFoundException("資料不存在");
        }
        return targetBud;
    }

//    @GetMapping()
//    private List<Bud> getFilteredWorkDayBud(@RequestParam String startDate,@RequestParam String endDate){
//        List<Bud> filteredBudList = budService.getFilteredWorkDayBud(startDate,endDate);
//return filteredBudList;
//    }

//    @GetMapping()
//    private List<Bud> getWorkDayBudByYear(@RequestParam String year){
//        List<Bud> filteredBudList = budService.getWorkDayBudByYear(year);
//        return filteredBudList;
//    }

    @PostMapping
    private StatusResponse createBud(@RequestBody CreateBudRequest createBudRequest){
        StatusResponse response=budService.createBud(createBudRequest);
        return response;
    }
    @GetMapping("/business/{budYmd}")
    private List<Bud> getWorkDayBudByYear(@RequestParam String year){
        List<Bud> filteredBudList = budService.getWorkDayBudByYear(year);
        return filteredBudList;
    }

    @PutMapping("/{budYmd}")
    private StatusResponse updateBudType(@PathVariable String budYmd, @RequestBody UpdateBudTypeRequest updateBudTypeRequest){
        StatusResponse response=budService.updateBudType(budYmd,updateBudTypeRequest);
        return response;
    }




}
