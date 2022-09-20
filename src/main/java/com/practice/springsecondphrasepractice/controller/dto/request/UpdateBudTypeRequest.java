package com.practice.springsecondphrasepractice.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBudTypeRequest {
//    @NotEmpty
    private String budType;
}
