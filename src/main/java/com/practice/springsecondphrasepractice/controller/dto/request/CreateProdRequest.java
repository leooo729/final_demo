package com.practice.springsecondphrasepractice.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProdRequest {
    private String prodKind;
    private String prodName;
    private String prodEname;
    private String prodCcy;
    private String prodEnable;


//    private String prodITime;
//    private String prodUTime;

}
