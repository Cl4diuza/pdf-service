package th.co.aware.common.pdf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pdf")
public class Pdf {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String userId;
    @NotNull
    @NotBlank
    private String service;
    private String framework;
    @NotNull
    @NotBlank
    private String payload;

    public Pdf(String name, String userId, String service, String payload) {
        super();
        this.name = name;
        this.userId = userId;
        this.service = service;
        this.payload = payload;
    }
}
