package th.co.aware.common.pdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get")
    public ResponseEntity<List<Pdf>> getAll() {
        List<Pdf> pdfList = service.getAll();
        return new ResponseEntity<>(pdfList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPdf(@RequestBody ReportRequest request) {
        service.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
