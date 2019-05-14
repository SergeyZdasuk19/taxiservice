package taxiservice.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EntityTest {
    private Person person;
    private Point firstPoint;
    private Point secondPoint;
    private Car car;

    @BeforeClass
    public void init() {
        person = new Person("Сергей", "Здасюк", "+375447715198");
        secondPoint = new Point(53.883341, 27.449910);
        firstPoint = new Point(53.929866, 27.763934);
        car = new Car("Мазда", "cx-5", "Седан", 1, "9000 АТ-7");
    }

    @Test
    public void testCheckMobile() {
        Assert.assertTrue(person.checkMobile(person.getPhoneNumber()));
    }

    @Test
    public void testCheckDistance() {
        Assert.assertTrue(new Point().checkDistance(firstPoint, secondPoint));
    }

    @Test
    public void testCheckCarType() {
        Assert.assertEquals("Ошибка",new Car().checkCarType(car.getSeatsnumber()));
    }
}