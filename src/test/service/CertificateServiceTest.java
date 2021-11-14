package service;

import com.epam.msu.config.SpringConfig;
import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Tag;
import com.epam.msu.service.CertificateService;
import com.epam.msu.util.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, DatabaseConfiguration.class})
public class CertificateServiceTest {

    private final CertificateService certificateService;

    @Autowired
    public CertificateServiceTest(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @Test
    public void getCertificateByIdTest() {
        List<Tag> tags = List.of(new Tag(6, "Developer4"));
        CertificateDto certificateDto = new CertificateDto(27, "Completed", "test_description",
                444, 12, new Timestamp(1632751839000L),
                new Timestamp(1632751839000L), tags);
        CertificateDto databaseCertificateDto = certificateService.getCertificateById(27);
        assertEquals(certificateDto, databaseCertificateDto);
    }

    @Test
    public void integrationTestForAddAndDelete() {
        List<Tag> tags = List.of(new Tag(6, "Developer4"));
        int certificateId = 57;
        CertificateDto certificateDto = new CertificateDto(certificateId, "Completed", "test_description",
                444, 12, new Timestamp(1632751839000L),
                new Timestamp(1632751839000L), tags);
        CertificateDto compareCertificateDto;
        assertNull(certificateService.getCertificateById(certificateId));
        compareCertificateDto = certificateService.createNewCertificate(certificateDto);
        assertNotNull(certificateService.getCertificateById(certificateId));
        assertEquals(certificateDto, compareCertificateDto);

        certificateService.deleteCertificateById(certificateId);
        assertNull(certificateService.getCertificateById(certificateId));

    }

    @Test
    public void getAllCertificatesTest() {
        List<Tag> tags1 = List.of(new Tag(6, "Developer4"));
        List<Tag> tags2 = List.of(new Tag(7, "CertificateTag"));
        List<Tag> tags3 = List.of(new Tag(8, "Naruto"));
        List<CertificateDto> certificatesDto = List.of(
                new CertificateDto(27, "Completed", "test_description",
                        444, 12, new Timestamp(1632751839000L),
                        new Timestamp(1632751839000L), tags1),
                new CertificateDto(28, "Work", "12455",
                        100, 5, new Timestamp(1632751839000L),
                        new Timestamp(1632751839000L), tags2),
                new CertificateDto(31, "Saske", "uchiha_saske",
                        100, 5, new Timestamp(1632751839000L),
                        new Timestamp(1632751839000L), tags3)

        );
        List<CertificateDto> databaseCertificatesDto = certificateService.getAllCertificates();
        List<CertificateDto> firstThreeCertificates = databaseCertificatesDto.subList(0, 3);
        assertEquals(certificatesDto, firstThreeCertificates);
    }
}
