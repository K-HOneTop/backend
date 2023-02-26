package khonetop.backend.Certificate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Integer round;

    @ManyToOne
    @JoinColumn(name = "cer_id")
    private CertificateInfo certificateInfo;
}
