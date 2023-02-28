package khonetop.backend.certificate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 자격증 회차 관련 상세 정보
 * (토익 1900회차와 같은 상세 자격증 - 즐겨찾기에 필요한 도메인)
 */
@Entity
@Table(name = "certificate_detail")
@Getter
@NoArgsConstructor
@ToString
public class CertificateDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dcerId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime addStartDate;
    private LocalDateTime addEndDate;
    private LocalDate examDate;
    private LocalDate announceDate;
    private Integer round; //회차 정보

    @ManyToOne
    @JoinColumn(name = "cer_id")
    private CertificateInfo certificateInfo;
}
