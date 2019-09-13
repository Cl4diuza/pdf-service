package th.co.aware.common.pdf.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import th.co.aware.common.pdf.dto.ReportRequest;
import th.co.aware.common.pdf.entity.Pdf;
import th.co.aware.common.pdf.entity.PdfRepository;
import th.co.aware.common.pdf.logic.KafkaProcessor;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaTest {

    @Autowired
    private KafkaProcessor kafkaProcessor;
    @Autowired
    private PdfRepository repo;

    @Test
    public void createViaKafka() {
        ReportRequest request = new ReportRequest("opal", "far", "away", "file");
        kafkaProcessor.create().send(MessageBuilder.withPayload(request).build());

        List<Pdf> pdfList = repo.findByName("away");
        Assert.assertNotEquals(0, pdfList.size());
        Assert.assertNotNull(pdfList.get(0).getId());
        Assert.assertEquals("file", pdfList.get(0).getPayload());

        repo.delete(pdfList.get(0));
        Assert.assertNull(pdfList.get(0));
    }
}
