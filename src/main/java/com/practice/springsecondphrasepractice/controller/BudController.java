package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.CreateBudRequest;
import com.practice.springsecondphrasepractice.controller.dto.request.UpdateBudTypeRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.model.entity.Bud;
import com.practice.springsecondphrasepractice.service.BudService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bud")
@Validated
@RequiredArgsConstructor
public class BudController {
    private final BudService budService;

    @GetMapping
    public List<Bud> getBudByMethod(@RequestParam(required = false) @Length(min = 8, max = 8, message = "起始日期 格式錯誤") String startDate,
                                    @RequestParam(required = false) @Length(min = 8, max = 8, message = "結束日期 格式錯誤") String endDate,
                                    @RequestParam(required = false) @Length(min = 4, max = 4, message = "年度 格式錯誤") String year) throws DataNotFoundException, ParamInvalidException {

        if (startDate!=null&&endDate!=null&&startDate.compareTo(endDate) > 0) {
            List<String> errMessageList = new ArrayList<>();
            errMessageList.add("起始日期 不能大於 結束日期");
            throw new ParamInvalidException(errMessageList);
        }
        List<Bud> budList = budService.getBudByMethod(startDate, endDate, year);
        if (budList.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return budList;
    }

    @GetMapping("/{budYmd}")
    public Bud getTargetBud(@PathVariable(required = false) @Length(min = 8, max = 8, message = "日期 格式錯誤") String budYmd) throws DataNotFoundException {
        Bud targetBud = budService.getTargetBud(budYmd);
        if (targetBud == null) {
            throw new DataNotFoundException("資料不存在");
        }
        return targetBud;
    }

    @PostMapping
    public StatusResponse createBud(@RequestBody @Valid CreateBudRequest createBudRequest) throws ParamInvalidException {
        StatusResponse response = budService.createBud(createBudRequest);
        return response;
    }
//    @GetMapping("/business/{budYmd}")
//    private List<Bud> getWorkDayBudByYear(@RequestParam String year){
//        List<Bud> filteredBudList = budService.getWorkDayBudByYear(year);
//        return filteredBudList;
//    }

    @PutMapping("/{budYmd}")
    public StatusResponse updateBudType(@PathVariable @Length(min = 8, max = 8, message = "日期 格式錯誤")String budYmd, @RequestBody @Valid UpdateBudTypeRequest updateBudTypeRequest) throws ParamInvalidException {
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