package th.co.aware.common.pdf.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import th.co.aware.common.pdf.client.StorageClient;
import th.co.aware.common.pdf.config.KafkaProcessor;
import th.co.aware.common.pdf.dto.ReportRequest;
import th.co.aware.common.pdf.entity.Pdf;
import th.co.aware.common.pdf.entity.PdfRepository;

import java.io.ByteArrayInputStream;
import java.util.List;

@Slf4j
@Service
public class PdfService {

    @Autowired
    private PdfRepository repo;
    @Autowired
    private StorageClient client;

    @StreamListener(KafkaProcessor.CREATE)
    public void listener(ReportRequest request) {
        this.create(request);
    }

    public void create(ReportRequest request) {
        checkParamIn(request);
        Pdf pdf = toEntity(request);
        repo.save(pdf);

        // TODO get template to send to generate report class
        // String template = pdf.getName();
        // generateReport(template, pdf.getPayload());

        ByteArrayInputStream bis = GeneratePdfReport.generateReport(pdf.getPayload());
        // TODO storage client
        client.send(bis);
    }

    public List<Pdf> getAll() {
        return repo.findAll();
    }

    private Pdf toEntity(ReportRequest request) {
        Pdf pdf = new Pdf();
        pdf.setUserId(request.getUserId());
        pdf.setName(request.getName());
        pdf.setPayload(request.getPayload());
        pdf.setService(request.getService());
        return pdf;
    }

    private void checkParamIn(ReportRequest request) {
        Assert.notNull(request, "request.not.null");
        Assert.notNull(request.getName(), "name.not.null");
        Assert.isTrue(!StringUtils.isEmpty(request.getName()), "name.not.empty");
        Assert.notNull(request.getUserId(), "user.not.null");
        Assert.isTrue(!StringUtils.isEmpty(request.getUserId()), "user.not.empty");
        Assert.notNull(request.getService(), "service.not.null");
        Assert.isTrue(!StringUtils.isEmpty(request.getService()), "service.not.empty");
        Assert.notNull(request.getPayload(), "payload.not.null");
        Assert.isTrue(!StringUtils.isEmpty(request.getPayload()), "payload.not.empty");
    }

}
