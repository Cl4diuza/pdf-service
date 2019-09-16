package th.co.aware.common.pdf.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component("storageClientMock")
public class StorageClientMock implements StorageClient {

    @Override
    public void send(ByteArrayInputStream stream) {
        log.info("streaming");
        log.info("sent to storage");
    }
}
