package khonetop.backend.Certificate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 자격증 기본 정보
 * (토익과 같은 큰 범주 - 점수에 필요한 도메인)
 */
@Entity
@Table(name = "certificate_info")
@Getter
@NoArgsConstructor
@ToString
public class CertificateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cerId;
    private String name;
    private String orgName;
    private String url;
    private Integer validateTerm;

}
