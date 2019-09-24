package th.co.aware.common.pdf.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import th.co.aware.common.pdf.client.StorageClient;
import th.co.aware.common.pdf.client.StorageClientMock;

@Configuration
@EnableBinding(KafkaProcessor.class)
public class PdfConfig {

    @Bean
    public StorageClient client() {
        return new StorageClientMock();
    }
}
