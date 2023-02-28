package khonetop.backend.certificate.service;


/**
 * 자격증 관련 서비스
 */
public interface CertificateService {
    //직접 생성한 자격증 CRUD, 리스트의 자격증 조회(이름별, 접수마감별 등등) (리스트의 자격증은 DB에 직접 넣을 것)
    //즐찾 추가(직접 생성한 자격증은 즉시 즐찾 추가됨, 즐찾에서 삭제하면 직접 생성한 자격증 삭제됨), 즐찾 조회(이름별, 접수날짜별 등등)

    void createCertificate();


}
