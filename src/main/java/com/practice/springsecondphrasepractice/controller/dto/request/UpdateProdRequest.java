package com.practice.springsecondphrasepractice.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProdRequest {
    @NotNull
    @Pattern(regexp = "[\\u4e00-\\u9fa5]+" ,message = "格式錯誤")
    private String prodName;
    @NotNull
    @Pattern(regexp = "[A-Za-z]+" ,message = "格式錯誤")
    private String prodEname;
    @NotNull
    @Pattern(regexp = "(Y|N)" ,message = "格式錯誤")
    private String prodEnable;
}
