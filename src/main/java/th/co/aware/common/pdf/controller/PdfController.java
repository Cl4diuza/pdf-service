package th.co.aware.common.pdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.aware.common.pdf.dto.ReportRequest;
import th.co.aware.common.pdf.entity.Pdf;
import th.co.aware.common.pdf.logic.PdfService;

import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService service;

    private HttpHeaders headers = new HttpHeaders();

    @GetMapping("/get")
    public ResponseEntity<List<Pdf>> getAll() {
        List<Pdf> pdfList = service.getAll();
        return new ResponseEntity<>(pdfList, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> hello(@RequestBody ReportRequest request) {
        headers.add("Content-Disposition", "inline; filename=report.pdf");
        return new ResponseEntity<>(new InputStreamResource(service.create(request)), headers, HttpStatus.CREATED);
    }

}
