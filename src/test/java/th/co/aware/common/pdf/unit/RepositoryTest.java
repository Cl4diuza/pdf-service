package th.co.aware.common.pdf.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import th.co.aware.common.pdf.entity.Pdf;
import th.co.aware.common.pdf.entity.PdfRepository;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private PdfRepository repo;

    @Test
    public void CRUDRepo() {
        Pdf pdf = new Pdf(null, "ss", "s", "s", "s", "s");
        pdf = repo.save(pdf);
        Assert.assertNotNull(pdf);
        Assert.assertNotNull(pdf.getId());

        Pdf find = repo.findById(pdf.getId()).orElse(null);
        assert find != null;
        Assert.assertEquals("ss", find.getName());

        repo.delete(pdf);
        find = repo.findById(pdf.getId()).orElse(null);
        Assert.assertNull(find);
    }

    @Test
    public void findByNameRepo() {
        Pdf pdf = new Pdf(null, "name", "user", "s", "s", "s");
        pdf = repo.save(pdf);
        Assert.assertNotNull(pdf);

        List<Pdf> pdfList = repo.findByName("name");
        Assert.assertEquals("name", pdfList.get(0).getName());

        repo.delete(pdfList.get(0));
        pdfList = repo.findByName("name");
        Assert.assertEquals(0, pdfList.size());

    }


}
