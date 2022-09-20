package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateBudTypeRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.model.entity.Bud;
import com.practice.springsecondphrasepractice.service.BudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
@RestController
@RequestMapping("/api/v1/bud")
@RequiredArgsConstructor
//@Validated
public class BudController {
    private final BudService budService;

    @GetMapping
    private List<Bud> getBudByMethod(@RequestParam (required = false)@Valid @Size(min = 8,max = 8) String startDate,
                                   @RequestParam(required = false) String endDate,
                                   @RequestParam(required = false) String year) throws DataNotFoundException {
        List<Bud> budList = budService.getBudByMethod(startDate, endDate, year);
        return budList;
    }

    @GetMapping("/{budYmd}")//@Valid @NotEmpty @Length(min = 8,max = 8,message = "666")
    private Bud getTargetBud(@PathVariable(required = false)String budYmd) throws DataNotFoundException {
        Bud targetBud = budService.getTargetBud(budYmd);
        return targetBud;
    }

    @PostMapping
    private StatusResponse createBud(@RequestBody CreateBudRequest createBudRequest) {
        StatusResponse response = budService.createBud(createBudRequest);
        return response;
    }
//    @GetMapping("/business/{budYmd}")
//    private List<Bud> getWorkDayBudByYear(@RequestParam String year){
//        List<Bud> filteredBudList = budService.getWorkDayBudByYear(year);
//        return filteredBudList;
//    }

    @PutMapping("/{budYmd}")
    private StatusResponse updateBudType(@PathVariable String budYmd, @RequestBody UpdateBudTypeRequest updateBudTypeRequest) {
        StatusResponse response = budService.updateBudType(budYmd, updateBudTypeRequest);
        return response;
    }
}


//    @GetMapping
//    private List<Bud> getAllBud() throws DataNotFoundException {
//        List<Bud> allBudList = budService.getAllBud();
//        return allBudList;
//    }
//    @GetMapping
//    private List<Bud> getWorkDayBudByYear(@RequestParam String year){
//        List<Bud> filteredBudList = budService.getWorkDayBudByYear(year);
//        return filteredBudList;
//    }
//    @GetMapping
//    private List<Bud> getFilteredWorkDayBud(@RequestParam String startDate,@RequestParam String endDate){
//        List<Bud> filteredBudList = budService.getFilteredWorkDayBud(startDate,endDate);
//        return filteredBudList;
//    }