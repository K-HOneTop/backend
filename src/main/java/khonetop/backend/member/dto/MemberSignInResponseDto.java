package khonetop.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignInResponseDto {
    @NotNull
    String name;
    @NotNull
    String email;
    @NotNull
    String nickname;
}
