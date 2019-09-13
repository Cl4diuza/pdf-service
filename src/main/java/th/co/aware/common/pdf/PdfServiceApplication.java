package th.co.aware.common.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import th.co.aware.common.pdf.logic.KafkaProcessor;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
public class PdfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfServiceApplication.class, args);
    }

}
