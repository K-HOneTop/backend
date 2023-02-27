package khonetop.backend.Certificate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 자격증 직접 추가 request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCertificateRequestDto {

    @NotNull
    String name;

    String org_name;
    String url;

}
