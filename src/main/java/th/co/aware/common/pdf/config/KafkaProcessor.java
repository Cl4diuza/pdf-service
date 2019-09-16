package th.co.aware.common.pdf.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface KafkaProcessor {

    String CREATE = "create";
    String UPDATE = "update";

    @Input(KafkaProcessor.CREATE)
    SubscribableChannel create();

    @Output(KafkaProcessor.UPDATE)
    MessageChannel update();
}
