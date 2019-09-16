package th.co.aware.common.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import th.co.aware.common.pdf.client.StorageClient;
import th.co.aware.common.pdf.client.StorageClientMock;
import th.co.aware.common.pdf.config.KafkaProcessor;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
public class PdfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfServiceApplication.class, args);
    }

    @Bean
    public StorageClient client() {
        return new StorageClientMock();
    }

}
