package com.practice.springsecondphrasepractice.controller.dto.response;

import lombok.*;

import javax.persistence.SqlResultSetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdInfoResponse {
    private String prodId;
    private String prodKind;
    private String prodName;
    private String prodEname;
    private String prodCcy;
    private String prodEnable;
}

//public interface ProdInfoResponse {
//    String getProdId();
//    String getProdKind();
//    String getProdName();
//    String getProdEname();
//    String getProdCcy();
//    String getProdEnable();
//}


