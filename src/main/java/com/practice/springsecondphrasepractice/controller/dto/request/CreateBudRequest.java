package com.practice.springsecondphrasepractice.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBudRequest {
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])",message = "格式錯誤")
    private String budYmd;
    @NotEmpty
    @Pattern(regexp = "^$|(Y|N)",message = "格式錯誤")
    private String budType;
}
