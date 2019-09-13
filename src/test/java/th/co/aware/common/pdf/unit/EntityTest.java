package th.co.aware.common.pdf.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import th.co.aware.common.pdf.entity.Pdf;

import javax.validation.ConstraintViolationException;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityTest {

    @Autowired
    private TestEntityManager manager;

    @Test
    public void CRUDEntity() {

        // create
        Pdf pdf = new Pdf(null, "ss", "s", "s", "s", "s");
        pdf = manager.persistAndFlush(pdf);
        Assert.assertNotNull(pdf);
        Assert.assertNotNull(pdf.getId());

        // read
        Pdf other = manager.find(Pdf.class, pdf.getId());
        Assert.assertEquals("ss", other.getName());

        // update
        other.setPayload("new");
        manager.persistAndFlush(other);
        Pdf updated = manager.find(Pdf.class, pdf.getId());
        Assert.assertEquals("new", updated.getPayload());

        // delete
        manager.remove(other);
        other = manager.find(Pdf.class, other.getId());
        Assert.assertNull(other);
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullName() {
        Pdf pdf = new Pdf(null, null, "s", "s", "s", "s");
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void blankName() {
        Pdf pdf = new Pdf(null, "", "s", "s", "s", "s");
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullUser() {
        Pdf pdf = new Pdf(null, "name", null, "s", "s", "s");
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void blankUser() {
        Pdf pdf = new Pdf(null, "name", "", "s", "s", "s");
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullService() {
        Pdf pdf = new Pdf(null, "name", "s", null, "s", "s");
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void blankService() {
        Pdf pdf = new Pdf(null, "name", "s", "", "s", "s");
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullPayload() {
        Pdf pdf = new Pdf(null, "name", "s", "s", "s", null);
        manager.persistAndFlush(pdf);
    }

    @Test(expected = ConstraintViolationException.class)
    public void blankPayload() {
        Pdf pdf = new Pdf(null, "name", "s", "s", "s", "");
        manager.persistAndFlush(pdf);
    }

}
