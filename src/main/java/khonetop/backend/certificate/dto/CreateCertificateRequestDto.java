package khonetop.backend.certificate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    Integer validateTerm; //유효기간
    LocalDateTime startDate; //접수 시작일자
    LocalDateTime endDate; //접수 종료일자
    LocalDateTime addStartDate; //추가접수 시작일자
    LocalDateTime addEndDate; //추가접수 종료일자
    LocalDate examDate; //시험일자
    LocalDate announceDate; //성적 발표일자

}
