package khonetop.backend.Certificate.domain;

import khonetop.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificate_by_member")
@Getter
@NoArgsConstructor
@ToString
public class CertificateByMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mcerId;

    private String name;
    private String orgName;
    private String url;
    private Integer validateTerm;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime addStartDate;
    private LocalDateTime addEndDate;
    private LocalDate examDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
