package khonetop.backend.certificate.repository;

import khonetop.backend.certificate.domain.CertificateByMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCertificateByMemberRepository extends JpaRepository<CertificateByMember, Long> {
}
