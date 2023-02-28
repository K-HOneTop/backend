package khonetop.backend.certificate.repository;

import khonetop.backend.certificate.domain.CertificateDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCertificateDetailRepository extends JpaRepository<CertificateDetail, Long> {
}
