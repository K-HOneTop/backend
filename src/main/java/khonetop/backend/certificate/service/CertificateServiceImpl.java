package khonetop.backend.certificate.service;

import khonetop.backend.certificate.repository.JpaCertificateByMemberRepository;
import khonetop.backend.certificate.repository.JpaCertificateDetailRepository;
import khonetop.backend.certificate.repository.JpaCertificateInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService{

    private final JpaCertificateInfoRepository certificateInfoRepository;
    private final JpaCertificateDetailRepository certificateDetailRepository;
    private final JpaCertificateByMemberRepository certificateByMemberRepository;

    @Override
    public void createCertificate() {

    }
}
