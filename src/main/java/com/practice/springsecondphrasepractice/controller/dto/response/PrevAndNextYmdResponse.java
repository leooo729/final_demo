package com.practice.springsecondphrasepractice.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrevAndNextYmdResponse {
private String budYmd;
    private String budPrevYmd;
    private String budNextYmd;
}
