package th.co.aware.common.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {

    private String userId;
    private String service;
    private String name;
    private String payload;
}
