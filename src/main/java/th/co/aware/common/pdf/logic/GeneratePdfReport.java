package th.co.aware.common.pdf.logic;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Slf4j
public class GeneratePdfReport {

    public static ByteArrayInputStream generateReport(String content) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            // prepare content
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Chunk chunk = new Chunk(content, font);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(chunk);
            document.close();

        } catch (DocumentException e) {
            log.error("Error occurred: {0}", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
