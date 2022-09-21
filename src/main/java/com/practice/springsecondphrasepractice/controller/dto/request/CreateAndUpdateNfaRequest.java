package com.practice.springsecondphrasepractice.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndUpdateNfaRequest {
    @NotEmpty
    private String subject;
    @NotEmpty
    private String content;
    @NotNull
    @Pattern(regexp = "(Y|N)" ,message = "格式錯誤")
    private String enable;
    @NotNull
    @Pattern(regexp = "[0-9]{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])", message = "格式錯誤")
    private String startDate;
    @NotNull
    @Pattern(regexp = "[0-9]{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])", message = "格式錯誤")
    private String endDate;
}
