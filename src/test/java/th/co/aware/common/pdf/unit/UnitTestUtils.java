package th.co.aware.common.pdf.unit;

import th.co.aware.common.pdf.entity.Pdf;

import java.util.Arrays;
import java.util.List;

public class UnitTestUtils {

    public static List<Pdf> genPdf() {
        return Arrays.asList(new Pdf("template1", "opal1", "pdf", "payload1"),
                new Pdf("template2", "opal2", "pdf", "payload2"),
                new Pdf("template3", "opal3", "pdf", "payload3"));
    }
}
