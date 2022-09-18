package com.practice.springsecondphrasepractice.controller.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdInfoResponse {
    private String prodId;
    private String prodKind;
    private String prodName;
    private String prodEname;
    private String prodCcy;
    private String prodEnable;
}
