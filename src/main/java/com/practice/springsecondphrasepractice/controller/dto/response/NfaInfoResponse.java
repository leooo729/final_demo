package com.practice.springsecondphrasepractice.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NfaInfoResponse {
    private String nfaUuid;
    private String nfaSubject;
    private String nfaContent;
    private String nfaEnable;
    private LocalDateTime nfaUTime;
}
