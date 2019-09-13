package th.co.aware.common.pdf.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import th.co.aware.common.pdf.entity.Pdf;
import th.co.aware.common.pdf.logic.PdfService;

import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate template;
    @MockBean
    private PdfService service;

    @Before
    public void prepare() {
        Mockito.when(service.getAll()).thenReturn(
                Arrays.asList(new Pdf(), new Pdf())
        );
    }

    @Test
    public void test() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port + "/pdf/hello", String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAllPdf() {
        ResponseEntity<Pdf[]> response = template.getForEntity("http://localhost:" + port + "/pdf/getall", Pdf[].class);
        Assert.assertNotNull(response.getBody());
        log.info(Arrays.toString(response.getBody()));
        Pdf[] pdfList = response.getBody();
        log.info(pdfList[0].getName());
    }
}
