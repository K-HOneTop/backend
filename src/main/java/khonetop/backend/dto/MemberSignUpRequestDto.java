package khonetop.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequestDto {
    @NonNull
    String email;
    @NonNull
    String nickname;
    @NonNull
    String password;
}
