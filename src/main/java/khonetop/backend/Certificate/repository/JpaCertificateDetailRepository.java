package khonetop.backend.Certificate.repository;

import khonetop.backend.Certificate.domain.CertificateDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCertificateDetailRepository extends JpaRepository<CertificateDetail, Long> {
}
