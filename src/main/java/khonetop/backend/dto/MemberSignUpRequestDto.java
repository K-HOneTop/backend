package khonetop.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequestDto {
    @NotNull
    String name;
    @NotNull
    String email;
    @NotNull
    String nickname;
    @NotNull
    String password;
}
