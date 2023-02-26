package khonetop.backend.Certificate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
