package khonetop.backend.certificate.repository;

import khonetop.backend.certificate.domain.CertificateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCertificateInfoRepository extends JpaRepository<CertificateInfo, Long> {
}
