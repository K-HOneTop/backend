package khonetop.backend.Certificate.repository;

import khonetop.backend.Certificate.domain.CertificateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCertificateInfoRepository extends JpaRepository<CertificateInfo, Long> {
}
