package khonetop.backend.Certificate.repository;

import khonetop.backend.Certificate.domain.CertificateByMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCertificateByMemberRepository extends JpaRepository<CertificateByMember, Long> {
}
