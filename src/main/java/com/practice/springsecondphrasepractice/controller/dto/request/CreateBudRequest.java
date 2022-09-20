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
    @NotNull
    @Length(min = 8,max = 8,message = "格式錯誤")
    private String budYmd;
    @NotNull
    @Pattern(regexp = "[YN]",message = "格式錯誤")
    private String budType;
}
