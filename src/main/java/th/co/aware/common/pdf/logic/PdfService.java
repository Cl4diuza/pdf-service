package th.co.aware.common.pdf.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
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

//    @StreamListener(KafkaProcessor.CREATE)
    public ByteArrayInputStream create(ReportRequest request) {
        Pdf pdf = toEntity(request);
        repo.save(pdf);
        ByteArrayInputStream bis = GeneratePdfReport.generateReport(pdf.getPayload());
        return bis;
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

}
