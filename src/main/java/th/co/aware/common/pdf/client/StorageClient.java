package th.co.aware.common.pdf.client;

import java.io.ByteArrayInputStream;

public interface StorageClient {

    void send(ByteArrayInputStream stream);
}
