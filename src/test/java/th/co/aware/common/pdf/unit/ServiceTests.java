package th.co.aware.common.pdf.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import th.co.aware.common.pdf.dto.ReportRequest;
import th.co.aware.common.pdf.entity.Pdf;
import th.co.aware.common.pdf.entity.PdfRepository;
import th.co.aware.common.pdf.logic.PdfService;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceTests {

    @Autowired
    private PdfService service;
    @Autowired
    private PdfRepository repo;

    @Test
    public void createPdf() {
        ReportRequest request = new ReportRequest("opal", "far", "away", "file");
        service.create(request);

        List<Pdf> pdfList = repo.findByName("away");
        Assert.assertNotEquals(0, pdfList.size());
        Assert.assertNotNull(pdfList.get(0).getId());
        Assert.assertEquals("file", pdfList.get(0).getPayload());

        List<Pdf> other = service.getAll();
        Assert.assertNotEquals(0, other.size());
        Assert.assertEquals("away", other.get(0).getName());

        // clean up
        repo.deleteAll(other);
        List<Pdf> list = repo.findByName("away");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void getAll() {
        repo.saveAll(UnitTestUtils.genPdf());
        List<Pdf> pdfList = service.getAll();
        Assert.assertEquals(3, pdfList.size());
        Assert.assertEquals("opal3", pdfList.get(2).getUserId());
    }

    @Test
    public void nullRequst() {
        try {
            service.create(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("request.not.null", e.getMessage());
        }
    }

    @Test
    public void nullNameTest() {
        ReportRequest request = new ReportRequest("opal1", "far", null, "file");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("name.not.null", e.getMessage());
        }
    }

    @Test
    public void EmptyNameTest() {
        ReportRequest request = new ReportRequest("opal1", "far", "", "file");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("name.not.empty", e.getMessage());
        }
    }

    @Test
    public void nullUserIdTest() {
        ReportRequest request = new ReportRequest(null, "far", "name", "file");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("user.not.null", e.getMessage());
        }
    }

    @Test
    public void EmptyUserIdTest() {
        ReportRequest request = new ReportRequest("", "far", "name", "file");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("user.not.empty", e.getMessage());
        }
    }

    @Test
    public void nullServiceTest() {
        ReportRequest request = new ReportRequest("opal1", null, "name", "file");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("service.not.null", e.getMessage());
        }
    }

    @Test
    public void emptyServiceTest() {
        ReportRequest request = new ReportRequest("opal1", "", "name", "file");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("service.not.empty", e.getMessage());
        }
    }

    @Test
    public void nullPayloadTest() {
        ReportRequest request = new ReportRequest("opal1", "far", "name", null);
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("payload.not.null", e.getMessage());
        }
    }

    @Test
    public void emptyPayloadTest() {
        ReportRequest request = new ReportRequest("opal1", "far", "name", "");
        try {
            service.create(request);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("payload.not.empty", e.getMessage());
        }
    }

    @TestConfiguration
    public static class testConfig {
        @Bean
        public PdfService pdfService() {
            return new PdfService();
        }
    }


}
