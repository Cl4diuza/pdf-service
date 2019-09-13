package th.co.aware.common.pdf.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdfRepository extends JpaRepository<Pdf, String> {

    List<Pdf> findByName(String name);
}
