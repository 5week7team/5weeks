package com.sparta.project.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

   @NotBlank(message = "비밀번호")
    @Size(min = 4, max = 12, message = "11")
    @Pattern(regexp = "[a-z\\d]*${3,12}", message = "22")
    private String nickname;

    @NotBlank(message = "123")
    @Size(min = 8, max = 20, message = "33")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$"
            , message = "44")
    private String password;

    @NotBlank
    private String passwordConfirm;
}


