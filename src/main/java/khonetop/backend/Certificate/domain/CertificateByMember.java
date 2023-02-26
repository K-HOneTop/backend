package khonetop.backend.Certificate.domain;

import khonetop.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * member가 직접 생성한 자격증
 * (즐겨찾기, 점수에 모두 들어감)
 */

@Entity
@Table(name = "certificate_by_member")
@Getter
@NoArgsConstructor
@ToString
public class CertificateByMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mcerId;

    private String name; //자격증 이름
    private String orgName; //발급기관명
    private String url; //홈페이지 주소
    private Integer validateTerm; //유효기간
    private LocalDateTime startDate; //접수 시작일자
    private LocalDateTime endDate; //접수 종료일자
    private LocalDateTime addStartDate; //추가접수 시작일자
    private LocalDateTime addEndDate; //추가접수 종료일자
    private LocalDate examDate; //시험일자
    private LocalDate announceDate; //성적 발표일자

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
